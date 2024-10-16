package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.pluralsight.HomeScreen.homePage;
import static com.pluralsight.HomeScreen.input;
import static com.pluralsight.Reports.reports;

public class Ledger {
    public static void main(String[] args) throws IOException {
        ledger();

    }

    public static void ledger() throws IOException {
        String answer = input("You are in the ledger screen" +
                "\nPlease pick the options below:" +
                "\n(A) All - Display all entries. " +
                "\n(D) Deposits - Display only the entries that are deposits into the account. " +
                "\n(P) Payments - Display only the negative entries (or payments). " +
                "\n(R) Reports - Display reports screen. " +
                "\n(H) Go back to home page. ").toLowerCase();

        BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
        List<AddDeposit> ledger = new ArrayList<>();
        try {
            reader.readLine();
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] ledgerData = line.split("\\|");
                AddDeposit a = new AddDeposit(LocalDate.parse(ledgerData[0]),
                        LocalTime.parse(ledgerData[1]),
                        ledgerData[2],
                        ledgerData[3],
                        Double.parseDouble(ledgerData[4]));
                ledger.add(a);
                index++;
            }
            switch (answer) {
                case ("a"):
                    System.out.println("All transactions: ");
                    for (AddDeposit a : ledger) {
                        System.out.println(a.toCsvLine());
                    }
                    break;
                case ("d"):
                    System.out.println("Deposits: ");
                    for (AddDeposit a : ledger) {
                        if (a.getAmount() > 0) {
                            System.out.println(a.toCsvLine());
                        }
                    }
                    break;
                case ("p"):
                    System.out.println("Payments: ");
                    for (AddDeposit a : ledger) {
                        if (a.getAmount() < 0) {
                            System.out.println(a.toCsvLine());
                        }
                    }
                    break;
                case ("r"):
                    System.out.println("Taking you to Reports page...");
                    reports();

                    break;
                case("h"):
                    System.out.println("Going back to home page...");
                    homePage();
                    break;
                default:
                    System.out.println("Invalid option selected. Please enter a, d, p, r, or h. ");
                    break;
            }
        } catch (IOException e) {
            e.getMessage();
        }
        reader.close();
    }
}