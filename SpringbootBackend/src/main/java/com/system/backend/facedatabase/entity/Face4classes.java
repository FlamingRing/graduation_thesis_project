package com.system.backend.facedatabase.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class Face4classes implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value="imageId", type=IdType.AUTO)
    private Integer imageId;

    @TableField("imageFilename")
    private String imageFilename;

    @TableField("imageClass")
    private Integer imageClass;


}
