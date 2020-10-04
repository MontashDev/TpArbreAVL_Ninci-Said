package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        makeMenu();
    }

    //Function to add a value
    static public void insertValue(int value, AVLTree tree){
        tree.root = tree.insert(tree.root, value );
    }

    //Function to remove a value
    static public void deleteValue(int value, AVLTree tree){
        tree.root = tree.deleteNode(tree.root, value);
    }

    //Aesthetic function for the console display
    static public void lineBreak(){
        System.out.println("\n");
    }

    static public void makeMenu(){
        System.out.println("Hello and welcome in this little program that materializes the design of an AVL tree !");
        Scanner ask = new Scanner(System.in);
        int choice, numberInsert;

        System.out.println("To start enter an integer that you want to add to the tree :  ");
        numberInsert = ask.nextInt();

        AVLTree tree = new AVLTree();
        insertValue(numberInsert, tree);
        System.out.println("\nYour tree is no longer empty now this is what it looks like :  ");
        AVLTree.printTree(tree.root);
        lineBreak();
        do {
            System.out.println("1- Add a value \n2- Delete a value \n3- Find a value \n4- Stop the program \nWhat do you want to do ? :  ");
            choice = ask.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("What value do you want to add ? :  ");
                    numberInsert = ask.nextInt();
                    insertValue(numberInsert, tree);
                    AVLTree.printTree(tree.root);
                    lineBreak();
                }
                case 2 -> {
                    System.out.println("What value do you want to remove ? :  ");
                    numberInsert = ask.nextInt();
                    System.out.println("The tree before the value " + numberInsert + " is removed : \n");
                    AVLTree.printTree(tree.root);
                    deleteValue(numberInsert, tree);
                    System.out.println("\nAnd with the value removed : \n");
                    AVLTree.printTree(tree.root);
                    lineBreak();
                }
                case 3 -> {
                    System.out.println("What value do you want to search in th tree ? :  ");
                    numberInsert = ask.nextInt();
                    if (tree.findAnElement(numberInsert))
                        System.out.println("This value is in the tree\n");
                    else
                        System.out.println("This value isn't in the tree\n");
                }
                case 4 -> System.out.println("The program is over");
                default -> throw new IllegalArgumentException("Wrong value entered");
            }
        }while(choice != 4);
    }
}