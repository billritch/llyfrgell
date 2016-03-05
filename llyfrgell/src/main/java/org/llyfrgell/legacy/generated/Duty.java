package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:27 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.llyfrgell.legacy.EnumList;

/**
 * Duty generated by hbm2java
 */
@Entity
@Table(name = "duty", catalog = "collections2007")
public class Duty
    extends EnumList
{

    /**
     *
     */
    private static final long serialVersionUID = -850926506761313123L;

    public Duty()
    {
    }

    public Duty(String duty)
    {
        setText(duty);
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "DutyNr", unique = true, nullable = false)
    public Integer getDutyNr()
    {
        return getId();
    }

    public void setDutyNr(Integer dutyNr)
    {
        setId(dutyNr);
    }

    @Column(name = "Duty", length = 13)
    public String getDuty()
    {
        return getText();
    }

    public void setDuty(String duty)
    {
        setText(duty);
    }

}