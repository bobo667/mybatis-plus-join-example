package icu.mhb.mpj.example.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.mhb.mpj.example.config.FuncKeyWordImpl;
import icu.mhb.mpj.example.entity.Users;
import icu.mhb.mpj.example.entity.UsersAge;
import icu.mhb.mpj.example.entity.chain.UsersAgeChain;
import icu.mhb.mpj.example.entity.chain.UsersChain;
import icu.mhb.mpj.example.mapper.UsersMapper;
import icu.mhb.mpj.example.service.UsersService;
import icu.mhb.mpj.example.vo.UsersVo;
import icu.mhb.mybatisplus.plugln.base.service.impl.JoinServiceImpl;
import icu.mhb.mybatisplus.plugln.extend.Joins;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author mahuibo
 * @Title: ChainUsersService
 * @email mhb0409@qq.com
 * @time 2024/6/22
 */
@Service
@Log4j2
public class ChainUsersServiceImpl extends JoinServiceImpl<UsersMapper, Users> implements UsersService {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public List<UsersVo> findByAgeName(String ageName) {
        log.info("66");
        Users users = new Users();
        users.setAgeId(1L);
        users.setUserName("6");

        UsersAge usersAge = new UsersAge();
        usersAge.setAgeName("ageName6");

        UsersChain usersChain = UsersChain.create()
                .setEntity(users);
        UsersAgeChain ageChain = UsersAgeChain.create()
                .setEntity(usersAge);

//        List<UsersVo> usersVoList = Joins.chain(usersChain)
//                .initEntityCondition(usersChain, ageChain)
//                .leftJoin(ageChain._id(), usersChain._ageId())
//                .joinList(UsersVo.class);
        usersMapper.joinSelectList(Joins.chain(usersChain)
                .initEntityCondition(usersChain, ageChain)
                .leftJoin(ageChain._id(), usersChain._ageId()),UsersVo.class);
        return Collections.emptyList();
    }

    @Override
    public List<UsersVo> setEntityTest() {
        Users users = new Users();
        users.setAgeId(1L);
        users.setUserName("6");

        UsersAge usersAge = new UsersAge();
        usersAge.setAgeName("ageName6");

        UsersChain usersChain = UsersChain.create()
                .setEntity(users);
        UsersAgeChain ageChain = UsersAgeChain.create()
                .setEntity(usersAge);

        List<UsersVo> usersVoList = Joins.chain(usersChain)
                .initEntityCondition(usersChain, ageChain)
                .leftJoin(ageChain._id(), usersChain._ageId())
                .joinList(UsersVo.class);
        return usersVoList;
    }

    @Override
    public List<UsersVo> testTypeHandler() {
        UsersChain usersChain = UsersChain.create();
        UsersAgeChain ageChain = UsersAgeChain.create();

        List<UsersVo> usersVoList = Joins.chain(usersChain)
                .setFuncKeyWord(new FuncKeyWordImpl())
                .distinct()
                .selectAs(() -> {
                    return usersChain.userId().ageId().contentJson()
                            .to(ageChain)
                            .ageName().id().contentJsonAge("contentJsonAge");
                })
                .leftJoin(ageChain._id(), usersChain._ageId())
                .joinList(UsersVo.class);

        return usersVoList;
    }

    @Override
    public List<UsersVo> indexOrder() {
        UsersChain usersChain = UsersChain.create();
        UsersAgeChain ageChain = UsersAgeChain.create();

        List<UsersVo> usersVoList = Joins.chain(usersChain)
                .setFuncKeyWord(new FuncKeyWordImpl())
                .distinct()
                .selectAs(() -> {
                    return usersChain.userId().ageId().contentJson()
                            .to(ageChain)
                            .ageName().id().contentJsonAge("contentJsonAge");
                })
                .leftJoin(ageChain._id(), usersChain._ageId())
                .orderBySql("users.user_id asc")
                .orderBySql("users_age.age_name desc")
                .orderByAsc(ageChain._id())
                .orderByDesc(usersChain._ageId())
                .joinList(UsersVo.class);
        return usersVoList;
    }

    @Override
    public List<UsersVo> oneToOne() {
        UsersChain usersChain = UsersChain.create();
        UsersAgeChain ageChain = UsersAgeChain.create();

        List<UsersVo> usersVoList = Joins.chain(usersChain)
                .selectAs(() -> {
                    return usersChain.userId().userName().createTime()
                            .userName("mpnb");
                })
                .selectAs("11", UsersVo::getAgeTableId)
                .leftJoin(ageChain._id(), usersChain._ageId())
//                .oneToOneSelect(UsersVo::getUsersAgeVo, () -> {
//                    return ageChain.ageName().id().createTime().ageDoc();
//                })
                .oneToOneSelect(UsersVo::getUsersAgeVo, ageChain)
                .joinList(UsersVo.class);

        return usersVoList;
    }

    @Override
    public UsersVo getByAgeName(String ageName) {
        return null;
    }

    @Override
    public List<UsersVo> allCondition() {
        UsersVo users = new UsersVo();
        users.setUserName("setUserName");
        users.setAgeName("setAgeName");
        UsersChain usersChain = UsersChain.create();
        UsersAgeChain ageChain = UsersAgeChain.create();

        return Joins.chain(usersChain)
                .selectAs(() -> {
                    return usersChain.userId().userName().createTime()
                            .to(ageChain)
                            .ageDoc().ageName().id();
                })
                .leftJoin(ageChain._id(), usersChain._ageId())
                .joinAnd(ageChain, (w) -> w.eq(ageChain._id(1)))
                .eqIfNull(() -> {
                    return usersChain.userName(users.getUserName())
                            .userId(users.getUserId())
                            .ageId(users.getAgeId())
                            .to(ageChain)
                            .ageName(users.getAgeName())
                            .ageDoc(users.getAgeDoc());
                }).joinList(UsersVo.class);
    }

    @Override
    public int getCountByAgeName(String ageName) {
        return 0;
    }

    @Override
    public Page<Users> page() {
        return null;
    }

    @Override
    public List<Integer> getIds() {
        return Collections.emptyList();
    }

    @Override
    public String getUserName() {
        return "";
    }

    @Override
    public List<Map> customizeAlias() {
        return Collections.emptyList();
    }

    @Override
    public List<UsersVo> automaticAlias() {
        return Collections.emptyList();
    }

    @Override
    public List<UsersVo> joinsTest() {
        return Collections.emptyList();
    }

    @Override
    public List<UsersVo> joinsAnd() {
        return Collections.emptyList();
    }
}
