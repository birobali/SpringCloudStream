package com.springclouddemo.processor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.binder.PartitionKeyExtractorStrategy;
import org.springframework.cloud.stream.binder.PartitionSelectorStrategy;
import org.springframework.messaging.Message;

import com.springclouddemo.processor.configuration.PartitionConfiguration;

@EnableConfigurationProperties(PartitionConfiguration.class)
public class EventPartitionExtractor implements PartitionKeyExtractorStrategy, PartitionSelectorStrategy {

	@Autowired
	private PartitionConfiguration partitionConfiguration;
	
	@Override
	public Object extractKey(Message<?> message) {
		return ((List<String>)message.getPayload()).stream().map(x -> x.toUpperCase()).collect(Collectors.toList());
	}

	@Override
	public int selectPartition(Object key, int numberOfPartition) {
		int partitionNumber = (textContainsCandidate(key.toString()))? 0 : numberOfPartition-1;
		System.out.println("partition Number:" + partitionNumber);
		return partitionNumber;
	}
	
	private boolean textContainsCandidate(String text)
	{
	    for(String candidate : Lists.newArrayList("CLINTON", "TRUMP"))
	    {
	        if(text.contains(candidate))
	        {
	            return true;
	        }
	    }
	    return false;
	}

}
