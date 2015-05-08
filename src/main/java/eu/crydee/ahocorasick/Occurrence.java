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

import com.google.common.primitives.Ints;

/**
 * Simple immutable data class.
 *
 * Used as a triple to delimit occurrences in result sets of an Aho-Corasick
 * search. Since it's immutable, the fields are accessible directly.
 *
 * @author m09
 */
public class Occurrence implements Comparable<Occurrence> {

    public final int begin;
    public final int end;
    public final int patternIndex;

    public Occurrence(int begin, int end, int patternIndex) {
        this.begin = begin;
        this.end = end;
        this.patternIndex = patternIndex;
    }

    @Override
    public int compareTo(Occurrence o) {
        int c = Ints.compare(begin, o.begin);
        if (c != 0) {
            return c;
        }
        c = Ints.compare(end, o.end);
        if (c != 0) {
            return c;
        }
        return Ints.compare(patternIndex, o.patternIndex);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.begin;
        hash = 97 * hash + this.end;
        hash = 97 * hash + this.patternIndex;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Occurrence other = (Occurrence) obj;
        if (this.begin != other.begin) {
            return false;
        }
        if (this.end != other.end) {
            return false;
        }
        if (this.patternIndex != other.patternIndex) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "(" + begin + ", " + end + ", " + patternIndex + ")";
    }
}
