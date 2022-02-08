import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class RelianceConsumer {
    String bootstrapServers ;
    String groupId ;
    String topics ;
    Properties props = new Properties();

    public RelianceConsumer(String bootstrapServers, String groupId, String topics) {
        this.bootstrapServers = bootstrapServers;
        this.groupId = groupId;
        this.topics = topics;
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        // if we want to adjust defaults
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest") ;// default is latest
        // props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false) // default 5000 - change how often to commit offsets
        // props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 10000) // default 5000 - change how often to commit offsets


    }

    public void collectVegies() {
        KafkaConsumer<String, String> bbConsumer = new KafkaConsumer<>(props);
        bbConsumer.subscribe(Collections.singletonList(this.topics));


        ConsumerRecords<String, String> veggies = bbConsumer.poll(Duration.ofSeconds(30));

        veggies.forEach(veggie -> System.out.println("RelianceConsumer: (key: " +
            veggie.key() + ", with value: " + veggie.value() +
            ") at on partition " + veggie.partition() + " at offset " + veggie.offset()));
        bbConsumer.close();
    }

}
