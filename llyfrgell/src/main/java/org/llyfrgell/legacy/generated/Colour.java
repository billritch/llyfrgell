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
 * Colour generated by hbm2java
 */
@Entity
@Table(name = "colour", catalog = "collections2007")
public class Colour
    extends EnumList
{

    /**
     *
     */
    private static final long serialVersionUID = 535777159233469306L;

    public Colour()
    {
    }

    public Colour(String colourText)
    {
        this.setText(colourText);
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Colour", unique = true, nullable = false)
    public Integer getColour()
    {
        return this.getId();
    }

    public void setColour(Integer colour)
    {
        this.setId(colour);
    }

    @Column(name = "`Colour Text`", length = 20)
    public String getColourText()
    {
        return this.getText();
    }

    public void setColourText(String colourText)
    {
        this.setText(colourText);
    }

}
