/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minitwitter;
/**
 *
 * @author amelieando
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements Element {
    private String userId;
    private List<User> followers;
    private List<User> followings;
    private List<String> newsFeed;
    private Set<String> uniqueTweets;

    public User(String userId) {
        this.userId = userId;
        this.followers = new ArrayList<>();
        this.followings = new ArrayList<>();
        this.newsFeed = new ArrayList<>();
        this.uniqueTweets = new HashSet<>();
    }

    public String getUserId() {
        return userId;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public List<User> getFollowings() {
        return followings;
    }

    public List<String> getNewsFeed() {
        return newsFeed;
    }

    public void followUser(User user) {
        if (!followings.contains(user)) {
            followings.add(user);
            user.addFollower(this);
            // Add the followed user's previous tweets to the current user's news feed without reformatting them
            for (String tweet : user.getNewsFeed()) {
                if (uniqueTweets.add(tweet)) {
                    newsFeed.add(tweet);
                }
            }
        }
    }

    public void postTweet(String tweet) {
        String formattedTweet = userId + ": " + tweet;
        if (uniqueTweets.add(formattedTweet)) {
            newsFeed.add(formattedTweet);
            notifyFollowers(formattedTweet);
        }
    }

    public void addFollower(User user) {
        if (!followers.contains(user)) {
            followers.add(user);
        }
    }

    private void notifyFollowers(String tweet) {
        for (User follower : followers) {
            follower.update(tweet);
        }
    }

    public void update(String tweet) {
        if (uniqueTweets.add(tweet)) {
            newsFeed.add(tweet);
        }
    }

    @Override
    public String toString() {
        return userId;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
