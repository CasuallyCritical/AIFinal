import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

	static int generations = 10000;
	static double trainingData[][][] = {{{0, 0}, {0}},
								 	    {{0, 1}, {1}},
									    {{1, 0}, {1}},
									    {{1, 1}, {0}}};
	
	public static void main(String[] args) {
		NeuralNetwork nn = new NeuralNetwork();
		Scanner scan = new Scanner(System.in);
		boolean flag = true;
		while(flag) {
			System.out.println("What do you want to do? (run, train, or exit)");
			String command = scan.nextLine();
			switch(command) {
			case "run":
				double[] result = new double[] {0,0,0,0};
				IntStream.range(0,  Main.trainingData.length).forEach(i -> {
					result[i] = nn.forwardPropagation(Main.trainingData[i][0])
							.neurons[NeuralNetwork.Input + NeuralNetwork.Hidden]
									.getOutput();
				});
				printResult(result);
				break;
			case "train":
				IntStream.range(0, generations).forEach(i -> {
					System.out.println("[Generation"+i+"]");
					IntStream.range(0,  trainingData.length).forEach(j -> 
					System.out.println(nn.forwardPropagation(trainingData[j][0])
							.backPropagation(trainingData[j][1][0])));
				});
				break;
			case "exit":
				flag = false;
				break;
			}
		}
	}
	
	static void printResult(double[] result) {
		System.out.println(" X1 | X2 | Target | Result");
		System.out.println("--------------------------");
		IntStream.range(0, trainingData.length).forEach(i -> {
			IntStream.range(0, trainingData[0][0].length).forEach(j -> System.out.print(" " + trainingData[i][0][j] + " | "));
			System.out.print(" " + trainingData[i][1][0] + " | " + String.format("%.5f", result[i]) + " \n");
		});
		
	}
	
	
}
