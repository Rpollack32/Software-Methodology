package com.example.project3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.InputMismatchException;

/**
 * Controller class for GUI tution manager
 * @author Ryan Pollack, Michael Kang
 */

public class MainController {

    private final int MIN_CREDS = 3;
    private final int MAX_CREDS = 24;
    private final int MIN_FULLTIME_CREDS = 12;
    private final int MAX_FINAID_AMOUNT = 10000;
    private final int INVALID = -1;

    @FXML
    private RadioButton cs;
    @FXML
    private RadioButton cs2;
    @FXML
    private RadioButton ee;
    @FXML
    private RadioButton ee2;
    @FXML
    private RadioButton me;
    @FXML
    private RadioButton me2;
    @FXML
    private RadioButton it;
    @FXML
    private RadioButton it2;
    @FXML
    private RadioButton ba;
    @FXML
    private RadioButton ba2;
    @FXML
    private TextField name;
    @FXML
    private TextField name2;
    @FXML
    private TextField credits;
    @FXML
    private TextField finAid;
    @FXML
    private RadioButton international;
    @FXML
    private RadioButton new_york;
    @FXML
    private RadioButton connecticut;
    @FXML
    private RadioButton non_resident;
    @FXML
    private TextArea output;
    @FXML
    private TextArea output2;
    @FXML
    private TextArea output3;
    @FXML
    private TextField payments;
    @FXML
    private DatePicker paymentDate;
    @FXML
    private RadioButton resident;
    @FXML
    private CheckBox studyAbroad;
    @FXML
    private RadioButton tristate;
    @FXML
    private TextField tuitionOutput;

    private final Roster roster;

    /**
     * The Constructor
     */
    public MainController()
    {
        roster = new Roster();
    }
    
    /**
     * If international selected, enables study abroad option
     * @param event - handled event
     */
    @FXML
    void enableStudyAbroadOption(ActionEvent event) 
    {
        new_york.setDisable(true);
        new_york.setSelected(false);

        connecticut.setDisable(true);
        connecticut.setSelected(false);

        studyAbroad.setDisable(false);
    }

    /**
     * If tristate selected, enable tristate options
     * @param event - handled event
     */
    @FXML
    void enableTristateOptions(ActionEvent event) 
    {
        studyAbroad.setSelected(false);
        studyAbroad.setDisable(true);

        new_york.setDisable(false);
        connecticut.setDisable(false);
    }

    /**
     * Disables international and tristate options
     */
    void disableResidentButtons(){
        tristate.setDisable(true);
        tristate.setSelected(false);

        new_york.setDisable(true);
        new_york.setSelected(false);

        connecticut.setDisable(true);
        connecticut.setSelected(false);

        international.setDisable(true);
        international.setSelected(false);

        studyAbroad.setDisable(true);
        studyAbroad.setSelected(false);
    }

    /**
     * Sets resident buttons to default and disables international and
     * tristate options
     * @param event - handled events
     */
    @FXML
    void setResidentStatus(ActionEvent event) {
        disableResidentButtons();

        if(non_resident.isSelected()){
            tristate.setDisable(false);
            international.setDisable(false);
        }
    }

    /**
     * Gets major that is selected
     * @return - the major selected
     * @throws InputMismatchException if major not selected
     */
    private Major getMajor() throws InputMismatchException 
    {
        if(cs.isSelected())
        {
        	return Major.CS;
        }
        else if(ee.isSelected())
        {
            return Major.EE;
    	}
        else if(me.isSelected())
        {
            return Major.ME;
    	}
        else if(it.isSelected()) 
        {
            return Major.IT;
        }
        else if(ba.isSelected())
        {
            return Major.BA;
        }
        else 
        {
            throw new InputMismatchException(); 
        }
    }

    /**
     * Gets major that is selected in payments tab
     * @return - the major selected
     * @throws InputMismatchException if major not selected
     */
    private Major getMajor2() throws InputMismatchException 
    {
        if(cs2.isSelected())
        {
        	return Major.CS;
        }
        else if(ee2.isSelected())
        {
            return Major.EE;
    	}
        else if(me2.isSelected())
        {
            return Major.ME;
    	}
        else if(it2.isSelected()) 
        {
            return Major.IT;
        }
        else if(ba2.isSelected())
        {
            return Major.BA;
        }
        else 
        {
            throw new InputMismatchException(); 
        }
    }

    /**
     * Builds a profile of a student
     * @return student profile
     */
    private Profile buildStudentProfile()
    {      
        String studentName = name.getText();
        
        if(studentName.isEmpty())
        {
            output.appendText("Student name field is empty. \n");
            return null;
        }

        try
        {
            Major major = getMajor();
            return new Profile(studentName, major);
        }
        catch (InputMismatchException majorNotSelected)
        {
            output.appendText("Student major has not been selected \n");
        }

        return null;
    }

    /**
     * Builds a profile of a student for payments tab
     * @return student profile
     */
    private Profile buildStudentProfile2()
    {

        String studentName = name2.getText();
        
        if(studentName.isEmpty())
        {
            output2.appendText("Student name field is empty. \n");
            return null;
        }
    
        try
        {
            Major major = getMajor2();
            return new Profile(studentName, major);
        }
        catch (InputMismatchException majorNotSelected)
        {
            output2.appendText("Student major has not been selected \n");
        }

        return null;
    }

    /**
     * Adds resident student to the roster
     * @param profile - student profile
     */
    public void addResidentStudent(Profile profile)
    {
        int creditHours = MIN_CREDS;

        try 
        {
            creditHours = Integer.parseInt(credits.getText());
        }
        catch(NumberFormatException badInput)
        {
            output.appendText("Invalid credit hours. \n");
            return;
        }

        if(creditHours < 0)
        {
            output.appendText("Credit hours cannot be negative. \n");
            return;
        }
        else if(creditHours < MIN_CREDS)
        {
            output.appendText("Minimum credit hours is 3. \n");
            return;
        }
        else if(creditHours > MAX_CREDS)
        { 
            output.appendText("Credit hours exceed the maximum 24. \n");
            return;
        }

        Resident residentStudent = new Resident(profile, creditHours);
        boolean studentAdded = roster.add(residentStudent);

        if(studentAdded)
        {
            output.appendText("Student added. \n");
        }
        else
        {
            output.appendText("Student is already in the roster. \n");
        }
    }

    /**
     * Adds nonresident student to the roster
     * @param profile - student profile
     */
    public void addNonResidentStudent(Profile profile)
    {
        int creditHours = MIN_CREDS;

        try 
        {
            creditHours = Integer.parseInt(credits.getText());
        }
        catch(NumberFormatException badInput)
        {
            output.appendText("Invalid credit hours. \n");
            return;
        }

        if(creditHours < 0)
        { 
            output.appendText("Credit hours cannot be negative. \n");
            return;
        }
        else if(creditHours < MIN_CREDS)
        { 
            output.appendText("Minimum credit hours is 3. \n");
            return;
        }
        else if(creditHours > MAX_CREDS)
        { 
            output.appendText("Credit hours exceed the maximum 24. \n");
            return;
        }

        NonResident nonResidentStudent = new NonResident(profile, creditHours);
        boolean studentAdded = roster.add(nonResidentStudent);

        if(studentAdded)
        {
            output.appendText("Student added. \n");
        }else
        {
            output.appendText("Student is already in the roster. \n");
        }
    }

    /**
     * Adds international student to the roster
     * @param profile - student profile
     */
    public void addInternationalStudent(Profile profile)
    {
        int creditHours = MIN_CREDS;
        boolean isStudyAbroad = studyAbroad.isSelected();

        try 
        {
            creditHours = Integer.parseInt(credits.getText());
        }
        catch(NumberFormatException badInput)
        {
            output.appendText("Invalid credit hours. \n");
            return;
        }

        if(creditHours < 0)
        { 
            output.appendText("Credit hours cannot be negative. \n");
            return;
        }
        else if(creditHours < MIN_CREDS)
        {
            output.appendText("Minimum credit hours is 3. \n");
            return;
        }
        else if(!isStudyAbroad && creditHours > MAX_CREDS)
        { 
            output.appendText("Credit hours exceed the maximum 24. \n");
            return;
        }

        if(!isStudyAbroad && creditHours < MIN_FULLTIME_CREDS)
        { 
            output.appendText("International students must enroll at least 12 credits. \n");
            return;
        }
        else if(isStudyAbroad && creditHours > MIN_FULLTIME_CREDS)
        {
            output.appendText("Credit hours exceed the maximum 12. \n");
            return;
        }

        International internationalStudent = new International(profile, creditHours, isStudyAbroad);
        boolean studentAdded = roster.add(internationalStudent);

        if(studentAdded)
        {
            output.appendText("Student added. \n");
        }
        else
        {
            output.appendText("Student is already in the roster. \n");
        }
    }
    
    /**
     * Adds tristate student to roster
     * @param profile - student profile
     */
    public void addTriStateStudent(Profile profile)
    {
        int creditHours = MIN_CREDS;

        try 
        {
            creditHours = Integer.parseInt(credits.getText());
        }
        catch(NumberFormatException badInput)
        {
            output.appendText("Invalid credit hours. \n");
            return;
        }

        if(creditHours < 0)
        { 
            output.appendText("Credit hours cannot be negative. \n");
            return;
        }
        else if(creditHours < MIN_CREDS)
        { 
            output.appendText("Minimum credit hours is 3. \n");
            return;
        }
        else if(creditHours > MAX_CREDS)
        { 
            output.appendText("Credit hours exceed the maximum 24. \n");
            return;
        }

        String stateCode = "";

        if(connecticut.isSelected()){
            stateCode = "CT";
        }else if(new_york.isSelected()){
            stateCode = "NY";
        }else
        {
            output.appendText("State has not been selected. \n");
            return;
        }

        TriState tristateStudent = new TriState(profile, creditHours, stateCode);
        boolean studentAdded = roster.add(tristateStudent);

        if(studentAdded)
        {
            output.appendText("Student added. \n");
        }
        else
        {
            output.appendText("Student is already in the roster. \n");
        }
    }

    /**
     * Adds student to the roster
     * @param event - handled event
     */
    @FXML
    void addStudent(ActionEvent event) 
    {
        Profile studentProfile = buildStudentProfile();
        
        if(studentProfile == null)
        { 
            return;
        }

        // determining the type of student (international, tristate, etc) and adding profile to respective type
        boolean isResidentStudent = resident.isSelected();
        boolean isNonResidentStudent = non_resident.isSelected() && !tristate.isSelected() && !international.isSelected();
        boolean isTriStateStudent = tristate.isSelected();
        boolean isIntlStudent = international.isSelected();
        
        if(isResidentStudent) 
        {
            addResidentStudent(studentProfile);
        }
        else if(isNonResidentStudent)
        {
            addNonResidentStudent(studentProfile);
        }
        else if(isTriStateStudent)
        {
            addTriStateStudent(studentProfile);
        }
        else if(isIntlStudent)
        {
            addInternationalStudent(studentProfile);
        }
        else
        {
            output.appendText("Student status has not been selected \n");
        }
    }
    
    /**
     * Removes student from roster
     * @param event - handled event
     */
    @FXML
    void removeStudent(ActionEvent event) 
    {
        Profile studentProfile = buildStudentProfile();

        if(studentProfile == null)
        {
            return;
        }

        int creditAmount = 0;
        
        Student studentToRemove = new Student(studentProfile, creditAmount);
        boolean removed = roster.remove(studentToRemove);

        if(removed) 
        {
            output.appendText("Student removed from roster. \n");
        }
        else
        {
            output.appendText("Student is not in the roster. \n");
        }
    }

    /**
     * Calculates tuition for all the students
     * @param event - handled event
     */
    @FXML
    void calculateTuitionDues(ActionEvent event) 
    {
        roster.calculateTuitionForAll();
        output3.appendText("Calculation completed. \n");
    }

    /**
     * Getter for payment amount
     * @return payment amount
     */
    private double getPaymentAmount()
    {
        String paymentInput = payments.getText();
        if(paymentInput.isEmpty())
        {
            output2.appendText("Invalid amount. \n");
            return INVALID;
        }

        try
        {
            double paymentAmount = Double.parseDouble(paymentInput);
            
            if(paymentAmount <= 0)
            {
                output2.appendText("Invalid amount. \n");
                return INVALID;
            }

            return paymentAmount;
        }
        catch(NumberFormatException invalidPaymentAmount)
        {
            output2.appendText("Invalid amount. \n");
            return INVALID;
        }
    }

    /**
     * Getter for date of payment
     * @return payment date
     */
    private Date getPaymentDate()
    {
        if(paymentDate.getValue() == null)
        {
            output2.appendText("Payment date missing. \n");
            return null;
        }

        String rawDate = paymentDate.getValue().toString();
        String year = rawDate.substring(0,4);
        String month = rawDate.substring(5,7);
        String day = rawDate.substring(8);

        String processedDate = month + "/" + day + "/" + year;
        Date paymentDate = new Date(processedDate);

        if(!paymentDate.isValid())
        {
            output2.appendText("Payment date is invalid. \n");
            return null;
        }

        return paymentDate;
    }

    /**
     * Makes payment for student
     * @param event - handled event
     */
    @FXML
    void pay(ActionEvent event) 
    {
        Profile studentProfile = buildStudentProfile2();
        
        if(studentProfile == null) 
        {
            return;
        }
        
        int creditAmount = 0;

        Student studentToPay = new Student(studentProfile, creditAmount);      

        double paymentAmount = getPaymentAmount();
        if(paymentAmount == INVALID)
        {
            return;
        }

        Date paymentDate = getPaymentDate();
        
        if(paymentDate == null) 
        {
            return;
        }
        
        boolean paymentMade = roster.makeStudentPayment(studentToPay, paymentAmount, paymentDate);
        
        if(paymentMade)
        {
            output2.appendText("Payment applied. \n");
        }
        else
        {
            output2.appendText("Invalid amount. \n");
        }
    }

    /**
     * prints roster
     * @param event - handled event
     */
    @FXML
    void printRoster(ActionEvent event) 
    {
        roster.printRoster(output3);
    }
    
    /**
     * prints roster by names of students
     * @param event - handled event
     */
    @FXML
    void printByNames(ActionEvent event) 
    {
        roster.printByNames(output3);
    }

    /**
     * prints roster by payment dates
     * @param event - handled event
     */
    @FXML
    void printByPaymentDates(ActionEvent event) 
    {
        roster.printByPaymentDates(output3);
    }

    /**
     * prints tuition due for a student
     * @param event - handled event
     */
    @FXML
    void printTuitionDue(ActionEvent event) 
    {
        Profile studentProfile = buildStudentProfile();

        if(studentProfile == null){
            return;
        }

        int creditAmount = 0;

        Student studentToCalculate = new Student(studentProfile, creditAmount);
        
        double tuition = roster.calculateTuitionForOne(studentToCalculate);

        if(tuition == -1.0)
        {
            output.appendText("Student is not in the roster. \n");
        }
        else
        {
            tuitionOutput.clear();
            tuitionOutput.appendText(tuition + "");
        }
    }

    /**
     * Sets the financial aid amount
     * @param event - handled event
     */
    @FXML
    void setFinAid(ActionEvent event) 
    {
        Profile studentProfile = buildStudentProfile2();
        
        if(studentProfile == null) 
        {
            return;
        }

        int creditAmount = 0;

        Student studentToSet = new Student(studentProfile, creditAmount);

        try 
        {
            double finAidAmount = Double.parseDouble(finAid.getText());

            if(finAidAmount > MAX_FINAID_AMOUNT || finAidAmount < 0)
            {
                output2.appendText("Invalid amount. \n");
                return;
            }

            String message = roster.setFinancialAid(studentToSet, finAidAmount);
            output2.appendText(message + "\n");
        }
        catch(NumberFormatException invalidFinAid)
        {
            output2.appendText("Missing the amount. \n");
        }
    }
}