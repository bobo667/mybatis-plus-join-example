package icu.mhb.mpj.example;

import com.alibaba.fastjson.JSON;
import icu.mhb.mpj.example.entity.Users;
import icu.mhb.mpj.example.service.UsersAgeService;
import icu.mhb.mpj.example.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MybatisPlusExampleApplicationTests {

    @Resource
    private UsersService usersService;

    @Resource
    private UsersAgeService usersAgeService;

    @Test
    void contextLoads() {
        System.out.println(JSON.toJSONString(usersService.findByAgeName("90")));
    }

    @Test
    void getByAgeName() {
        System.out.println(JSON.toJSONString(usersService.getByAgeName("90")));
    }
    @Test
    void getByAgeName1() {
        System.out.println(JSON.toJSONString(usersService.test1("90")));
    }

    @Test
    void getCountByAgeName() {
        System.out.println(JSON.toJSONString(usersService.getCountByAgeName("90")));
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
    void testManyToMany() {
        System.out.println(JSON.toJSONString(usersAgeService.manyToMany()));
    }

    @Test
    void testUserName() {
        System.out.println(usersService.getUserName());
    }

    @Test
    void testPage() {
        System.out.println(JSON.toJSONString(usersService.page()));
    }

    @Test
    void testAliasAndReturnMap() {
        System.out.println(JSON.toJSONString(usersService.customizeAlias()));
    }

}
