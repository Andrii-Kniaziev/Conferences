package dao;

import dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract AccountDAO createAccountDao();
    public abstract EventDAO createEventDao();
    public abstract TopicDAO createTopicDao();
    public abstract VisitDAO createVisitDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }

}
