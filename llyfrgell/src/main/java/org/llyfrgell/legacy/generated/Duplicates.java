package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:27 PM by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Duplicates generated by hbm2java
 */
@Entity
@Table(name = "duplicates", catalog = "collections2007")
public class Duplicates implements
        java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 4155965429301152283L;
    private DuplicatesId id;

    public Duplicates()
    {
    }

    public Duplicates(DuplicatesId id)
    {
        this.id = id;
    }

    @EmbeddedId
    @AttributeOverrides(
    {
            @AttributeOverride(name = "keepId", column = @Column(name = "KeepID")),
            @AttributeOverride(name = "ridId", column = @Column(name = "RidID")) })
    public DuplicatesId getId()
    {
        return this.id;
    }

    public void setId(DuplicatesId id)
    {
        this.id = id;
    }

}
