package id.ac.poliban.mi.atul.sqlitedemos01.domain;

import java.io.Serializable;

public class Friend implements Serializable {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String photo;

    public Friend(int id, String name, String address, String phone, String photo) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.photo = photo;
    }
    public Friend(String name, String address, String phone, String photo) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return String.format("%3d %25s %24s", getId(), getName(), getPhone());
    }
}
