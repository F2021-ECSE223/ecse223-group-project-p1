package ca.mcgill.ecse.climbsafe.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.model.ClimbSafe;
import ca.mcgill.ecse.climbsafe.model.Equipment;
import io.cucumber.java.an.E;

public class P1StepDefinitions {
  
  private ClimbSafe climbSafe;
  private String error;
  private int errorCount;
  
  @Given("the following ClimbSafe system exists: \\(p1)")
  public void the_following_climb_safe_system_exists_p1(io.cucumber.datatable.DataTable dataTable) {
    
    List<Object> columns = (dataTable.asLists(Object.class)).get(1);

    assertEquals(((java.sql.Date) columns.get(0)), climbSafe.getStartDate());
    assertEquals(((int) columns.get(1)), climbSafe.getNrWeeks());
    assertEquals(((int) columns.get(2)), climbSafe.getPriceOfGuidePerWeek());

    error = "";
    errorCount = 0;
    
  }

  @Given("the following pieces of equipment exist in the system: \\(p1)")
  public void the_following_pieces_of_equipment_exist_in_the_system_p1(
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

  @When("the administator attempts to update the piece of equipment in the system with name {string} to have name {string}, weight {string}, and price per week {string} \\(p1)")
  public void the_administator_attempts_to_update_the_piece_of_equipment_in_the_system_with_name_to_have_name_weight_and_price_per_week_p1(
      String string, String string2, String string3, String string4) {
    
    
    try {
     
      ClimbSafeFeatureSet4Controller.updateEquipment(string, string2, Integer.parseInt(string3),
          Integer.parseInt(string4));
          
      
    } catch(InvalidInputException e) { // Is raised if weight or price <=0, if name length ==0
        error += e.getMessage();
        errorCount++;
    }
    
  }

  @Then("the number of pieces of equipment in the system shall be {string} \\(p1)")
  public void the_number_of_pieces_of_equipment_in_the_system_shall_be_p1(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the piece of equipment with name {string}, weight {string}, and price per week {string} shall not exist in the system \\(p1)")
  public void the_piece_of_equipment_with_name_weight_and_price_per_week_shall_not_exist_in_the_system_p1(
      String string, String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("the piece of equipment with name {string}, weight {string}, and price per week {string} shall exist in the system \\(p1)")
  public void the_piece_of_equipment_with_name_weight_and_price_per_week_shall_exist_in_the_system_p1(
      String string, String string2, String string3) {
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

}
