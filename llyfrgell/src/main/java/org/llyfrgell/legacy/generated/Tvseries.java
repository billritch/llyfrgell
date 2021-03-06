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
 * Tvseries generated by hbm2java
 */
@Entity
@Table(name = "tvseries", catalog = "collections2007")
public class Tvseries implements
        java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 41846612596183803L;
    private Integer id;
    private Integer series;
    private String serialCode;
    private Integer serialNumber;
    private String seasonEp;
    private Date releaseDate;
    private String title;
    private String productionCode;
    private String recorded;
    private boolean prodCaptured;
    private boolean prodEdited;
    private boolean prodRendered;
    private boolean prodBurned;
    private boolean prodVerified;
    private boolean prodLabel;
    private Integer season;
    private Integer seasonOffest;
    private Integer programNumber;
    private Byte segment;
    private Byte part;
    private Byte parts;
    private Short year;
    private Integer category;
    private Integer genre;
    private Integer language;

    public Tvseries()
    {
    }

    public Tvseries(boolean prodCaptured, boolean prodEdited,
            boolean prodRendered, boolean prodBurned, boolean prodVerified,
            boolean prodLabel)
    {
        this.prodCaptured = prodCaptured;
        this.prodEdited = prodEdited;
        this.prodRendered = prodRendered;
        this.prodBurned = prodBurned;
        this.prodVerified = prodVerified;
        this.prodLabel = prodLabel;
    }

    public Tvseries(Integer series, String serialCode, Integer serialNumber,
            String seasonEp, Date releaseDate, String title,
            String productionCode, String recorded, boolean prodCaptured,
            boolean prodEdited, boolean prodRendered, boolean prodBurned,
            boolean prodVerified, boolean prodLabel, Integer season,
            Integer seasonOffest, Integer programNumber, Byte segment,
            Byte part, Byte parts, Short year, Integer category, Integer genre,
            Integer language)
    {
        this.series = series;
        this.serialCode = serialCode;
        this.serialNumber = serialNumber;
        this.seasonEp = seasonEp;
        this.releaseDate = releaseDate;
        this.title = title;
        this.productionCode = productionCode;
        this.recorded = recorded;
        this.prodCaptured = prodCaptured;
        this.prodEdited = prodEdited;
        this.prodRendered = prodRendered;
        this.prodBurned = prodBurned;
        this.prodVerified = prodVerified;
        this.prodLabel = prodLabel;
        this.season = season;
        this.seasonOffest = seasonOffest;
        this.programNumber = programNumber;
        this.segment = segment;
        this.part = part;
        this.parts = parts;
        this.year = year;
        this.category = category;
        this.genre = genre;
        this.language = language;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
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

    @Column(name = "SerialCode")
    public String getSerialCode()
    {
        return this.serialCode;
    }

    public void setSerialCode(String serialCode)
    {
        this.serialCode = serialCode;
    }

    @Column(name = "`Serial Number`")
    public Integer getSerialNumber()
    {
        return this.serialNumber;
    }

    public void setSerialNumber(Integer serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    @Column(name = "SeasonEp")
    public String getSeasonEp()
    {
        return this.seasonEp;
    }

    public void setSeasonEp(String seasonEp)
    {
        this.seasonEp = seasonEp;
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

    @Column(name = "Title")
    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Column(name = "ProductionCode")
    public String getProductionCode()
    {
        return this.productionCode;
    }

    public void setProductionCode(String productionCode)
    {
        this.productionCode = productionCode;
    }

    @Column(name = "Recorded", length = 50)
    public String getRecorded()
    {
        return this.recorded;
    }

    public void setRecorded(String recorded)
    {
        this.recorded = recorded;
    }

    @Column(name = "ProdCaptured", nullable = false)
    public boolean isProdCaptured()
    {
        return this.prodCaptured;
    }

    public void setProdCaptured(boolean prodCaptured)
    {
        this.prodCaptured = prodCaptured;
    }

    @Column(name = "ProdEdited", nullable = false)
    public boolean isProdEdited()
    {
        return this.prodEdited;
    }

    public void setProdEdited(boolean prodEdited)
    {
        this.prodEdited = prodEdited;
    }

    @Column(name = "ProdRendered", nullable = false)
    public boolean isProdRendered()
    {
        return this.prodRendered;
    }

    public void setProdRendered(boolean prodRendered)
    {
        this.prodRendered = prodRendered;
    }

    @Column(name = "ProdBurned", nullable = false)
    public boolean isProdBurned()
    {
        return this.prodBurned;
    }

    public void setProdBurned(boolean prodBurned)
    {
        this.prodBurned = prodBurned;
    }

    @Column(name = "ProdVerified", nullable = false)
    public boolean isProdVerified()
    {
        return this.prodVerified;
    }

    public void setProdVerified(boolean prodVerified)
    {
        this.prodVerified = prodVerified;
    }

    @Column(name = "ProdLabel", nullable = false)
    public boolean isProdLabel()
    {
        return this.prodLabel;
    }

    public void setProdLabel(boolean prodLabel)
    {
        this.prodLabel = prodLabel;
    }

    @Column(name = "Season")
    public Integer getSeason()
    {
        return this.season;
    }

    public void setSeason(Integer season)
    {
        this.season = season;
    }

    @Column(name = "SeasonOffest")
    public Integer getSeasonOffest()
    {
        return this.seasonOffest;
    }

    public void setSeasonOffest(Integer seasonOffest)
    {
        this.seasonOffest = seasonOffest;
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

}
