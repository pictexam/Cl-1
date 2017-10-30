package com.Classifiers;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

public class Classifier {
private static final Logger logger = LoggerFactory.getLogger(Classifier.class);
	
    public void run_my () throws Exception 
    { 
            BufferedReader breader = null;
            System.out.println("Decision Tree\n");
			breader = new BufferedReader (new FileReader("src/test.arff")); 
			Instances train = new Instances(breader); 
			train.setClassIndex(train.numAttributes()-1); 
			
			breader = new BufferedReader (new FileReader("src/test.arff")); 
			Instances test = new Instances(breader); 
			test.setClassIndex(test.numAttributes()-1); 
			
			breader.close();
			
			J48 tree= new J48(); 
			tree.setOptions(null); 
			tree.buildClassifier(train); 
			               
			Evaluation eval = new Evaluation(train);
			eval.evaluateModel(tree, test);
			
			logger.info(eval.toSummaryString("\nSummary\n======\n", false));
			logger.info(eval.toClassDetailsString("\nClass Details\n======\n"));
			logger.info(eval.toMatrixString("\nConfusion Matrix: false positives and false negatives\n======\n"));
    }
}
