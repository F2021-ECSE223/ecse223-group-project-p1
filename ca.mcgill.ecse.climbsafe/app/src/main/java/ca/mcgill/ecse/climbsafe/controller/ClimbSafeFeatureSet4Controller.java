package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;

public class ClimbSafeFeatureSet4Controller {

  public static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

  public static void addEquipment(String name, int weight, int pricePerWeek)
      throws InvalidInputException {}

  public static void updateEquipment(String oldName, String newName, int newWeight,
      int newPricePerWeek) throws InvalidInputException {

    var error = "";
    if (newWeight <= 0) {
      error += "The weight must be greater than zero";
    }
    if (newPricePerWeek < 0) {
      error += "The price per week must be greater than or equal to zero";
    }
    if (newName.isEmpty()) {
      error += "The name must not be empty";
    }
    if (!error.isEmpty()) {
      throw new InvalidInputException(error.trim());
    }

    try {



      for (Equipment equipment : climbSafe.getEquipment()) {
        if (equipment.getName().equals(oldName)) {

          equipment.setName(newName);
          equipment.setWeight(newWeight);
          equipment.setPricePerWeek(newPricePerWeek);

        }

      }


    } catch (RuntimeException e) {
      throw new InvalidInputException("Doesn't work");

    }



  }



}
