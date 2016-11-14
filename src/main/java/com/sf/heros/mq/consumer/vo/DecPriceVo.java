package com.sf.heros.mq.consumer.vo;

import com.sf.heros.mq.consumer.utils.APP;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Document(indexName = APP.ESProp.INDEX_NAME2, type = APP.ESProp.TYPE_DEC_PRICE)
public class DecPriceVo implements Serializable{
    /** 报价id,作为@BizId */
    @Id
    @Field(index =FieldIndex.not_analyzed, store = true)
    private String pid;

    /** 用户id */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String userId;

    /** 用户手机号 */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String userTel;

    /** 用户QQ */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String userQq;

    /** 用户昵称 */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String nickName;

    /** 城市id */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String cityId;

    /** 区县id */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String districtId;

    /** 街道id */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String streetId;

    /** 小区名称 */
    @Field(type = FieldType.String, searchAnalyzer="ik", store = true)
    private String residentialName;

    /** 小区id */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String baseId;

    /** 房屋户型（一室一厅...） */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String roomType;

    /** 建筑面积 */
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
    private Integer houseArea;

    /** 装修报价 */
    @Field(type = FieldType.Float, index = FieldIndex.not_analyzed, store = true)
    private Float decPrice;

    /** 审核状态（0-未审核，1-审核中，2-已审核，-1-已删除） */
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
    private Integer checkState;

    /** 阅读人次 */
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
    private Integer viewCount;

    /** 下载人次 */
    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
    private Integer downloadCount;

    /** 创建时间 */
    @Field(type = FieldType.Date, index = FieldIndex.not_analyzed, store = true)
    private Date gmtCreated;

    /** 修改时间 */
    @Field(type = FieldType.Date, index = FieldIndex.not_analyzed, store = true)
    private Date gmtModified;

    /** 创建人 */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String createdBy;

    /** 修改人 */
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String modifiedBy;

    private static final long serialVersionUID = 1L;
}