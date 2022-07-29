package com.assignment.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.assignment.model.Todoitem;

public class TodoitemRepository {
    
    // get all todo items
    static public List<Todoitem> getAll() throws SQLException, ClassNotFoundException{

        List<Todoitem> list = new ArrayList<Todoitem>();

        DbConnection.connect();
        Statement stmt = DbConnection.con.createStatement();
        ResultSet rs=stmt.executeQuery("select * from todoitem");  

        while(rs.next()) {
            Todoitem item = new Todoitem();
            item.setItem(rs);
            list.add(item);
        }

        stmt.close();
        DbConnection.disconnect();

        return list;
    }

    // add new todo items
    static public Todoitem add(Todoitem item) throws ClassNotFoundException, SQLException{
        DbConnection.connect();

        String sql = "INSERT INTO todoitem(name,description) VALUES (?,?)";
        PreparedStatement stmt = DbConnection.con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, item.getName());
        stmt.setString(2, item.getDescription());

        stmt.executeUpdate();  

        ResultSet keyResultSet = stmt.getGeneratedKeys();
        if (keyResultSet.next()) {
            item.setId((int) keyResultSet.getInt(1));
        }
        
        stmt.close();
        DbConnection.disconnect();

        return item;
    }

    // delete todo items by id
    static public void delete(int id) throws SQLException, ClassNotFoundException{
        DbConnection.connect();

        String sql = "delete from todoitem where id = ?";
        PreparedStatement stmt = DbConnection.con.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();  

        stmt.close();
        DbConnection.disconnect();
    }

    // check todo item exits by id
    static public boolean exits(int id) throws SQLException, ClassNotFoundException{
        DbConnection.connect();

        String sql = "select * from todoitem where id = ?";
        PreparedStatement stmt = DbConnection.con.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet resultSet = stmt.executeQuery();

        boolean recordFound = resultSet.next();

        stmt.close();
        DbConnection.disconnect();

        return recordFound;
    }

    // get todo item by id
    static public Todoitem get(int id) throws SQLException, ClassNotFoundException{
        DbConnection.connect();

        String sql = "select * from todoitem where id = ?";
        PreparedStatement stmt = DbConnection.con.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet resultSet = stmt.executeQuery();

        Todoitem item = new Todoitem();

        if(resultSet.next()){
            item.setItem(resultSet);
        }

        stmt.close();
        DbConnection.disconnect();

        return item;
    }

    // save todo item by id
    static public Todoitem save(Todoitem item) throws ClassNotFoundException, SQLException{
        DbConnection.connect();

        String sql = "UPDATE todoitem set name = ?, description = ?, completed = ?, update_date = CURRENT_TIMESTAMP() where id = ?";
        PreparedStatement stmt = DbConnection.con.prepareStatement(sql);
        stmt.setString(1, item.getName());
        stmt.setString(2, item.getDescription());
        stmt.setBoolean(3, item.getCompleted());
        stmt.setInt(4, item.getId());
        
        stmt.executeUpdate();  
        
        stmt.close();
        DbConnection.disconnect();

        return item;
    }

    // set the todo item completed or pending by id
    static public void complete(int id, boolean completed) throws ClassNotFoundException, SQLException{
        DbConnection.connect();

        String sql = "UPDATE todoitem set completed = ?, update_date = CURRENT_TIMESTAMP() where id = ?";
        PreparedStatement stmt = DbConnection.con.prepareStatement(sql);
        stmt.setBoolean(1, completed);
        stmt.setInt(2, id);
        
        stmt.executeUpdate();  
        
        stmt.close();
        DbConnection.disconnect();

    }

     // get all todo items by status
    static public List<Todoitem> getAllByStatus(Boolean completed) throws SQLException, ClassNotFoundException{

        List<Todoitem> list = new ArrayList<Todoitem>();

        DbConnection.connect();

        String sql = "select * from todoitem where completed = ?";
        PreparedStatement stmt = DbConnection.con.prepareStatement(sql);
        stmt.setBoolean(1, completed);

        ResultSet rs = stmt.executeQuery();

        while(rs.next()) {
            Todoitem item = new Todoitem();
            item.setItem(rs);
            list.add(item);
        }

        stmt.close();
        DbConnection.disconnect();

        return list;
    }
}
