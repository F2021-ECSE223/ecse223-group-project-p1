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
    Equipment foundEquipment = null;
    
    if (newWeight <= 0) {
      error += "The weight must be greater than zero";
    }
    if (newPricePerWeek < 0) {
      error += "The price per week must be greater than or equal to zero";
    }
    if (newName.isEmpty()) {
      error += "The name must not be empty";
    }
    

    foundEquipment = (Equipment) Equipment.getWithName(oldName);

    
    
    if(foundEquipment == null) {
      error = "The piece of equipment does not exist";
    }
    
    
    if(foundEquipment != null) {
      if(oldName.equals(newName)) {
        error = "The piece of equipment already exists";
      }
    }
    if (!error.isEmpty()) {
      throw new InvalidInputException(error.trim());
    }
    

    
    try {

      foundEquipment.setName(newName);
      foundEquipment.setWeight(newWeight);
      foundEquipment.setPricePerWeek(newPricePerWeek);
      

    } catch (RuntimeException e) {
      
      throw new InvalidInputException(e.getMessage());
    }



  }



}
