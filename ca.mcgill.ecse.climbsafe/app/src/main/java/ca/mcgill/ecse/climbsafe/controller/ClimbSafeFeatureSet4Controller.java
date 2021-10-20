package ca.mcgill.ecse.climbsafe.controller;

import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import ca.mcgill.ecse.climbsafe.model.BookableItem;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import ca.mcgill.ecse.climbsafe.model.EquipmentBundle;

public class ClimbSafeFeatureSet4Controller {

  public static ClimbSafe climbSafe = ClimbSafeApplication.getClimbSafe();

  public static void addEquipment(String name, int weight, int pricePerWeek)
      throws InvalidInputException {}

  public static void updateEquipment(String oldName, String newName, int newWeight,
      int newPricePerWeek) throws InvalidInputException {

    var error = "";
    Equipment foundEquipment = null;
    
    if (newWeight <= 0) {
      error += "The weight must be greater than 0";
    }
    if (newPricePerWeek < 0) {
      error += "The price per week must be greater than or equal to 0";
    }
    if (newName.isEmpty()) {
      error += "The name must not be empty";
    }
    
    if( !newName.equals(oldName) && BookableItem.hasWithName(newName)) {
      
      if(BookableItem.getWithName(newName) instanceof EquipmentBundle) {
        error = "An equipment bundle with the same name already exists";
      }
      else {
        error = "The piece of equipment already exists";
      }
    }

    foundEquipment = (Equipment) Equipment.getWithName(oldName);

    
    
    if(foundEquipment == null) {
      error = "The piece of equipment does not exist";
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
