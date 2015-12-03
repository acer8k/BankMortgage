package model;
import java.time.*;

public class Transaction {

	private int trans_Id;
	private String type;
	private double ammount;
	private int account_Id;
	private String time;
	private String date;
	private double oldValue;
	
	public Transaction(){
		trans_Id = -1;
		type = "";
		ammount = -1;
		account_Id = -1;
		time = null;
		date = null;
		oldValue = -1;
	}
	
	public Transaction(int tId, String typ, double amm, int aId, String t, String d, double oldV){
		trans_Id = tId;
		type = typ;
		ammount = amm;
		account_Id = aId;
		time = t;
		date = d;
		oldValue = oldV;
	}
	
	public int getTransactionId(){
		return trans_Id;
	}
	public void setTransactionId(int tId){
		trans_Id = tId;
	}
	public String getType(){
		return type;
	}
	public void setType(String typ){
		type = typ;
	}
	public double getAmmount(){
		return ammount;
	}
	public void setAmmount(double amm){
		ammount = amm;
	}
	public int getAccountId(){
		return account_Id;
	}
	public void setAccountId(int aId){
		account_Id = aId;
	}
	public String getTime(){
		return time;
	}
	public void setTime(String t){
		time = t;
	}
	public String getDate(){
		return date;
	}
	public void setDate(String t){
		date = t;
	}
	public double getOldValue(){
		return oldValue;
	}
	public void setOldValue(double oV){
		oldValue = oV;
	}
	public String toString(){
		//Creates a string with the format (transId, type, amount, accountId, date, time, oldvalue)
		String out = "";
		out += this.getTransactionId() + "\t\t\t" + this.getType() + "\t\t\t" + this.getAmmount() + "\t\t\t" + this.getAccountId() + "\t\t\t" + this.getDate() + "\t\t\t" + this.getTime() + "\t\t\t" + this.getOldValue();
		return out;
	}
}
