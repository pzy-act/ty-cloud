package com.vip.tycloud.common.enums;

import java.util.Objects;
import lombok.Getter;

/**
 * 发票状态。
 */
@Getter
public enum TyInvoiceStatusEnum {

    PENDING(0, "待开票"),

    ISSUED(1, "已开票"),

    VOIDED(2, "已作废");

    private final Integer code;

    private final String label;

    TyInvoiceStatusEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public static boolean contains(Integer code) {
        for (TyInvoiceStatusEnum item : values()) {
            if (Objects.equals(item.getCode(), code)) {
                return true;
            }
        }
        return false;
    }
}
