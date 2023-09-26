package icu.mhb.mpj.example.vo;
import icu.mhb.mpj.example.entity.TestUserJson;
import icu.mhb.mpj.example.entity.Users;
import icu.mhb.mpj.example.entity.UsersAge;
import icu.mhb.mybatisplus.plugln.annotations.JoinField;
import icu.mhb.mybatisplus.plugln.constant.RelevancyType;
import lombok.Builder;
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

    private Long id;

    private Long userId;

    private String userName;

    private Date createTime;

    private Integer ageId;

    private Long ageTableId;

    private String mpnb;

    private String ageDoc;

    private String users_age_name;

    @JoinField(masterModelClass = Users.class, masterModelField = "ageId",
            sunModelClass = UsersAge.class, sunModelField = "id", relevancyType = RelevancyType.ONE_TO_ONE,
            sunAlias = "t1")
    private UsersAge usersAge;

    @JoinField(masterModelClass = Users.class, masterModelField = "ageId",
            sunModelClass = UsersAge.class, sunModelField = "id", relevancyType = RelevancyType.MANY_TO_MANY,
            sunAlias = "t2")
    private List<UsersAge> usersAges;

    private String ageName;

    private UsersAgeVo usersAgeVo;

    private TestUserJson contentJson;

    private TestUserJson contentJsonAge;

    private String ageIds;

}
