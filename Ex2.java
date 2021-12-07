import GUI.GraphFrame;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.*;

import java.io.IOException;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) throws IOException {

        DirectedWeightedGraph ans;
        AlgoGraphClass algoGraphClass = new AlgoGraphClass();
        algoGraphClass.load(json_file);
        System.out.println(algoGraphClass.save("C:\\Users\\avi44\\IdeaProjects\\Ex2_06_12\\src\\saved.json"));
        ans = algoGraphClass.getGraph();
        return ans;
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) throws IOException {
        DirectedWeightedGraphAlgorithms ans = new AlgoGraphClass();
        ans.init(getGrapg(json_file));

        System.out.println(ans.center());


        System.out.println(ans.isConnected());
        return ans;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) throws IOException {
//        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        new GraphFrame((GraphClass) getGrapg(json_file));

    }

    public static void main(String[] args) throws IOException {


        String filename = "C:\\Users\\avi44\\IdeaProjects\\Ex2_06_12\\src\\data\\G3.json";
        getGrapgAlgo(filename);


    }
}