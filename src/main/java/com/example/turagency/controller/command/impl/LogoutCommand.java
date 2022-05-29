package com.example.turagency.controller.command.impl;

import com.example.turagency.controller.command.Command;
import com.example.turagency.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    private static Logger logger = LogManager.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String page = PathManager.getProperty(PathManager.PAGE_INDEX);
        request.getSession().invalidate();
        logger.info("User log out.");
        return page;
    }

}