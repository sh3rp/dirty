package org.kndl.spark.model;

/**
 * Created by skendall on 12/11/2014.
 */
public class Player {
    private long id;
    private String name;
    private String email;
    private int lifetimeScore;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getLifetimeScore() {
        return lifetimeScore;
    }

    public void setLifetimeScore(int lifetimeScore) {
        this.lifetimeScore = lifetimeScore;
    }

}
