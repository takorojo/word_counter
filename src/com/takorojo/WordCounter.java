package com.takorojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class WordCounter {
    private String _file_path;
    private String _file_contents;

    /**
     * A word counter for text files.
     *
     * @param input_file file to count words in
     */
    public WordCounter(String input_file) {
        this._file_path = input_file;
        readFile(this._file_path);
        normalize();

        System.out.println(this._file_contents);
    }

    /**
     * Normalize the file contents.
     *
     * This is achieved by lowercasing all contents and then stripping out
     * extraneous spaces and punctuation.
     */
    private void normalize() {
        // NOTE: -- and - are separate entities that require separate handling.
        String[] punctuation = {"   ", ",", "\\.", "--", "-", "\"", "\n"};

        this._file_contents = this._file_contents.toLowerCase(Locale.ROOT);

        for (String s : punctuation) {
            this._file_contents = this.stripChars(this._file_contents, s);
        }
    }

    /**
     * Strip all instances of the given character from the given string.
     *
     * @param string string to strip characters from
     * @param char_to_strip character to strip from the given string
     * @return The given string with all instances the given character stripped out.
     */
    private String stripChars(String string, String char_to_strip) {
        String replacement_character = "";

        if (Objects.equals(char_to_strip, "--")) {
            replacement_character = " ";
        }

        return string.replaceAll(char_to_strip, replacement_character);
    }

    /**
     * Read the given text file and load its contents into self._file_contents.
     *
     * @param file_path file to read contents of
     */
    private void readFile(String file_path) {
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(this._file_path))) {
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                sb.append(line + " ");
                line = reader.readLine();
            }

            this._file_contents = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Count all occurrences of each word in the given text file.
     *
     * @return Hash of words and the number of times they appear in the file
     */
    public Map<String, Integer> count() {
        String[] words = this._file_contents.split(" ");
        Set<String> word_set = Arrays.stream(words).collect(Collectors.toSet());
        Iterator<String> wordIterator = word_set.iterator();
        Map<String, Integer> values = new HashMap<String, Integer>();

        while (wordIterator.hasNext()) {
            String next_word = wordIterator.next();

            if (!Objects.equals(next_word, "")) {
                values.put(next_word, wordCount(words, next_word));
            }
        }

        return values;
    }

    private Integer wordCount(String[] word_list, String word) {
        int count = 0;

        for (int i = 0; i < word_list.length; i++) {
            if (Objects.equals(word_list[i], word)) {
                count++;
            }
        }

        return count;
    }
}
