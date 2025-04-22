package com.jameshao.nhsserver.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName nurserecord
 */
@TableName(value ="nurserecord")
@Data
public class Nurserecord {
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
     * 客户ID
     */
    private Integer customerId;

    /**
     * 护理项目ID
     */
    private Integer itemId;

    /**
     * 护理时间
     */
    private Date nursingTime;

    /**
     * 护理内容
     */
    private String nursingContent;

    /**
     * 护理数量
     */
    private Integer nursingCount;

    /**
     * 护理人员ID
     */
    private Integer userId;


    @TableField(exist = false)
    private List<Nursecontent> nurseContentInfo;

    @TableField(exist = false)
    private List<Customer> customerInfo;

    @TableField(exist = false)
    private List<User> userInfo;

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
        Nurserecord other = (Nurserecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getItemId() == null ? other.getItemId() == null : this.getItemId().equals(other.getItemId()))
            && (this.getNursingTime() == null ? other.getNursingTime() == null : this.getNursingTime().equals(other.getNursingTime()))
            && (this.getNursingContent() == null ? other.getNursingContent() == null : this.getNursingContent().equals(other.getNursingContent()))
            && (this.getNursingCount() == null ? other.getNursingCount() == null : this.getNursingCount().equals(other.getNursingCount()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getItemId() == null) ? 0 : getItemId().hashCode());
        result = prime * result + ((getNursingTime() == null) ? 0 : getNursingTime().hashCode());
        result = prime * result + ((getNursingContent() == null) ? 0 : getNursingContent().hashCode());
        result = prime * result + ((getNursingCount() == null) ? 0 : getNursingCount().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
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
        sb.append(", customerId=").append(customerId);
        sb.append(", itemId=").append(itemId);
        sb.append(", nursingTime=").append(nursingTime);
        sb.append(", nursingContent=").append(nursingContent);
        sb.append(", nursingCount=").append(nursingCount);
        sb.append(", userId=").append(userId);
        sb.append("]");
        return sb.toString();
    }
}