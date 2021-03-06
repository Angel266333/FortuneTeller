package com.angel266489.fortuneteller.database;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "wish")
public class Wish {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String wish;
    private String email;

    public Wish(String wish, String email) {
        this.wish = wish;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getWish() {
        return wish;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWish(String wish) {
        this.wish = wish;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wish wish1 = (Wish) o;

        if (id != wish1.id) return false;
        if (wish != null ? !wish.equals(wish1.wish) : wish1.wish != null) return false;
        return email != null ? email.equals(wish1.email) : wish1.email == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (wish != null ? wish.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Wish{" +
                "id=" + id +
                ", wish='" + wish + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
