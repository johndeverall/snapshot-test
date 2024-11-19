// src/main/java/com/example/TestRunner.java
package com.example;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide test class name as argument");
            System.exit(1);
        }

        try {
            Class<?>[] testClasses = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                testClasses[i] = Class.forName(args[i]);
            }
            
            Result result = JUnitCore.runClasses(testClasses);
            
            System.out.println("\nTest Results:");
            System.out.println("Tests run: " + result.getRunCount());
            System.out.println("Failed: " + result.getFailureCount());
            System.out.println("Ignored: " + result.getIgnoreCount());
            System.out.println("Time: " + result.getRunTime() + "ms");
            
            if (!result.getFailures().isEmpty()) {
                System.out.println("\nFailures:");
                for (Failure failure : result.getFailures()) {
                    System.out.println(failure.toString());
                }
            }
            
            if (result.wasSuccessful()) {
                System.out.println("\nALL TESTS PASSED");
            } else {
                System.exit(1);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Test class not found: " + e.getMessage());
            System.exit(1);
        }
    }
}

