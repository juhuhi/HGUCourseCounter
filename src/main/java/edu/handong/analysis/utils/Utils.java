package edu.handong.analysis.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;




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
			System.out.println ("The file path does not exist. Please check your CLI argument!");
			System.exit (0);
		} catch (IOException e) {
			System.out.println ("IOException");
			System.exit (0);
		}
        

		return line;

		
	}



public static void writeAFile(ArrayList<String> lines, String targetFileName) {
		//taretFileName이 올바른 경로에, 존재하는 파일인지 확인해야함.
		//그리고 lines에 있는 걸 file에 입력해야함.
	
		 File file = new File(targetFileName);
		 
		 //File directory = new File(tmp.getParentFile().getAbsolutePath());
		 //directory.mkdirs();
		 
		 if(!(file.getParentFile()==null)&&!file.getParentFile().exists()){
			// System.out.println("dd");
			 file.getParentFile().mkdirs();
		 }
		 
		 FileWriter writer = null;
		 
		 try {
			 writer = new FileWriter(file, false);
			 for(String line:lines) {
	            writer.write(line);
	            writer.write("\n");
	            writer.flush();
			 }
			 //System.out.println("done");
			
		} catch(IOException e) {
            e.printStackTrace();
            
        } try {
                if(writer != null) writer.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }


}




