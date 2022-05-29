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

public class ActivateEmailCommand implements Command {
    private static Logger logger = LogManager.getLogger(RegisterCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String page;
        String user = request.getParameter(AttributeName.USER);
        String currentUserInSession = (String) session.getAttribute(AttributeName.USER);
        boolean isActiveUser = user.equals(currentUserInSession);
        if (!isActiveUser) {
            session.invalidate();
        }
        try {
            if (service.activateUserEmail(user)) {
                page = (isActiveUser)
                        ? (String) session.getAttribute(AttributeName.CURRENT_PAGE)
                        : PathManager.getProperty(PathManager.PAGE_GUEST_AUTH);
                request.setAttribute(AttributeName.ACTIVATE_EMAIL_SUCCESS, true);
                logger.info("Email activate for user: " + user);
            } else {
                page = PathManager.getProperty(PathManager.PAGE_GUEST_REG);
                request.setAttribute(AttributeName.ACTIVATE_EMAIL_ERROR, true);
                logger.warn("Email don't activate for user: " + user);
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}