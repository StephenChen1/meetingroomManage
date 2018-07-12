package dtoout;

public class AllBooked {
	private String staffNumber;
	private String roomNumber;
	private int capability;
	private BookedTime bookedTime;

	public String getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getCapability() {
		return capability;
	}

	public void setCapability(int capability) {
		this.capability = capability;
	}

	public BookedTime getBookedTime() {
		return bookedTime;
	}

	public void setBookedTime(BookedTime bookedTime) {
		this.bookedTime = bookedTime;
	}
}
