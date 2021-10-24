package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;

public class ClimbSafeFeatureSet3Controller {
	
	public static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
	
	/**
	   * Feature: Register Guide
	   * 
	   * Registers a guide with information of email, password, name and emergency contact
	   * 
	   * @author Atreyi Srivastava
	   * 
	   */

  public static void registerGuide(String email, String password, String name,
      String emergencyContact) throws InvalidInputException {
	  try {
		  climbSafe.addGuide(email, password, name, emergencyContact);
	  } catch (RuntimeException e){
		  throw new InvalidInputException(e.getMessage());		  
	  }
  }
  

  /**
   * Feature: Update Guide
   * 
   * Update the exiting information of a guide with new information of password, name and emergency contact
   * 
   * @author Atreyi Srivastava
   * 
   */
  
  public static void updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) throws InvalidInputException {
	  try {		  
		  for(Guide guide: climbSafe.getGuides()) { 
			  var guideEmail = guide.getEmail();     
			  if( email== guideEmail) {   //validates the email 
				  guide.setPassword(newPassword);
				  guide.setName(newName);
				  guide.setEmergencyContact(newEmergencyContact);
				  		 
			  }
		  }
		  
		  
	  } catch (RuntimeException e) {
		  throw new InvalidInputException(e.getMessage());
	  }
  }
  }