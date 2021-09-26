package icu.mhb.mpj.example.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import icu.mhb.mybatisplus.plugln.injector.JoinDefaultSqlInjector;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MyBatisPlusConfig extends JoinDefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        return super.getMethodList(mapperClass);
    }

}
