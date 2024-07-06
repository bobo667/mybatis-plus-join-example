package icu.mhb.mpj.example.aop;

import java.util.List;
import java.util.Map;

public class RequestContext {
    private static final ThreadLocal<LogChain> context = new ThreadLocal<>();

    public static void set(LogChain value) {
        context.set(value);
    }

    public static LogChain get() {
        return context.get();
    }

    public static void remove() {
        context.remove();
    }
}
