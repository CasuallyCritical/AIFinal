import java.util.Arrays;
import java.util.stream.IntStream;

public class NeuralNetwork {
	static enum LayerType {I, H, O}
	static int Input = 2;
	static int Hidden = 2;
	static double rate = 0.8;
	public Neuron[] neurons = new Neuron[Input + Hidden + 1];
	
	public NeuralNetwork() {
		IntStream.range(0, Input).forEach(i -> neurons[i] = new Neuron(LayerType.I));
		IntStream.range(Input, Input+Hidden).forEach(i -> neurons[i] = new Neuron(LayerType.H));
		neurons[Input + Hidden] = new Neuron(LayerType.O);
	}
	
	public Neuron[] getNeurons() {
		return neurons;
	}
	
	public NeuralNetwork forwardPropagation(double input[]) {
		double weightedSum = 0;
		for(int i = 0; i < neurons.length; i++) {
			switch(neurons[i].layerType) {
			case I:
				neurons[i].setOutput(input[i]);
				break;
			case H:
				weightedSum = neurons[i].getThreshold() +
							  neurons[i].getWeights()[0] * neurons[0].getOutput() +
							  neurons[i].getWeights()[1] * neurons[1].getOutput();
				neurons[i].Activate(weightedSum);
				break;
			case O:
				weightedSum = neurons[i].getThreshold() +
							  neurons[i].getWeights()[0] * neurons[2].getOutput() +
							  neurons[i].getWeights()[1] * neurons[3].getOutput();
				neurons[i].Activate(weightedSum);
				break;
			}
		}
		
		return this;
	}
	
	public NeuralNetwork backPropagation(double targetResult) {
		neurons[4].setError((targetResult - neurons[4].getOutput()) * neurons[4].Derivative());
		neurons[4].setThreshold(neurons[4].getThreshold() + rate * neurons[4].getError());
		neurons[4].getWeights()[0] = neurons[4].getWeights()[0] + rate * neurons[4].getError() * neurons[2].getOutput();
		neurons[4].getWeights()[1] = neurons[4].getWeights()[1] + rate * neurons[4].getError() * neurons[3].getOutput();

		neurons[3].setError((neurons[4].getWeights()[1] * neurons[4].getError()) * neurons[3].Derivative());
		neurons[3].setThreshold(neurons[3].getThreshold() + rate * neurons[3].getError());
		neurons[3].getWeights()[0] = neurons[3].getWeights()[0] + rate * neurons[3].getError() * neurons[0].getOutput();
		neurons[3].getWeights()[1] = neurons[3].getWeights()[1] + rate * neurons[3].getError() * neurons[1].getOutput();

		neurons[2].setError((neurons[4].getWeights()[0] * neurons[4].getError()) * neurons[2].Derivative());
		neurons[2].setThreshold(neurons[2].getThreshold() + rate * neurons[2].getError());
		neurons[2].getWeights()[0] = neurons[2].getWeights()[0] + rate * neurons[2].getError() * neurons[0].getOutput();
		neurons[2].getWeights()[1] = neurons[2].getWeights()[1] + rate * neurons[2].getError() * neurons[1].getOutput();
		return this;
	}
	
	public Neuron[] getNetwork() {
		return neurons;
	}
	
	public String toString() {
		return Arrays.toString(neurons);
	}
}
