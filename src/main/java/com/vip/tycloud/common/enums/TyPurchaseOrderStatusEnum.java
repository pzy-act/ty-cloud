package com.vip.tycloud.common.enums;

import java.util.Objects;
import lombok.Getter;

/**
 * 采购单状态。
 */
@Getter
public enum TyPurchaseOrderStatusEnum {

    DRAFT(0, "草稿"),

    ORDERED(1, "已下单"),

    ARRIVED(2, "已到货"),

    CANCELED(3, "已取消");

    private final Integer code;

    private final String label;

    TyPurchaseOrderStatusEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public static boolean contains(Integer code) {
        for (TyPurchaseOrderStatusEnum item : values()) {
            if (Objects.equals(item.getCode(), code)) {
                return true;
            }
        }
        return false;
    }
}
