package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	// put all the values in the constant files which wont change

	public static final int DEFAULT_MEDIUM_TIME_OUT = 10;
	public static final int DEFAULT_SHORT_TIME_OUT = 5;
	public static final int DEFAULT_LONG_TIME_OUT = 15;

	public static final String LOGIN_PAGE_TITLE_VALUE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION_VALUE = "account/login";
	public static final String ACCOUNTS_PAGE_TITLE_VALUE = "My Account";
	public static final String ACCOUNT_PAGE_URL_FRACTION_VALUE = "route=account/account";

	public static final int ACCOUNT_PAGE_HEADERCOUNT_LIST = 4;
	public static final List<String> EXPECTED_ACCOUNTS_PAGEHEADERS_LIST_VALUE = Arrays.asList("My Account", "My Orders",
			"My Affiliate Account", "Newsletter");
	public static final CharSequence USER_REG_SUCCESS_MSG = "Your Account Has Been Created";
public static final String REGISTER_SHEET_NAME="register";
	// "route=account/account"

	// test data:which we pass in the test cases so in some excel file
	// constants:no point in putting these in excel so make them static and use them
	// throughout the files
	// env/configuration data:properties file
}
