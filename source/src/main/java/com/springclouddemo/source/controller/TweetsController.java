package com.springclouddemo.source.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springclouddemo.source.service.TweetCollectorService;

@Controller
@RequestMapping("/keyword")
public class TweetsController {
	@Autowired
	private TweetCollectorService collectorService;

    @GetMapping
    public KeywordConfig greetingForm(Model model) {
    	KeywordConfig keywordConfig = new KeywordConfig(collectorService.getKeyword());
        model.addAttribute("keywordConfig", keywordConfig);
		return keywordConfig;
    }

	
	@PostMapping
	public void setTweetersId(@ModelAttribute String keyword) {
		collectorService.setKeyword(keyword);
	}
}
