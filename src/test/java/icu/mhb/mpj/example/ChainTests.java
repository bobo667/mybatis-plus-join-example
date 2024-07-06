package icu.mhb.mpj.example;

import com.alibaba.fastjson.JSON;
import icu.mhb.mpj.example.service.UsersAgeService;
import icu.mhb.mpj.example.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author mahuibo
 * @Title: ChainTests
 * @email mhb0409@qq.com
 * @time 2024/6/23
 */
@SpringBootTest
public class ChainTests {

    @Resource(name = "chainUsersServiceImpl")
    private UsersService usersService;

    @Resource
    private UsersAgeService usersAgeService;

    @Test
    void setEntityTest() {
        System.out.println(JSON.toJSONString(usersService.setEntityTest()));
    }

    @Test
    void contextLoads() {
        // 创建并启动10个线程
//        for (int i = 0; i < 20; i++) {
//            new Thread(() -> usersService.findByAgeName("90"), "Thread-" + i).start();
//        }
//        System.out.println(JSON.toJSONString(usersService.findByAgeName("90")));
        System.out.println(JSON.toJSONString(usersService.findByAgeName("90")));
    }

    @Test
    void getByAgeName() {
        System.out.println(JSON.toJSONString(usersService.getByAgeName("90")));
    }

    @Test
    void testTypeHandler() {
        System.out.println(JSON.toJSONString(usersService.testTypeHandler()));
    }

    @Test
    void getCountByAgeName() {
        System.out.println(JSON.toJSONString(usersService.getCountByAgeName("90")));
    }

    //
    @Test
    void testIds() {
        System.out.println(JSON.toJSONString(usersService.getIds()));
    }

    @Test
    void indexOrder() {
        System.out.println(JSON.toJSONString(usersService.indexOrder()));
    }

    @Test
    void testOneToOne() {
        System.out.println(JSON.toJSONString(usersService.oneToOne()));
    }
    @Test
    void allCondition() {
        System.out.println(JSON.toJSONString(usersService.allCondition()));
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

    @Test
    void automaticAlias() {
        System.out.println(JSON.toJSONString(usersService.automaticAlias()));
    }

    @Test
    void joinsTest() {
        System.out.println(JSON.toJSONString(usersService.joinsTest()));
    }

    @Test
    void joinAnd() {
        System.out.println(JSON.toJSONString(usersService.joinsAnd()));
    }


}
