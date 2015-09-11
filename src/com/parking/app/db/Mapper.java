package com.parking.app.db;

import java.util.Collection;

public interface Mapper {
    public void insert (Object o);
    public void update (Object o);
    public void delete (Object d);
    public Collection<Object> select (Object o);
}
