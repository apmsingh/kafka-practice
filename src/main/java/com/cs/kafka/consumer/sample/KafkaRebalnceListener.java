package com.cs.kafka.consumer.sample;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

public class KafkaRebalnceListener implements ConsumerRebalanceListener {
  
  private Consumer<String, String>               consumer;
  private Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<TopicPartition, OffsetAndMetadata>();
  
  public KafkaRebalnceListener(Consumer<String, String> kafkaConsumer)
  {
    this.consumer = kafkaConsumer;
  }
  
  public void addoffset(String topic, int partition, long offset)
  {
    offsets.put(new TopicPartition(topic, partition), new OffsetAndMetadata(offset));
  }
  
  public Map<TopicPartition, OffsetAndMetadata> getCurrentOffset()
  {
    return offsets;
  }
  
  @Override
  public void onPartitionsRevoked(Collection<TopicPartition> partitions)
  {
    consumer.commitSync(offsets);
    offsets.clear();
  }
  
  @Override
  public void onPartitionsAssigned(Collection<TopicPartition> partitions)
  {
    
  }
  
}
