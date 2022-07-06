package com.system.backend.facedatabase.entity;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleResultBean {
    private Integer code;
    private String msg;
}
