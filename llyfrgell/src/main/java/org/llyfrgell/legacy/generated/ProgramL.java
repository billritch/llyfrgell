package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:27 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ProgramL generated by hbm2java
 */
@Entity
@Table(name = "program-l", catalog = "collections2007")
public class ProgramL implements
        java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 2509139394387088744L;
    private Integer programNumber;
    private String title;
    private Integer series;
    private Short serialNumber;
    private Short year;
    private Integer category;
    private Integer genre;
    private Integer language;
    private String description;
    private boolean deleteMe;
    private Integer bcode;

    public ProgramL()
    {
    }

    public ProgramL(boolean deleteMe)
    {
        this.deleteMe = deleteMe;
    }

    public ProgramL(String title, Integer series, Short serialNumber,
            Short year, Integer category, Integer genre, Integer language,
            String description, boolean deleteMe, Integer bcode)
    {
        this.title = title;
        this.series = series;
        this.serialNumber = serialNumber;
        this.year = year;
        this.category = category;
        this.genre = genre;
        this.language = language;
        this.description = description;
        this.deleteMe = deleteMe;
        this.bcode = bcode;
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

    @Column(name = "Serial Number")
    public Short getSerialNumber()
    {
        return this.serialNumber;
    }

    public void setSerialNumber(Short serialNumber)
    {
        this.serialNumber = serialNumber;
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

    @Column(name = "BCODE")
    public Integer getBcode()
    {
        return this.bcode;
    }

    public void setBcode(Integer bcode)
    {
        this.bcode = bcode;
    }

}
