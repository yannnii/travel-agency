package com.example.turagency.controller.command.impl;

import com.example.turagency.controller.AttributeName;
import com.example.turagency.controller.command.Command;
import com.example.turagency.exception.ServiceException;
import com.example.turagency.model.entity.ClientSheet;
import com.example.turagency.model.entity.UserType;
import com.example.turagency.model.service.SheetService;
import com.example.turagency.model.service.UserService;
import com.example.turagency.model.service.impl.SheetServiceImpl;
import com.example.turagency.model.service.impl.UserServiceImpl;
import com.example.turagency.util.PathManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements Command {
    private static Logger logger = LogManager.getLogger(RegisterCommand.class);
    private static final String DEFAULT_ROLE = UserType.USER.toString().toLowerCase();

    @Override
    public String execute(HttpServletRequest request) {
        UserService service = UserServiceImpl.getInstance();
        String page;
        String username = request.getParameter(AttributeName.USER);
        String password = request.getParameter(AttributeName.PASSWORD);
        String email = request.getParameter(AttributeName.EMAIL);
        String role = request.getParameter(AttributeName.ROLE);
        role = (role != null) ? role : DEFAULT_ROLE;
        try {
            if (service.createNewUser(username, password, email, role)) {
                if (role.equals(DEFAULT_ROLE)) {
                    HttpSession session = request.getSession();
                    session.setAttribute(AttributeName.USER, username);
                    session.setAttribute(AttributeName.ROLE, role);
                    int idUser = service.findIdUserByLogin(username);
                    session.setAttribute(AttributeName.ID_USER, idUser);
                    SheetService sheetService = SheetServiceImpl.getInstance();
                    ClientSheet sheet = sheetService.findSheetByIdUser(idUser);
                    session.setAttribute(AttributeName.SHEET, sheet);
                    page = PathManager.getProperty(PathManager.PAGE_USER_HOME);
                    logger.info("Register New username + " + username);
                } else {
                    page = (String) request.getSession().getAttribute(AttributeName.CURRENT_PAGE);
                }
//                String mailSubject = MailTextCreator.createMailSubject();
//                String mailText = MailTextCreator.createMailText(username, password);
//                SendMailManager sender = new SendMailManager(email, mailSubject, mailText);
//                sender.send();
                request.setAttribute(AttributeName.REGISTER_SUCCESS, true);
                logger.info(" Mail send to " + email);
            } else {
                request.setAttribute(AttributeName.REGISTER_ERROR, true);
                page = (role.equals(DEFAULT_ROLE))
                        ? PathManager.getProperty(PathManager.PAGE_GUEST_REG)
                        : (String) request.getSession().getAttribute(AttributeName.CURRENT_PAGE);
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}