//AUTHOR: Bryson Koch
//COURSE: CPT167
//PURPOSE: Calculates item cost(s) after tax and discount rate, shows total discount types and item types with a Grand Total.
//STARTDATE: 11/18/2021

package edu.cpt167.koch.exercise6;

import java.util.Scanner;

public class KochMainClass 
{	
	//Declare Class Constants
	public static final double TAX_RATE					=0.075;				//Tax Rate
	public static final String DISCOUNT_NAME_MEMBER		="Member";			//Discount Name Member
	public static final String DISCOUNT_NAME_SENIOR		="Senior";			//Discount Name Senior
	public static final String DISCOUNT_NAME_NONE		="No Discount";		//Discount Name None
	public static final String DISCOUNT_NAME_QUIT		="Quit";			//Discount Name Quit
	public static final double DISCOUNT_RATE_MEMBER		=0.15;				//Discount Rate Member
	public static final double DISCOUNT_RATE_SENIOR		=0.25;				//Discount Rate Senior
	public static final double DISCOUNT_RATE_NONE		=0.0;				//Discount Rate None
	public static final String ITEM_NAME_PREMIUM		="Premium Sod";		//Item Name Premium
	public static final String ITEM_NAME_SPECIAL		="Special Sod";		//Item Name Special
	public static final String ITEM_NAME_BASIC			="Basic Sod";		//Item Name Basic
	public static final double ITEM_PRICE_PREMIUM		=55.95;				//Item Price Premium
	public static final double ITEM_PRICE_SPECIAL		=24.95;				//Item Price Special
	public static final double ITEM_PRICE_BASIC			=15.95;				//Item Price Basic
	public static final int    RESET_VALUE				=0;					//Reset Value
	
	
	//Main Method
	public static void main(String[] args) 
	{
		
		//Introduction
		//Scanner Object for User Input
		Scanner input = new Scanner(System.in);
		
		//Declare Local Variables
		String userName 				= "";		//User's Name
		char rateSelection				= ' ';		//Rate Selection
		char itemSelection				= ' ';		//Item Selection
		int howMany						= 0;		//How Many Items
		String discountName				= " ";		//Discount Name
		double discountRate				= 0.0;		//Discount Rate
		String itemName		 			= "";		//Item's Name
		double itemPrice				= 0.0;		//Item's Price
		double discountAmount			= 0.0;		//Discount Amount
		double discountPrice 			= 0.0;		//Discount Price
		double subTotal 				= 0.0;		//Subtotal
		double tax 						= 0.0;		//Tax
		double totalCost				= 0.0;		//Total Transaction Cost
		int memberCount					= 0;		//Member Option Count
		int seniorCount					= 0;		//Senior Option Count
		int noDiscountCount				= 0;		//No Discount Option Count
		double grandTotal 				= 0.0;		//Sum of all total costs
		int premiumCount				= 0;		//Count of all Premium Items
		int specialCount				= 0;		//Count of all Special Items
		int basicCount					= 0;		//Count of all Basic Items
		double purchaseAmt				= 0.0;		//Purchase Amount
		
		//Display Welcome Banner
		displayWelcomeBanner();
		
		//User Input
		userName = getUserName(input);				//Gets User Name
		rateSelection = validateMainMenu(input);	//Validates Main Menu
		while (rateSelection != 'Q')				//While Loop for != 'Q'
		{
			itemSelection = validateItemMenu(input);//Validates Item Menu
			while (itemSelection != 'R')			//While Loop for != 'R'
			{
				howMany = validateHowMany(input);
				if (rateSelection == 'A')					//If rate selection is A
				{
					discountName = DISCOUNT_NAME_MEMBER;	//Discount name member
					discountRate = DISCOUNT_RATE_MEMBER;	//Discount rate member
					memberCount++;
				}//End of if A
				else if (rateSelection == 'B')				//If rate selection is B
				{
					discountName = DISCOUNT_NAME_SENIOR;	//Discount name senior
					discountRate = DISCOUNT_RATE_SENIOR;	//Discount rate senior
					seniorCount++;
					
				}//End of else if B
				else										//If rate selection is C 
				{
					discountName = DISCOUNT_NAME_NONE;		//Disccount name none
					discountRate = DISCOUNT_RATE_NONE;		//Discount rate none
					noDiscountCount++;
				}//End of else
				
				//Item selection
				if (itemSelection == 'A')					//If item selection A
				{
					itemName = ITEM_NAME_PREMIUM;			//Item name premium
					itemPrice = ITEM_PRICE_PREMIUM;			//Item price premium
					premiumCount++;
				}//End of if A
				else if (itemSelection == 'B')				//If item selection B
				{
					itemName = ITEM_NAME_SPECIAL;			//Item name special
					itemPrice = ITEM_PRICE_SPECIAL;			//Item price special
					specialCount++;
				}//End of else if B
				else										//If item selection C
				{
					itemName = ITEM_NAME_BASIC;				//Item name basic
					itemPrice = ITEM_PRICE_BASIC;			//Item price basic
					basicCount++;
				}//End of else
				
				//Arithmetic
				discountAmount = itemPrice * discountRate;	//Discount Amount assignment
				discountPrice = itemPrice - discountAmount;	//Discount Price assignment
				purchaseAmt = howMany * discountPrice;		//Purchase Amount Assignment
				subTotal = subTotal + purchaseAmt;			//Subtotal Assignment
				
				displayItemReceipt(itemName, itemPrice, 	//Displays the Item Receipt
						discountName, discountRate, discountAmount, 
						discountPrice, howMany, purchaseAmt, subTotal);
				
				itemSelection = validateItemMenu(input);	//Validates Item Menu
			}//End of while loop for itemSelection != 'R'
			
			//Arithmetic
			tax = subTotal * TAX_RATE;						//Tax Assignment
			totalCost = subTotal + tax;						//Total Cost Assignment
			grandTotal = grandTotal + totalCost;			//Grand Total Assignment
			
			//Displays the Order Total So Far
			displayOrderTotal(userName, subTotal, tax, totalCost);
			
			subTotal = RESET_VALUE;							//Sets the subTotal to 0
			
			rateSelection = validateMainMenu(input);		//Validates Main Menu
		}//End of while loop for rateSelection != 'Q'
		
		if (grandTotal > 0.0)								//If grandTotal > 0.0
		{
			displayFinalReport(memberCount, seniorCount, noDiscountCount, premiumCount, specialCount, basicCount, grandTotal); //Displays the Final Report
		}
			
		displayFarewellMessage(); //Displays the Farewell Message
		
		//Close Scanner
		input.close();
	}
	
	
	
	//Void method to display the welcome banner
	public static void displayWelcomeBanner()
	{
		//Welcome Banner
		System.out.println("Welcome to the Transaction Calculator 3.0");
		System.out.println("This program will calculate item cost");
		System.out.println("based on discount type menu selection.");
		System.out.println("***** ***** ***** ***** *****  ***** ***** ***** ***** *****");
	}//End of void method to display welcome banner
	
	//Void method to display the farewell message
	public static void displayFarewellMessage()
	{
		//Farewell message
		System.out.println("Thank you for using the Transaction Calculator 3.0");
		System.out.println("Have a great day!");
	}//End of void method to display farewell message
	
	//Void method to display the order details
	public static void displayItemReceipt(String borrowedItemName, double borrowedItemPrice, 
			String borrowedDiscountName, double borrowedDiscountRate, double borrowedDiscountAmount, 
			double borrowedDiscountPrice, int borrowedHowMany, double borrowedPurchaseAmt, double borrowedSubTotal)
	{
		System.out.println("***** ***** ***** ***** *****  ***** ***** ***** ***** *****");
		System.out.println("ITEM REPORT");														//"Order Report"
		System.out.println("***** ***** ***** ***** *****  ***** ***** ***** ***** *****");
		System.out.printf("%-18s%-1s\n", "Item Name", borrowedItemName);							//Prints the item's name
		System.out.printf("%-18s%-1s%8.2f\n", "Original Price", "$", borrowedItemPrice);			//Prints the original price
		System.out.printf("%-18s%-1s\n", "Discount Type", borrowedDiscountName);					//Prints the discount's name
		System.out.printf("%-18s%-1s%7.1f%1s\n", "Discount Rate", "", borrowedDiscountRate*100, " %");	//Prints the discount rate
		System.out.printf("%-18s%-1s%8.2f\n", "Discount Amount", "$", borrowedDiscountAmount);		//Prints the discount amount
		System.out.printf("%-18s%-1s%8.2f\n", "Discount Price", "$", borrowedDiscountPrice);		//Prints the price after discount
		System.out.printf("%-18s%-1s%8d\n", "Quantity", "", borrowedHowMany);						//Prints how many
		System.out.printf("%-18s%-1s%8.2f\n", "Purchase Amount", "$", borrowedPurchaseAmt);			//Prints the purchase amount
		System.out.printf("%-18s%-1s%8.2f\n",	"Subtotal", "$", borrowedSubTotal);					//Prints the subtotal
		System.out.println("***** ***** ***** ***** *****  ***** ***** ***** ***** *****");
	}//End of void method to display order details
	
	//Void method to display the order total
	public static void displayOrderTotal(String borrowedUserName, double borrowedSubTotal, double borrowedTax, double borrowedTotalCost)
	{
		System.out.println("***** ***** ***** ***** *****  ***** ***** ***** ***** *****");
		System.out.println("ORDER REPORT");														//"Order Report"
		System.out.println("***** ***** ***** ***** *****  ***** ***** ***** ***** *****");
		System.out.printf("%-18s%-1s\n", "User", borrowedUserName);									//Prints the user's name
		System.out.printf("%-18s%-1s%8.2f\n",	"Subtotal", "$", borrowedSubTotal);					//Prints the subtotal
		System.out.printf("%-18s%-1s%8.2f\n", "Tax", "$", borrowedTax);								//Prints the tax
		System.out.printf("%-18s%-1s%8.2f\n",	"Total Cost", "$", borrowedTotalCost);				//Prints the total cost
		System.out.println("***** ***** ***** ***** *****  ***** ***** ***** ***** *****");
	}//End of void method to display order total
	
	//Void method to display the final report
	public static void displayFinalReport(int borrowedMemberCount, int borrowedSeniorCount, 
			int borrowedNoDiscountCount, int borrowedPremiumCount, int borrowedSpecialCount, int borrowedBasicCount, double borrowedGrandTotal)
	{
		System.out.println("***** ***** ***** ***** *****  ***** ***** ***** ***** *****");
		System.out.println("FINAL REPORT");														//"Order Report"
		System.out.println("***** ***** ***** ***** *****  ***** ***** ***** ***** *****");
		System.out.printf("%-27s%1d\n", DISCOUNT_NAME_MEMBER, borrowedMemberCount);						//How many member discounts
		System.out.printf("%-27s%1d\n", DISCOUNT_NAME_SENIOR, borrowedSeniorCount);						//How many senior discounts
		System.out.printf("%-27s%1d\n", DISCOUNT_NAME_NONE, borrowedNoDiscountCount);					//How many no discounts
		System.out.println("***** ***** ***** ***** *****  ***** ***** ***** ***** *****");
		System.out.printf("%-27s%1d\n", ITEM_NAME_PREMIUM, borrowedPremiumCount);						//How many premium items
		System.out.printf("%-27s%1d\n", ITEM_NAME_SPECIAL, borrowedSpecialCount);						//How many special items
		System.out.printf("%-27s%1d\n", ITEM_NAME_BASIC, borrowedBasicCount);							//How many basic items
		System.out.println("***** ***** ***** ***** *****  ***** ***** ***** ***** *****");
		System.out.printf("%-18s%-4s%6.2f\n", "Grand Total", "$", borrowedGrandTotal);					//Grand total
		System.out.println("***** ***** ***** ***** *****  ***** ***** ***** ***** *****");
	}//End of void method to display the final report
	
	//Void method to display the main menu
	public static void displayMainMenu()
	{
		System.out.println("***** ***** ***** ***** *****  ***** ***** ***** ***** *****");
		System.out.println("DISCOUNT MENU");																		//Start of the discount selection menu
		System.out.printf("%-20s%4.1f%1s\n", "[A] for " + DISCOUNT_NAME_MEMBER, DISCOUNT_RATE_MEMBER*100, "%");		//Choosing A
		System.out.printf("%-20s%4.1f%1s\n", "[B] for " + DISCOUNT_NAME_SENIOR, DISCOUNT_RATE_SENIOR*100, "%");		//Choosing B
		System.out.printf("%-20s%4.1f%1s\n", "[C] for " + DISCOUNT_NAME_NONE, DISCOUNT_RATE_NONE*100, "%");			//Choosing C
		System.out.printf("%-20s\n", "[Q] to " + DISCOUNT_NAME_QUIT);												//Quitting
		System.out.println("***** ***** ***** ***** *****  ***** ***** ***** ***** *****");
	}//End of void method to display the main menu
	
	//Void method to display the item menu
	public static void displayItemMenu()
	{
		System.out.println("***** ***** ***** ***** *****  ***** ***** ***** ***** *****");
		System.out.println("ITEM MENU");																		//Start of the discount selection menu
		System.out.printf("%-20s%2s%5.2f\n", "[A] for " + ITEM_NAME_PREMIUM, "$ ", ITEM_PRICE_PREMIUM);			//Choosing A
		System.out.printf("%-20s%2s%5.2f\n", "[B] for " + ITEM_NAME_SPECIAL, "$ ", ITEM_PRICE_SPECIAL);			//Choosing B
		System.out.printf("%-20s%2s%5.2f\n", "[C] for " + ITEM_NAME_BASIC, "$ ", ITEM_PRICE_BASIC);				//Choosing C
		System.out.printf("%-20s\n", "[R] to Return to Main Menu");												//Return to main menu
		System.out.println("***** ***** ***** ***** *****  ***** ***** ***** ***** *****");
	}//End of void method to display the item menu
	
	
	//VR Method to get the Username
	public static String getUserName(Scanner borrowedInput)
	{
		String localUserName = "";																		//Varible for localUserName
		System.out.print("What is your name?: ");														//Asks user for their name
		localUserName = borrowedInput.nextLine();														//Assigns their name to localUserName
		return localUserName;
	}//End of VR method to get the username
	
	//VR Method to validate the Main Menu
	public static char validateMainMenu(Scanner borrowedInput)
	{
		char localSelection = ' ';
		displayMainMenu();																							//Displays Main Menu
		System.out.println("Enter your selection here: ");															//Asks user for their selection
		localSelection = borrowedInput.next().toUpperCase().charAt(0);
		while (localSelection != 'A' && localSelection != 'B' && localSelection != 'C' && localSelection != 'Q')	//Verify user input A, B, C or Q
		{
			System.out.println("The discount selected is invalid"); 												//Display Error
			displayMainMenu();																						//Displays Main Menu
			System.out.print("Enter your selection here: ");														//Asks user for their selection
			localSelection = borrowedInput.next().toUpperCase().charAt(0);													
		}//End of localSelection != A,B,C,Q loop
		return localSelection;
	}//End of VR method for main menu
	
	public static char validateItemMenu(Scanner borrowedInput)
	{
		char localSelection = ' ';
		displayItemMenu();																						//Displays Item Menu
		System.out.println("Enter your selection here: ");														//Asks user for their selection
		localSelection = borrowedInput.next().toUpperCase().charAt(0);
		while (localSelection != 'A' && localSelection != 'B' && localSelection != 'C' && localSelection != 'R')//Verify user input A, B, C, or R
		{
			System.out.println("The item selected is invalid");													//Display error
			displayItemMenu();																					//Displays Item Menu
			System.out.println("Enter your selection here: ");													//Asks user for their selection
			localSelection = borrowedInput.next().toUpperCase().charAt(0);
		}//End of localSelection != A,B,C,R
		return localSelection;
	}//End of VR method for item selection
	
	public static int validateHowMany(Scanner borrowedInput)
	{
		int localHowMany = 0;
		System.out.print("How many are you purchasing?: ");					//How many Item(s) & Assignment
		localHowMany = borrowedInput.nextInt();
		while (localHowMany <=0)											//Verify user put a value greater than 0
		{
			System.out.println("The quantity entered is invalid");			//Display error
			System.out.print("How many are you purchasing?: ");				//How many Item(s) & Assignment
			localHowMany = borrowedInput.nextInt();
		}//End of while howmany <=0 loop
		return localHowMany;
	}//End of VR method for how many
	
}//End of main class
	
	
	
	
	
	
	
