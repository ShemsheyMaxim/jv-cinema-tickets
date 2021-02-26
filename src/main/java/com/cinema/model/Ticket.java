package com.cinema.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "performance_session_id")
    private PerformanceSession performanceSession;
    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PerformanceSession getPerformanceSession() {
        return performanceSession;
    }

    public void setPerformanceSession(PerformanceSession performanceSession) {
        this.performanceSession = performanceSession;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id)
                && Objects.equals(performanceSession, ticket.performanceSession)
                && Objects.equals(user, ticket.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, performanceSession, user);
    }

    @Override
    public String toString() {
        return "Ticket{"
                + "id=" + id
                + ", performanceSession=" + performanceSession
                + ", user=" + user
                + '}';
    }
}
