package com.example.turagency.model.dao;

import com.example.turagency.exception.DaoException;
import com.example.turagency.model.entity.ClientPassport;
import com.example.turagency.model.entity.User;

import java.util.List;

/**
 * Interface for {@link ClientPassport} database interactions
 *
 * @version 1.0
 */
public interface PassportDao extends BaseDao<Integer, ClientPassport> {

    /**
     * Search for all passports for the concrete user in database
     *
     * @param idUser reference to the identifier of the concrete user
     * @return {@link List} of passports
     * @throws DaoException DaoException when {@link java.sql.SQLException} has happened
     * @see User
     */
    List<ClientPassport> findPassportsByIdUser(int idUser) throws DaoException;

    /**
     * Create the passport for the concrete user in database
     *
     * @param idUser   reference to the identifier of the concrete user
     * @param passport {@code ClientPassport} for insertion into database
     * @return {@code true} if passport creation is successful; {@code false} otherwise
     * @throws DaoException when {@link java.sql.SQLException} has happened
     * @see User
     */
    boolean createPassport(int idUser, ClientPassport passport) throws DaoException;

}