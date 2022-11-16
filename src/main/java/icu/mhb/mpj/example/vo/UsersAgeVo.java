package icu.mhb.mpj.example.vo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author mahuibo
 * @Title: UsersAge
 * @time 9/25/21 5:53 PM
 */
@TableName("users_age")
@Data
public class UsersAgeVo {

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    private String ageDoc;

    private String ageName;

}
