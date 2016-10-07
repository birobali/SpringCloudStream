package com.springclouddemo.hashtagprocessor.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.social.twitter.api.HashTagEntity;
import org.springframework.social.twitter.api.Tweet;

@EnableBinding(Processor.class)
public class TweetTagHandler {

    private Source source;

    @Autowired
    public TweetTagHandler(Source source) {
        this.source = source;
    }
    
	@StreamListener(Processor.INPUT)
	public void transform(List<Tweet> tweetList) {
		System.out.println("Processing message number of messages:" + tweetList.size());
		tweetList.forEach(tweet -> processHashTagList(tweet.getEntities().getHashTags()));
	}
	
	private void processHashTagList(List<HashTagEntity> hashTagEntityList){
		hashTagEntityList.forEach(he -> sendHashTags(he.getText()));
	}

	private void sendHashTags(String hashTag) {
		System.out.println("Sending hashtag: " + hashTag);
		source.output().send(MessageBuilder.withPayload(hashTag).build());
	}
	

}
