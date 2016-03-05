package org.llyfrgell.model.artist;

import org.llyfrgell.model.codes.Code;

/**
 * Role
 *
 * @author William Alan Ritch Jun 6, 2008
 *
 */
public interface Role
	extends Code
{

	public static final int E_Writer = 16;
	public static final int E_Director = 29;
	public static final int E_Editor = 61;
	public static final int E_Artist = 64;
	public static final int E_Translator = 65;
	public static final int E_OA = 66;
	public static final int E_Cartographer = 67;
	public static final int E_Photographer = 68;

} // interface Role