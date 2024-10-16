package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import static com.pluralsight.Ledger.ledger;

public class HomeScreen {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
       homePage();
    }

    public static void homePage() throws IOException {
        String answer = userOptions();

        if (answer.equalsIgnoreCase("d")){
            addDeposit();
        } else if (answer.equalsIgnoreCase("p")) {
            makePayment();
        } else if (answer.equalsIgnoreCase("l")) {
            ledger();
        } else if (answer.equalsIgnoreCase("x")) {
            System.out.println("Exiting the program...");
            System.exit(0);
        }   else {
            System.out.println("Invalid input please enter d,p,l, or x. ");
        }
    }

    public static String input(String message){
        System.out.println(message);
        return scanner.nextLine();
    }




    public static String userOptions(){
        String options = input("Please select the options below :" +
                "\n(D) Add Deposit " +
                "\n(P) Make Payment (Debit) " +
                "\n(L) Ledger " +
                "\n(X) Exit ").toLowerCase();

        return options;
    }

    public static void addDeposit() throws IOException {
        //creating a list
        List<AddDeposit> addDeposits = new ArrayList<>();
        //creating a variables using user input
        LocalDate date = randomDate();
        //LocalDate date = LocalDate.parse(input("Please enter the date in YYYY-MM-DD format. "));
        LocalTime time = randomTime();
        //LocalTime time = LocalTime.parse(input("Please enter the time in HH:mm:ss format "));
        String description = input("Please enter the description of the deposit. ");
        String vendor = input("Please enter the vendor name. ");
        double amount = Double.parseDouble(input("Please enter the amount deposited. "));
        //constructing AddDeposit object
        AddDeposit deposit = new AddDeposit(date, time, description, vendor, amount);
        //adding object to a list
        addDeposits.add(deposit);

            try{
                //create a BufferedWriter
                BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true));
                //if file is empty create a header
                File file = new File("transactions.csv");
                if (file.length() == 0) {
                    writer.write("date|time|description|vendor|amount\n");
                }
                //write deposit info to the file
                for (AddDeposit a : addDeposits) {
                    writer.write(a.toCsvLine());
                }

                writer.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
        }

    }

    public static void makePayment() throws IOException {
        //creating a list
        List<AddDeposit> addDeposits = new ArrayList<>();
        //creating a variables using user input
        LocalDate date = randomDate();
        //LocalDate date = LocalDate.parse(input("Please enter the date in YYYY-MM-DD format. "));
        LocalTime time = randomTime();
        //LocalTime time = LocalTime.parse(input("Please enter the time in HH:mm:ss format "));
        String description = input("Please enter the description of the item. ");
        String vendor = input("Please enter the vendor name. ");
        double amount = Double.parseDouble(input("Please enter the amount paid. "));
        //constructing AddDeposit object
        AddDeposit deposit = new AddDeposit(date, time, description, vendor, amount * -1);
        //adding object to a list
        addDeposits.add(deposit);

        try{
            //create a BufferedWriter
            BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true));
            //if file is empty create a header
            File file = new File("transactions.csv");
            if (file.length() == 0) {
                writer.write("date|time|description|vendor|amount\n");
            }
            //write deposit info to the file
            for (AddDeposit a : addDeposits) {
                writer.write(a.toCsvLine());
            }

            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }



    public static LocalDate randomDate(){
        long startDate = LocalDate.now().minusYears(1).toEpochDay();
        long endDate = LocalDate.now().toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startDate, endDate);
        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
        return  randomDate;
    }

    public  static LocalTime randomTime(){
        Random random = new Random();
        int hour = random.nextInt(12);
        int minute = random.nextInt(60);
        int second = random.nextInt(60);
        //int nano = random.nextInt(0);

        return LocalTime.of(hour,minute,second);
    }
}
