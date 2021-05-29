package dao;

public interface SuperDAO extends AutoCloseable{

    void close();

    default void close(AutoCloseable ac) {
        if(ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
