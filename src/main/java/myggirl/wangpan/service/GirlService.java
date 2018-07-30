package myggirl.wangpan.service;

import myggirl.wangpan.domain.Girl;
import myggirl.wangpan.repository.GilRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GirlService {
    @Autowired
    private GilRespository girlRespository;

    @Cacheable("girls")
    @Transactional
    public List<Girl> girlList() {
        return girlRespository.findAll();
    }

    @Cacheable(value="girls", key="'age'+#age")
    public List<Girl> findByAge(Integer age) {
        return girlRespository.findByAge(age);
    }

    @CacheEvict(value = "girls", allEntries = true)
    public Object addGirl(Girl girl) {
        return girlRespository.save(girl);
    }

    @Cacheable("girls")
    public Girl findOne(int id) {
        return girlRespository.findById(id).get();
    }
}
