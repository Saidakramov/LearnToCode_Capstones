# Accounting Ledger Application
## Overview
Our objective was to create an accounting ledger application where a user can track all financial
transactions for business or personal use. All transactions in the application were read and saved to
csv file named transactions.
<br>
<br>
## HomeScreen Page
First, we created a Home Screen to prompt users to choose their preferred action. 
<br><img width="584" alt="Screenshot 2024-10-17 at 12 00 00 PM" src="https://github.com/user-attachments/assets/bd7602ce-8abc-4a10-a82a-a73d5e79ac69">


If the user chooses the Add Deposit option, the program will automatically fill in the date and time and will ask the user to input the product description, vendor name, and amount.
If the user chooses the Add Deposit option, the program will automatically fill in the date and time and will ask the user to input the product description, vendor name, and amount. 
After the user inputs the required information, the program will write it down in a CSV file in the date|time|description|vendor|amount format and automatically return to the home screen.
<br><img width="428" alt="Screenshot 2024-10-17 at 12 07 35 PM" src="https://github.com/user-attachments/assets/530d60dc-99de-4c10-935b-7366424507b9">

If the user chooses the Make Payment (Debit) option, the program will perform the same actions as the Add Deposit method. 

## Ledger Page
If the user chooses the Ledger option, the program will display a new screen and ask the user to select a new set of actions. 
<br> <img width="641" alt="Screenshot 2024-10-17 at 1 32 36 PM" src="https://github.com/user-attachments/assets/03b499b4-d02f-4851-863e-06e843c35749">

(A) All—will display everything in the transactions.csv file and ask users if they want to return to the ledger screen.
<br> <img width="555" alt="Screenshot 2024-10-17 at 1 35 15 PM" src="https://github.com/user-attachments/assets/fe6aa7af-b1bd-40bf-b13b-864b26c56349">


(D) Deposits - will display only the entries deposited into the account and ask users if they want to return to the ledger screen.
<br> <img width="501" alt="Screenshot 2024-10-17 at 1 39 42 PM" src="https://github.com/user-attachments/assets/5abb4107-aac5-47d7-82dc-f301b89bc1a3">


(P) Payments—This will display only negative entries (or payments) and ask users if they want to return to the ledger screen.
<br> <img width="530" alt="Screenshot 2024-10-17 at 1 42 23 PM" src="https://github.com/user-attachments/assets/9b1cfa35-87d5-4135-a934-dc30cf1ee011">


## Reports Page
(R) Reports—This will display a report screen with a new set of actions for the user to select from.
<br> <img width="340" alt="Screenshot 2024-10-17 at 1 47 34 PM" src="https://github.com/user-attachments/assets/76b0192b-b0e6-43d3-bec7-7d9b27aeb37b">


(1) Month To Date. This will generate month-to-date reports from the beginning of the month to the current day and ask if the user wants to return to the reports page. 
<br> <img width="546" alt="Screenshot 2024-10-17 at 1 51 41 PM" src="https://github.com/user-attachments/assets/4f6b0d27-d197-41ae-9ad0-1a1df146f396">


(2) Previous Month. This will generate previous months' reports from the beginning to the end of the month and ask if the user wants to return to the reports page.
<br> <img width="526" alt="Screenshot 2024-10-17 at 1 54 00 PM" src="https://github.com/user-attachments/assets/987db569-b31a-4df1-a914-2620eff5333f">


(3) Year To Date. This will generate current-year reports from the beginning to the current day of the year and ask if the user wants to return to the reports page.

(4) Previous Year.  This will generate last-year reports from the beginning to the end of the year and ask if the user wants to return to the reports page.

(5) Search by Vendor. This will prompt a user to enter the vendor's name they are looking for and provide a report based on that vendor. If the vendor doesn't exist, the program will print that there are no reports for the provided vendor.
<br> <img width="511" alt="Screenshot 2024-10-17 at 2 02 22 PM" src="https://github.com/user-attachments/assets/e046a01d-acde-45e1-85d8-010c422dc419"> <img width="532" alt="Screenshot 2024-10-17 at 2 02 58 PM" src="https://github.com/user-attachments/assets/fc4f474d-9243-4972-b42c-e45f36de1ac5">

## Custom Search
(6) Custom Search. This will prompt users to enter a start date, end date, description, vendor name, and amount information. The user can skip any of the questions by leaving the fields blank. The program then generates a report based on the search criteria, and if there isn't a match, it will display a message stating that no matches were found.
<br> <img width="736" alt="Screenshot 2024-10-17 at 2 22 15 PM" src="https://github.com/user-attachments/assets/ea11b788-10c5-4bd2-9fca-3761c91d4d0e"> <img width="683" alt="Screenshot 2024-10-17 at 2 22 42 PM" src="https://github.com/user-attachments/assets/7edb040f-c045-4f35-9ec6-ee2faa0f034e">


(0) Go back to the Ledger page. This will take a user back to the Ledger page.
In the Ledger page ((H) Go back to the home page), the user will be returned to the Home Screen page.
In the Home Screen ((X) Exit) - will exit the program. 
<br> <img width="647" alt="Screenshot 2024-10-17 at 2 34 08 PM" src="https://github.com/user-attachments/assets/2856fe5e-d812-4ac6-81bf-40bb97e1a04b">

## Interesting Code
```
boolean anyMatches = false; // track if any matches are found
//loop through records and apply filters
for (AddDeposit a : reports){
boolean matches = true;//start with true and fail if conditions does not match

    if (enterStartDate != null && a.getDate().isBefore(enterStartDate)){
        matches = false;
    }
    if (enterEndDate != null && a.getDate().isAfter(enterEndDate)){
        matches = false;
    }
    if (!enterDescription.isEmpty() && !a.getDescription().contains(enterDescription)){
        matches = false;
    }
    if (!enterVendor.isEmpty() && !a.getVendor().contains(enterVendor)){
        matches = false;
    }
    if ((enterAmount != null) && Double.compare(a.getAmount(),enterAmount) != 0){
        matches =false;
    }
    if (matches){
        System.out.println(a.toCsvLine());
        anyMatches = true; //at least one match was found
    }
}
    // if no matches found display the message
    if (!anyMatches) {
        System.out.println("No matches found. ");
    }
```