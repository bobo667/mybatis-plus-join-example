package icu.mhb.mpj.example.entity;
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
public class UsersAge {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String ageDoc;

    private String ageName;

}
