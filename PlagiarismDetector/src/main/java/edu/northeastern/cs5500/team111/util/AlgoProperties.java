package edu.northeastern.cs5500.team111.util;

import edu.northeastern.cs5500.team111.plagiarismdetector.PlagiarismdetectorApplication;
import org.apache.log4j.Level;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by lixin on 4/20/18.
 */
public class AlgoProperties {
    public AlgoProperties() {

    }
    private static Properties getProp(){
        String configFilePath = Thread.currentThread().getContextClassLoader().getResource("algo.properties").getPath();
        Properties properties = new Properties();
        System.out.println(configFilePath);
        try {
            properties.load(new FileInputStream(configFilePath));
        } catch (IOException e) {
            PlagiarismdetectorApplication.getLoggerInstance().error(e.getMessage());
        }
        return properties;
    }
    public static String load(String name){
        return getProp().getProperty(name);
    }

    public static double loadFloat(String name){
        return Double.parseDouble(getProp().getProperty(name));

    }

    public static int loadInt(String name){
        return Integer.parseInt(getProp().getProperty(name));
    }
}
