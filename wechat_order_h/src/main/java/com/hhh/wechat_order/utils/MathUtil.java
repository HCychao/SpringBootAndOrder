package com.hhh.wechat_order.utils;

/**
 * 校验
 */
public class MathUtil {

    //最小范围常量
    private static final Double MONEY_RANGE = 0.01;

    /**
     * 比较2个金额是否相等
     * @param d1
     * @param d2
     * @return
     */
    public static Boolean equals(Double d1 , Double d2){
        Double result = Math.abs(d1 - d2);
        if(result < MONEY_RANGE){
            return true;
        }else {
            return false;
        }
    }
}
