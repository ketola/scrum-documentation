package com.codereanimator.scrum.documentation.sourceparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.codereanimator.scrum.documentation.annotation.Manual;
import com.codereanimator.scrum.documentation.annotation.Robot;
import com.codereanimator.scrum.documentation.model.Story;
import com.codereanimator.scrum.documentation.model.Test;
import com.codereanimator.scrum.documentation.model.Test.Type;
import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.Annotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaSource;


public class SourceParser {
	
	/**
	 * Extracts a User Story from a java source file.
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Story parseStory(File file) throws FileNotFoundException, IOException {
		JavaDocBuilder javaDocBuilder = new JavaDocBuilder();
		javaDocBuilder.addSource(file);
		
		JavaSource source = javaDocBuilder.getSources()[0];
		JavaClass javaClass = source.getClasses()[0];
	
		return toStory(javaClass);
	}
	
	private Story toStory(JavaClass javaClass){
		int storyNumber = Integer.parseInt(StringUtils.substringAfter(javaClass.getName(), "Story"));
		Story story = new Story(storyNumber, javaClass.getComment());
		story.setTests(readTests(javaClass));
		
		return story;
	}
	
	private List<Test> readTests(JavaClass javaClass){
		List<Test> tests = new ArrayList<Test>();
		
		for(JavaMethod method : javaClass.getMethods()){
			if(isTestMethod(method)){
				tests.add(toTest(method));
			}
		}
		
		return tests;
	}
	
	private Test toTest(JavaMethod method){
		String[] wordsInTestName = StringUtils.splitByCharacterTypeCamelCase((StringUtils.substringAfter(method.getName(), "test")));
		
		StringBuilder testName = new StringBuilder();
		
		for(String word : wordsInTestName){
			testName.append(word.toLowerCase());
			testName.append(" ");
		}
		
		String description = method.getComment();
		Type type = testType(method);
		
		
		Test test = new Test(StringUtils.capitalize(testName.toString()).trim(), description, type );
		return test;
	}
	
	private boolean isTestMethod(JavaMethod method){
		Annotation[] annotations = method.getAnnotations();
		
		if(ArrayUtils.isEmpty(annotations)){
			return false;
		}
		
		for(Annotation annotation : annotations){
			if(org.junit.Test.class.getCanonicalName().equals(annotation.getType().getFullyQualifiedName())){
				return true;
			}
			
			if(Manual.class.getCanonicalName().equals(annotation.getType().getFullyQualifiedName())){
				return true;
			}
			
			if(Robot.class.getCanonicalName().equals(annotation.getType().getFullyQualifiedName())){
				return true;
			}
		}
		
		return false;
	}
	
	private Type testType(JavaMethod method){
		Annotation[] annotations = method.getAnnotations();
		

		for(Annotation annotation : annotations){
			if(org.junit.Test.class.getCanonicalName().equals(annotation.getType().getFullyQualifiedName())){
				return Type.JUNIT;
			}
			
			if(Manual.class.getCanonicalName().equals(annotation.getType().getFullyQualifiedName())){
				return Type.MANUAL;
			}
			
			if(Robot.class.getCanonicalName().equals(annotation.getType().getFullyQualifiedName())){
				return Type.ROBOT;
			}
		}
		
		throw new IllegalArgumentException("Method is not a test method");
	}
	
}
