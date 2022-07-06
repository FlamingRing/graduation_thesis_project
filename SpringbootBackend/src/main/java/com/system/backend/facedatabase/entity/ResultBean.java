package com.system.backend.facedatabase.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultBean<T> {
    private Integer code;
    private String msg;
    private T data;
}
