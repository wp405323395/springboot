package myggirl.wangpan.spring.exceptionHandle.exception;

import myggirl.wangpan.resultUtils.ResultEnum;

public class BusinessException extends RuntimeException {
    private Integer code;

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }

    public BusinessException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
    }
}
