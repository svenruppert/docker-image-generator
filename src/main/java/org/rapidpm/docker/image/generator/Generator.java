package org.rapidpm.docker.image.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;

public class Generator {


  public static final String TARGET = "_data";

  //{0} maven version nr : 3.6.1
  //{1} jdk version - adopt-openj9:1.10.0-2
  private static final String MVN_TEMPLATE = "FROM svenruppert/{1}\n"
                                             + "MAINTAINER sven.ruppert@gmail.com\n"
                                             + "\n"
                                             + "ARG MAVEN_VERSION={0}\n"
                                             + "ARG USER_HOME_DIR=\"/root\"\n"
                                             + "ARG BASE_URL=http://mirror.synyx.de/apache/maven/maven-3/{0}/binaries\n"
                                             + "\n"
                                             + "RUN mkdir -p /usr/share/maven /usr/share/maven/ref \\\n"
                                             + "  && curl -o /tmp/apache-maven.tar.gz http://mirror.synyx.de/apache/maven/maven-3/{0}/binaries/apache-maven-{0}-bin.tar.gz \\\n"
                                             + "  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \\\n"
                                             + "  && rm -f /tmp/apache-maven.tar.gz \\\n"
                                             + "  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn\n"
                                             + "\n"
                                             + "ENV MAVEN_HOME /usr/share/maven\n"
                                             + "ENV MAVEN_CONFIG \"$USER_HOME_DIR/.m2\"\n"
                                             + "\n"
                                             + "CMD [\"mvn\"]";


  //{0} mvn version nr - 3.6.1
  //{1} jdk name - adopt-openj9
  //{2} jdk tag/version nr - 1.10.0-2
  private static final String MVN_BUILD_SH = "if docker_tag_exists svenruppert/maven-{0}-{1} {2}; then\n"
                                             + "    echo skip building, image already existing - svenruppert/maven-{0}-{1} {2}\n"
                                             + "else\n"
                                             + "    echo start building the images\n"
                                             + "    docker build -t svenruppert/maven-{0}-{1} .\n"
                                             + "    docker tag svenruppert/maven-{0}-{1}:latest svenruppert/maven-{0}-{1}:{2}\n"
                                             + "    docker push svenruppert/maven-{0}-{1}:{2}"
                                             + "\n"
                                             + "fi";


  public static final String DOCKERFILE = "Dockerfile";
  public static final String BUILDFILE  = "build.sh";


  //{0} - docker image name openjdk-ri@1.7.75 -> openjdk-ri@1.7.75
  private static String DOCKER_TEMPLATE = "FROM buildpack-deps:buster-curl\n"
                                          + "LABEL maintainer=\"Sven Ruppert sven.ruppert@gmail.com\" description=\"{0}\"\n"
                                          + "\n"
                                          + "ARG USER_HOME_DIR=\"/root\"\n"
                                          + "\n"
                                          + "RUN curl -sL https://github.com/shyiko/jabba/raw/master/install.sh | \\\n"
                                          + "    JABBA_COMMAND=\"install {1} -o /jdk\" bash\n"
                                          + "\n"
                                          + "ENV JAVA_HOME /jdk\n"
                                          + "ENV PATH $JAVA_HOME/bin:$PATH\n"
                                          + "\n"
                                          + "RUN java -version\n"
                                          + "\n"
                                          + "CMD [\"java\"]";


  //{0} - docker image name openjdk-ri@1.7.75 -> openjdk-ri
  //{1} - image tag openjdk-ri@1.7.75 -> 1.7.75
  private static final String PREFIX_BUILD_SH = "#!/bin/bash\n"
                                                + "function docker_tag_exists() {\n"
                                                + "    EXISTS=$(curl -s  https://hub.docker.com/v2/repositories/$1/tags/?page_size=10000 | jq -r \"[.results | .[] | .name == \\\"$2\\\"] | any\")\n"
                                                + "    test $EXISTS = true\n"
                                                + "}\n";


  private static final String BUILD_SH = "if docker_tag_exists svenruppert/{0} {1}; then\n"
                                         + "    echo skip building, image already existing - svenruppert/{0}:{1}\n"
                                         + "else\n"
                                         + "    echo start building the images\n"
                                         + "    docker build -t svenruppert/{0} .\n"
                                         + "\n"
                                         + "    docker tag svenruppert/{0}:latest svenruppert/{0}:{1}\n"
                                         + "    docker push svenruppert/{0}:{1}"
                                         + "\n"
                                         + "fi";


  public static void main(String[] args) {
    String[] versionArray = VERSIONS.split("\n");
    System.out.println("length = " + versionArray.length);

    final String[] mvnVersionArray = MVN_VERSIONS.split("\n");


    //Dockerfile MVN
    final Map<String, List<DockerImageInfo>> dockerImageInfos = Arrays.stream(versionArray)
                                                                      .map(version -> version.split("@"))
                                                                      .flatMap(v -> {
                                                                        String name = (v.length == 2)
                                                                                      ? v[0]
                                                                                      : "oracle-jdk";
                                                                        String tag = (v.length == 2)
                                                                                     ? v[1]
                                                                                     : v[0];

                                                                        String version = name + "@" + tag;
                                                                        String buildSH = PREFIX_BUILD_SH
                                                                                         + "\n"
                                                                                         + format(BUILD_SH, name, tag);
                                                                        return Arrays.stream(mvnVersionArray)
                                                                                     .map(
                                                                                         mvnVersion -> new DockerImageInfo(
                                                                                             format(DOCKER_TEMPLATE,
                                                                                                    version,
                                                                                                    (v.length == 2)
                                                                                                    ? version
                                                                                                    : tag), buildSH,
                                                                                             version, mvnVersion));


                                                                      })
                                                                      .peek(Generator::dockerfileJDK)
                                                                      .peek(Generator::dockerfileMVN)
                                                                      .peek(Generator::buildfileJDK)
                                                                      .peek(Generator::buildfileMVN)
                                                                      .collect(Collectors.groupingBy(
                                                                          DockerImageInfo::getVersion,
                                                                          Collectors.toList()));
//                                                   .collect(toList());


    final File folder = new File(TARGET);
    try {
      FileOutputStream fileOutputStream = new FileOutputStream(new File(folder, "build_all.sh"));
      fileOutputStream.write("#!/bin/bash\n".getBytes());

      dockerImageInfos.keySet()
                      .stream()
                      .sorted()
                      .forEachOrdered(p -> {
                        try {
                          fileOutputStream.write(
                              format("cd {0} ; chmod 777 build.sh ; ./build.sh ; cd .. ;\n", p).getBytes());

                        } catch (IOException e) {
                          e.printStackTrace();
                        }
                      });
      fileOutputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    try {
      FileOutputStream fileOutputStream = new FileOutputStream(new File(folder, "build_all_mvn.sh"));
      fileOutputStream.write("#!/bin/bash\n".getBytes());

      dockerImageInfos.values()
                      .stream()
                      .flatMap(Collection::stream)
                      .sorted(Comparator.comparing(o -> (o.version + "-" + o.versionMvn)))
                      .forEachOrdered(p -> {
                        try {
                          fileOutputStream.write(
                              format("cd {0}_maven_{1} ; chmod 777 build.sh ; ./build.sh ; cd .. ;\n", p.version,
                                     p.versionMvn).getBytes());

                        } catch (IOException e) {
                          e.printStackTrace();
                        }
                      });
      fileOutputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }


  }

  private static void dockerfileMVN(DockerImageInfo p) {
    File folder = new File(TARGET, p.getVersion() + "_maven_" + p.versionMvn);
    folder.mkdirs();
    try {
      FileOutputStream dockerFileOutputStream = new FileOutputStream(new File(folder, DOCKERFILE));
      String           version                = p.version.replace("@", ":");
      dockerFileOutputStream.write(format(MVN_TEMPLATE, p.versionMvn, version).getBytes());
      dockerFileOutputStream.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void buildfileJDK(DockerImageInfo p) {
    File folder = new File(TARGET, p.getVersion());
    folder.mkdirs();
    try {
      FileOutputStream buildFileOutputStream = new FileOutputStream(new File(folder, BUILDFILE));
      buildFileOutputStream.write(p.buildFile.getBytes());
      buildFileOutputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void buildfileMVN(DockerImageInfo p) {
    File folder = new File(TARGET, p.getVersion() + "_maven_" + p.versionMvn);
    folder.mkdirs();
    try {
      FileOutputStream buildFileOutputStream = new FileOutputStream(new File(folder, BUILDFILE));
      buildFileOutputStream.write((PREFIX_BUILD_SH + "\n" + format(MVN_BUILD_SH, p.versionMvn, p.version.split("@")[0],
                                                                   p.version.split("@")[1])).getBytes());
      buildFileOutputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void dockerfileJDK(DockerImageInfo p) {
    File folder = new File(TARGET, p.getVersion());
    folder.mkdirs();
    try {
      FileOutputStream dockerFileOutputStream = new FileOutputStream(new File(folder, DOCKERFILE));
      dockerFileOutputStream.write(p.dockerFile.getBytes());
      dockerFileOutputStream.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  private static class DockerImageInfo {
    private String dockerFile;
    private String buildFile;
    private String version;
    private String versionMvn;

    public DockerImageInfo(String dockerFile, String buildFile, String version, String versionMvn) {
      this.dockerFile = dockerFile;
      this.buildFile  = buildFile;
      this.version    = version;
      this.versionMvn = versionMvn;
    }

    public String getDockerFile() {
      return dockerFile;
    }

    public String getBuildFile() {
      return buildFile;
    }

    public String getVersion() {
      return version;
    }

    public String getVersionMvn() {
      return versionMvn;
    }
  }


  public static final String MVN_VERSIONS = "3.6.2\n"
                                            + "3.6.1\n"
                                            + "3.6.0\n"
                                            + "3.5.4\n"
                                            + "3.3.9\n"
                                            + "3.2.5\n"
                                            + "3.1.1\n"
                                            + "3.0.5\n";

  //generate this list with -> jabba ls-remote --os linux
  private static String VERSIONS = "1.12.0\n"
                                   + "1.12.0-2\n"
                                   + "adopt@1.13.0-0\n"
                                   + "adopt@1.12.0-2\n"
                                   + "adopt@1.12.0-1\n"
                                   + "adopt@1.12.0-0\n"
                                   + "adopt@1.11.0-4\n"
                                   + "adopt@1.11.0-3\n"
                                   + "adopt@1.11.0-2\n"
                                   + "adopt@1.11.0-1\n"
                                   + "adopt@1.11.0-0\n"
                                   + "adopt@1.10.0-2\n"
                                   + "adopt@1.9.0-4\n"
                                   + "adopt@1.9.0-0\n"
                                   + "adopt@1.8.0-222\n"
                                   + "adopt@1.8.0-212\n"
                                   + "adopt@1.8.0-202\n"
                                   + "adopt@1.8.0-192\n"
                                   + "adopt@1.8.0-181\n"
                                   + "adopt@1.8.0-172\n"
                                   + "adopt-openj9@1.13.0-0\n"
                                   + "adopt-openj9@1.12.0-2\n"
                                   + "adopt-openj9@1.12.0-1\n"
                                   + "adopt-openj9@1.12.0-0\n"
                                   + "adopt-openj9@1.11.0-4\n"
                                   + "adopt-openj9@1.11.0-3\n"
                                   + "adopt-openj9@1.11.0-2\n"
                                   + "adopt-openj9@1.11.0-1\n"
                                   + "adopt-openj9@1.11.0-0\n"
                                   + "adopt-openj9@1.10.0-2\n"
                                   + "adopt-openj9@1.9.0-4\n"
                                   + "adopt-openj9@1.8.0-222\n"
                                   + "adopt-openj9@1.8.0-212\n"
                                   + "adopt-openj9@1.8.0-202\n"
                                   + "adopt-openj9@1.8.0-192\n"
                                   + "adopt-openj9@1.8.0-181\n"
                                   + "adopt-openj9@1.8.0-162\n"
                                   + "amazon-corretto@1.11.0-4.11.1\n"
                                   + "amazon-corretto@1.8.222-10.1\n"
                                   + "graalvm@19.2.0\n"
                                   + "graalvm@19.2.0-1\n"
                                   + "graalvm@19.1.1\n"
                                   + "graalvm@19.1.0\n"
                                   + "graalvm@19.0.2\n"
                                   + "graalvm@19.0.0\n"
                                   + "graalvm@1.0.0-16\n"
                                   + "graalvm@1.0.0-15\n"
                                   + "graalvm@1.0.0-14\n"
                                   + "graalvm@1.0.0-13\n"
                                   + "graalvm@1.0.0-12\n"
                                   + "graalvm@1.0.0-11\n"
                                   + "graalvm@1.0.0-10\n"
                                   + "graalvm@1.0.0-9\n"
                                   + "graalvm@1.0.0-8\n"
                                   + "graalvm@1.0.0-7\n"
                                   + "graalvm@1.0.0-6\n"
                                   + "graalvm@1.0.0-5\n"
                                   + "graalvm@1.0.0-4\n"
                                   + "graalvm@1.0.0-3\n"
                                   + "graalvm@1.0.0-2\n"
                                   + "graalvm@1.0.0-1\n"
                                   + "ibm@1.8.0\n"
                                   + "ibm@1.8.0-5.41\n"
                                   + "ibm@1.8.0-5.40\n"
                                   + "ibm@1.8.0-5.6\n"
                                   + "ibm@1.7.1\n"
                                   + "ibm@1.7.1-4.50\n"
                                   + "ibm@1.7.1-4.45\n"
                                   + "ibm@1.7.0\n"
                                   + "ibm@1.7.0-10.50\n"
                                   + "ibm@1.7.0-10.45\n"
                                   + "liberica@1.13.0\n"
                                   + "liberica@1.13.0-0\n"
                                   + "liberica@1.12.0\n"
                                   + "liberica@1.12.0-2\n"
                                   + "liberica@1.12.0-1\n"
                                   + "liberica@1.12.0-0\n"
                                   + "liberica@1.11.0\n"
                                   + "liberica@1.11.0-4\n"
                                   + "liberica@1.11.0-3\n"
                                   + "liberica@1.11.0-2\n"
                                   + "liberica@1.11.0-1\n"
                                   + "liberica@1.11.0-0\n"
                                   + "liberica@1.10.0\n"
                                   + "liberica@1.10.0-2\n"
                                   + "liberica@1.8.222\n"
                                   + "liberica@1.8.212\n"
                                   + "liberica@1.8.202\n"
                                   + "liberica@1.8.192\n"
                                   + "openjdk@1.14.0-16\n"
                                   + "openjdk@1.13.0\n"
                                   + "openjdk@1.12.0\n"
                                   + "openjdk@1.12.0-2\n"
                                   + "openjdk@1.12.0-1\n"
                                   + "openjdk@1.11.0\n"
                                   + "openjdk@1.11.0-2\n"
                                   + "openjdk@1.11.0-1\n"
                                   + "openjdk@1.10.0\n"
                                   + "openjdk@1.10.0-2\n"
                                   + "openjdk@1.10.0-1\n"
                                   + "openjdk@1.9.0\n"
                                   + "openjdk@1.9.0-4\n"
                                   + "openjdk@1.9.0-1\n"
                                   + "openjdk-ri@1.12.0\n"
                                   + "openjdk-ri@1.12.0-0\n"
                                   + "openjdk-ri@1.11.0\n"
                                   + "openjdk-ri@1.11.0-0\n"
                                   + "openjdk-ri@1.10.0\n"
                                   + "openjdk-ri@1.10.0-0\n"
                                   + "openjdk-ri@1.9.0\n"
                                   + "openjdk-ri@1.9.0-0\n"
                                   + "openjdk-ri@1.8.40\n"
                                   + "openjdk-ri@1.7.75\n"
                                   + "openjdk-shenandoah@1.11.0\n"
                                   + "openjdk-shenandoah@1.8.0\n"
                                   + "zulu@1.13.0\n"
                                   + "zulu@1.12.0\n"
                                   + "zulu@1.12.0-2\n"
                                   + "zulu@1.12.0-1\n"
                                   + "zulu@1.11.0\n"
                                   + "zulu@1.11.0-4\n"
                                   + "zulu@1.11.0-3\n"
                                   + "zulu@1.11.0-2\n"
                                   + "zulu@1.11.0-1\n"
                                   + "zulu@1.10.0\n"
                                   + "zulu@1.10.0-2\n"
                                   + "zulu@1.10.0-1\n"
                                   + "zulu@1.9.0\n"
                                   + "zulu@1.9.0-7\n"
                                   + "zulu@1.9.0-4\n"
                                   + "zulu@1.9.0-1\n"
                                   + "zulu@1.9.0-0\n"
                                   + "zulu@1.8.222\n"
                                   + "zulu@1.8.212\n"
                                   + "zulu@1.8.202\n"
                                   + "zulu@1.8.201\n"
                                   + "zulu@1.8.192\n"
                                   + "zulu@1.8.181\n"
                                   + "zulu@1.8.172\n"
                                   + "zulu@1.8.163\n"
                                   + "zulu@1.8.162\n"
                                   + "zulu@1.8.152\n"
                                   + "zulu@1.8.144\n"
                                   + "zulu@1.8.131\n"
                                   + "zulu@1.8.121\n"
                                   + "zulu@1.8.112\n"
                                   + "zulu@1.8.102\n"
                                   + "zulu@1.8.92\n"
                                   + "zulu@1.8.91\n"
                                   + "zulu@1.8.72\n"
                                   + "zulu@1.8.71\n"
                                   + "zulu@1.7.232\n"
                                   + "zulu@1.7.222\n"
                                   + "zulu@1.7.211\n"
                                   + "zulu@1.7.201\n"
                                   + "zulu@1.7.191\n"
                                   + "zulu@1.7.181\n"
                                   + "zulu@1.7.171\n"
                                   + "zulu@1.7.161\n"
                                   + "zulu@1.7.154\n"
                                   + "zulu@1.7.141\n"
                                   + "zulu@1.7.131\n"
                                   + "zulu@1.7.121\n"
                                   + "zulu@1.7.111\n"
                                   + "zulu@1.7.101\n"
                                   + "zulu@1.7.95\n"
                                   + "zulu@1.6.119\n"
                                   + "zulu@1.6.113\n"
                                   + "zulu@1.6.107\n"
                                   + "zulu@1.6.103\n"
                                   + "zulu@1.6.99\n"
                                   + "zulu@1.6.97\n"
                                   + "zulu@1.6.93\n"
                                   + "zulu@1.6.89\n"
                                   + "zulu@1.6.87\n"
                                   + "zulu@1.6.83\n"
                                   + "zulu@1.6.79\n"
                                   + "zulu@1.6.77";


}
