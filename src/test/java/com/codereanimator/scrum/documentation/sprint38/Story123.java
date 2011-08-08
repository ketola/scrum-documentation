package com.codereanimator.scrum.documentation.sprint38;

import junit.framework.Assert;

import org.junit.Test;

import com.codereanimator.scrum.documentation.annotation.Manual;
import com.codereanimator.scrum.documentation.annotation.Robot;


/**
 * As a User, I want to log in to the application so that I can access my personal information.
 * 
 * @author Sauli
 *
 */
public class Story123 {
	
	/**
	 * User enters wrong credentials and sees a message 
	 * "Name and password combination does not match, please try again"
	 */
	@Test
	public void testUserEntersWrongCredentials(){
		// junit java code here
		Assert.assertTrue(true);
	}
	
	/**
	 * User enters wrong credentials 3 times and sees a message 
	 * "The account is locked. Contact administrator".
	 */
	@Manual
	public void testUserEntersWrongCredentials3Times(){
		// manual test
	}
	
	/**
	 * User enters correct credentials and is forwarded to her personal details page.
	 */
	@Robot
	public void testUserEntersCorrectCredentials(){
		// tested with automated ui-test. You could add code for executing the actual test.
	}
	
	/**
	 * Both fields, user name and password are required.
	 * The user name field accepts only letter a-z and numbers 0-9
	 */
	@Test
	public void testUserFormValidations(){
		// junit test java code here
		Assert.assertTrue(true);
	}
	
}
