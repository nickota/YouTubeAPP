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

@Service
public class YouTubeService {
	
	private static final String API_KEY = "AIzaSyCPxnc8_VVMyps0dqiks6IBZw6dbjt7ENU";
	private static final long NUMBER_OF_VIDEOS_RETURNED = 5;
	private static YouTube youtube;
	//refs https://developers.google.com/youtube/v3/getting-started#fields
	private static final String YOUTUBE_SEARCH_FIELDS = "items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url,snippet/publishedAt,snippet/description)";

	public List<YouTubeVideo> searchVideos(String queryTerm){
		List<YouTubeVideo> videos = new ArrayList<>();
		
		try {
			youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException {
				}
			}).setApplicationName("youtube-cmdline-search-sample").build();		

			YouTube.Search.List search = youtube.search().list("id,snippet");

			//set terms to connect API
			search.setKey(API_KEY);
			search.setQ(queryTerm);
			search.setType("video");
			search.setFields(YOUTUBE_SEARCH_FIELDS);
			search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
			
			//execute search
			//refs Search.java
			SearchListResponse searchResponse = search.execute();
			List<SearchResult> searchResultList = searchResponse.getItems();

			for (SearchResult searchResult : searchResultList) {
				YouTubeVideo video = new YouTubeVideo();
				video.setTitle(searchResult.getSnippet().getTitle());
				video.setUrl(createUrl(searchResult.getId().getVideoId()));
				video.setThumbnailUrl(searchResult.getSnippet().getThumbnails().getDefault().getUrl());
				video.setPublishDate(searchResult.getSnippet().getPublishedAt().toString());
				video.setDescription(searchResult.getSnippet().getDescription());
				videos.add(video);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return videos;
	}
	
	public String createUrl(String videoId) {
		//Below is the relation between videoid and url
		return "https://www.youtube.com/watch?v=" + videoId;
	}
	
}