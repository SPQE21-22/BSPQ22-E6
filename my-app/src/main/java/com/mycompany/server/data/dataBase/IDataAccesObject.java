package com.mycompany.server.data.dataBase;

import java.util.List;

import com.mycompany.server.data.domain.Ticket;

/**
 * The Interface IDataAccesObject.
 *
 * @param <DomainObject> the generic type
 */
public interface IDataAccesObject<DomainObject> {
    
    /**
     * Save.
     *
     * @param o the o
     */
    public void save(DomainObject o);
    
    /**
     * Delete.
     *
     * @param o the o
     */
    public void delete(DomainObject o);
    
    /**
     * Find.
     *
     * @param param the param
     * @return the domain object
     */
    public DomainObject find (String... param);
    
    /**
     * Gets the all.
     *
     * @return the all
     */
    public List<DomainObject> getAll();
}