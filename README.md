
# Pre Condition
1. Download the source code folder from the github url: https://github.com/pavankumar836/qaTechnicalTest
2. Download the Maven from https://maven.apache.org/download.cgi and extract it
3. System installed with Java 17

# Setup
1. In windows system go to Environment variables and add MAVEN_HOME in variable name and path of the Maven folder(extracted folder) Note: path of the folder should be only up to apache-maven-3.x.x
2. Add Maven Path to environment variable. The path of the maven should be %maven home %bin  ex: C:\apache-maven 3.x.x\bin
3. verify mvn -Version , if it displays the version then the installation was successful.

# Description of Project Code:
1. it is Rest Java based Project and JUnit Framework.
2. Maven will download the dependency libraries mentioned in the pom.xml file  from the maven repository.
3. Browse to the the github url :    and download the source code files
4. Open the command line window and browse to the source code folder and run the below command
   mvn -Dtest=ProductTests test
5. Maven will run the tests present in the ProductTests class and displayed the results
6. Results will be displayed on the command line


# Tested based on the following Assumptions
1. The max price field length is 16
2. Post requests don't return the response body
3. Duplicate and null product name values are accepted

# Limitations