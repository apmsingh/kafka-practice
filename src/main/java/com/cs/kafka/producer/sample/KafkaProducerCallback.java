package com.cs.kafka.producer.sample;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaProducerCallback implements Callback {
  
  @Override
  public void onCompletion(RecordMetadata metadata, Exception exception)
  {
    if (exception != null) {
      System.out.println(metadata.topic() + " " + metadata.partition() + " " + metadata.offset());
      return;
    }
    System.out.println("Message sent failed!...");
    return;
  }
  
}
