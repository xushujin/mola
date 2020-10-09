package com.mola.auth.web;

import com.mola.core.helper.AssertHelper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * demo
 *
 * @author hatim
 */
@Slf4j
@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    RedisIndexedSessionRepository redisIndexedSessionRepository;

    @ApiOperation(value = "user", notes = "user")
    @GetMapping("/user")
    public ResponseEntity<String> user(HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        log.info("sessionId:{},request.getSession().getId:{}", sessionId, request.getSession().getId());
        Session session = redisIndexedSessionRepository.findById(sessionId);
        log.info("session:{}", session.getId());
        return null;
    }

    @ApiOperation(value = "demo1", notes = "demo1")
    @GetMapping("/demo1")
    public ResponseEntity<String> get1() {
        log.info("demo");
        return null;
    }

    @ApiOperation(value = "demo2", notes = "demo2")
    @GetMapping("/demo2")
    public Boolean get2() {
        log.info("demo2");
        HashMap<String, Object> data = new HashMap<>();
        data.put("code", "succ");
        return true;
    }

    @ApiOperation(value = "demo3", notes = "demo3")
    @GetMapping("/demo3")
    public String get3() {
        log.info("demo2");
        HashMap<String, Object> data = new HashMap<>();
        data.put("code", "succ");
        return "succ";
    }

    @ApiOperation(value = "ex1", notes = "ex1")
    @GetMapping("/ex1")
    public ResponseEntity ex1() {
        AssertHelper.check(false, HttpStatus.UNPROCESSABLE_ENTITY, "ex1");
        return null;
    }

    @ApiOperation(value = "ex2", notes = "ex2")
    @GetMapping("/ex2")
    public ResponseEntity ex2() {
        AssertHelper.cast(HttpStatus.UNPROCESSABLE_ENTITY, "ex2");
        return null;
    }

    @ApiOperation(value = "ex3", notes = "ex2")
    @GetMapping("/ex3")
    public ResponseEntity ex3() {
        AssertHelper.cast(HttpStatus.UNPROCESSABLE_ENTITY, "ex2");
        return null;
    }

    @ApiOperation(value = "ex4", notes = "ex2")
    @GetMapping("/ex4")
    public ResponseEntity ex4() {
        AssertHelper.cast(HttpStatus.UNPROCESSABLE_ENTITY, "ex2");
        return null;
    }
}
