# Accounting Ledger Application
### Overview
Our objective was to create an accounting ledger application where a user can track all financial
transactions for business or personal use. All transactions in the application were read and saved to
csv file named transactions.
<br>
<br>
First, we created a Home Screen to prompt users to choose their preferred action. 
<br><img width="584" alt="Screenshot 2024-10-17 at 12 00 00 PM" src="https://github.com/user-attachments/assets/bd7602ce-8abc-4a10-a82a-a73d5e79ac69">


If the user chooses the Add Deposit option, the program will automatically fill in the date and time and will ask the user to input the product description, vendor name, and amount.
If the user chooses the Add Deposit option, the program will automatically fill in the date and time and will ask the user to input the product description, vendor name, and amount. 
After the user inputs the required information, the program will write it down in a CSV file in the date|time|description|vendor|amount format and automatically return to the home screen.
<br><img width="428" alt="Screenshot 2024-10-17 at 12 07 35 PM" src="https://github.com/user-attachments/assets/530d60dc-99de-4c10-935b-7366424507b9">

If the user chooses the Make Payment (Debit) option, the program will perform the same actions as the Add Deposit method. 

If the user chooses the Ledger option, the program will display a new screen and ask the user to select a new set of actions. 
<br> 



(A) All—will display everything in the transactions.csv file and ask users if they want to return to the ledger screen.
<br>

(D) Deposits - will display only the entries deposited into the account and ask users if they want to return to the ledger screen.
<br>

(P) Payments—This will display only negative entries (or payments) and ask users if they want to return to the ledger screen.
<br>

(R) Reports—This will display a report screen with a new set of actions for the user to select from.
<br>

(1) Month To Date. This will generate month-to-date reports from the beginning of the month to the current day and ask if the user wants to return to the reports page. 
<br>

(2) Previous Month. This will generate previous months' reports from the beginning to the end of the month and ask if the user wants to return to the reports page.
<br>

(3) Year To Date. This will generate current-year reports from the beginning to the current day of the year and ask if the user wants to return to the reports page.

(4) Previous Year.  This will generate last-year reports from the beginning to the end of the year and ask if the user wants to return to the reports page.

(5) Search by Vendor. This will prompt a user to enter the vendor's name they are looking for and provide a report based on that vendor. If the vendor doesn't exist, the program will print that there are no reports for the provided vendor.
<br>

