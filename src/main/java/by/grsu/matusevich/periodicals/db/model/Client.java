package by.grsu.matusevich.periodicals.db.model;

public class Client {
        private Integer id;
        private String name;
        private String email;
        private String address;
        private Integer phone;
        private Double balance;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return name;
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

        public Double getBalance() {
            return balance;
        }

        public void setBalance(Double balance) {
            this.balance = balance;
        }

        @Override
        public String toString() {
            return "Client[id = " + id + ", Name = " + name + ", Email = " + email + ", Address = " + address
                    + ", Phone = " + phone + ", Balance = " + balance + "]";      
    }
}