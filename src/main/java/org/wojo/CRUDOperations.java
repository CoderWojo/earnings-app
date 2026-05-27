package org.wojo;

import org.wojo.entity.Earning;

import java.util.List;

public interface CRUDOperations {

    List<Earning> findAll();

    int addEarning(Earning earning);
}
