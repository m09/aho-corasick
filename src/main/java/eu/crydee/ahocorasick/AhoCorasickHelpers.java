package eu.crydee.ahocorasick;

import com.google.common.collect.SetMultimap;
import java.util.AbstractMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class AhoCorasickHelpers {

    public static <T> SortedSet<Occurrence> toOccurrenceList(
            SetMultimap<Integer, Integer> searchResult,
            List<T[]> patterns,
            boolean isPatternToPos) {
        return searchResult.entries().stream()
                .map(e -> isPatternToPos
                                ? new AbstractMap.SimpleEntry<>(
                                        e.getValue(),
                                        e.getKey())
                                : e)
                .map(e -> new Occurrence(
                                e.getKey()
                                - patterns.get(e.getValue()).length
                                + 1,
                                e.getKey(),
                                e.getValue()))
                .collect(Collectors.toCollection(() -> new TreeSet<>()));
    }
}
