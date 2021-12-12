package classes;

import java.util.Comparator;

public class Node_comp implements Comparator<Node>
{
    @Override
    public int compare(Node n1, Node n2) {
        if (n1.getWeight() < n2.getWeight()) {
            return -1;
        }
        if (n1.getWeight() > n2.getWeight()) {
            return 1;
        }
        return 0;


    }

}
