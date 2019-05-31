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

}