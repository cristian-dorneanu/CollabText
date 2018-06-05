package com.freelancer.developer.collabtext.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 10, max = 1024)
    private String value;

    @NotNull
    @Column(name = "validity_period_in_days")
    private Integer validityPeriodInHours = 1;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "valid_from_date")
    private Date validFromDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "valid_until_date")
    private Date validUntilDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    private boolean expired;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getValidityPeriodInHours() {
        return validityPeriodInHours;
    }

    public void setValidityPeriodInHours(Integer validityPeriodInHours) {
        this.validityPeriodInHours = validityPeriodInHours;
    }

    public Date getValidFromDate() {
        return validFromDate;
    }

    public void setValidFromDate(Date validFromDate) {
        this.validFromDate = validFromDate;
    }

    public Date getValidUntilDate() {
        return validUntilDate;
    }

    public void setValidUntilDate(Date validUntilDate) {
        this.validUntilDate = validUntilDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
