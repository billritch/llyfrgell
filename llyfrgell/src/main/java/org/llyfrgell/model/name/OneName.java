package org.llyfrgell.model.name;

/**
 * A single name element.
 *
 * @author William Alan Ritch Oct 30, 2013
 */
public interface OneName  extends Name
{
    /**
     * 0 - not specified.
     * -1 - senior
     * 1 - junior
     * 2 - II
     * 3 - III
     * etc.
     */
    static final int GenerationNotSpecified = 0;
    static final int GenerationSenior = -1;
    static final int GenerationJunior = 1;


    /***
     * Property: Surname
     *
     * This is often called the "family name."
     * In English it is usually the last name.
     *
     * @return the surname.
     */
    CharSequence getSurname();

    /***
     * Property: GivenName
     *
     * This is the name given to the person at birth.
     * In English it is usual the first name, or the first several names.
     *
     * @return The given name.
     */
    CharSequence getGivenName();

    /***
     * Property: Generation.
     *
     * Code for generational designation.
     * A "generational" designation is, for example, "Jr." or "Sr." or "III".
     *
     * With the exceptions of -1, 0, 1 the generational designation are
     * numbers which represent the Roman numerals after a person's name.
     * Thus, "John Smith III"'s generational designation is "3".
     * Are defined above.
     *
     * @return Generational designation.
     */
    int getGeneration();


    /***
     * Get a text version of the generation specification.
     *
     * This will probably be generated from the generational specification.
     * For instance, a -1 generational designation will return "Sr."
     *
     * @return Generation text
     * If the generation designation is 0, the empty string is returned.
     */
    CharSequence getGenerationText();

    /***
     * Property: Honorific.
     *
     * Get honorific text that precedes the name.
     * For example, "Dr." or "Mr."
     *
     * @return Honorific Text.
     * If there is no honorific text, the empty string is returned.
     */
    CharSequence getHonorific();

    /***
     * Property: Titles
     *
     * Get the titles of the person that follows the name.
     * For example, "M.D." or "F.R.N.S."
     *
     * @return Titles text.
     * If there is no titles text, the empty string is returned.
     */
    CharSequence getTitles();

    /***
     * Flags for name type information.
     * @{
     */

    /***
     * Property: Pseudonym.
     *
     * Is this name a pseudonym?
     *
     * @return Pseudonym flag.
     */
    boolean isPseudonym();

    /***
     * Property: Nickname.
     *
     * Is this name a nick name?
     *
     * @return Nick name flag.
     */
    boolean isNickname();


    /***
     * @}
     */


} // interface OneName()
