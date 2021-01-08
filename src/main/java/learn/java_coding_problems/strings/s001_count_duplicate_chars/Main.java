package learn.java_coding_problems.strings.s001_count_duplicate_chars;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Slf4j
public class Main {

    private static final String STRING = "abcdefghacea";

    public static void main(String[] args) {
        Map<Character, Integer> duplicateChar2Count = countDuplicateCharacters(STRING);
        log.error("str: {}, duplicateChar2Count: {}", STRING, duplicateChar2Count);
    }

    private static Map<Character, Integer> countDuplicateCharacters(String str) {
        Map<Character, Integer> char2Count = new HashMap<>();
        for (char c : str.toCharArray()) {
            char2Count.merge(c, 1, Integer::sum);
        }
        return char2Count.entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    // TODO stream-oneliner impl

    // TODO UNICODE support

}
