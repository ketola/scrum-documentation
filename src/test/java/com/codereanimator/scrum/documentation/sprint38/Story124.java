package com.codereanimator.scrum.documentation.sprint38;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import com.codereanimator.scrum.documentation.export.ExcelExporter;
import com.codereanimator.scrum.documentation.export.WordExporter;
import com.codereanimator.scrum.documentation.model.Story;
import com.codereanimator.scrum.documentation.model.Test;
import com.codereanimator.scrum.documentation.sourceparser.SourceParser;

/**
 * As a Tester, I want documentation about the features and test cases the project contains so that
 * I know what I need to test manually and what is tested automatically.
 * 
 * @author Sauli
 *
 */
public class Story124 {
	
	private static final String STORY_123_SOURCE_PATH = new File(".").getAbsolutePath() + "/src/test/java".replaceAll("/", "\\"+File.separator) + File.separator + Story123.class.getCanonicalName().replaceAll("\\.", "\\"+File.separator) + ".java"; //"C:\\Users\\Sauli\\Programming\\Java\\eclipse-indigo-workspace\\tddpoc\\src\\test\\java\\fi\\mandatumlife\\tdd\\poc\\tddpoc\\sprint38\\Story644.java";
	private static final String TESTS_DIRECTORY = new File(".").getAbsolutePath() + "/src/test/java".replaceAll("/", "\\"+File.separator);
	
	/**
	 * Story number is 123 and the description is
	 * "As a User, I want to log in to the application so that I can access my personal information."
	 */
	@org.junit.Test
	public void testStory123Information() throws Exception {
		Story story = new SourceParser().parseStory(new File(STORY_123_SOURCE_PATH));
		Assert.assertEquals(123, story.getNumber());
		Assert.assertEquals("As a User, I want to log in to the application so that I can access my personal information.", story.getDescription());
	}
	
	/**
	 * Test directory contains two stories.
	 */
	@org.junit.Test
	public void testReadStoriesFromDirectory(){
		List<Story> stories = new SourceParser().parseStories(new File(TESTS_DIRECTORY));
		Assert.assertEquals(2, stories.size());
	}
	
	/**
	 * Story 123 contains 4 test cases.
	 * 1 Manual test, 1 robot test and 2 unit tests.
	 */
	@org.junit.Test
	public void testStory123TestTypes() throws Exception {
		Story story = new SourceParser().parseStory(new File(STORY_123_SOURCE_PATH));
		
		Assert.assertEquals(4, story.getTests().size());
		
		int countJunitTests = 0;
		int countManualTests = 0;
		int countRobotTests = 0;
		
		for(Test test : story.getTests()){
			if(Test.Type.JUNIT.equals(test.getType()))
				countJunitTests++;
			
			if(Test.Type.MANUAL.equals(test.getType()))
				countManualTests++;
			
			if(Test.Type.ROBOT.equals(test.getType()))
				countRobotTests++;
		}
		
		Assert.assertEquals(2, countJunitTests);
		Assert.assertEquals(1, countManualTests);
		Assert.assertEquals(1, countRobotTests);
	}
	
	/**
	 * Test names:
	 * User enters wrong credentials
	 * User enters wrong credentials 3 times
	 * User enters correct credentials
	 * User form validations
	 * 
	 */
	@org.junit.Test
	public void testStory123TestNames() throws Exception {
		Set<String> testNames = new HashSet<String>();
		
		Story story = new SourceParser().parseStory(new File(STORY_123_SOURCE_PATH));
		
		for(Test test : story.getTests()){
			testNames.add(test.getName());
		}
		
		Assert.assertTrue(testNames.contains("User enters wrong credentials"));
		Assert.assertTrue(testNames.contains("User enters wrong credentials 3 times"));
		Assert.assertTrue(testNames.contains("User enters correct credentials"));
		Assert.assertTrue(testNames.contains("User form validations"));
	}
	
	/**
	 * This isn't an actual test. Just used to generate the report. 
	 * But cause the method name does not start with "test" it will not be added to the report.
	 */
	@org.junit.Test
	public void generateWordReport() throws Exception {
		List<Story> stories = new SourceParser().parseStories(new File(TESTS_DIRECTORY));
		FileOutputStream fileOutputStream = new FileOutputStream(new File("c:\\temp\\report.doc"));
		new WordExporter().export(stories, fileOutputStream);
		fileOutputStream.close();
	}
	
	/**
	 * This isn't an actual test. Just used to generate the report. 
	 * But cause the method name does not start with "test" it will not be added to the report.
	 */
	@org.junit.Test
	public void generateExcelReport() throws Exception {
		List<Story> stories = new SourceParser().parseStories(new File(TESTS_DIRECTORY));
		FileOutputStream fileOutputStream = new FileOutputStream(new File("c:\\temp\\report.xls"));
		new ExcelExporter().export(stories, fileOutputStream);
		fileOutputStream.close();
	}
}
