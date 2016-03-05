package org.llyfrgell.model.name;

import org.llyfrgell.model.codes.Code;


public interface Honorific
	extends Code
{
    /**
     * Honorifics applied to a name.
     *
     */
    public enum EHonorific
    {
        None,
        Sir,
        Lord,
        OBE,
        MD,
        Doctor,
        PhD,
        Saint
    };

} // interface Honorific