package com.vip.tycloud.common.enums;

import java.util.Objects;
import lombok.Getter;

/**
 * 收款状态。
 */
@Getter
public enum TyPaymentStatusEnum {

    PENDING(0, "待确认"),

    SUCCESS(1, "成功"),

    FAILED(2, "失败");

    private final Integer code;

    private final String label;

    TyPaymentStatusEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public static boolean contains(Integer code) {
        for (TyPaymentStatusEnum item : values()) {
            if (Objects.equals(item.getCode(), code)) {
                return true;
            }
        }
        return false;
    }
}
