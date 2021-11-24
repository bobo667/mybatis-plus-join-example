package icu.mhb.mpj.example.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author mahuibo
 * @Title: Users
 * @time 9/25/21 5:52 PM
 */
@Data
@Accessors(chain = true)
@TableName("users")
public class Users {

    private Long userId;

    private String userName;

    private Date createTime;

    private Long ageId;

}
