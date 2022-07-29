package com.assignment.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.assignment.model.Todoitem;
import com.assignment.repository.TodoitemRepository;

@WebServlet(name = "serlvet_complete", urlPatterns = {"/todoitem/complete"})
public class TodoitemCompleteController extends HttpServlet{

    public void init(ServletConfig config) throws ServletException{
        super.init(config);
    }

    // get all completed todo item
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();

        JSONObject json = new JSONObject();

        try {
           
            List<Todoitem> list = TodoitemRepository.getAllByStatus(true);

            JSONArray ja = new JSONArray();
            for (Todoitem item : list) {
                ja.put(item.toJson());
            }

            json.put("status", "success");
            json.put("items", ja);
            json.put("count", list.size());
            response.setStatus(200);
  
        } catch (Exception e) {
            e.printStackTrace();
            json.put("status", "fail");
            json.put("reason", e.getMessage());
            response.setStatus(500);
        } 

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.write(json.toString());
        out.close();
    }

    // set todo item to completed by id
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        JSONObject json = new JSONObject();
        PrintWriter out = response.getWriter();

        int id = request.getIntHeader("id");

        try {

            if(TodoitemRepository.exits(id)){
                Todoitem item = TodoitemRepository.get(id);

                item.setName(request.getHeader("name"));
                item.setDescription(request.getHeader("description"));
                item.setCompleted(Boolean.parseBoolean(request.getHeader("complete")));

                TodoitemRepository.complete(id, true);
                item = TodoitemRepository.get(id);

                json.put("detail", item.toJson());
                json.put("status", "success");
                response.setStatus(200);
            }else{
                response.setStatus(404);
                json.put("status", "fail");
                json.put("reason", "item not found");
            }
          
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            json.put("status", "fail");
            json.put("reason", e.getMessage());
            response.setStatus(500);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.write(json.toString());
        out.close();
    }

}