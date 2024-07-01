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
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AdminControlPanel extends JFrame {
    private static AdminControlPanel instance;
    private UserTreeModel userTreeModel;
    private JTree userTree;
    private JTextField userIdText;
    private JTextField groupIdText;

    private AdminControlPanel() {
        userTreeModel = new UserTreeModel();
        initializeUI();
    }

    public static AdminControlPanel getInstance() {
        if (instance == null) {
            instance = new AdminControlPanel();
        }
        return instance;
    }

    private void initializeUI() {
        setTitle("Admin Control Panel");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        userTree = new JTree(userTreeModel);
        userTree.setCellRenderer(new UserTreeRenderer());
        JScrollPane treeView = new JScrollPane(userTree);

        userIdText = new JTextField();
        groupIdText = new JTextField();

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(this::addUser);
        JButton addGroupButton = new JButton("Add Group");
        addGroupButton.addActionListener(this::addGroup);
        JButton showUserViewButton = new JButton("Open User View");
        showUserViewButton.addActionListener(this::openUserView);
        JButton showTotalUsersButton = new JButton("Show Total Users");
        showTotalUsersButton.addActionListener(this::showTotalUsers);
        JButton showTotalGroupsButton = new JButton("Show Total Groups");
        showTotalGroupsButton.addActionListener(this::showTotalGroups);
        JButton showTotalTweetsButton = new JButton("Show Total Tweets");
        showTotalTweetsButton.addActionListener(this::showTotalTweets);
        JButton showPositiveTweetsButton = new JButton("Show Positive Tweets Percentage");
        showPositiveTweetsButton.addActionListener(this::showPositiveTweetsPercentage);
        JButton validateIDsButton = new JButton("Validate IDs");
        validateIDsButton.addActionListener(this::validateIDs);
        JButton showMostRecentUpdateUserButton = new JButton("Show Most Recent Update User");
        showMostRecentUpdateUserButton.addActionListener(this::showMostRecentUpdateUser);

        JPanel controlPanel = new JPanel(new GridLayout(0, 2));
        controlPanel.add(new JLabel("User ID:"));
        controlPanel.add(userIdText);
        controlPanel.add(new JLabel("Group ID:"));
        controlPanel.add(groupIdText);
        controlPanel.add(addUserButton);
        controlPanel.add(addGroupButton);
        controlPanel.add(showUserViewButton);
        controlPanel.add(showTotalUsersButton);
        controlPanel.add(showTotalGroupsButton);
        controlPanel.add(showTotalTweetsButton);
        controlPanel.add(showPositiveTweetsButton);
        controlPanel.add(validateIDsButton);
        controlPanel.add(showMostRecentUpdateUserButton);

        add(treeView, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    private void addUser(ActionEvent e) {
        String userId = userIdText.getText().trim();
        if (!userId.isEmpty()) {
            if (UserRepository.getInstance().getUser(userId) == null) {
                User user = new User(userId);
                UserRepository.getInstance().addUser(user);
                userTreeModel.addUser(user);
                userIdText.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "User ID already exists.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "User ID cannot be empty.");
        }
    }

    private void addGroup(ActionEvent e) {
        String groupId = groupIdText.getText().trim();
        if (!groupId.isEmpty()) {
            if (userTreeModel.getGroup(groupId) == null) {
                UserGroup group = new UserGroup(groupId);
                userTreeModel.addGroup(group);
                groupIdText.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Group ID already exists.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Group ID cannot be empty.");
        }
    }

    private void openUserView(ActionEvent e) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) userTree.getLastSelectedPathComponent();
        if (selectedNode != null && selectedNode.getUserObject() instanceof User) {
            User user = (User) selectedNode.getUserObject();
            new UserView(user).setVisible(true);
        }
    }

    private void showTotalUsers(ActionEvent e) {
        UserCountVisitor visitor = new UserCountVisitor();
        userTreeModel.accept(visitor);
        JOptionPane.showMessageDialog(this, "Total Users: " + visitor.getUserCount());
    }

    private void showTotalGroups(ActionEvent e) {
        GroupCountVisitor visitor = new GroupCountVisitor();
        userTreeModel.accept(visitor);
        JOptionPane.showMessageDialog(this, "Total Groups: " + visitor.getGroupCount());
    }

    private void showTotalTweets(ActionEvent e) {
        TweetCountVisitor visitor = new TweetCountVisitor();
        userTreeModel.accept(visitor);
        JOptionPane.showMessageDialog(this, "Total Tweets: " + visitor.getTweetCount());
    }

    private void showPositiveTweetsPercentage(ActionEvent e) {
        PositiveTweetVisitor visitor = new PositiveTweetVisitor();
        userTreeModel.accept(visitor);
        JOptionPane.showMessageDialog(this, "Positive Tweets Percentage: " + visitor.getPositivePercentage() + "%");
    }

    private void validateIDs(ActionEvent e) {
        ValidationVisitor visitor = new ValidationVisitor();
        userTreeModel.accept(visitor);
        boolean isValid = visitor.isValid();
        String message = isValid ? "All IDs are valid." : "There are invalid IDs.";
        JOptionPane.showMessageDialog(this, message);
    }

    private void showMostRecentUpdateUser(ActionEvent e) {
        UpdateVisitor visitor = new UpdateVisitor();
        userTreeModel.accept(visitor);
        User mostRecentUser = visitor.getMostRecentlyUpdatedUser();
        if (mostRecentUser != null) {
            JOptionPane.showMessageDialog(this, "User with most recent update: " + mostRecentUser.getUserId());
        } else {
            JOptionPane.showMessageDialog(this, "No users have updated yet.");
        }
    }
}
