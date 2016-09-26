package com.springclouddemo.processor.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.social.twitter.api.Tweet;

import com.springclouddemo.processor.configuration.PartitionConfiguration;

@EnableBinding(Processor.class)
@EnableConfigurationProperties(PartitionConfiguration.class)
public class StreamHandler {

	// @Transformer(inputChannel = Processor.INPUT, outputChannel =
	// Processor.OUTPUT)
	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public List<String> transform(List<Tweet> tweetList) {
	System.out.println("Processing message number of messages:" + tweetList.size());
		return tweetList.stream().map(x -> x.getText()).collect(Collectors.toList());
	}
}
