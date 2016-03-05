package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:27 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DoctorWhoBooks generated by hbm2java
 */
@Entity
@Table(name = "doctor who books", catalog = "collections2007")
public class DoctorWhoBooks implements
        java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 7833585091239411912L;
    private Integer id;
    private Integer idBooks;
    private String series;
    private Short serialNumber;
    private Short serialNumberTv;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String doctor;
    private String companions;
    private boolean have;
    private String year;
    private String category;

    public DoctorWhoBooks()
    {
    }

    public DoctorWhoBooks(boolean have)
    {
        this.have = have;
    }

    public DoctorWhoBooks(Integer idBooks, String series, Short serialNumber,
            Short serialNumberTv, String title, String author, String isbn,
            String publisher, String doctor, String companions, boolean have,
            String year, String category)
    {
        this.idBooks = idBooks;
        this.series = series;
        this.serialNumber = serialNumber;
        this.serialNumberTv = serialNumberTv;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.doctor = doctor;
        this.companions = companions;
        this.have = have;
        this.year = year;
        this.category = category;
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

    @Column(name = "ID-Books")
    public Integer getIdBooks()
    {
        return this.idBooks;
    }

    public void setIdBooks(Integer idBooks)
    {
        this.idBooks = idBooks;
    }

    @Column(name = "Series")
    public String getSeries()
    {
        return this.series;
    }

    public void setSeries(String series)
    {
        this.series = series;
    }

    @Column(name = "SerialNumber")
    public Short getSerialNumber()
    {
        return this.serialNumber;
    }

    public void setSerialNumber(Short serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    @Column(name = "SerialNumber-TV")
    public Short getSerialNumberTv()
    {
        return this.serialNumberTv;
    }

    public void setSerialNumberTv(Short serialNumberTv)
    {
        this.serialNumberTv = serialNumberTv;
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

    @Column(name = "Author")
    public String getAuthor()
    {
        return this.author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    @Column(name = "ISBN")
    public String getIsbn()
    {
        return this.isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    @Column(name = "Publisher")
    public String getPublisher()
    {
        return this.publisher;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    @Column(name = "Doctor")
    public String getDoctor()
    {
        return this.doctor;
    }

    public void setDoctor(String doctor)
    {
        this.doctor = doctor;
    }

    @Column(name = "Companions")
    public String getCompanions()
    {
        return this.companions;
    }

    public void setCompanions(String companions)
    {
        this.companions = companions;
    }

    @Column(name = "Have", nullable = false)
    public boolean isHave()
    {
        return this.have;
    }

    public void setHave(boolean have)
    {
        this.have = have;
    }

    @Column(name = "Year")
    public String getYear()
    {
        return this.year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    @Column(name = "Category")
    public String getCategory()
    {
        return this.category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

}
