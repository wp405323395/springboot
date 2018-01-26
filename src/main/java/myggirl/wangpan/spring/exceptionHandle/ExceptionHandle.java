package myggirl.wangpan.spring.exceptionHandle;

import myggirl.wangpan.spring.exceptionHandle.exception.BusinessException;
import myggirl.wangpan.resultUtils.Result;
import myggirl.wangpan.resultUtils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger  = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = java.lang.Exception.class)
    @ResponseBody
    public Result handle(java.lang.Exception e) {
        if(e instanceof BusinessException) {
            BusinessException er = (BusinessException)e;
            return ResultUtil.error(er.getCode(),er.getMessage());
        } else {
            logger.error("[系统异常]{}", e);
            return ResultUtil.error(-1, "未知异常");
        }
    }

}
