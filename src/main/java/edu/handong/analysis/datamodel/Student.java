package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	private String studentId;
	private ArrayList<Course> coursesTaken;
	private HashMap<String,Integer> semestersByYearAndSememster;

	public Student(String studentId){
		this.studentId = studentId;
		
	}
	
	public void addCourse(Course newRecord) {
		this.coursesTaken.add(newRecord);
		String takedate = Integer.toString(newRecord.yearTaken)+"-"+Integer.toString(newRecord.semesterCourseTaken);
		if(!(this.semestersByYearAndSememster.containsKey(takedate))) {
			semestersByYearAndSememster.put(takedate, semestersByYearAndSememster.size()+1);
		}
	}
	
	public HashMap<String, Integer> getSemestersByYearAndSemester(){
		return semestersByYearAndSememster;
	}
	
	public int getNumCourseInNthSementer(int semester) {

		String[] array = new String[2];
		
        for (String key : semestersByYearAndSememster.keySet()) {
            if (Integer.toString(semester).equals(semestersByYearAndSememster.get(key))) {
            	array = key.split("-");
            }

        }
        int count=0;
        for(Course course:coursesTaken) {
        	if((course.semesterCourseTaken == Integer.parseInt(array[0].trim()))&&(course.yearTaken == Integer.parseInt(array[1].trim()))){
        	count++;	
        	}
        }
        
        return count;
    }


	
} 
