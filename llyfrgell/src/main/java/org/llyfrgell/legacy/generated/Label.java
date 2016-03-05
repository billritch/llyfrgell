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
 * Label generated by hbm2java
 */
@Entity
@Table(name = "label", catalog = "collections2007")
public class Label
    extends EnumList
{

    /**
     *
     */
    private static final long serialVersionUID = -8001419520342389163L;

    public Label()
    {
    }

    public Label(String manufacturer)
    {
        setText(manufacturer);
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Label", unique = true, nullable = false)
    public Integer getLabel()
    {
        return getId();
    }

    public void setLabel(Integer label)
    {
        setId(label);
    }

    @Column(name = "Manufacturer", length = 50)
    public String getManufacturer()
    {
        return getText();
    }

    public void setManufacturer(String manufacturer)
    {
        setText(manufacturer);
    }

}