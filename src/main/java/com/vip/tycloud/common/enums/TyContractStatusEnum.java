package com.vip.tycloud.common.enums;

import java.util.Objects;
import lombok.Getter;

/**
 * 合同状态。
 */
@Getter
public enum TyContractStatusEnum {

    PENDING(0, "待生效"),

    ACTIVE(1, "生效中"),

    COMPLETED(2, "已完成"),

    VOIDED(3, "已作废");

    private final Integer code;

    private final String label;

    TyContractStatusEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public static boolean contains(Integer code) {
        for (TyContractStatusEnum item : values()) {
            if (Objects.equals(item.getCode(), code)) {
                return true;
            }
        }
        return false;
    }
}
