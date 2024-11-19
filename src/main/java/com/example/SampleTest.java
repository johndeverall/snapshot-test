
// src/test/java/com/example/SampleTest.java
package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class SampleTest {
    @Test
    public void testAddition() {
        assertEquals(4, 2 + 2);
    }
    
    @Test
    public void testSubtraction() {
        assertEquals(0, 2 - 2);
    }
}
