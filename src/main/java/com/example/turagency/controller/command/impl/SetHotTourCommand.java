package com.example.turagency.controller.command.impl;

import com.example.turagency.controller.AttributeName;
import com.example.turagency.controller.command.Command;
import com.example.turagency.exception.ServiceException;
import com.example.turagency.model.entity.Tour;
import com.example.turagency.model.service.TourService;
import com.example.turagency.model.service.impl.TourServiceImpl;
import com.example.turagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SetHotTourCommand implements Command {
    private static Logger logger = LogManager.getLogger(SetHotTourCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        TourService service = TourServiceImpl.getInstance();
        String idTour = request.getParameter(AttributeName.ID_TOUR);
        String discount = request.getParameter(AttributeName.DISCOUNT);
        try {
            if (service.setHotTour(idTour, discount)) {
                request.setAttribute(AttributeName.HOT_TOUR_GOOD, true);
                List<Tour> tours = service.findAllTours();
                session.setAttribute(AttributeName.ALL_TOURS, tours);
                logger.info("Tour has been discounted.");
            } else {
                request.setAttribute(AttributeName.HOT_TOUR_GOOD, false);
                logger.warn("Tour has not been discounted.");
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}