package com.example.kafkapoc.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.kafkapoc.models.Student;
import com.example.kafkapoc.services.StudentService;

@Service
public class Consumer {
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	Environment environment;

	@Autowired
	KafkaTemplate<String, Student> kafkaTemplate;

	@Autowired
	StudentService studentService;

	/**
	 * This method for getting consumer configuration bean
	 */
	@Bean
	public Map<String, Object> consumerConfig() {
		Map<String, Object> consumerProperties = new HashMap<String, Object>();
		consumerProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
				environment.getProperty("auto.offset.reset.config"));
		consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("kafka.config.bootstrap_server_config"));
		consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "student");
		return consumerProperties;
	}

	/**
	 * This method will return the consumer factory bean based on consumer
	 * configuration
	 */
	@Bean
	public ConsumerFactory<String, Student> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(),
				new JsonDeserializer<>(Student.class));

	}

	/**
	 * This bean will return kafka listner object based on consumer factory
	 */

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Student> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Student> factory = new ConcurrentKafkaListenerContainerFactory<String, Student>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	/**
	 * This method will create rest template object
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * receive method
	 * 
	 * @param PropertyRequest
	 *            This method is listened whenever property is created and
	 *            updated
	 */
	@KafkaListener(topics = { "#{environment.getProperty('com.example.kafkapoc.create.student')}" })
	public void receive(ConsumerRecord<String, Student> consumerRecord) throws Exception {

		if (consumerRecord.topic().equalsIgnoreCase(environment.getProperty("com.example.kafkapoc.create.student"))) {
			 studentService.getStudents(consumerRecord.value());
		}
	}
}

