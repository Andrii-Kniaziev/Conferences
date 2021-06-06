package dao.impl;

import dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public AccountDAO createAccountDao() {
        return new JDBCAccountDAO(getConnection());
    }

    @Override
    public EventDAO createEventDao() {
        return new JDBCEventDAO(getConnection());
    }

    @Override
    public TopicDAO createTopicDao() {
        return new JDBCTopicDAO(getConnection());
    }

    @Override
    public VisitDAO createVisitDao() {
        return new JDBCVisitDAO(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
