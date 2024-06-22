/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minitwitter;
/**
 *
 * @author amelieando
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class UserView extends JFrame {
    private User user;
    private DefaultListModel<String> newsFeedModel;
    private DefaultListModel<String> followingModel;
    private JTextArea tweetText;
    private JTextField followUserIdText;

    public UserView(User user) {
        this.user = user;
        initializeUI();
    }

    private void initializeUI() {
        setTitle(user.getUserId() + " - User View");
        setSize(400, 300);
        setLayout(new BorderLayout());

        newsFeedModel = new DefaultListModel<>();
        JList<String> newsFeedList = new JList<>(newsFeedModel);
        JScrollPane newsFeedScroll = new JScrollPane(newsFeedList);

        followingModel = new DefaultListModel<>();
        JList<String> followingList = new JList<>(followingModel);
        JScrollPane followingScroll = new JScrollPane(followingList);

        tweetText = new JTextArea("Write your tweet here...");
        JButton postButton = new JButton("Post Tweet");
        postButton.addActionListener(this::postTweet);

        followUserIdText = new JTextField("User ID to follow");
        JButton followButton = new JButton("Follow User");
        followButton.addActionListener(this::followUser);

        JPanel controlPanel = new JPanel(new GridLayout(0, 1));
        controlPanel.add(tweetText);
        controlPanel.add(postButton);
        controlPanel.add(followUserIdText);
        controlPanel.add(followButton);

        add(newsFeedScroll, BorderLayout.CENTER);
        add(followingScroll, BorderLayout.WEST);
        add(controlPanel, BorderLayout.SOUTH);

        refreshNewsFeed();
        refreshFollowingList();
    }

    private void postTweet(ActionEvent e) {
        String tweet = tweetText.getText().trim();
        if (!tweet.isEmpty()) {
            user.postTweet(tweet);
            refreshNewsFeed();
        }
    }

    private void followUser(ActionEvent e) {
        String followUserId = followUserIdText.getText().trim();
        User followUser = UserRepository.getInstance().getUser(followUserId);
        if (followUser != null) {
            user.followUser(followUser);
            refreshFollowingList();
            // Immediately show the followed user's previous tweets in the news feed
            for (String tweet : followUser.getNewsFeed()) {
                if (!newsFeedModel.contains(tweet)) {
                    newsFeedModel.addElement(tweet);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "User ID not found.");
        }
    }

    private void refreshNewsFeed() {
        newsFeedModel.clear();
        List<String> newsFeed = user.getNewsFeed();
        for (String tweet : newsFeed) {
            newsFeedModel.addElement(tweet);
        }
    }

    private void refreshFollowingList() {
        followingModel.clear();
        List<User> followings = user.getFollowings();
        for (User following : followings) {
            followingModel.addElement(following.getUserId());
        }
    }
}

