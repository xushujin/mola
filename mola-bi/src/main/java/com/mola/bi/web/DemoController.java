package com.mola.bi.web;

import com.mola.bi.outside.DemoOutsideDao;
import com.mola.common.pojo.dto.DemoDto;
import com.mola.core.response.success.MolaResponseBody;
import com.mola.core.response.success.SuccessVo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo
 *
 * @author hatim
 */
@Slf4j
@RestController
@MolaResponseBody
@RequestMapping("demo")
public class DemoController {
    @Autowired
    DemoOutsideDao demoOutsideDao;

    @ApiOperation(value = "demo2", notes = "demo2")
    @GetMapping("/demo2")
    public String get2() {
        log.info("demo2");
        String result = demoOutsideDao.demo2();
        log.info("result:{}", result);
        return "成功2";
    }

    @ApiOperation(value = "demo3", notes = "demo3")
    @GetMapping("/demo3")
    public String get3() {
        log.info("demo3");
        SuccessVo<DemoDto> result = demoOutsideDao.demo3();
        log.info("result:{}", result.getData());
        SuccessVo<DemoDto> result2 = demoOutsideDao.ex1();
        return "成功3";
    }

    @ApiOperation(value = "demo4", notes = "demo4")
    @GetMapping("/demo4")
    public String demo4() {
        log.info("demo4");
        return "demo4";
    }

    @ApiOperation(value = "ex2", notes = "ex2")
    @GetMapping("/ex2")
    public String ex2() {
        SuccessVo<DemoDto> result2 = demoOutsideDao.ex2();
        return "成功ex2";
    }
}
