package icu.mhb.mpj.example.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;

import icu.mhb.mpj.example.handler.TestUserJsonTypeHandler;
import icu.mhb.mybatisplus.plugln.annotations.JoinChainModel;
import lombok.Data;

/**
 * @author mahuibo
 * @Title: UsersAge
 * @time 9/25/21 5:53 PM
 */
@TableName(value = "users_age", autoResultMap = true)
@Data
@JoinChainModel
public class UsersAge {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //    @TableLogic
    private String ageDoc;

    private String ageName;

    private Date createTime;

    @TableField(typeHandler = TestUserJsonTypeHandler.class)
    private TestUserJson contentJsonAge;


}
