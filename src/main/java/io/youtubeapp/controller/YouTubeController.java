package io.youtubeapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.youtubeapp.model.YouTubeVideo;
import io.youtubeapp.service.YouTubeService;

@Controller
public class YouTubeController {
	
	@Autowired
	private YouTubeService youtubeService;

	@RequestMapping(value="/youtube",method=RequestMethod.GET)
	public String youTube(Model model) {
		return "youtube";
	}
	
	@RequestMapping(value="/youtube",method=RequestMethod.POST)
	public String searchMovies(@RequestParam("keyword")String keyword, Model model) {
		List<YouTubeVideo> videos = new ArrayList<>();
		videos = youtubeService.searchVideos(keyword);
		
		model.addAttribute("videos", videos);
		return "searchResults";
	}
	
	@RequestMapping("/")
	public String home(Model model) {
		return "redirect:youtube";
	}
}
