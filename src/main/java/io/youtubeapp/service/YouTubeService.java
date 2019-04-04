package io.youtubeapp.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.youtubeapp.model.YouTubeVideo;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

@Service
public class YouTubeService {
	
	private static final String API_KEY = "AIzaSyCPxnc8_VVMyps0dqiks6IBZw6dbjt7ENU";
	private static final long NUMBER_OF_VIDEOS_RETURNED = 5;
	private static YouTube youtube;
	//refs https://developers.google.com/youtube/v3/getting-started#fields
	private static final String YOUTUBE_SEARCH_FIELDS = "items(id/videoId)";
	//you only can set "id, snippet"
	private static final String YOUTUBE_SEARCH_PARTS = "id, snippet";
	private static final String YOUTUBE_VIDEO_PARTS = "snippet, statistics";
	
	public List<YouTubeVideo> searchVideos(String queryTerm){
		List<YouTubeVideo> youtubeVideos = new ArrayList<>();
		
		try {
			youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException {
				}
			}).setApplicationName("youtube-cmdline-search-sample").build();		

			YouTube.Search.List search = youtube.search().list(YOUTUBE_SEARCH_PARTS);

			//set terms to connect API
			search.setKey(API_KEY);
			search.setQ(queryTerm);
			search.setType("video");
			search.setFields(YOUTUBE_SEARCH_FIELDS);
			search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
			
			//execute search
			//refs Search.java(https://developers.google.com/youtube/v3/docs/search/list)
			SearchListResponse searchResponse = search.execute();
			List<SearchResult> searchResultList = searchResponse.getItems();
			
			//make list of videoIds to get statistics
			//connection to API should be necessary only once
			List<String> videoIds = new ArrayList<String>();
			for (SearchResult searchResult : searchResultList) {
				videoIds.add(searchResult.getId().getVideoId());
			}
			
			//get videos by videoId
			YouTube.Videos.List videoRequestList = youtube.videos().list(YOUTUBE_VIDEO_PARTS);
			
			//set terms to connect API
			videoRequestList.setKey(API_KEY);
			videoRequestList.setId(String.join(", ", videoIds));
			
			VideoListResponse videoResponse = videoRequestList.execute(); 
			
			List<Video> videoList = videoResponse.getItems();
			for (Video video : videoList) {
				YouTubeVideo youtubeVideo = new YouTubeVideo();
				youtubeVideo.setTitle(video.getSnippet().getTitle());
				youtubeVideo.setUrl(createUrl(video.getId()));
				youtubeVideo.setThumbnailUrl(video.getSnippet().getThumbnails().getDefault().getUrl());
				youtubeVideo.setPublishDate(video.getSnippet().getPublishedAt().toString());
				youtubeVideo.setDescription(video.getSnippet().getDescription());
				youtubeVideo.setChannelTitle(video.getSnippet().getChannelTitle());
				youtubeVideo.setViewCount(video.getStatistics().getViewCount());
				youtubeVideos.add(youtubeVideo);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return youtubeVideos;
	}
	
	public String createUrl(String videoId) {
		//Below is the relation between videoid and url
		return "http://www.youtube.com/embed/" + videoId;
	}
}