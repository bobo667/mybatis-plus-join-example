package icu.mhb.mpj.example.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LogInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    @Before("execution(* org.slf4j.Logger.info(..))")
    public void beforeLogInfo() {
        logger.info("Intercepted log.info call");
        // 这里可以添加自定义逻辑
    }
}
