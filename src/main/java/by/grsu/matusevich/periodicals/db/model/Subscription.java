package by.grsu.matusevich.periodicals.db.model;

import java.sql.Timestamp;

public class Subscription {
    private Integer id;
    private Integer client_id;
    private Integer periodicals_id;
    private Boolean status;
    private Timestamp created;
    private Double price;

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public Integer getPeriodicals_id() {
        return periodicals_id;
    }

    public void setPeriodicals_id(Integer integer) {
        this.periodicals_id = integer;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Subscription[Client_id = " + client_id + ", Periodicals_id = " + periodicals_id + ", Stasus = "
                + status + ", Created = " + created + ", Price = " + price;
    }
}
