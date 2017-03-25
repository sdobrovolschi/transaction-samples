package com.transaction.samples.db.infrastructure.persistence.repository;

import com.transaction.samples.db.domain.Member;
import com.transaction.samples.db.domain.Team;
import com.transaction.samples.db.domain.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Stanislav Dobrovolschi
 */
@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JdbcTeamRepository.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class JdbcTeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void addShouldInsertTeamAndMembers() {
        Team team = new Team(1, "FireFighters", Stream.of(new Member("jsnow")).collect(Collectors.toSet()));

        teamRepository.add(team);

        int teamRows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "team");
        assertThat(teamRows).isEqualTo(1);

        int teamMemberRows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "team_member");
        assertThat(teamMemberRows).isEqualTo(1);
    }
}