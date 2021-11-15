package ca.mcgill.ecse.climbsafe.controller;

import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;


public class ClimbSafeFeatureSet2Controller {

  public static void registerMember(String email, String password, String name,
      String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
      List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException {}

  public static void updateMember(String email, String newPassword, String newName,
      String newEmergencyContact, int newNrWeeks, boolean newGuideRequired,
      boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities)
      throws InvalidInputException {}


    for (int i = 0; i < newItemNames.size(); i++) {
      // newItemNames and newItemQuantities should be the same size or
      // an error will be thrown since each item booked has a size of
      // at least 0
      var bookableItem = BookableItem.getWithName(newItemNames.get(i));
      if (bookableItem == null) {
        throw new InvalidInputException("Requested item not found");
      }
      member.addBookedItem(newItemQuantities.get(i), climbSafe, bookableItem);
    }
    
    try {
      ClimbSafePersistence.save();
    } catch (RuntimeException e) {
      throw new InvalidInputException(e.getMessage());
    }
    
    
  }
}

