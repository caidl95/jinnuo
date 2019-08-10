package xyz.zhuoxuan.jinnuo.log.entity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import xyz.zhuoxuan.jinnuo.base.entity.BaseEntity;

public class StatusLog extends BaseEntity {
	//主键
	private Integer statusLogId;
	//用户id
	private Integer userId;
	//请求类型
	private String statusType;
	//请求地址
	private String ipAddress;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@JSONField(format = "yyyy-MM-dd")
	private Date loginDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date loginTime;

	// 字典类型：上线
	public static final String STATUS_TYPE_LOGIN = "statusTypeLogin";

	// 字典类型：登出
	public static final String STATUS_TYPE_LOGOUT = "statusTypeLogout";

	// 字典类型：自动离线
	public static final String STATUS_TYPE_AUTO_LOGOUT = "statusTypeAutoLogout";

	public StatusLog() {
		super();
	}

	public StatusLog(Integer statusLogId, Integer userId, String statusType, String ipAddress, Date loginDate,
			Date loginTime) {
		super();
		this.statusLogId = statusLogId;
		this.userId = userId;
		this.statusType = statusType;
		this.ipAddress = ipAddress;
		this.loginDate = loginDate;
		this.loginTime = loginTime;
	}

	public Integer getStatusLogId() {
		return statusLogId;
	}

	public void setStatusLogId(Integer statusLogId) {
		this.statusLogId = statusLogId ;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress == null ? null : ipAddress.trim();
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}