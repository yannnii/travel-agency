package com.example.turagency.controller.command.impl.page;

import com.example.turagency.controller.AttributeName;
import com.example.turagency.controller.command.Command;
import com.example.turagency.exception.ServiceException;
import com.example.turagency.model.service.UserService;
import com.example.turagency.model.service.impl.GeneralServiceImpl;
import com.example.turagency.model.service.impl.UserServiceImpl;
import com.example.turagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;

public class AdminHomeCommand implements Command {
    private static Logger logger = LogManager.getLogger(AdminHomeCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserService service = UserServiceImpl.getInstance();
        String page;
        try {
            Map<String, Integer> usersByRoles = service.countUsersQuantityByRole();
            int quantityUsers = GeneralServiceImpl.getInstance().sumListValues(new ArrayList<>(usersByRoles.values()));
            session.setAttribute(AttributeName.USERS_BY_ROLES, usersByRoles);
            session.setAttribute(AttributeName.QUANTITY_USERS, quantityUsers);
            page = PathManager.getProperty(PathManager.PAGE_ADMIN_HOME);
            logger.info("Admin home page created.");
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}