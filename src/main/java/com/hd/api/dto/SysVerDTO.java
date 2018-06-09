package com.hd.api.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class SysVerDTO {

	private String Version;
	private String AppUrl;
	@JSONField(serialize = false)
	private String DescriptionStr;
	private List<String> Description;
	private String CreateTime;

	public String getDescriptionStr() {
		return DescriptionStr;
	}

	public void setDescriptionStr(String descriptionStr) {
		DescriptionStr = descriptionStr;
	}

	public void setDescription(List<String> description) {
		Description = description;
	}

	public List<String> getDescription() {
		return Description;
	}

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public String getAppUrl() {
		return AppUrl;
	}

	public void setAppUrl(String appUrl) {
		AppUrl = appUrl;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
}
