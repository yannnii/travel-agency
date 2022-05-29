package com.example.turagency.model.service;

import com.example.turagency.exception.DaoException;
import com.example.turagency.exception.ServiceException;
import com.example.turagency.model.entity.SheetOperation;

import java.util.List;

/**
 * Interface for {@link SheetOperation} service actions
 *
 * @version 1.0
 */
public interface OperationService {

    /**
     * Select the operations for the concrete sheet
     *
     * @param idSheet reference to the identifier of the concrete sheet
     * @return {@link List} of operations
     * @throws ServiceException when {@link DaoException} has happened
     */
    List<SheetOperation> findOperationsByIdSheet(int idSheet) throws ServiceException;

}