package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static com.pluralsight.Ledger.loopLedger;

public class HomeScreen {
    //creating a public scanner so it can be accessed throughout the applications
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
       homePage();
    }

    public static void homePage() throws IOException {
        //create a while loop to return a user to main page
        while (true){
            // creating a variable answer to save user input
            String answer = userOptions();
            // creating if else statements to check for user input options and provide actions accordingly
            if (answer.equalsIgnoreCase("d")){
                addDeposit();
            } else if (answer.equalsIgnoreCase("p")) {
                makePayment();
            } else if (answer.equalsIgnoreCase("l")) {
                loopLedger();
            } else if (answer.equalsIgnoreCase("x")) {
                System.out.println("Exiting the program...");
                System.exit(0);
            }   else {
                System.out.println("Invalid input please enter d,p,l, or x. ");
            }
        }
    }
    // creating input method that takes String as argument and scans the user input using nextLine().
    public static String input(String message){
        System.out.println(message);
        return scanner.nextLine();
    }




    public static String userOptions(){
        //providing user with options and asking for their input

        return input("""
                Please select the options below :\
                
                (D) Add Deposit \
                
                (P) Make Payment (Debit) \
                
                (L) Ledger \
                
                (X) Exit\s""").toLowerCase();
    }
    public static void writer(AddDeposit transaction){
        List<AddDeposit> addDeposits = new ArrayList<>();
        try{
            //create a BufferedWriter
            BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true));
            //if file is empty create a header
            File file = new File("transactions.csv");
            if (file.length() == 0) {
                writer.write("date|time|description|vendor|amount\n");
            }
            //write deposit info to the file
            writer.write(transaction.toCsvLine());
            // closing a writer
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addDeposit() {
        //creating a list
        List<AddDeposit> addDeposits = new ArrayList<>();
        //assigning randomDate() to date variable
        LocalDate date = randomDate();
        //assigning randomTime() to time variable
        LocalTime time = randomTime();
        //saving user inputs to variables
        String description = input("Please enter the description of the deposit. ");
        String vendor = input("Please enter the vendor name. ");
        double amount = Double.parseDouble(input("Please enter the amount deposited. "));
        //constructing AddDeposit object
        AddDeposit deposit = new AddDeposit(date, time, description, vendor, amount);
        //adding object to a list
        addDeposits.add(deposit);
        //writing to csv
        writer(deposit);



    }

    public static void makePayment() {
        //creating a list
        List<AddDeposit> addDeposits = new ArrayList<>();
        //assigning randomDate() to date variable
        LocalDate date = randomDate();
        //assigning randomTime() to time variable
        LocalTime time = randomTime();
        //saving user inputs to variables
        String description = input("Please enter the description of the item. ");
        String vendor = input("Please enter the vendor name. ");
        double amount = Double.parseDouble(input("Please enter the amount paid. "));
        //constructing AddDeposit object
        AddDeposit payment = new AddDeposit(date, time, description, vendor, amount * -1);
        //adding object to a list
        addDeposits.add(payment);
        writer(payment);

    }


    //creating a randomDate() method to generate random dates
    public static LocalDate randomDate(){
        //creating startDate to include a previous year
        long startDate = LocalDate.now().minusYears(1).toEpochDay();
        long endDate = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startDate, endDate);
        return LocalDate.ofEpochDay(randomDay);
    }
    //creating randomTime() method to generate random times
    public  static LocalTime randomTime(){
        Random random = new Random();
        int hour = random.nextInt(12);
        int minute = random.nextInt(60);
        int second = random.nextInt(60);
        //int nano = random.nextInt(0);

        return LocalTime.of(hour,minute,second);
    }
}
