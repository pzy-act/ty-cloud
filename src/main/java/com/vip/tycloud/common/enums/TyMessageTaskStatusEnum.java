package com.vip.tycloud.common.enums;

import java.util.Objects;
import lombok.Getter;

/**
 * 消息任务状态。
 */
@Getter
public enum TyMessageTaskStatusEnum {

    PENDING(0, "待发送"),

    SENDING(1, "发送中"),

    COMPLETED(2, "已完成"),

    FAILED(3, "失败");

    private final Integer code;

    private final String label;

    TyMessageTaskStatusEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public static boolean contains(Integer code) {
        for (TyMessageTaskStatusEnum item : values()) {
            if (Objects.equals(item.getCode(), code)) {
                return true;
            }
        }
        return false;
    }
}
