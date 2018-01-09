package cn.wenqi;

import cn.wenqi.storm.MyBolt;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.kafka.bolt.KafkaBolt;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.topology.TopologyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
public class RealLogSystemApplication implements CommandLineRunner{

    private static final Logger logger= LoggerFactory.getLogger(RealLogSystemApplication.class);


    public static void main(String[] args) {

        SpringApplication.run(RealLogSystemApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {

        KafkaSpoutConfig<String,String> config=KafkaSpoutConfig.builder("localhost:2181","log").build();
        KafkaSpout<String,String> kafkaSpout=new KafkaSpout<>(config);

        final TopologyBuilder tp = new TopologyBuilder();
        tp.setSpout("kafka_spout", kafkaSpout, 1);
        tp.setBolt("bolt", new MyBolt()).shuffleGrouping("kafka_spout");
        Config conf = new Config();
        conf.setDebug(true) ;
        System.setProperty("storm.jar", "/Users/wenqi/Develop/apache-storm-1.1.1/lib/storm-core-1.1.1.jar");
        StormSubmitter.submitTopology("log_topology", conf, tp.createTopology());
        Thread.sleep(2000);

        for (int i = 0; i < 20; i++) {
            logger.info("开始打印日志："+i+"===>"+System.currentTimeMillis());
        }
    }
}
