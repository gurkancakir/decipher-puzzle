package com.luxoft.decipherpuzzle.core.entity;

public enum OperationType {

    ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/");

    private String type;

    OperationType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
