package io.youtubeapp.service.com.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import com.google.api.client.util.DateTime;


/**
 * Utility class for methods relating to date.
 */
public final class DateUtil {

	/**
	 * parse DateTime into LocalDate
	 * 
	 * pattern "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
	 * @param DateTime
	 * @return
	 */
	public static LocalDate toLocalDate(DateTime dt) {
		try {
			DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			return LocalDate.parse(dt.toStringRfc3339(), f);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get elapsed days, months or years from fromDate.
	 *  
	 * @param LocalDate fromDate
	 * @return "~days ago"/"~months ago"/"~years ago"
	 */
	public static String getElapsedTime(LocalDate fromDate) {
		int passedMonths = calculatePassedMonths(fromDate);
		if (passedMonths == 0) {
			int passedDays = calculatePassedDays(fromDate);
			return passedDays + " days ago";
		} else {
			return toYearOrMonthAgo(passedMonths);
		}
	}
	
	
	/**
	 * Calculate passed days from fromDate using Period class.
	 * 
	 * @param LocalDate fromDate
	 * @return int number of passed days
	 */
	public static int calculatePassedDays(LocalDate fromDate) {
		return Period.between(fromDate, LocalDate.now()).getDays();
	}

	/**
	 * Calculate passed months from fromDate using Period class.
	 * 
	 * @param LocalDate fromDate
	 * @return int number of passed months
	 */
	public static int calculatePassedMonths(LocalDate fromDate) {
		return (int)Period.between(fromDate, LocalDate.now()).toTotalMonths();
	}

	
	/**
	 * Exchange int months to String "months ago" or "years ago"
	 * 
	 * @param months
	 * @return ~months ago/~years ago
	 */
	public static String toYearOrMonthAgo(int months) {
		int years = months % 12;
		if (months == 0 ) {
			return months + " month ago";
		} else if (0 < months && months < 12) {
			return months + " months ago";
		} else { 
			if(years == 1) {
				return years + " year ago";
			}else {
				return years + " years ago";
			}
		}
	}
}
