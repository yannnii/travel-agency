package com.example.turagency.controller.command.impl;

import com.example.turagency.controller.AttributeName;
import com.example.turagency.controller.command.Command;
import com.example.turagency.exception.ServiceException;
import com.example.turagency.model.entity.Tour;
import com.example.turagency.model.service.OrderService;
import com.example.turagency.model.service.TourService;
import com.example.turagency.model.service.impl.OrderServiceImpl;
import com.example.turagency.model.service.impl.TourServiceImpl;
import com.example.turagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MakeOrderCommand implements Command {
    private static Logger logger = LogManager.getLogger(MakeOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        String idTour = request.getParameter(AttributeName.ID_TOUR);
        String idPassport = request.getParameter(AttributeName.ID_PASSPORT);
        OrderService orderService = OrderServiceImpl.getInstance();
        try {
            if (orderService.createOrder(idTour, idPassport)) {
                TourService tourService = TourServiceImpl.getInstance();
                List<Tour> tours = tourService.findAllTours();
                session.setAttribute(AttributeName.ALL_TOURS, tours);
                request.setAttribute(AttributeName.CREATE_ORDER, true);
                logger.info("Order has been created.");
            } else {
                request.setAttribute(AttributeName.CREATE_ORDER, false);
                logger.warn("Order has not been created.");
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}