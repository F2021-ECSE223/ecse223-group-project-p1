package ca.mcgill.ecse.climbsafe.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;

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
  
  public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
  
  public static boolean validate(String emailStr) {
      Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
      return matcher.find();
}

  public static void registerGuide(String email, String password, String name,
      String emergencyContact) throws InvalidInputException {
    boolean alreadyExist = false;
    Member existingMember = null;
    Guide existingGuide = null;

    for (var guide : climbSafe.getGuides()) {
      if (guide.getEmail().equals(email)) {
        alreadyExist = true;
        existingGuide = guide;
      }
    }

    for (var member : climbSafe.getMembers()) {
      if (member.getEmail().equals(email)) {
        alreadyExist = true;
        existingMember = member;
      }
    }

    if (email.isEmpty()) {
      throw new InvalidInputException("Email cannot be empty");
    }

    if (password.isEmpty()) {
      throw new InvalidInputException("Password cannot be empty");
    }

    if (name.isEmpty()) {
      throw new InvalidInputException("Name cannot be empty");
    }

    if (emergencyContact.isEmpty()) {
      throw new InvalidInputException("Emergency contact cannot be empty");
    }

    if (email.equals("admin@nmc.nt")) {
      throw new InvalidInputException("Email cannot be admin@nmc.nt");
    }

    for (int i = 0; i < email.length(); i++) {
      if (email.charAt(i) == ' ') {
        throw new InvalidInputException("Email must not contain any spaces");
      }
    }


    if (!validate(email)) {
      throw new InvalidInputException("Invalid email");
    }


    if (alreadyExist == false) {
      climbSafe.addGuide(email, password, name, emergencyContact);

      try {
        ClimbSafePersistence.save(climbSafe);
      } catch (RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
      }
    } else if (existingGuide != null) {
      throw new InvalidInputException("Email already linked to a guide account");
    } else {
      throw new InvalidInputException("Email already linked to a member account");
    }

  }


  /**
   * Feature: Update Guide
   * 
   * Update the exiting information of a guide with new information of password, name and emergency
   * contact
   * 
   * @author Atreyi Srivastava
   * 
   */

  public static void updateGuide(String email, String newPassword, String newName,
      String newEmergencyContact) throws InvalidInputException {

    var guide = (Guide) User.getWithEmail(email);

    if (newPassword.isEmpty()) {
      throw new InvalidInputException("Password cannot be empty");
    }
    if (newName.isEmpty()) {
      throw new InvalidInputException("Name cannot be empty");
    }
    if (newEmergencyContact.isEmpty()) {
      throw new InvalidInputException("Emergency contact cannot be empty");
    }


    guide.setPassword(newPassword);
    guide.setName(newName);
    guide.setEmergencyContact(newEmergencyContact);

    try {
      ClimbSafePersistence.save(climbSafe);
    } catch (RuntimeException e) {
      throw new InvalidInputException(e.getMessage());
    }


  }
}
