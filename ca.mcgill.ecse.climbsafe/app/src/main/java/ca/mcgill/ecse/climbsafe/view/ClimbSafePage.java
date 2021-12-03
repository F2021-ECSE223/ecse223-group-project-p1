package ca.mcgill.ecse.climbsafe.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import ca.mcgill.ecse.climbsafe.controller.AssignmentController;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet2Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet3Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet4Controller;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet6Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.controller.TOAssignment;
import ca.mcgill.ecse.climbsafe.controller.TOGuide;
import ca.mcgill.ecse.climbsafe.controller.TOMember;
import ca.mcgill.ecse.climbsafe.controller.TOBookableItem;
import ca.mcgill.ecse.climbsafe.controller.TOEquipment;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.border.BevelBorder;


public class ClimbSafePage extends JFrame {
  private static final long serialVersionUID = -4426310869335015542L;
  // tabs
  JTabbedPane tabbedPane = new JTabbedPane();
  private String error = "";

  // All text fields are listed here
  private JLabel currentErrorMessage;
  private boolean successStatus;

  private JTextField newMemberWeekTextField;
  private JTextField newMemberEmergencyNumberTextField;
  private JTextField newMemberNameTextField;
  private JTextField newMemberPasswordTextField;
  private JTextField memberItemQuantityTextField;
  private JTextField memberRegisterEmailTextField;
  private JTextField memberRegisterWeekTextField;
  private JTextField memberRegisterContactTextField;
  private JTextField memberRegisterPasswordField;
  private JTextField memberRegisterNameTextField;
  private JTextField guideRegisterPasswordTextField;
  private JTextField guideRegisterEmailTextField;
  private JTextField guideRegisterNameTextField;
  private JTextField guideRegisterEmergencyContactTextField;
  private JTextField setUpStartAnchorDateTextField;
  private JTextField setUpNumWeeksTextField;
  private JDatePickerImpl NMCDatePicker;
  private JTextField setUpPriceGuidePerWeekTextField;
  private JTextField newGuidePasswordTextField;
  private JTextField newGuideEmergencyNumberTextField;
  private JTextField newGuideNameTextField;
  private JTextField nameEquipmentToAddTextField;
  private JTextField weightEquipmentToAddTextField;
  private JTextField pricePerWeekEquipmentToAddTextField;
  private JTextField newEquipmentNameTextField;
  private JTextField newEquipmentWeightTextField;
  private JTextField newEquipmentPricePerWeekTextField;
  private JTextField authorizationCodeTextField;
  private JTextField quantityRegisterMemberField;

  // FIRST TAB BELOW (except textFields) -- UPDATE PAGE
  private JLabel errorMessageTab1 = new JLabel(); // element for error message
  private JTabbedPane tabbedPane_1;
  private JPanel panel;
  private JLabel selectMemberToUpdateLabel;
  private JComboBox selectMemberToUpdateComboBox;
  private JLabel newMemberWeekLabel;
  private JLabel newMemberEmergencyNumberLabel;
  private JLabel newMemberPasswordLabel;
  private JLabel newMemberNameLabel;
  private JLabel checkIfAppliesLabel;
  private JRadioButton memberGuideRequiredRdBtn;
  private JRadioButton memberHotelRequiredRdBtn;
  private JLabel selectNewItemsComboBoxUpdatePage;
  private JComboBox selectNewItemsComboBox;
  private JLabel memberItemQuantityLabel;
  private JButton memberAddItemButton;
  private JButton updateMemberButton;
  private JLabel updateMemberInformationLabel;
  private JLabel updateGuideInformationLabel;
  private JLabel selectGuideToUpdateLabel;
  private JLabel newGuidePasswordLabel;
  private JComboBox selectGuideToUpdateComboBox;
  private JLabel newGuideEmergencyNumberLabel;
  private JLabel newGuideNameLabel;
  private JButton updateGuideButton;
  private JTable updateMemberEquipmentTable;
  private JLabel background;

  // SECOND TAB BELOW (except text fields) -- REGISTER PAGE
  private JLabel errorMessageTab2 = new JLabel(); // element for error message
  private JPanel panel_1;
  private JLabel memberRegisterEmailLabel;
  private JLabel memberRegisterPasswordLabel;
  private JLabel memberRegisterNameLabel;
  private JLabel memberRegisterEmergencyContactLabel;
  private JRadioButton memberRegisterGuideRequiredRdBtn;
  private JRadioButton memberRegisterHotelRequiredRdBtn;
  private JLabel guideRegisterEmailLabel;
  private JLabel guideRegisterPasswordLabel;
  private JLabel guideRegisterEmergencyContactLabel;
  private JLabel guideRegisterNameLabel;
  private JButton registerMemberBtn;
  private JButton registerGuideBtn;
  private JLabel registerAsMemberLabel;
  private JLabel registerAsGuideLabel;
  private JComboBox selectItemsRegisterMemberComboBox;
  private JLabel registerMemberRequiredLabel;
  private JLabel registerMemberPasswordLabel;
  private JLabel backgroundRegisterPage;
  private JButton addItemRegisterMemberButton;
  private JLabel selectItemRegisterMemberList;
  private JLabel quantityRegisterMemberLabel;
  private JTable itemRegisterMemberTable;

  // THIRD TAB BELOW -- SET UP NMC INFO
  private JLabel errorMessageTab3 = new JLabel(); // element for error message
  private JPanel panel_2;
  private JLabel setUpStartdateLabel;
  private JLabel setUpNumWeeksLabel;
  private JLabel setUpPriceGuidePerWeekLabel;
  private JButton confirmSetUpButton;
  private JLabel setUpNMCLabel;
  private JLabel backgroundSetUpPage;
  private ImageIcon backgroundImageSetUpPage;

  // FOURTH TAB BELOW -- REMOVE USER OR EQUIPMENT
  private JLabel errorMessageTab4 = new JLabel(); // element for error message
  private JPanel panel_3;
  private JLabel removeUserLabel;
  private JLabel selectGuideRemovePageLabel;
  private JComboBox selectGuideRemovePageComboBox;
  private JButton deleteGuideButton;
  private JLabel selectMemberRemovePageLabel;
  private JComboBox selectMemberRemovePageComboBox;
  private JButton deleteMemberButton;
  private JLabel selectEquipmentRemovePageLabel;
  private JComboBox selectEquipmentRemovePageComboBox;
  private JButton deleteEquipmentButton;
  private JLabel removeEquipmentLabel;
  private JLabel backgroundRemovePage;

  // FIFTH TAB BELOW -- ADD OR UPDATE EQUIPMENT
  private JLabel errorMessageTab5 = new JLabel(); // element for error message
  private JPanel panel_4;
  private JLabel nameEquipmentToAddLabel;
  private JLabel weightEquipmentToAddLabel;
  private JLabel pricePerWeekEquipmentToAddLabel;
  private JLabel addEquipmentLabel;
  private JButton addEquipmentButton;
  private JLabel updateEquipmentLabel;
  private JLabel newEquipmentNameLabel;
  private JLabel newEquipmentWeightLabel;
  private JLabel newEquipmentPricePerWeekLabel;
  private JButton updateEquipmentButton;
  private JLabel selectEquipmentToUpdateLabel;
  private JComboBox selectEquipmentToUpdateComboBox;
  private JLabel backgroundEquipmentPage;

  // SIXTH TAB BELOW -- ASSIGNMENT AND PAYMENT
  private JLabel errorMessageTab6 = new JLabel(); // element for error message
  private JPanel panel_5;
  private JLabel selectMemberForPayLabel;
  private JComboBox selectMemberForPayComboBox;
  private JButton payForTheTripButton;
  private JButton finishTheTripButton;
  private JButton cancelTheTripButton;
  private JLabel autorizationCodeLabel;
  private JLabel payFinishOrCancelTripLabel;
  private JLabel initiateAssignmentsLabel;
  private JButton initiateAssignmentsForAllMembersButton;
  private JLabel selectStartWeekNumberLabel;
  private JComboBox selectStartWeekNumberComboBox;
  private JLabel startTripsLabel;
  private JButton startAllTripsForThisWeekLabel;
  private JLabel backgroundAssignmentPage;

  // SEVENTH TAB BELOW -- VIEW ASSIGNMENTS
  private JLabel errorMessageTab7 = new JLabel(); // element for error message
  private JPanel panel_6;
  private JTable viewAssignmentTable;
  private JLabel viewAssignmentLabel;
  private JLabel backgroundViewtPage;

  private static final Map<String, String> DATE_PROPS =
      Map.of("text.today", "Today", "text.month", "Month", "text.year", "Year");



  public ClimbSafePage() {
    initComponents();
    refreshData();
    refreshUpdateMemberItem();
    refreshRegisterMemberItem();
  }

  /**
   * This method creates the UI design of the application
   * by adding Java Swing components to each panel.
   * 
   * @author 
   * reviewed by Asma Gandour
   */
  private void initComponents() {


    errorMessageTab1
        .setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessageTab1.getFont().getSize()));
    setResizable(false);

    errorMessageTab2
        .setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessageTab2.getFont().getSize()));
    setResizable(false);

    errorMessageTab3
        .setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessageTab3.getFont().getSize()));
    setResizable(false);

    errorMessageTab4
        .setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessageTab4.getFont().getSize()));
    setResizable(false);

    errorMessageTab5
        .setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessageTab5.getFont().getSize()));
    setResizable(false);

    errorMessageTab6
        .setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessageTab6.getFont().getSize()));
    setResizable(false);

    errorMessageTab7
        .setFont(new Font(Font.SANS_SERIF, Font.BOLD, errorMessageTab7.getFont().getSize()));
    setResizable(false);

    currentErrorMessage = errorMessageTab1;

    getContentPane().setLayout(null);



    tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
    tabbedPane_1.setBounds(0, 0, 1211, 537);
    getContentPane().add(tabbedPane_1);

    panel = new JPanel();
    tabbedPane_1.addTab("Update user information", null, panel, null);
    panel.setLayout(null);



    JSeparator separator = new JSeparator();
    separator.setBounds(10, 205, 1176, 2);
    panel.add(separator);

    errorMessageTab1 = new JLabel("");
    errorMessageTab1.setForeground(Color.RED);
    errorMessageTab1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
    errorMessageTab1.setBounds(38, 39, 601, 24);
    panel.add(errorMessageTab1);


    selectMemberToUpdateLabel = new JLabel("Select member: ");
    selectMemberToUpdateLabel.setForeground(Color.WHITE);
    selectMemberToUpdateLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    selectMemberToUpdateLabel.setBounds(72, 273, 132, 27);
    panel.add(selectMemberToUpdateLabel);

    selectMemberToUpdateComboBox = new JComboBox();
    selectMemberToUpdateComboBox.setEditable(false);
    selectMemberToUpdateComboBox.setBackground(Color.WHITE);

    selectMemberToUpdateComboBox.setBounds(210, 276, 189, 27);
    panel.add(selectMemberToUpdateComboBox);

    newMemberWeekTextField = new JTextField();
    newMemberWeekTextField.setBounds(210, 328, 189, 27);
    panel.add(newMemberWeekTextField);
    newMemberWeekTextField.setColumns(10);

    newMemberWeekLabel = new JLabel("New number of weeks: ");
    newMemberWeekLabel.setForeground(Color.WHITE);
    newMemberWeekLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    newMemberWeekLabel.setBounds(10, 325, 194, 27);
    panel.add(newMemberWeekLabel);

    newMemberEmergencyNumberLabel = new JLabel("New emergency number:");
    newMemberEmergencyNumberLabel.setForeground(Color.WHITE);
    newMemberEmergencyNumberLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    newMemberEmergencyNumberLabel.setBounds(409, 323, 211, 30);
    panel.add(newMemberEmergencyNumberLabel);

    newMemberEmergencyNumberTextField = new JTextField();
    newMemberEmergencyNumberTextField.setColumns(10);
    newMemberEmergencyNumberTextField.setBounds(623, 328, 189, 27);
    panel.add(newMemberEmergencyNumberTextField);

    newMemberPasswordLabel = new JLabel("New password: ");
    newMemberPasswordLabel.setForeground(Color.WHITE);
    newMemberPasswordLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    newMemberPasswordLabel.setBounds(842, 273, 138, 27);
    panel.add(newMemberPasswordLabel);

    newMemberNameTextField = new JTextField();
    newMemberNameTextField.setColumns(10);
    newMemberNameTextField.setBounds(623, 276, 189, 27);
    panel.add(newMemberNameTextField);

    newMemberNameLabel = new JLabel("New name: ");
    newMemberNameLabel.setForeground(Color.WHITE);
    newMemberNameLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    newMemberNameLabel.setBounds(494, 273, 102, 27);
    panel.add(newMemberNameLabel);

    checkIfAppliesLabel = new JLabel("Check if applies:");
    checkIfAppliesLabel.setForeground(Color.WHITE);
    checkIfAppliesLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    checkIfAppliesLabel.setBounds(842, 322, 138, 27);
    panel.add(checkIfAppliesLabel);

    newMemberPasswordTextField = new JTextField();
    newMemberPasswordTextField.setColumns(10);
    newMemberPasswordTextField.setBounds(984, 273, 189, 27);
    panel.add(newMemberPasswordTextField);

    memberGuideRequiredRdBtn = new JRadioButton("Guide Required");
    memberGuideRequiredRdBtn.setForeground(SystemColor.textHighlightText);
    memberGuideRequiredRdBtn.setContentAreaFilled(false);
    memberGuideRequiredRdBtn.setOpaque(false);
    memberGuideRequiredRdBtn.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 17));
    memberGuideRequiredRdBtn.setBounds(986, 327, 162, 23);
    panel.add(memberGuideRequiredRdBtn);

    memberHotelRequiredRdBtn = new JRadioButton("Hotel Required");
    memberHotelRequiredRdBtn.setForeground(SystemColor.textHighlightText);
    memberHotelRequiredRdBtn.setContentAreaFilled(false);
    memberHotelRequiredRdBtn.setOpaque(false);
    memberHotelRequiredRdBtn.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 17));
    memberHotelRequiredRdBtn.setBounds(986, 365, 171, 23);
    panel.add(memberHotelRequiredRdBtn);

    selectNewItemsComboBoxUpdatePage = new JLabel("Select new items:");
    selectNewItemsComboBoxUpdatePage.setForeground(Color.WHITE);
    selectNewItemsComboBoxUpdatePage.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    selectNewItemsComboBoxUpdatePage.setBounds(51, 389, 147, 27);
    panel.add(selectNewItemsComboBoxUpdatePage);

    selectNewItemsComboBox = new JComboBox();
    selectNewItemsComboBox.setEditable(false);
    selectNewItemsComboBox.setBounds(210, 389, 189, 27);
    panel.add(selectNewItemsComboBox);

    memberItemQuantityLabel = new JLabel("Item quantity: ");
    memberItemQuantityLabel.setForeground(Color.WHITE);
    memberItemQuantityLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    memberItemQuantityLabel.setBounds(72, 430, 124, 27);
    panel.add(memberItemQuantityLabel);

    memberItemQuantityTextField = new JTextField();
    memberItemQuantityTextField.setColumns(10);
    memberItemQuantityTextField.setBounds(210, 433, 189, 27);
    panel.add(memberItemQuantityTextField);

    memberAddItemButton = new JButton("Add item");
    memberAddItemButton.setContentAreaFilled(false);
    memberAddItemButton.setOpaque(true);
    memberAddItemButton.setBackground(SystemColor.activeCaption);
    memberAddItemButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
    memberAddItemButton.setBounds(275, 471, 124, 27);
    memberAddItemButton.setContentAreaFilled(false);
    memberAddItemButton.setOpaque(true);
    panel.add(memberAddItemButton);

    updateMemberButton = new JButton("UPDATE MEMBER");
    updateMemberButton.setBackground(new Color(173, 216, 230));
    updateMemberButton.setContentAreaFilled(false);
    updateMemberButton.setOpaque(true);
    updateMemberButton.setForeground(SystemColor.desktop);
    updateMemberButton.setFont(new Font("Myanmar Text", Font.BOLD, 17));
    updateMemberButton.setBounds(984, 420, 189, 62);
    panel.add(updateMemberButton);

    updateMemberInformationLabel = new JLabel("UPDATE MEMBER INFORMATION");
    updateMemberInformationLabel.setForeground(Color.WHITE);
    updateMemberInformationLabel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 23));
    updateMemberInformationLabel.setBounds(411, 218, 401, 27);
    panel.add(updateMemberInformationLabel);

    updateGuideInformationLabel = new JLabel("UPDATE GUIDE INFORMATION");
    updateGuideInformationLabel.setForeground(Color.WHITE);
    updateGuideInformationLabel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 23));
    updateGuideInformationLabel.setBounds(418, 11, 401, 27);
    panel.add(updateGuideInformationLabel);

    selectGuideToUpdateLabel = new JLabel("Select guide: ");
    selectGuideToUpdateLabel.setForeground(Color.WHITE);
    selectGuideToUpdateLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    selectGuideToUpdateLabel.setBounds(131, 71, 110, 27);
    panel.add(selectGuideToUpdateLabel);

    newGuidePasswordLabel = new JLabel("New password: ");
    newGuidePasswordLabel.setForeground(Color.WHITE);
    newGuidePasswordLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    newGuidePasswordLabel.setBounds(109, 129, 132, 27);
    panel.add(newGuidePasswordLabel);

    selectGuideToUpdateComboBox = new JComboBox();
    selectGuideToUpdateComboBox.setEditable(false);
    selectGuideToUpdateComboBox.setBounds(247, 74, 189, 27);
    panel.add(selectGuideToUpdateComboBox);

    newGuidePasswordTextField = new JTextField();
    newGuidePasswordTextField.setColumns(10);
    newGuidePasswordTextField.setBounds(247, 132, 189, 27);
    panel.add(newGuidePasswordTextField);

    newGuideEmergencyNumberLabel = new JLabel("New emergency number:");
    newGuideEmergencyNumberLabel.setForeground(Color.WHITE);
    newGuideEmergencyNumberLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    newGuideEmergencyNumberLabel.setBounds(534, 129, 211, 30);
    panel.add(newGuideEmergencyNumberLabel);

    newGuideEmergencyNumberTextField = new JTextField();
    newGuideEmergencyNumberTextField.setColumns(10);
    newGuideEmergencyNumberTextField.setBounds(744, 132, 189, 27);
    panel.add(newGuideEmergencyNumberTextField);

    newGuideNameTextField = new JTextField();
    newGuideNameTextField.setColumns(10);
    newGuideNameTextField.setBounds(744, 71, 189, 27);
    panel.add(newGuideNameTextField);

    newGuideNameLabel = new JLabel("New name: ");
    newGuideNameLabel.setForeground(Color.WHITE);
    newGuideNameLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    newGuideNameLabel.setBounds(632, 68, 102, 27);
    panel.add(newGuideNameLabel);

    updateGuideButton = new JButton("UPDATE GUIDE");
    updateGuideButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {}
    });
    updateGuideButton.setContentAreaFilled(false);
    updateGuideButton.setOpaque(true);
    updateGuideButton.setForeground(Color.BLACK);
    updateGuideButton.setFont(new Font("Myanmar Text", Font.BOLD, 17));
    updateGuideButton.setBackground(new Color(173, 216, 230));
    updateGuideButton.setBounds(984, 114, 189, 62);
    panel.add(updateGuideButton);


    updateMemberEquipmentTable = new JTable(
        new DefaultTableModel(new Object[][] {}, new String[] {"Added items", "Quantities"})) {
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    updateMemberEquipmentTable.setBackground(SystemColor.window);
    updateMemberEquipmentTable.setFont(new Font("Baskerville", Font.PLAIN, 12));
    updateMemberEquipmentTable.setFillsViewportHeight(true);
    updateMemberEquipmentTable.setBorder(null);
    updateMemberEquipmentTable.setShowGrid(false);
    updateMemberEquipmentTable.setBounds(494, 424, 318, 58);
    JScrollPane memberItemContainer = new JScrollPane(updateMemberEquipmentTable);
    var updateMemberEquipmentTableOffset = 15;
    memberItemContainer.setBounds(457, 382, 355, 116);
    panel.add(memberItemContainer);


    background = new JLabel("");
    background.setBounds(0, 0, 1206, 509);
    ImageIcon backgroundImage = new ImageIcon(this.getClass().getResource("/backgroundImage.png"));
    background.setIcon(backgroundImage);
    panel.add(background);



    panel_1 = new JPanel();
    tabbedPane_1.addTab("Register page", null, panel_1, null);
    panel_1.setLayout(null);

    errorMessageTab2 = new JLabel("");
    errorMessageTab2.setForeground(Color.RED);
    errorMessageTab2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
    errorMessageTab2.setBounds(38, 39, 601, 24);
    panel_1.add(errorMessageTab2);

    memberRegisterEmailLabel = new JLabel("Email:");
    memberRegisterEmailLabel.setForeground(Color.WHITE);
    memberRegisterEmailLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    memberRegisterEmailLabel.setBounds(123, 64, 55, 27);
    panel_1.add(memberRegisterEmailLabel);

    memberRegisterEmailTextField = new JTextField();
    memberRegisterEmailTextField.setColumns(10);
    memberRegisterEmailTextField.setBounds(188, 67, 199, 27);
    panel_1.add(memberRegisterEmailTextField);

    memberRegisterPasswordLabel = new JLabel("Number of weeks:");
    memberRegisterPasswordLabel.setForeground(Color.WHITE);
    memberRegisterPasswordLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    memberRegisterPasswordLabel.setBounds(25, 111, 153, 27);
    panel_1.add(memberRegisterPasswordLabel);

    memberRegisterWeekTextField = new JTextField();
    memberRegisterWeekTextField.setColumns(10);
    memberRegisterWeekTextField.setBounds(188, 114, 199, 27);
    panel_1.add(memberRegisterWeekTextField);

    memberRegisterNameLabel = new JLabel("Name: ");
    memberRegisterNameLabel.setForeground(Color.WHITE);
    memberRegisterNameLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    memberRegisterNameLabel.setBounds(503, 64, 62, 27);
    panel_1.add(memberRegisterNameLabel);

    memberRegisterContactTextField = new JTextField();
    memberRegisterContactTextField.setColumns(10);
    memberRegisterContactTextField.setBounds(584, 114, 199, 27);
    panel_1.add(memberRegisterContactTextField);

    memberRegisterEmergencyContactLabel = new JLabel("Emergency contact: ");
    memberRegisterEmergencyContactLabel.setForeground(Color.WHITE);
    memberRegisterEmergencyContactLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    memberRegisterEmergencyContactLabel.setBounds(411, 114, 163, 27);
    panel_1.add(memberRegisterEmergencyContactLabel);

    memberRegisterNameTextField = new JTextField();
    memberRegisterNameTextField.setColumns(10);
    memberRegisterNameTextField.setBounds(584, 67, 199, 27);
    panel_1.add(memberRegisterNameTextField);

    memberRegisterGuideRequiredRdBtn = new JRadioButton("Guide Required");
    memberRegisterGuideRequiredRdBtn.setOpaque(false);
    memberRegisterGuideRequiredRdBtn.setForeground(Color.WHITE);
    memberRegisterGuideRequiredRdBtn.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 17));
    memberRegisterGuideRequiredRdBtn.setContentAreaFilled(false);
    memberRegisterGuideRequiredRdBtn.setBounds(955, 118, 162, 23);
    panel_1.add(memberRegisterGuideRequiredRdBtn);

    memberRegisterHotelRequiredRdBtn = new JRadioButton("Hotel Required");
    memberRegisterHotelRequiredRdBtn.setOpaque(false);
    memberRegisterHotelRequiredRdBtn.setForeground(Color.WHITE);
    memberRegisterHotelRequiredRdBtn.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 17));
    memberRegisterHotelRequiredRdBtn.setContentAreaFilled(false);
    memberRegisterHotelRequiredRdBtn.setBounds(955, 162, 153, 23);
    panel_1.add(memberRegisterHotelRequiredRdBtn);

    guideRegisterEmailLabel = new JLabel("Email:");
    guideRegisterEmailLabel.setForeground(Color.WHITE);
    guideRegisterEmailLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    guideRegisterEmailLabel.setBounds(116, 329, 62, 27);
    panel_1.add(guideRegisterEmailLabel);

    guideRegisterPasswordLabel = new JLabel("Password:");
    guideRegisterPasswordLabel.setForeground(Color.WHITE);
    guideRegisterPasswordLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    guideRegisterPasswordLabel.setBounds(92, 390, 86, 27);
    panel_1.add(guideRegisterPasswordLabel);

    guideRegisterPasswordTextField = new JTextField();
    guideRegisterPasswordTextField.setColumns(10);
    guideRegisterPasswordTextField.setBounds(188, 393, 333, 27);
    panel_1.add(guideRegisterPasswordTextField);

    guideRegisterEmailTextField = new JTextField();
    guideRegisterEmailTextField.setColumns(10);
    guideRegisterEmailTextField.setBounds(188, 332, 333, 27);
    panel_1.add(guideRegisterEmailTextField);

    guideRegisterEmergencyContactLabel = new JLabel("Emergency contact: ");
    guideRegisterEmergencyContactLabel.setForeground(Color.WHITE);
    guideRegisterEmergencyContactLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    guideRegisterEmergencyContactLabel.setBounds(542, 390, 163, 27);
    panel_1.add(guideRegisterEmergencyContactLabel);

    guideRegisterNameLabel = new JLabel("Name: ");
    guideRegisterNameLabel.setForeground(Color.WHITE);
    guideRegisterNameLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    guideRegisterNameLabel.setBounds(643, 328, 62, 27);
    panel_1.add(guideRegisterNameLabel);

    guideRegisterNameTextField = new JTextField();
    guideRegisterNameTextField.setColumns(10);
    guideRegisterNameTextField.setBounds(704, 332, 333, 27);
    panel_1.add(guideRegisterNameTextField);

    guideRegisterEmergencyContactTextField = new JTextField();
    guideRegisterEmergencyContactTextField.setColumns(10);
    guideRegisterEmergencyContactTextField.setBounds(704, 394, 333, 27);
    panel_1.add(guideRegisterEmergencyContactTextField);

    registerMemberBtn = new JButton("REGISTER MEMBER");
    registerMemberBtn.setContentAreaFilled(false);
    registerMemberBtn.setOpaque(true);
    registerMemberBtn.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
    registerMemberBtn.setBackground(new Color(255, 215, 0));
    registerMemberBtn.setBounds(930, 210, 224, 49);
    panel_1.add(registerMemberBtn);

    registerGuideBtn = new JButton("REGISTER GUIDE");
    registerGuideBtn.setContentAreaFilled(false);
    registerGuideBtn.setOpaque(true);
    registerGuideBtn.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
    registerGuideBtn.setBackground(new Color(255, 215, 0));
    registerGuideBtn.setBounds(503, 446, 234, 36);
    panel_1.add(registerGuideBtn);

    registerAsMemberLabel = new JLabel("REGISTER AS A MEMBER");
    registerAsMemberLabel.setForeground(Color.WHITE);
    registerAsMemberLabel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 23));
    registerAsMemberLabel.setBounds(453, 11, 300, 27);
    panel_1.add(registerAsMemberLabel);

    registerAsGuideLabel = new JLabel("REGISTER AS A GUIDE");
    registerAsGuideLabel.setForeground(Color.WHITE);
    registerAsGuideLabel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 23));
    registerAsGuideLabel.setBounds(465, 290, 272, 27);
    panel_1.add(registerAsGuideLabel);

    JSeparator separator_1 = new JSeparator();
    separator_1.setForeground(Color.WHITE);
    separator_1.setBounds(10, 277, 1176, 2);
    panel_1.add(separator_1);


    registerMemberRequiredLabel = new JLabel("Check if applies:");
    registerMemberRequiredLabel.setForeground(Color.WHITE);
    registerMemberRequiredLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    registerMemberRequiredLabel.setBounds(815, 111, 138, 27);
    panel_1.add(registerMemberRequiredLabel);

    registerMemberPasswordLabel = new JLabel("Password:");
    registerMemberPasswordLabel.setForeground(Color.WHITE);
    registerMemberPasswordLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    registerMemberPasswordLabel.setBounds(863, 64, 86, 27);
    panel_1.add(registerMemberPasswordLabel);

    memberRegisterPasswordField = new JTextField();
    memberRegisterPasswordField.setColumns(10);
    memberRegisterPasswordField.setBounds(955, 67, 199, 27);
    panel_1.add(memberRegisterPasswordField);

    selectItemsRegisterMemberComboBox = new JComboBox();
    selectItemsRegisterMemberComboBox.setEditable(false);
    selectItemsRegisterMemberComboBox.setBounds(188, 158, 199, 27);
    panel_1.add(selectItemsRegisterMemberComboBox);

    selectItemRegisterMemberList = new JLabel("Select items:");
    selectItemRegisterMemberList.setForeground(Color.WHITE);
    selectItemRegisterMemberList.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    selectItemRegisterMemberList.setBounds(73, 155, 105, 27);
    panel_1.add(selectItemRegisterMemberList);

    quantityRegisterMemberField = new JTextField();
    quantityRegisterMemberField.setColumns(10);
    quantityRegisterMemberField.setBounds(188, 199, 199, 27);
    panel_1.add(quantityRegisterMemberField);

    quantityRegisterMemberLabel = new JLabel("Quantity:");
    quantityRegisterMemberLabel.setForeground(Color.WHITE);
    quantityRegisterMemberLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    quantityRegisterMemberLabel.setBounds(92, 196, 78, 27);
    panel_1.add(quantityRegisterMemberLabel);

    addItemRegisterMemberButton = new JButton("Add Item");
    addItemRegisterMemberButton.setContentAreaFilled(false);
    addItemRegisterMemberButton.setOpaque(true);
    addItemRegisterMemberButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 17));
    addItemRegisterMemberButton.setBackground(new Color(255, 204, 102));
    addItemRegisterMemberButton.setBounds(264, 237, 123, 28);
    panel_1.add(addItemRegisterMemberButton);

    itemRegisterMemberTable = new JTable(
        new DefaultTableModel(new Object[][] {}, new String[] {"Added items", "Quantities"}));
    itemRegisterMemberTable.setFont(new Font("Baskerville", Font.PLAIN, 12));
    itemRegisterMemberTable.setFillsViewportHeight(true);
    itemRegisterMemberTable.setBounds(449, 164, 349, 85);
    itemRegisterMemberTable.setShowGrid(false);


    JScrollPane spRegisterMember = new JScrollPane(itemRegisterMemberTable);
    spRegisterMember.setBounds(483, 157, 300, 106);
    panel_1.add(spRegisterMember);



    backgroundRegisterPage = new JLabel("");
    backgroundRegisterPage.setBounds(0, 0, 1206, 509);
    ImageIcon backgroundImageRegisterPage =
        new ImageIcon(this.getClass().getResource("/backgroundImage.png"));
    backgroundRegisterPage.setIcon(backgroundImageRegisterPage);
    backgroundRegisterPage.setOpaque(true);
    panel_1.add(backgroundRegisterPage);


    panel_2 = new JPanel();
    tabbedPane_1.addTab("Set up NMC info", null, panel_2, null);
    panel_2.setLayout(null);

    errorMessageTab3 = new JLabel("");
    errorMessageTab3.setForeground(Color.RED);
    errorMessageTab3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
    errorMessageTab3.setBounds(37, 83, 601, 24);
    panel_2.add(errorMessageTab3);

    setUpStartdateLabel = new JLabel("Start date:");
    setUpStartdateLabel.setForeground(Color.WHITE);
    setUpStartdateLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    setUpStartdateLabel.setBounds(37, 146, 93, 27);
    panel_2.add(setUpStartdateLabel);

    setUpStartAnchorDateTextField = new JTextField();
    setUpStartAnchorDateTextField.setColumns(10);
    setUpStartAnchorDateTextField.setBounds(136, 149, 189, 27);
    panel_2.add(setUpStartAnchorDateTextField);
    setUpStartAnchorDateTextField.setVisible(false);

    // from here...

    var props = new Properties();
    props.putAll(DATE_PROPS);
    JDatePanelImpl datePanel = new JDatePanelImpl(new SqlDateModel(), props);
    new JDatePickerImpl(datePanel, new DateLabelFormatter());

    // elements for daily overview
    var overviewModel = new SqlDateModel();
    var now = LocalDate.now();
    overviewModel.setDate(now.getYear(), now.getMonthValue() - 1, now.getDayOfMonth());
    overviewModel.setSelected(true);
    JDatePanelImpl overviewDatePanel = new JDatePanelImpl(overviewModel, props);
    NMCDatePicker = new JDatePickerImpl(overviewDatePanel, new DateLabelFormatter());

    NMCDatePicker.setBounds(setUpStartAnchorDateTextField.getBounds());
    panel_2.add(NMCDatePicker);

    // ... to here --> Date picker for the selection of the begining of the climbing season

    setUpNumWeeksLabel = new JLabel("Number of weeks: ");
    setUpNumWeeksLabel.setForeground(Color.WHITE);
    setUpNumWeeksLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    setUpNumWeeksLabel.setBounds(357, 146, 149, 27);
    panel_2.add(setUpNumWeeksLabel);

    setUpNumWeeksTextField = new JTextField();
    setUpNumWeeksTextField.setColumns(10);
    setUpNumWeeksTextField.setBounds(512, 149, 189, 27);
    panel_2.add(setUpNumWeeksTextField);

    setUpPriceGuidePerWeekLabel = new JLabel("Price of guide per week: ");
    setUpPriceGuidePerWeekLabel.setForeground(Color.WHITE);
    setUpPriceGuidePerWeekLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    setUpPriceGuidePerWeekLabel.setBounds(739, 149, 202, 27);
    panel_2.add(setUpPriceGuidePerWeekLabel);

    setUpPriceGuidePerWeekTextField = new JTextField();
    setUpPriceGuidePerWeekTextField.setColumns(10);
    setUpPriceGuidePerWeekTextField.setBounds(947, 152, 189, 27);
    panel_2.add(setUpPriceGuidePerWeekTextField);

    confirmSetUpButton = new JButton("CONFIRM");
    confirmSetUpButton.setContentAreaFilled(false);
    confirmSetUpButton.setOpaque(true);
    confirmSetUpButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
    confirmSetUpButton.setBackground(new Color(102, 204, 204));
    confirmSetUpButton.setBounds(509, 290, 202, 62);
    panel_2.add(confirmSetUpButton);

    setUpNMCLabel = new JLabel("SET UP NMC INFORMATION");
    setUpNMCLabel.setForeground(Color.WHITE);
    setUpNMCLabel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 23));
    setUpNMCLabel.setBounds(449, 35, 324, 27);
    panel_2.add(setUpNMCLabel);

    backgroundSetUpPage = new JLabel("");
    backgroundSetUpPage.setBounds(0, 0, 1206, 509);
    backgroundImageSetUpPage = new ImageIcon(this.getClass().getResource("/backgroundImage.png"));
    backgroundSetUpPage.setIcon(backgroundImageSetUpPage);
    backgroundSetUpPage.setOpaque(true);
    panel_2.add(backgroundSetUpPage);

    panel_3 = new JPanel();
    tabbedPane_1.addTab("Remove user or equipment", null, panel_3, null);
    panel_3.setLayout(null);

    errorMessageTab4 = new JLabel("");
    errorMessageTab4.setForeground(Color.RED);
    errorMessageTab4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
    errorMessageTab4.setBounds(38, 55, 601, 24);
    panel_3.add(errorMessageTab4);

    removeUserLabel = new JLabel("REMOVE USER");
    removeUserLabel.setForeground(Color.WHITE);
    removeUserLabel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 23));
    removeUserLabel.setBounds(524, 25, 177, 27);
    panel_3.add(removeUserLabel);

    selectGuideRemovePageLabel = new JLabel("Select guide:");
    selectGuideRemovePageLabel.setForeground(Color.WHITE);
    selectGuideRemovePageLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    selectGuideRemovePageLabel.setBounds(178, 90, 111, 27);
    panel_3.add(selectGuideRemovePageLabel);

    selectGuideRemovePageComboBox = new JComboBox();
    selectGuideRemovePageComboBox.setEditable(false);
    selectGuideRemovePageComboBox.setBounds(295, 90, 234, 27);
    panel_3.add(selectGuideRemovePageComboBox);

    deleteGuideButton = new JButton("Delete guide");
    deleteGuideButton.setContentAreaFilled(false);
    deleteGuideButton.setOpaque(true);
    deleteGuideButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
    deleteGuideButton.setBackground(new Color(250, 128, 114));
    deleteGuideButton.setBounds(295, 132, 234, 36);
    panel_3.add(deleteGuideButton);

    selectMemberRemovePageLabel = new JLabel("Select member:");
    selectMemberRemovePageLabel.setForeground(Color.WHITE);
    selectMemberRemovePageLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    selectMemberRemovePageLabel.setBounds(543, 90, 134, 27);
    panel_3.add(selectMemberRemovePageLabel);

    selectMemberRemovePageComboBox = new JComboBox();
    selectMemberRemovePageComboBox.setEditable(false);
    selectMemberRemovePageComboBox.setBounds(687, 90, 234, 27);
    panel_3.add(selectMemberRemovePageComboBox);

    deleteMemberButton = new JButton("Delete member");
    deleteMemberButton.setContentAreaFilled(false);
    deleteMemberButton.setOpaque(true);
    deleteMemberButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
    deleteMemberButton.setBackground(new Color(250, 128, 114));
    deleteMemberButton.setBounds(687, 132, 234, 36);
    panel_3.add(deleteMemberButton);

    selectEquipmentRemovePageLabel = new JLabel("Select equipment:");
    selectEquipmentRemovePageLabel.setForeground(Color.WHITE);
    selectEquipmentRemovePageLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    selectEquipmentRemovePageLabel.setBounds(347, 360, 147, 27);
    panel_3.add(selectEquipmentRemovePageLabel);

    selectEquipmentRemovePageComboBox = new JComboBox();
    selectEquipmentRemovePageComboBox.setEditable(false);
    selectEquipmentRemovePageComboBox.setBounds(504, 360, 224, 27);
    panel_3.add(selectEquipmentRemovePageComboBox);

    deleteEquipmentButton = new JButton("Delete equipment");
    deleteEquipmentButton.setContentAreaFilled(false);
    deleteEquipmentButton.setOpaque(true);
    deleteEquipmentButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
    deleteEquipmentButton.setBackground(new Color(250, 128, 114));
    deleteEquipmentButton.setBounds(504, 402, 224, 36);
    panel_3.add(deleteEquipmentButton);

    removeEquipmentLabel = new JLabel("REMOVE EQUIPMENT");
    removeEquipmentLabel.setForeground(Color.WHITE);
    removeEquipmentLabel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 23));
    removeEquipmentLabel.setBounds(495, 273, 253, 27);
    panel_3.add(removeEquipmentLabel);

    JSeparator separator_1_1 = new JSeparator();
    separator_1_1.setForeground(Color.WHITE);
    separator_1_1.setBounds(10, 233, 1176, 2);
    panel_3.add(separator_1_1);

    backgroundRemovePage = new JLabel("");
    backgroundRemovePage.setBounds(0, 0, 1206, 509);
    ImageIcon backgroundImageRemovePage =
        new ImageIcon(this.getClass().getResource("/backgroundImage.png"));
    backgroundRemovePage.setIcon(backgroundImageRemovePage);
    backgroundRemovePage.setOpaque(true);
    panel_3.add(backgroundRemovePage);

    panel_4 = new JPanel();
    tabbedPane_1.addTab("Add or update equipment", null, panel_4, null);
    panel_4.setLayout(null);

    errorMessageTab5 = new JLabel("");
    errorMessageTab5.setForeground(Color.RED);
    errorMessageTab5.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
    errorMessageTab5.setBounds(38, 54, 601, 24);
    panel_4.add(errorMessageTab5);

    nameEquipmentToAddLabel = new JLabel("Name:");
    nameEquipmentToAddLabel.setForeground(Color.WHITE);
    nameEquipmentToAddLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    nameEquipmentToAddLabel.setBounds(81, 89, 63, 27);
    panel_4.add(nameEquipmentToAddLabel);

    nameEquipmentToAddTextField = new JTextField();
    nameEquipmentToAddTextField.setColumns(10);
    nameEquipmentToAddTextField.setBounds(154, 92, 189, 27);
    panel_4.add(nameEquipmentToAddTextField);

    weightEquipmentToAddLabel = new JLabel("Weight:");
    weightEquipmentToAddLabel.setForeground(Color.WHITE);
    weightEquipmentToAddLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    weightEquipmentToAddLabel.setBounds(430, 89, 73, 27);
    panel_4.add(weightEquipmentToAddLabel);

    weightEquipmentToAddTextField = new JTextField();
    weightEquipmentToAddTextField.setColumns(10);
    weightEquipmentToAddTextField.setBounds(513, 92, 189, 27);
    panel_4.add(weightEquipmentToAddTextField);

    pricePerWeekEquipmentToAddLabel = new JLabel("Price per week:");
    pricePerWeekEquipmentToAddLabel.setForeground(Color.WHITE);
    pricePerWeekEquipmentToAddLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    pricePerWeekEquipmentToAddLabel.setBounds(764, 89, 125, 27);
    panel_4.add(pricePerWeekEquipmentToAddLabel);

    pricePerWeekEquipmentToAddTextField = new JTextField();
    pricePerWeekEquipmentToAddTextField.setColumns(10);
    pricePerWeekEquipmentToAddTextField.setBounds(891, 92, 189, 27);
    panel_4.add(pricePerWeekEquipmentToAddTextField);

    addEquipmentLabel = new JLabel("ADD EQUIPMENT");
    addEquipmentLabel.setForeground(Color.WHITE);
    addEquipmentLabel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 23));
    addEquipmentLabel.setBounds(488, 21, 225, 27);
    panel_4.add(addEquipmentLabel);

    addEquipmentButton = new JButton("ADD ITEM");
    addEquipmentButton.setContentAreaFilled(false);
    addEquipmentButton.setOpaque(true);
    addEquipmentButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
    addEquipmentButton.setBackground(new Color(153, 204, 153));
    addEquipmentButton.setBounds(534, 160, 149, 35);
    panel_4.add(addEquipmentButton);

    updateEquipmentLabel = new JLabel("UPDATE EQUIPMENT");
    updateEquipmentLabel.setForeground(Color.WHITE);
    updateEquipmentLabel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 23));
    updateEquipmentLabel.setBounds(473, 268, 262, 27);
    panel_4.add(updateEquipmentLabel);

    newEquipmentNameLabel = new JLabel("New name:");
    newEquipmentNameLabel.setForeground(Color.WHITE);
    newEquipmentNameLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    newEquipmentNameLabel.setBounds(52, 372, 92, 27);
    panel_4.add(newEquipmentNameLabel);

    newEquipmentNameTextField = new JTextField();
    newEquipmentNameTextField.setColumns(10);
    newEquipmentNameTextField.setBounds(154, 375, 189, 27);
    panel_4.add(newEquipmentNameTextField);

    newEquipmentWeightLabel = new JLabel("New weight:");
    newEquipmentWeightLabel.setForeground(Color.WHITE);
    newEquipmentWeightLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    newEquipmentWeightLabel.setBounds(404, 372, 99, 27);
    panel_4.add(newEquipmentWeightLabel);

    newEquipmentWeightTextField = new JTextField();
    newEquipmentWeightTextField.setColumns(10);
    newEquipmentWeightTextField.setBounds(513, 375, 189, 27);
    panel_4.add(newEquipmentWeightTextField);

    newEquipmentPricePerWeekLabel = new JLabel("New price per week:");
    newEquipmentPricePerWeekLabel.setForeground(Color.WHITE);
    newEquipmentPricePerWeekLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    newEquipmentPricePerWeekLabel.setBounds(728, 372, 161, 27);
    panel_4.add(newEquipmentPricePerWeekLabel);

    newEquipmentPricePerWeekTextField = new JTextField();
    newEquipmentPricePerWeekTextField.setColumns(10);
    newEquipmentPricePerWeekTextField.setBounds(891, 375, 189, 27);
    panel_4.add(newEquipmentPricePerWeekTextField);

    JSeparator separator_1_1_1 = new JSeparator();
    separator_1_1_1.setForeground(Color.WHITE);
    separator_1_1_1.setBounds(10, 245, 1176, 2);
    panel_4.add(separator_1_1_1);

    updateEquipmentButton = new JButton("UPDATE");
    updateEquipmentButton.setContentAreaFilled(false);
    updateEquipmentButton.setOpaque(true);
    updateEquipmentButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
    updateEquipmentButton.setBackground(SystemColor.activeCaption);
    updateEquipmentButton.setBounds(534, 436, 149, 35);
    panel_4.add(updateEquipmentButton);

    selectEquipmentToUpdateLabel = new JLabel("Select equipment:");
    selectEquipmentToUpdateLabel.setForeground(Color.WHITE);
    selectEquipmentToUpdateLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    selectEquipmentToUpdateLabel.setBounds(337, 316, 147, 27);
    panel_4.add(selectEquipmentToUpdateLabel);

    selectEquipmentToUpdateComboBox = new JComboBox();
    selectEquipmentToUpdateComboBox.setEditable(false);
    selectEquipmentToUpdateComboBox.setBounds(487, 319, 237, 27);
    panel_4.add(selectEquipmentToUpdateComboBox);

    backgroundEquipmentPage = new JLabel("");
    backgroundEquipmentPage.setBounds(0, 0, 1206, 509);
    ImageIcon backgroundImageEquipmentPage =
        new ImageIcon(this.getClass().getResource("/backgroundImage.png"));
    backgroundEquipmentPage.setIcon(backgroundImageEquipmentPage);
    backgroundEquipmentPage.setOpaque(true);
    panel_4.add(backgroundEquipmentPage);

    panel_5 = new JPanel();
    tabbedPane_1.addTab("Assignment and payment", null, panel_5, null);
    panel_5.setLayout(null);

    errorMessageTab6 = new JLabel("");
    errorMessageTab6.setForeground(Color.RED);
    errorMessageTab6.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
    errorMessageTab6.setBounds(38, 39, 601, 24);
    panel_5.add(errorMessageTab6);

    selectMemberForPayLabel = new JLabel("Select member:");
    selectMemberForPayLabel.setForeground(Color.WHITE);
    selectMemberForPayLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    selectMemberForPayLabel.setBounds(162, 64, 147, 27);
    panel_5.add(selectMemberForPayLabel);

    selectMemberForPayComboBox = new JComboBox();
    selectMemberForPayComboBox.setEditable(false);
    selectMemberForPayComboBox.setBounds(312, 67, 237, 27);
    panel_5.add(selectMemberForPayComboBox);

    payForTheTripButton = new JButton("Pay for the trip");
    payForTheTripButton.setContentAreaFilled(false);
    payForTheTripButton.setOpaque(true);
    payForTheTripButton.setForeground(Color.BLACK);
    payForTheTripButton.setFont(new Font("Myanmar Text", Font.BOLD, 17));
    payForTheTripButton.setBackground(new Color(173, 216, 230));
    payForTheTripButton.setBounds(228, 130, 189, 46);
    panel_5.add(payForTheTripButton);

    finishTheTripButton = new JButton("Finish the trip");
    finishTheTripButton.setContentAreaFilled(false);
    finishTheTripButton.setOpaque(true);
    finishTheTripButton.setForeground(Color.BLACK);
    finishTheTripButton.setFont(new Font("Myanmar Text", Font.BOLD, 17));
    finishTheTripButton.setBackground(new Color(173, 216, 230));
    finishTheTripButton.setBounds(501, 130, 189, 46);
    panel_5.add(finishTheTripButton);

    cancelTheTripButton = new JButton("Cancel the trip");
    cancelTheTripButton.setContentAreaFilled(false);
    cancelTheTripButton.setOpaque(true);
    cancelTheTripButton.setForeground(Color.BLACK);
    cancelTheTripButton.setFont(new Font("Myanmar Text", Font.BOLD, 17));
    cancelTheTripButton.setBackground(new Color(173, 216, 230));
    cancelTheTripButton.setBounds(766, 130, 189, 46);
    panel_5.add(cancelTheTripButton);

    autorizationCodeLabel = new JLabel("Authorization code:");
    autorizationCodeLabel.setForeground(Color.WHITE);
    autorizationCodeLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    autorizationCodeLabel.setBounds(642, 64, 155, 27);
    panel_5.add(autorizationCodeLabel);

    authorizationCodeTextField = new JTextField();
    authorizationCodeTextField.setColumns(10);
    authorizationCodeTextField.setBounds(818, 67, 189, 27);
    panel_5.add(authorizationCodeTextField);

    payFinishOrCancelTripLabel = new JLabel("PAY, FINISH, OR CANCEL TRIP");
    payFinishOrCancelTripLabel.setForeground(Color.WHITE);
    payFinishOrCancelTripLabel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 23));
    payFinishOrCancelTripLabel.setBounds(419, 11, 378, 27);
    panel_5.add(payFinishOrCancelTripLabel);

    initiateAssignmentsLabel = new JLabel("INITIATE ASSIGNMENTS");
    initiateAssignmentsLabel.setForeground(Color.WHITE);
    initiateAssignmentsLabel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 23));
    initiateAssignmentsLabel.setBounds(451, 238, 287, 27);
    panel_5.add(initiateAssignmentsLabel);

    initiateAssignmentsForAllMembersButton = new JButton("Initiate assignments for all members");
    initiateAssignmentsForAllMembersButton.setContentAreaFilled(false);
    initiateAssignmentsForAllMembersButton.setOpaque(true);
    initiateAssignmentsForAllMembersButton.setForeground(Color.BLACK);
    initiateAssignmentsForAllMembersButton.setFont(new Font("Myanmar Text", Font.BOLD, 17));
    initiateAssignmentsForAllMembersButton.setBackground(new Color(173, 216, 230));
    initiateAssignmentsForAllMembersButton.setBounds(416, 289, 347, 46);
    panel_5.add(initiateAssignmentsForAllMembersButton);

    JSeparator separator_1_1_1_1 = new JSeparator();
    separator_1_1_1_1.setForeground(Color.WHITE);
    separator_1_1_1_1.setBounds(10, 206, 1176, 2);
    panel_5.add(separator_1_1_1_1);

    JSeparator separator_1_1_1_1_1 = new JSeparator();
    separator_1_1_1_1_1.setForeground(Color.WHITE);
    separator_1_1_1_1_1.setBounds(10, 361, 1176, 2);
    panel_5.add(separator_1_1_1_1_1);

    selectStartWeekNumberLabel = new JLabel("Select week number:");
    selectStartWeekNumberLabel.setForeground(Color.WHITE);
    selectStartWeekNumberLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
    selectStartWeekNumberLabel.setBounds(99, 437, 180, 27);
    panel_5.add(selectStartWeekNumberLabel);

    selectStartWeekNumberComboBox = new JComboBox();
    selectStartWeekNumberComboBox.setEditable(false);
    selectStartWeekNumberComboBox.setBounds(273, 440, 237, 27);
    panel_5.add(selectStartWeekNumberComboBox);

    startTripsLabel = new JLabel("START TRIPS");
    startTripsLabel.setForeground(Color.WHITE);
    startTripsLabel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 23));
    startTripsLabel.setBounds(501, 379, 171, 27);
    panel_5.add(startTripsLabel);

    startAllTripsForThisWeekLabel = new JButton("Start all trips for this week");
    startAllTripsForThisWeekLabel.setContentAreaFilled(false);
    startAllTripsForThisWeekLabel.setOpaque(true);
    startAllTripsForThisWeekLabel.setForeground(Color.BLACK);
    startAllTripsForThisWeekLabel.setFont(new Font("Myanmar Text", Font.BOLD, 17));
    startAllTripsForThisWeekLabel.setBackground(new Color(173, 216, 230));
    startAllTripsForThisWeekLabel.setBounds(698, 417, 301, 46);
    panel_5.add(startAllTripsForThisWeekLabel);

    backgroundAssignmentPage = new JLabel("");
    backgroundAssignmentPage.setBounds(0, 0, 1206, 509);
    ImageIcon backgroundImageAssignmentPage =
        new ImageIcon(this.getClass().getResource("/backgroundImage.png"));
    backgroundAssignmentPage.setIcon(backgroundImageAssignmentPage);
    backgroundAssignmentPage.setOpaque(true);
    panel_5.add(backgroundAssignmentPage);

    panel_6 = new JPanel();
    panel_6.setToolTipText("");
    tabbedPane_1.addTab("View assignments", null, panel_6, null);
    panel_6.setLayout(null);

    errorMessageTab7 = new JLabel("");
    errorMessageTab7.setForeground(Color.RED);
    errorMessageTab7.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
    errorMessageTab7.setBounds(56, 76, 601, 24);
    panel_6.add(errorMessageTab7);

    // VIEW ASSIGNMENT TABLE

    viewAssignmentTable = new JTable(new DefaultTableModel(new Object[][] {},
        new String[] {"Member email", "Member name", "Guide email", "Guide name", "Hotel name",
            "Start week", "End week", "Total guide cost", "Total equipment cost", "Status",
            "Authorization code", "Refund percentage"})) {
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    viewAssignmentTable.setBackground(SystemColor.window);
    viewAssignmentTable.setFont(new Font("Baskerville", Font.PLAIN, 12));
    viewAssignmentTable.setFillsViewportHeight(true);
    viewAssignmentTable.setBorder(null);
    viewAssignmentTable.setShowGrid(false);
    viewAssignmentTable.getColumnModel().getColumn(0).setPreferredWidth(120);
    viewAssignmentTable.getColumnModel().getColumn(1).setPreferredWidth(120);
    viewAssignmentTable.getColumnModel().getColumn(2).setPreferredWidth(120);
    viewAssignmentTable.getColumnModel().getColumn(3).setPreferredWidth(120);
    viewAssignmentTable.getColumnModel().getColumn(4).setPreferredWidth(120);
    viewAssignmentTable.getColumnModel().getColumn(5).setPreferredWidth(120);
    viewAssignmentTable.getColumnModel().getColumn(6).setPreferredWidth(80);
    viewAssignmentTable.getColumnModel().getColumn(7).setPreferredWidth(120);
    viewAssignmentTable.getColumnModel().getColumn(8).setPreferredWidth(120);
    viewAssignmentTable.getColumnModel().getColumn(9).setPreferredWidth(120);
    viewAssignmentTable.getColumnModel().getColumn(10).setPreferredWidth(120);
    viewAssignmentTable.getColumnModel().getColumn(11).setPreferredWidth(120);

    int viewAssignmentTableOffset = 450;
    viewAssignmentTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    JScrollPane scrollPane = new JScrollPane(viewAssignmentTable);
    scrollPane.setBounds(10, 109, backgroundImage.getIconWidth() - 40,
        backgroundImage.getIconHeight() - viewAssignmentTableOffset);
    // scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    // scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    panel_6.add(scrollPane);

    // END VIEW ASSIGNMENT TABLE


    viewAssignmentLabel = new JLabel("VIEW ASSIGNMENTS");
    viewAssignmentLabel.setForeground(Color.WHITE);
    viewAssignmentLabel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 23));
    viewAssignmentLabel.setBounds(470, 38, 265, 27);
    panel_6.add(viewAssignmentLabel);

    backgroundViewtPage = new JLabel("");
    backgroundViewtPage.setBounds(0, 0, 1206, 509);
    ImageIcon backgroundImageViewPage =
        new ImageIcon(this.getClass().getResource("/backgroundImage.png"));
    backgroundViewtPage.setIcon(backgroundImageAssignmentPage);
    backgroundViewtPage.setOpaque(true);
    panel_6.add(backgroundViewtPage);



    // ACTION LISTENERS
    memberAddItemButton.addActionListener(this::addUpdateEquipmentButtonActionPerformed);
    updateMemberButton.addActionListener(this::updateMemberButtonActionPerformed);
    updateGuideButton.addActionListener(this::updateGuideButtonActionPerformed);
    registerMemberBtn.addActionListener(this::registerMemberButtonActionPerformed);
    registerGuideBtn.addActionListener(this::registerGuideButtonActionPerformed);
    addItemRegisterMemberButton.addActionListener(this::addItemRegisterMemberActionPerformed);
    confirmSetUpButton.addActionListener(this::confirmSetUpButtonActionPerformed);
    deleteGuideButton.addActionListener(this::deleteGuideButtonActionPerformed);
    deleteMemberButton.addActionListener(this::deleteMemberButtonActionPerformed);
    deleteEquipmentButton.addActionListener(this::deleteEquipmentButtonActionPerformed);
    addEquipmentButton.addActionListener(this::addEquipmentButtonActionPerformed);
    updateEquipmentButton.addActionListener(this::updateEquipmentButtonActionPerformed);
    payForTheTripButton.addActionListener(this::payButtonActionPerformed);
    finishTheTripButton.addActionListener(this::finishButtonActionPerformed);
    cancelTheTripButton.addActionListener(this::cancelButtonActionPerformed);
    initiateAssignmentsForAllMembersButton.addActionListener(this::initiateActionPerformed);
    startAllTripsForThisWeekLabel.addActionListener(this::startTripsButtonActionPerformed);

    var offset = 228; // will help set the default size of the window
    // set window location to be in the center of the screen
    setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight() - offset);
    refreshData();
    setVisible(true);
  }

  /**
   * This method resets text fields and item tables and updates comboboxes and the assignment table.
   * 
   * @author Asma Gandour
   */
  private void refreshData() {
    currentErrorMessage.setText(error);
    if (error == null || error.isEmpty()) {

      newMemberWeekTextField.setText("");
      newMemberEmergencyNumberTextField.setText("");
      newMemberNameTextField.setText("");
      newMemberPasswordTextField.setText("");
      memberItemQuantityTextField.setText("");
      memberRegisterEmailTextField.setText("");
      memberRegisterWeekTextField.setText("");
      memberRegisterContactTextField.setText("");
      memberRegisterNameTextField.setText("");
      memberRegisterPasswordField.setText("");
      guideRegisterPasswordTextField.setText("");
      guideRegisterEmailTextField.setText("");
      guideRegisterNameTextField.setText("");
      guideRegisterEmergencyContactTextField.setText("");
      setUpStartAnchorDateTextField.setText("");
      setUpNumWeeksTextField.setText("");
      setUpPriceGuidePerWeekTextField.setText("");
      newGuidePasswordTextField.setText("");
      newGuideEmergencyNumberTextField.setText("");
      newGuideNameTextField.setText("");
      nameEquipmentToAddTextField.setText("");
      weightEquipmentToAddTextField.setText("");
      pricePerWeekEquipmentToAddTextField.setText("");
      newEquipmentNameTextField.setText("");
      newEquipmentWeightTextField.setText("");
      newEquipmentPricePerWeekTextField.setText("");
      authorizationCodeTextField.setText("");
      quantityRegisterMemberField.setText("");

      DefaultTableModel updateMemberEquipmentModel =
          (DefaultTableModel) updateMemberEquipmentTable.getModel();

      for (int i = updateMemberEquipmentModel.getRowCount() - 1; i >= 0; i--) {
        updateMemberEquipmentModel.removeRow(i);
      }

      DefaultTableModel registerMemberEquipmentModel =
          (DefaultTableModel) itemRegisterMemberTable.getModel();

      for (int i = registerMemberEquipmentModel.getRowCount() - 1; i >= 0; i--) {
        registerMemberEquipmentModel.removeRow(i);
      }

      DefaultTableModel viewAssignmentModel = (DefaultTableModel) viewAssignmentTable.getModel();

      for (int i = viewAssignmentModel.getRowCount() - 1; i >= 0; i--) {
        viewAssignmentModel.removeRow(i);
      }

      for (TOAssignment assignment : ClimbSafeFeatureSet6Controller.getAssignments()) {
        viewAssignmentModel.addRow(new Object[] {assignment.getMemberEmail(),
            assignment.getMemberName(), assignment.getGuideEmail(), assignment.getGuideName(),
            assignment.getHotelName(), assignment.getStartWeek(), assignment.getEndWeek(),
            assignment.getTotalCostForGuide(), assignment.getTotalCostForEquipment(),
            assignment.getStatus(), assignment.getAuthorizationCode(),
            assignment.getRefundedPercentageAmount()});
      }

      var lists = List.of(selectMemberToUpdateComboBox, selectNewItemsComboBox,
          selectGuideToUpdateComboBox, selectGuideRemovePageComboBox,
          selectMemberRemovePageComboBox, selectEquipmentRemovePageComboBox,
          selectEquipmentToUpdateComboBox, selectMemberForPayComboBox,
          selectStartWeekNumberComboBox, selectItemsRegisterMemberComboBox);

      lists.forEach(JComboBox::removeAllItems);

      // select member to update
      AssignmentController.getMembers().forEach(selectMemberToUpdateComboBox::addItem);

      // select items (equipments) when updating member
      AssignmentController.getItems().forEach(selectNewItemsComboBox::addItem);

      // select guide to update
      AssignmentController.getGuides().forEach(selectGuideToUpdateComboBox::addItem);

      // select guide to remove
      AssignmentController.getGuides().forEach(selectGuideRemovePageComboBox::addItem);

      // select member to remove
      AssignmentController.getMembers().forEach(selectMemberRemovePageComboBox::addItem);

      // select equipment to remove
      AssignmentController.getEquipments().forEach(selectEquipmentRemovePageComboBox::addItem);

      // select equipment to update
      AssignmentController.getEquipments().forEach(selectEquipmentToUpdateComboBox::addItem);

      // select member to pay
      AssignmentController.getMembers().forEach(selectMemberForPayComboBox::addItem);

      // select start week
      AssignmentController.getWeekNbrs().forEach(selectStartWeekNumberComboBox::addItem);

      // select equipment for new member
      AssignmentController.getItems().forEach(selectItemsRegisterMemberComboBox::addItem);


      lists.forEach(list -> list.setSelectedIndex(-1));
    }
    successStatus = false;
  }

  /**
   * This method resets the quantity field and updates the item combobox in the register panel.
   * 
   * @author Asma Gandour
   */
  private void refreshRegisterMemberItem() {
    currentErrorMessage.setText(error);
    if (error == null || error.isEmpty()) {
      quantityRegisterMemberField.setText("");

      selectItemsRegisterMemberComboBox.removeAllItems();

      // select member to update
      AssignmentController.getItems().forEach(selectItemsRegisterMemberComboBox::addItem);
    }
  }

  /**
   * This method resets the quantity field and updates the item combobox in the update panel.
   * 
   * @author Asma Gandour
   */
  private void refreshUpdateMemberItem() {
    currentErrorMessage.setText(error);
    if (error == null || error.isEmpty()) {
      memberItemQuantityTextField.setText("");

      selectNewItemsComboBox.removeAllItems();

      // select member to update
      AssignmentController.getItems().forEach(selectNewItemsComboBox::addItem);
    }
    successStatus = false;
  }

  /**
   * Registers a member using information of email, password, name, contact, number of weeks,
   * guideRequired, hotelRequied, item names and quantities.
   * 
   * @param evt
   * @author Atreyi Srivastava
   */
  private void registerMemberButtonActionPerformed(ActionEvent evt) {
    error = "";
    String email = memberRegisterEmailTextField.getText();
    if (email.isEmpty()) {
      error = "Enter an email!";
    }
    String password = memberRegisterPasswordField.getText();
    if (password.isEmpty()) {
      error += "Enter a password!";
    }
    String name = memberRegisterNameTextField.getText();
    if (name.isEmpty()) {
      error += "Enter a name!";
    }

    String[] parsedName = name.split(" ");
    if (parsedName.length != 2) {
      error += "Please enter only the first and last name!";
    } else if (!firstName(parsedName[0]) || !lastName(parsedName[1])) {
      error += "Please enter a valid name!";
    }

    String emergencyContact = memberRegisterContactTextField.getText();
    if (emergencyContact.isEmpty()) {
      error += "Enter an emergency number!";
    }

    if (!validateNumber(emergencyContact)) {
      error += "Please enter a valid number!";
    }

    int nrWeeks = getNumberFromField(memberRegisterWeekTextField,
        "Number of weeks needs to be a numerical value!");
    boolean guideRequired = memberGuideRequiredRdBtn.isSelected(); // true if guide is selected
    boolean hotelRequired = memberHotelRequiredRdBtn.isSelected(); // true if hotel is selected


    DefaultTableModel memberItemTable = (DefaultTableModel) itemRegisterMemberTable.getModel();
    List<String> itemNames = new ArrayList<String>(); // iterates through the list to get the names
    for (int i = 0; i < memberItemTable.getRowCount(); i++) {
      String current = (String) memberItemTable.getValueAt(i, 0);
      itemNames.add(current);
    }
    List<Integer> itemQuantities = new ArrayList<Integer>(); // iterates through the list to get the
                                                             // quantities
    for (int i = 0; i < memberItemTable.getRowCount(); i++) {
      int quantity = (int) (memberItemTable.getValueAt(i, 1));
      itemQuantities.add(quantity);
    }

    error = error.trim();
    if (error.isEmpty()) {
      // if there are no errors
      callController(() -> ClimbSafeFeatureSet2Controller.registerMember(email, password, name,
          emergencyContact, nrWeeks, guideRequired, hotelRequired, itemNames, itemQuantities));
      if (successStatus == true) {
        infoBox("Member successfully registered!", "Success");
      }
    }

    currentErrorMessage = errorMessageTab2;
    refreshData(); // updates the visual
  }

  /**
   * This method updates the selected member with the new data
   * if the strings and numbers entered are valid
   * 
   * @author Asma Gandour
   * @param evt
   */
  private void updateMemberButtonActionPerformed(ActionEvent evt) {
    // clear error message and basic input validation
    error = "";
    var selectedMember = (TOMember) selectMemberToUpdateComboBox.getSelectedItem();
    if (selectedMember == null) {
      error = "Member needs to be selected to be updated!";
    }
    String newPassword = newMemberPasswordTextField.getText();
    if (newPassword.isEmpty()) {
      error = "Enter a password!";
    }
    String newName = newMemberNameTextField.getText();
    if (newName.isEmpty()) {
      error = "Enter a name!";
    }

    String[] parsedName = newName.split(" ");
    if (parsedName.length != 2) {
      error += "Please enter only the first and last name!";
    } else if (!firstName(parsedName[0]) || !lastName(parsedName[1])) {
      error += "Please enter a valid name!";
    }

    String newContact = newMemberEmergencyNumberTextField.getText();
    if (newContact.isEmpty()) {
      error += "Enter an emergency number!";
    }

    if (!validateNumber(newContact)) {
      error += "Please enter a valid number!";
    }

    int newNbrWeek = getNumberFromField(newMemberWeekTextField,
        "The number of weeks needs to be a numerical value!");
    boolean newGuideRequired = memberGuideRequiredRdBtn.isSelected();
    boolean newHotelRequired = memberHotelRequiredRdBtn.isSelected();

    DefaultTableModel updateMemberEquipmentModel =
        (DefaultTableModel) updateMemberEquipmentTable.getModel();

    List<String> newItemNames = new ArrayList<String>();
    for (int i = 0; i < updateMemberEquipmentModel.getRowCount(); i++) { // Loop through the rows
      // Record the 5th column value (index 4)
      String currentName = (String) (updateMemberEquipmentModel.getValueAt(i, 0));
      newItemNames.add(currentName);
    }

    List<Integer> newItemQuantities = new ArrayList<Integer>();
    for (int i = 0; i < updateMemberEquipmentModel.getRowCount(); i++) { // Loop through the rows
      // Record the 5th column value (index 4)
      int currentQuantity = (int) (updateMemberEquipmentModel.getValueAt(i, 1));
      newItemQuantities.add(currentQuantity);
    }

    error = error.trim();
    if (error.isEmpty()) {
      callController(() -> ClimbSafeFeatureSet2Controller.updateMember(selectedMember.getEmail(),
          newName, newPassword, newContact, newNbrWeek, newGuideRequired, newHotelRequired,
          newItemNames, newItemQuantities));
      if (successStatus == true) {
        infoBox("Member successfully updated!", "Success");
      }
    }
    currentErrorMessage = errorMessageTab1; // Assign THE currentErrorMessage to the errorMessage of
                                            // the tab the trigger element is comprised in.
    // update visuals
    refreshData();
  }

  private void updateGuideButtonActionPerformed(ActionEvent evt) {
    error = "";
    var selectGuide = (TOGuide) selectGuideToUpdateComboBox.getSelectedItem();
    if (selectGuide == null) {
      error = "Guide needs to be selected to be updated!";
    }

    String newName = newGuideNameTextField.getText();
    if (newName.isEmpty()) {
      error += "Enter a name!";
    }

    String[] parsedName = newName.split(" ");
    if (parsedName.length != 2) {
      error += "Please enter only the first and last name!";
    } else if (!firstName(parsedName[0]) || !lastName(parsedName[1])) {
      error += "Please enter a valid name!";
    }


    String newPassword = newGuidePasswordTextField.getText();
    if (newPassword.isEmpty()) {
      error += "Enter a password!";
    }

    String newEmergencyContact = newGuideEmergencyNumberTextField.getText();
    if (newEmergencyContact.isEmpty()) {
      error += "Enter an emergency number!";
    }

    if (!validateNumber(newEmergencyContact)) {
      error += "Please enter a valid number!";
    }


    error = error.trim();
    if (error.isEmpty()) {
      callController(() -> ClimbSafeFeatureSet3Controller.updateGuide(selectGuide.getEmail(),
          newPassword, newName, newEmergencyContact));
      if (successStatus == true) {
        infoBox("Guide successfully updated!", "Success");
      }
    }


    currentErrorMessage = errorMessageTab1;
    refreshData();
  }


  /**
   * Registers a guide using information of email, password, name and contact
   * 
   * @author Atreyi Srivastava
   * @param evt
   */
  private void registerGuideButtonActionPerformed(ActionEvent evt) {
    error = "";
    String email = guideRegisterEmailTextField.getText();
    if (email.isEmpty()) {
      error += "Enter an email!";
    }
    String password = guideRegisterPasswordTextField.getText();
    if (password.isEmpty()) {
      error += "Enter a password!";
    }
    String name = guideRegisterNameTextField.getText();
    if (name.isEmpty()) {
      error += "Enter a name!";
    }

    String[] parsedName = name.split(" ");
    if (parsedName.length != 2) {
      error += "Please enter only the first and last name!";
    } else if (!firstName(parsedName[0]) || !lastName(parsedName[1])) {
      error += "Please enter a valid name!";
    }

    String emergencyContact = guideRegisterEmergencyContactTextField.getText();
    if (emergencyContact.isEmpty()) {
      error += "Enter an emergency number!";
    }

    if (!validateNumber(emergencyContact)) {
      error += "Please enter a valid number!";
    }

    error = error.trim();
    if (error.isEmpty()) {
      callController(() -> ClimbSafeFeatureSet3Controller.registerGuide(email, password, name,
          emergencyContact));
      if (successStatus == true) {
        infoBox("Guide successfully registered!", "Success");
      }
    }
    currentErrorMessage = errorMessageTab2;
    refreshData(); // updates the visual
  }
  
  /**
  * Adds the Items selected while registering a member
  * @author Atreyi Srivastava
  */

  private void addItemRegisterMemberActionPerformed(ActionEvent evt) {
    error = "";
    var selectedItem = (TOBookableItem) selectItemsRegisterMemberComboBox.getSelectedItem();
    if (selectedItem == null) {
      error = "An item needs to be selected to be added!";
    }
    int itemQuantity = getNumberFromField(quantityRegisterMemberField,
        "The item quantity needs to be a numerical value!");

    error = error.trim();
    if (error.isEmpty()) {
      DefaultTableModel memberItemTable = (DefaultTableModel) itemRegisterMemberTable.getModel();
      memberItemTable.addRow(new Object[] {selectedItem.getName(), itemQuantity});
    }
    currentErrorMessage = errorMessageTab2;
    refreshRegisterMemberItem(); // update visual
  }

  private void confirmSetUpButtonActionPerformed(ActionEvent evt) {
    error = "";

    int nbrOfWeeks = getNumberFromField(setUpNumWeeksTextField,
        "The number of weeks needs to be a numerical value!");

    int priceperGuide = getNumberFromField(setUpPriceGuidePerWeekTextField,
        "The price for a guide needs to be a numerical value!");

    error = error.trim();
    if (error.isEmpty()) {
      callController(() -> ClimbSafeFeatureSet1Controller
          .setup((Date) NMCDatePicker.getModel().getValue(), nbrOfWeeks, priceperGuide));
      if (successStatus == true) {
        infoBox("Set up successful!", "Success");
      }

    }
    currentErrorMessage = errorMessageTab3;
    refreshData();
  }

  
  /**
   * This method deletes the selected guide by calling
   *  the controller method deleteGuide the guide's email.
   * 
   * @author Asma Gandour
   * @param evt
   */
  private void deleteGuideButtonActionPerformed(ActionEvent evt) {
    // clear error message and basic input validation
    error = "";
    var selectedGuide = (TOGuide) selectGuideRemovePageComboBox.getSelectedItem();
    if (selectedGuide == null) {
      error = "Guide needs to be selected to be removed!";
    } else {
      // call the controller
      callController(() -> ClimbSafeFeatureSet1Controller.deleteGuide(selectedGuide.getEmail()));
      if (successStatus == true) {
        infoBox("Guide successfully deleted!", "Success");
      }
    }

    currentErrorMessage = errorMessageTab4;
    // update visuals
    refreshData();
  }

  /**
   * This method deletes the selected member by calling
   *  the controller method deleteMember the member's email.
   * 
   * @author Asma Gandour
   * @param evt
   */
  private void deleteMemberButtonActionPerformed(ActionEvent evt) {
    // clear error message and basic input validation
    error = "";
    var selectedMember = (TOMember) selectMemberRemovePageComboBox.getSelectedItem();
    if (selectedMember == null) {
      error = "Member needs to be selected to be removed!";
    } else {
      // call the controller
      callController(() -> ClimbSafeFeatureSet1Controller.deleteMember(selectedMember.getEmail()));
      if (successStatus == true) {
        infoBox("Member successfully deleted!", "Success");
      }
    }

    currentErrorMessage = errorMessageTab4;
    // update visuals
    refreshData();
  }

  /**
   * This method deletes the selected equipment by calling
   *  the controller method deleteEquipment the equipment's name.
   * 
   * @author Asma Gandour
   * @param evt
   */
  private void deleteEquipmentButtonActionPerformed(ActionEvent evt) {
    // clear error message and basic input validation
    error = "";
    var selectedEquipment = (TOEquipment) selectEquipmentRemovePageComboBox.getSelectedItem();
    if (selectedEquipment == null) {
      error = "Equipment needs to be selected to be removed!";
    } else {
      // call the controller
      callController(
          () -> ClimbSafeFeatureSet6Controller.deleteEquipment(selectedEquipment.getName()));
      if (successStatus == true) {
        infoBox("Equipment successfully deleted!", "Success");
      }
    }
    currentErrorMessage = errorMessageTab4;
    // update visuals
    refreshData();
  }

  private void addEquipmentButtonActionPerformed(ActionEvent evt) {
    error = "";

    String name = nameEquipmentToAddTextField.getText();
    if (name.isEmpty()) {
      error = "Enter a name!";
    }
    int weight = getNumberFromField(weightEquipmentToAddTextField,
        "The weight needs to be a numerical value!");


    int price = getNumberFromField(pricePerWeekEquipmentToAddTextField,
        "The price per week needs to be a numerical value!");

    if (!validateEquipmentName(name)) {
      error = "Enter a valid equipment name!";
    }

    if (error.isEmpty()) {
      callController(() -> ClimbSafeFeatureSet4Controller.addEquipment(name, weight, price));
      if (successStatus == true) {
        infoBox("Equipment successfully added!", "Success");
      }
    }
    currentErrorMessage = errorMessageTab5;
    refreshData();
  }

  /**
   * This method updates the item table in the update panel
   * with the selected item and the quantity entered.
   * 
   * @author Asma Gandour
   * @param evt
   */
  private void addUpdateEquipmentButtonActionPerformed(ActionEvent evt) {
    // clear error message and basic input validation
    error = "";
    var selectedItem = (TOBookableItem) selectNewItemsComboBox.getSelectedItem();
    if (selectedItem == null) {
      error = "An item needs to be selected to be added!";
    }
    int itemQuantity = getNumberFromField(memberItemQuantityTextField,
        "The item quantity needs to be a numerical value!");

    error = error.trim();
    if (error.isEmpty()) {
      DefaultTableModel updateMemberEquipmentModel =
          (DefaultTableModel) updateMemberEquipmentTable.getModel();
      // adding item to table model
      updateMemberEquipmentModel.addRow(new Object[] {selectedItem.getName(), itemQuantity});;

    }

    currentErrorMessage = errorMessageTab1;
    // update visuals
    refreshUpdateMemberItem();
  }

  /**
   * This method updates the selected equipment with
   * the new data if the strings and numbers entered are valid.
   * 
   * @author Asma Gandour
   * @param evt
   */
  private void updateEquipmentButtonActionPerformed(ActionEvent evt) {
    error = "";
    var selectedEquipment = (TOEquipment) selectEquipmentToUpdateComboBox.getSelectedItem();
    if (selectedEquipment == null) {
      error = "An item needs to be selected to be updated!";
    }
    var newName = newEquipmentNameTextField.getText();
    if (newName.isEmpty()) {
      error += "Enter a name";
    }
    int newWeight = getNumberFromField(newEquipmentWeightTextField,
        "The weight needs to be a numerical value!");

    int newPrice = getNumberFromField(newEquipmentPricePerWeekTextField,
        "The price needs to be a numerical value!");
    error = error.trim();
    if (error.isEmpty()) {
      callController(() -> ClimbSafeFeatureSet4Controller
          .updateEquipment(selectedEquipment.getName(), newName, newWeight, newPrice));
      if (successStatus == true) {
        infoBox("Equipment successfully updated!", "Success");
      }
    }

    currentErrorMessage = errorMessageTab5;
    refreshData();
  }

  private void payButtonActionPerformed(ActionEvent evt) {
    error = "";
    var member = (TOMember) selectMemberForPayComboBox.getSelectedItem();
    if (member == null) {
      error = "A member has to be selected first!";
    }
    String code = authorizationCodeTextField.getText();
    if (code.isEmpty()) {
      error += "Authorization code cannot be null!";
    }

    if (error.isEmpty()) {
      callController(() -> AssignmentController.payment(member.getEmail(), code));
      if (successStatus == true) {
        infoBox("Payment successful!", "Success");
      }
    }
    currentErrorMessage = errorMessageTab6;
    refreshData();
  }

  private void finishButtonActionPerformed(ActionEvent evt) {
    error = "";
    var member = (TOMember) selectMemberForPayComboBox.getSelectedItem();
    if (member == null) {
      error = "A member has to be selected first!";
    }
    if (error.isEmpty()) {
      callController(() -> AssignmentController.finishTrip(member.getEmail()));
      if (successStatus == true) {
        infoBox("Trip successfully finished!", "Success");
      }
    }
    currentErrorMessage = errorMessageTab6;
    refreshData();
  }

  private void cancelButtonActionPerformed(ActionEvent evt) {
    error = "";
    var member = (TOMember) selectMemberForPayComboBox.getSelectedItem();
    if (member == null) {
      error = "A member has to be selected first!";
    }

    if (error.isEmpty()) {
      callController(() -> AssignmentController.cancelTrip(member.getEmail()));
      if (successStatus == true) {
        infoBox("Assignment canceled!", "Success");
      }
    }

    currentErrorMessage = errorMessageTab6;
    refreshData();
  }

  private void initiateActionPerformed(ActionEvent evt) {
    callController(() -> AssignmentController.initiateAssignmentForAllMembers());
    currentErrorMessage = errorMessageTab6;
    refreshData();
  }


  /**
   * Starts the trip given a valid week number.
   * 
   * @author 
   * @param evt
   */
  private void startTripsButtonActionPerformed(ActionEvent evt) {
    error = "";
    var selectedItem = selectStartWeekNumberComboBox.getSelectedItem();
    if (selectedItem == null) {
      error = "A week number needs to be selected to start the trip!";
    }
    if (error.isEmpty()) {
      callController(() -> AssignmentController.startTrips((Integer) selectedItem));
      if (successStatus == true) {
        infoBox("Trips correctly initiated!", "Success");
      }
    }
    currentErrorMessage = errorMessageTab6;
    refreshData();
  }


  /**
   * From BtmsPage.java
   * 
   * Returns the number from the given text field if present, 
   * otherwise appends error string to the given message.
   */
  private int getNumberFromField(JTextField field, String errorMessage) {
    try {
      return Integer.parseInt(field.getText());
    } catch (NumberFormatException e) {
      error += errorMessage;
    }
    return 0;
  }


  /**
   * From BtmsPage.java
   * 
   * Calls the controller and sets the error message.
   *
   * @param executable a controller call preceded by "() -> "
   */
  private String callController(Executable executable) {
    try {
      executable.execute();
      successStatus = true;
    } catch (InvalidInputException e) {
      error = e.getMessage();
      return error;
    } catch (Throwable t) {
      t.printStackTrace();
    }
    return "";
  }

  public JFrame getInstance() {
    return this;
  }


  /**
   * @author Alexandre Chiasera
   * !! Code found on stack Overflow -- method unused
   * @param String infoMessage String titleBar
   */
  public void resizeColumnWidth(JTable table) {
    final TableColumnModel columnModel = table.getColumnModel();
    for (int column = 0; column < table.getColumnCount(); column++) {
      int width = 15; // Min width
      for (int row = 0; row < table.getRowCount(); row++) {
        TableCellRenderer renderer = table.getCellRenderer(row, column);
        Component comp = table.prepareRenderer(renderer, row, column);
        width = Math.max(comp.getPreferredSize().width + 1, width);
      }
      if (width > 200)
        width = 200;
      columnModel.getColumn(column).setPreferredWidth(width);
    }
  }

  private static boolean firstName(String firstName) {
    return firstName.matches("[A-Z][a-z]*");
  }

  /**
   * @author Alexandre Chiasera
   * !! regular expression found on stack Overflow
   * @param String infoMessage String titleBar
   */
  // validate last name
  private static boolean lastName(String lastName) {
    return lastName.matches("[A-Z]([ '-]+[a-z])*") || lastName.matches("[A-Z][a-z]*");
  }
  
  /**
   * @author Alexandre Chiasera
   * !! regex found on stack Overflow
   * @param String infoMessage String titleBar
   */
  private static boolean validateNumber(String number) {
    String patterns = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
        + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
        + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";

    Pattern pattern = Pattern.compile(patterns);
    Matcher matcher = pattern.matcher(number);
    return matcher.matches();
  }

  /**
   * @author Alexandre Chiasera
   * 
   * @param String infoMessage String titleBar
   */
  private boolean validateEquipmentName(String name) {
    return name.matches("[A-Z][a-z]*") || name.matches("[a-z]*");
  }


  /**
   * @author Alexandre Chiasera
   * !! Code asked to a friend outside of McGill
   * @param String infoMessage String titleBar
   */
  private static void infoBox(String infoMessage, String titleBar) {
    JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar,
        JOptionPane.INFORMATION_MESSAGE);
  }

  @FunctionalInterface
  interface Executable {
    public void execute() throws Throwable;
  }
}
