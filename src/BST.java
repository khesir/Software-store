package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BST implements Comparator<Software> {
    public Node root;
    
    public BST(){
        root = null;
    }
    public void visit(Node p) {
        System.out.print(p.key);
    }
    public Software search(String el, String el2){
        Node p = root;
        while (p !=null) {
            if(compare(p.key, el) == 0 && comparebyVersion(p.key, el2) == 0)
                return p.key;
            else if(compare(p.key,el) < 0 && comparebyVersion(p.key, el2) < 0)
                p = p.left;
            else p = p.right;
        }
        return null;
    }

    public void breadthFirst(){
        Node p = root;
        Queue<Node> queue = new LinkedList<>();
        if(p != null) {
            queue.add(p);
            while (!queue.isEmpty()) {
                p = (Node) queue.remove();
                visit(p);
                if(p.left != null) queue.add(p.left);
                if(p.right != null) queue.add(p.right);
            }
        }
    }
    public void preorder(Node p){
        if(p != null){
            visit(p);
            preorder(p.left);
            preorder(p.right);
        }
    }
    public void postorder(Node p){
        if(p != null){
            postorder(p.left);
            postorder(p.right);
            visit(p);
        }
    }
   
    public void insert(Software el){
        Node p = root, prev = null;

        while(p != null){
            prev = p;
            if(compare(p.key, el) < 0)
                p = p.left;
            else p = p.right;
        }
        if( root == null)
            root = new Node(el);
        else if (compare(prev.key, el) < 0)
            prev.left = new Node(el);
        else prev.right = new Node(el);
    }

    public void deleteByCopying(Software el){
        Node node, p = root, prev = null;
        while(p != null && compare(p.key, el) != 0){
            prev = p;
            if(compare(p.key, el) < 0)
                p = p.left;
            else p = p.right;
        }
        node = p;
        if(p != null && compare(p.key, el) == 0){
            if(node.right == null)
                node = node.left;
            else if(node.left == null)
                node = node.right;
            else {
                Node tmp = node.left;
                Node previous = node;
                while(tmp.right != null){
                    previous = tmp;
                    tmp = tmp.right;
                }
                node.key = tmp.key;
                if(previous == node)
                    previous.left = tmp.left;
                else previous.right = tmp.left;
            }
            if(p == root)
                root = node;
            else if(prev.left == p)
                prev.left = node;
            else prev.right = node;
        }
        else if(root != null)
            System.out.println("key name:" + el.name +" Version:" +el.version + " is not in the tree");
        else System.out.println("the tree is empty");
    }
    public void inorder(Node p, ArrayList<Software> ls){
        if(p != null){
            inorder(p.left,ls);
            ls.add(p.key);
            inorder(p.right,ls);
        }
    }
    public void printData() throws Exception{
        BufferedWriter br = new BufferedWriter(new FileWriter("UpdatedData.txt"));
        StringBuilder sb = new StringBuilder();
        Node p = root;
        ArrayList<Software> list = new ArrayList<>();
        inorder(p,list);
        
        
        System.out.println("Quantity !=0");
        for(Software x: list){
            if(x.quantity != 0){
                System.out.println(x);
                sb.append(x.name+"\n");
                sb.append(x.version+"\n");
                sb.append(x.quantity+"\n");
                sb.append(x.price+"\n");
            }
        }
        System.out.println("\nQuantity = 0");
        for(Software x: list){
            if(x.quantity == 0){
                System.out.println(x);
                sb.append(x.name+"\n");
                sb.append(x.version+"\n");
                sb.append(x.quantity+"\n");
                sb.append(x.price+"\n");
            }
        }
        br.write(sb.toString());
        br.close();
        System.out.println("Data has been updated. see UpdatedData.txt");
    }

    // Comaparitor
    public int compare(Software a, Software b){
        return a.name.compareTo(b.name);
    }
    public int compare(Software a, String b){
        return a.name.compareTo(b);
    }
    public int comparebyVersion(Software a, Software b){
        return a.version.compareTo(b.version);
    }
    public int comparebyVersion(Software a, String b){
        return a.version.compareTo(b);
    }
}
