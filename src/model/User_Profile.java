package model;

import java.util.ArrayList;
import model.User;

public class User_Profile {

	private int user_Id;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state_country;


	//private int phone;
	private String phone_number;
	private int zipcode;
	
	private ArrayList<Account> accounts;
	
	public User_Profile(){
		user_Id = -1;
		firstName = "";
		lastName = "";
		city = "";
		address = "";
		state_country = "";
		//phone = -1;
		phone_number="";
		zipcode=0;
		accounts = new ArrayList<Account>();
	}
	
	public User_Profile(int uId, String fName, String lName, String tCity, 
						String tAddress, String tstate_country, String uPhone, int uZipcode){
		user_Id = uId;
		firstName = fName;
		lastName = lName;
		city = tCity;
		address = tAddress;
		state_country = tstate_country;
		//phone = tPhone;
		phone_number = uPhone;
		zipcode = uZipcode;
		accounts = new ArrayList<Account>();
	}
	
	public User_Profile(int uId, String fName, String lName, String tCity, 
			String tAddress, String tstate_country, String uPhone, int uZipcode, ArrayList<Account> acc){
		user_Id = uId;
		firstName = fName;
		lastName = lName;
		city = tCity;
		address = tAddress;
		state_country = tstate_country;
	//	phone = tPhone;
		phone_number = uPhone;
		zipcode = uZipcode;
		accounts = acc;
	}
	
	public int getUserId(){
		return user_Id;
	}
	public void setUserId(int i){
		user_Id = i;
	}
	/*
	public int getPhone(){
		return phone;
	}
	public void setPhone(int i){
		phone = i;
	}*/
	
	
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String i){
		firstName = i;
	}
	public String getLastName(){
		return lastName;
	}
	public void setLastName(String i){
		lastName = i;
	}
	public String getCity(){
		return city;
	}
	public void setCity(String i){
		city = i;
	}
	public String getAddress(){
		return address;
	}
	public void setAddress(String i){
		address = i;
	}
	public String getState_country() {
		return state_country;
	}
	public void setState_country(String i) {
		state_country = i;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String i) {
		phone_number = i;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int i) {
		zipcode = i;
	}
	
	public ArrayList<Account> getAccounts(){
		return accounts;
	}
	public void setAccounts(ArrayList<Account> i){
		accounts = i;
	}
	
	//put in DB
	public void addAccount(Account acc){
		accounts.add(acc);
	}
	
	//remove from BD
	public void removeAccount(Account account_Id){
		accounts.remove(account_Id);
	}
}
