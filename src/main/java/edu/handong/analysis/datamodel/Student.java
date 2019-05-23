package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	private String studentId;
	private ArrayList<Course> coursesTaken;
	private HashMap<String,Integer> semestersByYearAndSememster;

	public Student(String studentId){
		this.studentId = studentId;
		coursesTaken = new ArrayList<Course>();
		semestersByYearAndSememster = new HashMap<String,Integer>();
		
	}
	
	public void addCourse(Course newRecord) {
		this.coursesTaken.add(newRecord);
		String takedate = Integer.toString(newRecord.yearTaken)+"-"+Integer.toString(newRecord.semesterCourseTaken);
		//System.out.println(takedate);
		if(!(this.semestersByYearAndSememster.containsKey(takedate))) {
			semestersByYearAndSememster.put(takedate, semestersByYearAndSememster.size()+1);
		}
	}
	
	public HashMap<String, Integer> getSemestersByYearAndSemester(){
		return semestersByYearAndSememster;
	}
	
	public int getNumCourseInNthSementer(int semester) {

		String[] array = new String[2];
	//	System.out.println(semester);
        for(String key : semestersByYearAndSememster.keySet()) {
            if (Integer.toString(semester).equals(Integer.toString(semestersByYearAndSememster.get(key)))) {
            	array = key.split("-");
//            	System.out.println(array[0]+"  , "+array[1]);
//            	System.out.println("dd");
            }

        }
        int count=0;
        //System.out.println(array[0]+","+array[1]);
        for(Course course:coursesTaken) {
        	if((Integer.toString(course.semesterCourseTaken).equals(array[1]))&&
        			(Integer.toString(course.yearTaken).equals(array[0]))){
        		//System.out.println("dd");
        		
        		
        		count++;	
        	}
        }
        
        return count;
    }


	
} 
