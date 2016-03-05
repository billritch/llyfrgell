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
 * Media generated by hbm2java
 */
@Entity
@Table(name = "media", catalog = "collections2007")
public class Media
    extends EnumList
{

    /**
     *
     */
    private static final long serialVersionUID = 6252314327948927051L;
    private String mediumCategory;

    public Media()
    {
    }

    public Media(String mediumName, String mediumCategory)
    {
        setText(mediumName);
        setMediumCategory(mediumCategory);
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Medium", unique = true, nullable = false)
    public Integer getMedium()
    {
        return getId();
    }

    public void setMedium(Integer medium)
    {
        setId(medium);
    }

    @Column(name = "`Medium Name`", length = 10)
    public String getMediumName()
    {
        return getText();
    }

    public void setMediumName(String mediumName)
    {
        setText(mediumName);
    }

    @Column(name = "`Medium Category`", length = 10)
    public String getMediumCategory()
    {
        return this.mediumCategory;
    }

    public void setMediumCategory(String mediumCategory)
    {
        this.mediumCategory = mediumCategory;
    }

    @Override
    protected StringBuilder buildString(StringBuilder builder) {
        builder = super.buildString(builder);

        // Category
        builder.append(" category='");
        builder.append(getMediumCategory());
        builder.append("'");

        return builder;
    } // buildString()

}
