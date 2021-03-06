package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:27 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DoctorWhoStories generated by hbm2java
 */
@Entity
@Table(name = "doctor who stories", catalog = "collections2007")
public class DoctorWhoStories implements
        java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 4374047872597893521L;
    private Integer id;
    private String book;
    private Integer bookId;
    private String title;
    private String author;
    private String doctor;
    private String companions;

    public DoctorWhoStories()
    {
    }

    public DoctorWhoStories(String book, Integer bookId, String title,
            String author, String doctor, String companions)
    {
        this.book = book;
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.doctor = doctor;
        this.companions = companions;
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

    @Column(name = "Book")
    public String getBook()
    {
        return this.book;
    }

    public void setBook(String book)
    {
        this.book = book;
    }

    @Column(name = "BookID")
    public Integer getBookId()
    {
        return this.bookId;
    }

    public void setBookId(Integer bookId)
    {
        this.bookId = bookId;
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

}
