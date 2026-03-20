package com.vip.tycloud.common.dto;

import lombok.Data;

/**
 * 通用接口响应。
 *
 * @param <T> 数据类型
 */
@Data
public class ApiResponse<T> {

    /**
     * 响应码。
     */
    private Integer code;

    /**
     * 响应消息。
     */
    private String message;

    /**
     * 响应数据。
     */
    private T data;

    /**
     * 成功响应。
     *
     * @param data 数据
     * @param <T> 数据类型
     * @return 响应体
     */
    public static <T> ApiResponse<T> success(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(0);
        response.setMessage("success");
        response.setData(data);
        return response;
    }

    /**
     * 失败响应。
     *
     * @param message 错误消息
     * @param <T> 数据类型
     * @return 响应体
     */
    public static <T> ApiResponse<T> fail(String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(-1);
        response.setMessage(message);
        return response;
    }
}
