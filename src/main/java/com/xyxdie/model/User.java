package com.xyxdie.model;


import org.hibernate.validator.constraints.NotEmpty;


import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;

    @Column(name = "TYPE", nullable = false)
    protected int type;

    @Column(name = "NAME")
    private String name;

    @NotEmpty
    @Column(name = "PASSWD", nullable = false)
    private String passwd;

    @Column(name = "IMGURL", nullable = true)
    private String imgUrl;

    public User(){}

    public User(int id, String name, String passwd){
        this.name = name;
        this.id = id;
        this.passwd = passwd;
    }

    public User(int id, String name, String passwd, int type){
        this.name = name;
        this.id = id;
        this.passwd = passwd;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
