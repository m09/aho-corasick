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

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author m09
 */
public class AhoCorasickTest {

    /**
     * Test of search method, of class AhoCorasick.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        Character[] text = ArrayUtils.toObject("hushers".toCharArray());
        List<Character[]> patterns = Stream.of(
                "hu",
                "she",
                "hers")
                .map(s -> ArrayUtils.toObject(s.toCharArray()))
                .collect(Collectors.toList());
        AhoCorasick<Character> ac = new AhoCorasick<>(patterns);
        SetMultimap<Integer, Integer> expResult = HashMultimap.create();
        expResult.put(1, 0);
        expResult.put(4, 1);
        expResult.put(6, 2);
        SetMultimap<Integer, Integer> result = ac.searchPosToPattern(text);
        assertEquals(expResult, result);
    }
}
