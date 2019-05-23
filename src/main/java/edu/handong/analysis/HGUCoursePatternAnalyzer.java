package edu.handong.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

public class HGUCoursePatternAnalyzer {

	private HashMap<String,Student> students;
	
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 */
	public void run(String[] args) {
		
		try {
			// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
			if(args.length<2)
				throw new NotEnoughArgumentException();
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		String dataPath = args[0]; // csv file to be analyzed
		String resultPath = args[1]; // the file path where the results are saved.
		ArrayList<String> lines = Utils.getLines(dataPath, true);
		//System.out.println(lines);
		
		students = loadStudentCourseRecords(lines);
		
		// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
		Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
		
		// Generate result lines to be saved.
		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		
		// Write a file (named like the value of resultPath) with linesTobeSaved.
		Utils.writeAFile(linesToBeSaved, resultPath);
	}
	
	/**
	 * This method create HashMap<String,Student> from the data csv file. Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param lines
	 * @return
	 */
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<String> lines) {
		
		// TODO: Implement this method
		HashMap<String,Student> resultstudentinfo = new HashMap<String,Student>();
		//ArrayList<Student> makestudent= new ArrayList<Student>();
		//ArrayList<Course> makeCourse = new ArrayList<Course>();
		
		for(String studentinfo: lines) {
			String[] array = studentinfo.split(",");
			System.out.println(studentinfo);
			Course mycourse =  new Course(studentinfo);
			
			
			//System.out.println(mycourse.studentId);
			if(resultstudentinfo.containsKey(array[0])) {
				resultstudentinfo.get(array[0]).addCourse(mycourse);
			}
			else {
				Student mystudent = new Student(array[0]);
				mystudent.addCourse(mycourse);
				resultstudentinfo.put(array[0],mystudent);
				
			}
				
		}
		
		return resultstudentinfo; // do not forget to return a proper variable.
	}

	/**
	 * This method generate the number of courses taken by a student in each semester. The result file look like this:
	 * StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester
	 * 0001,14,1,9
     * 0001,14,2,8
	 * ....
	 * 
	 * 0001,14,1,9 => this means, 0001 student registered 14 semeters in total. In the first semeter (1), the student took 9 courses.
	 * 
	 * 
	 * @param sortedStudents
	 * @return
	 */
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents) {
		
		// TODO: Implement this method
		ArrayList<String> result = new ArrayList<String>();
		result.add("StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester");
		
		for(String laststudent: sortedStudents.keySet()) {
			Student newStudent = sortedStudents.get(laststudent);
			int numOfsemester = newStudent.getSemestersByYearAndSemester().size();
			
			for(int i=0; i<numOfsemester;i++) {
				String laststring =  laststudent + ","+ numOfsemester +","+ i+","+ newStudent.getNumCourseInNthSementer(i+1);
				result.add(laststudent);
			}
		}
		
//		for(Map.Entry<String, Student> entry : sortedStudents.entrySet()) {
//			int numOfsemester = entry.getValue().getSemestersByYearAndSemester().size();
//			for(int i=0; i<numOfsemester)
//		}
		
		
		return result; // do not forget to return a proper variable.
	}
}
