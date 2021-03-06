package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:27 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Booktemp generated by hbm2java
 */
@Entity
@Table(name = "booktemp", catalog = "collections2007")
public class Booktemp implements
        java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 6793463672990590757L;
    private Integer id;
    private String title;
    private String series;
    private Short serialNumber;
    private String publisher;
    private String isbn;
    private Short pages;
    private Integer medium;
    private String cost;
    private Short copyright;
    private String publicationDate;
    private String writer1;
    private String writer2;
    private String writer3;
    private String artistCover;
    private String artistInterior;
    private String category;
    private boolean verified;
    private Integer bcode;
    private Short count;
    private String url;
    private String alternaiveTitle;
    private String genre;

    public Booktemp()
    {
    }

    public Booktemp(boolean verified)
    {
        this.verified = verified;
    }

    public Booktemp(String title, String series, Short serialNumber,
            String publisher, String isbn, Short pages, Integer medium,
            String cost, Short copyright, String publicationDate,
            String writer1, String writer2, String writer3, String artistCover,
            String artistInterior, String category, boolean verified,
            Integer bcode, Short count, String url, String alternaiveTitle,
            String genre)
    {
        this.title = title;
        this.series = series;
        this.serialNumber = serialNumber;
        this.publisher = publisher;
        this.isbn = isbn;
        this.pages = pages;
        this.medium = medium;
        this.cost = cost;
        this.copyright = copyright;
        this.publicationDate = publicationDate;
        this.writer1 = writer1;
        this.writer2 = writer2;
        this.writer3 = writer3;
        this.artistCover = artistCover;
        this.artistInterior = artistInterior;
        this.category = category;
        this.verified = verified;
        this.bcode = bcode;
        this.count = count;
        this.url = url;
        this.alternaiveTitle = alternaiveTitle;
        this.genre = genre;
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

    @Column(name = "Title", length = 100)
    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
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

    @Column(name = "Publisher", length = 50)
    public String getPublisher()
    {
        return this.publisher;
    }

    public void setPublisher(String publisher)
    {
        this.publisher = publisher;
    }

    @Column(name = "ISBN", length = 25)
    public String getIsbn()
    {
        return this.isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    @Column(name = "Pages")
    public Short getPages()
    {
        return this.pages;
    }

    public void setPages(Short pages)
    {
        this.pages = pages;
    }

    @Column(name = "Medium")
    public Integer getMedium()
    {
        return this.medium;
    }

    public void setMedium(Integer medium)
    {
        this.medium = medium;
    }

    @Column(name = "Cost", length = 10)
    public String getCost()
    {
        return this.cost;
    }

    public void setCost(String cost)
    {
        this.cost = cost;
    }

    @Column(name = "Copyright")
    public Short getCopyright()
    {
        return this.copyright;
    }

    public void setCopyright(Short copyright)
    {
        this.copyright = copyright;
    }

    @Column(name = "Publication Date", length = 20)
    public String getPublicationDate()
    {
        return this.publicationDate;
    }

    public void setPublicationDate(String publicationDate)
    {
        this.publicationDate = publicationDate;
    }

    @Column(name = "Writer1", length = 60)
    public String getWriter1()
    {
        return this.writer1;
    }

    public void setWriter1(String writer1)
    {
        this.writer1 = writer1;
    }

    @Column(name = "Writer2", length = 60)
    public String getWriter2()
    {
        return this.writer2;
    }

    public void setWriter2(String writer2)
    {
        this.writer2 = writer2;
    }

    @Column(name = "Writer3", length = 60)
    public String getWriter3()
    {
        return this.writer3;
    }

    public void setWriter3(String writer3)
    {
        this.writer3 = writer3;
    }

    @Column(name = "ArtistCover", length = 60)
    public String getArtistCover()
    {
        return this.artistCover;
    }

    public void setArtistCover(String artistCover)
    {
        this.artistCover = artistCover;
    }

    @Column(name = "ArtistInterior", length = 60)
    public String getArtistInterior()
    {
        return this.artistInterior;
    }

    public void setArtistInterior(String artistInterior)
    {
        this.artistInterior = artistInterior;
    }

    @Column(name = "Category", length = 25)
    public String getCategory()
    {
        return this.category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    @Column(name = "Verified", nullable = false)
    public boolean isVerified()
    {
        return this.verified;
    }

    public void setVerified(boolean verified)
    {
        this.verified = verified;
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

    @Column(name = "Count")
    public Short getCount()
    {
        return this.count;
    }

    public void setCount(Short count)
    {
        this.count = count;
    }

    @Column(name = "URL")
    public String getUrl()
    {
        return this.url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    @Column(name = "AlternaiveTitle", length = 100)
    public String getAlternaiveTitle()
    {
        return this.alternaiveTitle;
    }

    public void setAlternaiveTitle(String alternaiveTitle)
    {
        this.alternaiveTitle = alternaiveTitle;
    }

    @Column(name = "Genre", length = 50)
    public String getGenre()
    {
        return this.genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

}
