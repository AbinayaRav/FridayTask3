README.md  
ABINAYA R  
11/23/2020  

**CODE AVAILABILITY:**       

GitHub URL: https://github.com/AbinayaRav/FridayTask3.git     

**PROJECT DESCRIPTION**
This banking system performs the following operations:  
**1. Create a new Account.**      
**2. Retrieve all the account details from an external file. (.xlsx file)**       
**3. If already an account holder, the program deposits amount to the account balance. The code makes sure the account balance is always non negative.**        
**4. If already an account holder, the program  withdraws amount from the account balance. The code makes sure the account balance is always non negative.**        
**5. Delete an account**    

-The POJOs used here is the Customer.java which has the customer's personal details along with account balance. 
-Interface CustomerActivities.java defines the account operations related methods as mentioned above.  
-The Business layer is the AccountOperations.java which implements all the above mentioned operations by implementing CustomerActivities.java  
-The CustomerAccounts.java manages the all the customer objects mapping to their account numbers using HashMap and updated appropriately.    
-The UpdateExcelFile updates the sampledata.xlsx file based on various account operations. All the exceptions are properly handled.  


**INSTALLATION, COMPILE AND RUNTIME REQUIREMENTS:**  

**IDE**    
IntelliJ IDEA Community Edition 2020.2.3 x64  

The assignment has been coded and runs on  
**HARDWARE**    
• Intel® Core™ i7-7500U CPU @ 2.70 GHz 2.90 GHz  

**SOFTWARE**    
• Windows 8 64-bit, Java SE8 (jdk1.8.0_251, jre8)  
