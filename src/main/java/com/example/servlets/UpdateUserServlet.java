package com.example.servlets;

import com.example.models.UserModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.example.utils.ServerUtils.*;

public class UpdateUserServlet extends HttpServlet {

    private Map<Integer, UserModel> getUsersFromContext() {
        Object users = getServletContext().getAttribute("users");
        return getUsersMap(users);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel userModel = getUserById(req);
        req.setAttribute("user", userModel);
        req.getRequestDispatcher("updateUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserModel userModel = getUserById(req);
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        if(isUserDataValid(name, age)){
            int intAge = Integer.parseInt(age);
            userModel.setName(name);
            userModel.setAge(intAge);
            req.setAttribute("user", userModel);
            req.getRequestDispatcher("updateUser.jsp").forward(req, resp);
            return;
        }
        throw new IllegalArgumentException("Please, provide valid user data");
    }

    private UserModel getUserById(HttpServletRequest req){
        String id = req.getParameter("id");
        if (isInteger(id)) {
            int userId = Integer.parseInt(id);
            Map<Integer, UserModel> usersFromContext = getUsersFromContext();
            UserModel userModel = usersFromContext.get(userId);
            if (userModel != null) {
                return userModel;
            }
        }
        throw new IllegalArgumentException("There is no such user in the system.");
    }
}