package com.transaction.samples.db.infrastructure.persistence.repository;

import com.transaction.samples.db.domain.Team;
import com.transaction.samples.db.domain.TeamRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * @author Stanislav Dobrovolschi
 */
@Repository("jpaTeamRepository")
public class JpaTeamRepository implements TeamRepository {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Override
    public void add(Team team) {
        entityManager.persist(team);
    }
}
