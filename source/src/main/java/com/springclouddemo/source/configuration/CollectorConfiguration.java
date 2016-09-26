package com.springclouddemo.source.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="twitter")
public class CollectorConfiguration {
	private String keyword;

    public String getKeyword() {
        return this.keyword;
    }

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
