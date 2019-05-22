package edu.handong.analysis.datamodel;

public class Course {
	public String studentId;
	private String yearMonthGraduated;
	private String firstMajor;
	private String secondMajor;
	private String courseCode;
	private String courseName;
	private String courseCredit;
	int yearTaken;
	int semesterCourseTaken;
	
	public Course(String line){
		String[] array = line.split(",");
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
	
}
