package com.tt98.server.common.util;

import com.tt98.pojo.Enum.StatusEnum;
import com.tt98.server.common.TT98Exception;

public class ExceptionUtil {

    public static TT98Exception of(StatusEnum status, Object... args) {
        return new TT98Exception(status, args);
    }

}
