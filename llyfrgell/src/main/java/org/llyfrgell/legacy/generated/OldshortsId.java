package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:27 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OldshortsId generated by hbm2java
 */
@Embeddable
public class OldshortsId implements
        java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = -7549860358956807172L;
    private String waroffset;
    private String mediumName;
    private String manufacturer;
    private String titleFull;

    public OldshortsId()
    {
    }

    public OldshortsId(String waroffset, String mediumName,
            String manufacturer, String titleFull)
    {
        this.waroffset = waroffset;
        this.mediumName = mediumName;
        this.manufacturer = manufacturer;
        this.titleFull = titleFull;
    }

    @Column(name = "WAROffset")
    public String getWaroffset()
    {
        return this.waroffset;
    }

    public void setWaroffset(String waroffset)
    {
        this.waroffset = waroffset;
    }

    @Column(name = "`Medium Name`", length = 10)
    public String getMediumName()
    {
        return this.mediumName;
    }

    public void setMediumName(String mediumName)
    {
        this.mediumName = mediumName;
    }

    @Column(name = "Manufacturer", length = 50)
    public String getManufacturer()
    {
        return this.manufacturer;
    }

    public void setManufacturer(String manufacturer)
    {
        this.manufacturer = manufacturer;
    }

    @Column(name = "TitleFull")
    public String getTitleFull()
    {
        return this.titleFull;
    }

    public void setTitleFull(String titleFull)
    {
        this.titleFull = titleFull;
    }

    @Override
    public boolean equals(Object other)
    {
        if ((this == other))
        {
            return true;
        }
        if ((other == null))
        {
            return false;
        }
        if (!(other instanceof OldshortsId))
        {
            return false;
        }
        OldshortsId castOther = (OldshortsId) other;

        return ((this.getWaroffset() == castOther.getWaroffset()) || ((this
                .getWaroffset() != null) && (castOther.getWaroffset() != null) && this
                .getWaroffset().equals(castOther.getWaroffset())))
                && ((this.getMediumName() == castOther.getMediumName()) || ((this
                        .getMediumName() != null)
                        && (castOther.getMediumName() != null) && this
                        .getMediumName().equals(castOther.getMediumName())))
                && ((this.getManufacturer() == castOther.getManufacturer()) || ((this
                        .getManufacturer() != null)
                        && (castOther.getManufacturer() != null) && this
                        .getManufacturer().equals(castOther.getManufacturer())))
                && ((this.getTitleFull() == castOther.getTitleFull()) || ((this
                        .getTitleFull() != null)
                        && (castOther.getTitleFull() != null) && this
                        .getTitleFull().equals(castOther.getTitleFull())));
    }

    @Override
    public int hashCode()
    {
        int result = 17;

        result = (37 * result)
                + (getWaroffset() == null ? 0 : this.getWaroffset().hashCode());
        result = (37
                * result)
                + (getMediumName() == null ? 0 : this.getMediumName()
                        .hashCode());
        result = (37
                * result)
                + (getManufacturer() == null ? 0 : this.getManufacturer()
                        .hashCode());
        result = (37 * result)
                + (getTitleFull() == null ? 0 : this.getTitleFull().hashCode());
        return result;
    }

}
