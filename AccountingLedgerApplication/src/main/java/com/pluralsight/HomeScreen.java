package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomeScreen {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        //userOptions();
        addDeposit();

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
        String answer = userOptions();
//        LocalDate date = LocalDate.parse(input("Please "))
//        AddDeposit deposit = new AddDeposit();
        if (!answer.equalsIgnoreCase("D")){
            return;
        }

        List<AddDeposit> addDeposits = new ArrayList<>();

        LocalDate date = LocalDate.parse(input("Please enter the date in YYYY-MM-DD format. "));
        LocalTime time = LocalTime.parse(input("Please enter the time in HH:mm format "));
        String description = input("Please enter the description of item. ");
        String vendor = input("Please enter the vendor name. ");
        double amount = Double.parseDouble(input("Please enter the amount paid. "));
        AddDeposit deposit = new AddDeposit(date, time, description, vendor, amount);
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
            //}
        }
    }
}
