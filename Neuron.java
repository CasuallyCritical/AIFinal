import java.util.ArrayList;

public class Neuron {

	public NeuralNetwork.LayerType layerType;
	
	public void Activate(double weight) {
		output = 1.0 / (1 + Math.exp(-1.0 * weight));
	}
	
	public double Derivative() {
		return output * (1.0 - output);
	}
	
	public Neuron(NeuralNetwork.LayerType layer) {
		this.layerType = layer;
	}
	
	public double threshold = 0.5 - Math.random();
	public double[] weights = {0.5 - Math.random(), 0.5 - Math.random()};
	public double output = 0;
	public double error = 0;
	
	public Neuron parent;
	public ArrayList<Neuron> children;
	
	public double getThreshold() {
		return threshold;
	}
	
	public void setThreshold(double val) {
		threshold = val;
	}
	
	public double getOutput() {
		return output;
	}
	
	public void setOutput(double x) {
		output = x;
	}
	
	public double getError() {
		return error;
	}
	
	public void setError(double x) {
		error = x;
	}
	
	public double[] getWeights() {
		return weights;
	}
	
	
	
	public String toString() {
		String returnValue = "";
		
		if(layerType == NeuralNetwork.LayerType.I) {
			returnValue = "(" + layerType + ": " + String.format("%.2f", output) + ")";
		} else {
			returnValue = "("+layerType+", "
					+String.format("%.2f", weights[0])+", "+String.format("%.2f", weights[1])+", "
					+String.format("%.2f", threshold)+", "+String.format("%.2f", output)+")";
		}
		
		return returnValue;
	}
	
	
	
	
}
