package icu.mhb.mpj.example.service;
import icu.mhb.mpj.example.entity.Users;
import icu.mhb.mpj.example.vo.UsersVo;
import icu.mhb.mybatisplus.plugln.base.service.JoinIService;

import java.util.List;

/**
 * @author mahuibo
 * @Title: UsersService
 * @time 9/25/21 5:53 PM
 */
public interface UsersService extends JoinIService<Users> {

    /**
     * 基础多表示例
     */
    List<UsersVo> findByAgeName(String ageName);

    /**
     * 一对一实例
     */
    List<UsersVo> oneToOne();

    /**
     * 获取单个id列表示例
     *
     * @return id列表
     */
    List<Integer> getIds();

    /**
     * 获取单个姓名示例
     *
     * @return 姓名
     */
    String getUserName();

}
