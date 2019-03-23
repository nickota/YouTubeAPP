package io.youtubeapp.service;

import java.io.IOException;
import java.util.ArrayList;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;

public class YouTubeService {
	/////////READY TO USE API///////////
	private static final long NUMBER_OF_VIDEOS_RETURNED = 5;
	private static YouTube youtube;
	//refs https://developers.google.com/youtube/v3/getting-started#fields
	private static final String YOUTUBE_SEARCH_FIELDS = "items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)";

	public List<YouTubeVideo> searchVideos(String queryTerm){
		List<YouTubeVideo> videos = new ArrayList<>();

        youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName("youtube-cmdline-search-sample").build();		
        
        /////////////TERMS TO SEARCH//////////////////
        
		YouTube.Search.List search = youtube.search().list("id,snippet");

		//set terms to search
		public static final String API_KEY = "aaa";
		search.setKey(API_KEY);
		search.setQ(queryTerm);
		search.setType("video");
		search.setFields(YOUTUBE_SEARCH_FIELDS);
		search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

		SearchListResponse searchResponse = search.execute();
		
		
		
		//////GATHER THE RESULTS/////////
	}
}