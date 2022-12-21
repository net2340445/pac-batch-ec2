package com.cainz.pacbatchec2.dto;

public class Consolidate {
    private String MasterStoreCode;
    private String MaintenanceStoreMode;
    private String PromotionCode;
    private String Code;
    private String PromotionDescription;
    private String PromotionStartDate;
    private String PromotionStartTime;
    private String PromotionEndDate;
    private String PromotionEndTime;
    private String TodaysUpdateFlag;
    private String TodaysDeleteFlag;
    private String MemberRewardLevelFlags;
    private String RewardType;
    private String RewardValue;
    private String ItemCode;

    public Consolidate() {
    }

    public Consolidate(String masterStoreCode, String maintenanceStoreMode, String promotionCode, String code, String promotionDescription, String promotionStartDate, String promotionStartTime, String promotionEndDate, String promotionEndTime, String todaysUpdateFlag, String todaysDeleteFlag, String memberRewardLevelFlags, String rewardType, String rewardValue, String itemCode) {
        MasterStoreCode = masterStoreCode;
        MaintenanceStoreMode = maintenanceStoreMode;
        PromotionCode = promotionCode;
        Code = code;
        PromotionDescription = promotionDescription;
        PromotionStartDate = promotionStartDate;
        PromotionStartTime = promotionStartTime;
        PromotionEndDate = promotionEndDate;
        PromotionEndTime = promotionEndTime;
        TodaysUpdateFlag = todaysUpdateFlag;
        TodaysDeleteFlag = todaysDeleteFlag;
        MemberRewardLevelFlags = memberRewardLevelFlags;
        RewardType = rewardType;
        RewardValue = rewardValue;
        ItemCode = itemCode;
    }

    public String getMasterStoreCode() {
        return MasterStoreCode;
    }

    public void setMasterStoreCode(String masterStoreCode) {
        MasterStoreCode = masterStoreCode;
    }

    public String getMaintenanceStoreMode() {
        return MaintenanceStoreMode;
    }

    public void setMaintenanceStoreMode(String maintenanceStoreMode) {
        MaintenanceStoreMode = maintenanceStoreMode;
    }

    public String getPromotionCode() {
        return PromotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        PromotionCode = promotionCode;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getPromotionDescription() {
        return PromotionDescription;
    }

    public void setPromotionDescription(String promotionDescription) {
        PromotionDescription = promotionDescription;
    }

    public String getPromotionStartDate() {
        return PromotionStartDate;
    }

    public void setPromotionStartDate(String promotionStartDate) {
        PromotionStartDate = promotionStartDate;
    }

    public String getPromotionStartTime() {
        return PromotionStartTime;
    }

    public void setPromotionStartTime(String promotionStartTime) {
        PromotionStartTime = promotionStartTime;
    }

    public String getPromotionEndDate() {
        return PromotionEndDate;
    }

    public void setPromotionEndDate(String promotionEndDate) {
        PromotionEndDate = promotionEndDate;
    }

    public String getPromotionEndTime() {
        return PromotionEndTime;
    }

    public void setPromotionEndTime(String promotionEndTime) {
        PromotionEndTime = promotionEndTime;
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

    public String getMemberRewardLevelFlags() {
        return MemberRewardLevelFlags;
    }

    public void setMemberRewardLevelFlags(String memberRewardLevelFlags) {
        MemberRewardLevelFlags = memberRewardLevelFlags;
    }

    public String getRewardType() {
        return RewardType;
    }

    public void setRewardType(String rewardType) {
        RewardType = rewardType;
    }

    public String getRewardValue() {
        return RewardValue;
    }

    public void setRewardValue(String rewardValue) {
        RewardValue = rewardValue;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }
}
