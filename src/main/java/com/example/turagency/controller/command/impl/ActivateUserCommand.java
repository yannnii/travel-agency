package com.example.turagency.controller.command.impl;

import com.example.turagency.controller.AttributeName;
import com.example.turagency.controller.command.Command;
import com.example.turagency.exception.ServiceException;
import com.example.turagency.model.entity.User;
import com.example.turagency.model.service.UserService;
import com.example.turagency.model.service.impl.UserServiceImpl;
import com.example.turagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ActivateUserCommand implements Command {
    private static Logger logger = LogManager.getLogger(ActivateUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        String idUserModerate = request.getParameter(AttributeName.ID_USER_MODERATE);
        try {
            if (service.activateUser(idUserModerate)) {
                String currentUser = (String) session.getAttribute(AttributeName.USER);
                List<User> users = service.findAllUsersWithoutCurrent(currentUser);
                session.setAttribute(AttributeName.USERS, users);
                logger.info("Activate user: " + idUserModerate);
            } else {
                request.setAttribute(AttributeName.ACTIVATE_USER_ERROR, true);
                logger.warn("User" + idUserModerate + " activation is failed");
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}