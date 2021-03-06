package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:27 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PerformanceA generated by hbm2java
 */
@Entity
@Table(name = "performance-a", catalog = "collections2007")
public class PerformanceA implements
        java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = -8968919984168963270L;
    private Integer performanceId;
    private Integer programNumber;
    private Date recordingDate;
    private Double length;
    private String codes;
    private String description;

    public PerformanceA()
    {
    }

    public PerformanceA(Integer programNumber, Date recordingDate,
            Double length, String codes, String description)
    {
        this.programNumber = programNumber;
        this.recordingDate = recordingDate;
        this.length = length;
        this.codes = codes;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "PerformanceID", unique = true, nullable = false)
    public Integer getPerformanceId()
    {
        return this.performanceId;
    }

    public void setPerformanceId(Integer performanceId)
    {
        this.performanceId = performanceId;
    }

    @Column(name = "ProgramNumber")
    public Integer getProgramNumber()
    {
        return this.programNumber;
    }

    public void setProgramNumber(Integer programNumber)
    {
        this.programNumber = programNumber;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "RecordingDate", length = 19)
    public Date getRecordingDate()
    {
        return this.recordingDate;
    }

    public void setRecordingDate(Date recordingDate)
    {
        this.recordingDate = recordingDate;
    }

    @Column(name = "Length", precision = 7)
    public Double getLength()
    {
        return this.length;
    }

    public void setLength(Double length)
    {
        this.length = length;
    }

    @Column(name = "Codes", length = 5)
    public String getCodes()
    {
        return this.codes;
    }

    public void setCodes(String codes)
    {
        this.codes = codes;
    }

    @Column(name = "Description")
    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

}
