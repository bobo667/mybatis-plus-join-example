package icu.mhb.mpj.example;

import icu.mhb.mpj.example.service.UsersService;
import icu.mhb.mpj.example.vo.UsersVo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@SpringBootApplication
@MapperScan("icu.mhb.mpj.example.mapper")
@RestController
@EnableAspectJAutoProxy
public class MybatisPlusExampleApplication {


    @Resource(name = "chainUsersServiceImpl")
    private UsersService usersService;

    @GetMapping("/")
    public String query() {
        usersService.findByAgeName("5");
        return "6666";
    }

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusExampleApplication.class, args);
    }

}
