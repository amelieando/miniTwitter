/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.minitwitter;

/**
 *
 * @author amelieando
 */
public interface Visitor {
    void visit(User user);
    void visit(UserGroup group);
}
