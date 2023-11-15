package src;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class BST implements Comparator<Software> {
    private Node root;
    
    public BST(){
        root = null;
    }
    public void visit(Node p) {
        System.out.println(p.key + " ");
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
    public int compare(Software a, Software b){
        return a.name.compareTo(b.name);
    }
}
