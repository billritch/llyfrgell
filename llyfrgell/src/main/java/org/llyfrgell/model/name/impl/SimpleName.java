/**
 * SimpleName.java Jan 22, 2014
 */
package org.llyfrgell.model.name.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.jboss.logging.Logger;
import org.llyfrgell.model.name.OneName;
import org.springframework.util.StringUtils;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;




/**
 * A simple implementation of the OneName interface
 *
 * @author William Alan Ritch Jan 22, 2014
 *
 */
public class SimpleName
    extends NameBase
    implements OneName {

    private static final String XML_NAME = "Name";
    private static final String XML_FULL_NAME = "";
    private static final String XML_SORT_NAME = "";
    private static final String XML_SUR_NAME = "";
    private static final String XML_GIVEN_NAME = "";
    private static final String XML_GENERATION = "";
    private static final String XML_HONORIFIC = "";
    private static final String XML_TITLES = "";
    private static final String XML_NICKNAME = "";
    private static final String XML_PRIMARY = "";
    private static final String XML_PSEUDONYM = "";
    private static final String XML_SHORT = "";

    /**
     * Property: FullName
     * For documentation @see getFullName().
     */
    private CharSequence strFullName;


    /***
     * Property: SortName
     * For documentation @see getSortName().
     */
    private CharSequence strSortName;

    /***
     * Property: Surname
     * For documentation @see getSurname().
     */
    private CharSequence strSurname;

    /***
     * Property: GivenName
     * For documentation @see getGivenName().
     */
    private CharSequence strGivenName;

    /***
     * Property: Generation.
     * For documentation @see getGeneration() and getGenerationText().
     */
    private int nGeneration;


    /***
     * Property: Honorific.
     * For documentation @see getHonorific().
     */
    private CharSequence strHonorific;

    /***
     * Property: Titles
     * For documentation @see getTitles().
     */
    private CharSequence strTitles;

    /***
     * Property: Pseudonym.
     * For documentation @see isPseudonym().
     */
    private boolean bPseudonym;

    /***
     * Property: Nickname.
     * For documentation @see isNickname().
     */
    private boolean bNickname;

    /***
     * Property: Primary.
     * For documentation @see isPrimary().
     */
    private boolean bPrimary;

    /***
     * Property: Short.
     * For documentation @see isShort().
     */
    private boolean bShort;



    /**
     *
     */
    public SimpleName() {
        super(XML_NAME, "NameList");
        strSurname = new StringBuilder();
        strGivenName = new StringBuilder();
        strHonorific = new StringBuilder();
    }


    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.artist.OneName#getFullName()
     */
    @Override
    public CharSequence getFullName() {
        return strFullName;
    } // getFullName()

    @Override
    public void setFullName(CharSequence p_name) {
        strFullName = p_name;
    } // setFullName()



    /* (non-Javadoc)
     * @see org.llyfrgell.model.artist.OneName#getSortName()
     */
    @Override
    public CharSequence getSortName() {
        if (null == strSortName) {
            strSortName = generateSortName();
        }
        return strSortName;
    } // getSortName()

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.artist.OneName#getSurname()
     */
    public CharSequence getSurname() {
        return (null == strSurname) ? "" : strSurname;
    } // getSurname()

    public void setSurname(CharSequence p_name) {
        strSurname = p_name;
    } // setSurname()

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.artist.OneName#getGivenName()
     */
    public CharSequence getGivenName() {
        return (null == strGivenName) ? "" : strGivenName;
    } // getGivenName()

    public void setGivenName(CharSequence p_name) {
        strGivenName = p_name;
    } // setGivenName()

    /**
     * @{
     * Generation.
     */

    private static final int GenerationSenior = -2;
    private static final int GenerationJunior = -1;

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.artist.OneName#getGeneration()
     */
    public int getGeneration() {
        return nGeneration;
    } // getGeneration()

    public void setGeneration(int p_generation) {
        nGeneration = p_generation;
    }

    /**
     * Is this a Generation token?
     *
     * @param str Text of token to be tested.
     * @return \c true if the string can be parsed as a generation string.
     */
    public boolean isGeneration(CharSequence str) {
        boolean bReturn = false;
        if (StringUtils.hasText(str)) {
            bReturn = getGenerationPattern().matcher(
                    str.toString().toUpperCase()).matches() ||
                    validateRoman(str);
        }

        return bReturn;
    } // isGeneration()

    public boolean setGeneration(CharSequence str) throws Exception {
        int nGeneration = 0;
        boolean bProcessedGeneration = false;

        // test for null or empty string
        if (StringUtils.hasText(str)) {
            String strUpper = str.toString().toUpperCase();

            if (getGenerationPattern().matcher(strUpper).matches()) {
                // set junior or senior
                nGeneration = ('S' == strUpper.charAt(0))
                        ? GenerationSenior : GenerationJunior;
                bProcessedGeneration = true;
            } else if (validateRoman(strUpper)) {
                nGeneration = (int) parseRoman(strUpper);
                bProcessedGeneration = true;
            } // else if
        } // if (null != str)

        this.setGeneration(nGeneration);
        return bProcessedGeneration;
    } // setGeneration()

    public CharSequence getGenerationText() {
        switch(nGeneration)
        {
            case GenerationSenior:
                return "Sr.";
            case GenerationJunior:
                return "Jr.";
            case 0:
                return "";
            default:
                try {
                    return generateRoman(nGeneration);
                } catch (Exception ex) {
                    return "-invalid-";
                }
        }
    } // getGenerationText()

    private Pattern patGeneration;

    private Pattern getGenerationPattern() {
        if (null == patGeneration) {
            patGeneration = Pattern.compile(
                    "^(SENIOR|JUNIOR)|((SR|JR)\\.{0,1})$");
        }
        return patGeneration;
    } // getGenerationPattern()

    /**
     * @}
     * Generation.
     */

    /* (non-Javadoc)
     * @see org.llyfrgell.model.artist.OneName#getHonorific()
     */
    public CharSequence getHonorific() {
        return strHonorific;
    } // getHonorific()


    /* (non-Javadoc)
     * @see org.llyfrgell.model.artist.OneName#getTitles()
     */
    public CharSequence getTitles() {
        return strTitles;
    } // getTitles()

    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.artist.OneName#isPseudonym()
     */
    public boolean isPseudonym() {
        return bPseudonym;
    } // isPseudonym()

    public void setPseudonym(boolean p_flag) {
        bPseudonym = p_flag;
    } // setPseudonym()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.artist.OneName#isNickname()
     */
    public boolean isNickname() {
        return bNickname;
    } // isNickname()

    public void setNickname(boolean p_flag) {
        bNickname = p_flag;
    } // setNickname()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.artist.OneName#isPrimary()
     */
    @Override
    public boolean isPrimary() {
        return bPrimary;
    } // isPrimary()

    @Override
    public void setPrimary(boolean p_flag) {
        bPrimary = p_flag;
    } // setPrimary()

    /* (non-Javadoc)
     * @see org.llyfrgell.model.artist.OneName#isShort()
     */
    @Override
    public boolean isShort() {
        return bShort;
    } // isShort()

    @Override
    public void setShort(boolean p_flag) {
        bShort = p_flag;
    } // setShort()

    public void addTitle(CharSequence str) {
        if (null == this.strTitles) {
            this.strTitles = new StringBuilder(str);
        } else {
            if (0 != this.strTitles.length()) {
                ((StringBuilder) this.strTitles).append(", ");
            }
            ((StringBuilder) this.strTitles).append(str);
        }
    } // addTitle()

    public void addHonorific(CharSequence str) {
        if (null == this.strHonorific) {
            this.strHonorific = new StringBuilder(str);
        } else {
            if (0 != this.strHonorific.length()) {
                ((StringBuilder) this.strHonorific).append(", ");
            }
            ((StringBuilder) this.strHonorific).append(str);
        }
    } // addHonorific()

    /**
     * Generate a sort name from the stored name.
     *
     * @return Generated sort name.
     */
    private CharSequence generateSortName()
    {
        StringBuffer str = new StringBuffer();

        // append the surname
        str.append(simplifyKey(strSurname));
        if (org.springframework.util.StringUtils.hasText(strGivenName))
        {
            // append the comma
            str.append(",");
            // append the given name
            str.append(simplifyKey(strGivenName));
        }
        if (0 != nGeneration)
        {
            str.append(",");
            str.append(nGeneration);
        }
        return str.toString();
    } // generateSortName()

    /**
     * Simplify a keyword.
     *
     * The keyword has all the non-identifier characters squeezed out
     * and all the letters are converted into lower case.
     *
     * @param p_key EnumImpl to be simplified.
     * @return Simplified keyword.
     */
    public static String simplifyKey(CharSequence p_key)
    {
        // remove all the punctuation and
        // convert everything to lower case
        StringBuffer strBuffer = new StringBuffer();
        if (null != p_key)
        {
            for (int i = 0; i < p_key.length(); i++)
            {
                Character ch = p_key.charAt(i);

                if (Character.isJavaIdentifierStart(ch))
                {
                    strBuffer.append(Character.toLowerCase(ch));
                }
        } // for i
        }

        return strBuffer.toString();
    } // simplifyKey()

    /**
     * @{
     * Roman numerals
     */

    private static final String romanNumerals =
        //   0123456     7     8     9     0
            "IVXLCDM\u2180\u2181\u2182\u2183";
    private static final long[] romanValues = new long[] {
        1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000, 1000000};
    private static Pattern patRomanNumeral;

    private static final long maxRomanValue = 1000000;

    /**
     * http://stackoverflow.com/questions/267399/how-do-you-match-only-valid-roman-numerals-with-a-regular-expression
     *
     * @param str_roman
     * @return \c true if the Roman numeral string is valid.
     * TODO Add in the additional characters for higher numbers
     */
    public static boolean validateRoman(CharSequence str_roman) {
        return getRomanPattern().matcher(str_roman).matches();
    } // validateRomanNumerals()

    /**
     * Parse a Roman numeral.
     *
     * @param str_roman Roman numeral string.
     * @return Converted into a long integer.
     * @throws Exception
     */
    public static long parseRoman(String str_roman) throws Exception {
        // first validate Roman numeral string
        if (!validateRoman(str_roman)) {
            throw new Exception("Invalid Roman numerals");
        }

        long nValue = 0;
        long max = 0;

        str_roman = str_roman.toUpperCase();

        for (int i = (str_roman.length()-1); i >= 0; i--) {
          char c = str_roman.charAt(i);

          for (int j = 0; j < romanNumerals.length(); j++) {
            if (romanNumerals.charAt(j) == c) {
              long k = romanValues[j];
              if (k >= max) {
                nValue += k;
                max = k;
              } else {
                nValue -= k;
              }
              break;
            }
          } // for j
        } // for i

        return  nValue;
      } // parseRoman()

    /**
     * Get the Roman Numeral pattern.
     *
     * If the pattern has not yet been generated, then we generate it.
     *
     * @return Pattern for Roman numerals.
     */
    private static Pattern getRomanPattern() {
        if (null == patRomanNumeral) {
            patRomanNumeral = Pattern.compile(
                    "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
        }
        return patRomanNumeral;
    } // getRomanNumeralPattern()

    /**
     * Generate a Roman Numeral.
     *
     * Values for a Roman numeral must be positive (>=0) and must be less
     * than 1,000,000.
     *
     * @param n_value Value to be encoded.
     * @return The encoded Roman numeral string.
     * @throws Exception
     */
    public static String generateRoman(long n_value) throws Exception
    {
        if ((n_value <= 0) || (n_value >= maxRomanValue)) {
            // throw exception.
            throw new Exception("Invalid value.");
        }

        StringBuffer strRoman = new StringBuffer("");
        int nDecimalDigit;

        // 100,000 place
//        nDecimalDigit = (int) (n_value / 100000);
//        generateRomanSequence(strRoman, nDecimalDigit, 10);
        // 10,000 place
//        n_value -= nDecimalDigit * 100000;
        nDecimalDigit = (int) (n_value / 10000);
        generateRomanSequence(strRoman, nDecimalDigit, 8);
        // 1,000 place
        n_value -= nDecimalDigit * 10000;
        nDecimalDigit = (int) (n_value / 1000);
        generateRomanSequence(strRoman, nDecimalDigit, 6);
        // 100 place
        n_value -= nDecimalDigit * 1000;
        nDecimalDigit = (int) (n_value / 100);
        generateRomanSequence(strRoman, nDecimalDigit, 4);
        // 10 place
        n_value -= nDecimalDigit * 100;
        nDecimalDigit = (int) (n_value / 10);
        generateRomanSequence(strRoman, nDecimalDigit, 2);
        // 1 place
        n_value -= nDecimalDigit * 10;
        nDecimalDigit = (int) n_value;
        generateRomanSequence(strRoman, nDecimalDigit, 0);

        return strRoman.toString();
    } // generateRomanNumeral()

    /**
     * Generate a sequence of Roman numerals for a given digit.
     *
     * How it works.  Each digit from a normal Arabic number represents
     * a uniform way to in encode using letters. We pass in a digit value
     * (0 - 9) and a base index into the letter table.
     *
     * If the digit is 0, no characters are used.
     * If the digit is 1, 2, or 3 we use 1 to 3 letters from the base index
     * location.
     * If the digit is 4 we use one letter from the base index and one from
     * [base+1].
     * If the digit is 5 we use the letter from [base+1].
     * If the digit is 6, 7, or 8 we use the letter from [base+1] and
     * 1 to 3 letters from [base].
     * If the digit is 9 we use one letter from [base] and one from [base+2].
     *
     * @param bufRoman Buffer of already assembled Roman numerals.
     * @param n_digit Digit to be encoded.
     * @param n_base Index into the table of Roman numerals so
     * that we know which letter represents the nominal range of
     * numerals to use.
     * @return
     */
    private static StringBuffer generateRomanSequence(StringBuffer bufRoman,
            int n_digit, int n_base)
    {
        switch (n_digit)
        {
            case 3:
                bufRoman.append(romanNumerals.charAt(n_base));
            case 2:
                bufRoman.append(romanNumerals.charAt(n_base));
            case 1:
                bufRoman.append(romanNumerals.charAt(n_base));
                break;
            case 4:
                bufRoman.append(romanNumerals.charAt(n_base));
            case 5:
                bufRoman.append(romanNumerals.charAt(n_base+1));
                break;
            case 6:
                bufRoman.append(romanNumerals.charAt(n_base+1))
                    .append(romanNumerals.charAt(n_base));
                break;
            case 7:
                bufRoman.append(romanNumerals.charAt(n_base+1))
                    .append(romanNumerals.charAt(n_base))
                    .append(romanNumerals.charAt(n_base));
                break;
            case 8:
                bufRoman.append(romanNumerals.charAt(n_base+1))
                    .append(romanNumerals.charAt(n_base))
                    .append(romanNumerals.charAt(n_base))
                    .append(romanNumerals.charAt(n_base));
                break;
            case 9:
                bufRoman.append(romanNumerals.charAt(n_base))
                    .append(romanNumerals.charAt(n_base+2));
                break;
            case 0:
                break;
        } // switch (n_digit)

        return bufRoman;
    } // generateRomanSequence()


    public boolean isSingle() {
        // TODO Auto-generated method stub
        return false;
    }


    public OneName getPrimary() {
        // TODO Auto-generated method stub
        return null;
    }


    public List<OneName> getList() {
        // TODO Auto-generated method stub
        return null;
    }


    /*
     * (non-Javadoc)
     * @see org.llyfrgell.model.XmlConverter#toXml(org.w3c.dom.Document)
     */
    @Override
    public Element toXml(Document doc) {
        Element el = null;

        try {
            Element elChild;

            el = doc.createElement(XML_NAME);

            // TODO add the ID
            /*
            Element elId;
            elId = doc.createElement(ID_XML_NAME);
            elId.appendChild(doc.createTextNode(getIdString()));
            el.appendChild(elId);
            */

            elChild = doc.createElement(XML_FULL_NAME);
            elChild.appendChild(doc.createTextNode(getFullName().toString()));
            el.appendChild(elChild);

            elChild = doc.createElement(XML_SORT_NAME);
            elChild.appendChild(doc.createTextNode(getSortName().toString()));
            el.appendChild(elChild);

            elChild = doc.createElement(XML_SUR_NAME);
            elChild.appendChild(doc.createTextNode(getSurname().toString()));
            el.appendChild(elChild);

            elChild = doc.createElement(XML_GIVEN_NAME);
            elChild.appendChild(doc.createTextNode(getGivenName().toString()));
            el.appendChild(elChild);

            elChild = doc.createElement(XML_GENERATION);
            elChild.appendChild(doc.createTextNode(Integer.toString(getGeneration())));
            el.appendChild(elChild);

            elChild = doc.createElement(XML_HONORIFIC);
            elChild.appendChild(doc.createTextNode(getHonorific().toString()));
            el.appendChild(elChild);

            elChild = doc.createElement(XML_TITLES);
            elChild.appendChild(doc.createTextNode(getTitles().toString()));
            el.appendChild(elChild);

            elChild = doc.createElement(XML_NICKNAME);
            elChild.appendChild(doc.createTextNode(Boolean.toString(isNickname())));
            el.appendChild(elChild);

            elChild = doc.createElement(XML_PRIMARY);
            elChild.appendChild(doc.createTextNode(Boolean.toString(isPrimary())));
            el.appendChild(elChild);

            elChild = doc.createElement(XML_PSEUDONYM);
            elChild.appendChild(doc.createTextNode(Boolean.toString(isPseudonym())));
            el.appendChild(elChild);

            elChild = doc.createElement(XML_SHORT);
            elChild.appendChild(doc.createTextNode(Boolean.toString(isShort())));
            el.appendChild(elChild);


        } catch (DOMException ex) {
            Logger l = Logger.getLogger(this.getClass().getName());
            l.error("Could not create Code XML element.", ex);
        }

        return el;
    } // toXml()

    /**
     * @}
     * Roman numerals
     */
} // class SimpleName
