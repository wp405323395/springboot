package myggirl.wangpan.service;

import myggirl.wangpan.domain.Girl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTest {

    @Autowired
    private GirlService girlService;

    @Test
    public void findByAge() throws Exception {
        List<Girl> girls = girlService.findByAge(18);
        Assert.state(girls.size() !=0,"查不出来");
    }

    @Test
    public void findOne() throws Exception {
        Girl girl = girlService.findOne(70);
        Assert.notNull(girl,"她是空的");
        System.out.println(girl);
    }

}