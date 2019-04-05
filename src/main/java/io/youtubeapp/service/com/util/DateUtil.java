package io.youtubeapp.service.com.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.google.api.client.util.DateTime;


public class DateUtil {

	public static LocalDate toLocalDate(DateTime dt) {
		try {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		return LocalDate.parse(dt.toStringRfc3339(), f);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
