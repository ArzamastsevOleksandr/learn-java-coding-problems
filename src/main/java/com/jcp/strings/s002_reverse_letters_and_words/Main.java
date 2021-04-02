package com.jcp.strings.s002_reverse_letters_and_words;

import lombok.Builder;
import lombok.Value;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class Main {

    private static final String SPACE = " ";
    private static final Predicate<String> STRING_NOT_BLANK = s -> !s.isBlank();
    private static final Pattern PATTERN = Pattern.compile(" +");

    public static void main(String[] args) {
        var reverseCondition = ReverseCondition.builder()
                .reverseLetters(false)
                .reverseWords(false)
                .build();
        System.out.println(performReverse("I am reversing words", reverseCondition));

        reverseCondition = reverseCondition.toBuilder()
                .reverseLetters(true)
                .build();
        System.out.println(performReverse("I am reversing words", reverseCondition));

        reverseCondition = reverseCondition.toBuilder()
                .reverseWords(true)
                .build();
        System.out.println(performReverse("I am reversing words", reverseCondition));
    }

    private static String performReverse(String input, ReverseCondition reverseCondition) {
        if (reverseCondition.isFalse()) {
            return input;
        }
        if (reverseCondition.isReverseLetters() && !reverseCondition.isReverseWords()) {
            return reverseLetters1(input);
        }
        return reverseLettersAndWords2(input);
    }

    private static String reverseLetters1(String input) {
        return Arrays.stream(input.split(SPACE))
                .filter(STRING_NOT_BLANK)
                .map(Main::reverseString)
                .collect(joining(SPACE));
    }

    private static String reverseLetters2(String input) {
        return PATTERN.splitAsStream(input)
                .map(Main::reverseString)
                .collect(joining(SPACE));

    }

    private static StringBuilder reverseString(String s) {
        return new StringBuilder(s).reverse();
    }

    private static String reverseLettersAndWords1(String input) {
        String[] reversedWords = reverseLetters1(input).split(SPACE);
        return IntStream.range(0, reversedWords.length)
                .mapToObj(i -> reversedWords[reversedWords.length - i - 1])
                .collect(joining(SPACE));
    }

    private static String reverseLettersAndWords2(String input) {
        return reverseString(input).toString();
    }

    @Value
    @Builder(toBuilder = true)
    private static class ReverseCondition {
        boolean reverseLetters;
        boolean reverseWords;

        public boolean isFalse() {
            return !(reverseLetters || reverseWords);
        }
    }

}
