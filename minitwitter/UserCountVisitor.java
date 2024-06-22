/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minitwitter;

/**
 *
 * @author amelieando
 */
public class UserCountVisitor implements Visitor {
    private int userCount;

    public int getUserCount() {
        return userCount;
    }

    @Override
    public void visit(User user) {
        userCount++;
    }

    @Override
    public void visit(UserGroup group) {
        // Do nothing for groups
    }
}
