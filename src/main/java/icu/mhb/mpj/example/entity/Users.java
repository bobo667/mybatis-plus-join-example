package icu.mhb.mpj.example.entity;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.JDBCType;
import java.util.Date;

/**
 * @author mahuibo
 * @Title: Users
 * @time 9/25/21 5:52 PM
 */
@Data
@Accessors(chain = true)
@TableName(value = "users", autoResultMap = true)
public class Users {

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    private String userName;

    private Date createTime;

    @TableLogic
    private Long ageId;

    @TableField(typeHandler = FastjsonTypeHandler.class)
    private TestUserJson contentJson;

}
