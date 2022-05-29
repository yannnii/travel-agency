package com.example.turagency.controller.command.impl.page;

import com.example.turagency.controller.AttributeName;
import com.example.turagency.controller.command.Command;
import com.example.turagency.exception.ServiceException;
import com.example.turagency.model.entity.ClientPassport;
import com.example.turagency.model.service.PassportService;
import com.example.turagency.model.service.impl.PassportServiceImpl;
import com.example.turagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AllPassportsCommand implements Command {
    private static Logger logger = LogManager.getLogger(AllPassportsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int idUser = (int) session.getAttribute(AttributeName.ID_USER);
        PassportService service = PassportServiceImpl.getInstance();
        String page;
        try {
            List<ClientPassport> passports = service.findPassportsByIdUser(idUser);
            session.setAttribute(AttributeName.PASSPORTS, passports);
            page = PathManager.getProperty(PathManager.PAGE_USER_PASSPORTS);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}