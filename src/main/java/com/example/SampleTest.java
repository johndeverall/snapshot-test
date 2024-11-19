package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import au.com.origin.snapshots.junit5.SnapshotExtension;
import au.com.origin.snapshots.Expect;
import au.com.origin.snapshots.annotations.SnapshotName;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SnapshotExtension.class})
class SampleTest {
	
	private Expect expect;
	
	@SnapshotName("i_can_give_custom_names_to_my_snapshots")
    @Test
    public void toStringSerializationTest() {
        expect.toMatchSnapshot("Hello World");
    }
	
    @Test
    void testAddition() {
        assertEquals(4, 2 + 2, "2 + 2 should equal 4");
    }
    
    @Test
    void testSubtraction() {
        assertEquals(0, 2 - 2, "2 - 2 should equal 0");
    }

    @Test
    void testWithDisplayName() {
        assertTrue(3 > 2, "3 should be greater than 2");
    }
}
