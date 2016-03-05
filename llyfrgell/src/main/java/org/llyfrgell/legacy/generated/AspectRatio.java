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
 * AspectRatio generated by hbm2java
 */
@Entity
@Table(name = "aspect", catalog = "collections2007")
public class AspectRatio
    extends EnumList
{

    /**
     *
     */
    private static final long serialVersionUID = 8294134943936101604L;

    public AspectRatio()
    {
    }

    public AspectRatio(String aspectName)
    {
        setText(aspectName);
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Aspect", unique = true, nullable = false)
    public Integer getAspect()
    {
        return this.getId();
    }

    public void setAspect(Integer aspect)
    {
        this.setId(aspect);
    }

    @Column(name = "`Aspect Name`", length = 20)
    public String getAspectName()
    {
        return this.getText();
    }

    public void setAspectName(String aspectName)
    {
        this.setText(aspectName);
    }

} // class AspectRatio
