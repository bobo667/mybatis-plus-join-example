package icu.mhb.mpj.example.aop;

import com.alibaba.fastjson.JSON;
import icu.mhb.mybatisplus.plugln.tookit.IdUtil;
import icu.mhb.mybatisplus.plugln.tookit.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mahuibo
 * @Title: LogAop
 * @email mhb0409@qq.com
 * @time 2024/6/27
 */
@Component
// lombok 的日志注解
// 该注解 标识这是个aop类
@Aspect
public class LogAop {

    /**
     * 切入被该注解标志的方法切入
     */
    @Pointcut("execution(* icu.mhb.mpj.example..*(..))")
    public void logPointcut() {

    }

    /**
     * 前置切入
     */
    @Around("logPointcut()")
    public Object doBefore(ProceedingJoinPoint pjp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
        LogChain logChain = RequestContext.get();
        if (null == logChain) {
            logChain = new LogChain();
            logChain.setId(IdUtil.getSimpleUUID());
            RequestContext.set(logChain);
        }
        logChain.getCount().addAndGet(1);
        // 获取方法参数
        Object[] args = pjp.getArgs();

//        log.info("请求路径：" + request.getRequestURL().toString());
//        log.info("请求类型:" + request.getMethod());
//        Enumeration<String> enu = request.getParameterNames();
//        while (enu.hasMoreElements()) {
//            String name = enu.nextElement();
//            log.info("属性名:{},属性值:{}", name, request.getParameter(name));
//        }

        System.out.println("链路请求编号：" + logChain.getId() + ",当前层级" + logChain.getCount().get());
        try {
            System.out.println("传参：" + JSON.toJSONString(args));
            Object proceed = pjp.proceed();
            System.out.println("方法返回：" + JSON.toJSONString(proceed));
            return proceed;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            int count = logChain.getCount().decrementAndGet();
            System.out.println("结果层级：" + count);
            if (count == 0) {
                System.out.println("清理成功：" + count);
                RequestContext.remove();
            }
        }
    }

}
