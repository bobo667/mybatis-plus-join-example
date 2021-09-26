package icu.mhb.mpj.example;

import com.alibaba.fastjson.JSON;
import icu.mhb.mpj.example.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MybatisPlusExampleApplicationTests {

    @Resource
    private UsersService usersService;

    @Test
    void contextLoads() {
        System.out.println(JSON.toJSONString(usersService.findByAgeName("95")));
    }

}
