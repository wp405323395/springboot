package myggirl.wangpan.service;

import myggirl.wangpan.domain.Girl;
import myggirl.wangpan.repository.GilRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GirlService {
    @Autowired
    private GilRespository girlRespository;

    @Transactional
    public List<Girl> girlList() {
        return girlRespository.findAll();
    }

    public List<Girl> findByAge(Integer age) {
        return girlRespository.findByAge(age);
    }

    public Object addGirl(Girl girl) {
        return girlRespository.save(girl);
    }

    public Girl findOne(int id) {
        return girlRespository.findOne(id);
    }
}
