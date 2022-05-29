package com.example.turagency.model.service;

import com.example.turagency.exception.DaoException;
import com.example.turagency.exception.ServiceException;
import com.example.turagency.model.entity.ClientSheet;
import com.example.turagency.model.entity.User;

/**
 * Interface for {@link ClientSheet} service actions
 *
 * @version 1.0
 */
public interface SheetService {

    /**
     * Search for the concrete sheet for the concrete user
     *
     * @param idUser reference to the identifier of the concrete user
     * @return {@code ClientSheet} for this user
     * @throws ServiceException when {@link DaoException} has happened
     * @see User
     */
    ClientSheet findSheetByIdUser(int idUser) throws ServiceException;

    /**
     * Result of adding sum to the user sheet
     *
     * @param idUser        reference to the identifier of the concrete user
     * @param numberPaycard reference to the number of the paycard
     * @return {@code true} if parameters is correct and adding sum is successful; {@code false} otherwise
     * @throws ServiceException when {@link DaoException} has happened
     * @see User
     */
    boolean addSheetSum(int idUser, String numberPaycard) throws ServiceException;

}