package icu.mhb.mpj.example.vo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import icu.mhb.mpj.example.entity.Users;
import lombok.Data;

import java.util.List;

/**
 * @author mahuibo
 * @Title: UsersAgesVo
 * @email mhb0409@qq.com
 * @date 2022-02-16
 */
@Data
public class UsersAgesVo {

    private Long id;

    private String ageDoc;

    private String ageName;

    private List<Users> usersList;

}
