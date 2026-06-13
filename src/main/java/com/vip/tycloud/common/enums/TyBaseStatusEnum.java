package com.vip.tycloud.common.enums;

import java.util.Objects;
import lombok.Getter;

/**
 * 通用启停状态。
 */
@Getter
public enum TyBaseStatusEnum {

    DISABLED(0, "停用"),

    ENABLED(1, "启用");

    private final Integer code;

    private final String label;

    TyBaseStatusEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public static boolean contains(Integer code) {
        for (TyBaseStatusEnum item : values()) {
            if (Objects.equals(item.getCode(), code)) {
                return true;
            }
        }
        return false;
    }
}
