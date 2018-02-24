package myggirl.wangpan.mybatis.dao;

import myggirl.wangpan.domain.Girl;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface GirlDao {
    List<Girl> queryGirl();
    Girl queryGirlById(int id);
    int insertGirl(Girl girl);
    int updateGirl(Girl girl);
    int deleteGirl(int id);
}
