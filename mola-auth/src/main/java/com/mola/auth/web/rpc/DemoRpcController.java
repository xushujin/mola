package com.mola.auth.web.rpc;

import com.mola.auth.dao.EmployeeDao;
import com.mola.auth.enyity.Employee;
import com.mola.common.pojo.dto.DemoDto;
import com.mola.core.helper.AssertHelper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * demo
 *
 * @author hatim
 */
@Slf4j
@RestController
@RequestMapping("rpc/demo")
public class DemoRpcController {

    @ApiOperation(value = "demo1", notes = "demo1")
    @GetMapping("/demo1")
    public ResponseEntity get1() {
        log.info("demo");
        return null;
    }

    @ApiOperation(value = "demo2", notes = "demo2")
    @GetMapping("/demo2")
    public String get2() {
        log.info("demo2");
        return "成功";
    }

    @ApiOperation(value = "demo3", notes = "demo3")
    @GetMapping("/demo3")
    public DemoDto get3() {
        log.info("demo3");
        return DemoDto.builder().id("123454").name("hatim").build();
    }

    @ApiOperation(value = "ex1", notes = "ex1")
    @GetMapping("/ex1")
    public String ex1() {
        AssertHelper.check(false, HttpStatus.UNPROCESSABLE_ENTITY, "ex1");
        return null;
    }

    @ApiOperation(value = "ex2", notes = "ex2")
    @GetMapping("/ex2")
    public String ex2() {
//        try {
//            Thread.sleep(1000 * 60 * 2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        AssertHelper.cast(HttpStatus.UNAUTHORIZED, "ex2");
        return null;
    }


    @Autowired
    EmployeeDao employeeDao;

    @ApiOperation(value = "test1", notes = "test1")
    @GetMapping("/test1")
    public Employee test1(String key) {
        return employeeDao.selectByPrimaryKey(key);
    }

    @ApiOperation(value = "test2", notes = "test2")
    @GetMapping("/test2")
    public List<Employee> test2() {
        return employeeDao.select(Employee.builder().account("hatim").build());
    }

    public static void main(String[] args) {
        log.info("{}", 0 % 3);
        log.info("{}", 1 % 3);
        log.info("{}", 2 % 3);
        log.info("{}", 3 % 3);
        log.info("{}", 4 % 3);
        log.info("{}", 5 % 3);
        log.info("{}", 6 % 3);
        log.info("{}", 7 % 3);
        log.info("{}", 8 % 3);
        log.info("{}", 9 % 3);

        log.info("key:{}", new SnowflakeShardingKeyGenerator().generateKey());
    }
}
