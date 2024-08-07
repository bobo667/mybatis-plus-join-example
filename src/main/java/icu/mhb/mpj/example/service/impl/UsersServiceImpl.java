package icu.mhb.mpj.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.mhb.mpj.example.config.FuncKeyWordImpl;
import icu.mhb.mpj.example.entity.Users;
import icu.mhb.mpj.example.entity.UsersAge;
import icu.mhb.mpj.example.entity.chain.UsersAgeChain;
import icu.mhb.mpj.example.entity.chain.UsersChain;
import icu.mhb.mpj.example.mapper.UsersMapper;
import icu.mhb.mpj.example.service.UsersService;
import icu.mhb.mpj.example.vo.UsersAgeVo;
import icu.mhb.mpj.example.vo.UsersVo;
import icu.mhb.mybatisplus.plugln.base.service.impl.JoinServiceImpl;
import icu.mhb.mybatisplus.plugln.core.JoinLambdaWrapper;
import icu.mhb.mybatisplus.plugln.core.chain.JoinChainQueryWrapper;
import icu.mhb.mybatisplus.plugln.entity.BaseChainModel;
import icu.mhb.mybatisplus.plugln.extend.Joins;
import icu.mhb.mybatisplus.plugln.tookit.StringUtils;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
        JoinLambdaWrapper<Users> wrapper = joinLambdaQueryWrapper(Users.class);
        // 或者可以采用这样的setEntity
//        wrapper.setEntity(new Users().setUserName("name啊"));
        wrapper
//                .eq(Users::getUserId, 1)
//                .orderByDesc(Users::getUserId)
                // 因为默认是查询主表所有查询字段，如果不需要查询主表全部字段就调用该方法
//                .notDefaultSelectAll()
//                .groupBy(Users::getAgeId)
                // selectAll 如果有参数就代表是排除的
                // F解释下为啥用list接受不用可变数组接受，因为可变数组idea有警告看着难受
                .selectAll(Arrays.asList(Users::getUserName, Users::getAgeId));

        QueryWrapper<UsersAge> wrapper2 = new QueryWrapper<>();
        wrapper2
                .isNotNull("id")
                .and(w -> {
                    w.and(w2 -> {
                        w2.and(w3 -> {
                            w3.and(w4 -> {
                                w4.and(w5 -> w5.like("id", 2).or()
                                        .le("id", 34));
                            });
                        });
                    });
                })
                .orderByDesc("id")
                .groupBy("id")
                .having("1={0}", 1);


        // 还可以有rightJoin innerJoin 使用，具体使用看场景
        wrapper.leftJoin(UsersAge.class, UsersAge::getId, Users::getAgeId, "u_age")
//                .joinAnd(UsersAge::getId, "1", 0)
                .select(UsersAge::getAgeDoc)
                .changeQueryWrapper(wrapper2)
                // selectAs 四种添加查询列的方式
                .selectAs((cb -> {
                    cb.add(UsersAge::getAgeDoc, UsersAge::getAgeName)
                            .add(UsersAge::getId)
                            .add(UsersAge::getContentJsonAge)
                            .add(UsersAge::getId, "ageTableId")
                            .add("1", "mpnb")
                            .add("sum(u_age.id)", "ageIds", false);
                }))
//                .eq(UsersAge::getAgeDoc, "12313")
//                .and(w -> w.eq(ageName != null, UsersAge::getAgeName, ageName).eq(UsersAge::getId, 1))
//                .orderByAsc(UsersAge::getId)
//                .groupBy(UsersAge::getId)
                .end();


        QueryWrapper<Users> wrapper1 = new QueryWrapper<>();

        wrapper1
                .isNotNull("user_id")
                .and(w -> {
                    w.and(w2 -> {
                        w2.and(w3 -> {
                            w3.and(w4 -> {
                                w4.and(w5 -> w5.like("user_id", 2).or()
                                        .le("user_id", 34));
                            });
                        });
                    });
                })
                .orderByDesc("user_id")
                .groupBy("user_id")
                .having("1={0}", 1);
        wrapper
                .isNotNull(Users::getUserId)
                .changeQueryWrapper(wrapper1);
        return super.joinList(wrapper, UsersVo.class);
    }

    @Override
    public List<UsersVo> setEntityTest() {
        return Collections.emptyList();
    }

    @Override
    public List<UsersVo> testTypeHandler() {
//        StringUtils
        JoinLambdaWrapper<Users> wrapper = joinLambdaQueryWrapper(Users.class)
                .setFuncKeyWord(new FuncKeyWordImpl())
                .distinct()
                .selectAs(w -> w.add(Users::getContentJson, Users::getUserId, Users::getAgeId))
                .leftJoin(UsersAge.class, UsersAge::getId, Users::getAgeId)
                .selectAs(w -> w.add(UsersAge::getContentJsonAge, "contentJsonAge")
                        .add(UsersAge::getAgeName, UsersAge::getId))
                .end();

        return super.joinList(wrapper, UsersVo.class);
    }

    @Override
    public List<UsersVo> indexOrder() {
        JoinLambdaWrapper<Users> wrapper = joinLambdaQueryWrapper(Users.class)
                .setFuncKeyWord(new FuncKeyWordImpl())
                .distinct()
                .orderByDesc(Users::getAgeId)
                .leftJoin(UsersAge.class, UsersAge::getId, Users::getAgeId)
                .select(UsersAge::getAgeName, UsersAge::getId)
                .orderByAsc(UsersAge::getId, 2)
                .orderBySql("users.user_id asc", 0)
                .end()
                .orderBySql("users_age.age_name desc", 1);

        return super.joinList(wrapper, UsersVo.class);
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
                .oneToOneSelect(UsersVo::getUsersAgeVo, (cb) -> {
                    cb.add(UsersAge::getAgeDoc, UsersAge::getAgeName)
                            .add(UsersAge::getCreateTime)
                            .add(UsersAge::getId);
                }).end();

        return super.joinList(wrapper, UsersVo.class);
    }

    @Override
    public UsersVo getByAgeName(String ageName) {
        JoinLambdaWrapper<Users> wrapper = joinLambdaQueryWrapper(Users.class);

        wrapper.select(Users::getUserId, Users::getUserId, Users::getUserName, Users::getContentJson)
                .leftJoin(UsersAge.class, UsersAge::getId, Users::getAgeId, "user_age")
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
    public List<UsersVo> allCondition() {
        UsersVo users = new UsersVo();
        users.setUserName("setUserName");
        users.setAgeName("setAgeName");

        return Joins.of(Users.class)
                .selectAs((cb) -> {
                    cb.add(Users::getUserId, Users::getUserName, Users::getCreateTime);
                })
                .leftJoin(UsersAge.class, UsersAge::getId, Users::getAgeId, w -> {
                    w.joinAnd(0, and -> and.eq(UsersAge::getId, 1))
                            .eq(StringUtils.isNotBlank(users.getAgeName()), UsersAge::getAgeName, users.getAgeName())
                            .eq(StringUtils.isNotBlank(users.getAgeDoc()), UsersAge::getAgeDoc, users.getAgeDoc())
                            .selectAs((cb) -> {
                                cb.add(UsersAge::getAgeDoc, UsersAge::getAgeName, UsersAge::getId);
                            });
                })
                .eq(StringUtils.isNotBlank(users.getUserName()), Users::getUserName, users.getUserName())
                .eq(ObjectUtils.isNotEmpty(users.getUserId()), Users::getUserId, users.getUserId())
                .eq(ObjectUtils.isNotEmpty(users.getAgeId()), Users::getAgeId, users.getAgeId())
                .joinList(UsersVo.class);
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
    public Page<Users> page() {
//        LambdaMeta lambdaMeta = LambdaUtils.extract(Users::getAgeId);
//        System.out.println(lambdaMeta.getInstantiatedClass().getDeclaredField(lambdaMeta.getImplMethodName()).getDeclaringClass());
        List<Users> usersList = joinLambdaQueryWrapper()
                .joinList();
        Users joinGetOne = joinLambdaQueryWrapper()
                .last("limit 1")
                .joinGetOne();
        Page<Users> page = new Page<>(1, 100);
        return joinLambdaQueryWrapper().joinPage(page);
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
                .select(Users::getUserId)
                .select(Users::getUserName)
                .leftJoin(UsersAge.class, UsersAge::getId, Users::getAgeId, "u_age")
                .eq(UsersAge::getId, 1)
                .select(UsersAge::getAgeDoc).end()
                .leftJoin(UsersAge.class, UsersAge::getId, Users::getAgeId, "u_a")
                .select(UsersAge::getAgeName).end();

        return super.joinList(wrapper, Map.class);
    }

    @Override
    public List<UsersVo> automaticAlias() {

        JoinLambdaWrapper<Users> wrapper = joinLambdaQueryWrapper(Users.class)
//                .selectAs(Users::getUserName, UsersVo::getMpnb)
//                .selectAs(cb -> {
//                    cb.add(Users::getUserId)
//                            .add(Users::getUserName, Users::getAgeId)
//                            .addFunAlias(Users::getCreateTime, UsersVo::getCreateTime)
//                            .add(Users::getAgeId, "ageId");
//                })
//                .leftJoin(UsersAge.class, UsersAge::getId, Users::getAgeId)
//                .selectAs(UsersAge::getAgeName, UsersVo::getMpnb)
////                .selectAs(cb -> {
////                    cb.add(UsersAge::getCreateTime)
////                            .add(UsersAge::getAgeName, "ageName")
////                            .add(UsersAge::getId);
////                })
//                .end();

                .leftJoin(UsersAge.class, UsersAge::getId, Users::getAgeId, "t1", w ->
                        w.eq(UsersAge::getId, 1).selectAs(UsersAge::getAgeName, UsersVo::getMpnb)
                ).selectAs(cb -> {
                    cb.add("count(id)", "asdasd");
                });

        return super.joinList(wrapper, UsersVo.class);
    }

    @Override
    public List<UsersVo> joinsTest() {

        List<UsersVo> list = Joins.of(Users.class)
                .pushLeftJoin(UsersVo::getUsersAge, UsersAge.class)
                .eq(UsersAge::getId, Users::getAgeId).end()
                .eq(Users::getAgeId, UsersAge::getId)
                .le(Users::getAgeId, UsersAge::getId)
                .lt(Users::getAgeId, UsersAge::getId)
                .ge(Users::getAgeId, UsersAge::getId)
                .gt(Users::getAgeId, UsersAge::getId)
                .ne(Users::getAgeId, UsersAge::getId)
                .between(Users::getAgeId, UsersAge::getId, UsersAge::getAgeName)
                .notBetween(Users::getAgeId, UsersAge::getId, UsersAge::getAgeName)
                .joinList(UsersVo.class);

//        Page<UsersAgesVo> usersAgesVoPage = new Page<>(1, 10);
//        Page<UsersAgesVo> joinList = Joins.of(UsersAge.class)
//                .leftJoin(Users.class, Users::getAgeId, UsersAge::getId)
//                .manyToManySelect(UsersAgesVo::getUsersList, Users.class).end()
//                .joinPage(usersAgesVoPage, UsersAgesVo.class);


        return Joins.of(Users.class)
                .leftJoin(UsersAge.class, UsersAge::getId, Users::getAgeId)
                .oneToOneSelect(UsersVo::getUsersAge, UsersAge.class).end()
                .joinList(UsersVo.class);
    }

    @Override
    public List<UsersVo> joinsAnd() {
        return Joins.of(Users.class)
                .leftJoin(UsersAge.class, UsersAge::getId, Users::getAgeId, w -> w.joinAnd(0, and -> {
                }))
                .selectSunQuery(UsersAge.class, w -> {
                    w.selectAs(cb -> {
                                cb.add(UsersAge::getAgeDoc, "cccccc")
                                        .add("IFNULL(count(1),0)", "ass", false);
                            })
                            .eq(UsersAge::getId, Users::getAgeId);
                })
                /*
                SELECT
                    users.user_name,users.create_time,users.age_id,users.content_json,users.user_id,
                    (SELECT users_age.age_doc as cccccc FROM users_age users_age where (users_age.id = users.age_id) ) as cccccc ,
                    (SELECT IFNULL(count(1),0) as ass FROM users_age users_age where (users_age.id = users.age_id) ) as ass
                 FROM users as users
                 LEFT JOIN users_age as users_age ON users_age.id = users.age_id
                 */
                .joinList(UsersVo.class);
    }


}
