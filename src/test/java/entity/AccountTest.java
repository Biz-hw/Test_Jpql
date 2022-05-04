package entity;

import dao.JpaDAO;
import org.junit.Test;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class AccountTest {

    protected JpaDAO jpaDAO = new JpaDAO();
    protected EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();

    @Test
    public void createAccount(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        Account account = new Account();
        account.setAccountCode("1234");
        account.setName("test");
        account.setCreatedAt(new Date());

        /*테스트 진행중_최형우*/

        try{
            transaction.begin();
            entityManager.persist(account);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }

        System.out.println("create Account : " + account.toString());

        assertTrue(account instanceof Account);
    }

    @Test
    public void findAccount(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Integer id = 1;

        Account account = entityManager.find(Account.class, id);

        assertEquals(id, account.getId());
    }

    @Test
    public void updateAccount(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        Integer id = 1;
        String accountCode = "456678";
        String name = "집";

        Account account = new Account();
        account.setId(id);
        account.setAccountCode(accountCode);
        account.setName(name);
        account.setCreatedAt(new Date());

        try{
            transaction.begin();
            entityManager.merge(account);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }

        System.out.println("updateAccount : " + account.toString());

        assertTrue(account instanceof Account);

        assertEquals(account.getId(), id);
        assertEquals(account.getAccountCode(), accountCode);
        assertEquals(account.getName(), name);

    }

    @Test
    public void deleteAccount(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        Integer id = 4;

        Object reference = entityManager.getReference(Account.class, id);

        try{
            transaction.begin();
            entityManager.remove(reference);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }

        assertNotNull(reference);
    }

    @Test
    public void findAllAccount(){
        List<Account> findAllList = jpaDAO.findWithNamedQuery("findAll");

        System.out.println("findAllList : " + findAllList.toString());

        assertTrue(findAllList.size() > 0);
    }

}