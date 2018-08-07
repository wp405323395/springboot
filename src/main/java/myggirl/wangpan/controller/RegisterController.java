package myggirl.wangpan.controller;

import myggirl.wangpan.domain.User;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaoxinguo on 2018/06/05.
 */
@RestController
@RequestMapping("/users")
public class RegisterController extends BaseController {

    /**
     * 注册用户 默认开启白名单
     * @param user
     */

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        User bizUser = userRepository.findByUsername(user.getUsername());
        if(null != bizUser){
            throw new RuntimeException("用户已经存在");
        }
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword()).getBytes()));
        return userRepository.save(user);
    }

}
