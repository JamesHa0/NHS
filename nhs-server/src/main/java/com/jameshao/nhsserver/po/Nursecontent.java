package com.jameshao.nhsserver.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName nursecontent
 */
@TableName(value ="nursecontent")
@Data
public class Nursecontent {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 编号
     */
    private String serialNumber;

    /**
     * 名称
     */
    private String nursingName;

    /**
     * 价格
     */
    private String servicePrice;

    /**
     *  描述
     */
    private String message;

    /**
     * 状态 1：启用；2：停用
     */
    private Integer status;

    /**
     * 执行周期
     */
    private String executionCycle;

    /**
     * 执行次数
     */
    private String executionTimes;

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
        Nursecontent other = (Nursecontent) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSerialNumber() == null ? other.getSerialNumber() == null : this.getSerialNumber().equals(other.getSerialNumber()))
            && (this.getNursingName() == null ? other.getNursingName() == null : this.getNursingName().equals(other.getNursingName()))
            && (this.getServicePrice() == null ? other.getServicePrice() == null : this.getServicePrice().equals(other.getServicePrice()))
            && (this.getMessage() == null ? other.getMessage() == null : this.getMessage().equals(other.getMessage()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getExecutionCycle() == null ? other.getExecutionCycle() == null : this.getExecutionCycle().equals(other.getExecutionCycle()))
            && (this.getExecutionTimes() == null ? other.getExecutionTimes() == null : this.getExecutionTimes().equals(other.getExecutionTimes()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSerialNumber() == null) ? 0 : getSerialNumber().hashCode());
        result = prime * result + ((getNursingName() == null) ? 0 : getNursingName().hashCode());
        result = prime * result + ((getServicePrice() == null) ? 0 : getServicePrice().hashCode());
        result = prime * result + ((getMessage() == null) ? 0 : getMessage().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getExecutionCycle() == null) ? 0 : getExecutionCycle().hashCode());
        result = prime * result + ((getExecutionTimes() == null) ? 0 : getExecutionTimes().hashCode());
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
        sb.append(", serialNumber=").append(serialNumber);
        sb.append(", nursingName=").append(nursingName);
        sb.append(", servicePrice=").append(servicePrice);
        sb.append(", message=").append(message);
        sb.append(", status=").append(status);
        sb.append(", executionCycle=").append(executionCycle);
        sb.append(", executionTimes=").append(executionTimes);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append("]");
        return sb.toString();
    }
}