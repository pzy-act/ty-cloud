package com.vip.tycloud.common.enums;

import java.util.Objects;
import lombok.Getter;

/**
 * 退款状态。
 */
@Getter
public enum TyRefundStatusEnum {

    APPLYING(0, "申请中"),

    REFUNDED(1, "已退款"),

    REJECTED(2, "已驳回");

    private final Integer code;

    private final String label;

    TyRefundStatusEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public static boolean contains(Integer code) {
        for (TyRefundStatusEnum item : values()) {
            if (Objects.equals(item.getCode(), code)) {
                return true;
            }
        }
        return false;
    }
}
