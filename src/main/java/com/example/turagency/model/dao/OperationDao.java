package com.example.turagency.model.dao;

import com.example.turagency.exception.DaoException;
import com.example.turagency.model.entity.SheetOperation;

import java.util.List;

/**
 * Interface for {@link SheetOperation} database interactions
 *
 * @version 1.0
 */
public interface OperationDao extends BaseDao<Integer, SheetOperation> {

    /**
     * Select the operations for the concrete sheet from database
     *
     * @param idSheet reference to the identifier of the concrete sheet
     * @return {@link List} of operations
     * @throws DaoException when {@link java.sql.SQLException} has happened
     */
    List<SheetOperation> findOperationsByIdSheet(int idSheet) throws DaoException;

}