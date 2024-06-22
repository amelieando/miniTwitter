/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minitwitter;

/**
 *
 * @author amelieando
 */
public class GroupCountVisitor implements Visitor {
    private int groupCount;

    public int getGroupCount() {
        return groupCount;
    }

    @Override
    public void visit(User user) {
        // Do nothing for users
    }

    @Override
    public void visit(UserGroup group) {
        groupCount++;
    }
}
