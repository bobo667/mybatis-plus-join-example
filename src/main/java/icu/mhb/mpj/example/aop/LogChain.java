package icu.mhb.mpj.example.aop;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mahuibo
 * @Title: LogChain
 * @email mhb0409@qq.com
 * @time 2024/6/27
 */
@Data
public class LogChain {

    private String id;

    private AtomicInteger count = new AtomicInteger();

}
