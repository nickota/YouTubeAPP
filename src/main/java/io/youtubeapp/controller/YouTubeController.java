package io.youtubeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class YouTubeController {

	@RequestMapping(value="/youtube",method=RequestMethod.GET)
	public String youTube(Model model) {
		return "youtube";
	}
	
	@RequestMapping(value="youtube",method=RequestMethod.POST)
	public String searchMovies(@RequestParam("keyword")String keyword, Model model) {
		model.addAttribute("keyword",keyword);
		return "searchResults";
	}
}
