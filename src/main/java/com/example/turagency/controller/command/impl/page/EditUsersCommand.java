package com.example.turagency.controller.command.impl.page;

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

public class EditUsersCommand implements Command {
    private static Logger logger = LogManager.getLogger(EditUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String page;
        try {
            String login = (String) session.getAttribute(AttributeName.USER);
            List<User> users = service.findAllUsersWithoutCurrent(login);
            session.setAttribute(AttributeName.USERS, users);
            page = PathManager.getProperty(PathManager.PAGE_ADMIN_EDIT_USERS);
            logger.info("Admin edit users page forward.");
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}