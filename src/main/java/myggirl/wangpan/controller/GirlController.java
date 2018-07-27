package myggirl.wangpan.controller;

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

@RestController
@RequestMapping("/school")
public class GirlController {
    @Autowired
    private GirlService girlService;

    @GetMapping(value = "/girls")
    public Result<List<Girl>> girlList(){
        return ResultUtil.success(girlService.girlList());
    }

    @GetMapping(value = "/getMsg")
    public Result<String> getMsg(){
        return ResultUtil.success("ffffffffffffffffffffffffffffffff");
    }

    @GetMapping(value = "/err")
    public void err(){
        int a[] = {};
        a[1] = 10;
    }

    @GetMapping(value = "/err2")
    public void err2(){
        throw new BusinessException(ResultEnum.PRIMARRY_SCHOOL);
    }

    @PostMapping(value = "/findByAge")
    public Result<List<Girl>> findByAge(@RequestParam("age") Integer age) {
        return ResultUtil.success(girlService.findByAge(age));
    }

    @PostMapping(value = "/findOne")
    public Result<List<Girl>> findOne(@RequestParam("id") Integer id) {
        return ResultUtil.success(girlService.findOne(id));
    }

//    /**
//     * 这里是用form表单形式传递数据的。
//     * @param girl
//     * @return
//     */
//    @PostMapping(value = "addGirl")
//    public Result<Girl> addGirl(@Valid Girl girl, BindingResult bindingResult) {
//        if(bindingResult.hasErrors()) {
//            throw new BusinessException(10,bindingResult.getFieldError().getDefaultMessage());
//        }
//        return ResultUtil.success(girl);
//        //return ResultUtil.success(girlService.addGirl(girl));
//    }

    /**
     * 这里是用form表单形式传递数据的。
     * @return
     */
    @PostMapping(value = "/addGirl/age/{age}/cupSize/{cupSize}")
    public Result<Girl> addGirl(@PathVariable Integer age, @PathVariable String cupSize) {
        Girl girl = new Girl();
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return ResultUtil.success(girlService.addGirl(girl));
    }
}
