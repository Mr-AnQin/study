package com.anqin.common;

import lombok.Builder;
import lombok.Data;

/**
 * 统一返回结果
 *
 * @author Mr.An
 * @date 2024/03/02
 */
@Data
@Builder(builderMethodName = "i", buildMethodName = "e")
public class Result<T> {

    /**
     * 数据
     */
    private T data;

    /**
     * 消息
     */
    private String message;

    /**
     * code
     */
    private String code;


    /**
     * ok
     *
     * @param data 数据
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> ok(T data) {
        return Result.<T>i().code("200").data(data).e();
    }

    /**
     * ok
     *
     * @param message 消息
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> ok(String message) {
        return Result.<T>i().code("200").message(message).e();
    }
    /**
     * ok
     *
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> ok() {
        return Result.<T>i().code("200").e();
    }

    /**
     * 500
     *
     * @param message 消息
     * @return {@link Result}<{@link T}>
     */
    public static <T> Result<T> error(String message) {
        return Result.<T>i().code("500").message(message).e();
    }
}
