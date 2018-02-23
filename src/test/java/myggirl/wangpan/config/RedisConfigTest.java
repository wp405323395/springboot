package myggirl.wangpan.config;

import myggirl.wangpan.domain.Girl;
import myggirl.wangpan.resultUtils.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {

    @Autowired
    RedisConnectionFactory factory;
    @Autowired
    RedisTemplate<String,Object> template;

    @Test
    public void redisCF() throws Exception {
        RedisConnection conn = factory.getConnection();
        conn.set("hello".getBytes(), "罚款罚款罚款罚款罚款2222".getBytes());
        System.out.println(new String(conn.get("hello".getBytes())));
    }

    @Test
    public void test2() throws Exception {
        template.opsForValue().set("key1",1234213);
        System.out.println(template.opsForValue().get("key1"));
    }

    @Test
    public void test3() throws Exception {
        Girl girl = new Girl("M",10);
        Girl girl2 = new Girl("v",20);
        template.opsForList().rightPush("girls",girl);
        template.opsForList().rightPush("girls",girl2);

        Long girlListSize = template.opsForList().size("girls");
        List<Object> girlList = template.opsForList().range("girls",0,girlListSize-1);
        for (Object obj : girlList) {
            System.out.println((Girl) obj);
        }
    }

    @Test
    public void test4() throws Exception {
        Result result = (Result) template.opsForValue().get("guagua~keys");
        System.out.println(result);
    }

}