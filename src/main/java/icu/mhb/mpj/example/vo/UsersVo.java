package icu.mhb.mpj.example.vo;
import icu.mhb.mpj.example.entity.UsersAge;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author mahuibo
 * @Title: UsersVo
 * @time 9/25/21 6:04 PM
 */
@Data
public class UsersVo {

    private Long userId;

    private String userName;

    private Date createTime;

    private Integer ageId;

    private Long ageTableId;

    private String mpnb;

    private String ageDoc;

    private String users_age_name;

    private UsersAge usersAge;

}
