package com.example.turagency.model.dao;

import com.example.turagency.exception.DaoException;
import com.example.turagency.model.entity.ClientSheet;
import com.example.turagency.model.entity.User;

/**
 * Interface for {@link ClientSheet} database interactions
 *
 * @version 1.0
 */
public interface SheetDao extends BaseDao<Integer, ClientSheet> {

    /**
     * Search for the concrete sheet for the concrete user in database
     *
     * @param idUser reference to the identifier of the concrete user
     * @return {@code ClientSheet} for this user
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see User
     */
    ClientSheet findSheetByIdUser(int idUser) throws DaoException;

    /**
     * Method provides one transaction for adding sum to the user sheet in database:
     * at first check for the valid of paycard number,
     * then adding the paycard sum to the concrete user sheet,
     * in the end, create {@code SheetOperation} for this sheet
     *
     * @param idUser        reference to the identifier of the concrete user
     * @param numberPaycard reference to the number of the paycard
     * @return {@code true} if adding sum transaction is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see User
     */
    boolean addSheetSum(int idUser, int numberPaycard) throws DaoException;

}