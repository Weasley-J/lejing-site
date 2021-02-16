package cn.alphahub.mall.order.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 场地预约入场券卷号表
 *
 * @author Weasley J
 * @email 1432689025@qq.com
 * @date 2021-02-16 18:17:37
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("eb_order_site_coupon")
public class OrderSiteCoupon implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 订单id
	 */
    @TableId
    private Long orderMasterId;

	/**
	 * 场地id
	 */
    private Long siteId;

	/**
	 * 场次id
	 */
    private Long siteSessionId;

	/**
	 * 券号，优惠券码，后台处理（规则：订单创建日期加上6位随机数-yyyyMMddxxxxxx，如：20210121687587）
	 */
    private String couponCode;

	/**
	 * 状态（1-未使用；2-已使用；3-已过期；4-已关闭；5-已退款；默认未使用：1）
	 */
    private String couponStatus;

	/**
	 * 入场卷核销人员
	 */
    private String writeOffUser;

	/**
	 * 入场卷消费时间
	 */
    private Date consumeDate;

	/**
	 * 删除状态（0：未删，1：已删，默认：未删除）
	 */
    private Integer deleted;

	/**
	 * 创建时间
	 */
    private Date createTime;

	/**
	 * 创建者
	 */
    private String createBy;

	/**
	 * 更新时间
	 */
    private Date updateTime;

	/**
	 * 更新者
	 */
    private String updateBy;

	/**
	 * 备注信息
	 */
    private String remark;

}