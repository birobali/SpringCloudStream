package com.springclouddemo.sink.service;

import org.springframework.stereotype.Service;

@Service
public class TweetAnalizerService {

	public void analize(String message) {
		System.out.println("Tweet message: " + message);
		
	}
}
