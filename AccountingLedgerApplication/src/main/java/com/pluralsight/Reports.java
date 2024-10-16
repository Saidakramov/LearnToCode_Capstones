package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.pluralsight.HomeScreen.input;
import static com.pluralsight.Ledger.ledger;

public class Reports {
    public static void main(String[] args) throws IOException {
    loopReports();
    }

    public static void loopReports() throws IOException {
        //creating String outside the loops
        String reportsPage;
        //creating do while loop to make application running until otherwise user chooses to be so
        do {
            reports();
            do {
                reportsPage = input("Would you like to go back to the reports page? Please enter y/n");
                if (!reportsPage.equals("y") && !reportsPage.equals("n")) {
                    System.out.println("Invalid input. Please input 'y' or 'n' .");
                }
            } while (!reportsPage.equals("y") && !reportsPage.equals("n")) ;

        } while (reportsPage.equals("y"));

    }

    public static void reports() throws IOException {
        //creating a String variable to hold a user input
        String answer = input("You are on the Reports page: " +
                "\nPlease chose the options below: " +
                "\n(1) Month To Date. " +
                "\n(2) Previous Month. " +
                "\n(3) Year To Date. " +
                "\n(4) Previous Year. " +
                "\n(5) Search by Vendor. " +
                "\n(0) Go back to Ledger page. ");
        //creating buff reader
        BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"));
        //creating a list
        List<AddDeposit> reports = new ArrayList<>();
        try {
            reader.readLine();//skip the header line
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] ledgerData = line.split("\\|");
                AddDeposit a = new AddDeposit(LocalDate.parse(ledgerData[0]),
                        LocalTime.parse(ledgerData[1]),
                        ledgerData[2],
                        ledgerData[3],
                        Double.parseDouble(ledgerData[4]));
                reports.add(a);
                index++;
            }
            //handling different options
            switch (answer) {
                case ("1")://if user chose 1 generate MTD report
                    System.out.println("Month To Date Report: ");
                    //getting today's date
                    LocalDate today = LocalDate.now();
                    //getting the first day of current month
                    LocalDate firstDayOfMonth = today.withDayOfMonth(1);

                    for (AddDeposit a : reports){
                        if (!a.getDate().isBefore(firstDayOfMonth) && !a.getDate().isAfter(today)){
                            System.out.println(a.toCsvLine());
                        }
                    }
                    break;
                case ("2"):// if user chose 2 generate PM report
                    System.out.println("Previous Month Report: ");
                    //getting first day of the previous month
                    LocalDate firstDayMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
                    //getting last day of the previous month
                    LocalDate lastDayMonth = LocalDate.now().minusMonths(1).withDayOfMonth(LocalDate.now().minusMonths(1).lengthOfMonth());
                    for (AddDeposit a: reports){
                        if (!a.getDate().isBefore(firstDayMonth) && !a.getDate().isAfter(lastDayMonth)){
                            System.out.println(a.toCsvLine());
                        }
                    }
                    break;
                case ("3"):// if user chose 3 generate YTD report
                    System.out.println("Year To Date Report: ");
                    //getting the first day of the Year
                    LocalDate firstDayYear = LocalDate.now().withDayOfYear(1);

                    for (AddDeposit a : reports) {
                        if (!a.getDate().isBefore(firstDayYear) && !a.getDate().isAfter(LocalDate.now())){
                            System.out.println(a.toCsvLine());
                        }
                    }
                    break;
                case ("4"):// if user chose 4 generate PY report
                    System.out.println("Previous Year Report: ");
                    //getting first day of last year
                    LocalDate firstDayLastYear = LocalDate.now().minusYears(1).withDayOfYear(1);
                    //getting the last day of the last year
                    LocalDate lastDayLastYear = LocalDate.now().minusYears(1).withDayOfYear(LocalDate.now().minusYears(1).lengthOfYear());
                    for (AddDeposit a : reports) {
                        if (!a.getDate().isBefore(firstDayLastYear) && !a.getDate().isAfter(lastDayLastYear)){
                            System.out.println(a.toCsvLine());
                        }
                    }
                    break;
                case ("5")://if user chose 5 ask for vendor name and generate report
                    //creating a string to hold a user input
                    String vendorSearch = input("Please enter the vendor name to search for: ");
                    System.out.println("Search By Vendor: " + vendorSearch);
                    //creating a boolean to check if the vendor name has transactions
                    boolean found = false;
                    for (AddDeposit a : reports){
                        if (a.getVendor().contains(vendorSearch)){
                            System.out.println(a.toCsvLine());
                            found = true;
                        }
                    }
                    //if vendor name doesn't match data print below statement
                    if (!found) {
                        System.out.println("No transactions found for: " + vendorSearch);
                    }
                    break;
                case ("0"):// if user chose 0 take them back to ledger page
                    System.out.println("Taking you back to Ledger page ... ");
                    ledger();
                default:
                    System.out.println("Invalid option selected. Please press 1,2,3,4,5, or 0. ");

            }
        }catch (IOException e) {
            e.getMessage();
        }
        reader.close();//ensure reader is closed
    }
}
