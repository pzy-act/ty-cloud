package com.vip.tycloud.common.enums;

import java.util.Objects;
import lombok.Getter;

/**
 * 通用处理进度状态。
 */
@Getter
public enum TyProgressStatusEnum {

    PENDING(0, "待处理"),

    PROCESSING(1, "处理中"),

    COMPLETED(2, "已完成"),

    CANCELED(3, "已取消");

    private final Integer code;

    private final String label;

    TyProgressStatusEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public static boolean contains(Integer code) {
        for (TyProgressStatusEnum item : values()) {
            if (Objects.equals(item.getCode(), code)) {
                return true;
            }
        }
        return false;
    }
}
