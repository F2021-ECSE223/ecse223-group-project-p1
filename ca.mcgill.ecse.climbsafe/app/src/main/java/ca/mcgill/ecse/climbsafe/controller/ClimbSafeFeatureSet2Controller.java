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

  private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();
  // regex to confirm email validity
  public static final Pattern REGEX_EMAIL =
      Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  /**
   * @author Alexandre Chiasera
   * @param email the user email
   * @param password the user password
   * @param name the user name
   * @param emergencyContact the user's emergency contact
   * @param nrWeeks the number of climbing weeks the user wishes to sign up for
   * @param guideRequired states whether the user will hire a guide or not
   * @param hotelRequired states whether the user will book a hotel or not
   * @param itemNames the items the user wishes to rent
   * @param itemQuantities the item quantities for the rented items respectively
   * 
   */
  public static void registerMember(String email, String password, String name,
      String emergencyContact, int nrWeeks, boolean guideRequired, boolean hotelRequired,
      List<String> itemNames, List<Integer> itemQuantities) throws InvalidInputException {
    boolean isNewMember = true;

    if (password.equals("")) {
      throw new InvalidInputException("The password cannot be empty");
    }

    if (name.equals("")) {
      throw new InvalidInputException("The name cannot be empty");
    }

    if (emergencyContact.equals("")) {
      throw new InvalidInputException("The emergency contact cannot be empty");
    }

    for (var itemName : itemNames) {
      var item = BookableItem.getWithName(itemName);
      if (item == null) {
        throw new InvalidInputException("Requested item not found");
      }
    }

    for (var guide : climbSafe.getGuides()) {
      var member = Guide.getWithEmail(email);
      if (guide.equals(member)) {
        throw new InvalidInputException("A guide with this email already exists");
      }
    }

    for (var member : climbSafe.getMembers()) {
      if (member.getEmail().equals(email)) {
        isNewMember = false;
        break;
      }
    }

    for (var guide : climbSafe.getGuides()) {
      if (guide.getEmail().equals(email)) {
        isNewMember = false;
        break;
      }
    }

    Matcher matcher = REGEX_EMAIL.matcher(email);
    if (!matcher.find()) {
      if (email.contains(" ")) {
        throw new InvalidInputException("The email must not contain any spaces");
      }
      throw new InvalidInputException("Invalid email");
    }

    if (password.equals("") || name.equals("") || emergencyContact.equals("")
        || (Integer) nrWeeks == null || (Boolean) guideRequired == null
        || (Boolean) hotelRequired == null) {
      throw new InvalidInputException("One or several omitted fields are required");
    }

    if (nrWeeks <= 0 || nrWeeks > climbSafe.getNrWeeks()) {
      throw new InvalidInputException(
          "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season");
    }

    if (email.contains("admin")) {
      throw new InvalidInputException("The email entered is not allowed for members");
    }

    if (isNewMember) {
      try {
        climbSafe.addMember(email, password, name, emergencyContact, nrWeeks, guideRequired,
            hotelRequired);
        var member = (Member) Member.getWithEmail(email);
        for (int i = 0; i < itemNames.size(); i++) { // newItemNames and newItemQuantities should be
                                                     // the same size // or an error will be thrown
                                                     // since each item booked has a
                                                     // size of at least 0
          var bookableItem = BookableItem.getWithName(itemNames.get(i));
          member.addBookedItem(itemQuantities.get(i), climbSafe, bookableItem);
        }
        ClimbSafePersistence.save();
      } catch (RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
      }


    } else {
      throw new InvalidInputException("A member with this email already exists");
    }
  }


  /**
   * @author Alexandre Chiasera
   * @param email the user email
   * @param newPassword the new password entered by the user
   * @param newName the new name entered by the user
   * @param newEmergencyContact the new emergency contact entered by the user
   * @param newNrWeeks the new number of weeks the user wishes to sign up for
   * @param newGuideRequired states whether the user requires a guide or not
   * @param newHotelRequired states whether the user requires to stay at a hotel or not
   * @param newItemNames the new items the user wishes to rent
   * @param newItemQuantities the quantities of the items newly added
   * 
   */
  public static void updateMember(String email, String newPassword, String newName,
      String newEmergencyContact, int newNrWeeks, boolean newGuideRequired,
      boolean newHotelRequired, List<String> newItemNames, List<Integer> newItemQuantities)
      throws InvalidInputException {
    var member = (Member) User.getWithEmail(email);

    if (member == null) {
      throw new InvalidInputException("Member not found");
    }
    member.setPassword(newPassword);
    member.setName(newName);
    member.setEmergencyContact(newEmergencyContact);
    member.setNrWeeks(newNrWeeks);
    member.setGuideRequired(newGuideRequired);
    member.setHotelRequired(newHotelRequired);

    for (int i = member.getBookedItems().size() - 1; i >= 0; i--) {
      // if we use for(item : member.getBookeItems()) our elements will be shifter to
      // avoid gaps, and hence elements won't be deleted
      member.getBookedItem(i).delete();
    }

    if (newPassword.equals("")) {
      throw new InvalidInputException("The password cannot be empty");
    }
    if (newName.equals("")) {
      throw new InvalidInputException("The name cannot be empty");
    }
    if (newEmergencyContact.equals("")) {
      throw new InvalidInputException("The emergency contact cannot be empty");
    }
    if (newNrWeeks <= 0 || newNrWeeks > climbSafe.getNrWeeks()) {
      throw new InvalidInputException(
          "The number of weeks must be greater than zero and less than or equal to the number of climbing weeks in the climbing season");
    }
    member.setPassword(newPassword);
    member.setName(newName);
    member.setEmergencyContact(newEmergencyContact);
    member.setNrWeeks(newNrWeeks);
    member.setGuideRequired(newGuideRequired);
    member.setHotelRequired(newHotelRequired);

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
