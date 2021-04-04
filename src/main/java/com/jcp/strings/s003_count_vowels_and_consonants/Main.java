package com.jcp.strings.s003_count_vowels_and_consonants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

    private static final String INPUT = "abcdefg1]$";
    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');

    enum CharType {
        VOWEL, CONSONANT, UNDEFINED
    }

    public static void main(String[] args) {
        System.out.println(count(INPUT));
        System.out.println(countFunctional(INPUT));
    }

    private static VowelConsonantCount count(String input) {
        var counter = new VowelConsonantCount();
        for (var c : input.toLowerCase().toCharArray()) {
            if (VOWELS.contains(c)) {
                counter.incVowelCount();
            } else if ('a' <= c && c <= 'z') {
                counter.incConsonantCount();
            } else {
                counter.incUndefinedCount();
            }
        }
        return counter;
    }

    private static VowelConsonantCount countFunctional(String input) {
        Map<CharType, List<Character>> collect = input.toLowerCase()
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Main::getCharType));
        return VowelConsonantCount.builder()
                .vowelCount(collect.get(CharType.VOWEL).size())
                .consonantCount(collect.get(CharType.CONSONANT).size())
                .undefinedCount(collect.get(CharType.UNDEFINED).size())
                .build();
    }

    private static CharType getCharType(Character c) {
        if (VOWELS.contains(c)) {
            return CharType.VOWEL;
        } else if ('a' <= c && c <= 'z') {
            return CharType.CONSONANT;
        } else {
            return CharType.UNDEFINED;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class VowelConsonantCount {
        private int vowelCount;
        private int consonantCount;
        private int undefinedCount;

        public void incVowelCount() {
            vowelCount++;
        }

        public void incConsonantCount() {
            consonantCount++;
        }

        public void incUndefinedCount() {
            undefinedCount++;
        }

    }

}
