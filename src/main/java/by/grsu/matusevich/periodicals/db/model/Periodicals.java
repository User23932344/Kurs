package by.grsu.matusevich.periodicals.db.model;


public class Periodicals {
    private Integer id;
    private String name;
    private String synopsis;
    private Double price;
    private Integer publishing_house_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPublishing_house_id() {
        return publishing_house_id;
    }

    public void setPublishing_house_id(Integer publishing_house_id) {
        this.publishing_house_id = publishing_house_id;
    }

    @Override
    public String toString() {
        return "Periodicals[ Id = " + id + ", Name = " + name + ", Synopsis = " + synopsis + ", Price = " + price
                + ", Publishing house id = " + publishing_house_id;}
    
}

