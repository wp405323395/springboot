package myggirl.wangpan.repository;

import myggirl.wangpan.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GilRespository extends JpaRepository<Girl,Integer> {

    public List<Girl> findByAge(Integer age);
}
