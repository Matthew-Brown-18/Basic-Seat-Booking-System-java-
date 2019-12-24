public class seats {
	
	private String seatNum;
	private String seatClass;
	private Boolean isWindow;
	private Boolean isAisle;
	private Boolean isTable;
	private Double seatPrice;
	private String eMail;
	
	public seats(String sn, String sc, Boolean iw, Boolean ia, Boolean it, Double sp, String em) { // Creating a class as well as defining the data types for the objects in the class.
		
		seatNum = sn;
		seatClass = sc;
		isWindow = iw;
		isAisle = ia;
		isTable = it;
		seatPrice = sp;
		eMail = em;
		
	}
	
	@Override public String toString() { //Override toString so that it shows the array contents rather than memory addresses.
		return "Seat Number: " + seatNum + " " + "Seat Class: " + seatClass + " " + "Window: " + (hasWindow() ? "Y" : "N") + " " + "Aisle: " + (hasAisle() ? "Y" : "N") + " " + "Table: " + (hasTable() ? "Y" : "N") + " " + "Seat Price: £" + seatPrice + " " + "Reserved: " + (seatReserved() ? "Y" : "N"); // Line that will be printed to the console.
	}
	
	public boolean seatReserved() {
		return !eMail.equals("free"); // if the array line contains the word free returns the value N.
	}
	
	public boolean hasWindow() {
		return isWindow;
	}
	
	public boolean hasAisle() {
		return isAisle;
	}
	
	public boolean hasTable() {
		return isTable;
	}
	
	public String getSeatNum() {
		return seatNum;
	}
	
	public Double getSeatPrice() {
		return seatPrice;
	}
	
	public String showEmail() {
		return eMail;
	}

	public void seatBooking(String em) {
		if(!seatReserved()) { // if statement that checks to see if the seat is not reserved.
			eMail = em; // if the seat is not reserved replace the variable eMail with the object em.
		}
	}
	
	public void seatCanceling(String em) {
		eMail = "free"; // replace the variable contents with free.
	}
	
	public String saveFormat() {
		return (seatNum + " " + seatClass + " " + isWindow + " " + isAisle + " " + isTable + " " + seatPrice + " " + eMail + "\n"); // Line that will be written to file.
	}

}
