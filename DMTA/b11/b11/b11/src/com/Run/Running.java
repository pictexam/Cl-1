package com.Run;

import com.Classifiers.Classifier;
import com.Classifiers.Classifier2;

public class Running {
	
	public static void main(String[] args) {
		
		Classifier c = new Classifier();
		Classifier2 c1 = new Classifier2();
		try {
			c.run_my();
			c1.run_my();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}		
	}

}