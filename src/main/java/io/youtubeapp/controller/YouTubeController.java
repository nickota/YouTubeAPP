package io.youtubeapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class YouTubeController {

	@RequestMapping(value="/youtube",method=RequestMethod.GET)
	public String youTube(Model model) {
		return "youtube";
	}
}
