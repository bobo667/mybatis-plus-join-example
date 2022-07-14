package icu.mhb.mpj.example.service.impl;
import icu.mhb.mpj.example.entity.UsersAge;
import icu.mhb.mpj.example.mapper.UsersAgeMapper;
import icu.mhb.mpj.example.service.UsersAgeService;
import icu.mhb.mpj.example.vo.UsersAgesVo;
import icu.mhb.mybatisplus.plugln.base.service.impl.JoinServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mahuibo
 * @Title: UsersAgeMapper
 * @time 9/25/21 5:53 PM
 */
@Service
public class UsersAgeServiceImpl extends JoinServiceImpl<UsersAgeMapper, UsersAge> implements UsersAgeService {

    @Override
    public List<UsersAgesVo> manyToMany() {
//        JoinLambdaWrapper<UsersAge> wrapper = joinLambdaQueryWrapper(UsersAge.class);
//
//        wrapper.leftJoin(Users.class, Users::getAgeId, UsersAge::getId)
//                .manyToManySelect(UsersAgesVo::getUsersList, Users.class, (cb) -> {
//                    cb.add(Users::getUserName, Users::getUserId, Users::getCreateTime);
//                }).end();
//        return super.joinList(wrapper, UsersAgesVo.class);
        return super.joinList(joinLambdaQueryWrapper(UsersAge.class), UsersAgesVo.class);
    }

}
