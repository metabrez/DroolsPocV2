/*package com.app.drools.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	
	boolean dateBefore(String inputDate, String read) throws ParseException {
	
	//eval(dateBefore($productObject.getPurchasedDate(), $param))
		Date purchasedDate;
		Date readDateFromExcel;
		if(inputDate=="") {
			purchasedDate = new SimpleDateFormat("dd-MMM-yyyy").parse("00-00-0000");
		}
		if(read == "") {
			readDateFromExcel = new SimpleDateFormat("dd-MMM-yyyy").parse("00-00-0000");
		}
		purchasedDate = new SimpleDateFormat("dd-MMM-yyyy").parse(inputDate);
		readDateFromExcel = purchasedDate = new SimpleDateFormat("dd-MMM-yyyy").parse(read);
		return readDateFromExcel.before(purchasedDate);
	}

}
*/