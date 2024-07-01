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
    private long creationTime;

    public UserGroup(String groupId) {
        this.groupId = groupId;
        this.members = new ArrayList<>();
        this.creationTime = System.currentTimeMillis();
    }

    public String getGroupId() {
        return groupId;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void addMember(Element element) {
        members.add(element);
    }

    public List<Element> getMembers() {
        return members;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Element member : members) {
            member.accept(visitor);
        }
    }
}
