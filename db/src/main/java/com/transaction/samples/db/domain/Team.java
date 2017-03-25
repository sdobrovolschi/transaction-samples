package com.transaction.samples.db.domain;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

/**
 * @author Stanislav Dobrovolschi
 */
@Entity
@Table(name = "TEAM")
public class Team {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @ElementCollection
    @CollectionTable(name = "TEAM_MEMBER", joinColumns = @JoinColumn(name = "TEAM_ID", nullable = false))
    private Set<Member> members;

    @SuppressWarnings("unused")
    private Team() { //for JPA
    }

    public Team(Integer id, String name, Set<Member> members) {
        Assert.notEmpty(members, "Team must have at least one member.");
        this.id = id;
        this.name = name;
        this.members = members;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Member> getMembers() {
        return Collections.unmodifiableSet(members);
    }
}
