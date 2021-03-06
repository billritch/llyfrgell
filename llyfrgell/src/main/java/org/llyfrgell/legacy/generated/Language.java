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
 * Language generated by hbm2java
 */
@Entity
@Table(name = "language", catalog = "collections2007")
public class Language
    extends EnumList
{

    /**
     *
     */
    private static final long serialVersionUID = 1390021452203461914L;

    public Language()
    {
    }

    public Language(String languageName)
    {
        setText(languageName);
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Language", unique = true, nullable = false)
    public Integer getLanguage()
    {
        return getId();
    }

    public void setLanguage(Integer language)
    {
        setId(language);
    }

    @Column(name = "`Language Name`", length = 20)
    public String getLanguageName()
    {
        return getText();
    }

    public void setLanguageName(String languageName)
    {
        setText(languageName);
    }

}
