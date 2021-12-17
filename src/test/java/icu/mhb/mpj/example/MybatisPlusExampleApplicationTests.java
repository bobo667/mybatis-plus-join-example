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

    @Test
    void testIds() {
        System.out.println(JSON.toJSONString(usersService.getIds()));
    }

    @Test
    void testOneToOne() {
        System.out.println(JSON.toJSONString(usersService.oneToOne()));
    }

    @Test
    void testUserName() {
        System.out.println(usersService.getUserName());
    }

}
