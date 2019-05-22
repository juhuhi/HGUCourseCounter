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
//		for(int i:seme)
//		return 0;
		//public static <K, V> K getKey(Map<K, V> map, V value) 
		String array[];
		
        for (String key : semestersByYearAndSememster.keySet()) {
            if (Integer.toString(semester).equals(semestersByYearAndSememster.get(key))) {
            	array = key.split("-");
            }
//            else {
//            	array[0]="a";
//            	array[1]="a";
//            }
        }
        int count=0;
        for(Course course:coursesTaken) {
        	if((course.semesterCourseTaken == Integer.parseInt(array[0]))&&(course.yearTaken == Integer.parseInt(array[1]))){
        	count++;	
        	}
        }
        
        return count;
    }


	
} 
