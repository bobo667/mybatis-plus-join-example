package icu.mhb.mpj.example.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import icu.mhb.mybatisplus.plugln.config.MybatisPlusJoinConfig;
import icu.mhb.mybatisplus.plugln.injector.JoinDefaultSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MyBatisPlusConfig {

//    @Bean
//    public JoinDefaultSqlInjector joinDefaultSqlInjector() {
//        return new JoinDefaultSqlInjector(new JoinDefaultSqlInjector());
//    }


    @Bean
    public MybatisPlusJoinConfig mybatisPlusJoinConfig() {
        return MybatisPlusJoinConfig.builder()
                // 查询字段别名关键字
                .columnAliasKeyword("as")
                // 表、left join、right join、inner join 表别名关键字
                .tableAliasKeyword("as")
                /*
                  是否使用MappedStatement缓存，如果使用在JoinInterceptor中就会更改
                  MappedStatement的id，导致mybatis-plus-mate 的某些拦截器插件报错，
                  设置成false，代表不使用缓存则不会更改MappedStatement的id
                 */
                .isUseMsCache(true)
                .build();
    }

//    @Bean
//    public MybatisPlusJoinConfig mybatisPlusJoinConfig() {
//        return MybatisPlusJoinConfig.builder()
//                // 查询字段别名关键字
//                .columnAliasKeyword("as")
//                // 表、left join、right join、inner join 表别名关键字
//                .tableAliasKeyword("is")
//                .build();
//    }


    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }


}
