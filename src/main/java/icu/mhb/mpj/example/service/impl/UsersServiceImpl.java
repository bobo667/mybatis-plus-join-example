package icu.mhb.mpj.example.service.impl;
import icu.mhb.mpj.example.entity.Users;
import icu.mhb.mpj.example.entity.UsersAge;
import icu.mhb.mpj.example.mapper.UsersMapper;
import icu.mhb.mpj.example.service.UsersService;
import icu.mhb.mpj.example.vo.UsersVo;
import icu.mhb.mybatisplus.plugln.base.service.impl.JoinServiceImpl;
import icu.mhb.mybatisplus.plugln.core.JoinLambdaWrapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author mahuibo
 * @Title: UsersServiceImpl
 * @time 9/25/21 5:54 PM
 */
@Service
public class UsersServiceImpl extends JoinServiceImpl<UsersMapper, Users> implements UsersService {

    @Override
    public List<UsersVo> findByAgeName(String ageName) {
        JoinLambdaWrapper<Users> wrapper = joinLambdaQueryWrapper(Users.class);

        wrapper.eq(Users::getUserId, 1)
                .orderByDesc(Users::getUserId)
                .groupBy(Users::getAgeId)
                // selectAll 如果有参数就代表是排除的
                // F解释下为啥用list接受不用可变数组接受，因为可变数组idea有警告看着难受
                .selectAll(Arrays.asList(Users::getUserName, Users::getAgeId));

        // 还可以有rightJoin innerJoin 使用，具体使用看场景
        wrapper.leftJoin(UsersAge.class, UsersAge::getId, Users::getAgeId)
                .joinAnd(UsersAge::getId, "1", 0)
                // selectAs 四种添加查询列的方式
                .selectAs((cb -> {
                    cb.add(Arrays.asList(UsersAge::getAgeDoc, UsersAge::getAgeName))
                            .add(UsersAge::getId)
                            // 此处为了演示哪怕你的字段不符合标准还是可以映射的
                            .add(UsersAge::getAgeName, "usersAgeName")
                            .add(UsersAge::getId, "age_table_id")
                            .add("", "mpnb");
                }))
                .eq(ageName != null, UsersAge::getAgeName, ageName)
                .orderByAsc(UsersAge::getId)
                .groupBy(UsersAge::getId)
                .end();

        return super.joinList(wrapper, UsersVo.class);
    }

}
