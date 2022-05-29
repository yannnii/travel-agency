package com.example.turagency.controller.command.impl.page;

import com.example.turagency.controller.AttributeName;
import com.example.turagency.controller.command.Command;
import com.example.turagency.exception.ServiceException;
import com.example.turagency.model.entity.ClientPassport;
import com.example.turagency.model.entity.Tour;
import com.example.turagency.model.service.PassportService;
import com.example.turagency.model.service.TourService;
import com.example.turagency.model.service.impl.PassportServiceImpl;
import com.example.turagency.model.service.impl.TourServiceImpl;
import com.example.turagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

public class MakeOrderPageCommand implements Command {
    private static Logger logger = LogManager.getLogger(MakeOrderPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        TourService tourService = TourServiceImpl.getInstance();
        PassportService passportService = PassportServiceImpl.getInstance();
        String idConcreteTour = request.getParameter(AttributeName.ID_TOUR);
        boolean isConcreteTour = idConcreteTour != null;
        int idUser = (int) session.getAttribute(AttributeName.ID_USER);
        String page;
        try {
            Set<String> countries = tourService.findAvailableCountries();
            session.setAttribute(AttributeName.COUNTRIES, countries);
            if (isConcreteTour) {
                Tour tour = tourService.findTourById(idConcreteTour);
                request.setAttribute(AttributeName.CONCRETE_TOUR, tour);
            } else {
                List<Tour> tours = tourService.findAllTours();
                session.setAttribute(AttributeName.ALL_TOURS, tours);
            }
            List<ClientPassport> passports = passportService.findPassportsByIdUser(idUser);
            session.setAttribute(AttributeName.PASSPORTS, passports);
            page = PathManager.getProperty(PathManager.PAGE_USER_MAKE_ORDER);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}