package com.vip.tycloud.common.enums;

import java.util.Objects;
import lombok.Getter;

/**
 * 作品状态。
 */
@Getter
public enum TyArtworkStatusEnum {

    CREATED(0, "建档中"),

    GLAZED(1, "已上釉"),

    FIRED(2, "已烧制"),

    PICKED_UP(3, "已取件"),

    DAMAGED(4, "损坏");

    private final Integer code;

    private final String label;

    TyArtworkStatusEnum(Integer code, String label) {
        this.code = code;
        this.label = label;
    }

    public static boolean contains(Integer code) {
        for (TyArtworkStatusEnum item : values()) {
            if (Objects.equals(item.getCode(), code)) {
                return true;
            }
        }
        return false;
    }
}
