/**
 *
 */
package org.llyfrgell.model;

/**
 * Parameter Sink - a consumer of parameters.
 *
 * The function parseParameter(String) gives the Parameter Sink
 * an opportunity to parse and consume a parameter.
 *
 * @author William Alan Ritch 2008/02/21.
 * @revised William Alan Ritch 2013/10/30. Renamed to "ParameterSink."
 */
public interface ParameterSink
{
    /**
     * Parse the parameter.  If it one that we want, we can consume it
     * and return \c true.  If not, \c false is returned.
     *
     * @param str_param
     * @return \c true if we want this parameter.
     */
    boolean parseParameter(String str_param);
} // interface ParameterSink
