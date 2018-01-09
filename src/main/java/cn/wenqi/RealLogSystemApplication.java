package cn.wenqi;

import cn.wenqi.storm.MyBolt;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
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

        test1();

    }

    private void test1() throws InterruptedException {
        KafkaSpoutConfig<String,String> config=KafkaSpoutConfig.builder("localhost:9092","log")
                .setFirstPollOffsetStrategy(KafkaSpoutConfig.FirstPollOffsetStrategy.EARLIEST)
                .setGroupId("log")
                .build();

        KafkaSpout<String,String> kafkaSpout=new KafkaSpout<>(config);

        final TopologyBuilder tp = new TopologyBuilder();

        String id="kafka_spout_"+System.currentTimeMillis();
        String boltId="kafka_bold_"+System.currentTimeMillis();
        tp.setSpout(id, kafkaSpout, 1);
        tp.setBolt(boltId, new MyBolt()).shuffleGrouping(id);

        Config conf = new Config();
        conf.setDebug(false) ;
        System.setProperty("storm.jar", "/Users/wenqi/Develop/apache-storm-1.1.1/lib/storm-core-1.1.1.jar");

        String topoName="log_top_"+System.currentTimeMillis();

        //本地测试一定要使用LocalCluster！！！！！否则将kafka的消息将永远无法被storm消费！！
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology(topoName, conf, tp.createTopology());

//        StormSubmitter.submitTopology(topoName, conf, tp.createTopology());

        Thread.sleep(500);

        for (int i = 0; i < 20; i++) {
            logger.info("开始打印日志："+i+"===>"+System.currentTimeMillis());
        }
    }
}
