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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ToursByTypeCommand implements Command {
    private static Logger logger = LogManager.getLogger(ToursByTypeCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        TourService service = TourServiceImpl.getInstance();
        String page;
        try {
            Set<String> countries = service.findAvailableCountries();
            session.setAttribute(AttributeName.COUNTRIES, countries);
            Set<String> tourTypes = GeneralServiceImpl.getInstance().formTourTypes();
            session.setAttribute(AttributeName.TOUR_TYPES, tourTypes);
            Map<String, List<Tour>> tours = new HashMap<>();
            for (String type : tourTypes) {
                List<Tour> currentTypeTours = service.findToursByType(type);
                tours.put(type, currentTypeTours);
            }
            session.setAttribute(AttributeName.TOURS_BY_TYPES, tours);
            page = PathManager.getProperty(PathManager.PAGE_GUEST_TYPE_TOURS);
            logger.info("Tours by types page created.");
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}