package icu.mhb.mpj.example.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusPropertiesCustomizer;
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
public class MyBatisPlusConfig extends JoinDefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        return super.getMethodList(mapperClass, tableInfo);
    }

    @Bean
    public MybatisPlusPropertiesCustomizer plusPropertiesCustomizer() {
        MybatisPlusJoinConfig.builder()
                // 查询字段别名关键字
                .columnAliasKeyword("as")
                // 表、left join、right join、inner join 表别名关键字
                .tableAliasKeyword("as")
                .build();
        return MybatisPlusProperties::getGlobalConfig;
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
