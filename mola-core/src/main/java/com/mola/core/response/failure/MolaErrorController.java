package com.mola.core.response.failure;

import com.mola.core.response.failure.pojo.FailureEntity;
import com.mola.core.response.success.pojo.SuccessEntity;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 全局异常控制器
 *
 * @author hatim
 */
@Slf4j
@Controller
@RestControllerAdvice
@RequestMapping({"${server.error.path}"})
public class MolaErrorController extends AbstractErrorController {

    private final ErrorAttributes errorAttributes;

    public MolaErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
        this.errorAttributes = errorAttributes;
    }

    /**
     * (@ExceptionHandler)捕获不到的异常这里捕获
     *
     * @param request
     * @return
     */
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity error(HttpServletRequest request) {
        HttpStatus status = this.getStatus(request);
        WebRequest webRequest = new ServletWebRequest(request);
        ErrorAttributeOptions options = ErrorAttributeOptions.defaults();
        options.including(ErrorAttributeOptions.Include.EXCEPTION,
                ErrorAttributeOptions.Include.BINDING_ERRORS,
                ErrorAttributeOptions.Include.STACK_TRACE,
                ErrorAttributeOptions.Include.MESSAGE);
        Map<String, Object> error = this.errorAttributes.getErrorAttributes(webRequest, options);
        return response(status.value(), FailureEntity.builder().msg(error.toString()).build());
    }

    /**
     * 业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MolaException.class)
    public ResponseEntity exception(MolaException e) {
        log.error("CCException:", e);
        if (e.isOk()) {
            // 返回请求成功视图（业务code）
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(SuccessEntity.builder().code(e.getCode()).msg(e.getMsg()).build());
        }
        return response(Integer.parseInt(e.getCode()), FailureEntity.builder().msg(e.getMsg()).build());
    }

    /**
     * 未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity exception(Exception e) {
        log.error("authenticationException:", e);
        return response(HttpStatus.INTERNAL_SERVER_ERROR.value(), FailureEntity.builder().msg("未知异常!请联系管理员。").build());
    }

    /**
     * 返回异常报文
     *
     * @param code
     * @param msg
     * @return
     */
    public ResponseEntity response(int code, FailureEntity msg) {
        // MDC清理
        MDC.clear();
        return ResponseEntity.status(code).body(msg);
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
