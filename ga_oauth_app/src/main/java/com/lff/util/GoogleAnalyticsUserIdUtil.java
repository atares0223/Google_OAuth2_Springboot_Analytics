package com.lff.util;

public class GoogleAnalyticsUserIdUtil {
	private static String PREFIX = "_ga_uid";  
	public static String toGoogleAnalyticsUserId(Integer userId){
		return PREFIX + userId;
	};
}
