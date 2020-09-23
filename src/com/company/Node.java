package com.company;

public class Node {
    protected int value;
    protected int height;
    protected Node leftSon;
    protected Node rightSon;

    Node(int value, int height){
        this.value=value;
        this.height=height;
        leftSon=null;
        rightSon=null;
    }

    public int getHeight() {
        return height;
    }


}
