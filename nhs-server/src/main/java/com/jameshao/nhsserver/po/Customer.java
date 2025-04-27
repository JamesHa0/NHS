package com.jameshao.nhsserver.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName customer
 */
@TableName(value ="customer")
@Data
public class Customer {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 逻辑删除标记（0：显示；1：隐藏）
     */
    private Integer isDeleted;

    /**
     * 客户姓名
     */
    private String customerName;

    /**
     * 年龄
     */
    private Integer customerAge;

    /**
     * 性别  0：男  1：女
     */
    private Integer customerSex;

    /**
     * 身份证号
     */
    private String idcard;

    /**
     * 房间号
     */
    private String roomNo;

    /**
     * 所属楼房
     */
    private String buildingNo;

    /**
     * 入住时间
     */
    private Date checkinDate;

    /**
     * 合同到期时间
     */
    private Date expirationDate;

    /**
     * 联系电话
     */
    private String contactTel;

    /**
     * 床号
     */
    private Integer bedId;

    /**
     * 身心状况
     */
    private String psychosomaticState;

    /**
     * 注意事项
     */
    private String attention;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 身高
     */
    private String height;

    /**
     * 体重
     */
    private String weight;

    /**
     * 血型
     */
    private String bloodType;

    /**
     * 头像路径
     */
    private String filepath;

    /**
     * 关联系统健康管家(护工)  无管家设置  -1
     */
    private Integer userId;

    /**
     * 护理等级
     */
    private Integer levelId;

    /**
     * 家属
     */
    private String familyMember;

    @TableField(exist = false)
    private String nickName;

    @TableField(exist = false)
    private String levelName;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Customer other = (Customer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getCustomerAge() == null ? other.getCustomerAge() == null : this.getCustomerAge().equals(other.getCustomerAge()))
            && (this.getCustomerSex() == null ? other.getCustomerSex() == null : this.getCustomerSex().equals(other.getCustomerSex()))
            && (this.getIdcard() == null ? other.getIdcard() == null : this.getIdcard().equals(other.getIdcard()))
            && (this.getRoomNo() == null ? other.getRoomNo() == null : this.getRoomNo().equals(other.getRoomNo()))
            && (this.getBuildingNo() == null ? other.getBuildingNo() == null : this.getBuildingNo().equals(other.getBuildingNo()))
            && (this.getCheckinDate() == null ? other.getCheckinDate() == null : this.getCheckinDate().equals(other.getCheckinDate()))
            && (this.getExpirationDate() == null ? other.getExpirationDate() == null : this.getExpirationDate().equals(other.getExpirationDate()))
            && (this.getContactTel() == null ? other.getContactTel() == null : this.getContactTel().equals(other.getContactTel()))
            && (this.getBedId() == null ? other.getBedId() == null : this.getBedId().equals(other.getBedId()))
            && (this.getPsychosomaticState() == null ? other.getPsychosomaticState() == null : this.getPsychosomaticState().equals(other.getPsychosomaticState()))
            && (this.getAttention() == null ? other.getAttention() == null : this.getAttention().equals(other.getAttention()))
            && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday()))
            && (this.getHeight() == null ? other.getHeight() == null : this.getHeight().equals(other.getHeight()))
            && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
            && (this.getBloodType() == null ? other.getBloodType() == null : this.getBloodType().equals(other.getBloodType()))
            && (this.getFilepath() == null ? other.getFilepath() == null : this.getFilepath().equals(other.getFilepath()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getLevelId() == null ? other.getLevelId() == null : this.getLevelId().equals(other.getLevelId()))
            && (this.getFamilyMember() == null ? other.getFamilyMember() == null : this.getFamilyMember().equals(other.getFamilyMember()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getCustomerAge() == null) ? 0 : getCustomerAge().hashCode());
        result = prime * result + ((getCustomerSex() == null) ? 0 : getCustomerSex().hashCode());
        result = prime * result + ((getIdcard() == null) ? 0 : getIdcard().hashCode());
        result = prime * result + ((getRoomNo() == null) ? 0 : getRoomNo().hashCode());
        result = prime * result + ((getBuildingNo() == null) ? 0 : getBuildingNo().hashCode());
        result = prime * result + ((getCheckinDate() == null) ? 0 : getCheckinDate().hashCode());
        result = prime * result + ((getExpirationDate() == null) ? 0 : getExpirationDate().hashCode());
        result = prime * result + ((getContactTel() == null) ? 0 : getContactTel().hashCode());
        result = prime * result + ((getBedId() == null) ? 0 : getBedId().hashCode());
        result = prime * result + ((getPsychosomaticState() == null) ? 0 : getPsychosomaticState().hashCode());
        result = prime * result + ((getAttention() == null) ? 0 : getAttention().hashCode());
        result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
        result = prime * result + ((getHeight() == null) ? 0 : getHeight().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getBloodType() == null) ? 0 : getBloodType().hashCode());
        result = prime * result + ((getFilepath() == null) ? 0 : getFilepath().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getLevelId() == null) ? 0 : getLevelId().hashCode());
        result = prime * result + ((getFamilyMember() == null) ? 0 : getFamilyMember().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", customerName=").append(customerName);
        sb.append(", customerAge=").append(customerAge);
        sb.append(", customerSex=").append(customerSex);
        sb.append(", idcard=").append(idcard);
        sb.append(", roomNo=").append(roomNo);
        sb.append(", buildingNo=").append(buildingNo);
        sb.append(", checkinDate=").append(checkinDate);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", contactTel=").append(contactTel);
        sb.append(", bedId=").append(bedId);
        sb.append(", psychosomaticState=").append(psychosomaticState);
        sb.append(", attention=").append(attention);
        sb.append(", birthday=").append(birthday);
        sb.append(", height=").append(height);
        sb.append(", weight=").append(weight);
        sb.append(", bloodType=").append(bloodType);
        sb.append(", filepath=").append(filepath);
        sb.append(", userId=").append(userId);
        sb.append(", levelId=").append(levelId);
        sb.append(", familyMember=").append(familyMember);
        sb.append("]");
        return sb.toString();
    }
}