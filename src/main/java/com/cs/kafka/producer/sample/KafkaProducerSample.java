package com.cs.kafka.producer.sample;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.cs.kafka.constants.KafkaConstants;
import com.cs.kafka.utility.KafkaConfigurationLoad;

/**
 * @author: abhaypratap singh
 **/
public class KafkaProducerSample {
  
  public static String TOPIC_NAME = null;
  
  public static void main(String[] args) throws Exception
  {
    Properties properties = KafkaConfigurationLoad.getProperties();
    TOPIC_NAME = properties.getProperty(KafkaConstants.TOPIC_NAME);
    String key = "myTestKey";
    String value = "myTestValue";
    
    Properties produceProperties = new Properties();
    produceProperties.setProperty(KafkaConstants.BOOTSTRAP_SERVERS,
        properties.getProperty(KafkaConstants.BOOTSTRAP_SERVERS));
    produceProperties.setProperty(KafkaConstants.KEY_SERIALIZER,
        properties.getProperty(KafkaConstants.KEY_SERIALIZER));
    produceProperties.setProperty(KafkaConstants.VALUE_SERIALIZER,
        properties.getProperty(KafkaConstants.VALUE_SERIALIZER));
    
    Producer<String, String> producer = new KafkaProducer<String, String>(produceProperties);
    try {
      for (int i = 0; i < 5; i++) {
        String keyForMessage = key + "_" + i + "_" + UUID.randomUUID().toString();
        ProducerRecord<String, String> message = new ProducerRecord<String, String>(TOPIC_NAME,
            keyForMessage, value + i);
        Future<RecordMetadata> send = producer.send(message);
        RecordMetadata recordMetadata = send.get();
        int partition = recordMetadata.partition();
        String topic = recordMetadata.topic();
        System.out.println(partition);
        System.out.println(topic);
        System.out.println("Message sent.....");
      }
    }
    catch (Throwable e) {
      System.out.println("Exception Caught....");
      e.printStackTrace();
    }
    finally {
      producer.close();
    }
    
  }
  
}
