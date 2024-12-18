package cn.ahzoo.z2blog.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @TableName b_category
 */
@TableName(value = "b_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

    @TableId
    private Long id;

    private String name;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
