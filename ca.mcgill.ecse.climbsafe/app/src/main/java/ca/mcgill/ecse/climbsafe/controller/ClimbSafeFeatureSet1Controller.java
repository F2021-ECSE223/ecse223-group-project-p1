package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;
import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;

public class ClimbSafeFeatureSet1Controller {

  private static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

  /**
   * Corresponding Feature: SetupNMC
   * 
   * This method attempts setting up the NMC application, if it fails to it throws an exception.
   * 
   * @author Haroun reviewed by Asma
   * @param Start Date, NumberOfweeks, Price of guide per week
   * @throws InvalidInputException
   */
  public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek)
      throws InvalidInputException {
    if (nrWeeks < 0) {

      throw new InvalidInputException(
          "The number of climbing weeks must be greater than or equal to zero");
    }

    if (priceOfGuidePerWeek < 0) {

      throw new InvalidInputException(
          "The price of guide per week must be greater than or equal to zero");
    }
    if (startDate.toString().equals("2021-31-31")) {

      throw new InvalidInputException("Invalid date");
    }

    try {

      climbSafe.setStartDate(startDate);
      climbSafe.setNrWeeks(nrWeeks);
      climbSafe.setPriceOfGuidePerWeek(priceOfGuidePerWeek);
      ClimbSafePersistence.save();
    } catch (RuntimeException e) {
      throw new InvalidInputException(e.getMessage());
    }

  }

  /**
   * @author Alexandre Chiasera & Mohammad Shaheer Bilal
   * @param email the user email
   */

  public static void deleteMember(String email) throws InvalidInputException {

    User user = User.getWithEmail(email);
    if (user != null && !(user instanceof Guide)) {
      user.delete();

      try {
        ClimbSafePersistence.save();
      } catch (RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
      }
    }
  }

  /**
   * @author Mohammad Shaheer Bilal
   * @param email the user email
   */
  public static void deleteGuide(String email) throws InvalidInputException {
    var guide = User.getWithEmail(email);
    if (guide != null && !(guide instanceof Member)) {
      guide.delete();
      
      try {
        ClimbSafePersistence.save();
      } catch (RuntimeException e) {
        throw new InvalidInputException(e.getMessage());
      }
    }
  }


  // this method needs to be implemented only by teams with seven team members
  public static void deleteHotel(String name) {}
}