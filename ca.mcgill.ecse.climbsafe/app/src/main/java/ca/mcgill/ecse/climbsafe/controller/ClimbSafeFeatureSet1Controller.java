package ca.mcgill.ecse.climbsafe.controller;

import java.sql.Date;

import ca.mcgill.ecse.climbsafe.persistence.ClimbSafePersistence;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Guide;
import ca.mcgill.ecse.climbsafe.model.Member;
import ca.mcgill.ecse.climbsafe.model.User;


public class ClimbSafeFeatureSet1Controller {

  public static void setup(Date startDate, int nrWeeks, int priceOfGuidePerWeek)
      throws InvalidInputException {}

  public static void deleteMember(String email) {}

  public static void deleteGuide(String email) {}

  // this method needs to be implemented only by teams with seven team members
  public static void deleteHotel(String name) {}

}

