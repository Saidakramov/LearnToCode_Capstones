package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class HomeScreen {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        //userOptions();
        addDeposit();
        //System.out.println(randomDate());
        //System.out.println(randomTime());

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
        //saving users input to an answer variable
        String answer = userOptions();

        if (!answer.equalsIgnoreCase("D")){
            return;
        }
        //creating a list
        List<AddDeposit> addDeposits = new ArrayList<>();
        //creating a variables using user input
        LocalDate date = randomDate();
        //LocalDate date = LocalDate.parse(input("Please enter the date in YYYY-MM-DD format. "));
        LocalTime time = randomTime();
        //LocalTime time = LocalTime.parse(input("Please enter the time in HH:mm:ss format "));
        String description = input("Please enter the description of item. ");
        String vendor = input("Please enter the vendor name. ");
        double amount = Double.parseDouble(input("Please enter the amount paid. "));
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
