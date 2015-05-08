Aho-Corasick Search
===
[![Build Status](https://travis-ci.org/m09/aho-corasick.svg?branch=master)](https://travis-ci.org/m09/aho-corasick)
[![Maven Central version](https://img.shields.io/maven-central/v/eu.crydee/aho-corasick.svg)](http://search.maven.org/#search|ga|1|a%3A%22aho-corasick%22) [![Coverage Status](https://coveralls.io/repos/m09/aho-corasick/badge.svg?branch=master)](https://coveralls.io/r/m09/aho-corasick?branch=master)

Generic implementation of the Aho-Corasick search algorithm. If you
want a library to perform an Aho-Corasick search on strings only and
not a generic alphabet, I'd recommend using
[Robert Bor's implementation][robertbor] instead of this library, it
has many goodies for the string use-case.

A generic alphabet can be anything from pairs of token and part of
speech in an NLP application to type of log entries in a security
application…

See http://dl.acm.org/citation.cfm?id=360855 for the original paper by
Aho and Corasick.

[robertbor]: https://github.com/robert-bor/aho-corasick

Requirements
---

To use this library, you need Java 8 and Maven 3.

Installation
---

With Maven, you can just add the following to the `dependencies`
section of your `pom.xml`:

```xml
    <dependency>
      <groupId>eu.crydee</groupId>
      <artifactId>aho-corasick</artifactId>
      <version>1.0.0</version>
    </dependency>
```

If you do not use maven, you can still [download][dl] the jar from
Maven Central and use it as appropriate.

[dl]: http://search.maven.org/remotecontent?filepath=eu/crydee/aho-corasick/1.0.0/aho-corasick-1.0.0.jar

Usage
-----

To use the Aho-Corasick search algorithm, you first need to pass to
the constructor of `AhoCorasick` the list of patterns you want to
detect. A pattern is represented by an array of objects, so you need
to pass a list of arrays to the constructor. For example, if our
alphabet is the `Character` type and we want to be able to detect the
patterns “hu”, “she” and “hers”, we can create the list of patterns as
follows, using Java 8 functional idioms:

    List<Character[]> patterns = Stream.of(
            "hu",
            "she",
            "hers")
            .map(s -> ArrayUtils.toObject(s.toCharArray()))
            .collect(Collectors.toList());

Once we have the list of patterns we can create the search object:

    AhoCorasick<Character> ac = new AhoCorasick<>(patterns);

This builds the automaton required to efficiently search any text with
all your patterns.

Now you have to prepare the texts you want to search on as arrays. For
example, here, if we wanted to test the “hushers” text, we'd code
(using Apache Commons Lang 3 to ease the arrays manipulation):

    Character[] text = ArrayUtils.toObject("hushers".toCharArray());

You can now choose a search method to detect patterns in your
text. The choice depends on your use-case:

- `searchPosToPattern` will return a Guava multimap whose keys are
  positions in the text, and values are the patterns whose end matched
  at that position.

- `searchPatternToPos` will return a Guava multimap whose keys are the
  patterns successfully matched and values are the end positions of
  the spans where they matched.

If you do not want to manipulate either of those Guava multimaps, you
can use the `AhoCorasickHelpers` class to produce a sorted set of
`Occurrence`s instead. An `Occurrence` is just a triple (`begin`,
`end`, `patternIndex`). `begin` and `end` specify the span where
`patterns.get(patternIndex)` was matched (`end` is inclusive). The
occurrences are ordered by smallest `begin`, largest `end` and
smallest `patternIndex`:

    SortedSet<Occurrence> result
            = AhoCorasickHelpers.toOccurrenceList(
                    ac.searchPosToPattern(text),
                    patterns,
                    false);
    // result is {(0, 1, 0), (2, 4, 1), (3, 6, 2)}
