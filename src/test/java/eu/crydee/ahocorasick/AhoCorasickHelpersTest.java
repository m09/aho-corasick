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

import com.google.common.collect.Sets;
import java.util.List;
import java.util.SortedSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author m09
 */
public class AhoCorasickHelpersTest {

    /**
     * Test of toOccurrenceList method, of class AhoCorasickHelpers.
     */
    @Test
    public void testToOccurrenceList() {
        Character[] text = ArrayUtils.toObject("hushers".toCharArray());
        List<Character[]> patterns = Stream.of(
                "hu",
                "she",
                "hers")
                .map(s -> ArrayUtils.toObject(s.toCharArray()))
                .collect(Collectors.toList());
        AhoCorasick<Character> ac = new AhoCorasick<>(patterns);
        SortedSet<Occurrence> expResult = Sets.newTreeSet();
        expResult.add(new Occurrence(0, 1, 0));
        expResult.add(new Occurrence(2, 4, 1));
        expResult.add(new Occurrence(3, 6, 2));
        SortedSet<Occurrence> result
                = AhoCorasickHelpers.toOccurrenceList(
                        ac.searchPosToPattern(text),
                        patterns,
                        false);
        assertEquals(expResult, result);
        result = AhoCorasickHelpers.toOccurrenceList(
                ac.searchPatternToPos(text),
                patterns,
                true);
        assertEquals(expResult, result);
    }

}
