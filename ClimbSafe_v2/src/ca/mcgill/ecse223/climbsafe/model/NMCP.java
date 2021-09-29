package ca.mcgill.ecse223.climbsafe.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/


import java.util.*;
import java.sql.Date;

// line 1 "umpleCode-V3.ump"
// line 212 "umpleCode-V3.ump"
public class NMCP
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //NMCP Associations
  private List<Account> accounts;
  private Admin admin;
  private List<Equipment> equipments;
  private ClimbingSeason climbingSeason;
  private List<Hotel> hotels;
  private List<MountainGuide> mountainGuides;
  private List<Member> members;
  private List<Bundle> bundles;
  private List<ClimbingWeek> climbingWeeks;
  private List<UserRegistration> userRegistrations;
  private List<Assignment> assignments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public NMCP(Admin aAdmin, ClimbingSeason aClimbingSeason)
  {
    accounts = new ArrayList<Account>();
    if (aAdmin == null || aAdmin.getNMCP() != null)
    {
      throw new RuntimeException("Unable to create NMCP due to aAdmin. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    admin = aAdmin;
    equipments = new ArrayList<Equipment>();
    if (aClimbingSeason == null || aClimbingSeason.getNMCP() != null)
    {
      throw new RuntimeException("Unable to create NMCP due to aClimbingSeason. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    climbingSeason = aClimbingSeason;
    hotels = new ArrayList<Hotel>();
    mountainGuides = new ArrayList<MountainGuide>();
    members = new ArrayList<Member>();
    bundles = new ArrayList<Bundle>();
    climbingWeeks = new ArrayList<ClimbingWeek>();
    userRegistrations = new ArrayList<UserRegistration>();
    assignments = new ArrayList<Assignment>();
  }

  public NMCP(Account aAccountForAdmin, ClimbingSeason aClimbingSeasonForAdmin, Date aStartDateForClimbingSeason, Date aEndDateForClimbingSeason, Admin aSeasonDefinerForClimbingSeason)
  {
    accounts = new ArrayList<Account>();
    admin = new Admin(this, aAccountForAdmin, aClimbingSeasonForAdmin);
    equipments = new ArrayList<Equipment>();
    climbingSeason = new ClimbingSeason(aStartDateForClimbingSeason, aEndDateForClimbingSeason, aSeasonDefinerForClimbingSeason, this);
    hotels = new ArrayList<Hotel>();
    mountainGuides = new ArrayList<MountainGuide>();
    members = new ArrayList<Member>();
    bundles = new ArrayList<Bundle>();
    climbingWeeks = new ArrayList<ClimbingWeek>();
    userRegistrations = new ArrayList<UserRegistration>();
    assignments = new ArrayList<Assignment>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Account getAccount(int index)
  {
    Account aAccount = accounts.get(index);
    return aAccount;
  }

  public List<Account> getAccounts()
  {
    List<Account> newAccounts = Collections.unmodifiableList(accounts);
    return newAccounts;
  }

  public int numberOfAccounts()
  {
    int number = accounts.size();
    return number;
  }

  public boolean hasAccounts()
  {
    boolean has = accounts.size() > 0;
    return has;
  }

  public int indexOfAccount(Account aAccount)
  {
    int index = accounts.indexOf(aAccount);
    return index;
  }
  /* Code from template association_GetOne */
  public Admin getAdmin()
  {
    return admin;
  }
  /* Code from template association_GetMany */
  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipments.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipments()
  {
    List<Equipment> newEquipments = Collections.unmodifiableList(equipments);
    return newEquipments;
  }

  public int numberOfEquipments()
  {
    int number = equipments.size();
    return number;
  }

  public boolean hasEquipments()
  {
    boolean has = equipments.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipments.indexOf(aEquipment);
    return index;
  }
  /* Code from template association_GetOne */
  public ClimbingSeason getClimbingSeason()
  {
    return climbingSeason;
  }
  /* Code from template association_GetMany */
  public Hotel getHotel(int index)
  {
    Hotel aHotel = hotels.get(index);
    return aHotel;
  }

  public List<Hotel> getHotels()
  {
    List<Hotel> newHotels = Collections.unmodifiableList(hotels);
    return newHotels;
  }

  public int numberOfHotels()
  {
    int number = hotels.size();
    return number;
  }

  public boolean hasHotels()
  {
    boolean has = hotels.size() > 0;
    return has;
  }

  public int indexOfHotel(Hotel aHotel)
  {
    int index = hotels.indexOf(aHotel);
    return index;
  }
  /* Code from template association_GetMany */
  public MountainGuide getMountainGuide(int index)
  {
    MountainGuide aMountainGuide = mountainGuides.get(index);
    return aMountainGuide;
  }

  public List<MountainGuide> getMountainGuides()
  {
    List<MountainGuide> newMountainGuides = Collections.unmodifiableList(mountainGuides);
    return newMountainGuides;
  }

  public int numberOfMountainGuides()
  {
    int number = mountainGuides.size();
    return number;
  }

  public boolean hasMountainGuides()
  {
    boolean has = mountainGuides.size() > 0;
    return has;
  }

  public int indexOfMountainGuide(MountainGuide aMountainGuide)
  {
    int index = mountainGuides.indexOf(aMountainGuide);
    return index;
  }
  /* Code from template association_GetMany */
  public Member getMember(int index)
  {
    Member aMember = members.get(index);
    return aMember;
  }

  public List<Member> getMembers()
  {
    List<Member> newMembers = Collections.unmodifiableList(members);
    return newMembers;
  }

  public int numberOfMembers()
  {
    int number = members.size();
    return number;
  }

  public boolean hasMembers()
  {
    boolean has = members.size() > 0;
    return has;
  }

  public int indexOfMember(Member aMember)
  {
    int index = members.indexOf(aMember);
    return index;
  }
  /* Code from template association_GetMany */
  public Bundle getBundle(int index)
  {
    Bundle aBundle = bundles.get(index);
    return aBundle;
  }

  public List<Bundle> getBundles()
  {
    List<Bundle> newBundles = Collections.unmodifiableList(bundles);
    return newBundles;
  }

  public int numberOfBundles()
  {
    int number = bundles.size();
    return number;
  }

  public boolean hasBundles()
  {
    boolean has = bundles.size() > 0;
    return has;
  }

  public int indexOfBundle(Bundle aBundle)
  {
    int index = bundles.indexOf(aBundle);
    return index;
  }
  /* Code from template association_GetMany */
  public ClimbingWeek getClimbingWeek(int index)
  {
    ClimbingWeek aClimbingWeek = climbingWeeks.get(index);
    return aClimbingWeek;
  }

  public List<ClimbingWeek> getClimbingWeeks()
  {
    List<ClimbingWeek> newClimbingWeeks = Collections.unmodifiableList(climbingWeeks);
    return newClimbingWeeks;
  }

  public int numberOfClimbingWeeks()
  {
    int number = climbingWeeks.size();
    return number;
  }

  public boolean hasClimbingWeeks()
  {
    boolean has = climbingWeeks.size() > 0;
    return has;
  }

  public int indexOfClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    int index = climbingWeeks.indexOf(aClimbingWeek);
    return index;
  }
  /* Code from template association_GetMany */
  public UserRegistration getUserRegistration(int index)
  {
    UserRegistration aUserRegistration = userRegistrations.get(index);
    return aUserRegistration;
  }

  public List<UserRegistration> getUserRegistrations()
  {
    List<UserRegistration> newUserRegistrations = Collections.unmodifiableList(userRegistrations);
    return newUserRegistrations;
  }

  public int numberOfUserRegistrations()
  {
    int number = userRegistrations.size();
    return number;
  }

  public boolean hasUserRegistrations()
  {
    boolean has = userRegistrations.size() > 0;
    return has;
  }

  public int indexOfUserRegistration(UserRegistration aUserRegistration)
  {
    int index = userRegistrations.indexOf(aUserRegistration);
    return index;
  }
  /* Code from template association_GetMany */
  public Assignment getAssignment(int index)
  {
    Assignment aAssignment = assignments.get(index);
    return aAssignment;
  }

  public List<Assignment> getAssignments()
  {
    List<Assignment> newAssignments = Collections.unmodifiableList(assignments);
    return newAssignments;
  }

  public int numberOfAssignments()
  {
    int number = assignments.size();
    return number;
  }

  public boolean hasAssignments()
  {
    boolean has = assignments.size() > 0;
    return has;
  }

  public int indexOfAssignment(Assignment aAssignment)
  {
    int index = assignments.indexOf(aAssignment);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfAccountsValid()
  {
    boolean isValid = numberOfAccounts() >= minimumNumberOfAccounts();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAccounts()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Account addAccount(String aName, String aPassword, boolean aIsLogged, UserRegistration aUserRegistration, Admin aAdminUser)
  {
    Account aNewAccount = new Account(aName, aPassword, aIsLogged, aUserRegistration, aAdminUser, this);
    return aNewAccount;
  }

  public boolean addAccount(Account aAccount)
  {
    boolean wasAdded = false;
    if (accounts.contains(aAccount)) { return false; }
    NMCP existingNMCP = aAccount.getNMCP();
    boolean isNewNMCP = existingNMCP != null && !this.equals(existingNMCP);

    if (isNewNMCP && existingNMCP.numberOfAccounts() <= minimumNumberOfAccounts())
    {
      return wasAdded;
    }
    if (isNewNMCP)
    {
      aAccount.setNMCP(this);
    }
    else
    {
      accounts.add(aAccount);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAccount(Account aAccount)
  {
    boolean wasRemoved = false;
    //Unable to remove aAccount, as it must always have a nMCP
    if (this.equals(aAccount.getNMCP()))
    {
      return wasRemoved;
    }

    //nMCP already at minimum (1)
    if (numberOfAccounts() <= minimumNumberOfAccounts())
    {
      return wasRemoved;
    }

    accounts.remove(aAccount);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAccountAt(Account aAccount, int index)
  {  
    boolean wasAdded = false;
    if(addAccount(aAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAccounts()) { index = numberOfAccounts() - 1; }
      accounts.remove(aAccount);
      accounts.add(index, aAccount);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAccountAt(Account aAccount, int index)
  {
    boolean wasAdded = false;
    if(accounts.contains(aAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAccounts()) { index = numberOfAccounts() - 1; }
      accounts.remove(aAccount);
      accounts.add(index, aAccount);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAccountAt(aAccount, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEquipments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Equipment addEquipment(int aWeight, String aName, int aPrice)
  {
    return new Equipment(aWeight, aName, aPrice, this);
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipments.contains(aEquipment)) { return false; }
    NMCP existingNMCP = aEquipment.getNMCP();
    boolean isNewNMCP = existingNMCP != null && !this.equals(existingNMCP);
    if (isNewNMCP)
    {
      aEquipment.setNMCP(this);
    }
    else
    {
      equipments.add(aEquipment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    //Unable to remove aEquipment, as it must always have a nMCP
    if (!this.equals(aEquipment.getNMCP()))
    {
      equipments.remove(aEquipment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEquipmentAt(Equipment aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipments()) { index = numberOfEquipments() - 1; }
      equipments.remove(aEquipment);
      equipments.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipments.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipments()) { index = numberOfEquipments() - 1; }
      equipments.remove(aEquipment);
      equipments.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHotels()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Hotel addHotel(int aStarClass, String aName, String aAddress)
  {
    return new Hotel(aStarClass, aName, aAddress, this);
  }

  public boolean addHotel(Hotel aHotel)
  {
    boolean wasAdded = false;
    if (hotels.contains(aHotel)) { return false; }
    NMCP existingNMCP = aHotel.getNMCP();
    boolean isNewNMCP = existingNMCP != null && !this.equals(existingNMCP);
    if (isNewNMCP)
    {
      aHotel.setNMCP(this);
    }
    else
    {
      hotels.add(aHotel);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeHotel(Hotel aHotel)
  {
    boolean wasRemoved = false;
    //Unable to remove aHotel, as it must always have a nMCP
    if (!this.equals(aHotel.getNMCP()))
    {
      hotels.remove(aHotel);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHotelAt(Hotel aHotel, int index)
  {  
    boolean wasAdded = false;
    if(addHotel(aHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotels()) { index = numberOfHotels() - 1; }
      hotels.remove(aHotel);
      hotels.add(index, aHotel);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHotelAt(Hotel aHotel, int index)
  {
    boolean wasAdded = false;
    if(hotels.contains(aHotel))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHotels()) { index = numberOfHotels() - 1; }
      hotels.remove(aHotel);
      hotels.add(index, aHotel);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHotelAt(aHotel, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMountainGuides()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MountainGuide addMountainGuide(int aWeeklyCost, UserRegistration aRegistration)
  {
    return new MountainGuide(aWeeklyCost, this, aRegistration);
  }

  public boolean addMountainGuide(MountainGuide aMountainGuide)
  {
    boolean wasAdded = false;
    if (mountainGuides.contains(aMountainGuide)) { return false; }
    NMCP existingNMCP = aMountainGuide.getNMCP();
    boolean isNewNMCP = existingNMCP != null && !this.equals(existingNMCP);
    if (isNewNMCP)
    {
      aMountainGuide.setNMCP(this);
    }
    else
    {
      mountainGuides.add(aMountainGuide);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMountainGuide(MountainGuide aMountainGuide)
  {
    boolean wasRemoved = false;
    //Unable to remove aMountainGuide, as it must always have a nMCP
    if (!this.equals(aMountainGuide.getNMCP()))
    {
      mountainGuides.remove(aMountainGuide);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMountainGuideAt(MountainGuide aMountainGuide, int index)
  {  
    boolean wasAdded = false;
    if(addMountainGuide(aMountainGuide))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMountainGuides()) { index = numberOfMountainGuides() - 1; }
      mountainGuides.remove(aMountainGuide);
      mountainGuides.add(index, aMountainGuide);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMountainGuideAt(MountainGuide aMountainGuide, int index)
  {
    boolean wasAdded = false;
    if(mountainGuides.contains(aMountainGuide))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMountainGuides()) { index = numberOfMountainGuides() - 1; }
      mountainGuides.remove(aMountainGuide);
      mountainGuides.add(index, aMountainGuide);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMountainGuideAt(aMountainGuide, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMembers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Member addMember(boolean aIsStaying, boolean aWillHireAGuide, int aNumberOfWeeks, UserRegistration aRegistration)
  {
    return new Member(aIsStaying, aWillHireAGuide, aNumberOfWeeks, this, aRegistration);
  }

  public boolean addMember(Member aMember)
  {
    boolean wasAdded = false;
    if (members.contains(aMember)) { return false; }
    NMCP existingNMCP = aMember.getNMCP();
    boolean isNewNMCP = existingNMCP != null && !this.equals(existingNMCP);
    if (isNewNMCP)
    {
      aMember.setNMCP(this);
    }
    else
    {
      members.add(aMember);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMember(Member aMember)
  {
    boolean wasRemoved = false;
    //Unable to remove aMember, as it must always have a nMCP
    if (!this.equals(aMember.getNMCP()))
    {
      members.remove(aMember);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMemberAt(Member aMember, int index)
  {  
    boolean wasAdded = false;
    if(addMember(aMember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMembers()) { index = numberOfMembers() - 1; }
      members.remove(aMember);
      members.add(index, aMember);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMemberAt(Member aMember, int index)
  {
    boolean wasAdded = false;
    if(members.contains(aMember))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMembers()) { index = numberOfMembers() - 1; }
      members.remove(aMember);
      members.add(index, aMember);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMemberAt(aMember, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBundles()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Bundle addBundle(boolean aOnDiscount, String aName, int aPercentageDiscount, Equipment... allEquipments)
  {
    return new Bundle(aOnDiscount, aName, aPercentageDiscount, this, allEquipments);
  }

  public boolean addBundle(Bundle aBundle)
  {
    boolean wasAdded = false;
    if (bundles.contains(aBundle)) { return false; }
    NMCP existingNMCP = aBundle.getNMCP();
    boolean isNewNMCP = existingNMCP != null && !this.equals(existingNMCP);
    if (isNewNMCP)
    {
      aBundle.setNMCP(this);
    }
    else
    {
      bundles.add(aBundle);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBundle(Bundle aBundle)
  {
    boolean wasRemoved = false;
    //Unable to remove aBundle, as it must always have a nMCP
    if (!this.equals(aBundle.getNMCP()))
    {
      bundles.remove(aBundle);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBundleAt(Bundle aBundle, int index)
  {  
    boolean wasAdded = false;
    if(addBundle(aBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundles()) { index = numberOfBundles() - 1; }
      bundles.remove(aBundle);
      bundles.add(index, aBundle);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBundleAt(Bundle aBundle, int index)
  {
    boolean wasAdded = false;
    if(bundles.contains(aBundle))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBundles()) { index = numberOfBundles() - 1; }
      bundles.remove(aBundle);
      bundles.add(index, aBundle);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBundleAt(aBundle, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfClimbingWeeksValid()
  {
    boolean isValid = numberOfClimbingWeeks() >= minimumNumberOfClimbingWeeks();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfClimbingWeeks()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public ClimbingWeek addClimbingWeek(int aNumber, ClimbingSeason aClimbingSeason)
  {
    ClimbingWeek aNewClimbingWeek = new ClimbingWeek(aNumber, this, aClimbingSeason);
    return aNewClimbingWeek;
  }

  public boolean addClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasAdded = false;
    if (climbingWeeks.contains(aClimbingWeek)) { return false; }
    NMCP existingNMCP = aClimbingWeek.getNMCP();
    boolean isNewNMCP = existingNMCP != null && !this.equals(existingNMCP);

    if (isNewNMCP && existingNMCP.numberOfClimbingWeeks() <= minimumNumberOfClimbingWeeks())
    {
      return wasAdded;
    }
    if (isNewNMCP)
    {
      aClimbingWeek.setNMCP(this);
    }
    else
    {
      climbingWeeks.add(aClimbingWeek);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeClimbingWeek(ClimbingWeek aClimbingWeek)
  {
    boolean wasRemoved = false;
    //Unable to remove aClimbingWeek, as it must always have a nMCP
    if (this.equals(aClimbingWeek.getNMCP()))
    {
      return wasRemoved;
    }

    //nMCP already at minimum (1)
    if (numberOfClimbingWeeks() <= minimumNumberOfClimbingWeeks())
    {
      return wasRemoved;
    }

    climbingWeeks.remove(aClimbingWeek);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addClimbingWeekAt(ClimbingWeek aClimbingWeek, int index)
  {  
    boolean wasAdded = false;
    if(addClimbingWeek(aClimbingWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbingWeeks()) { index = numberOfClimbingWeeks() - 1; }
      climbingWeeks.remove(aClimbingWeek);
      climbingWeeks.add(index, aClimbingWeek);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveClimbingWeekAt(ClimbingWeek aClimbingWeek, int index)
  {
    boolean wasAdded = false;
    if(climbingWeeks.contains(aClimbingWeek))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClimbingWeeks()) { index = numberOfClimbingWeeks() - 1; }
      climbingWeeks.remove(aClimbingWeek);
      climbingWeeks.add(index, aClimbingWeek);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addClimbingWeekAt(aClimbingWeek, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUserRegistrations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public UserRegistration addUserRegistration(String aName, String aEmail, String aEmergencyPhone, boolean aIsRegistered, boolean aIsMember, MountainGuide aRegisteredGuide, Member aRegisteredMember, Account aAccount)
  {
    return new UserRegistration(aName, aEmail, aEmergencyPhone, aIsRegistered, aIsMember, aRegisteredGuide, aRegisteredMember, this, aAccount);
  }

  public boolean addUserRegistration(UserRegistration aUserRegistration)
  {
    boolean wasAdded = false;
    if (userRegistrations.contains(aUserRegistration)) { return false; }
    NMCP existingNMCP = aUserRegistration.getNMCP();
    boolean isNewNMCP = existingNMCP != null && !this.equals(existingNMCP);
    if (isNewNMCP)
    {
      aUserRegistration.setNMCP(this);
    }
    else
    {
      userRegistrations.add(aUserRegistration);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUserRegistration(UserRegistration aUserRegistration)
  {
    boolean wasRemoved = false;
    //Unable to remove aUserRegistration, as it must always have a nMCP
    if (!this.equals(aUserRegistration.getNMCP()))
    {
      userRegistrations.remove(aUserRegistration);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserRegistrationAt(UserRegistration aUserRegistration, int index)
  {  
    boolean wasAdded = false;
    if(addUserRegistration(aUserRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserRegistrations()) { index = numberOfUserRegistrations() - 1; }
      userRegistrations.remove(aUserRegistration);
      userRegistrations.add(index, aUserRegistration);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserRegistrationAt(UserRegistration aUserRegistration, int index)
  {
    boolean wasAdded = false;
    if(userRegistrations.contains(aUserRegistration))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserRegistrations()) { index = numberOfUserRegistrations() - 1; }
      userRegistrations.remove(aUserRegistration);
      userRegistrations.add(index, aUserRegistration);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserRegistrationAt(aUserRegistration, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Assignment addAssignment(Date aDate, Admin aAssigner, MountainGuide aGuide, Member aAssignedTo, ClimbingWeek... allClimbingWeeks)
  {
    return new Assignment(aDate, aAssigner, aGuide, aAssignedTo, this, allClimbingWeeks);
  }

  public boolean addAssignment(Assignment aAssignment)
  {
    boolean wasAdded = false;
    if (assignments.contains(aAssignment)) { return false; }
    NMCP existingNMCP = aAssignment.getNMCP();
    boolean isNewNMCP = existingNMCP != null && !this.equals(existingNMCP);
    if (isNewNMCP)
    {
      aAssignment.setNMCP(this);
    }
    else
    {
      assignments.add(aAssignment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAssignment(Assignment aAssignment)
  {
    boolean wasRemoved = false;
    //Unable to remove aAssignment, as it must always have a nMCP
    if (!this.equals(aAssignment.getNMCP()))
    {
      assignments.remove(aAssignment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssignmentAt(Assignment aAssignment, int index)
  {  
    boolean wasAdded = false;
    if(addAssignment(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssignmentAt(Assignment aAssignment, int index)
  {
    boolean wasAdded = false;
    if(assignments.contains(aAssignment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignments()) { index = numberOfAssignments() - 1; }
      assignments.remove(aAssignment);
      assignments.add(index, aAssignment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssignmentAt(aAssignment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (accounts.size() > 0)
    {
      Account aAccount = accounts.get(accounts.size() - 1);
      aAccount.delete();
      accounts.remove(aAccount);
    }
    
    Admin existingAdmin = admin;
    admin = null;
    if (existingAdmin != null)
    {
      existingAdmin.delete();
    }
    while (equipments.size() > 0)
    {
      Equipment aEquipment = equipments.get(equipments.size() - 1);
      aEquipment.delete();
      equipments.remove(aEquipment);
    }
    
    ClimbingSeason existingClimbingSeason = climbingSeason;
    climbingSeason = null;
    if (existingClimbingSeason != null)
    {
      existingClimbingSeason.delete();
    }
    while (hotels.size() > 0)
    {
      Hotel aHotel = hotels.get(hotels.size() - 1);
      aHotel.delete();
      hotels.remove(aHotel);
    }
    
    while (mountainGuides.size() > 0)
    {
      MountainGuide aMountainGuide = mountainGuides.get(mountainGuides.size() - 1);
      aMountainGuide.delete();
      mountainGuides.remove(aMountainGuide);
    }
    
    while (members.size() > 0)
    {
      Member aMember = members.get(members.size() - 1);
      aMember.delete();
      members.remove(aMember);
    }
    
    while (bundles.size() > 0)
    {
      Bundle aBundle = bundles.get(bundles.size() - 1);
      aBundle.delete();
      bundles.remove(aBundle);
    }
    
    while (climbingWeeks.size() > 0)
    {
      ClimbingWeek aClimbingWeek = climbingWeeks.get(climbingWeeks.size() - 1);
      aClimbingWeek.delete();
      climbingWeeks.remove(aClimbingWeek);
    }
    
    while (userRegistrations.size() > 0)
    {
      UserRegistration aUserRegistration = userRegistrations.get(userRegistrations.size() - 1);
      aUserRegistration.delete();
      userRegistrations.remove(aUserRegistration);
    }
    
    while (assignments.size() > 0)
    {
      Assignment aAssignment = assignments.get(assignments.size() - 1);
      aAssignment.delete();
      assignments.remove(aAssignment);
    }
    
  }

}