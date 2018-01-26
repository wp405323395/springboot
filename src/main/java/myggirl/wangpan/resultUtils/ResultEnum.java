package myggirl.wangpan.resultUtils;

public enum ResultEnum {
    AUTHERROR(413,"您没有权限调用此接口"),
    UNKNOW_ERROR(-1,"未知错误"),
    SUCCESS(0, "成功"),
    PRIMARRY_SCHOOL(100,"你可能在上小学");
    private Integer code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
    ResultEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
