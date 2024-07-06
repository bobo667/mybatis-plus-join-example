package icu.mhb.mpj.example.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import icu.mhb.mpj.example.entity.TestUserJson;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mahuibo
 * @Title: TestUserJsonTypeHandler
 * @email mhb0409@qq.com
 * @time 2024/1/3
 */
@MappedTypes({Object.class})
@MappedJdbcTypes({JdbcType.VARCHAR})
public class TestUserJsonTypeHandler extends AbstractJsonTypeHandler<Object> {
    private static final Logger log = LoggerFactory.getLogger(FastjsonTypeHandler.class);
    private final Class<?> type;

    public TestUserJsonTypeHandler(Class<?> type) {
        if (log.isTraceEnabled()) {
            log.trace("FastjsonTypeHandler(" + type + ")");
        }

        Assert.notNull(type, "Type argument cannot be null", new Object[0]);
        this.type = type;
    }

    protected TestUserJson parse(String json) {
        return JSON.parseObject(json, TestUserJson.class);
    }

    protected String toJson(Object obj) {
        return JSON.toJSONString(obj, new SerializerFeature[]{SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty});
    }

}
