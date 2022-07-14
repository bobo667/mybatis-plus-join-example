package icu.mhb.mpj.example.vo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import icu.mhb.mpj.example.entity.Users;
import icu.mhb.mpj.example.entity.UsersAge;
import icu.mhb.mybatisplus.plugln.annotations.MasterTable;
import icu.mhb.mybatisplus.plugln.annotations.TableAlias;
import lombok.Data;

import java.util.List;

/**
 * @author mahuibo
 * @Title: UsersAgesVo
 * @email mhb0409@qq.com
 * @date 2022-02-16
 */
@Data
@MasterTable(UsersAge.class)
public class UsersAgesVo extends UsersAge {

    private List<Users> usersList;

}
