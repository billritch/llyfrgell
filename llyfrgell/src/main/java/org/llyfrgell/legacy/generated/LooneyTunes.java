package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:27 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LooneyTunes generated by hbm2java
 */
@Entity
@Table(name = "looney tunes", catalog = "collections2007")
public class LooneyTunes implements
        java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 327715775464072393L;
    private Integer programNumber;
    private Integer series;
    private String title;
    private Integer disc;
    private Integer off;
    private Integer offset;

    public LooneyTunes()
    {
    }

    public LooneyTunes(Integer series, String title, Integer disc, Integer off,
            Integer offset)
    {
        this.series = series;
        this.title = title;
        this.disc = disc;
        this.off = off;
        this.offset = offset;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ProgramNumber", unique = true, nullable = false)
    public Integer getProgramNumber()
    {
        return this.programNumber;
    }

    public void setProgramNumber(Integer programNumber)
    {
        this.programNumber = programNumber;
    }

    @Column(name = "Series")
    public Integer getSeries()
    {
        return this.series;
    }

    public void setSeries(Integer series)
    {
        this.series = series;
    }

    @Column(name = "Title", length = 60)
    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Column(name = "Disc")
    public Integer getDisc()
    {
        return this.disc;
    }

    public void setDisc(Integer disc)
    {
        this.disc = disc;
    }

    @Column(name = "Off")
    public Integer getOff()
    {
        return this.off;
    }

    public void setOff(Integer off)
    {
        this.off = off;
    }

    @Column(name = "Offset")
    public Integer getOffset()
    {
        return this.offset;
    }

    public void setOffset(Integer offset)
    {
        this.offset = offset;
    }

}
