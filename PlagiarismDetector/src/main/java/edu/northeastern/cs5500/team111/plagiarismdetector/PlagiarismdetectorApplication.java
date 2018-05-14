package edu.northeastern.cs5500.team111.plagiarismdetector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlagiarismdetectorApplication {

    public static void main(String[] args) {

        SpringApplication.run(PlagiarismdetectorApplication.class, args);
        getLoggerInstance().info("Started running Plagiarism Detector");
    }

    public static Logger getLoggerInstance(){
        return LoggerFactory.getLogger(PlagiarismdetectorApplication.class);
    }
}