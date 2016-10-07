package com.springclouddemo.source.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;

import com.springclouddemo.source.configuration.CollectorConfiguration;

@EnableBinding(Source.class)
@EnableConfigurationProperties(CollectorConfiguration.class)
public class TweetCollectorService {

	private final Logger LOG = LoggerFactory.getLogger(TweetCollectorService.class);
	private final StreamListener streamListener = getStreamListener();
	private List<Tweet> tweets = new ArrayList<>();
	private String keyword;

	@Autowired
	private Twitter twitter;
	@Autowired
	private CollectorConfiguration collectorConfiguration;
	
	@PostConstruct
	public void postConstruct(){
		keyword = collectorConfiguration.getKeyword();
		twitter.streamingOperations().filter(keyword, Collections.singletonList(streamListener));
	}
	
	@InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "${spring.integration.poller.fixed-delay}", maxMessagesPerPoll = "1"))
	public List<Tweet> timerMessageSource() {
		LOG.info("Number of tweets was sent: " + tweets.size());
		List<Tweet> tweetToSend = new ArrayList<>(tweets);
		tweets.clear();
		return tweetToSend;
	}

	private StreamListener getStreamListener(){
		return new StreamListener() {
	        @Override
	        public void onTweet(Tweet tweet) {
	        	LOG.info("Tweet message for " + tweet.getFromUser() + ": " + tweet.getText());
	        	tweets.add(tweet);
	        }

	        @Override
	        public void onDelete(StreamDeleteEvent deleteEvent) {
	        	LOG.info("onDelete");
	        }

	        @Override
	        public void onLimit(int numberOfLimitedTweets) {
	        	LOG.info("onLimit");
	        }

	        @Override
	        public void onWarning(StreamWarningEvent warningEvent) {
	        	LOG.info("onWarning");
	        }
	    };
	}

}
