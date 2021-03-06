package com.springclouddemo.sink.handler.service;

import java.util.AbstractMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import com.springclouddemo.sink.service.TweetAnalizerService;

@EnableBinding(Sink.class)
public class StreamHandler {

	  @Autowired
	  TweetAnalizerService tweetAnalizerService;
	  
	  @StreamListener(Sink.INPUT)
//	  @ServiceActivator(inputChannel=Sink.INPUT)
	  public void handle(AbstractMap.SimpleImmutableEntry<String, Integer> hashTagCount) {
		  tweetAnalizerService.analize(hashTagCount.getKey(), hashTagCount.getValue());
	  }
}
