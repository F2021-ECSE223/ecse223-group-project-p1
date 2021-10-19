package ca.mcgill.ecse.climbsafe.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;

import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import io.cucumber.java.an.E;

public class P1StepDefinitions {
  
  private ClimbSafe climbSafe;
  private String error;
  private int errorCount;
	
  /**
   * @author Asma Gandour
   */
  @Given("the following ClimbSafe system exists: \\(p1)")
  public void the_following_climb_safe_system_exists_p1(io.cucumber.datatable.DataTable dataTable) {
    
    List<Object> columns = (dataTable.asLists(Object.class)).get(1);
    
    climbSafe.setStartDate((java.sql.Date) columns.get(0));
    climbSafe.setNrWeeks((int) columns.get(1));
    climbSafe.setPriceOfGuidePerWeek((int) columns.get(2));

    error = "";
    errorCount = 0;
    
  }

  @Given("the following pieces of equipment exist in the system: \\(p1)")
  public void the_following_pieces_of_equipment_exist_in_the_system_p1(
      io.cucumber.datatable.DataTable dataTable) {
	  var rows = dataTable.asMaps();
	  for (var row : rows) {
	      String name = row.get("name");
	      Integer weight =  Integer.parseInt(row.get("weight"));
	      Integer pricePerWeek = Integer.parseInt(row.get("pricePerWeek"));
	      climbSafe.addEquipment(name, weight, pricePerWeek);
	    }
	  
    throw new io.cucumber.java.PendingException();
  }

  @Given("the following equipment bundles exist in the system: \\(p1)")
  public void the_following_equipment_bundles_exist_in_the_system_p1(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }
	
  /**
   * @author Asma Gandour
   */
  @When("the administator attempts to update the piece of equipment in the system with name {string} to have name {string}, weight {string}, and price per week {string} \\(p1)")
  public void the_administator_attempts_to_update_the_piece_of_equipment_in_the_system_with_name_to_have_name_weight_and_price_per_week_p1(
      String string, String string2, String string3, String string4) {
    
    callController(() -> ClimbSafeFeatureSet4Controller.updateEquipment(string, string2,
        Integer.parseInt(string3), Integer.parseInt(string4)));
    
  }

  @Then("the number of pieces of equipment in the system shall be {string} \\(p1)")
  public void the_number_of_pieces_of_equipment_in_the_system_shall_be_p1(String string) {
	  
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the piece of equipment with name {string}, weight {string}, and price per week {string} shall not exist in the system \\(p1)")
  public void the_piece_of_equipment_with_name_weight_and_price_per_week_shall_not_exist_in_the_system_p1(
      String string, String string2, String string3) {
	  
	  String name = string;
	  Integer weight = Integer.parseInt(string2);
	  Integer pricePerWeek = Integer.parseInt(string3);
	  
	  for(Equipment pieceOfEquipment: climbSafe.getEquipment()) { //for each piece of equipment in the climbSafe system (for the admin)
		  if(pieceOfEquipment.getName() == name && pieceOfEquipment.getWeight() == weight && pieceOfEquipment.getPricePerWeek() == pricePerWeek)
		  {
			  fail("The piece of equipment <" + pieceOfEquipment.getName() + "> has not been deleted from the system"); //if everything matches, it means the object is not deleted.
		  }
	  }
    throw new io.cucumber.java.PendingException();
  }

  @Then("the piece of equipment with name {string}, weight {string}, and price per week {string} shall exist in the system \\(p1)")
  public void the_piece_of_equipment_with_name_weight_and_price_per_week_shall_exist_in_the_system_p1(
      String string, String string2, String string3) {
	  
	  String name = string;
	  Integer weight = Integer.parseInt(string2);
	  Integer pricePerWeek = Integer.parseInt(string3);
	  Boolean isEquipmentInTheSystem = false;
	  
	  for(Equipment pieceOfEquipment: climbSafe.getEquipment()) { //for each piece of equipment in the climbSafe system (for the admin)
		  if(pieceOfEquipment.getName() == name && pieceOfEquipment.getWeight() == weight && pieceOfEquipment.getPricePerWeek() == pricePerWeek)
		  {
			   isEquipmentInTheSystem = true;//if everything matches --> object in the system.
		  }
	  }
	  
	  assertEquals(true, isEquipmentInTheSystem);
	  assertEquals("", error);
	  assertEquals(0, errorCount);	  
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the following pieces of equipment shall exist in the system: \\(p1)")
  public void the_following_pieces_of_equipment_shall_exist_in_the_system_p1(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }

  @Then("the system shall raise the error {string} \\(p1)")
  public void the_system_shall_raise_the_error_p1(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the number of equipment bundles in the system shall be {string} \\(p1)")
  public void the_number_of_equipment_bundles_in_the_system_shall_be_p1(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the following equipment bundles shall exist in the system: \\(p1)")
  public void the_following_equipment_bundles_shall_exist_in_the_system_p1(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }
  
  private void callController(Executable executable) {
	    try {
	      executable.execute();
	    } catch (InvalidInputException e) {
	      error += e.getMessage();
	      errorCount += 1;
	    } catch (Throwable t) {
	      fail();
	    }
	  }

}
