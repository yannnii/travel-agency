package com.example.turagency.controller.command.impl;

import com.example.turagency.controller.AttributeName;
import com.example.turagency.controller.command.Command;
import com.example.turagency.exception.ServiceException;
import com.example.turagency.model.service.UserService;
import com.example.turagency.model.service.impl.UserServiceImpl;
import com.example.turagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangePasswordCommand implements Command {
    private static Logger logger = LogManager.getLogger(ChangePasswordCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        String login = (String) session.getAttribute(AttributeName.USER);
        String currentPassword = request.getParameter(AttributeName.CURRENT_PASSWORD);
        String newPassword = request.getParameter(AttributeName.NEW_PASSWORD);
        try {
            if (service.changePassword(login, currentPassword, newPassword)) {
                request.setAttribute(AttributeName.CHANGE_PASSWORD, true);
                logger.info("Changing password is complete for " + login);
            } else {
                request.setAttribute(AttributeName.CHANGE_PASSWORD, false);
                logger.warn("Changing password is failed for " + login);
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}