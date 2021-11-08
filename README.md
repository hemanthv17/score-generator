Project Name: - Score Generator

Description: - 
	This Project is capable of calculating score for a given text file with names. The process of calculation in this version is listed below:
	1. The file provided must contain names(both first name and last name can be included) enclosed in quotes, separated with a comma.
	2. Names can be included in a single line or multiple lines.
	3. Each letter is given a specific value. (Ex: A/a - 1, B/b - 2, C/c - 3... etc). Names are first sorted in an ascending fashion.
	4. The score of a name is achieved by adding all the values and multiplying that with the position of the name in the list.
	   (Ex:- For example LINDA, which is worth 12 + 9 + 14 + 4 + 1 = 40, is the 1st name in the list, total score is 40 * 1 = 40)
	5. The final file score is achieved by adding all the individual scores. A sample file can be found in the below path of the project.
	   "src/test/resources/testFile.txt"

Technologies/Tools used: -
1. Java v1.8
2. Maven v3.6.3
3. Slf4j v1.7.5
4. Powermock v2.0.7

How to run the Application: -
1. Download the zip file from the root folder or clone the project and save it a particualr location.
2. Extract the contents and run the below commands to setup the maven project.
	a. mvn compile
	b. mvn package
	c. mvn install
	d. java -Dfile.encoding=UTF-8 -classpath <target classes path>:<.m2 path>/.m2/repository/org/slf4j/slf4j-api/1.7.5/slf4j-api-1.7.5.jar:<.m2 path>/.m2/repository/org/slf4j/slf4j-simple/1.7.5/slf4j-simple-1.7.5.jar com.occ.utility.score.ScoreApplication <text file path>
	   ex: - java -Dfile.encoding=UTF-8 -classpath /Users/hemanthvuyyuru/Documents/occScreeningProblem/score-generator/target/classes:/Users/hemanthvuyyuru/.m2/repository/org/slf4j/slf4j-api/1.7.5/slf4j-api-1.7.5.jar:/Users/hemanthvuyyuru/.m2/repository/org/slf4j/slf4j-simple/1.7.5/slf4j-simple-1.7.5.jar com.occ.utility.score.ScoreApplication "/Users/hemanthvuyyuru/Downloads/OCC Take Home Coding names.txt"
	Note: - The file path should the first program argument. 
	
Test cases have been included in src/test/java package.

Java Documentation can be found in doc folder in the root project.

Contribution Opportunity: -
Additional scoring logic can be provided by implementing ScoreGenerator interface.