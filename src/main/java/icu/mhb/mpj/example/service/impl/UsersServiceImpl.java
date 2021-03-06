package icu.mhb.mpj.example.service.impl;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.mhb.mpj.example.entity.Users;
import icu.mhb.mpj.example.entity.UsersAge;
import icu.mhb.mpj.example.mapper.UsersMapper;
import icu.mhb.mpj.example.service.UsersService;
import icu.mhb.mpj.example.vo.UsersAgesVo;
import icu.mhb.mpj.example.vo.UsersVo;
import icu.mhb.mybatisplus.plugln.base.service.impl.JoinServiceImpl;
import icu.mhb.mybatisplus.plugln.core.JoinLambdaWrapper;
import lombok.SneakyThrows;
import org.apache.ibatis.reflection.property.PropertyNamer;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author mahuibo
 * @Title: UsersServiceImpl
 * @time 9/25/21 5:54 PM
 */
@Service
public class UsersServiceImpl extends JoinServiceImpl<UsersMapper, Users> implements UsersService {

    @Override
    public List<UsersVo> findByAgeName(String ageName) {
//        JoinLambdaWrapper<Users> wrapper = joinLambdaQueryWrapper(Users.class);
        // 如果需要根据实体查询可以采用这样的实例化
        JoinLambdaWrapper<Users> wrapper = joinLambdaQueryWrapper(new Users().setUserId(1L));
        // 或者可以采用这样的setEntity
//        wrapper.setEntity(new Users().setUserName("name啊"));
        wrapper
//                .eq(Users::getUserId, 1)
                .orderByDesc(Users::getUserId)
                // 因为默认是查询主表所有查询字段，如果不需要查询主表全部字段就调用该方法
//                .notDefaultSelectAll()
                .groupBy(Users::getAgeId)
                // selectAll 如果有参数就代表是排除的
                // F解释下为啥用list接受不用可变数组接受，因为可变数组idea有警告看着难受
                .selectAll(Arrays.asList(Users::getUserName, Users::getAgeId));

        // 还可以有rightJoin innerJoin 使用，具体使用看场景
        wrapper.leftJoin(UsersAge.class, UsersAge::getId, Users::getAgeId)
//                .joinAnd(UsersAge::getId, "1", 0)
                .select(UsersAge::getAgeDoc)
                // selectAs 四种添加查询列的方式
                .selectAs((cb -> {
                    cb.add(UsersAge::getAgeDoc, UsersAge::getAgeName)
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

    @Override
    public List<UsersAgesVo> test1(String ageName) {
        JoinLambdaWrapper<UsersAgesVo> wrapper = joinLambdaQueryWrapper(UsersAgesVo.class).eq(UsersAgesVo::getId, 1)
                .select(UsersAgesVo::getId);

        return super.joinList(wrapper, UsersAgesVo.class);
    }

    public static void main(String[] args) {
        LambdaMeta extract = LambdaUtils.extract(UsersAgesVo::getId);
        System.out.println(extract.getImplMethodName());
        System.out.println(extract.getInstantiatedClass());
    }

    @Override
    public List<UsersVo> oneToOne() {
        JoinLambdaWrapper<Users> wrapper = joinLambdaQueryWrapper(Users.class)
                .selectAs((cb) -> {
                    cb.add(Users::getUserId, Users::getUserName, Users::getCreateTime)
                            .add("11", "ageTableId")
                            .add(Users::getUserName, "mpnb");
                });

        wrapper.leftJoin(UsersAge.class, UsersAge::getId, Users::getAgeId)
                .oneToOneSelect(UsersVo::getUsersAge, (cb) -> {
                    cb.add(UsersAge::getAgeDoc, UsersAge::getAgeName)
                            .add(UsersAge::getId, "ageId", UsersAge::getId);
                }).end();

        return super.joinList(wrapper, UsersVo.class);
    }

    @Override
    public UsersVo getByAgeName(String ageName) {
        JoinLambdaWrapper<Users> wrapper = joinLambdaQueryWrapper(Users.class);

        wrapper.select(Users::getUserId, Users::getUserName)
                .leftJoin(UsersAge.class, UsersAge::getId, Users::getAgeId)
                .oneToOneSelect(UsersVo::getUsersAge, (cb) -> {
                    cb.add(UsersAge::getId, "ageId", UsersAge::getId)
                            .add(UsersAge::getAgeDoc, UsersAge::getAgeName);
                })
                .eq(UsersAge::getAgeName, ageName)
                .end()
                .last("limit 1");

        return super.joinGetOne(wrapper, UsersVo.class);
    }

    @Override
    public int getCountByAgeName(String ageName) {
        JoinLambdaWrapper<Users> wrapper = joinLambdaQueryWrapper(Users.class);

        wrapper.select(Users::getUserId, Users::getUserName)
                .leftJoin(UsersAge.class, UsersAge::getId, Users::getAgeId)
                .eq(UsersAge::getAgeName, ageName).end();

        return super.joinCount(wrapper);
    }


    @SneakyThrows
    @Override
    public Page<UsersVo> page() {
//        LambdaMeta lambdaMeta = LambdaUtils.extract(Users::getAgeId);
//        System.out.println(lambdaMeta.getInstantiatedClass().getDeclaredField(lambdaMeta.getImplMethodName()).getDeclaringClass());
        return super.joinPage(new Page<>(1, 100), new JoinLambdaWrapper<>(Users.class), UsersVo.class);
    }


    @Override
    public List<Integer> getIds() {

        JoinLambdaWrapper<Users> wrapper = joinLambdaQueryWrapper(Users.class)
                .select(Users::getUserId);

        return super.joinList(wrapper, Integer.class);
    }

    @Override
    public String getUserName() {

        JoinLambdaWrapper<Users> wrapper = joinLambdaQueryWrapper(Users.class)
                .select(Users::getUserName)
                .eq(Users::getUserId, 1)
                .last("limit 1");

        return super.joinGetOne(wrapper, String.class);
    }

    @Override
    public List<Map> customizeAlias() {
        // 两个参数代表自定义别名
        JoinLambdaWrapper<Users> wrapper = joinLambdaQueryWrapper(Users.class, "userMaster");

        wrapper
                .select(Users::getUserId, Users::getUserName)
                .leftJoin(UsersAge.class, UsersAge::getId, Users::getAgeId, "u_age")
                .select(UsersAge::getAgeDoc).end()
                .leftJoin(UsersAge.class, UsersAge::getId, Users::getAgeId, "u_a")
                .select(UsersAge::getAgeName).end();

        return super.joinList(wrapper, Map.class);
    }


}
