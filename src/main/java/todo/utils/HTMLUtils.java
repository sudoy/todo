package todo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HTMLUtils {
	public static String formatImportance(int importance) {
//		if(importance == 1) {
//			return "★";
//		}else if(importance == 2) {
//			return "★★";
//		}else {
//			return "★★★";
//		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < importance; i++) {
			sb.append("★");
		}
		return sb.toString();
	}
	
	public static String formatLimitDate(Date limitDate) {
		// 期限は未入力を許可しているため
		if(limitDate == null) {
			return "";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(limitDate);
	}
}
