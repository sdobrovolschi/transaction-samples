package com.transaction.samples.db.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * @author Stanislav Dobrovolschi
 */
@Embeddable
public class Member {

    @Column(name = "USERNAME")
    private String username;

    @SuppressWarnings("unused")
    private Member() { // for JPA
    }

    public Member(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Member member = (Member) o;
        return Objects.equals(username, member.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}
