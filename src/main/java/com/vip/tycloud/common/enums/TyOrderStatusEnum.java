package com.vip.tycloud.common.enums;

import java.util.Objects;
import lombok.Getter;

/**
 * 订单状态。
 */
@Getter
public enum TyOrderStatusEnum {

    UNPAID(0, "待支付"),

    PAID(1, "已支付"),

    CANCELED(2, "已取消");

    private final Integer code;

    private final String label;

    TyOrderStatusEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public static boolean contains(Integer code) {
        for (TyOrderStatusEnum item : values()) {
            if (Objects.equals(item.getCode(), code)) {
                return true;
            }
        }
        return false;
    }
}
