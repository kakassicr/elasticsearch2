package com.sf.heros.mq.consumer.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.sf.heros.mq.consumer.utils.APP;

@Document(indexName = APP.ESProp.INDEX_NAME, type = APP.ESProp.TYPE_USER_INFO)
public class UserInfo {
	@Id
    @Field(index = FieldIndex.not_analyzed, store = true)
	private String userId;
	
	@Field(type = FieldType.String,  searchAnalyzer="ik", store = true)
	private String userName;
	
	@Field(type = FieldType.String,  searchAnalyzer="ik", store = true)
	private String nickName;
	
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
	private String headUrl;
	
	@Field(type = FieldType.String,  searchAnalyzer="ik", store = true)
	private String tags;
	
	@Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
	private Integer sex;
	
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
	private String location;
	
	@Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
	private Integer type;
	
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
	private String personalDes;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPersonalDes() {
		return personalDes;
	}

	public void setPersonalDes(String personalDes) {
		this.personalDes = personalDes;
	}
	
}
