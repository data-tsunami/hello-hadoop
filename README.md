hello-hadoop
============

Hello World Hadoop project... Eclipse + Maven + unit tests

# Project files & dirs

- _/conf_: config to be used for running MR on the cluster
- _/conf-local_: config to be used for running MR locally
- _/hadoop-1.2.0_: Hadoop binaries (recomended WITHOUT conf/ dir, to avoid mistakes)

To setup files on /conf directory, you can use `core-site.xml.sample` and `mapred-site.xml.sample` as reference.

# Before running

Before running the MR jobs you need to build the JAR and generate some data.

### Build the jar

	$ mvn clean package

### Generate sample data

	$ find /usr/share/doc > /tmp/MYDATA.txt

# Run the MR job locally using Ant

From Eclipse, run the `launch-local.xml` as an Ant script. By default the task `launch-local` is used, which does _not_ clean the output directory. You can use the `clean-and-launch` task to clean the output directory before the launch of the MR job.

# Run the MR job locally from CLI

### Run locally

	$ ./hadoop-1.2.0/bin/hadoop --config conf-local jar hello-hadoop-0.0.1-SNAPSHOT.jar \
		ar.com.datatsunami.hellohadoop.Launcher file:///tmp/MYDATA.txt file:///tmp/OUTPUT

# Run the MR job in the cluster

### Build the jar

	$ mvn clean package

### Copy the data to HDFS

	$ ./hadoop-1.2.0/bin/hadoop --config conf fs -copyFromLocal /tmp/MYDATA.txt /

### Launch!

	$ ./hadoop-1.2.0/bin/hadoop --config conf jar hello-hadoop-0.0.1-SNAPSHOT.jar \
		ar.com.datatsunami.hellohadoop.Launcher /MYDATA.txt /OUTPUT

### See the output directory:

	$ ./hadoop-1.2.0/bin/hadoop --config conf fs -ls /OUTPUT

# Some Maven recipes

### Setup Eclipse classpath using Maven

    $ mvn eclipse:eclipse

### Hadoop sources for use in Eclipse (doesn't works)

To download the Hadoop sources or javadocs DOESN'T WORKS with Maven, so you'll have to setup in Eclipse by yourself.

	$ mvn dependency:sources -DincludeGroupIds=org.apache.hadoop 
	(...)
	[INFO] The following files were skipped:
	[INFO]    org.apache.hadoop:hadoop-core:java-source:sources:1.2.0
	[INFO]    org.apache.hadoop:hadoop-test:java-source:sources:1.2.0
	(...)

	$ mvn dependency:resolve -DincludeGroupIds=org.apache.hadoop -Dclassifier=javadoc 
	(...)
	[INFO] The following files have NOT been resolved:
	[INFO]    org.apache.hadoop:hadoop-core:java-source:javadoc:1.2.0
	[INFO]    org.apache.hadoop:hadoop-test:java-source:javadoc:1.2.0
	(...)

# Web resources

## Eclipse + Hadoop

- http://www.thecloudavenue.com/search/label/gettingstarted

- http://www.thecloudavenue.com/p/hadoopresources.html

- Developing, testing and debugging Hadoop map/reduce jobs with Eclipse
    - http://stackoverflow.com/questions/11007423/developing-testing-and-debugging-hadoop-map-reduce-jobs-with-eclipse

- Open Source Big Data for the Impatient, Part 1: Hadoop tutorial: Hello World with Java, Pig, Hive, Flume, Fuse, Oozie, and Sqoop with Informix, DB2, and MySQL
	- http://www.ibm.com/developerworks/data/library/techarticle/dm-1209hadoopbigdata/

- http://www.philippeadjiman.com/blog/2009/12/07/hadoop-tutorial-part-1-setting-up-your-mapreduce-learning-playground/

- http://comments.gmane.org/gmane.comp.java.hadoop.mapreduce.user/10598

## Maven / Eclipse m2e

- Get source jar files attached to Eclipse for Maven-managed dependencies
    - http://stackoverflow.com/questions/310720/get-source-jar-files-attached-to-eclipse-for-maven-managed-dependencies

- Eclipse + MRUnit + Maven
	- https://github.com/onefoursix/mrunit-example

# TODOs

 - Use Maven task for Ant: https://maven.apache.org/ant-tasks/installation.html
 - Try other versions of Hadoop
 - Try debug of MR job from Eclipse
 - Try to find a way to attach Hadoop sources to Eclipse
    + https://issues.apache.org/jira/browse/HADOOP-8363
    + https://issues.apache.org/jira/browse/HADOOP-8498
    + https://issues.apache.org/jira/browse/MAPREDUCE-4035
    + http://stackoverflow.com/questions/12551977/repository-for-hadoop-stable-release-with-sources
    + https://github.com/apache/hadoop-common/pull/8

# License

    Copyright 2013 (C) Horacio G. de Oro - hgdeoro@gmail.com
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
    http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

