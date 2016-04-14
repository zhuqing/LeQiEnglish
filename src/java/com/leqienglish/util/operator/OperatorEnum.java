/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqienglish.util.operator;

/**
 *
 * @author guona
 */
public enum OperatorEnum {

    GT(">"),
    LT("<"),
    GE(">="),
    LE("<="),
    EQ("=");

    private String operator;

    private OperatorEnum(String operator) {
        this.operator = operator;
    }

    public String toString() {
        return this.operator;
    }
}
