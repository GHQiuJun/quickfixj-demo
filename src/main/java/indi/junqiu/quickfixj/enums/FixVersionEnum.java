package indi.junqiu.quickfixj.enums;

import lombok.Getter;

/**
 * @program: FixVersionEnum
 * @description: FixVersion Enum
 * @author: Lorin
 * @create: 2023-07-11 18:00
 **/
public enum FixVersionEnum {
    FIX_VERSION_4_1("FIX.4.1"),
    FIX_VERSION_4_2("FIX.4.2");

    @Getter
    final String code;

    FixVersionEnum(String code) {
        this.code = code;
    }
}
