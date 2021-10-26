package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.User;

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
	  boolean alreadyExist=false;
	  
	  for(var guide:climbSafe.getGuides()) {
		  if(guide.getEmail().equals(email)) {
			  alreadyExist=true;
		  }
	  }
	  
	  for(var member:climbSafe.getMembers()) {
		  if(member.getEmail().equals(email)) {
			  alreadyExist=true;
		  }
	  }
	  
	  if(email.equals("")) {
		  throw new InvalidInputException("Email cannot be empty");
	  }
	  
	  if(password.equals("")) {
		  throw new InvalidInputException("Password cannot be empty");
	  }
	  
	  if(name.equals("")) {
		  throw new InvalidInputException("Name cannot be empty");
	  }
	  
	  if(emergencyContact.equals("")) {
		  throw new InvalidInputException("Emergency contact cannot be empty");
	  }
	  
	  if(email.equals("admin@nmc.nt")) {
		  throw new InvalidInputException("Email cannot be admin@nmc.nt");
	  }
	  
	  for(int i=0; i<email.length(); i++) {
		  if(email.charAt(i)==' ') {
			  throw new InvalidInputException("Email must not contain any spaces");
		  }
	  }
	  
	
	  if(!(email.equals(name+"@email.com"))) {
		  throw new InvalidInputException("Invalid email");
	  }
	  
	  
	  if(alreadyExist==false) {
		 climbSafe.addGuide(email, password, name, emergencyContact);
	  }else {
		  throw new InvalidInputException("Email already linked to a member/guide account");
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
	  
	  var guide= (Guide) User.getWithEmail(email);
	  
	  if (newPassword.equals("")) {
			throw new InvalidInputException("The password cannot be empty");
		}
	  if (newName.equals("")) {
			throw new InvalidInputException("The name cannot be empty");
		}
	  if (newEmergencyContact.equals("")) {
			throw new InvalidInputException("The emergency contact cannot be empty");
		}
	    
	  
	  guide.setPassword(newPassword);
	  guide.setName(newName);
	  guide.setEmergencyContact(newEmergencyContact);
	  
	    
  }
  }
