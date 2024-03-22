package parking_lot.models;

import java.util.Date;

public abstract class  BaseModel {
    private int id;
    private Date createdAt;
    private Date updatedAt;

    private static int nextAvailableId=1;

    public BaseModel() {
        this.id=nextAvailableId;
        nextAvailableId++;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
