package com.company;

public class AVLTree {

    public Node root;

    private int GetHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    private int max(int a, int b) {
        return Math.max(a, b);
    }

    public boolean findAnElement(int value) {
        Node node = root;
        while (node != null && value != node.value) {
            if (value < node.value) {
                node = node.leftSon;
            } else {
                node = node.rightSon;
            }
        }
        return node != null;
    }

    private int getBalance(Node node) {
        if (node != null)
            return GetHeight(node.leftSon) - GetHeight(node.rightSon);
        else return 0;
    }
    private boolean isBalanced(Node node) {
        int balance  = getBalance(node);
        return balance <= 1 && balance >= -1;
    }

    private Node minValueInTheTree(Node node)
    {
        Node minNode = node;

        while (minNode.leftSon != null)
            minNode = minNode.leftSon;

        return minNode;
    }

    public Node insert(Node node, int value) {
        if (node == null)
            return new Node(value);

        if (value < node.value)
            node.leftSon = insert(node.leftSon, value);
        else if (value > node.value)
            node.rightSon = insert(node.rightSon, value);
        else
            return node;

        node.height = 1 + max(GetHeight(node.leftSon), GetHeight(node.rightSon));
        int balance = getBalance(node);
        if(!isBalanced(node)) {
            System.out.println("\nThe incorrect tree looks like : ");
            printTree(root);
            System.out.println("\n\nAnd the correct tree looks like : \n");
        }

        //Make the left left assignment
        if (balance > 1 && value < node.leftSon.value) {
            return rightAssignment(node);
        }
        //Make the right right assignment
        if (balance < -1 && value > node.rightSon.value) {
            return leftAssignment(node);
        }
        //Make the left right assignment
        if (balance > 1 && value > node.leftSon.value) {
            node.leftSon = leftAssignment(node.leftSon);
            return rightAssignment(node);
        }
        //Make the right left assignment
        if (balance < -1 && value < node.rightSon.value) {
            node.rightSon = rightAssignment(node.rightSon);
            return leftAssignment(node);
        }
        return node;
    }
    private Node rightAssignment(Node node) {
        Node swapNode = node.leftSon;
        Node tmpNode = swapNode.rightSon;
        swapNode.rightSon = node;
        node.leftSon = tmpNode;

        node.height = 1 + max(GetHeight(node.leftSon), GetHeight(node.rightSon));
        swapNode.height = 1 + max(GetHeight(swapNode.leftSon), GetHeight(swapNode.rightSon));

        return swapNode;
    }

    private Node leftAssignment(Node node) {
        Node swapNode = node.rightSon;
        Node tmpNode = swapNode.leftSon;
        swapNode.leftSon = node;
        node.rightSon = tmpNode;

        node.height = 1 + max(GetHeight(node.leftSon), GetHeight(node.rightSon)) ;
        swapNode.height = 1 + max(GetHeight(swapNode.leftSon), GetHeight(swapNode.rightSon)) ;

        return swapNode;
    }

    public Node deleteNode(Node node, int value)
    {
        if (node == null) return null;

        //Recursive way to select the left tree
        if (value < node.value) {
            node.leftSon = deleteNode(node.leftSon, value);
        }
        //Recursive way to select the right tree
        else if (value > node.value) {
            node.rightSon = deleteNode(node.rightSon, value);
        }
        //The node we want to delete
        else {
            //The case if the node got one or no child
            if ((node.leftSon == null) || (node.rightSon == null)) {
                Node tmpNode;
                if (node.leftSon == null){
                    tmpNode = node.rightSon;
                }
                else tmpNode = node.leftSon;

                node = tmpNode;
            }
            //The case if the node got 2 children
            else {
                Node tmpNode = minValueInTheTree(node.rightSon);
                node.value = tmpNode.value;
                node.rightSon = deleteNode(node.rightSon, tmpNode.value);
            }
        }
        if (node == null) return null;

        node.height =  1 + max(GetHeight(node.leftSon), GetHeight(node.rightSon));
        int balance = getBalance(node);

        //Make the left left assignment
        if (balance > 1 && getBalance(node.leftSon) >= 0) {
            return rightAssignment(node);
        }
        //Make the left right assignment
        if (balance > 1 && getBalance(node.leftSon) < 0) {
            node.leftSon = leftAssignment(node.leftSon);
            return rightAssignment(node);
        }
        //Make right right assignment
        if (balance < -1 && getBalance(node.rightSon) <= 0) {
            return leftAssignment(node);
        }
        //Make the right left assignment
        if (balance < -1 && getBalance(node.rightSon) > 0) {
            node.rightSon = rightAssignment(node.rightSon);
            return leftAssignment(node);
        }

        return node;

    }

    //The functions to print the display of the tree :
    public static int count = 10;

    static void printTreeUsage(Node node, int space) {
        if (node != null) {
            space += count;
            printTreeUsage(node.rightSon, space);
            System.out.print("\n");
            for (int i = count; i < space; i++) {
                System.out.print(" ");
            }
            System.out.print(node.value + "\n");
            printTreeUsage(node.leftSon, space);
        }
    }

    static void printTree(Node node) {
        printTreeUsage(node, 0);
    }


    //functions not used to produce the tree display (bugs)

    /*public int pass = 0 ;

    public void printTree(Node node) {
        if (node != null) {
            int spaces = GetHeight(node) * 6;
            int spacesBetween = 4;
            int dash = 1+ (GetHeight(node)/2);

            if(pass == 0) {
                makeSpaces(spaces);
                spaces -= 2;
                System.out.println(node.value);
                pass++;
            }

            for (int i = 0; i < dash ; i++) {
                makeSpaces(spaces);
                spaces -= 2;

                if(node.leftSon != null)
                    System.out.print("/");
                else makeSpaces(1);

                makeSpaces(spacesBetween);
                spacesBetween = spacesBetween + 4;

                if(node.rightSon != null)
                    System.out.print("\\");

                System.out.println();
            }
            makeSpaces(spaces);
            System.out.print(node.leftSon.value);
            printTree(node.leftSon);
            makeSpaces(spacesBetween - 2);
            System.out.print(node.rightSon.value);
            printTree(node.rightSon);
        }
    }
    public void makeSpaces(int spaces) {
        for (int i = 0; i < spaces; i++) {
            System.out.print(" ");
        }
    }*/
}