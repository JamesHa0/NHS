package com.jameshao.nhsserver.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName customernurseitem
 */
@TableName(value ="customernurseitem")
@Data
public class Customernurseitem {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 护理项目编号
     */
    private Integer itemId;

    /**
     * 客户编号
     */
    private Integer custormerId;

    /**
     * 护理级别编号
     */
    private Integer levelId;

    /**
     * 购买数量
     */
    private Integer nurseNumber;

    /**
     * 逻辑删除标记（0：显示；1：隐藏）
     */
    private Integer isDeleted;

    /**
     * 服务购买日期
     */
    private Date buyTime;

    /**
     * 服务到期日
     */
    private Date maturityTime;

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
        Customernurseitem other = (Customernurseitem) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getItemId() == null ? other.getItemId() == null : this.getItemId().equals(other.getItemId()))
            && (this.getCustormerId() == null ? other.getCustormerId() == null : this.getCustormerId().equals(other.getCustormerId()))
            && (this.getLevelId() == null ? other.getLevelId() == null : this.getLevelId().equals(other.getLevelId()))
            && (this.getNurseNumber() == null ? other.getNurseNumber() == null : this.getNurseNumber().equals(other.getNurseNumber()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()))
            && (this.getBuyTime() == null ? other.getBuyTime() == null : this.getBuyTime().equals(other.getBuyTime()))
            && (this.getMaturityTime() == null ? other.getMaturityTime() == null : this.getMaturityTime().equals(other.getMaturityTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getItemId() == null) ? 0 : getItemId().hashCode());
        result = prime * result + ((getCustormerId() == null) ? 0 : getCustormerId().hashCode());
        result = prime * result + ((getLevelId() == null) ? 0 : getLevelId().hashCode());
        result = prime * result + ((getNurseNumber() == null) ? 0 : getNurseNumber().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        result = prime * result + ((getBuyTime() == null) ? 0 : getBuyTime().hashCode());
        result = prime * result + ((getMaturityTime() == null) ? 0 : getMaturityTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", itemId=").append(itemId);
        sb.append(", custormerId=").append(custormerId);
        sb.append(", levelId=").append(levelId);
        sb.append(", nurseNumber=").append(nurseNumber);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", buyTime=").append(buyTime);
        sb.append(", maturityTime=").append(maturityTime);
        sb.append("]");
        return sb.toString();
    }
}