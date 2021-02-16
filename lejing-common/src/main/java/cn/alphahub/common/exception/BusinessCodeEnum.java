package cn.alphahub.common.exception;

/***
 * 错误码和错误信息定义类
 * <br><br/>
 * 1. 错误码定义规则为5为数字
 * 2. 前两位表示业务场景，最后三位表示错误码。例如：100001。10:通用 001:系统未知异常
 * 3. 维护错误码后需要维护错误描述，将他们定义为枚举形式
 *<br><br/>
 * 错误码列表：
 *       10: 通用
 *              001：参数格式校验
 *       11: 商品
 *       12: 订单
 *       13: 购物车
 *       14: 物流
 */
public enum BusinessCodeEnum {
    UNKNOWN_EXCEPTION(10000, "系统未知异常"),
    VALID_EXCEPTION(10001, "参数格式校验异常");
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误消息
     */
    private String message;

    BusinessCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
