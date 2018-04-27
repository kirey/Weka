package com.kubris.weka.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instance;
import weka.core.Instances;

public class WekaTest {

	public static void main(String[] args) throws Exception{
		BufferedReader buffer = new BufferedReader(new FileReader("C:/Users/paunovicm/Documents/Weka assets/data/diabetes.arff"));
		
		Instances train = new Instances(buffer);
		train.setClassIndex(train.numAttributes() - 1);
		buffer.close();

		
		
		MultilayerPerceptron wekaClassifers = new MultilayerPerceptron();
		wekaClassifers.buildClassifier(train);
		

		Evaluation eval = new Evaluation(train);
		Random rand = new Random(1);
//		wekaClassifers.setSeed(2);
		
		eval.crossValidateModel(wekaClassifers, train, 5, rand);
		double[][] matrix = eval.confusionMatrix();
		
		for(int x=1; x<100; x++) {
			Instance instance = train.get(x);
			System.out.println("----------------------------:"+wekaClassifers.classifyInstance(instance));
		}

		System.out.println("Class index is: "+train.get(1).classIndex());

		
		System.out.println("=== Confusion Matrix ===");
		for (int i = 0; i < matrix.length; i++) {
		    for (int j = 0; j < matrix[i].length; j++) {
		        System.out.print(matrix[i][j] + "  ");
		    }
		    System.out.println();
		}
		System.out.println("\n" + eval.toSummaryString());
		System.out.println("\n" + eval.fMeasure(1) + " " + eval.precision(1) + " " + eval.recall(1));

	}

}
