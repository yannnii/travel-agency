package com.example.turagency.controller.command.impl.page;

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
import java.util.Map;

public class EditOrdersPageCommand implements Command {
    private static Logger logger = LogManager.getLogger(EditOrdersPageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        OrderService service = OrderServiceImpl.getInstance();
        Map<ClientOrder, String> ordersAndUsers;
        String page;
        try {
            ordersAndUsers = service.findOrdersAndUsersToEditOrders();
            request.getSession().setAttribute(AttributeName.ORDERS_AND_USERS, ordersAndUsers);
            page = PathManager.getProperty(PathManager.PAGE_MODERATOR_EDIT_ORDERS);
            logger.info("Edit orders page created.");
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(AttributeName.ERROR_INFO, e);
            page = PathManager.getProperty(PathManager.PAGE_ERROR);
        }
        return page;
    }

}