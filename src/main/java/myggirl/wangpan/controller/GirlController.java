package myggirl.wangpan.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import myggirl.wangpan.spring.exceptionHandle.exception.BusinessException;
import myggirl.wangpan.domain.Girl;
import myggirl.wangpan.resultUtils.Result;
import myggirl.wangpan.resultUtils.ResultEnum;
import myggirl.wangpan.service.GirlService;
import myggirl.wangpan.resultUtils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Api(value = "/school", tags="女孩儿的接口")
@RestController
@RequestMapping("/school")
public class GirlController {
    @Autowired
    private GirlService girlService;

    @ApiOperation(value="搜索所有女孩儿的信息", notes = "拉取到所有女孩儿的数据。。。")
    @GetMapping(value = "/girls")
    public Result<List<Girl>> girlList(){
        return ResultUtil.success(girlService.girlList());
    }

    @ApiOperation(value="随便获取一个字符串", notes = "")
    @GetMapping(value = "/getMsg")
    public Result<String> getMsg(){
        return ResultUtil.success("ffffffffffffffffffffffffffffffff");
    }

    @ApiOperation(value="ControllerAdvice去拦截异常", notes = "")
    @GetMapping(value = "/err")
    public void err(){
        int a[] = {};
        a[1] = 10;
    }

    @ApiOperation(value="赤裸裸的抛出业务异常", notes = "")
    @GetMapping(value = "/err2")
    public void err2(){
        throw new BusinessException(ResultEnum.PRIMARRY_SCHOOL);
    }

    @ApiOperation(value="jpa的查询方式", notes = "")
    @PostMapping(value = "/findByAge")
    public Result<List<Girl>> findByAge(@RequestParam("age") Integer age) {
        return ResultUtil.success(girlService.findByAge(age));
    }

    @ApiOperation(value="jpa的默认查询方式", notes = "")
    @PostMapping(value = "/findOne")
    public Result<List<Girl>> findOne(@RequestParam("id") Integer id) {
        return ResultUtil.success(girlService.findOne(id));
    }

    @ApiOperation(value="springboot的异常判断，女孩儿年龄不能小于10岁", notes = "")
    @PostMapping(value = "addGirl")
    public Result<Girl> addGirl(@Valid @RequestBody Girl girl, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new BusinessException(10,bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(girlService.addGirl(girl));
    }

    @ApiOperation(value="restfulApi形式", notes = "")
    @PostMapping(value = "/addGirlRestFul/age/{age}/cupSize/{cupSize}")
    public Result<Girl> addGirl(@PathVariable Integer age, @PathVariable String cupSize) {
        Girl girl = new Girl();
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return ResultUtil.success(girlService.addGirl(girl));
    }
}
