package com.jameshao.nhsserver.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName meal
 */
@TableName(value ="meal")
@Data
public class Meal {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 
     */
    private String weekDay;

    /**
     * 
     */
    private Integer foodId;

    /**
     * 
     */
    private Integer mealType;

    /**
     * 
     */
    private String taste;

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
        Meal other = (Meal) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getWeekDay() == null ? other.getWeekDay() == null : this.getWeekDay().equals(other.getWeekDay()))
            && (this.getFoodId() == null ? other.getFoodId() == null : this.getFoodId().equals(other.getFoodId()))
            && (this.getMealType() == null ? other.getMealType() == null : this.getMealType().equals(other.getMealType()))
            && (this.getTaste() == null ? other.getTaste() == null : this.getTaste().equals(other.getTaste()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getWeekDay() == null) ? 0 : getWeekDay().hashCode());
        result = prime * result + ((getFoodId() == null) ? 0 : getFoodId().hashCode());
        result = prime * result + ((getMealType() == null) ? 0 : getMealType().hashCode());
        result = prime * result + ((getTaste() == null) ? 0 : getTaste().hashCode());
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
        sb.append(", weekDay=").append(weekDay);
        sb.append(", foodId=").append(foodId);
        sb.append(", mealType=").append(mealType);
        sb.append(", taste=").append(taste);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append("]");
        return sb.toString();
    }
}