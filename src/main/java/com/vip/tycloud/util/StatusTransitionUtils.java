package com.vip.tycloud.util;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * 状态校验工具。
 */
public final class StatusTransitionUtils {

    private StatusTransitionUtils() {
    }

    public static boolean isValid(Integer status, Predicate<Integer> contains) {
        return Objects.isNull(status) || contains.test(status);
    }

    public static boolean canForward(Integer oldStatus, Integer newStatus, Predicate<Integer> contains) {
        if (Objects.isNull(newStatus)) {
            return true;
        }
        if (!contains.test(newStatus)) {
            return false;
        }
        return Objects.isNull(oldStatus) || newStatus >= oldStatus;
    }
}
