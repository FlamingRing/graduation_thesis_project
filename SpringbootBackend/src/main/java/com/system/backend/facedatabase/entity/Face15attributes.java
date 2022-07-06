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
public class Face15attributes implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "imageId", type = IdType.AUTO)
    private Integer imageId;

    @TableField("imageFilename")
    private String imageFilename;

    @TableField("beard")
    private Integer beard;

    @TableField("bald")
    private Integer bald;

    @TableField("eyeglasses")
    private Integer eyeglasses;

    @TableField("smiling")
    private Integer smiling;

    @TableField("wearingHat")
    private Integer wearingHat;

    @TableField("male")
    private Integer male;

    @TableField("shadow")
    private Integer shadow;

    @TableField("mustache")
    private Integer mustache;

    @TableField("bangs")
    private Integer bangs;

    @TableField("blackHair")
    private Integer blackHair;

    @TableField("blondeHair")
    private Integer blondeHair;

    @TableField("brownHair")
    private Integer brownHair;

    @TableField("grayHair")
    private Integer grayHair;

    @TableField("straightHair")
    private Integer straightHair;

    @TableField("wavyHair")
    private Integer wavyHair;


}
