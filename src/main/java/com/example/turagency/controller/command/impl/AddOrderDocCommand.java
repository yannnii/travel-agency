package com.example.turagency.controller.command.impl;

import com.example.turagency.controller.AttributeName;
import com.example.turagency.controller.command.Command;
import com.example.turagency.exception.ServiceException;
import com.example.turagency.model.entity.ClientOrder;
import com.example.turagency.model.service.OrderService;
import com.example.turagency.model.service.TravelDocsService;
import com.example.turagency.model.service.impl.OrderServiceImpl;
import com.example.turagency.model.service.impl.TravelDocsServiceImpl;
import com.example.turagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class AddOrderDocCommand implements Command {
    private static Logger logger = LogManager.getLogger(AddOrderDocCommand.class);
    private static final String VOUCHER_TYPE = "voucher";
    private static final String INSURANCE_TYPE = "insurance";
    private static final String TICKET_TYPE = "ticket";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        String idDocs = request.getParameter(AttributeName.ID_DOCS);
        String doctype = request.getParameter(AttributeName.DOC_TYPE);
        String imageName = (String) request.getAttribute(AttributeName.IMAGE_NAME);
        TravelDocsService docsService = TravelDocsServiceImpl.getInstance();
        try {
            boolean result = switch (doctype) {
                case VOUCHER_TYPE -> docsService.addVoucher(idDocs, imageName);
                case INSURANCE_TYPE -> docsService.addInsurance(idDocs, imageName);
                case TICKET_TYPE -> docsService.addTicket(idDocs, imageName);
                default -> false;
            };
            if (result) {
                request.setAttribute(AttributeName.ADD_DOC, true);
                Map<ClientOrder, String> ordersAndUsers;
                OrderService orderService = OrderServiceImpl.getInstance();
                ordersAndUsers = orderService.findOrdersAndUsersToAddDocs();
                session.setAttribute(AttributeName.ORDERS_AND_USERS, ordersAndUsers);
                logger.info("Add doc: " + doctype + " to docs " + idDocs);
            } else {
                request.setAttribute(AttributeName.ADD_DOC, false);
                logger.warn("Doc has not been added.");
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}