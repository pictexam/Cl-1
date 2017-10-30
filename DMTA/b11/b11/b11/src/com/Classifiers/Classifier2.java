package com.Classifiers;
import java.io.*;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class Classifier2 {
	public static BufferedReader readDataFile(String filename) {
		BufferedReader inputReader = null;
		
		try {
			inputReader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException ex) {
			System.err.println("File not found: " + filename);
		}
 
		return inputReader;
	}
	public void run_my() {
		BufferedReader read;
		try {
			System.out.println("Naive Bayes\n");
			read = new BufferedReader(new FileReader("src/test.arff"));
			Instances train = new Instances(read);
			train.setClassIndex(train.numAttributes()-1);
			read.close();
			
			NaiveBayes nB = new NaiveBayes();
			nB.buildClassifier(train);
			Evaluation eval = new Evaluation(train);
			eval.crossValidateModel(nB, train, 10, new Random(1));
			System.out.println(eval.toSummaryString("\nResults===============>\n",true));
			System.out.println(eval.fMeasure(1)+" "+eval.precision(1)+" "+eval.recall(1));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
}