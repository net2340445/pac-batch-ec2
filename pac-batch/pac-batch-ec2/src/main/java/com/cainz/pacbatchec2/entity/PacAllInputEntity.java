package com.cainz.pacbatchec2.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable(tableName = "pac_all")
public class PacAllInputEntity {
    @DynamoDBHashKey(attributeName = "pk")
    private String pk;
    @DynamoDBRangeKey(attributeName = "sk")
    private String sk;
    @DynamoDBAttribute(attributeName = "jan")
    private String jan;
    @DynamoDBAttribute(attributeName = "rank")
    private String rank;
    @DynamoDBAttribute(attributeName = "point")
    private String point;
    @DynamoDBAttribute(attributeName = "PromotionDesc")
    private String PromotionDesc;
    @DynamoDBAttribute(attributeName = "type")
    private String type;
    @DynamoDBAttribute(attributeName = "sdt")
    private String sdt;
    @DynamoDBAttribute(attributeName = "edt")
    private String edt;
    @DynamoDBAttribute(attributeName = "TodaysUpdateFlag")
    private String TodaysUpdateFlag;
    @DynamoDBAttribute(attributeName = "TodaysDeleteFlag")
    private String TodaysDeleteFlag;
    @DynamoDBAttribute(attributeName = "createUser")
    private String createUser;
    @DynamoDBAttribute(attributeName = "createTime")
    private String createTime;
    @DynamoDBAttribute(attributeName = "updateUser")
    private String updateUser;
    @DynamoDBAttribute(attributeName = "updateTime")
    private  String updateTime;

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getupdateTime() {
        return updateTime;
    }

    public void setupdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTodaysUpdateFlag() {
        return TodaysUpdateFlag;
    }

    public void setTodaysUpdateFlag(String todaysUpdateFlag) {
        TodaysUpdateFlag = todaysUpdateFlag;
    }

    public String getTodaysDeleteFlag() {
        return TodaysDeleteFlag;
    }

    public void setTodaysDeleteFlag(String todaysDeleteFlag) {
        TodaysDeleteFlag = todaysDeleteFlag;
    }

    public String getPromotionDesc() {
        return PromotionDesc;
    }

    public void setPromotionDesc(String promotionDesc) {
        PromotionDesc = promotionDesc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }

    public String getJan() {
        return jan;
    }

    public void setJan(String jan) {
        this.jan = jan;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }



    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEdt() {
        return edt;
    }

    public void setEdt(String edt) {
        this.edt = edt;
    }

    @Override
    public String toString() {
        return "PacAllEntity{" +
                "pk='" + pk + '\'' +
                ", sk='" + sk + '\'' +
                ", jan='" + jan + '\'' +
                ", rank='" + rank + '\'' +
                ", point='" + point + '\'' +
                ", pointdesc='" + PromotionDesc + '\'' +
                ", rewardtype='" + type + '\'' +
                ", sdt='" + sdt + '\'' +
                ", edt='" + edt + '\'' +
                ", TodaysUpdateFlag='" + TodaysUpdateFlag + '\'' +
                ", TodaysDeleteFlag='" + TodaysDeleteFlag + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateUser ='" + updateUser + '\'' +
                ", updateTime ='" + updateTime + '\'' +
                '}';
    }
}
