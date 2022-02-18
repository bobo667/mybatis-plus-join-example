package icu.mhb.mpj.example.service;
import icu.mhb.mpj.example.entity.UsersAge;
import icu.mhb.mpj.example.vo.UsersAgesVo;
import icu.mhb.mybatisplus.plugln.base.service.JoinIService;

import java.util.List;

/**
 * @author mahuibo
 * @Title: UsersAgeMapper
 * @time 9/25/21 5:53 PM
 */
public interface UsersAgeService extends JoinIService<UsersAge> {

    /**
     * 多对多实例
     */
    List<UsersAgesVo> manyToMany();


}
