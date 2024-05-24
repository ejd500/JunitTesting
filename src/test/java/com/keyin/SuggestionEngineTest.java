package com.keyin;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Paths;

public class SuggestionEngineTest {

    private SuggestionEngine suggestionEngine = new SuggestionEngine();

    /**
     * When you put in a word that's not an exact match it should return suggestions.
     * */
    @Test
    public void testReturnsSuggestionsWhenNotExactMatch() throws IOException {
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("hellq").contains("hello"));
    }


    /**
     * When you put in a word that's an exact match it should return an empty string.
     * */
    @Test
    public void testReturnsEmptyStringWithExactMatch() throws IOException {
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));
        String result = suggestionEngine.generateSuggestions("word");
        Assertions.assertEquals("", result);
    }

    /**
     * Test suggestion limit of ten
     * */
    @Test
    public void testReturnsListOfTenWhenNotAnExactMatch() throws IOException {
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));
        String result = suggestionEngine.generateSuggestions("worq");
        int numberOfSuggestions = result.split("\n").length;
        Assertions.assertEquals(10, numberOfSuggestions);
    }



}
