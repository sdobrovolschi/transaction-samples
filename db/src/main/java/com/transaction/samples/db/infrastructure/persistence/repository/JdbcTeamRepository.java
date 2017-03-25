package com.transaction.samples.db.infrastructure.persistence.repository;

import com.transaction.samples.db.domain.Team;
import com.transaction.samples.db.domain.TeamRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Stanislav Dobrovolschi
 */
@Repository("jdbcTeamRepository")
public class JdbcTeamRepository implements TeamRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTeamRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Team team) {
        String teamSqlQuery = "insert into team(id, name) values (?, ?)";
        String teamMemberSqlQuery = "insert into team_member(team_id, username) values (?, ?)";

        jdbcTemplate.update(teamSqlQuery, team.getId(), team.getName());
        team.getMembers()
                .forEach(m -> jdbcTemplate.update(teamMemberSqlQuery, team.getId(), m.getUsername()));
    }
}
