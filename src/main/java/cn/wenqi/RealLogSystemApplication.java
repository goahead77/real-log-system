package cn.wenqi;

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

        for (int i = 0; i < 10; i++) {
            logger.info("print log with " +i);
            Thread.sleep(1000);
        }
    }
}
