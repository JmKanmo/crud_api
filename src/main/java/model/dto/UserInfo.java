package model.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserInfo {
    private int id;
    private String name;
    private String job;
    private String hometown;
    private String email;
    private Timestamp createdOn;

    public static class Builder {
        private int id_ = 0;
        private String name_ = "";
        private String job_ = "";
        private String hometown_ = "";
        private String email_ = "";
        private Timestamp createdOn_ = null;

        public Builder id_(int id) {
            id_ = id;
            return this;
        }

        public Builder name_(String name) {
            name_ = name;
            return this;
        }

        public Builder job_(String job) {
            job_ = job;
            return this;
        }

        public Builder hometown_(String hometown) {
            hometown_ = hometown;
            return this;
        }

        public Builder email_(String email) {
            email_ = email;
            return this;
        }

        public Builder createdOn_(Timestamp createdOn) {
            createdOn_ = createdOn;
            return this;
        }

        public UserInfo build() {
            return new UserInfo(this);
        }
    }

    private UserInfo(Builder builder) {
        id = builder.id_;
        name = builder.name_;
        job = builder.job_;
        hometown = builder.hometown_;
        email = builder.email_;
        createdOn = builder.createdOn_;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreatedOn() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        String today = formatter.format(cal.getTime());
        createdOn = Timestamp.valueOf(today);
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", hometown='" + hometown + '\'' +
                ", email='" + email + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}
