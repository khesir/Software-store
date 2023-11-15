package src;

public class Node {
    protected Software key;
    protected Node left, right;
    public Node(){
        left=right=null;
    }
    public Node(Software el){
        this(el, null,null);
    }
    public Node(Software el, Node left, Node right){
        key = el;
        this.left = left;
        this.right = right;
    }
}
