import GUI.GraphFrame;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.*;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.Scanner;
import java.io.File;

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

        DirectedWeightedGraph ans ;
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
//        Scanner file = new Scanner(args[4]);
//        getGrapgAlgo(String.valueOf(file));
////        runGUI(String.valueOf(file));
//
        getGrapgAlgo(args[1]);


    }
}