package ca.mcgill.ecse.climbsafe.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilDateModel;

import ca.mcgill.ecse.climbsafe.view.DateLabelFormatter;
import ca.mcgill.ecse.climbsafe.application.ClimbSafeApplication;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.Map;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import ca.mcgill.ecse.climbsafe.controller.ClimbSafeFeatureSet1Controller;
import ca.mcgill.ecse.climbsafe.controller.InvalidInputException;
import ca.mcgill.ecse.climbsafe.controller.TOGuide;
import ca.mcgill.ecse.climbsafe.controller.TOMember;
import ca.mcgill.ecse.climbsafe.controller.TOBookableItem;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Component;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

public class ClimbSafeMainPage extends JFrame {
  private static final long serialVersionUID = -4426310869335015542L;
  // tabs
  JTabbedPane tabbedPane = new JTabbedPane();

  //All text fields are listed here
  private String error = "";
  private JTextField newMemberEmailTextField;
  private JTextField newMemberEmergencyNumberTextField;
  private JTextField newMemberPasswordTextField;
  private JTextField newMemberNameTextField;
  private JTextField memberItemQuantityTextField;
  private JTextField memberRegisterEmailTextField;
  private JTextField memberRegisterPasswordTextField;
  private JTextField memberRegisterNameTextField;
  private JTextField memberRegisterEmergencyContactTextField;
  private JTextField guideRegisterPasswordTextField;
  private JTextField guideRegisterEmailTextField;
  private JTextField guideRegisterNameTextField;
  private JTextField guideRegisterEmergencyContactTextField;
  private JTextField setUpStartAnchorDateTextField;
  private JTextField setUpNumWeeksTextField;
  private JDatePickerImpl NMCDatePicker;  
  private JTextField setUpPriceGuidePerWeekTextField;
  private JTextField newGuideEmailTextField;
  private JTextField newGuideEmergencyNumberTextField;
  private JTextField newGuideNameTextField;
  private JTextField nameEquipmentToAddTextField;
  private JTextField weightEquipmentToAddTextField;
  private JTextField pricePerWeekEquipmentToAddTextField;
  private JTextField newEquipmentNameTextField;
  private JTextField newEquipmentWeightTextField;
  private JTextField newEquipmentPricePerWeekTextField;
  private JTextField authorizationCodeTextField;
  
  //FIRST TAB BELOW (except textFields) -- UPDATE PAGE
  private JTabbedPane tabbedPane_1;
  private JPanel panel;
  private JLabel selectMemberToUpdateLabel;
  private JComboBox selectMemberToUpdateComboBox;
  private JLabel newMemberEmailLabel;
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
  private JLabel newGuideEmailLabel;
  private JComboBox selectGuideToUpdateComboBox;
  private JLabel newGuideEmergencyNumberLabel;
  private JLabel newGuideNameLabel;
  private JButton updateGuideButton;
  private JLabel background;
  
  //SECOND TAB BELOW (except text fields) -- REGISTER PAGE
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
  private JLabel backgroundRegisterPage;
  
  //THIRD TAB BELOW -- SET UP NMC INFO
  private JPanel panel_2;
  private JLabel setUpStartdateLabel;
  private JLabel setUpNumWeeksLabel;
  private JLabel setUpPriceGuidePerWeekLabel;
  private JButton confirmSetUpButton;
  private JLabel setUpNMCLabel;
  private JLabel backgroundSetUpPage;
  private ImageIcon backgroundImageSetUpPage;
  
  //FOURTH TAB BELOW -- REMOVE USER OR EQUIPMENT
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
  
  //FIFTH TAB BELOW -- ADD OR UPDATE EQUIPMENT
  private JPanel panel_4;
  private JLabel nameEquipmentToAddLabel;
  JLabel weightEquipmentToAddLabel;
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
  
  //SIXTH TAB BELOW -- ASSIGNMENT AND PAYMENT
  private JPanel panel_5;
  private JLabel selectMemberForPayLabel;
  private JComboBox selectMemberForPayComboBox ;
  private JButton payForTheTripButton;
  private JButton finishTheTripButton;
  private JButton cancelTheTripButton ;
  private JLabel autorizationCodeLabel;
  private JLabel payFinishOrCancelTripLabel;
  private JLabel initiateAssignmentsLabel;
  private JButton initiateAssignmentsForAllMembersButton;
  private JLabel selectStartWeekNumberLabel;
  private JComboBox selectStartWeekNumberComboBox;
  private JLabel startTripsLabel;
  private JButton startAllTripsForThisWeekLabel;
  private JLabel backgroundAssignmentPage;
  
  private static final Map<String, String> DATE_PROPS = Map.of(
	      "text.today", "Today",
	      "text.month", "Month",
	      "text.year", "Year");


  public ClimbSafeMainPage() {
	  initComponents();
  }

  private void initComponents() {
	  setResizable(false);
	  
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
	  	
	  	selectMemberToUpdateLabel = new JLabel("Select member: ");
	  	selectMemberToUpdateLabel.setForeground(Color.WHITE);
	  	selectMemberToUpdateLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
	  	selectMemberToUpdateLabel.setBounds(72, 273, 132, 27);
	  	panel.add(selectMemberToUpdateLabel);
	  	
	  	selectMemberToUpdateComboBox = new JComboBox(); 
	  	selectMemberToUpdateComboBox.setEditable(true);
	  	selectMemberToUpdateComboBox.setBounds(210, 276, 189, 27);
	  	panel.add(selectMemberToUpdateComboBox);
	  	
	  	newMemberEmailTextField = new JTextField();
	  	newMemberEmailTextField.setBounds(210, 328, 189, 27);
	  	panel.add(newMemberEmailTextField);
	  	newMemberEmailTextField.setColumns(10);
	  	
	  	newMemberEmailLabel = new JLabel("New email: ");
	  	newMemberEmailLabel.setForeground(Color.WHITE);
	  	newMemberEmailLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
	  	newMemberEmailLabel.setBounds(111, 325, 93, 27);
	  	panel.add(newMemberEmailLabel);
	  	
	  	newMemberEmergencyNumberLabel = new JLabel("New emergency number:");
	  	newMemberEmergencyNumberLabel.setForeground(Color.WHITE);
	  	newMemberEmergencyNumberLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
	  	newMemberEmergencyNumberLabel.setBounds(10, 377, 211, 30);
	  	panel.add(newMemberEmergencyNumberLabel);
	  	
	  	newMemberEmergencyNumberTextField = new JTextField();
	  	newMemberEmergencyNumberTextField.setColumns(10);
	  	newMemberEmergencyNumberTextField.setBounds(210, 380, 189, 27);
	  	panel.add(newMemberEmergencyNumberTextField);
	  	
	  	newMemberPasswordLabel = new JLabel("New password: ");
	  	newMemberPasswordLabel.setForeground(Color.WHITE);
	  	newMemberPasswordLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
	  	newMemberPasswordLabel.setBounds(485, 358, 138, 27);
	  	panel.add(newMemberPasswordLabel);
	  	
	  	newMemberPasswordTextField = new JTextField();
	  	newMemberPasswordTextField.setColumns(10);
	  	newMemberPasswordTextField.setBounds(622, 361, 189, 27);
	  	panel.add(newMemberPasswordTextField);
	  	
	  	newMemberNameLabel = new JLabel("New name: ");
	  	newMemberNameLabel.setForeground(Color.WHITE);
	  	newMemberNameLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
	  	newMemberNameLabel.setBounds(881, 273, 102, 27);
	  	panel.add(newMemberNameLabel);
	  	
	  	checkIfAppliesLabel = new JLabel("Check if applies:");
	  	checkIfAppliesLabel.setForeground(Color.WHITE);
	  	checkIfAppliesLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
	  	checkIfAppliesLabel.setBounds(842, 322, 138, 27);
	  	panel.add(checkIfAppliesLabel);
	  	
	  	newMemberNameTextField = new JTextField();
	  	newMemberNameTextField.setColumns(10);
	  	newMemberNameTextField.setBounds(984, 273, 189, 27);
	  	panel.add(newMemberNameTextField);
	  	
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
	  	selectNewItemsComboBoxUpdatePage.setBounds(57, 440, 147, 27);
	  	panel.add(selectNewItemsComboBoxUpdatePage);
	  	
	  	selectNewItemsComboBox = new JComboBox();
	  	selectNewItemsComboBox.setEditable(true);
	  	selectNewItemsComboBox.setBounds(210, 445, 189, 27);
	  	panel.add(selectNewItemsComboBox);
	  	
	  	memberItemQuantityLabel = new JLabel("Item quantity: ");
	  	memberItemQuantityLabel.setForeground(Color.WHITE);
	  	memberItemQuantityLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
	  	memberItemQuantityLabel.setBounds(494, 441, 124, 27);
	  	panel.add(memberItemQuantityLabel);
	  	
	  	memberItemQuantityTextField = new JTextField();
	  	memberItemQuantityTextField.setColumns(10);
	  	memberItemQuantityTextField.setBounds(623, 445, 189, 27);
	  	panel.add(memberItemQuantityTextField);
	  	
	  	memberAddItemButton = new JButton("Add item");
	  	memberAddItemButton.setContentAreaFilled(false);
	  	memberAddItemButton.setOpaque(true);
	  	memberAddItemButton.setBackground(SystemColor.activeCaption);
	  	memberAddItemButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
	  	memberAddItemButton.setBounds(822, 445, 124, 27);
	  	memberAddItemButton.setContentAreaFilled(false);
	  	memberAddItemButton.setOpaque(true);
	  	panel.add(memberAddItemButton);
	  	
	  	updateMemberButton = new JButton("UPDATE MEMBER");
	  	updateMemberButton.setBackground(new Color(173, 216, 230));
	    updateMemberButton.setContentAreaFilled(false);
	    updateMemberButton.setOpaque(true);
	  	updateMemberButton.setForeground(SystemColor.desktop);
	  	updateMemberButton.setFont(new Font("Myanmar Text", Font.BOLD, 17));
	  	updateMemberButton.setBounds(984, 410, 189, 62);
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
	  	
	  	newGuideEmailLabel = new JLabel("New email: ");
	  	newGuideEmailLabel.setForeground(Color.WHITE);
	  	newGuideEmailLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
	  	newGuideEmailLabel.setBounds(148, 129, 93, 27);
	  	panel.add(newGuideEmailLabel);
	  	
	  	selectGuideToUpdateComboBox = new JComboBox();
	  	selectGuideToUpdateComboBox.setEditable(true);
	  	selectGuideToUpdateComboBox.setBounds(247, 74, 189, 27);
	  	panel.add(selectGuideToUpdateComboBox);
	  	
	  	newGuideEmailTextField = new JTextField();
	  	newGuideEmailTextField.setColumns(10);
	  	newGuideEmailTextField.setBounds(247, 132, 189, 27);
	  	panel.add(newGuideEmailTextField);
	  	
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
	  		public void actionPerformed(ActionEvent e) {
	  		}
	  	});
	  	updateGuideButton.setContentAreaFilled(false);
	  	updateGuideButton.setOpaque(true);
	  	updateGuideButton.setForeground(Color.BLACK);
	  	updateGuideButton.setFont(new Font("Myanmar Text", Font.BOLD, 17));
	  	updateGuideButton.setBackground(new Color(173, 216, 230));
	  	updateGuideButton.setBounds(984, 114, 189, 62);
	  	panel.add(updateGuideButton);
	  	
	  	background = new JLabel("");  
	  	background.setBounds(0, 0, 1206, 509);
	  	ImageIcon backgroundImage = new ImageIcon(this.getClass().getResource("/backgroundImage.png"));
	  	background.setIcon(backgroundImage);
	  	background.setOpaque(true);
	  	panel.add(background);
	  	
	  	panel_1 = new JPanel();
	  	tabbedPane_1.addTab("Register page", null, panel_1, null);
	  	panel_1.setLayout(null);
	  	
	  	memberRegisterEmailLabel = new JLabel("Email:");
	  	memberRegisterEmailLabel.setForeground(Color.WHITE);
	  	memberRegisterEmailLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
	  	memberRegisterEmailLabel.setBounds(116, 64, 62, 27);
	  	panel_1.add(memberRegisterEmailLabel);
	  	
	  	memberRegisterEmailTextField = new JTextField();
	  	memberRegisterEmailTextField.setColumns(10);
	  	memberRegisterEmailTextField.setBounds(188, 67, 333, 27);
	  	panel_1.add(memberRegisterEmailTextField);
	  	
	  	memberRegisterPasswordLabel = new JLabel("Password:");
	  	memberRegisterPasswordLabel.setForeground(Color.WHITE);
	  	memberRegisterPasswordLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
	  	memberRegisterPasswordLabel.setBounds(92, 125, 86, 27);
	  	panel_1.add(memberRegisterPasswordLabel);
	  	
	  	memberRegisterPasswordTextField = new JTextField();
	  	memberRegisterPasswordTextField.setColumns(10);
	  	memberRegisterPasswordTextField.setBounds(188, 128, 333, 27);
	  	panel_1.add(memberRegisterPasswordTextField);
	  	
	  	memberRegisterNameLabel = new JLabel("Name: ");
	  	memberRegisterNameLabel.setForeground(Color.WHITE);
	  	memberRegisterNameLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
	  	memberRegisterNameLabel.setBounds(643, 63, 62, 27);
	  	panel_1.add(memberRegisterNameLabel);
	  	
	  	memberRegisterNameTextField = new JTextField();
	  	memberRegisterNameTextField.setColumns(10);
	  	memberRegisterNameTextField.setBounds(704, 67, 333, 27);
	  	panel_1.add(memberRegisterNameTextField);
	  	
	  	memberRegisterEmergencyContactLabel = new JLabel("Emergency contact: ");
	  	memberRegisterEmergencyContactLabel.setForeground(Color.WHITE);
	  	memberRegisterEmergencyContactLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
	  	memberRegisterEmergencyContactLabel.setBounds(542, 125, 163, 27);
	  	panel_1.add(memberRegisterEmergencyContactLabel);
	  	
	  	memberRegisterEmergencyContactTextField = new JTextField();
	  	memberRegisterEmergencyContactTextField.setColumns(10);
	  	memberRegisterEmergencyContactTextField.setBounds(704, 129, 333, 27);
	  	panel_1.add(memberRegisterEmergencyContactTextField);
	  	
	  	memberRegisterGuideRequiredRdBtn = new JRadioButton("Guide Required");
	  	memberRegisterGuideRequiredRdBtn.setOpaque(false);
	  	memberRegisterGuideRequiredRdBtn.setForeground(Color.WHITE);
	  	memberRegisterGuideRequiredRdBtn.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 17));
	  	memberRegisterGuideRequiredRdBtn.setContentAreaFilled(false);
	  	memberRegisterGuideRequiredRdBtn.setBounds(246, 183, 162, 23);
	  	panel_1.add(memberRegisterGuideRequiredRdBtn);
	  	
	  	memberRegisterHotelRequiredRdBtn = new JRadioButton("Hotel Required");
	  	memberRegisterHotelRequiredRdBtn.setOpaque(false);
	  	memberRegisterHotelRequiredRdBtn.setForeground(Color.WHITE);
	  	memberRegisterHotelRequiredRdBtn.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 17));
	  	memberRegisterHotelRequiredRdBtn.setContentAreaFilled(false);
	  	memberRegisterHotelRequiredRdBtn.setBounds(830, 183, 171, 23);
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
	  	registerMemberBtn.setBounds(503, 189, 234, 36);
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
	  	registerAsMemberLabel.setBounds(463, 11, 300, 27);
	  	panel_1.add(registerAsMemberLabel);
	  	
	  	registerAsGuideLabel = new JLabel("REGISTER AS A GUIDE");
	  	registerAsGuideLabel.setForeground(Color.WHITE);
	  	registerAsGuideLabel.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 23));
	  	registerAsGuideLabel.setBounds(483, 270, 272, 27);
	  	panel_1.add(registerAsGuideLabel);
	  	
	  	JSeparator separator_1 = new JSeparator();
	  	separator_1.setForeground(Color.WHITE);
	  	separator_1.setBounds(10, 246, 1176, 2);
	  	panel_1.add(separator_1);
	  	
	  	backgroundRegisterPage = new JLabel("");  
	  	backgroundRegisterPage.setBounds(0, 0, 1206, 509);
	  	ImageIcon backgroundImageRegisterPage = new ImageIcon(this.getClass().getResource("/backgroundImage.png"));
	  	backgroundRegisterPage.setIcon(backgroundImageRegisterPage);
	  	backgroundRegisterPage.setOpaque(true);
	  	panel_1.add(backgroundRegisterPage);
	  	
	  	panel_2 = new JPanel();
	  	tabbedPane_1.addTab("Set up NMC info", null, panel_2, null);
	  	panel_2.setLayout(null);
	  	
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
	  	selectGuideRemovePageComboBox.setEditable(true);
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
	  	selectMemberRemovePageComboBox.setEditable(true);
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
	  	selectEquipmentRemovePageComboBox.setEditable(true);
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
	  	ImageIcon backgroundImageRemovePage = new ImageIcon(this.getClass().getResource("/backgroundImage.png"));
	  	backgroundRemovePage.setIcon(backgroundImageRemovePage);
	  	backgroundRemovePage.setOpaque(true);
	  	panel_3.add(backgroundRemovePage);
	  	
	  	panel_4 = new JPanel();
	  	tabbedPane_1.addTab("Add or update equipment", null, panel_4, null);
	  	panel_4.setLayout(null);
	  	
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
	  	selectEquipmentToUpdateComboBox.setEditable(true);
	  	selectEquipmentToUpdateComboBox.setBounds(487, 319, 237, 27);
	  	panel_4.add(selectEquipmentToUpdateComboBox);
	  		
	  	backgroundEquipmentPage = new JLabel("");  
	  	backgroundEquipmentPage.setBounds(0, 0, 1206, 509);
	  	ImageIcon backgroundImageEquipmentPage = new ImageIcon(this.getClass().getResource("/backgroundImage.png"));
	  	backgroundEquipmentPage.setIcon(backgroundImageEquipmentPage);
	  	backgroundEquipmentPage.setOpaque(true);
	  	panel_4.add(backgroundEquipmentPage);
	  	
	  	panel_5 = new JPanel();
	  	tabbedPane_1.addTab("Assignment and payment", null, panel_5, null);
	  	panel_5.setLayout(null);
	  	
	  	selectMemberForPayLabel = new JLabel("Select member:");
	  	selectMemberForPayLabel.setForeground(Color.WHITE);
	  	selectMemberForPayLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
	  	selectMemberForPayLabel.setBounds(162, 64, 147, 27);
	  	panel_5.add(selectMemberForPayLabel);
	  	
	  	selectMemberForPayComboBox = new JComboBox();
	  	selectMemberForPayComboBox.setEditable(true);
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
	  	selectStartWeekNumberComboBox.setEditable(true);
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
	  	ImageIcon backgroundImageAssignmentPage = new ImageIcon(this.getClass().getResource("/backgroundImage.png"));
	  	backgroundAssignmentPage.setIcon(backgroundImageAssignmentPage);
	  	backgroundAssignmentPage.setOpaque(true);
	  	panel_5.add(backgroundAssignmentPage);
	  	
	  	var offset = 228;  	//will help set the default size of the window
	     // set window location to be in the center of the screen 
	  	setSize(backgroundImage.getIconWidth(), backgroundImage.getIconHeight()- offset);	  
	    refreshData();
	    setVisible(true);
  }

  private void refreshData() {
    if (error == null || error.isEmpty()) {
      
      // update member fields
      //updateMemberNameField.setText("");
      //updateMemberEmailField.setText("");
      //updateMemberContactField.setText("");
      //updateMemberPasswordField.setText("");
      //updateMemberWeekField.setText("");
      //updateMemberQuantityField.setText("");

    }
  }

  private void deleteGuideButtonActionPerformed(ActionEvent evt) {
    // clear error message
    error = "";

    //callController(() -> ClimbSafeFeatureSet1Controller.deleteGuide(deleteGuideButton.getText()));

    // update visuals
    refreshData();
  }


  private void deleteMemberButtonActionPerformed(ActionEvent evt) {
    // clear error message
    error = "";

    //callController(() -> ClimbSafeFeatureSet1Controller.deleteMember(deleteMemberButton.getText()));

    // update visuals
    refreshData();
  }

  /**
   * Calls the controller and sets the error message.
   *
   * @param executable a controller call preceded by "() -> "
   */
  private String callController(Executable executable) {
    try {
      executable.execute();
    } catch (InvalidInputException e) {
      error = e.getMessage();
      return error;
    } catch (Throwable t) {
      t.printStackTrace();
    }
    return "";
  }

  @FunctionalInterface
  interface Executable {
    public void execute() throws Throwable;
  }
}