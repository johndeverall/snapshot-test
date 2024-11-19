// src/main/java/com/example/TestRunner.java
package com.example;

import org.junit.platform.engine.discovery.ClassNameFilter;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.PrintWriter;

public class TestRunner {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide test class name as argument");
            System.exit(1);
        }

        LauncherDiscoveryRequestBuilder requestBuilder = LauncherDiscoveryRequestBuilder.request()
            .filters(ClassNameFilter.includeClassNamePatterns(".*"));

        // Add all test classes to the request
        for (String className : args) {
            try {
                Class.forName(className); // Verify class exists
                requestBuilder.selectors(DiscoverySelectors.selectClass(className));
            } catch (ClassNotFoundException e) {
                System.out.println("Test class not found: " + className);
                System.exit(1);
            }
        }

        LauncherDiscoveryRequest request = requestBuilder.build();
        Launcher launcher = LauncherFactory.create();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);

        TestExecutionSummary summary = listener.getSummary();
        PrintWriter out = new PrintWriter(System.out, true);
        
        // Print results
        System.out.println("\nTest Results:");
        System.out.println("Tests Found: " + summary.getTestsFoundCount());
        System.out.println("Tests Started: " + summary.getTestsStartedCount());
        System.out.println("Tests Skipped: " + summary.getTestsSkippedCount());
        System.out.println("Tests Failed: " + summary.getTestsFailedCount());
        System.out.println("Tests Successful: " + summary.getTestsSucceededCount());
        System.out.println("Time: " + summary.getTimeFinished() + "ms");

        if (!summary.getFailures().isEmpty()) {
            System.out.println("\nFailures:");
            summary.printFailuresTo(out);
        }

        if (summary.getTestsFailedCount() == 0) {
            System.out.println("\nALL TESTS PASSED");
        } else {
            System.exit(1);
        }
    }
}
