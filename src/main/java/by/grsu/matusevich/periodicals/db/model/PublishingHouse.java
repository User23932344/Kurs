package by.grsu.matusevich.periodicals.db.model;

public class PublishingHouse {
    private Integer id;
    private String email;
    private String address;
    private Integer phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Publishing house[Id = " + id + ", Email = " + email + ", Address = " + address + ", Phone number = "
                + phone;
    }
    
}
