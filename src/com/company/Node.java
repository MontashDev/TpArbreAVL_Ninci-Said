package com.company;

public class Node extends AVLTree {
    int value, height;
    Node leftSon, rightSon;

    public Node(int value) {
        leftSon = null;
        rightSon = null;
        this.value = value;
        this.height = 1;
    }
}