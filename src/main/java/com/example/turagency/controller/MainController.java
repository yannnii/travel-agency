package com.example.turagency.controller;

import com.example.turagency.controller.command.Command;
import com.example.turagency.model.connection.ConnectionPool;
import com.example.turagency.util.PathManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/controller"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Command command = ActionProvider.defineCommand(request);
        String page = (command != null) ? command.execute(request) : PathManager.getProperty(PathManager.PAGE_INDEX);
        if (page != null && !page.isBlank()) {
            HttpSession session = request.getSession();
            session.setAttribute(AttributeName.CURRENT_PAGE, page);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = PathManager.getProperty(PathManager.PAGE_GUEST_HOME);
            response.sendRedirect(request.getContextPath() + page);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.INSTANCE.destroyPool();
    }

}