package edu.handong.analysis.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

import edu.handong.analysis.datamodel.Student;

public class Utils {
	public static ArrayList<String> getLines(String file,boolean removeHeader){
		ArrayList<String> line= new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			while(true) {
	            String fileline = br.readLine();
	            if (fileline==null) break;
	            if(removeHeader==false) {
	            	line.add(fileline);
	            }
	            removeHeader=false;
	        }
	        br.close();
		} catch (FileNotFoundException e) {
			System.out.println ("Error opening the file " + file);
			System.exit (0);
		} catch (IOException e) {
			System.out.println ("IOException");
			System.exit (0);
		}
        
//		Scanner inputStream = null;
//	
//			try {
//				inputStream = new Scanner(new File(file));
//			}  catch (FileNotFoundException e) {
//				System.out.println ("Error opening the file " + file);
//				System.exit (0);
//			}
//			
//			while (inputStream.hasNextLine()) {
//				String flieline = inputStream.nextLine();
//				System.out.println (line);
//				System.out.println ("\n");
//				line.add(flieline);
//			}
//			inputStream.close ();
		return line;

		
	}

	public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		
	}

}
