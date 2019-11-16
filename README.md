# Aho-Corasick Search

[![Build Status](https://github.com/m09/aho-corasick/workflows/CI/badge.svg)](https://github.com/m09/aho-corasick/actions?workflow=CI)
[![Deploy Status](https://github.com/m09/aho-corasick/workflows/Deploy/badge.svg)](https://github.com/m09/aho-corasick/actions?workflow=Deploy)
[![Maven Central version](https://img.shields.io/maven-central/v/eu.crydee/aho-corasick.svg)](https://search.maven.org/search?q=g:eu.crydee%20a:aho-corasick)
[![codecov](https://codecov.io/gh/m09/aho-corasick/branch/master/graph/badge.svg)](https://codecov.io/gh/m09/aho-corasick)

Generic implementation of the Aho-Corasick search algorithm. If you want a library to perform an Aho-Corasick search on strings only and not a generic alphabet, I'd recommend using [Robert Bor's implementation](https://github.com/robert-bor/aho-corasick) instead of this library, it has many goodies for the string use-case.

A generic alphabet can be anything from pairs of token and part of speech in an NLP application to type of log entries in a security application…

See also [the original paper by Aho and Corasick](http://dl.acm.org/citation.cfm?id=360855).

## Requirements

To use this library, you need Java 8 and Maven 3.

## Installation

Refer to the [Maven Central page](https://search.maven.org/search?q=g:eu.crydee%20a:aho-corasick) to find the installation instructions for your build tool or to download the jar directly.

## Usage

To use the Aho-Corasick search algorithm, you first need to pass to the constructor of `AhoCorasick` the list of patterns you want to detect. A pattern is represented by an array of objects, so you need to pass a list of arrays to the constructor. For example, if our alphabet is the `Character` type and we want to be able to detect the patterns “hu”, “she” and “hers”, we can create the list of patterns as follows, using Java 8 functional idioms:

```java
List<Character[]> patterns = Stream.of(
        "hu",
        "she",
        "hers")
        .map(s -> ArrayUtils.toObject(s.toCharArray()))
        .collect(Collectors.toList());
```

Once we have the list of patterns we can create the search object:

```java
AhoCorasick<Character> ac = new AhoCorasick<>(patterns);
```

This builds the automaton required to efficiently search any text with all your patterns.

Now you have to prepare the texts you want to search on as arrays. For example, here, if we wanted to test the “hushers” text, we'd code (using Apache Commons Lang 3 to ease the arrays manipulation):

```java
Character[] text = ArrayUtils.toObject("hushers".toCharArray());
```

You can now choose a search method to detect patterns in your text. The choice depends on your use-case:

- `searchPosToPattern` will return a Guava multimap whose keys are positions in the text, and values are the patterns whose end matched at that position.

- `searchPatternToPos` will return a Guava multimap whose keys are the patterns successfully matched and values are the end positions of the spans where they matched. If you do not want to manipulate either of those Guava multimaps, you can use the `AhoCorasickHelpers` class to produce a sorted set of `Occurrence`s instead. An `Occurrence` is just a triple (`begin`, `end`, `patternIndex`). `begin` and `end` specify the span where `patterns.get(patternIndex)` was matched (`end` is inclusive). The occurrences are ordered by smallest `begin`, largest `end` and smallest `patternIndex`:

```java
SortedSet<Occurrence> result
        = AhoCorasickHelpers.toOccurrenceList(
                ac.searchPosToPattern(text),
                patterns,
                false);
// result is {(0, 1, 0), (2, 4, 1), (3, 6, 2)}
```
