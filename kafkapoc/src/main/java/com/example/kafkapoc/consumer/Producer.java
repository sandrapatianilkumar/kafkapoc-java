package com.example.kafkapoc.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.web.client.RestTemplate;

import com.example.kafkapoc.models.Student;

@Configuration
public class Producer {
	@Autowired
	Environment environment;

	@Autowired
	KafkaTemplate<String, Student> kafkaTemplate;

	@Bean
	public Map<String, Object> producerConfig() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				environment.getProperty("kafka.config.bootstrap_server_config"));
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return props;
	}

	@Bean
	public ProducerFactory<String, Student> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfig());
	}

	@Bean
	public KafkaTemplate<String, Student> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * This method will send object to kafka server based on topic name topic:
	 * name of topic Student:Student object
	 */

	public void send(String topic, Student student) {
		kafkaTemplate.send(topic, student);
	}

}
