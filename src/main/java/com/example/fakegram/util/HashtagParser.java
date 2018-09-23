package com.example.fakegram.util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HashtagParser {

    private String text;

    public HashtagParser(String text) {
        this.text = text;
    }

    public Set<String> getHashtags() {
        Pattern pattern = Pattern.compile("(\\s|\\A)#(\\w+)");
        Matcher matcher = pattern.matcher(text);
        Set<String> hasthags = new HashSet<>();
        while (matcher.find()) hasthags.add(matcher.group().trim().replace("#",""));
        return hasthags;
    }

}
