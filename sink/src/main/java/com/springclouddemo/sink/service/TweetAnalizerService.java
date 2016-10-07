package com.springclouddemo.sink.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class TweetAnalizerService {

	private Map<String, Integer> hashTagCount = new HashMap<>();
	public void analize(String hashTag, Integer count) {
		hashTagCount.merge(hashTag, count, Integer::sum);
		System.out.println("Tweet messages count: " + hashTagCount.toString());
		
	}
}
