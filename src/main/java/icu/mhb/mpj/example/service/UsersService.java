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

    List<UsersVo> findByAgeName(String ageName);

}
