import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class VegFarmProducer {
    String bootstrapServers ;
    String topics ;
    Properties props = new Properties();

    public VegFarmProducer(String bootstrapServers, String topics) {
        this.bootstrapServers = bootstrapServers;
        this.topics = topics;
        this.props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG , bootstrapServers);
        this.props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        this.props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
    }

    public void produceVeg(){
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>(topics,"carrots" ,"this is carrot No-"+i));
        }
        producer.close();
    }

}
