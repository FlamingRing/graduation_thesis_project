package com.system.backend.facedatabase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Face8attributes implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="imageId", type=IdType.AUTO)
    private Integer imageId;

    @TableField("imageFilename")
    private String imageFilename;

    @TableField("bigLips")
    private Integer bigLips;

    @TableField("bigNose")
    private Integer bigNose;

    @TableField("eyeglasses")
    private Integer eyeglasses;

    @TableField("male")
    private Integer male;

    @TableField("noBeard")
    private Integer noBeard;

    @TableField("smiling")
    private Integer smiling;

    @TableField("wearingHat")
    private Integer wearingHat;

    @TableField("young")
    private Integer young;


}
