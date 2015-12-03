package model;

//import java.util.ArrayList;

public abstract class Account {

	private int account_Id;
	//private int user_Id;
	private double balance;
	private double intrestRate;
	private String type;
	//private ArrayList<Integer> history;

	public Account(){
		
		account_Id = -1;
		//user_Id = -1;
		balance = -1;
		intrestRate = -1;
		type = "";
		//history = new ArrayList<Integer>();
	}
	
	public Account(int aId, int uId, double bal, double iRate, String typ){
		
		account_Id = aId;
		//user_Id = uId;
		balance = bal;
		intrestRate = iRate;
		type = typ;
		//history = his;
	}
	
	public int getAccountId(){
		return account_Id;
	}
	public void setAccountId(int aId){
		account_Id = aId;
	}
	/*
	public int getUserId(){
		return user_Id;
	}
	public void setUserId(int uId){
		user_Id = uId;
	}
	*/
	public double getBalance(){
		return balance;
	}
	public void setBalance(double bal){
		balance = bal;
	}
	public double getIntrestRate(){
		return intrestRate;
	}
	public void setIntrestRate(double iRate){
		intrestRate = iRate;
	}
	public String getType(){
		return type;
	}
	public void setType(String typ){
		type = typ;
	}
	
	public abstract Transaction addTransaction(double in);
	
	public abstract Transaction addIntrest();
	
	public abstract void generateStatement();
	
	
	/*public ArrayList<Integer> getHistory(){
		return history;
	}
	public void setHistory(ArrayList<Integer> in){
		history = in;
	}
	public void addHistory(int in){
		history.add(new Integer(in));
	}*/
	
	
}
