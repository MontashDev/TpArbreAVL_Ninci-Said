package com.company;

public class BinaryTree {
    Node root;

    public BinaryTree(Node root){
        this.root=root;
    }

    public Node insertValue(Node current, int value){
        if(current == null){
            return new Node(value, current.height+1);
        }

        if (value< current.value) {
            current.leftSon = insertValue(current.leftSon, value);
        } else if (value> current.value){
            current.rightSon = insertValue(current.rightSon, value);
        } else {
            System.out.println("La valeur existe déjà");
            return current;
        }
        return current;
    }

    public void add(int value){
        root= insertValue(root, value);
    }

    public boolean containsValue(Node current, int value){
        if (current == null){
            return false;
        }
        if (value == current.value){
            return true;
        }
        return value < current.value ? containsValue(current.leftSon, value) : containsValue(current.rightSon, value);
    }

    public boolean containsNode(int value){
        return containsValue(root,value);
    }

    public Node deleteElement(Node current, int value){
        if (current == null){
            return null;
        }

        if (value== current.value){
            if (current.leftSon == null && current.rightSon == null) {
                return null;
            }

            if (current.rightSon == null){
                return current.leftSon;
            }

            if (current.leftSon == null){
                return current.rightSon;
            }

        }

        if (value < current.value){
            current.leftSon = deleteElement(current.leftSon, value);
            return current;
        }
        current.rightSon= deleteElement(current.rightSon,value);
        return current;
    }


}
