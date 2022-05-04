package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static EntityManagerFactory entityManagerFactory;

    static {
        if (entityManagerFactory == null){
            try{
                entityManagerFactory = Persistence.createEntityManagerFactory("test");
            }catch (IllegalStateException e){
                e.printStackTrace();
                HibernateUtil.hibernateClose();
            }
        }
    }

    public static EntityManagerFactory getEntityManagerFactory(){
        return entityManagerFactory;
    }

    public static void hibernateClose(){
        try{
            if(entityManagerFactory instanceof EntityManagerFactory){
                entityManagerFactory.close();
            }
        }catch (IllegalStateException e){
            System.out.println("DB Connection 오류");
        }
    }
}
