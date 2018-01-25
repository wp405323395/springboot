package myggirl.wangpan.controller;

import myggirl.wangpan.ExceptionHandle.Exception.GirlException;
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

@RestController
@RequestMapping("/school")
public class GirlController {
    @Autowired
    private GirlService girlService;
    @GetMapping(value = "/girls")
    public Result<List<Girl>> girlList(){
        return ResultUtil.success(girlService.girlList());
    }

    @GetMapping(value = "/err")
    public void err(){
        int a[] = {};
        a[1] = 10;
    }

    @GetMapping(value = "/err2")
    public void err2(){
        throw new GirlException(ResultEnum.PRIMARRY_SCHOOL);
    }

    @PostMapping(value = "/findByAge")
    public Result<List<Girl>> findByAge(@RequestParam("age") Integer age) {
        return ResultUtil.success(girlService.findByAge(age));
    }

    /**
     * 这里是用form表单形式传递数据的。
     * @param girl
     * @return
     */
    @PostMapping(value = "addGirl")
    public Result<Girl> addGirl(@Valid Girl girl, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new GirlException(10,bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(girlService.addGirl(girl));
    }
}
