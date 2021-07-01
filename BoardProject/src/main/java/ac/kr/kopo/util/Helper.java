package ac.kr.kopo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
	
	public static String today() {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(today);
	}
}
