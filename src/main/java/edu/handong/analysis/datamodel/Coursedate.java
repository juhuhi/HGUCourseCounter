package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Coursedate {
   private String date;
   private ArrayList<Course>courseTakendate;
   
   
   public Coursedate(String date){
      this.date = date;
      courseTakendate = new ArrayList<Course>();      
   }
   
   
   public void addCourse(Course newRecord) {
      this.courseTakendate.add(newRecord);
   }
   
	public ArrayList<Course> getcourseTakendate(){
		return courseTakendate;
	}
	
   
   public int yearTaken() {
	   String yearTaken[]=date.split("-");
	   return Integer.parseInt(yearTaken[0].trim());
   }
   
   
   public int semesterTaken() {
	   String semesterTaken[]=date.split("-");
	   return Integer.parseInt(semesterTaken[1].trim());
   }
   
   

   

}