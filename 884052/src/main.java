import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

    public static Scanner readFile, console;
    public static FileWriter writeFile;
    public static ArrayList <seats> seatArray = new ArrayList <seats> ();
    public static String seatChoice, emailInput = null;

    public static void main(String[] args) throws IOException {

        console = new Scanner(System.in);

        try {

            readFile = new Scanner(new FileReader("M:\\data\\seats.txt"));// Create a new Scanner and FileReader that reads the data in the file chose.

            while (readFile.hasNext()) { // While loop to check if the file has a line that it can read.
                seatArray.add(new seats(readFile.next(), readFile.next(), readFile.nextBoolean(), readFile.nextBoolean(), readFile.nextBoolean(), readFile.nextDouble(), readFile.next()));//Updating the object with data coming from the file.

            }

        } finally {

            if (readFile != null) { // if statement to check if the file has a next line to read.

                readFile.close(); // if there is no more lines that can be read close the reader.
            }
        }

        MainMenu(); // Call MainMenu().

    }

    public static void MainMenu() throws IOException {

        String userChoice = "";

        System.out.println("--Seat Booking System--");
        System.out.println("--Main Menu--");
        System.out.println("1 - Reserve Seat");
        System.out.println("2 - Cancel Seat");
        System.out.println("3 - View Seat Reservations");
        System.out.println("Q - Quit");

        userChoice = console.next(); // Checks the next input from the user and updates the variable with that data.

        switch (userChoice) { // Switch case that checks for the user input.
            case "1":
                EmailVerification(); // Calls EmailVerification().
            case "2":
                CancelSeat(); // Calls CancelSeat().
            case "3":
                ViewAllSeats(); // Calls ViewAllSeats().
            case "Q":
                SaveFile(); // Calls SaveFile().
                System.exit(1);
        }
    }

    public static void ReserveSeat() throws IOException {
    	
    	String seatChoice = "";
    	boolean hasWindow, hasAisle, hasTable;
    	Double seatPrice;
    	
    	//Asks the user for their choice of seat.
        System.out.println("Would you like a window?");
        hasWindow = (console.next().contains("Y") ? true : false);
        
        System.out.println("Would you like a Aisle?");
        hasAisle = (console.next().contains("Y") ? true : false);
        
        System.out.println("Would you like a Table?");
        hasTable = (console.next().contains("Y") ? true : false);
        
        System.out.println("What is the most you want to pay for the seat? [£ 00.00]");
        seatPrice = console.nextDouble();
        
        for (seats seatNum : seatArray) { // for loop that displays the seats that matches the users needs.
        	if(seatNum.hasWindow() == hasWindow && seatNum.hasAisle() == hasAisle && seatNum.hasTable() == hasTable && seatNum.getSeatPrice() <= seatPrice) {
        		System.out.println(seatNum);
        	}else {
        		System.out.println("There are no matches please try again");
        		ReserveSeat();
        	}
        		
        }
        
        System.out.println("What seat would you like to book");
        seatChoice = console.next();

        for (seats seatNum: seatArray) { // for each loop to navigate through seatArray.
        	
            if (seatNum.getSeatNum().contains(seatChoice)) { //Checks to see if the line in the array contains the value that the user has inputed.
                try {
                    seatNum.seatBooking(emailInput); // Update the object with the emailInput from the user.
                    System.out.println("You have successfully booked this seat."); // Provide visual feedback that it has been booked.

                } catch (Exception e) { // Catch an error that would output to the console.

                }
            }
        }

        String userChoice = "";

        System.out.println("Would you like to return to the home screen? Y:N"); // Prompt the user if they wish to return to the Main Menu.
        userChoice = console.next(); // Checks for the next user input.

        switch (userChoice) { // switch case for the user input.
            case "Y":
                MainMenu(); // Call MainMenu().
            case "N":
                return;
        }

    }


    public static void EmailVerification() throws IOException {

        System.out.println("Please enter your email for verification."); // Prompts the user for their email address.
        emailInput = console.next(); // Checks for the next user input.
        EmailChecker(); // Calls EmailChecker().

        if (EmailChecker()) { // if statement to check if the email is valid.
            ReserveSeat(); // if the email is valid call ReserveSeat().
        } else {
            System.out.println("Please enter a valid email address."); // if the email is valid tell the user to try again.
            EmailVerification(); // Call EmailVerification().
        }
    }

    public static boolean EmailChecker() {

        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$"; // Check to make sure that the email that has been entered follows the correct format for an email.
        return emailInput.matches(regex); // returns a true or false value based on the email that has been entered.
    }

    public static void CancelSeat() throws IOException {
    	
        System.out.println("Please enter your email for verification."); // Prompts the user for their email address.
        emailInput = console.next(); // Checks for the next user input.
        EmailChecker(); // Calls EmailChecker().

        if (EmailChecker()) { // if statement that runs when a valid email has been entered.
        	
            ViewAllSeats();// call ViewAllSeats().
            System.out.println("What seat would you like to cancel?");
            seatChoice = console.next();//Checks for the next user input.
            
            for (seats seatNum2: seatArray) { // for each loop to navigate through seatArray.
            	
                if (seatNum2.getSeatNum().contains(seatChoice) && seatNum2.showEmail().equals(emailInput)) { // if statement that checks if the array entry contains the same seat number and email that the user has inputed.
                	
                    try {
                        seatNum2.seatCanceling(emailInput); // Calls the object and replaces emailInput.
                        System.out.println("You have successfully cancelled this seat."); // visual feedback to the user.
                    } catch (Exception e) {

                    }
                }
            }
        } else {
            System.out.println("You have entered an incorrect email please try again.");
        }


        String userChoice = "";

        System.out.println("Would you like to return to the home screen? Y:N");
        userChoice = console.next();

        switch (userChoice) { // switch case that checks for the user input.
            case "Y":
                MainMenu(); // if the user choose Y call MainMenu().
            case "N":
                return;
        }
    }


    private static void ViewAllSeats() {
    	
        for (seats seatAmt: seatArray) { // for each loop to navigate through seatArray.
            System.out.println(seatAmt.toString()); // Print the contents of the array to the console.
        }
        System.out.println("");
    }

    public static void SaveFile() throws IOException {

        try {

            writeFile = new FileWriter("M:\\data\\seats.txt"); // Create a FileWriter that will write information to the given file.
            for (seats seatWriting: seatArray) { // for each loop to navigate through seatArray.
                writeFile.write(seatWriting.saveFormat());// Writing the array data to the file.
            }
        } finally {

            if (writeFile != null) { // if statement to check if there is another line available in the file.
                writeFile.close(); // If there is no other lines in the file close the writer.
            }
        }
    }

}