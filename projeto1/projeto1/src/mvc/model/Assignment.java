package mvc.model;

public class Assignment {
	private int id;
	private int userId;
	private String subject;
	private String note;
	private String date;
	
	//setter id
	public void setId(int id) {
		this.id = id;
	}
	
	//getter id
	public int getId() {
		return this.id;
	}
	
	//setter userId
	public void setUserId(int userId){
		this.userId = userId;
	}	
	
	//getter userId
	public int getUserId(){
		return this.userId;
	}
	
	//setter subject
	public void setSub(String subject){
		this.subject = subject;
	}
	
	//setter subject
	public String getSub(){
			return this.subject;
		}
	
	//setter assignment	
	public void setNote(String note) {
		this.note = note;
	}
	
	//getter assignment	
	public String getNote() {
		return this.note;
	}
	
	//setter date
	public void setDate(String date) {
		this.date = date;
	}
	
	//getter date
	public String getDate() {
		return this.date;
	}
	
}
