public class Demo {
    public static void main(String[] args) {
        String bootstrapServers = ":29092,localhost:29092";
        String topics = "GenericVeggie24";
        String consumerGroup = "VeggiesConsumers";
        String diffConsumerGroup = "DiffVeggiesConsumers";


        VegFarmProducer vegFarmProducer = new VegFarmProducer(bootstrapServers, topics);
        BigBasketConsumer bigBasketConsumer = new BigBasketConsumer(bootstrapServers, consumerGroup,topics);
        FTHConsumer fthConsumer = new FTHConsumer(bootstrapServers,consumerGroup ,topics);
        RelianceConsumer rConsumer = new RelianceConsumer(bootstrapServers, consumerGroup,topics);


        (new Thread(() -> vegFarmProducer.produceVeg())).start();
        (new Thread(() -> bigBasketConsumer.collectVegies())).start();
        (new Thread(() -> fthConsumer.collectVegies())).start();
//        (new Thread(() -> rConsumer.collectVegies())).start();


    }
}
