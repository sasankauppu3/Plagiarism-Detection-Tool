# Plagiarism Detection Tool
Team Project: 
- Veera Venkata Sasanka Uppu
- Amritansh Tripathi
- Gautam Baghel
- Xin Li

## Description : 

- Plagiarism Detection Tool is a tool for instructors to detect plagiarism on multiple student submissions (Python code files) with scores and compare them line-by-line for plagiarism. This tool has been built and deployed as a web-based system using Java SpringBoot, Jenkins for Continuous Integration and deployed on AWS. 
- Various Abstract syntax tree matching algorithms have been implemented for detecting plagiarism, and a gradient descent algorithm has been used on the outputs of the algorithms as a Machine Learning model to improve the plagiarism score prediction.

## Getting Started

- Clone the repo on local machine to get started.
- Open project using the PlagirismDetector folder
- Open as a maven project
- Use Java 8 as the SDK
- Run mvn clean install
- If the project is properly setup it could be run by the IDE
- Alternatively run following command through terminal 
```
 java -jar target/plagiarismdetector-0.0.1-SNAPSHOT.jar
```
- visit [Localhost](http://127.0.0.1:8080) to see the running server


### Prerequisites

- Java IDE - Eclipse, Intellij , Atom etc.  running on local machine

## Course Webpage

For more info visit - [Course Webpage](https://course.ccs.neu.edu/cs5500/)

## Acknowledgments

* **Michael Weintraub** - *Instructor* - [Info](https://www.ccis.northeastern.edu/people/michael-weintraub/)
* **Jose Annunziato** - *Instructor* - [Info](https://www.ccis.northeastern.edu/people/jose-annunziato/)
