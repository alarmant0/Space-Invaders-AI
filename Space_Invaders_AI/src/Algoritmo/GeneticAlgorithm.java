package Algoritmo;

import nn.NeuralNetwork;
import space.*;
import java.util.Arrays;
import java.util.Random;

public class GeneticAlgorithm {
	
	private static final int POPULATION_SIZE = 10000;
	private static final int NUM_GENERATIONS = 10000;
	private static final double MUTATION_RATE = 0.1;
	private static final double CROSSOVER_RATE = 0.7;
	private static Random random = new Random();

    public GeneticAlgorithm(){
		NeuralNetwork [] population = new NeuralNetwork[POPULATION_SIZE];
		for (int i = 0; i < POPULATION_SIZE; i++) {
			population[i] = new NeuralNetwork(Commons.STATE_SIZE, 10, Commons.NUM_ACTIONS);
		}
		for (int i = 0; i < NUM_GENERATIONS; i++) {
			Arrays.sort(population);
			//NeuralNetwork[] newPopulation = new NeuralNetwork[POPULATION_SIZE];
//			for (int j = 0; j < POPULATION_SIZE; j++) {
			NeuralNetwork parent1 = selectParent(population);
			NeuralNetwork parent2 = selectParent(population);
			NeuralNetwork child = crossover(parent1, parent2);
			mutate(child);
			population[random.nextInt(population.length)] = child;
//			}
			//population = newPopulation;		
			//System.out.println("BEST: " + population[0].getBoard().getFitness());
		}
		Arrays.sort(population);
		System.out.println("BEST OF ALL : " + population[0].getBoard().getFitness());		
		SpaceInvaders.showControllerPlaying(population[0], 10);
	}
    
    private NeuralNetwork selectParent(NeuralNetwork[] population) {
        NeuralNetwork[] tournament = new NeuralNetwork[10];
        for (int i = 0; i < 10; i++) {
            tournament[i] = population[random.nextInt(population.length)];
        }
        Arrays.sort(tournament);
        return tournament[0];
    }
    
    private void mutate(NeuralNetwork child) {
    	double[] childChromosome = child.getChromossome();
    	if (random.nextDouble() < MUTATION_RATE) {
            childChromosome[random.nextInt(childChromosome.length)] += random.nextGaussian() * 0.5;
        }
    }
     
    private NeuralNetwork crossover(NeuralNetwork parent1, NeuralNetwork parent2) {
    	NeuralNetwork child = new NeuralNetwork(Commons.STATE_SIZE, 10, Commons.NUM_ACTIONS);
    	double [] childChromosome = child.getChromossome();
    	double randomValue = random.nextDouble();
		for (int i = 0; i < child.getChromossomeSize(); i++) {
			childChromosome[i] = (randomValue < CROSSOVER_RATE) ? parent1.getChromossome()[i] : parent2.getChromossome()[i];
		}
		return child;
	}

    
}