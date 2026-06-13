package com.vip.tycloud.common.enums;

import java.util.Objects;
import lombok.Getter;

/**
 * 薪资核算状态。
 */
@Getter
public enum TyPayrollStatusEnum {

    CALCULATING(0, "待核算"),

    CONFIRMED(1, "已确认"),

    PAID(2, "已发放");

    private final Integer code;

    private final String label;

    TyPayrollStatusEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public static boolean contains(Integer code) {
        for (TyPayrollStatusEnum item : values()) {
            if (Objects.equals(item.getCode(), code)) {
                return true;
            }
        }
        return false;
    }
}
