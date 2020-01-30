package com.cs.kafka.utility;

import java.io.InputStream;
import java.util.Properties;

import com.cs.kafka.constants.KafkaConstants;

public class KafkaConfigurationLoad {
  
  private static Properties prop;
  
  private static void loadConfiguration() throws Exception
  {
    InputStream is = KafkaConfigurationLoad.class.getResourceAsStream("/" + KafkaConstants.KAFKA_PROPERTIES_FILE_NAME);
    prop = new Properties();
    prop.load(is);
  }
  
  public static Properties getProperties() throws Exception
  {
    if (prop == null) {
      loadConfiguration();
    }
    return prop;
  }
}
