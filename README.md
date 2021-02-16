

# 1. 通用商城类【场地预约】后端

- [x] 自动生成代码
- [x]  自动输出可调式`api`文档，完整`api`文档启动项目访问：http://127.0.0.1:8090/debug-all.html，非手写`API`文档，

​        PS: 都**2021**年了，还有团队还在手写`API`文档...... , 应该与时俱进。

- [x]  可整合分布式微服务，或者单体服务
- [x] 项目结构

- 业务模块
- 代码生成
- `common`工程
- 完整`SQL`文件

- [x] 基于`mybatis-plus`代码构建，以领域对象变动应对业务的变化，DB变化，修改对应的领域对象即可

- [x]  基础软件版本：

  ![image-20210216193247118](https://alphahub-test-bucket.oss-cn-shanghai.aliyuncs.com/image/image-20210216193247118.png)

- [ ] `api`调试效果：

![image-20210216195052317](https://alphahub-test-bucket.oss-cn-shanghai.aliyuncs.com/image/image-20210216195052317.png)

# 2. 项目结构

![image-20210216192825670](https://alphahub-test-bucket.oss-cn-shanghai.aliyuncs.com/image/image-20210216192825670.png)







# 3. 场地预约-后端接口文档

Version |  Update Time  | Status | Author |  Description
---|---|---|---|---
1.0|2020-12-31 10:30|创建|Weasley J|创建api文档
1.1|2021-02-16 14:02|更新|Weasley J|版本升级



## App场地预约Controller
### 预约场地-分页（当前往后推7日）
**URL:** http://127.0.0.1:8090/app/site/reserveList

**Type:** GET

**Author:** Weasley J

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 预约场地-分页（当前往后推7日）

**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
page|int32|     当前页码,默认第一页|true|-
rows|int32|     本页条数，默认每页显示10条|true|-
projectId|string|项目id|true|-

**Request-example:**
```
curl -X GET -i http://127.0.0.1:8090/app/site/reserveList?rows=10&page=1&projectId=LJ1000
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
message|string|返回消息|-
success|boolean|返回成功消息|-
timestamp|string|响应时间戳|-
code|int32|状态码|-
data|object|No comments found.|-
└─totalCount|int64|总条数|-
└─totalPage|int64|总页数|-
└─items|array|当前页数据|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─siteId|int64|场地id（主键）|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─projectId|string|项目id（同一类场地项目id相同）|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─siteTitle|string|场地名称|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─siteLocationDesc|string|场地的位置描述信息|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─siteCommunity|string|场地所属社区|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─imageUrl|array|场地图片列表|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─weekReserveCount|int32|7日内预约情况，weekReserveCount >= 1，可预约|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─sort|int32|排序值，数值越大，越靠前|-

**Response-example:**
```
{
	"message": "success",
	"success": true,
	"timestamp": "2021-02-16 19:20:11",
	"code": 595,
	"data": {
		"totalCount": 321,
		"totalPage": 267,
		"items": [
			{
				"siteId": 865,
				"projectId": "61",
				"siteTitle": "qvoyia",
				"siteLocationDesc": "jbqt8b",
				"siteCommunity": "gvz5vb",
				"imageUrl": [
					"y2g50d"
				],
				"weekReserveCount": 812,
				"sort": 758
			}
		]
	}
}
```

### 获取XX场地的7日内预约详情
**URL:** http://127.0.0.1:8090/app/site/siteBookDetail/{siteId}

**Type:** GET

**Author:** Weasley J

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 获取XX场地的7日内预约详情

**Path-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
siteId|int64|场地id|true|-

**Request-example:**
```
curl -X GET -i http://127.0.0.1:8090/app/site/siteBookDetail/241
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
message|string|返回消息|-
success|boolean|返回成功消息|-
timestamp|string|响应时间戳|-
code|int32|状态码|-
data|object|No comments found.|-
└─siteId|int64|场地id（主键）|-
└─projectId|string|项目id（同一类场地项目id相同）|-
└─siteTitle|string|场地名称|-
└─siteLocationDesc|string|场地的位置描述信息|-
└─siteCommunity|string|场地所属社区|-
└─imageUrl|array|场地图片列表|-
└─weekReserveCount|int32|7日内预约情况，weekReserveCount >= 1，可预约|-
└─sort|int32|排序值，数值越大，越靠前|-
└─sitePrice|int32|预约价格（单位：分），默认价格|-
└─siteOpenTime|string|开始营业时间（规则: 24H制，时分中间用":"号隔开；如: 08:00, 09:30，可精确到具体分钟）|-
└─siteCloseTime|string|结束营业时间（规则: 24H制，时分中间用":"号隔开；如: 19:00, 22:30，可精确到具体分钟）|-
└─siteOpenStatus|int32|营运状态（0-不可用，不营运；1-可用，可运营；默认：1）|-
└─siteAdministratorPhone|string|场地管理员电话|-
└─siteBookList|array|场地场地预定列表|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─siteSessionId|int64|场次id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─bookDate|string|预定日期|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─latestTime|string|最近一场时间 HH:mm|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─bookCount|int32|可预定数量，>=1 可预订|-
└─siteDetailList|array|场地其他信息 eb_site_reserve_detail表|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─sitePubDictCode|int32|发布的信息(关联sys_dict_data表的dict_code)|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─sitePubDictName|string|发布的信息的名称|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─sitePubTopic|string|场地信息|-

**Response-example:**
```
{
	"message": "success",
	"success": true,
	"timestamp": "2021-02-16 19:20:11",
	"code": 424,
	"data": {
		"siteId": 216,
		"projectId": "61",
		"siteTitle": "c84g1a",
		"siteLocationDesc": "fi6rts",
		"siteCommunity": "atdpoe",
		"imageUrl": [
			"jz9e17"
		],
		"weekReserveCount": 525,
		"sort": 540,
		"sitePrice": 145,
		"siteOpenTime": "2021-02-16 19:20:11",
		"siteCloseTime": "2021-02-16 19:20:11",
		"siteOpenStatus": 848,
		"siteAdministratorPhone": "17019066238",
		"siteBookList": [
			{
				"siteSessionId": 103,
				"bookDate": "2021-02-16",
				"latestTime": "2021-02-16 19:20:11",
				"bookCount": 88
			}
		],
		"siteDetailList": [
			{
				"sitePubDictCode": 224,
				"sitePubDictName": "雨泽.顾",
				"sitePubTopic": "fli94u"
			}
		]
	}
}
```

### 获取场地yyyy-MM-dd号的场次信息
**URL:** http://127.0.0.1:8090/app/site/siteSessions

**Type:** GET

**Author:** Weasley J

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 获取场地yyyy-MM-dd号的场次信息

**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
siteId|int64|    场地id|true|-
effectDate|string|要查询的日期, 格式yyyy-MM-dd|true|-

**Request-example:**
```
curl -X GET -i http://127.0.0.1:8090/app/site/siteSessions?effectDate=2021-02-16&siteId=546
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
message|string|返回消息|-
success|boolean|返回成功消息|-
timestamp|string|响应时间戳|-
code|int32|状态码|-
data|object|No comments found.|-
└─siteSessionId|int64|场次id|-
└─siteId|int64|场地id|-
└─siteTitle|string|场地名称|-
└─effectDate|string|场次生效日期|-
└─sessionStartTime|string|场次开始时间点（规则: 24H制，时分中间用:号隔开；如: 08:00, 09:30，可精确到具体分钟）|-
└─sessionFinishTime|string|场次结束时间点（规则: 24H制，时分中间用:号隔开；如: 20:00, 21:30，可精确到具体分钟）|-
└─currentPrice|int32|当前场次价格（单位：分，默认值从eb_site_reserve表site_price带过来）|-
└─sessionStatus|int32|该场次状态（0：可预定，1：已预定，2：不可预定）|-
└─statusName|string|该场次状态名称|-
└─deleted|boolean|删除状态（0：未删，1：已删，默认：未删除）|-

**Response-example:**
```
{
	"message": "success",
	"success": true,
	"timestamp": "2021-02-16 19:20:11",
	"code": 125,
	"data": [
		{
			"siteSessionId": 689,
			"siteId": 932,
			"siteTitle": "dlpmnh",
			"effectDate": "2021-02-16",
			"sessionStartTime": "2021-02-16 19:20:11",
			"sessionFinishTime": "2021-02-16 19:20:11",
			"currentPrice": 414,
			"sessionStatus": 911,
			"statusName": "雨泽.顾",
			"deleted": true
		}
	]
}
```

### 确认订单提交预约
**URL:** http://127.0.0.1:8090/app/site/confirmOrder

**Type:** POST

**Author:** Weasley J

**Content-Type:** application/json; charset=utf-8

**Description:** 确认订单提交预约

**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
userId|string|用户名|false|-
username|string|用户名|false|-
userPhone|string|用户手机号码|false|-
totalPrice|int32|场次集合各个场次价格相加之和|false|-
remark|string|用户备注|false|-
sessions|array|场地预约场次集合|false|-
└─siteStatusId|int64|场地状态id|false|-
└─siteSessionId|int64|场地场次id|false|-
└─siteId|int64|场地id|false|-
└─siteTitle|string|场地名称|false|-
└─effectDate|string|场次生效日期|false|-
└─sessionStartTime|string|场次开始时间点（规则: 24H制，时分中间用:号隔开；如: 08:00, 09:30，可精确到具体分钟）|false|-
└─sessionFinishTime|string|场次结束时间点（规则: 24H制，时分中间用:号隔开；如: 20:00, 21:30，可精确到具体分钟）|false|-
└─currentPrice|int32|当前场次价格（单位：分，默认值从eb_site_reserve表site_price带过来）|false|-

**Request-example:**
```
curl -X POST -H 'Content-Type: application/json; charset=utf-8' -i http://127.0.0.1:8090/app/site/confirmOrder? --data '{
	"userId": "61",
	"username": "雨泽.顾",
	"userPhone": "17019066238",
	"totalPrice": 162,
	"remark": "mwlrh0",
	"sessions": [
		{
			"siteStatusId": 401,
			"siteSessionId": 713,
			"siteId": 978,
			"siteTitle": "wm58xv",
			"effectDate": "2021-02-16",
			"sessionStartTime": "2021-02-16 19:20:11",
			"sessionFinishTime": "2021-02-16 19:20:11",
			"currentPrice": 953
		}
	]
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
message|string|返回消息|-
success|boolean|返回成功消息|-
timestamp|string|响应时间戳|-
code|int32|状态码|-
data|object|No comments found.|-
└─userId|string|用户名|-
└─username|string|用户名|-
└─userPhone|string|用户手机号码|-
└─totalPrice|int32|场次集合各个场次价格相加之和|-
└─remark|string|用户备注|-
└─sessions|array|场地预约场次集合|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─siteStatusId|int64|场地状态id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─siteSessionId|int64|场地场次id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─siteId|int64|场地id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─siteTitle|string|场地名称|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─effectDate|string|场次生效日期|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─sessionStartTime|string|场次开始时间点（规则: 24H制，时分中间用:号隔开；如: 08:00, 09:30，可精确到具体分钟）|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─sessionFinishTime|string|场次结束时间点（规则: 24H制，时分中间用:号隔开；如: 20:00, 21:30，可精确到具体分钟）|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─currentPrice|int32|当前场次价格（单位：分，默认值从eb_site_reserve表site_price带过来）|-

**Response-example:**
```
{
	"message": "success",
	"success": true,
	"timestamp": "2021-02-16 19:20:11",
	"code": 291,
	"data": {
		"userId": "61",
		"username": "雨泽.顾",
		"userPhone": "17019066238",
		"totalPrice": 982,
		"remark": "fq7z7v",
		"sessions": [
			{
				"siteStatusId": 941,
				"siteSessionId": 780,
				"siteId": 188,
				"siteTitle": "6eulbi",
				"effectDate": "2021-02-16",
				"sessionStartTime": "2021-02-16 19:20:11",
				"sessionFinishTime": "2021-02-16 19:20:11",
				"currentPrice": 835
			}
		]
	}
}
```

### 场地预约订单列表-分页
**URL:** http://127.0.0.1:8090/app/site/siteOrderStatus

**Type:** GET

**Author:** Weasley J

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 场地预约订单列表-分页

**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
page|int32|       当前页码,默认第一页|true|-
rows|int32|       本页条数，默认每页显示10条|true|-
userId|string|     用户id|true|-
orderStatus|string|订单状态码/券的使用状态码|true|-

**Request-example:**
```
curl -X GET -i http://127.0.0.1:8090/app/site/siteOrderStatus?userId=61&orderStatus=11bfcv&rows=10&page=1
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
message|string|返回消息|-
success|boolean|返回成功消息|-
timestamp|string|响应时间戳|-
code|int32|状态码|-
data|object|No comments found.|-
└─totalCount|int64|总条数|-
└─totalPage|int64|总页数|-
└─items|array|当前页数据|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─userId|string|当前登录用户id|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─siteId|int64|场地id（主键）|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─siteTitle|string|场地名称|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─effectDate|string|场次生效日期|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─sessionStartTime|string|场次开始时间点（规则: 24H制，时分中间用:号隔开；如: 08:00, 09:30，可精确到具体分钟）|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─sessionFinishTime|string|场次结束时间点（规则: 24H制，时分中间用:号隔开；如: 20:00, 21:30，可精确到具体分钟）|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─totalPrice|int32|场次集合各个场次价格相加之和|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─couponStatus|int32|状态（1-未使用；2-已使用；3-已过期；4-已关闭；5-已退款；默认未使用：1）|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─couponStatusName|string|入场券状态名称|-

**Response-example:**
```
{
	"message": "success",
	"success": true,
	"timestamp": "2021-02-16 19:20:11",
	"code": 347,
	"data": {
		"totalCount": 911,
		"totalPage": 318,
		"items": [
			{
				"userId": "61",
				"siteId": 138,
				"siteTitle": "px37s2",
				"effectDate": "2021-02-16",
				"sessionStartTime": "2021-02-16 19:20:11",
				"sessionFinishTime": "2021-02-16 19:20:11",
				"totalPrice": 201,
				"couponStatus": 826,
				"couponStatusName": "雨泽.顾"
			}
		]
	}
}
```

### 查询订单详情
**URL:** http://127.0.0.1:8090/app/site/siteOrderDetail

**Type:** GET

**Author:** Weasley J

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 查询订单详情

**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
orderMasterId|string|主订单号|true|-

**Request-example:**
```
curl -X GET -i http://127.0.0.1:8090/app/site/siteOrderDetail?orderMasterId=61
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
message|string|返回消息|-
success|boolean|返回成功消息|-
timestamp|string|响应时间戳|-
code|int32|状态码|-
data|object|No comments found.|-
└─siteId|int64|场地id（主键）|-
└─siteTitle|string|场地名称|-
└─couponCode|string|入场券券号，优惠券码，后台处理（规则：订单创建日期加上6位随机数-yyyyMMddxxxxxx，如：20210121687587）|-
└─couponStatus|int32|入场券状态（1-未使用；2-已使用；3-已过期；4-已关闭；5-已退款；默认未使用：1）|-
└─couponStatusName|string|入场券状态名称|-
└─orderMasterId|string|订单id，订单号（eb_site_reserve_detail表主键）|-
└─userPhone|string|用户手机号码|-
└─createTime|string|master order订单创建时间|-
└─orderActAmount|int32|实际支付金额（单位：分）,查询eb_order_master表order_act_amount字段|-
└─reserveDateList|array|场地预约时间列表|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─effectDate|string|生效日期（yyyy-MM-dd,关联eb_site_reserve_session表查询该日期对应的所有场次）|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─siteOpenTime|string|开始营业时间（规则: 24H制，时分中间用":"号隔开；如: 08:00, 09:30，可精确到具体分钟）|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─siteCloseTime|string|结束营业时间（规则: 24H制，时分中间用":"号隔开；如: 19:00, 22:30，可精确到具体分钟）|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─currentPrice|int32|当前场次价格（单位：分，默认值从eb_site_reserve表site_price带过来）|-
└─siteDetailList|array|场地其他信息(退款规则，使用须知，....)见eb_site_reserve_detail表|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─sitePubDictCode|int32|发布的信息(关联sys_dict_data表的dict_code)|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─sitePubDictName|string|发布的信息的名称|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─sitePubTopic|string|场地信息|-

**Response-example:**
```
{
	"message": "success",
	"success": true,
	"timestamp": "2021-02-16 19:20:11",
	"code": 637,
	"data": {
		"siteId": 610,
		"siteTitle": "5svt65",
		"couponCode": "85863",
		"couponStatus": 295,
		"couponStatusName": "雨泽.顾",
		"orderMasterId": "61",
		"userPhone": "17019066238",
		"createTime": "2021-02-16",
		"orderActAmount": 711,
		"reserveDateList": [
			{
				"effectDate": "2021-02-16",
				"siteOpenTime": "2021-02-16 19:20:11",
				"siteCloseTime": "2021-02-16 19:20:11",
				"currentPrice": 744
			}
		],
		"siteDetailList": [
			{
				"sitePubDictCode": 20,
				"sitePubDictName": "雨泽.顾",
				"sitePubTopic": "j2caln"
			}
		]
	}
}
```

### 查看入场券
**URL:** http://127.0.0.1:8090/app/site/lookOverCoupon

**Type:** GET

**Author:** Weasley J

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 查看入场券

**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
couponCode|string|优惠券码|true|-

**Request-example:**
```
curl -X GET -i http://127.0.0.1:8090/app/site/lookOverCoupon?couponCode=85863
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
message|string|返回消息|-
success|boolean|返回成功消息|-
timestamp|string|响应时间戳|-
code|int32|状态码|-
data|object|No comments found.|-
└─orderMasterId|string|订单id，订单号（eb_site_reserve_detail表主键）|-
└─siteId|int64|场地id|-
└─couponCode|string|券号，优惠券码，后台处理（规则：订单创建日期加上6位随机数-yyyyMMddxxxxxx，如：20210121687587）|-
└─siteReserveDates|array|场地预约时间列表|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─effectDate|string|生效日期（yyyy-MM-dd,关联eb_site_reserve_session表查询该日期对应的所有场次）|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─siteOpenTime|string|开始营业时间（规则: 24H制，时分中间用":"号隔开；如: 08:00, 09:30，可精确到具体分钟）|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─siteCloseTime|string|结束营业时间（规则: 24H制，时分中间用":"号隔开；如: 19:00, 22:30，可精确到具体分钟）|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─currentPrice|int32|当前场次价格（单位：分，默认值从eb_site_reserve表site_price带过来）|-
└─deleted|boolean|删除状态（0：未删，1：已删，默认：未删除）|-

**Response-example:**
```
{
	"message": "success",
	"success": true,
	"timestamp": "2021-02-16 19:20:11",
	"code": 912,
	"data": {
		"orderMasterId": "61",
		"siteId": 430,
		"couponCode": "85863",
		"siteReserveDates": [
			{
				"effectDate": "2021-02-16",
				"siteOpenTime": "2021-02-16 19:20:11",
				"siteCloseTime": "2021-02-16 19:20:11",
				"currentPrice": 640
			}
		],
		"deleted": true
	}
}
```

### 下载入场券
**URL:** http://127.0.0.1:8090/app/site/downloadCoupon

**Type:** GET

**Author:** Weasley J

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 下载入场券

**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
couponCode|string|优惠券码|true|-

**Request-example:**
```
curl -X GET -i http://127.0.0.1:8090/app/site/downloadCoupon?couponCode=85863
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
message|string|返回消息|-
success|boolean|返回成功消息|-
timestamp|string|响应时间戳|-
code|int32|状态码|-
data|object|No comments found.|-
└─any object|object|any object.|-

**Response-example:**
```
{
	"message": "success",
	"success": true,
	"timestamp": "2021-02-16 19:20:11",
	"code": 759,
	"data": {
		
	}
}
```

### 申请退款
**URL:** http://127.0.0.1:8090/app/site/requestRefund

**Type:** GET

**Author:** Weasley J

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 申请退款

**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
orderMasterId|string|主订单号|true|-

**Request-example:**
```
curl -X GET -i http://127.0.0.1:8090/app/site/requestRefund?orderMasterId=61
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
message|string|返回消息|-
success|boolean|返回成功消息|-
timestamp|string|响应时间戳|-
code|int32|状态码|-
data|object|No comments found.|-

**Response-example:**
```
{
	"message": "success",
	"success": true,
	"timestamp": "2021-02-16 19:20:11",
	"code": 451,
	"data": true
}
```

### 退款详情
**URL:** http://127.0.0.1:8090/app/site/refundDetail

**Type:** GET

**Author:** Weasley J

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 退款详情

**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
orderMasterId|string|主订单号|true|-

**Request-example:**
```
curl -X GET -i http://127.0.0.1:8090/app/site/refundDetail?orderMasterId=61
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
message|string|返回消息|-
success|boolean|返回成功消息|-
timestamp|string|响应时间戳|-
code|int32|状态码|-
data|object|No comments found.|-
└─reimburseId|int64|主键id|-
└─orderMasterId|string|主订单id（关联eb_order_master主键order_master_id）|-
└─userId|string|用户id|-
└─productTitle|string|商品名称|-
└─actualPrice|int32|退款金额（实际付款金额，单位：分）|-
└─receiveAccount|string|收款账户（从哪付款就退到哪里）|-
└─reimburseStatus|int32|退款状态（0：等待退款，1：平台处理中，2：退款成功，3：退款失败，默认0等待退款）|-
└─reimburseApplyDate|string|申请退款日期|-
└─merchantProcessDate|string|平台处理日期|-
└─refundSuccessfulDate|string|退款成功日期|-

**Response-example:**
```
{
	"message": "success",
	"success": true,
	"timestamp": "2021-02-16 19:20:11",
	"code": 735,
	"data": {
		"reimburseId": 452,
		"orderMasterId": "61",
		"userId": "61",
		"productTitle": "js4sr8",
		"actualPrice": 603,
		"receiveAccount": "wto80y",
		"reimburseStatus": 362,
		"reimburseApplyDate": "2021-02-16",
		"merchantProcessDate": "2021-02-16",
		"refundSuccessfulDate": "2021-02-16"
	}
}
```


