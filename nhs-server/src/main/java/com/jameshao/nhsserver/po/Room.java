package com.jameshao.nhsserver.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName room
 */
@TableName(value ="room")
@Data
public class Room {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 房间楼层
     */
    private String roomFloor;

    /**
     * 房间编号
     */
    private Integer roomNo;

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
        Room other = (Room) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRoomFloor() == null ? other.getRoomFloor() == null : this.getRoomFloor().equals(other.getRoomFloor()))
            && (this.getRoomNo() == null ? other.getRoomNo() == null : this.getRoomNo().equals(other.getRoomNo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoomFloor() == null) ? 0 : getRoomFloor().hashCode());
        result = prime * result + ((getRoomNo() == null) ? 0 : getRoomNo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roomFloor=").append(roomFloor);
        sb.append(", roomNo=").append(roomNo);
        sb.append("]");
        return sb.toString();
    }
}