package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.pluralsight.HomeScreen.homePage;
import static com.pluralsight.HomeScreen.input;
import static com.pluralsight.Reports.loopReports;

public class Ledger {
    public static void main(String[] args) throws IOException {
        loopLedger();

    }

    public static void loopLedger() throws IOException {
        //creating String variable outside the loop
        String ledgerPage;
        //creating do while loop to make sure application is running until the user wants to quit
        do {
            ledger();
            do {
                ledgerPage = input("Would you like to go back to ledger page? Please enter y/n").toLowerCase();
                 if (!ledgerPage.equals("y") && !ledgerPage.equals("n")) {
                     System.out.println("Invalid input. Please input 'y' or 'n' .");
                 }
            } while (!ledgerPage.equals("y") && !ledgerPage.equals("n")) ;

        } while (ledgerPage.equals("y"));
    }
    // creating a ledger method to provide user with options and save their input and provide actions accordingly
    public static void ledger() throws IOException {
        //saving user input for options to a variable
        String answer = input("""
                You are in the ledger page\
                
                Please pick the options below:\
                
                (A) All - Display all entries. \
                
                (D) Deposits - Display only the entries that are deposits into the account. \
                
                (P) Payments - Display only the negative entries (or payments). \
                
                (R) Reports - Display reports screen. \
                
                (H) Go back to home page.\s""").toLowerCase();
        //creating a bufferedReader
        BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
        //creating a list ledger
        List<AddDeposit> ledger = new ArrayList<>();
        try {
            //reading first line header
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {// checking if a reader returning a non-null value
                String[] ledgerData = line.split("\\|");// creating an arrayList to save each line that is split into columns
                //creating object with parsed data
                AddDeposit a = new AddDeposit(LocalDate.parse(ledgerData[0]),
                        LocalTime.parse(ledgerData[1]),
                        ledgerData[2],
                        ledgerData[3],
                        Double.parseDouble(ledgerData[4]));
                //adding object to reports list
                ledger.add(a);
            }
            // handling different user inputs
            switch (answer) {
                case "a"://all transactions
                    System.out.println("All transactions: \n");
                    for (AddDeposit a : ledger) {
                        System.out.println(a.toCsvLine());
                    }
                    break;
                case "d":// all deposits
                    System.out.println("Deposits: \n");
                    for (AddDeposit a : ledger) {
                        if (a.getAmount() > 0) {
                            System.out.println(a.toCsvLine());
                        }
                    }
                    break;
                case "p":// all payments
                    System.out.println("Payments: \n");
                    for (AddDeposit a : ledger) {
                        if (a.getAmount() < 0) {
                            System.out.println(a.toCsvLine());
                        }
                    }
                    break;
                case "r":// takes to reports page
                    System.out.println("Taking you to Reports page...");
                    loopReports();
                    break;
                case"h":// take back to home page
                    System.out.println("Going back to home page...");
                    homePage();
                    break;
                default:// prompts user to input correct input
                    System.out.println("Invalid option selected. Please enter a, d, p, r, or h. ");
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error reading file " + e.getMessage());
        }
        reader.close();//ensure reader is closed
    }
}
