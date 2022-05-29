package com.example.turagency.controller.command;

import com.example.turagency.controller.ActionProvider;

import javax.servlet.http.HttpServletRequest;

/**
 * Base interface for Command pattern
 *
 * @version 1.0
 */
public interface Command {

    /**
     * Define target page to go
     *
     * @param request is request from user
     * @return target page
     * @see HttpServletRequest
     * @see ActionProvider
     */
    String execute(HttpServletRequest request);

}