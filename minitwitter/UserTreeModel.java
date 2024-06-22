/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minitwitter;
/**
 *
 * @author amelieando
 */
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class UserTreeModel extends DefaultTreeModel {
    private DefaultMutableTreeNode root;

    public UserTreeModel() {
        super(new DefaultMutableTreeNode("Root"));
        root = (DefaultMutableTreeNode) getRoot();
    }

    public void addUser(User user) {
        DefaultMutableTreeNode userNode = new DefaultMutableTreeNode(user);
        root.add(userNode);
        reload();
    }

    public void addGroup(UserGroup group) {
        DefaultMutableTreeNode groupNode = new DefaultMutableTreeNode(group);
        root.add(groupNode);
        reload();
    }

    public void accept(Visitor visitor) {
        for (int i = 0; i < root.getChildCount(); i++) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) root.getChildAt(i);
            Element element = (Element) node.getUserObject();
            element.accept(visitor);
        }
    }

    public UserGroup getGroup(String groupId) {
        for (int i = 0; i < root.getChildCount(); i++) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) root.getChildAt(i);
            if (node.getUserObject() instanceof UserGroup) {
                UserGroup group = (UserGroup) node.getUserObject();
                if (group.getGroupId().equals(groupId)) {
                    return group;
                }
            }
        }
        return null;
    }
}
