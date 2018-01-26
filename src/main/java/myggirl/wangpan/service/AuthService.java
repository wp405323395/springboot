package myggirl.wangpan.service;

import myggirl.wangpan.resultUtils.ResultEnum;
import myggirl.wangpan.spring.exceptionHandle.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    public void checkAccess(){
        throw new BusinessException(ResultEnum.AUTHERROR);
    }
}
