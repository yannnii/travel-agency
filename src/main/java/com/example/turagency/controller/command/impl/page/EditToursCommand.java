package com.example.turagency.controller.command.impl.page;

import com.example.turagency.controller.AttributeName;
import com.example.turagency.controller.command.Command;
import com.example.turagency.exception.ServiceException;
import com.example.turagency.model.entity.Tour;
import com.example.turagency.model.service.TourService;
import com.example.turagency.model.service.impl.GeneralServiceImpl;
import com.example.turagency.model.service.impl.TourServiceImpl;
import com.example.turagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

public class EditToursCommand implements Command {
    private static Logger logger = LogManager.getLogger(EditToursCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        TourService tourService = TourServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String page;
        try {
            Set<String> tourTypes = GeneralServiceImpl.getInstance().formTourTypes();
            session.setAttribute(AttributeName.TOUR_TYPES, tourTypes);
            List<Tour> tours = tourService.findAllTours();
            session.setAttribute(AttributeName.ALL_TOURS, tours);
            page = PathManager.getProperty(PathManager.PAGE_MODERATOR_EDIT_TOURS);
            logger.info("Moderator edit tours page reload.");
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}