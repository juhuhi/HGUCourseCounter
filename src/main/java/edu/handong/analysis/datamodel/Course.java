package edu.handong.analysis.datamodel;

import java.util.ArrayList;

public class Course {
	private ArrayList<Course> coursesTaken;
	private String studentId;
	private String yearMonthGraduated;
	private String firstMajor;
	private String secondMajor;
	private String courseCode;
	private String courseName;
	private String courseCredit;
	int yearTaken;
	int semesterCourseTaken;
	
	public Course(String[] array){
		studentId = array[0].trim();
		yearMonthGraduated= array[1].trim();
		firstMajor= array[2].trim();
		secondMajor= array[3].trim();
		courseCode= array[4].trim();
		courseName= array[5].trim();
		courseCredit= array[6].trim();
		yearTaken= Integer.parseInt(array[7].trim());
		semesterCourseTaken= Integer.parseInt(array[8].trim());
	}
	
	public String getcourseCode(){
		return courseCode;
	}
	
	public int getyearTaken(){
		return yearTaken;
	}
	public String getcourseName(){
		return courseName;
	}
	public int getsemesterCourseTaken(){
		return semesterCourseTaken;
	}

}
