/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private ArrayList<String> wordList = new ArrayList<>();
    private HashSet<String> wordSet = new HashSet<>();
    private HashMap<String, ArrayList<String>> lettersToWords = new HashMap<>();


    public AnagramDictionary(Reader reader) throws IOException {
        BufferedReader in = new BufferedReader(reader);
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            //wordList.add(word);
            wordSet.add(word);

            String sortedWord = sortLetters(word);
            if(lettersToWords.containsKey(sortedWord)){
                lettersToWords.get(sortedWord).add(word);
            }else{
                ArrayList<String> words = new ArrayList<>();
                words.add(word);
                lettersToWords.put(sortedWord, words);
            }
        }
    }

    public boolean isGoodWord(String word, String base) {
        if(wordSet.contains(word) && !(word.contains(base))) {
            return true;
        }else{
            return false;
        }
    }

    public String sortLetters(String word){
        String[] ary = word.split("");
        Arrays.sort(ary);
        String str = Arrays.toString(ary);
        return str;
    }

    public List<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        String targetSorted = sortLetters(targetWord);
        //wordList
        //use an enhanced for loop that will check if word in the word list is an anagram of the targetword
            //then add it to the results arrayList
//        for(String word : wordSet)
//            if(word.length() == targetWord.length())
//                if(sortLetters(word).equals(sortLetters(targetWord)))
//                    result.add(word);
        if(lettersToWords.containsKey(targetSorted)){
            result.addAll(lettersToWords.get(targetSorted));
        }
            return result;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        int char_a = 97;
        int char_z = 122;
        for(int i=char_a; i<=char_z; i++) {
            char c = (char) i;
            String wordPlusOne = word.concat(String.valueOf(c));
            ArrayList<String> anagrams = (ArrayList<String>) getAnagrams(wordPlusOne);
            for(String w : anagrams)
                if(isGoodWord(w, word))
                    result.add(w);


        }

        return result;
    }

    //Homework
    public String pickGoodStarterWord() {
    //Use wordSet
        int size = wordSet.size();
        int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
        int i = 0;
        for(String w : wordSet) {
            if (i == item)
                return w;
        }
    }
}
