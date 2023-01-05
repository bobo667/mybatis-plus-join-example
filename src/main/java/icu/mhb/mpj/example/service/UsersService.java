package icu.mhb.mpj.example.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.mhb.mpj.example.entity.Users;
import icu.mhb.mpj.example.vo.UsersVo;
import icu.mhb.mybatisplus.plugln.base.service.JoinIService;

import java.util.List;
import java.util.Map;

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
     * 测试@TableField(typeHandler = xx.class)
     *
     * @return
     */
    List<UsersVo> testTypeHandler();

    /**
     * 排序相关
     */
    List<UsersVo> indexOrder();

    /**
     * 一对一实例
     */
    List<UsersVo> oneToOne();

    /**
     * 获取单个实例
     */
    UsersVo getByAgeName(String ageName);

    /**
     * 获取count实例
     *
     * @return
     */
    int getCountByAgeName(String ageName);

    /**
     * 分页查询
     */
    Page<UsersVo> page();

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

    /**
     * 自定义别名
     *
     * @return String
     */
    List<Map> customizeAlias();

    /**
     * 自动别名映射
     *
     * @return
     */
    List<UsersVo> automaticAlias();

    /**
     * joins 测试
     *
     * @return
     */
    List<UsersVo> joinsTest();

}
