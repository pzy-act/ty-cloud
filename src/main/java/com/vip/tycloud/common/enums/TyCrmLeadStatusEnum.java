package com.vip.tycloud.common.enums;

import java.util.Objects;
import lombok.Getter;

/**
 * Lead status enum.
 */
@Getter
public enum TyCrmLeadStatusEnum {

    INVALID(0, "无效"),

    ACTIVE(1, "有效"),

    CONVERTED(2, "已转学员");

    private final Integer code;

    private final String label;

    TyCrmLeadStatusEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public static TyCrmLeadStatusEnum of(Integer code) {
        for (TyCrmLeadStatusEnum item : values()) {
            if (Objects.equals(item.getCode(), code)) {
                return item;
            }
        }
        return null;
    }
}
