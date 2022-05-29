package com.example.turagency.controller.command.impl;

import com.example.turagency.controller.AttributeName;
import com.example.turagency.controller.command.Command;
import com.example.turagency.exception.ServiceException;
import com.example.turagency.model.entity.ClientOrder;
import com.example.turagency.model.service.OrderService;
import com.example.turagency.model.service.impl.OrderServiceImpl;
import com.example.turagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CancelOrderCommand implements Command {
    private static Logger logger = LogManager.getLogger(CancelOrderCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int idUser = (int) session.getAttribute(AttributeName.ID_USER);
        String page = (String) session.getAttribute(AttributeName.CURRENT_PAGE);
        String idOrder = request.getParameter(AttributeName.ID_ORDER);
        try {
            OrderService orderService = OrderServiceImpl.getInstance();
            if (orderService.deleteOrder(idOrder)) {
                List<ClientOrder> orders = orderService.findAllOrdersWithValues(idUser);
                session.setAttribute(AttributeName.ORDERS, orders);
                request.setAttribute(AttributeName.CANCEL_ORDER, true);
            } else {
                request.setAttribute(AttributeName.CANCEL_ORDER, false);
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}