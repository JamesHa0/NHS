package com.jameshao.nhsserver.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName backdown
 */
@TableName(value ="backdown")
@Data
public class Backdown {
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
     * 退住时间
     */
    private Date retreattime;

    /**
     * 退住类型 0-正常退住  1-死亡退住 2-保留床位
     */
    private Integer retreattype;

    /**
     * 退住原因
     */
    private String retreatreason;

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
        Backdown other = (Backdown) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getRetreattime() == null ? other.getRetreattime() == null : this.getRetreattime().equals(other.getRetreattime()))
            && (this.getRetreattype() == null ? other.getRetreattype() == null : this.getRetreattype().equals(other.getRetreattype()))
            && (this.getRetreatreason() == null ? other.getRetreatreason() == null : this.getRetreatreason().equals(other.getRetreatreason()))
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
        result = prime * result + ((getRetreattime() == null) ? 0 : getRetreattime().hashCode());
        result = prime * result + ((getRetreattype() == null) ? 0 : getRetreattype().hashCode());
        result = prime * result + ((getRetreatreason() == null) ? 0 : getRetreatreason().hashCode());
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
        sb.append(", retreattime=").append(retreattime);
        sb.append(", retreattype=").append(retreattype);
        sb.append(", retreatreason=").append(retreatreason);
        sb.append(", auditstatus=").append(auditstatus);
        sb.append(", auditperson=").append(auditperson);
        sb.append(", audittime=").append(audittime);
        sb.append("]");
        return sb.toString();
    }
}