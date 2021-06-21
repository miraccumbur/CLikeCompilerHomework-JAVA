import java.util.ArrayList;
import java.util.List;

public class Node {
    public String data; //data for storage
    public List<Node> children;//array will keep children
    public Node parent;//parent to start the tree

    public Node(String data) {
        children = new ArrayList<>();
        this.data = data;
    }

    public Node addChild(Node node) {
        children.add(node);
        node.parent = this;
        return this;
    }
}
