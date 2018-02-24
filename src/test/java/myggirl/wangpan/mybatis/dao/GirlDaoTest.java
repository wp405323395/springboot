package myggirl.wangpan.mybatis.dao;

import myggirl.wangpan.domain.Girl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlDaoTest {
    @Autowired
    private GirlDao girlDao;
    @Test
    public void queryGirl() {
        List<Girl> girls = girlDao.queryGirl();
        System.out.println("执行啦。。。。"+girls.get(0));
    }

    @Test
    public void queryGirlById() {
    }

    @Test
    public void insertGirl() {
    }

    @Test
    public void updateGirl() {
    }

    @Test
    public void deleteGirl() {
    }
}