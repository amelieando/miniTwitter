/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.minitwitter;

/**
 *
 * @author amelieando
 */
import java.util.HashSet;
import java.util.Set;

public class ValidationVisitor implements Visitor {
    private Set<String> ids = new HashSet<>();
    private boolean isValid = true;

    public boolean isValid() {
        return isValid;
    }

    @Override
    public void visit(User user) {
        validateID(user.getUserId());
    }

    @Override
    public void visit(UserGroup group) {
        validateID(group.getGroupId());
    }

    private void validateID(String id) {
        if (ids.contains(id) || id.contains(" ")) {
            isValid = false;
        }
        ids.add(id);
    }
}
