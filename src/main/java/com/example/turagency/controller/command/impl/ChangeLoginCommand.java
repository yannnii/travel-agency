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

public class ChangeLoginCommand implements Command {
    private static Logger logger = LogManager.getLogger(ChangeLoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        String currentLogin = (String) session.getAttribute(AttributeName.USER);
        String newLogin = request.getParameter(AttributeName.NEW_USERNAME);
        try {
            if (service.changeUsername(currentLogin, newLogin)) {
                session.setAttribute(AttributeName.USER, newLogin);
                request.setAttribute(AttributeName.CHANGE_LOGIN, true);
                logger.info("Change login from " + currentLogin + " to " + newLogin);
            } else {
                request.setAttribute(AttributeName.CHANGE_LOGIN, false);
                logger.warn("Changing login is failed for " + currentLogin);
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}