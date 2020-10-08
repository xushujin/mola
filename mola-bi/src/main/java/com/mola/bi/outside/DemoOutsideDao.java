package com.mola.bi.outside;

import com.mola.common.pojo.dto.DemoDto;
import com.mola.core.feign.FeignLogConfiguration;
import com.mola.core.response.success.SuccessVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * demo
 *
 * @author hatim
 */
@Component
@FeignClient(configuration = {FeignLogConfiguration.class},
        name = "demo",
        url = "http://localhost:8100/rpc/demo")
public interface DemoOutsideDao {

    /**
     * demo2
     *
     * @return
     */
    @GetMapping("/demo2")
    String demo2();

    /**
     * demo3
     *
     * @return
     */
    @GetMapping("/demo3")
    SuccessVo<DemoDto> demo3();

    /**
     * ex1
     *
     * @return
     */
    @GetMapping("/ex1")
    SuccessVo<DemoDto> ex1();

    /**
     * ex2
     *
     * @return
     */
    @GetMapping("/ex2")
    SuccessVo<DemoDto> ex2();


}
