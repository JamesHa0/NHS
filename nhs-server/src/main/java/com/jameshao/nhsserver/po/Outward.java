package com.jameshao.nhsserver.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName outward
 */
@TableName(value ="outward")
@Data
public class Outward {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 逻辑删除标记（0：显示；1：隐藏）
     */
    private Integer isDeleted;

    /**
     * 客户ID
     */
    private Integer customerId;

    /**
     * 外出事由
     */
    private String outgoingreason;

    /**
     * 外出时间
     */
    private Date outgoingtime;

    /**
     * 预计回院时间
     */
    private Date expectedreturntime;

    /**
     * 实际回院时间
     */
    private Date actualreturntime;

    /**
     * 陪同人
     */
    private String escorted;

    /**
     * 与老人关系
     */
    private String relation;

    /**
     * 陪同人电话
     */
    private String escortedtel;

    /**
     * 审批状态  0-已提交 1-同意  2-拒绝
     */
    private Integer auditstatus;

    /**
     * 审批人
     */
    private String auditperson;

    /**
     * 审批时间
     */
    private Date audittime;

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
        Outward other = (Outward) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getOutgoingreason() == null ? other.getOutgoingreason() == null : this.getOutgoingreason().equals(other.getOutgoingreason()))
            && (this.getOutgoingtime() == null ? other.getOutgoingtime() == null : this.getOutgoingtime().equals(other.getOutgoingtime()))
            && (this.getExpectedreturntime() == null ? other.getExpectedreturntime() == null : this.getExpectedreturntime().equals(other.getExpectedreturntime()))
            && (this.getActualreturntime() == null ? other.getActualreturntime() == null : this.getActualreturntime().equals(other.getActualreturntime()))
            && (this.getEscorted() == null ? other.getEscorted() == null : this.getEscorted().equals(other.getEscorted()))
            && (this.getRelation() == null ? other.getRelation() == null : this.getRelation().equals(other.getRelation()))
            && (this.getEscortedtel() == null ? other.getEscortedtel() == null : this.getEscortedtel().equals(other.getEscortedtel()))
            && (this.getAuditstatus() == null ? other.getAuditstatus() == null : this.getAuditstatus().equals(other.getAuditstatus()))
            && (this.getAuditperson() == null ? other.getAuditperson() == null : this.getAuditperson().equals(other.getAuditperson()))
            && (this.getAudittime() == null ? other.getAudittime() == null : this.getAudittime().equals(other.getAudittime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getOutgoingreason() == null) ? 0 : getOutgoingreason().hashCode());
        result = prime * result + ((getOutgoingtime() == null) ? 0 : getOutgoingtime().hashCode());
        result = prime * result + ((getExpectedreturntime() == null) ? 0 : getExpectedreturntime().hashCode());
        result = prime * result + ((getActualreturntime() == null) ? 0 : getActualreturntime().hashCode());
        result = prime * result + ((getEscorted() == null) ? 0 : getEscorted().hashCode());
        result = prime * result + ((getRelation() == null) ? 0 : getRelation().hashCode());
        result = prime * result + ((getEscortedtel() == null) ? 0 : getEscortedtel().hashCode());
        result = prime * result + ((getAuditstatus() == null) ? 0 : getAuditstatus().hashCode());
        result = prime * result + ((getAuditperson() == null) ? 0 : getAuditperson().hashCode());
        result = prime * result + ((getAudittime() == null) ? 0 : getAudittime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", remarks=").append(remarks);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", customerId=").append(customerId);
        sb.append(", outgoingreason=").append(outgoingreason);
        sb.append(", outgoingtime=").append(outgoingtime);
        sb.append(", expectedreturntime=").append(expectedreturntime);
        sb.append(", actualreturntime=").append(actualreturntime);
        sb.append(", escorted=").append(escorted);
        sb.append(", relation=").append(relation);
        sb.append(", escortedtel=").append(escortedtel);
        sb.append(", auditstatus=").append(auditstatus);
        sb.append(", auditperson=").append(auditperson);
        sb.append(", audittime=").append(audittime);
        sb.append("]");
        return sb.toString();
    }
}