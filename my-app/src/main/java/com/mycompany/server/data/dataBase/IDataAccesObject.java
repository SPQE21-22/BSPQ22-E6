package com.mycompany.server.data.dataBase;

import java.util.List;

public interface IDataAccesObject<DomainObject> {
    public void save(DomainObject o);
    public void delete(DomainObject o);
    public DomainObject find (String param);
    public List<DomainObject> getAll();
}