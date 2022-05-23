package com.mycompany.server.data.dataBase;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

/** The Class DataAccesObjectBase.*/
public class DataAccesObjectBase {
    
    /** The pmf. */
    protected static PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

    /**
     * Delete object.
     *
     * @param object the object
     */
    public void deleteObject(Object object) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            pm.deletePersistent(object);
            tx.commit();
        } catch (Exception ex) {
            System.out.println(" $ Error deleting an object: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
    }

    /**
     * Save object.
     *
     * @param object the object
     */
    public void saveObject(Object object) {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();

        try {
            tx.begin();
            pm.makePersistent(object);
            tx.commit();
        } catch (Exception ex) {
            System.out.println(" $ Error storing an object: " + ex.getMessage());
        } finally {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            pm.close();
        }
    }
}
