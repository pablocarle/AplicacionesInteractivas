package com.parking.app.db;

import java.util.Collection;

public interface Mapper {
    public int insert (Object o) throws Exception;
    public void update (Object o) throws Exception;
    public void delete (Object d) throws Exception;
    public Collection<? extends Object> select (Object o) throws Exception;
    public Collection<? extends Object> selectAll() throws Exception;
}
