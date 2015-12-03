package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SavingsAccount extends Account {

//	@Override
	public Transaction addTransaction(double in) {
		if(getBalance() + in < 0 || in == 0)
			return null;
		else{
			Transaction T = new Transaction();
			T.setOldValue(getBalance());
			T.setAccountId(getAccountId());
			T.setTransactionId(-1);
			if(in > 0)
				T.setType("Deposit");
			if(in < 0)
				T.setType("Withdrawal");
			LocalDate d = LocalDate.now();
			LocalTime t = LocalTime.now();
			T.setTime(t.toString());
			T.setDate(d.toString());
			T.setAmmount(in);
			return T;
		}
	}

	//@Override
	public Transaction addIntrest() {
		Transaction T = new Transaction();
		T.setOldValue(getBalance());
		T.setAccountId(getAccountId());
		T.setTransactionId(-1);
		T.setType("Monthly Intrest");
		LocalDate d = LocalDate.now();
		LocalTime t = LocalTime.now();
		T.setTime(t.toString());
		T.setDate(d.toString());
		T.setAmmount(getIntrestRate() * getBalance());
		return T;
	}

	//@Override
	public void generateStatement() {
		// TODO Auto-generated method stub

	}

}
