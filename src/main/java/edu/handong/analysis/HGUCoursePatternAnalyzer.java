package edu.handong.analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class HGUCoursePatternAnalyzer {

	private HashMap<String,Student> students;
	String input;
	String output;
	String analysis;
	String coursecode;
	String startyear;
	String endyear;
	boolean help;
	
	//C:\Users\한주희\Desktop\hw5data.csv C:\Users\한주희\Desktop\Iwannagobakchame\ladfasdfasdfsdfd.csv
	
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
		
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			if (help){
				printHelp(options);
				return;
			}

			ArrayList<String[]> line= new ArrayList<String[]>();
			try {
				Reader reader = Files.newBufferedReader(Paths.get(input));
			    CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			    
				for (CSVRecord csvRecord : csvParser) {
					String flieline[] = new String[9];
					for(int i=0; i<9;i++) {
						flieline[i] = csvRecord.get(i);
					}
					line.add(flieline);
				}			

			} catch (FileNotFoundException e) {
				System.out.println ("The file path does not exist. Please check your CLI argument!");
				System.exit (0);
			} catch (IOException e) {
				System.out.println ("IOException");
				System.exit (0);
			}
			
			students = loadStudentCourseRecord(line);
			
			// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
			Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
		
//		String dataPath = args[0]; // csv file to be analyzed
//		String resultPath = args[1]; // the file path where the results are saved.
//		ArrayList<String> lines = Utils.getLines(dataPath, true);
//		//System.out.println(lines);
//		
//		students = loadStudentCourseRecords(lines);
//		
//		// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
//		Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
//		
//		// Generate result lines to be saved.
//		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
//		
//		// Write a file (named like the value of resultPath) with linesTobeSaved.
//		Utils.writeAFile(linesToBeSaved, resultPath);
			
	}

}
	
	/**
	 * This method create HashMap<String,Student> from the data csv file. Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param lines
	 * @return
	 */
	//오버라이딩 하고 싶은데 왜 안될까?
	private HashMap<String,Student> loadStudentCourseRecord(ArrayList<String[]>lines) {
		HashMap<String,Student> resultstudentinfo = new HashMap<String,Student>();
		for (String[] studentinfo:lines) {
			Course mycourse = new Course(studentinfo);
			
			if(resultstudentinfo.containsKey(studentinfo[0])) {
				resultstudentinfo.get(studentinfo[0]).addCourse(mycourse);
			}
			else {
				Student mystudent = new Student(studentinfo[0]);
				mystudent.addCourse(mycourse);
				resultstudentinfo.put(studentinfo[0],mystudent);
			}
		}

		return resultstudentinfo;
	}
	
//	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<String>lines) {
//		
//		// TODO: Implement this method
//		HashMap<String,Student> resultstudentinfo = new HashMap<String,Student>();
//		
//		for(String studentinfo: lines) {
//			String[] array = studentinfo.split(",");
//			Course mycourse =  new Course(studentinfo);
//			
//			
//			//System.out.println(mycourse.studentId);
//			if(resultstudentinfo.containsKey(array[0])) {
//				resultstudentinfo.get(array[0]).addCourse(mycourse);
//			}
//			else {
//				Student mystudent = new Student(array[0]);
//				mystudent.addCourse(mycourse);
//				resultstudentinfo.put(array[0],mystudent);
//				
//			}
//				
//		}
//		
//		return resultstudentinfo; // do not forget to return a proper variable.
//	}

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
			
			for(int i=1; i<numOfsemester+1;i++) {
				String laststring =  laststudent + ","+ numOfsemester +","+ i+","+ newStudent.getNumCourseInNthSementer(i);
				result.add(laststring);
			}
		}
		
		
		return result; // do not forget to return a proper variable.
	}
	
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			input = cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			analysis = cmd.getOptionValue("a");
			coursecode = cmd.getOptionValue("c");
			startyear = cmd.getOptionValue("s");
			endyear = cmd.getOptionValue("e");

			help = cmd.hasOption("h");


		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input flie path")
				.hasArg()
				.argName("input path")
				.required()
				.build());
		
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output flie path")
				.hasArg()
				.argName("output path")
				.required()
				.build());
		
		options.addOption(Option.builder("a").longOpt("analysis")
				.desc("1: Count courses per semester, 2: Count per course name and year")
				.hasArg()
				.argName("Analysis option")
				.required()
				.build());
		
		options.addOption(Option.builder("c").longOpt("coursecode")
				.desc("Course code for '-a 2' option")
				.hasArg()
				.argName("course code")
				.build());
		
		options.addOption(Option.builder("s").longOpt("startyear")
				.desc("Set the start year for analysis e.g., -s 2002")
				.hasArg()
				.argName("Start year for analysis")
				.required()
				.build());
		
		options.addOption(Option.builder("e").longOpt("endyear")
				.desc("Set the end year for analysis e.g., -e 2005 ")
				.hasArg()
				.argName("End year for analysis")
				.required()
				.build());

		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Help")
		        .build());
		


		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "HGU Course program";
		String footer =";";
		formatter.printHelp("HGUCourseCounter", header, options, footer, true);
	}

}



