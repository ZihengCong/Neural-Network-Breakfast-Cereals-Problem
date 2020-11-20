package neuralnetwork;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Ziheng Cong
 */

public class NeuralNetTest {
    public static void main(String[] args) throws NeuralException {
        int numInputs = 2; //Specify the number of inputs
        double Learning_Rate = 0.001; //Learning rate for Delta Rule
        int Number_Of_Iteration = 500; //Number of Iteration
        
        Neuron one = new Neuron(numInputs); //Creates a neuron object.
        //Create an array of double values and intialise them with random values for inputs.
        double[] inputs = new double[numInputs];
        for (int i = 0; i < inputs.length; i++) {
            int randomValues = ThreadLocalRandom.current().nextInt(1,30+1);
            inputs[i] = randomValues;
        }
        
        //Initialises weights to random values.
        one.init(); 
        
        //Set activation Function as Linear Method.
        one.setActivationFnc("Linear", 1.0);
        
        //Load training data into arrays.
        //Health_rate
        double[] datasetoutput = {54.8, 38.8, 43.4, 7.6, 40.2, -11, 79.6, 35.8, 92, 37.6, 94.2, 10, 81.8, 19.6, 47, 79.6, 11.6, -60.2, 12,-54.6, 32.2, 16.4, -11, -2, -10.8};
        //Nut_Quantity
        double[] datasetinput1 = {24, 16, 19, 20, 23, 3, 28, 25, 30, 14, 29, 24, 27, 12, 25, 28, 8, 5, 18, 5, 19, 16, 3, 4, 8};
        //Sugar_Level
        double[] datasetinput2 = {10, 6, 8, 22, 14, 8, 6, 18, 4, 4, 2, 26, 4, 8, 14, 6, 6, 28, 18, 26, 12, 14, 8, 6, 14};

        for (int iter = 0; iter < Number_Of_Iteration; iter++) {
            for (int d = 0; d < datasetinput1.length; d++) {
                inputs[0] = datasetinput1[d];//Nut
                inputs[1] = datasetinput2[d];//Sugar
                //Set the inputs of the Neutron
                one.setInputs(inputs);
                //Calculate output based in current weights and given inputs
                one.calc();
                //Apply Delta Rule to update weights
                double output_value = one.getOutput(); 
                double delta = datasetoutput[d] - output_value;
                //Store new weight in array
                double[] new_weight = new double[one.getWeights().length];
                new_weight[0] = one.getWeights()[0] + Learning_Rate*delta*datasetinput1[d];//Constant A
                new_weight[1] = one.getWeights()[1] + Learning_Rate*delta*datasetinput2[d];//Constant B
                new_weight[2] = one.getWeights()[2] + Learning_Rate*delta;//Constant C
                //Set new weight and next data can use
                one.setWeight(0, new_weight[0]);
                one.setWeight(1, new_weight[1]);
                one.setWeight(2, new_weight[2]);
                //Print appropriate output for each iteration.
                System.out.println(one.toString());
            }
        }
        
        //Load testing data into arrays.
        //Test nut_quantity
        double[] testdatainput1 = {2, 29, 15, 28, 4, 12, 13, 4, 29, 2, 11, 1, 6, 22, 14, 9, 6, 6, 19, 3, 24, 14, 10, 16, 24};
        //Test sugar_level
        double[] testdatainput2 = {28, 16, 12, 12, 24, 8, 4, 30, 18, 8, 26, 2, 20, 20, 18, 24, 22, 12, 24, 28, 16, 14, 16, 26, 20};
        //Test health_rate
        double[] testdataoutput = new double[testdatainput1.length];
                  
        //Apply the testimated weight on test set
        System.out.println("Apply the esstimated weight on the test set:");
        System.out.println("Nut_Quantity  Sugar Level  Estimated_health_rating");
        for (int t = 0; t < testdatainput1.length; t++) {
            //Apply the estimated weights to compute output.
            testdataoutput[t] = testdatainput1[t]*one.getWeights()[0] + testdatainput2[t]*one.getWeights()[1] + one.getWeights()[2];
            //Print out the test data and estimated weights. 
            StringBuilder str = new StringBuilder();
            str.append(String.format("%.0f              ", testdatainput1[t]))
               .append(String.format("%.0f              ", testdatainput2[t]))
               .append(String.format("%.1f  ", testdataoutput[t]));
            System.out.println(str.toString());
        }
    }
}
