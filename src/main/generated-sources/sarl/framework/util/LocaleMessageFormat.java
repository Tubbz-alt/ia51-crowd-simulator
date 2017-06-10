/**
 * $Id$
 * 
 * Copyright (c) 2011-17 Stephane GALLAND <stephane.galland@utbm.fr>.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * This program is free software; you can redistribute it and/or modify
 */
package framework.util;

import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * <code>LocaleMessageFormat</code> provides a means to produce concatenated
 * messages in a language-neutral way in the {@link Locale}
 * utility class.
 * <p>
 * <code>LocaleMessageFormat</code> takes a set of objects, formats them, then
 * inserts the formatted strings into the pattern at the appropriate places.
 * <p>
 * In addition to the standard JDK {@link MessageFormat}, <code>LocaleMessageFormat</code>
 * provides the <code>FormatStyle</code> named "raw". This new style does not try
 * to format the given data according to the locale. It simply put the
 * not-formatted data in the result.
 * 
 * Copied from {@link https://github.com/gallandarakhneorg/afc/}.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public class LocaleMessageFormat extends MessageFormat {
  /**
   * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
   * @version $Name$ $Revision$ $Date$
   */
  @SarlSpecification("0.5")
  private static class RawNumberFormat extends NumberFormat {
    private final static String RAW_NEGATIVE_SIGN = "-";
    
    private final static String RAW_DECIMAL_SEPARATOR = ".";
    
    private final static String RAW_ZERO_DIGIT = "0";
    
    private final boolean isUnformatted;
    
    private final RoundingMode roundingMode;
    
    public RawNumberFormat(final String pattern, final int groupSize, final int minInt, final int maxInt, final int minFrac, final int maxFrac, final RoundingMode roundingMode) {
      this.roundingMode = roundingMode;
      this.isUnformatted = (((((groupSize == 0) && (minInt == 0)) && (maxInt == Integer.MAX_VALUE)) && (minFrac == 0)) && (maxFrac == 0));
      this.setMinimumIntegerDigits(minInt);
      this.setMaximumIntegerDigits(maxInt);
      this.setMinimumFractionDigits(minFrac);
      this.setMaximumFractionDigits(maxFrac);
    }
    
    @Override
    public StringBuffer format(final Object number, final StringBuffer toAppendTo, final FieldPosition pos) {
      if ((number instanceof BigInteger)) {
        return this.format(BigInteger.class, toAppendTo, pos);
      }
      if ((number instanceof BigDecimal)) {
        return this.format(BigDecimal.class, toAppendTo, pos);
      }
      return super.format(number, toAppendTo, pos);
    }
    
    private void formatInteger(final boolean negative, final String number, final StringBuffer toAppendTo) {
      if (negative) {
        toAppendTo.append(LocaleMessageFormat.RawNumberFormat.RAW_NEGATIVE_SIGN);
      }
      for (int c = (this.getMinimumIntegerDigits() - number.length()); (c > 0); c--) {
        toAppendTo.append(LocaleMessageFormat.RawNumberFormat.RAW_ZERO_DIGIT);
      }
      toAppendTo.append(number);
      int n = this.getMinimumFractionDigits();
      if ((n > 0)) {
        toAppendTo.append(LocaleMessageFormat.RawNumberFormat.RAW_DECIMAL_SEPARATOR);
        for (int c = 0; (c < n); c++) {
          toAppendTo.append(LocaleMessageFormat.RawNumberFormat.RAW_ZERO_DIGIT);
        }
      }
    }
    
    private void formatDecimal(final BigDecimal number, final StringBuffer toAppendTo) {
      int _spaceship = (number.compareTo(BigDecimal.ZERO));
      boolean negative = (_spaceship < 0);
      int minInt = this.getMinimumIntegerDigits();
      int minFrac = this.getMinimumFractionDigits();
      int maxFrac = this.getMaximumFractionDigits();
      BigDecimal n = number.setScale(maxFrac, this.roundingMode);
      String rawString = n.abs().toPlainString();
      int decimalPos = rawString.indexOf(LocaleMessageFormat.RawNumberFormat.RAW_DECIMAL_SEPARATOR);
      String integer = null;
      String decimal = null;
      if ((decimalPos < 0)) {
        integer = rawString;
        decimal = "";
      } else {
        integer = rawString.substring(0, decimalPos);
        decimal = rawString.substring((decimalPos + 1));
      }
      if (negative) {
        toAppendTo.append(LocaleMessageFormat.RawNumberFormat.RAW_NEGATIVE_SIGN);
      }
      int _length = integer.length();
      int c = (minInt - _length);
      while ((c > 0)) {
        {
          toAppendTo.append(LocaleMessageFormat.RawNumberFormat.RAW_ZERO_DIGIT);
          c++;
        }
      }
      toAppendTo.append(integer);
      if (((minFrac > 0) || ((maxFrac > 0) && (decimal.length() > 0)))) {
        toAppendTo.append(LocaleMessageFormat.RawNumberFormat.RAW_DECIMAL_SEPARATOR);
        toAppendTo.append(decimal);
        int _length_1 = decimal.length();
        int _minus = (minFrac - _length_1);
        c = _minus;
        while ((c > 0)) {
          {
            toAppendTo.append(LocaleMessageFormat.RawNumberFormat.RAW_ZERO_DIGIT);
            c--;
          }
        }
      }
    }
    
    /**
     * Specialization of format.
     * 
     * @param number is the number to format.
     * @param toAppendTo is the string buffer into which the formatting result may be appended.
     * @param pos is on input: an alignment field, if desired. On output: the offsets of
     * 	the alignment field.
     * @return the value passed in as <code>toAppendTo</code>
     * @throws ArithmeticException if rounding is needed with rounding
     *                   mode being set to RoundingMode.UNNECESSARY
     * @see java.text.Format#format
     */
    public StringBuffer format(final BigInteger number, final StringBuffer toAppendTo, final FieldPosition pos) {
      if (this.isUnformatted) {
        toAppendTo.append(number.toString());
      } else {
        int _signum = number.signum();
        boolean _lessThan = (_signum < 0);
        this.formatInteger(_lessThan, number.abs().toString(), toAppendTo);
      }
      return toAppendTo;
    }
    
    /**
     * Specialization of format.
     * 
     * @param number is the number to format.
     * @param toAppendTo is the string buffer into which the formatting result may be appended.
     * @param pos is on input: an alignment field, if desired. On output: the offsets of
     * 	the alignment field.
     * @return the value passed in as <code>toAppendTo</code>
     * @throws ArithmeticException if rounding is needed with rounding
     *                   mode being set to RoundingMode.UNNECESSARY
     * @see java.text.Format#format
     */
    public StringBuffer format(final BigDecimal number, final StringBuffer toAppendTo, final FieldPosition pos) {
      if (this.isUnformatted) {
        toAppendTo.append(number.toPlainString());
      } else {
        this.formatDecimal(number, toAppendTo);
      }
      return toAppendTo;
    }
    
    @Override
    public StringBuffer format(final double number, final StringBuffer toAppendTo, final FieldPosition pos) {
      if (this.isUnformatted) {
        toAppendTo.append(Double.toString(number));
      } else {
        BigDecimal _bigDecimal = new BigDecimal(number);
        this.formatDecimal(_bigDecimal, toAppendTo);
      }
      return toAppendTo;
    }
    
    @Override
    public StringBuffer format(final long number, final StringBuffer toAppendTo, final FieldPosition pos) {
      if (this.isUnformatted) {
        toAppendTo.append(Long.toString(number));
      } else {
        this.formatInteger((number < 0), Long.toString(Math.abs(number)), toAppendTo);
      }
      return toAppendTo;
    }
    
    public Number parse(final String source, final ParsePosition parsePosition) {
      throw new UnsupportedOperationException();
    }
    
    @Override
    @Pure
    @SyntheticMember
    public boolean equals(final Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      RawNumberFormat other = (RawNumberFormat) obj;
      if (other.isUnformatted != this.isUnformatted)
        return false;
      if (this.roundingMode == null) {
        if (other.roundingMode != null)
          return false;
      } else if (!this.roundingMode.equals(other.roundingMode))
        return false;
      return super.equals(obj);
    }
    
    @Override
    @Pure
    @SyntheticMember
    public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + (this.isUnformatted ? 1231 : 1237);
      result = prime * result + ((this.roundingMode== null) ? 0 : this.roundingMode.hashCode());
      return result;
    }
    
    @Override
    @Pure
    @SyntheticMember
    public LocaleMessageFormat.RawNumberFormat clone() {
      try {
        return (LocaleMessageFormat.RawNumberFormat) super.clone();
      } catch (Throwable exception) {
        throw new Error(exception);
      }
    }
    
    @SyntheticMember
    private final static long serialVersionUID = 13450935064L;
  }
  
  /**
   * String that corresponds to the raw format style.
   */
  private final static String RAW_FORMAT_STYLE = "raw";
  
  /**
   * Creates a LocaleMessageFormat with the given pattern and uses it
   * to format the given arguments. This is equivalent to
   * <blockquote>
   *     <code>(new {@link #LocaleMessageFormat(String) MessageFormat}(pattern)).{@link #format(java.lang.Object[], java.lang.StringBuffer, java.text.FieldPosition) format}(arguments, new StringBuffer(), null).toString()</code>
   * </blockquote>
   * 
   * @exception IllegalArgumentException if the pattern is invalid,
   *            or if an argument in the <code>arguments</code> array
   *            is not of the type expected by the format element(s)
   *            that use it.
   */
  public static String format(final String pattern, final Object... arguments) {
    String _xblockexpression = null;
    {
      LocaleMessageFormat temp = new LocaleMessageFormat(pattern);
      _xblockexpression = temp.format(arguments);
    }
    return _xblockexpression;
  }
  
  @Override
  public void applyPattern(final String pattern) {
    super.applyPattern(pattern);
    Format[] formats = this.getFormats();
    boolean changed = false;
    for (int i = 0; (i < formats.length); i++) {
      {
        Format df = this.getFormats()[i];
        if ((df instanceof DecimalFormat)) {
          boolean _equalsIgnoreCase = LocaleMessageFormat.RAW_FORMAT_STYLE.equalsIgnoreCase(((DecimalFormat)df).getPositivePrefix());
          if (_equalsIgnoreCase) {
            int _groupingSize = ((DecimalFormat)df).getGroupingSize();
            int _minimumIntegerDigits = ((DecimalFormat)df).getMinimumIntegerDigits();
            int _maximumIntegerDigits = ((DecimalFormat)df).getMaximumIntegerDigits();
            int _minimumFractionDigits = ((DecimalFormat)df).getMinimumFractionDigits();
            int _maximumFractionDigits = ((DecimalFormat)df).getMaximumFractionDigits();
            RoundingMode _roundingMode = ((DecimalFormat)df).getRoundingMode();
            LocaleMessageFormat.RawNumberFormat _rawNumberFormat = new LocaleMessageFormat.RawNumberFormat(pattern, _groupingSize, _minimumIntegerDigits, _maximumIntegerDigits, _minimumFractionDigits, _maximumFractionDigits, _roundingMode);
            this.getFormats()[i] = _rawNumberFormat;
            changed = true;
          }
        }
      }
    }
    if (changed) {
      this.setFormats(formats);
    }
  }
  
  @Override
  @Pure
  @SyntheticMember
  public LocaleMessageFormat clone() {
    try {
      return (LocaleMessageFormat) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  public LocaleMessageFormat(final String pattern) {
    super(pattern);
  }
  
  @SyntheticMember
  public LocaleMessageFormat(final String pattern, final Locale locale) {
    super(pattern, locale);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -3539274853L;
}
