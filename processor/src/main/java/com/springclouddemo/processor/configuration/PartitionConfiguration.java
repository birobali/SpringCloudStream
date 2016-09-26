package com.springclouddemo.processor.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="partition")
public class PartitionConfiguration {
	
	private List<String> candidates = new ArrayList<String>();

	public List<String> getCandidates() {
		return candidates;
	}
}
