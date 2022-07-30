package com.assignment.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assignment.repository.DbConnection;

@WebServlet(name = "Index Serlvet", urlPatterns = {"/"})
public class IndexController extends HttpServlet{

    public void init(ServletConfig config) throws ServletException{
        super.init(config);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PrintWriter out = response.getWriter();

        try{
            DbConnection.connect();
            DbConnection.disconnect();
            out.write("<h1>Database connected. Welcome!!</h1>");
        }catch(Exception e){
            out.write("<h1>Database connection error, please try again later.</h1>");
        }
        
        response.setStatus(200);
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        out.close();
    }



}