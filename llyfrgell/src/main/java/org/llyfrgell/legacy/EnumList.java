/**
 * EnumList.java Nov 19, 2013
 */
package org.llyfrgell.legacy;


/**
 * @author William Alan Ritch Nov 19, 2013
 *
 */
public class EnumList
    implements java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 4338771609748970183L;
    private Integer id;
    private String txt;

    /**
     *
     */
    public EnumList()
    {
    }


    public EnumList(String colourText)
    {
        this.txt = colourText;
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer colour)
    {
        this.id = colour;
    }

    public String getText()
    {
        return this.txt;
    }

    public void setText(String txt)
    {
        this.txt = txt;
    }

    @Override
    public String toString() {
        return buildString(new StringBuilder()).toString();
    }

    protected StringBuilder buildString(StringBuilder builder) {
        if (null == builder) {
            builder = new StringBuilder();
        }

        // ID
        builder.append("id=");
        builder.append(id);

        // Text
        builder.append(" text='");
        builder.append(getText());
        builder.append("'");

        return builder;
    } // buildString()

} // class EnumList
