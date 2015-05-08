/*
 * Copyright 2015 m09.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.crydee.ahocorasick;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author m09
 */
public class OccurrenceTest {

    /**
     * Test of compareTo method, of class Occurrence.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Occurrence o1 = new Occurrence(1, 3, 2),
                o2 = new Occurrence(1, 3, 2),
                o3 = new Occurrence(1, 3, 1),
                o4 = new Occurrence(1, 3, 3),
                o5 = new Occurrence(1, 2, 2),
                o6 = new Occurrence(1, 4, 2),
                o7 = new Occurrence(0, 3, 2),
                o8 = new Occurrence(2, 3, 2);
        assertTrue(o1.compareTo(o2) == 0);
        assertTrue(o1.compareTo(o3) > 0);
        assertTrue(o1.compareTo(o4) < 0);
        assertTrue(o1.compareTo(o5) > 0);
        assertTrue(o1.compareTo(o6) < 0);
        assertTrue(o1.compareTo(o7) > 0);
        assertTrue(o1.compareTo(o8) < 0);
    }

    /**
     * Test of hashCode method, of class Occurrence.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        assertEquals(
                new Occurrence(0, 1, 2).hashCode(),
                new Occurrence(0, 1, 2).hashCode());
        assertNotEquals(
                new Occurrence(0, 1, 2).hashCode(),
                new Occurrence(0, 1, 3).hashCode());
        assertNotEquals(
                new Occurrence(0, 1, 2).hashCode(),
                new Occurrence(0, 2, 2).hashCode());
        assertNotEquals(
                new Occurrence(0, 1, 2).hashCode(),
                new Occurrence(1, 1, 2).hashCode());
    }

    /**
     * Test of equals method, of class Occurrence.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        assertEquals(
                new Occurrence(0, 1, 2),
                new Occurrence(0, 1, 2));
        assertNotEquals(
                new Occurrence(0, 1, 2),
                new Occurrence(0, 1, 3));
        assertNotEquals(
                new Occurrence(0, 1, 2),
                new Occurrence(0, 2, 2));
        assertNotEquals(
                new Occurrence(0, 1, 2),
                new Occurrence(1, 1, 2));
    }

    /**
     * Test of toString method, of class Occurrence.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        assertEquals("(0, 1, 2)", new Occurrence(0, 1, 2).toString());
    }
}
