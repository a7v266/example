package com.example.service.persistence;

import com.example.model.Input;
import com.example.model.InputStatus;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public class InputPersistenceImpl extends BasePersistenceImpl<Input, Long> implements InputPersistence {

    protected InputPersistenceImpl() {
        super(Input.class);
    }

    @Override
    public List<Object[]> getStatistics(Set<Long> producerIds) {
        Query query = getNamedQuery("getInputStatistics");
        query.setParameter("producerIds", producerIds);
        query.setParameter("errorStatus", InputStatus.ERROR);
        return query.getResultList();
    }
}
