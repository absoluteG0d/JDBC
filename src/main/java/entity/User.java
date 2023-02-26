package entity;

import java.time.LocalDate;

public class User {
    private int id;
    private String name;
    private String address;
    private String email;
    private LocalDate birthday;
    private long telephone;

    private User(int id, String name, String address, String email, LocalDate birthday, long telephone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.birthday = birthday;
        this.telephone = telephone;
    }
    public static class UserBuilder{
        private int id;
        private String name;
        private String address;
        private String email;
        private LocalDate birthday;
        private long telephone;

        public UserBuilder setId(int id) {
            this.id = id;
            return this;
        }
        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }
        public UserBuilder setAddress(String address) {
            this.address = address;
            return this;
        }
        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }
        public UserBuilder setBirthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }
        public UserBuilder setTelephone(long telephone) {
            this.telephone = telephone;
            return this;
        }

        public User build(){
            if(name==null || address==null || email==null || birthday==null || telephone<=0){
                throw new RuntimeException("Trying to create an empty user");
            }
            return new User(id,name,address,email,birthday, telephone);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", telephone=" + telephone +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public long getTelephone() {
        return telephone;
    }
}
