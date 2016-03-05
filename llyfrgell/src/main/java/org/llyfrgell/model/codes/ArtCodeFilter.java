/**
 * ArtCodeFilter.java Jan 21, 2014
 */
package org.llyfrgell.model.codes;

/**
 * A CodeFilter for the type of artwork: literary, audio, video, picture, etc.
 *
 * @author William Alan Ritch Jan 21, 2014
 */
public class ArtCodeFilter extends
        SimpleCodeFilter {

    /**
     * The art type as a bitmask.  This allows to have some kinds of
     * art works that exist in multiple types.
     *
     */
    private Integer maskArtType;

    /**
     * Name of the filter. The ones defined here have names.
     */
    private String filterName;

    private static final int LiteraryMask       = 0x0001;
    private static final int VideoMask          = 0x0002;
    private static final int AudioMask          = 0x0004;
    private static final int PictureMask        = 0x0008;
    private static final int SculptureMask      = 0x0010;

    /**
     *
     */
    private ArtCodeFilter() {
    }

    /**
     * Create a new ArtCodeFilter with the specified mask.
     * Since these fileters are effectively constants the user should not
     * directly create one.
     *
     * @param mask Mask bits
     * @param name Filter name
     */
    private ArtCodeFilter(int mask, String name) {
        super();
        setFilter(new Integer(mask));
        filterName = name;
    } // ArtCodeFilter()


    /**
     * Art that is like a book, or magazine, or article, or story.
     */
    public static ArtCodeFilter Literary =
            new ArtCodeFilter(LiteraryMask, "Literary");

    /**
     * Art that is moving images, like a movie or TV show.
     */
    public static ArtCodeFilter Video =
            new ArtCodeFilter(VideoMask, "Video");

    /**
     * Art that is audio sounds, like a song, or radio show, or CD.
     */
    public static ArtCodeFilter Audio =
            new ArtCodeFilter(AudioMask, "Audio");

    /**
     * Art that is a still image, like a painting or a photograph.
     */
    public static ArtCodeFilter Picture =
            new ArtCodeFilter(PictureMask, "Picture");

    /**
     * Art that is a "3D" real-world object, like a statue, or a quilt.
     */
    public static ArtCodeFilter Sculpture =
            new ArtCodeFilter(SculptureMask, "Sculpture");

    /**
     * Store the mask for this particular type.
     *
     * @param mask_filter
     */
    private final void setFilter(Integer mask_filter) {
        maskArtType = mask_filter;
    } // setFilter()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.codes.SimpleCodeFilter#getFilterValue()
     */
    @Override
    protected Object getFilterValue() {
        return maskArtType;
    } // getFilterValue()


    /* (non-Javadoc)
     * @see org.llyfrgell.model.codes.SimpleCodeFilter#toString()
     */
    @Override
    public String toString() {
        // is there a name to this
        if (null != filterName) {
            return filterName;
        }

        if (null == maskArtType) {
            return "-null-";
        }

        return maskArtType.toString();
    } // toString()


} // class ArtCodeFilter
