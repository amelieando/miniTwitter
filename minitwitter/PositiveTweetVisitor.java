/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minitwitter;
/**
 *
 * @author amelieando
 */
import java.util.List;

public class PositiveTweetVisitor implements Visitor {
    private int tweetCount;
    private int positiveTweetCount;
    private static final List<String> positiveWords = List.of("good", "great", "excellent", "awesome", "fantastic");

    public double getPositivePercentage() {
        return tweetCount == 0 ? 0 : (double) positiveTweetCount / tweetCount * 100;
    }

    @Override
    public void visit(User user) {
        for (String tweet : user.getNewsFeed()) {
            tweetCount++;
            for (String word : positiveWords) {
                if (tweet.contains(word)) {
                    positiveTweetCount++;
                    break;
                }
            }
        }
    }

    @Override
    public void visit(UserGroup group) {
        // Do nothing for groups
    }
}
