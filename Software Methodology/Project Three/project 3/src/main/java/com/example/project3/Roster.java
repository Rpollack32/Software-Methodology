package com.example.project3;

import javafx.scene.control.TextArea;

/**
  * This is the roster class, which is a growable array list data structure of students.
 * It includes methods that manipulate the array and its students. 
 * @author Ryan Pollack, Michael Kang
 */

public class Roster 
{
    private Student[] roster;
    private int size; 
    private final int NOT_FOUND = -1;

    /**
     * This is the roster constructor, which creates a new roster and sets its length to 4.
     */
    public Roster() 
    {
        int initialCapacity = 4;
        roster = new Student[initialCapacity];
        size = 0;
    }

    /**
	 * This is the find method, which finds and returns the index of an album if it is in the array.
	 * If the student cannot be found, return NOT_FOUND
	 * @param a student
	 * @return the index of a student
     */
    private int find(Student student) 
    {
        Profile profileToFind = student.getProfile();

        for(int i = 0; i < size; i++) 
        {
            System.out.println(roster[i].getProfile());
            Profile currentStudentProfile = roster[i].getProfile();

            if(currentStudentProfile.equals(profileToFind)) 
            {
                System.out.println("-------");
                System.out.println(roster[i].getProfile());
                return i;
            }
        }

        return NOT_FOUND; 
    }

    /**
     * This is the grow method, which increases the size of the array by 4
     */
    private void grow() 
    {
        int growth = 4;
        Student[] newRoster = new Student[roster.length + growth]; // growing roster

        for(int i = 0; i < size; i++) {
            newRoster[i] = roster[i];
        }

        this.roster = newRoster;
    }
    
    /**
     * Adds a student to the roster
     * @param student - the student to add
     * @return true if the student was added, false if not
     */
    public boolean add(Student student) {
        boolean rosterHasStudent = this.find(student) != NOT_FOUND;

        // if student is already in roster, they should not be added
        if(rosterHasStudent){
            return false;
        }

        // if not enough space, grow
        if(size >= roster.length){
            this.grow();
        }

        // adding new student
        roster[size] = student;
        size++;

        return true;
    }

    /**
     * Removes a student from the roster
     * @param student - the student to remove
     * @return true if the student was removed, false if not
     */
    public boolean remove(Student student) {
        int studentIndex = this.find(student);
        boolean isNotInRoster = studentIndex == NOT_FOUND;

        if(isNotInRoster){
            return false;
        }

        roster[studentIndex] = null;
        size--;

        this.closesGap();

        return true;
    }

    /**
     * Cleans up any gaps in the roster caused by removing a student
     */
    private void closesGap() {
        Student[] newRoster = new Student[roster.length]; // growing roster

        // copying students to new roster
        int newRosterIndex = 0;
        for(Student student: roster) {
            if(student == null){
                continue;
            }

            newRoster[newRosterIndex] = student;
            newRosterIndex++;
        }

        this.roster = newRoster;
    }

    /**
     * Sorts the roster by names
     */
    private void sorterForNames() {
        int lesserNameIndex;

        for(int i = 0; i < size - 1; i++)
        {
            lesserNameIndex = i;

            for(int j = i+1; j < size; j++)
            {
                String currentStudentName = this.roster[j].getProfile().getName();
                String lesserStudentName = this.roster[lesserNameIndex].getProfile().getName();

                if(currentStudentName.compareTo(lesserStudentName) < 0)
                {
                    lesserNameIndex = j;
                }
            }

            Student temp = this.roster[i];
            this.roster[i] = this.roster[lesserNameIndex];
            this.roster[lesserNameIndex] = temp;
        }
    }

    /**
     * Sorts collection based on payment dates
     */
    private Student[] sorterForPaymentDates() 
    {
        Student[] sortedRoster = new Student[size];

        int index = 0;
        for(int i = 0; i < size; i++){
            if(this.roster[i] == null || this.roster[i].lastPaymentDate == null){
                continue;
            }

            sortedRoster[index] = this.roster[i];
            index++;
        }

        int earlierPaymentDateIndex;

        // selection sort
        for(int i = 0; i < size - 1; i++){
            if(sortedRoster[i] == null){
                break;
            }

            earlierPaymentDateIndex = i;

            for(int j = i+1; j < size; j++){
                if(sortedRoster[j] == null){
                    break;
                }

                Date currentStudentPaymentDate = sortedRoster[j].lastPaymentDate;
                Date earlierStudentPaymentDate = sortedRoster[earlierPaymentDateIndex].lastPaymentDate;

                if(currentStudentPaymentDate.compareTo(earlierStudentPaymentDate) < 0){
                    earlierPaymentDateIndex = j;
                }
            }

            // swapping two albums to put them in order
            Student temp = sortedRoster[i];
            sortedRoster[i] = sortedRoster[earlierPaymentDateIndex];
            sortedRoster[earlierPaymentDateIndex] = temp;
        }

        return sortedRoster;
    }

    /**
     * Calculates tuition for one student
     * @param student - student to calculate for
     * @return the student's tuition
     */
    public double calculateTuitionForOne(Student student)
    {
        int studentIndex = this.find(student);
        if(studentIndex == NOT_FOUND)
        {
            return -1;
        }

        this.roster[studentIndex].tuitionDue();
        return this.roster[studentIndex].tuition;
    }

    /**
     * Calculates tuition for all students
     */
    public void calculateTuitionForAll() 
    {
        for(int i = 0; i < size; i++){
            this.roster[i].tuitionDue();
        }
    }

    /**
     * Makes a payment for  given student
     * @param student - the student
     * @return true if payment is above possible amount, false otherwise
     */
    public boolean makeStudentPayment(Student student, double paymentAmount, Date paymentDate) 
    {
        int studentIndex = this.find(student);
        if(studentIndex == NOT_FOUND){
            return false;
        }

        Student studentInRoster = this.roster[studentIndex];

        if(paymentAmount > studentInRoster.getBalance())
        {
            return false;
        }

        studentInRoster.makePayment(paymentAmount, paymentDate);
        return true;
    }

    /**
     * Sets study abroad status of a student
     * @param student - the student
     * @return true if student was found to be studying abroad, false if not
     */
    public boolean setStudyAbroadStatus(Student student) {
        int studentIndex = this.find(student);

        if(studentIndex == NOT_FOUND){
            return false;
        }

        if(this.roster[studentIndex] instanceof International){
            ((International) this.roster[studentIndex]).setStudyingAbroad();
            return true;
        }

        return false;
    }

    /**
     * Sets amount of financial aid to student
     * @param student - the student
     * @param finAidAmount - the financial aid 
     * @return message saying whether the financial aid was given
     */
    public String setFinancialAid(Student student, double finAidAmount) 
    {
        int studentIndex = this.find(student);

        if(studentIndex == NOT_FOUND)
        {
            return "Student not in the roster.";
        }
        else if(!(this.roster[studentIndex] instanceof Resident))
        {
            return "Not a resident student.";
        }

        int fullTimeCredits = 12;

        if(this.roster[studentIndex].credits < fullTimeCredits)
        {
            return "Part-time student doesn't qualify for the award.";
        }
        else if(this.roster[studentIndex].finAid > 0)
        {
            return "Awarded once already.";
        }

        this.roster[studentIndex].setFinAid(finAidAmount);
        
        return "Tuition updated.";
    }

    /**
     * Prints the roster plain
     */
    public void printRoster(TextArea textArea) 
    {
        textArea.clear();

        if(this.size == 0) 
        {
            textArea.appendText("Student roster is empty! \n");
            return;
        }

        textArea.appendText("* list of students in the roster ** \n");

        for(int i = 0; i < size; i++){
            textArea.appendText(this.roster[i].toString() + "\n");
        }

        textArea.appendText("* end of roster ** \n");
    }

    /**
     * Prints roster in aphabetical order by nane
     */
    public void printByNames(TextArea textArea) 
    {
        textArea.clear();

        if(this.size == 0) 
        {
            textArea.appendText("Student roster is empty! \n");
            return;
        }

        this.sorterForNames();

        textArea.appendText("* list of students ordered by name ** \n");

        for(int i = 0; i < size; i++)
        {
            textArea.appendText(this.roster[i].toString() + "\n");
        }

        textArea.appendText("* end of roster ** \n");
    }

    /**
     * Prints roster by payment dates
     */
    public void printByPaymentDates(TextArea textArea) 
    {
        textArea.clear();

        if(this.size == 0) 
        {
            textArea.appendText("Student roster is empty! \n");
            return;
        }

        Student[] sortedRoster = this.sorterForPaymentDates();

        textArea.appendText("* list of students made payments ordered by payment date ** \n");

        for(int i = 0; i < size; i++)
        {
            if(sortedRoster[i] == null)
            {
                break;
            }
            textArea.appendText(this.roster[i].toString() + "\n");
        }

        textArea.appendText("* end of roster ** \n");
    }
}