package com.cs.kafka.consumer.sample;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.cs.kafka.constants.KafkaConstants;
import com.cs.kafka.utility.KafkaConfigurationLoad;

/**
 * @author: abhaypratap singh
 **/
public class KafkaConsumerSample {
  
  public static void main(String[] args) throws Exception
  {
    Properties properties = KafkaConfigurationLoad.getProperties();
    Properties consumerProperties = new Properties();
    consumerProperties.setProperty(KafkaConstants.BOOTSTRAP_SERVERS,
        properties.getProperty(KafkaConstants.BOOTSTRAP_SERVERS));
    consumerProperties.setProperty(KafkaConstants.KEY_DESERIALIZER,
        properties.getProperty(KafkaConstants.KEY_DESERIALIZER));
    consumerProperties.setProperty(KafkaConstants.VALUE_DESERIALIZER,
        properties.getProperty(KafkaConstants.VALUE_DESERIALIZER));
    consumerProperties.setProperty(KafkaConstants.GROUP_ID,
        properties.getProperty(KafkaConstants.GROUP_ID));
    consumerProperties.setProperty(KafkaConstants.GROUP_ID,
        properties.getProperty(KafkaConstants.GROUP_ID));
    
    Consumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(consumerProperties);
    kafkaConsumer
        .subscribe(Collections.singletonList(properties.getProperty(KafkaConstants.TOPIC_NAME)));
    try {
      while (true) {
        Duration duration = Duration.ofMillis(1000);
        ConsumerRecords<String, String> record = kafkaConsumer.poll(duration);
        System.out.println("Waitting for msg....");
        for (ConsumerRecord<String, String> consumerRecord : record) {
          String key = consumerRecord.key();
          String value = consumerRecord.value();
          System.out.println(key);
          System.out.println(value);
          System.out.println("Message Received");
        }
        kafkaConsumer.commitAsync();
      }
    }
    catch (Throwable e) {
      System.out.println("Exception Caught......");
      e.printStackTrace();
    }
    finally {
      kafkaConsumer.commitSync();
      kafkaConsumer.close();
    }
  }
}
