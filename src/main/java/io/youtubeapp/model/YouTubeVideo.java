package io.youtubeapp.model;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;

import io.youtubeapp.service.com.util.DateUtil;

public class YouTubeVideo {

	//Resource representation
	//https://developers.google.com/youtube/v3/docs/videos?hl=en#resource
	private String title;
	private String url;
	private String thumbnailUrl;
	private LocalDate publishDate;
	private String description;
	private String channelTitle;
	private BigInteger viewCount;

	/**
	 * Calculate elapsed time from publishedDate.
	 * 
	 * @return
	 * 	0 months elapsed →　~days ago
	 * 	1 ~ 11 months elapsed →　~months ago
	 * 	12~ months elapsed ~years ago
	 */
	public String getElapsedTime() {
		Period elapsedTime = DateUtil.calculateElapsedTime(publishDate); 
		if (elapsedTime.getYears() >= 1) {
			return toYearYearsAgo(elapsedTime.getYears());
		} else if(elapsedTime.getMonths() >= 1) {
			return toMonthMonthsAgo(elapsedTime.getMonths());
		} else {
			return toDayDaysAgo(elapsedTime.getDays());
		}
	}
	private String toDayDaysAgo(int day) {
		return (day > 1) ? (day + " days ago") : (day + " day ago");
	}
	private String toYearYearsAgo(int year) {
		return (year > 1) ? (year + " years ago") : (year + " year ago");
	}
	private String toMonthMonthsAgo(int month) {
		return (month > 1) ? (month + " months ago") : (month + " month ago");
	}

	/**
	 * Returns viewCount.
	 * 
	 * @return
	 *	~999 → viewCount
	 *	1000 ~ 99999 → (viewCount / 1000) K
	 *	1000000 ~ → (viewCount / 1000000) M
	 */
	public String viewCountToKM() {
		BigInteger m = new BigInteger("1000000");
		BigInteger k = new BigInteger("1000");
		//more than 1000000
		if (viewCount.divide(m).compareTo(BigInteger.ONE) >= 0) {
			return viewCount.divide(m).toString() + "M";
		}
		//1000~99999
		if (viewCount.divide(k).compareTo(BigInteger.ONE) >= 0) {
			return viewCount.divide(k).toString() + "K";
		}
		//~999
		return viewCount.toString();
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	public LocalDate getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(LocalDate publishDate) {
		this.publishDate = publishDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigInteger getViewCount() {
		return viewCount;
	}
	public void setViewCount(BigInteger viewCount) {
		this.viewCount = viewCount;
	}
	public String getChannelTitle() {
		return channelTitle;
	}
	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}

}
