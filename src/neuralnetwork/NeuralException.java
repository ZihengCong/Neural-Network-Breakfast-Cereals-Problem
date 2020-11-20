package neuralnetwork;

/**
 *
 * @author Ziheng Cong
 */

public class NeuralException extends Exception{
    /**
     * No-parameter construction method
     */
    public NeuralException(){
        super();
    }
    /**
     * Construct a new exception with the specified details and reason
     * @param message
     * @param cause 
     */
    public NeuralException(String message, Throwable cause){
        super(message, cause);
    }
    /**
     * Parametric construction method
     * @param message 
     */
    public NeuralException(String message){
        super(message);
    }
    /**
     * Construct a new exception with the specified reason
     * @param cause 
     */
    public NeuralException(Throwable cause){
        super(cause);
    }
}
