package com.example.elena.binarysearchtree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elena on 9/9/2017.
 */

class BinarySearchTree {
    private Node mRoot;
    private List<Double> mListedTree = new ArrayList<>();

    boolean search(double value){
        Node node = mRoot;
        while(node!=null){
            if(node.value==value){
                return true;
            }else if(node.value< value){
                node = node.right;
            }else{
                node = node.left;
            }
        }
        return false;
    }
    void remove(double value){
        Node parent = mRoot, node=mRoot;
        boolean isLeft = false;
        while(node.value!= value){
            parent = node;
            if(node.value > value){
                isLeft = true;
                node = node.left;
            }else{
                isLeft = false;
                node = node.right;
            }
            if(node ==null){
                return;
            }
        }

        if(node.left==null && node.right==null){
            if(node==mRoot)
                mRoot = null;
            if(isLeft)
                parent.left = null;
            else
                parent.right = null;
        }else if(node.right==null){
            if(node == mRoot)
                mRoot = node.left;
            else if(isLeft)
                parent.left = node.left;
            else
                parent.right = node.left;
        }else if(node.left==null){
            if(node==mRoot)
                mRoot = node.right;
            else if(isLeft)
                parent.left = node.right;
            else
                parent.right = node.right;
        }else{
            Node successor	 = getSuccessor(node);
            if(node == mRoot)
                mRoot = successor;
            else if(isLeft)
                parent.left = successor;
            else
                parent.right = successor;
            successor.left = node.left;
        }
    }

    private Node getSuccessor(Node node){
        Node successsor = null;
        Node successsorParent = null;
        Node current = node.right;
        while(current!=null){
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }
        if(successsor != node.right){
            successsorParent.left = successsor.right;
            successsor.right = node.right;
        }
        return successsor;
    }

    void insert(double value){
        Node newNode = new Node(value);
        if(mRoot==null){
            mRoot = newNode;
            return;
        }
        Node node = mRoot;
        Node parent;
        while(true){
            parent = node;
            if( value < node.value){
                node = node.left;
                if(node==null){
                    parent.left = newNode;
                    return;
                }
            }else{
                node = node.right;
                if(node==null){
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    private int count = 0;
    void setList(Node mRoot){
        if(count == 0){
            mListedTree.clear();
        }
        count++;
        if(mRoot != null){
            setList(mRoot.left);
            mListedTree.add(mRoot.value);
            setList(mRoot.right);
        }
    }

    List<Double> getList(){
        count = 0;
        return mListedTree;
    }

    Node getRoot(){
        return mRoot;
    }

    double getMin() {
        Node current = mRoot;
        if (current == null){
            return 0;
        }
        while(current.left != null){
            current = current.left;
        }
        return current.value;
    }

    double getMax(){
        Node current = mRoot;
        if (current == null){
            return 0;
        }
        while(current.right != null){
            current = current.right;
        }
        return current.value;
    }


    class Node {
        double value;
        Node left, right;

        Node(double value) {
            this.value = value;
        }
    }
}
