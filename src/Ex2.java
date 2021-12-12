import GUI.GraphFrame;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import classes.AlgoGraphClass;
import classes.GraphClass;

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

        return ans;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) throws IOException {
        new GraphFrame((GraphClass) getGrapg(json_file));

    }

    public static void main(String[] args) throws IOException {
        getGrapg("G1.json");
        runGUI("G1.json");
//        System.out.println(args.length);
//       Scanner file = new Scanner(args[0]);
//        System.out.println(file);
//        getGrapg(String.valueOf("G2.json"));
//        runGUI("G2.json");
//        getGrapgAlgo(String.valueOf(file));
//       runGUI(String.valueOf(file));


    }
}