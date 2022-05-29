package com.example.turagency.controller.command.impl.page;

import com.example.turagency.controller.AttributeName;
import com.example.turagency.controller.command.Command;
import com.example.turagency.exception.ServiceException;
import com.example.turagency.model.entity.ClientSheet;
import com.example.turagency.model.entity.SheetOperation;
import com.example.turagency.model.service.OperationService;
import com.example.turagency.model.service.impl.OperationServiceImpl;
import com.example.turagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SeeOperationsCommand implements Command {
    private static Logger logger = LogManager.getLogger(SeeOperationsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ClientSheet sheet = (ClientSheet) session.getAttribute(AttributeName.SHEET);
        int idSheet = sheet.getId();
        OperationService service = OperationServiceImpl.getInstance();
        String page;
        try {
            List<SheetOperation> operations = service.findOperationsByIdSheet(idSheet);
            session.setAttribute(AttributeName.OPERATIONS, operations);
            page = PathManager.getProperty(PathManager.PAGE_USER_SEE_OPERATIONS);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}