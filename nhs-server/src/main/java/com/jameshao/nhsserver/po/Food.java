package com.jameshao.nhsserver.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName food
 */
@TableName(value ="food")
@Data
public class Food {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 
     */
    private String foodName;

    /**
     * 
     */
    private String foodType;

    /**
     * 
     */
    private BigDecimal price;

    /**
     * 
     */
    private Integer isHalal;

    /**
     * 
     */
    private String foodImg;

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
        Food other = (Food) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFoodName() == null ? other.getFoodName() == null : this.getFoodName().equals(other.getFoodName()))
            && (this.getFoodType() == null ? other.getFoodType() == null : this.getFoodType().equals(other.getFoodType()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getIsHalal() == null ? other.getIsHalal() == null : this.getIsHalal().equals(other.getIsHalal()))
            && (this.getFoodImg() == null ? other.getFoodImg() == null : this.getFoodImg().equals(other.getFoodImg()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFoodName() == null) ? 0 : getFoodName().hashCode());
        result = prime * result + ((getFoodType() == null) ? 0 : getFoodType().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getIsHalal() == null) ? 0 : getIsHalal().hashCode());
        result = prime * result + ((getFoodImg() == null) ? 0 : getFoodImg().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", foodName=").append(foodName);
        sb.append(", foodType=").append(foodType);
        sb.append(", price=").append(price);
        sb.append(", isHalal=").append(isHalal);
        sb.append(", foodImg=").append(foodImg);
        sb.append("]");
        return sb.toString();
    }
}