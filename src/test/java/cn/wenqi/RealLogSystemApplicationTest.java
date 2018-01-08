package cn.wenqi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple RealLogSystemApplication.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RealLogSystemApplicationTest {

    private static final Logger logger= LoggerFactory.getLogger(RealLogSystemApplicationTest.class);

    @Test
    public void testLog(){

        for (int i = 0; i < 10; i++) {
            logger.info("log ..."+System.currentTimeMillis());
        }
    }
}
