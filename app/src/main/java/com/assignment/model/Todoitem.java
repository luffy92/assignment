package com.assignment.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;

import org.json.JSONObject;

public class Todoitem {

    private int id;
    private String name;
    private String description;
    private boolean completed = false;
    private Date create_date = new Date();
    private Date update_date = new Date();

    public Todoitem() {}

    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getCompleted() {
        return this.completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void renewUpdateDate() {
        this.update_date = new Date();
    }

    public void setItem(ResultSet result) throws SQLException{
        this.id = result.getInt("id");
        this.name = result.getString("name");
        this.description = result.getString("description");
        this.completed = result.getBoolean("completed");
        this.create_date = result.getTimestamp("create_date");
        this.update_date = result.getTimestamp("update_date");
    }   

    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("id",this.id);
        json.put("name",this.name);
        json.put("description",this.description);
        json.put("completed",this.completed);
        json.put("create_date",this.create_date.toString());
        json.put("update_date",this.update_date.toString());

        return json;
    }
}
