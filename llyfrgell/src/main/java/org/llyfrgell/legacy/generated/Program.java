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
 * Program generated by hbm2java
 */
@Entity
@Table(name = "program", catalog = "collections2007")
public class Program implements
        java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = -1232493696104066764L;
    private Integer programNumber;
    private String title;
    private Integer series;
    private Short serialNumber;
    private Short season;
    private Short seasonOffset;
    private Byte segment;
    private Byte part;
    private Byte parts;
    private Short year;
    private Date releaseDate;
    private String productionCode;
    private Integer category;
    private Integer genre;
    private Integer language;
    private String writer;
    private String director;
    private String imdb;
    private String description;
    private boolean deleteMe;

    public Program()
    {
    }

    public Program(boolean deleteMe)
    {
        this.deleteMe = deleteMe;
    }

    public Program(String title, Integer series, Short serialNumber,
            Short season, Short seasonOffset, Byte segment, Byte part,
            Byte parts, Short year, Date releaseDate, String productionCode,
            Integer category, Integer genre, Integer language, String writer,
            String director, String imdb, String description, boolean deleteMe)
    {
        this.title = title;
        this.series = series;
        this.serialNumber = serialNumber;
        this.season = season;
        this.seasonOffset = seasonOffset;
        this.segment = segment;
        this.part = part;
        this.parts = parts;
        this.year = year;
        this.releaseDate = releaseDate;
        this.productionCode = productionCode;
        this.category = category;
        this.genre = genre;
        this.language = language;
        this.writer = writer;
        this.director = director;
        this.imdb = imdb;
        this.description = description;
        this.deleteMe = deleteMe;
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

    @Column(name = "Title", length = 60)
    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
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

    @Column(name = "`Serial Number`")
    public Short getSerialNumber()
    {
        return this.serialNumber;
    }

    public void setSerialNumber(Short serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    @Column(name = "Season")
    public Short getSeason()
    {
        return this.season;
    }

    public void setSeason(Short season)
    {
        this.season = season;
    }

    @Column(name = "SeasonOffset")
    public Short getSeasonOffset()
    {
        return this.seasonOffset;
    }

    public void setSeasonOffset(Short seasonOffset)
    {
        this.seasonOffset = seasonOffset;
    }

    @Column(name = "Segment")
    public Byte getSegment()
    {
        return this.segment;
    }

    public void setSegment(Byte segment)
    {
        this.segment = segment;
    }

    @Column(name = "Part")
    public Byte getPart()
    {
        return this.part;
    }

    public void setPart(Byte part)
    {
        this.part = part;
    }

    @Column(name = "Parts")
    public Byte getParts()
    {
        return this.parts;
    }

    public void setParts(Byte parts)
    {
        this.parts = parts;
    }

    @Column(name = "Year")
    public Short getYear()
    {
        return this.year;
    }

    public void setYear(Short year)
    {
        this.year = year;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ReleaseDate", length = 19)
    public Date getReleaseDate()
    {
        return this.releaseDate;
    }

    public void setReleaseDate(Date releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    @Column(name = "ProductionCode", length = 15)
    public String getProductionCode()
    {
        return this.productionCode;
    }

    public void setProductionCode(String productionCode)
    {
        this.productionCode = productionCode;
    }

    @Column(name = "Category")
    public Integer getCategory()
    {
        return this.category;
    }

    public void setCategory(Integer category)
    {
        this.category = category;
    }

    @Column(name = "Genre")
    public Integer getGenre()
    {
        return this.genre;
    }

    public void setGenre(Integer genre)
    {
        this.genre = genre;
    }

    @Column(name = "Language")
    public Integer getLanguage()
    {
        return this.language;
    }

    public void setLanguage(Integer language)
    {
        this.language = language;
    }

    @Column(name = "Writer", length = 100)
    public String getWriter()
    {
        return this.writer;
    }

    public void setWriter(String writer)
    {
        this.writer = writer;
    }

    @Column(name = "Director", length = 100)
    public String getDirector()
    {
        return this.director;
    }

    public void setDirector(String director)
    {
        this.director = director;
    }

    @Column(name = "IMDB")
    public String getImdb()
    {
        return this.imdb;
    }

    public void setImdb(String imdb)
    {
        this.imdb = imdb;
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

    @Column(name = "DeleteMe", nullable = false)
    public boolean isDeleteMe()
    {
        return this.deleteMe;
    }

    public void setDeleteMe(boolean deleteMe)
    {
        this.deleteMe = deleteMe;
    }

}
