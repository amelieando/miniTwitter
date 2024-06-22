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
import java.util.List;

public class UserGroup implements Element {
    private String groupId;
    private List<Element> members;

    public UserGroup(String groupId) {
        this.groupId = groupId;
        this.members = new ArrayList<>();
    }

    public String getGroupId() {
        return groupId;
    }

    public List<Element> getMembers() {
        return members;
    }

    public void addMember(Element member) {
        members.add(member);
    }

    @Override
    public String toString() {
        return groupId;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Element member : members) {
            member.accept(visitor);
        }
    }
}
