package com.example.turagency.controller.command.impl;

import com.example.turagency.controller.AttributeName;
import com.example.turagency.controller.command.Command;
import com.example.turagency.exception.ServiceException;
import com.example.turagency.model.service.PassportService;
import com.example.turagency.model.service.impl.PassportServiceImpl;
import com.example.turagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddPassportCommand implements Command {
    private static Logger logger = LogManager.getLogger(AddPassportCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        int idUser = (int) session.getAttribute(AttributeName.ID_USER);
        String surname = request.getParameter(AttributeName.SURNAME);
        String name = request.getParameter(AttributeName.NAME);
        String birthDate = request.getParameter(AttributeName.BIRTH_DATE);
        String passportNo = request.getParameter(AttributeName.PASSPORT_NO);
        String imageName = (String) request.getAttribute(AttributeName.IMAGE_NAME);
        PassportService service = PassportServiceImpl.getInstance();
        try {
            if (service.createPassport(idUser, surname, name, birthDate, passportNo, imageName)) {
                request.setAttribute(AttributeName.CREATE_PASSPORT, true);
                logger.info("Passport has been created.");
            } else {
                request.setAttribute(AttributeName.CREATE_PASSPORT, false);
                logger.warn("Passport has not been created.");
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}