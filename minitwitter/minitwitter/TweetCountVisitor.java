/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minitwitter;

/**
 *
 * @author amelieando
 */
public class TweetCountVisitor implements Visitor {
    private int tweetCount;

    public int getTweetCount() {
        return tweetCount;
    }

    @Override
    public void visit(User user) {
        tweetCount += user.getNewsFeed().size();
    }

    @Override
    public void visit(UserGroup group) {
        // Do nothing for groups
    }
}
