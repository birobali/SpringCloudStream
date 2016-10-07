package com.springclouddemo.processor.service;

import java.util.AbstractMap;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.binder.PartitionKeyExtractorStrategy;
import org.springframework.cloud.stream.binder.PartitionSelectorStrategy;
import org.springframework.messaging.Message;

import com.springclouddemo.processor.configuration.PartitionConfiguration;

@EnableConfigurationProperties(PartitionConfiguration.class)
public class EventPartitionExtractor implements PartitionKeyExtractorStrategy, PartitionSelectorStrategy {

	
	@Override
	public Object extractKey(Message<?> message) {
		return ((AbstractMap.SimpleImmutableEntry<String, Integer>)message.getPayload()).getKey();
	}

	@Override
	public int selectPartition(Object hashTag, int numberOfPartition) {
		int partitionNumber =  (hashTag.equals("clinton") || hashTag.equals("trump") )? 1 : 0;
		System.out.println("partition Number:" + partitionNumber);
		return partitionNumber;
	}
}
