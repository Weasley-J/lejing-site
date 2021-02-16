package cn.alphahub.mall.app.service.util;

import cn.alphahub.mall.site.mapper.SiteReserveStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * APP场地预约综合业处理务类
 *
 * @author liuwenjing
 */
@Component
public class SiteUtil {

    @Autowired
    private SiteReserveStatusMapper siteReserveStatusMapper;

    /**
     * 根据siteId查当前时间往后七天的可预约场次数量，>= 1可预约
     * 当前时间起7天内的数据数量
     *
     * @param siteId site id
     * @param days   How many days of data do you want to get
     * @return count，>= 1可预约
     */
    public int getWeekReserveCount(Long siteId, Integer days) {
        return siteReserveStatusMapper.getDataCountInDays(siteId, days);
    }
}
