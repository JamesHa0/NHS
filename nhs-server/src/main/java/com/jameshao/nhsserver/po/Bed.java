package com.jameshao.nhsserver.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName bed
 */
@TableName(value ="bed")
@Data
public class Bed {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 房间编号
     */
    private Integer roomNo;

    /**
     * 房间状态 1：空闲  2：有人  3：外出
     */
    private Integer bedStatus;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 床位编号
     */
    private String bedNo;

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
        Bed other = (Bed) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRoomNo() == null ? other.getRoomNo() == null : this.getRoomNo().equals(other.getRoomNo()))
            && (this.getBedStatus() == null ? other.getBedStatus() == null : this.getBedStatus().equals(other.getBedStatus()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getBedNo() == null ? other.getBedNo() == null : this.getBedNo().equals(other.getBedNo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoomNo() == null) ? 0 : getRoomNo().hashCode());
        result = prime * result + ((getBedStatus() == null) ? 0 : getBedStatus().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getBedNo() == null) ? 0 : getBedNo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roomNo=").append(roomNo);
        sb.append(", bedStatus=").append(bedStatus);
        sb.append(", remarks=").append(remarks);
        sb.append(", bedNo=").append(bedNo);
        sb.append("]");
        return sb.toString();
    }
}