package com.example.turagency.controller.command.impl;

import com.example.turagency.controller.AttributeName;
import com.example.turagency.controller.command.Command;
import com.example.turagency.exception.ServiceException;
import com.example.turagency.model.entity.ClientOrder;
import com.example.turagency.model.entity.TravelDocs;
import com.example.turagency.model.service.OrderService;
import com.example.turagency.model.service.TravelDocsService;
import com.example.turagency.model.service.impl.OrderServiceImpl;
import com.example.turagency.model.service.impl.TravelDocsServiceImpl;
import com.example.turagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SeeTravelDocsCommand implements Command {
    private static Logger logger = LogManager.getLogger(SeeTravelDocsCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String idDocs = request.getParameter(AttributeName.ID_DOCS);
        String idOrder = request.getParameter(AttributeName.ID_ORDER);
        String page;
        TravelDocsService docsService = TravelDocsServiceImpl.getInstance();
        OrderService orderService = OrderServiceImpl.getInstance();
        try {
            ClientOrder order = orderService.findConcreteOrderWithValues(idOrder);
            session.setAttribute(AttributeName.ORDER, order);
            TravelDocs docs = docsService.findTravelDocsById(idDocs);
            session.setAttribute(AttributeName.TOUR_DOCS, docs);
            page = PathManager.getProperty(PathManager.PAGE_USER_TOUR_DOCS);
            logger.info("See Docs page for order " + idOrder);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}