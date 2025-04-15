package com.jameshao.nhsserver.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName customerpreference
 */
@TableName(value ="customerpreference")
@Data
public class Customerpreference {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 
     */
    private Integer customerId;

    /**
     * 
     */
    private String preferences;

    /**
     * 
     */
    private String attention;

    /**
     * 
     */
    private String remark;

    /**
     * 
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
        Customerpreference other = (Customerpreference) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getPreferences() == null ? other.getPreferences() == null : this.getPreferences().equals(other.getPreferences()))
            && (this.getAttention() == null ? other.getAttention() == null : this.getAttention().equals(other.getAttention()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getPreferences() == null) ? 0 : getPreferences().hashCode());
        result = prime * result + ((getAttention() == null) ? 0 : getAttention().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
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
        sb.append(", customerId=").append(customerId);
        sb.append(", preferences=").append(preferences);
        sb.append(", attention=").append(attention);
        sb.append(", remark=").append(remark);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append("]");
        return sb.toString();
    }
}