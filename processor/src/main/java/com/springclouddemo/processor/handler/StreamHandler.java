package com.springclouddemo.processor.handler;

import java.time.Duration;
import java.util.AbstractMap;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;

import com.springclouddemo.processor.configuration.PartitionConfiguration;

import reactor.core.publisher.Flux;

@EnableBinding(Processor.class)
@EnableConfigurationProperties(PartitionConfiguration.class)
public class StreamHandler {

	@StreamListener
	@Output(Processor.OUTPUT)
	public Flux<AbstractMap.SimpleImmutableEntry<String, Integer>> processHashTags(
			@Input(Processor.INPUT) Flux<String> hashTags) {
		return hashTags.log().window(Duration.ofSeconds(10), Duration.ofSeconds(5))
				.flatMap(window -> window.groupBy(hashTag -> hashTag)
						.flatMap(group -> group.reduce(0, (counter, word) -> counter + 1).log().map(
								count -> new AbstractMap.SimpleImmutableEntry<String, Integer>(group.key().toLowerCase(), count))))
				.log();
	}
}
