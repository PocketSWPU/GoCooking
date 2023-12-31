package com.pocket.gocooking.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共响应类
 * @author Pocket
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(){
        return new Result<>(20000, "success", null);
    }

    public static <T> Result<T> success(T data){
        return new Result<>(20000, "success", data);
    }

    public static <T> Result<T> success(T data, String message){
        return new Result<>(20000, message, data);
    }

    public static <T> Result<T> success(String message){
        return new Result<>(20000, message, null);
    }

    // 失败
    public static <T> Result<T> fail(){
        return new Result<>(20001, "success", null);
    }

    public static <T> Result<T> fail(Integer code){
        return new Result<>(code, "success", null);
    }

    public static <T> Result<T> fail(Integer code, String message){
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> fail(String message){
        return new Result<>(20001, message, null);
    }
}
