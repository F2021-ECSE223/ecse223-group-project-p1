package ca.mcgill.ecse.climbsafe.controller;


import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;


public class ClimbSafeFeatureSet3Controller {

  public static void registerGuide(String email, String password, String name,
      String emergencyContact) throws InvalidInputException {}

  public static void updateGuide(String email, String newPassword, String newName,


      String newEmergencyContact) throws InvalidInputException {
      
      var guide= (Guide) User.getWithEmail(email);
      
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
        ClimbSafePersistence.save();
      } catch (RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
      }
      
        
  }
  }

