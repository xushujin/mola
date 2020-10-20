package com.mola.authx.web.rpc;

import com.mola.authx.dao.LoginUserDao;
import com.mola.authx.enyity.LoginUser;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("rpc/demo")
public class DemoController {
    @Autowired
    LoginUserDao loginUserDao;

    @ApiOperation(value = "test1", notes = "test1")
    @GetMapping("/test1")
    public Flux<LoginUser> test1() {
        return loginUserDao.findAll();
    }

    @ApiOperation(value = "test2", notes = "test2")
    @GetMapping("/test2")
    public Mono<LoginUser> test2(String username) {
        return loginUserDao.findByUsername(username);
    }
}
