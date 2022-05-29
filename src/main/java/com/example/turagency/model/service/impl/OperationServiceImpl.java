package com.example.turagency.model.service.impl;

import com.example.turagency.exception.DaoException;
import com.example.turagency.exception.ServiceException;
import com.example.turagency.model.dao.OperationDao;
import com.example.turagency.model.dao.impl.OperationDaoImpl;
import com.example.turagency.model.entity.SheetOperation;
import com.example.turagency.model.service.OperationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OperationServiceImpl implements OperationService {
    private static final OperationServiceImpl INSTANCE = new OperationServiceImpl();
    private static Logger logger = LogManager.getLogger(OperationServiceImpl.class);

    private OperationServiceImpl() {
    }

    public static OperationServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<SheetOperation> findOperationsByIdSheet(int idSheet) throws ServiceException {
        List<SheetOperation> operations;
        OperationDao dao = OperationDaoImpl.getInstance();
        try {
            operations = dao.findOperationsByIdSheet(idSheet);
            logger.info("Find operations for sheet " + idSheet);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return operations;
    }

}