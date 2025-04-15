package com.jameshao.nhsserver.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName beddetails
 */
@TableName(value ="beddetails")
@Data
public class Beddetails {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 床位起始日期
     */
    private Date startDate;

    /**
     * 床位结束日期
     */
    private Date endDate;

    /**
     * 床位详情信息
     */
    private String bedDetails;

    /**
     * 顾客ID
     */
    private Integer customerId;

    /**
     * 床位ID
     */
    private Integer bedId;

    /**
     * 逻辑删除标记（0：显示；1：隐藏）
     */
    private Integer isDeleted;

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
        Beddetails other = (Beddetails) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getBedDetails() == null ? other.getBedDetails() == null : this.getBedDetails().equals(other.getBedDetails()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getBedId() == null ? other.getBedId() == null : this.getBedId().equals(other.getBedId()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getBedDetails() == null) ? 0 : getBedDetails().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getBedId() == null) ? 0 : getBedId().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", startDate=").append(startDate);
        sb.append(", endDate=").append(endDate);
        sb.append(", bedDetails=").append(bedDetails);
        sb.append(", customerId=").append(customerId);
        sb.append(", bedId=").append(bedId);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append("]");
        return sb.toString();
    }
}