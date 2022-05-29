package com.example.turagency.controller.command.impl.page;

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
import java.util.Set;

public class ToursHotCommand implements Command {
    private static Logger logger = LogManager.getLogger(ToursHotCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        TourService service = TourServiceImpl.getInstance();
        String page;
        try {
            List<Tour> hotTours = service.findAllHotTours();
            if (hotTours.size() > 0) {
                session.setAttribute(AttributeName.HOT_TOURS, hotTours);
            } else {
                request.setAttribute(AttributeName.HOT_TOURS_NOTHING, true);
            }
            Set<String> countries = service.findAvailableCountries();
            session.setAttribute(AttributeName.COUNTRIES, countries);
            page = PathManager.getProperty(PathManager.PAGE_GUEST_HOT_TOURS);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}