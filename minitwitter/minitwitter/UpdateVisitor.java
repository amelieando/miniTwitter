/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minitwitter;

/**
 *
 * @author amelieando
 */
public class UpdateVisitor implements Visitor {
    private User mostRecentlyUpdatedUser = null;

    public User getMostRecentlyUpdatedUser() {
        return mostRecentlyUpdatedUser;
    }

    @Override
    public void visit(User user) {
        if (mostRecentlyUpdatedUser == null || user.getLastUpdateTime() > mostRecentlyUpdatedUser.getLastUpdateTime()) {
            mostRecentlyUpdatedUser = user;
        }
    }

    @Override
    public void visit(UserGroup group) {
        // Do nothing for groups
    }
}
