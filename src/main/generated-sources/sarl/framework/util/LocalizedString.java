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

import com.google.common.base.Objects;
import framework.util.LocaleMessageFormat;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * This utility class permits a easier use of localized strings.
 * <code>Locale</code> provides a means to retreive
 * messages in the default language. Use this to construct messages
 * displayed for end users.
 * <p>
 * <code>Locale</code> takes a string from a properties resource,
 * then inserts the parameter strings into the extracted strings
 * at the appropriate places.
 * The pattern matching is proceeded with {@link LocaleMessageFormat}
 * formatter. Note that <code>''</code> may represent a single quote
 * in strings (see {@link LocaleMessageFormat} for details).
 * 
 * FIXME: Does java.text.Normalizer may replace decodeString functions?
 * 
 * Copied from {@link https://github.com/gallandarakhneorg/afc/}.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @author Olivier LAMOTTE &lt;olivier.lamotte@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public class LocalizedString {
  /**
   * Replies the text that corresponds to the specified resource.
   * <p>
   * The <code>resourcePath</code> argument should be a fully
   * qualified class name. However, for compatibility with earlier
   * versions, Sun's Java SE Runtime Environments do not verify this,
   * and so it is possible to access <code>PropertyResourceBundle</code>s
   * by specifying a path name (using "/") instead of a fully
   * qualified class name (using ".").
   * 
   * @param resourcePath is the name (path) of the resource file, a fully qualified class name
   * @param key is the name of the resource into the specified file
   * @param defaultValue is the default value to replies if the resource does not contain the specified key.
   * @param params is the the list of parameters which will
   * replaces the <code>#1</code>, <code>#2</code>... into the string.
   * @return the text that corresponds to the specified resource
   */
  @Pure
  public static String getStringWithDefaultFrom(final String resourcePath, final String key, final String defaultValue, final Object... params) {
    return LocalizedString.getStringWithDefaultFrom(
      LocalizedString.class.getClassLoader(), resourcePath, key, defaultValue, params);
  }
  
  /**
   * Replies the text that corresponds to the specified resource.
   * <p>
   * The <code>resourcePath</code> argument should be a fully
   * qualified class name. However, for compatibility with earlier
   * versions, Sun's Java SE Runtime Environments do not verify this,
   * and so it is possible to access <code>PropertyResourceBundle</code>s
   * by specifying a path name (using "/") instead of a fully
   * qualified class name (using ".").
   * 
   * @param classLoader is the class loader to use, a fully qualified class name
   * @param resourcePath is the name (path) of the resource file
   * @param key is the name of the resource into the specified file
   * @param defaultValue is the default value to replies if the resource does not contain the specified key.
   * @param params is the the list of parameters which will
   * replaces the <code>#1</code>, <code>#2</code>... into the string.
   * @return the text that corresponds to the specified resource
   */
  @Pure
  public static String getStringWithDefaultFrom(final ClassLoader classLoader, final String resourcePath, final String key, final String defaultValue, final Object... params) {
    String _xblockexpression = null;
    {
      if ((resourcePath == null)) {
        return defaultValue;
      }
      ResourceBundle resource = null;
      try {
        resource = ResourceBundle.getBundle(resourcePath, 
          Locale.getDefault(), classLoader);
      } catch (final Throwable _t) {
        if (_t instanceof MissingResourceException) {
          final MissingResourceException exep = (MissingResourceException)_t;
          return defaultValue;
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
      String result = null;
      try {
        result = resource.getString(key);
      } catch (final Throwable _t_1) {
        if (_t_1 instanceof Exception) {
          final Exception e = (Exception)_t_1;
          return defaultValue;
        } else {
          throw Exceptions.sneakyThrow(_t_1);
        }
      }
      result = result.replaceAll("[\\n\\r]", "\n");
      result = result.replaceAll("\\t", "\t");
      _xblockexpression = LocaleMessageFormat.format(result, params);
    }
    return _xblockexpression;
  }
  
  /**
   * Replies the text that corresponds to the specified resource.
   * <p>
   * The <code>resourcePath</code> argument should be a fully
   * qualified class name. However, for compatibility with earlier
   * versions, Sun's Java SE Runtime Environments do not verify this,
   * and so it is possible to access <code>PropertyResourceBundle</code>s
   * by specifying a path name (using "/") instead of a fully
   * qualified class name (using ".").
   * 
   * @param resourcePath is the name (path) of the resource file, a fully qualified class name
   * @param key is the name of the resource into the specified file
   * @param params is the the list of parameters which will
   * replaces the <code>#1</code>, <code>#2</code>... into the string.
   * @return the text that corresponds to the specified resource
   */
  @Pure
  public static String getStringFrom(final String resourcePath, final String key, final Object... params) {
    return LocalizedString.getStringWithDefaultFrom(resourcePath, key, key, params);
  }
  
  /**
   * Replies the text that corresponds to the specified resource.
   * <p>
   * The <code>resourcePath</code> argument should be a fully
   * qualified class name. However, for compatibility with earlier
   * versions, Sun's Java SE Runtime Environments do not verify this,
   * and so it is possible to access <code>PropertyResourceBundle</code>s
   * by specifying a path name (using "/") instead of a fully
   * qualified class name (using ".").
   * 
   * @param classLoader is the classLoader to use.
   * @param resourcePath is the name (path) of the resource file, a fully qualified class name
   * @param key is the name of the resource into the specified file
   * @param params is the the list of parameters which will
   * replaces the <code>#1</code>, <code>#2</code>... into the string.
   * @return the text that corresponds to the specified resource
   */
  @Pure
  public static String getStringFrom(final ClassLoader classLoader, final String resourcePath, final String key, final Object... params) {
    return LocalizedString.getStringWithDefaultFrom(classLoader, resourcePath, key, key, params);
  }
  
  /**
   * Replies the text that corresponds to the specified resource.
   * 
   * @param resource is the name of the resource file
   * @param key is the name of the resource into the specified file
   * @param params is the the list of parameters which will
   * replaces the <code>#1</code>, <code>#2</code>... into the string.
   * @return the text that corresponds to the specified resource
   */
  @Pure
  public static String getString(final Class<?> resource, final String key, final Object... params) {
    return LocalizedString.getString(resource.getClassLoader(), resource, key, params);
  }
  
  /**
   * Replies the text that corresponds to the specified resource.
   * 
   * @param classLoader is the class loader to use.
   * @param resource is the name of the resource file
   * @param key is the name of the resource into the specified file
   * @param params is the the list of parameters which will
   * replaces the <code>#1</code>, <code>#2</code>... into the string.
   * @return the text that corresponds to the specified resource
   */
  @Pure
  public static String getString(final ClassLoader classLoader, final Class<?> resource, final String key, final Object... params) {
    Class<?> res = resource;
    if ((res == null)) {
      return key;
    }
    String val = LocalizedString.getStringWithDefaultFrom(classLoader, res.getCanonicalName(), key, null, params);
    if (((val == null) && (!Objects.equal(classLoader, res.getClassLoader())))) {
      val = LocalizedString.getStringWithDefaultFrom(classLoader, res.getCanonicalName(), key, null, params);
    }
    while (((res != null) && (val == null))) {
      {
        res = res.getSuperclass();
        if ((res != null)) {
          val = LocalizedString.getStringWithDefaultFrom(classLoader, res.getCanonicalName(), key, null, params);
        }
      }
    }
    if ((val == null)) {
      return key;
    }
    return val;
  }
  
  /**
   * Replies the text that corresponds to the specified resource.
   * 
   * @param resource is the name of the resource file
   * @param key is the name of the resource into the specified file
   * @param defaultValue is the default value to replies if the resource does not contain the specified key.
   * @param params is the the list of parameters which will
   * replaces the <code>#1</code>, <code>#2</code>... into the string.
   * @return the text that corresponds to the specified resource
   */
  @Pure
  public static String getStringWithDefault(final Class<?> resource, final String key, final String defaultValue, final Object... params) {
    return LocalizedString.getStringWithDefault(resource.getClassLoader(), resource, key, defaultValue, params);
  }
  
  /**
   * Replies the text that corresponds to the specified resource.
   * 
   * @param classLoader is the class loader to use.
   * @param resource is the name of the resource file
   * @param key is the name of the resource into the specified file
   * @param defaultValue is the default value to replies if the resource does not contain the specified key.
   * @param params is the the list of parameters which will
   * replaces the <code>#1</code>, <code>#2</code>... into the string.
   * @return the text that corresponds to the specified resource
   */
  @Pure
  public static String getStringWithDefault(final ClassLoader classLoader, final Class<?> resource, final String key, final String defaultValue, final Object... params) {
    Class<?> res = resource;
    if ((res == null)) {
      return defaultValue;
    }
    String val = LocalizedString.getStringWithDefaultFrom(classLoader, res.getCanonicalName(), key, null, params);
    if (((val == null) && (!Objects.equal(classLoader, res.getClassLoader())))) {
      val = LocalizedString.getStringWithDefaultFrom(classLoader, res.getCanonicalName(), key, null, params);
    }
    while (((res != null) && (val == null))) {
      {
        res = res.getSuperclass();
        if ((res != null)) {
          val = LocalizedString.getStringWithDefaultFrom(classLoader, resource.getCanonicalName(), key, null, params);
        }
      }
    }
    if ((val == null)) {
      return defaultValue;
    }
    return val;
  }
  
  /**
   * Decode the specified array of bytes according to
   * a charset selection. This function tries
   * to decode a string from the given byte array
   * with the following charsets (in preferred order):
   * <ul>
   * <li>the current charset returned by {@link Charset#defaultCharset()},</li>
   * <li>OEM United States: IBM437,</li>
   * <li>West European: ISO-8859-1,</li>
   * <li>one of the charst returned by {@link Charset#availableCharsets()}.</li>
   * </ul>
   * <p>
   * The IBM437 charset was added to support several specific files (Dbase files)
   * generated from a GIS.
   * 
   * @param bytes is the array of bytes to decode.
   * @return the decoded string with the appropriate charset set.
   */
  public static String decodeString(final byte[] bytes) {
    Charset default_charset = Charset.defaultCharset();
    Charset west_european = Charset.forName("ISO-8859-1");
    Charset utf = Charset.forName("UTF-8");
    String refBuffer = new String(bytes);
    CharBuffer buffer = LocalizedString.decodeString(bytes, default_charset, refBuffer.length());
    if (((buffer == null) && (!Objects.equal(default_charset, west_european)))) {
      buffer = LocalizedString.decodeString(bytes, west_european, refBuffer.length());
    }
    if (((buffer == null) && (!Objects.equal(default_charset, utf)))) {
      buffer = LocalizedString.decodeString(bytes, utf, refBuffer.length());
    }
    if ((buffer == null)) {
      Collection<Charset> _values = Charset.availableCharsets().values();
      for (final Charset charset : _values) {
        {
          buffer = LocalizedString.decodeString(bytes, charset, refBuffer.length());
          if ((buffer != null)) {
            return buffer.toString();
          }
        }
      }
    }
    return refBuffer;
  }
  
  /**
   * Decode the specified array of bytes with the specified charset.
   * 
   * @param bytes is the array of bytes to decode.
   * @param charset is the charset to use for decoding
   * @param referenceLength is the length of the attempted result. If negative, this parameter is ignored.
   * @return the decoded string with the appropriate charset set,
   * or <code>null</code> if the specified charset cannot be
   * used to decode all the characters inside the byte array.
   */
  private static CharBuffer decodeString(final byte[] bytes, final Charset charset, final int referenceLength) {
    try {
      Charset autodetected_charset = charset;
      CharsetDecoder decoder = charset.newDecoder();
      CharBuffer buffer = decoder.decode(ByteBuffer.wrap(bytes));
      if ((decoder.isAutoDetecting() && decoder.isCharsetDetected())) {
        autodetected_charset = decoder.detectedCharset();
        boolean _contains = charset.contains(autodetected_charset);
        if (_contains) {
          buffer.position(0);
          if (((referenceLength >= 0) && (buffer.remaining() == referenceLength))) {
            return buffer;
          }
          return null;
        }
      }
      buffer.position(0);
      char c = 0;
      int type = 0;
      while (buffer.hasRemaining()) {
        {
          c = buffer.get();
          type = Character.getType(c);
          switch (type) {
            case Character.UNASSIGNED:
            case Character.CONTROL:
            case Character.FORMAT:
            case Character.PRIVATE_USE:
            case Character.SURROGATE:
              return null;
          }
        }
      }
      buffer.position(0);
      if (((referenceLength >= 0) && (buffer.remaining() == referenceLength))) {
        return buffer;
      }
    } catch (final Throwable _t) {
      if (_t instanceof CharacterCodingException) {
        final CharacterCodingException e = (CharacterCodingException)_t;
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    return null;
  }
  
  /**
   * Decode the bytes from the specified input stream
   * according to a charset selection. This function tries
   * to decode a string from the given byte array
   * with the following charsets (in preferred order):
   * <ul>
   * <li>the current charset returned by {@link Charset#defaultCharset()},</li>
   * <li>OEM United States: IBM437,</li>
   * <li>West European: ISO-8859-1,</li>
   * <li>one of the charst returned by {@link Charset#availableCharsets()}.</li>
   * </ul>
   * <p>
   * The IBM437 charset was added to support several specific files (Dbase files)
   * generated from a GIS.
   * 
   * @param stream is the stream to decode.
   * @return the decoded string with the appropriate charset set.
   * @throws IOException
   */
  public static String decodeString(final InputStream stream) {
    try {
      String _xblockexpression = null;
      {
        Object _newInstance = Array.newInstance(byte.class, 0);
        byte[] complete_buffer = ((byte[]) _newInstance);
        Object _newInstance_1 = Array.newInstance(byte.class, 2048);
        byte[] buffer = ((byte[]) _newInstance_1);
        int read = 0;
        while (((read = stream.read(buffer)) > 0)) {
          {
            int _length = complete_buffer.length;
            int _plus = (_length + read);
            Object _newInstance_2 = Array.newInstance(byte.class, _plus);
            byte[] tmp = ((byte[]) _newInstance_2);
            System.arraycopy(complete_buffer, 0, tmp, 0, complete_buffer.length);
            System.arraycopy(buffer, 0, tmp, complete_buffer.length, read);
            complete_buffer = tmp;
            tmp = null;
          }
        }
        _xblockexpression = LocalizedString.decodeString(complete_buffer);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Decode the bytes from the specified input stream
   * according to a charset selection. This function tries
   * to decode a string from the given byte array
   * with the following charsets (in preferred order):
   * <ul>
   * <li>the current charset returned by {@link Charset#defaultCharset()},</li>
   * <li>OEM United States: IBM437,</li>
   * <li>West European: ISO-8859-1,</li>
   * <li>one of the charst returned by {@link Charset#availableCharsets()}.</li>
   * </ul>
   * <p>
   * The IBM437 charset was added to support several specific files (Dbase files)
   * generated from a GIS.
   * <p>
   * This function read the input stream line by line.
   * 
   * @param stream is the stream to decode.
   * @param lineArray is the array of lines to fill
   * @return <code>true</code> is the decoding was successful,
   * otherwhise <code>false</code>
   * @throws IOException
   */
  public static boolean decodeString(final InputStream stream, final List<String> lineArray) {
    try {
      Object _newInstance = Array.newInstance(byte.class, 0);
      byte[] complete_buffer = ((byte[]) _newInstance);
      Object _newInstance_1 = Array.newInstance(byte.class, 2048);
      byte[] buffer = ((byte[]) _newInstance_1);
      int read = 0;
      while (((read = stream.read(buffer)) > 0)) {
        {
          int _length = complete_buffer.length;
          int _plus = (_length + read);
          Object _newInstance_2 = Array.newInstance(byte.class, _plus);
          byte[] tmp = ((byte[]) _newInstance_2);
          System.arraycopy(complete_buffer, 0, tmp, 0, complete_buffer.length);
          System.arraycopy(buffer, 0, tmp, complete_buffer.length, read);
          complete_buffer = tmp;
          tmp = null;
        }
      }
      buffer = null;
      Charset west_european = Charset.forName("ISO-8859-1");
      Charset default_charset = Charset.defaultCharset();
      ByteArrayInputStream _byteArrayInputStream = new ByteArrayInputStream(complete_buffer);
      boolean ok = LocalizedString.decodeString(_byteArrayInputStream, lineArray, default_charset);
      if (((!ok) && (!Objects.equal(default_charset, west_european)))) {
        ByteArrayInputStream _byteArrayInputStream_1 = new ByteArrayInputStream(complete_buffer);
        ok = LocalizedString.decodeString(_byteArrayInputStream_1, lineArray, west_european);
      }
      if ((!ok)) {
        Set<Map.Entry<String, Charset>> _entrySet = Charset.availableCharsets().entrySet();
        for (final Map.Entry<String, Charset> charset : _entrySet) {
          ByteArrayInputStream _byteArrayInputStream_2 = new ByteArrayInputStream(complete_buffer);
          boolean _decodeString = LocalizedString.decodeString(_byteArrayInputStream_2, lineArray, charset.getValue());
          if (_decodeString) {
            complete_buffer = null;
            return true;
          }
        }
      }
      complete_buffer = null;
      return ok;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Decode the bytes from the specified input stream
   * according to a charset selection. This function tries
   * to decode a string from the given byte array
   * with the following charsets (in preferred order):
   * <p>
   * This function read the input stream line by line.
   * 
   * @param stream is the stream to decode.
   * @param lineArray is the array of lines to fill.
   * @param charset is the charset to use.
   * @return <code>true</code> is the decoding was successful,
   * otherwhise <code>false</code>
   * @throws IOException
   */
  private static boolean decodeString(final InputStream stream, final List<String> lineArray, final Charset charset) {
    try {
      try {
        CharsetDecoder _newDecoder = charset.newDecoder();
        InputStreamReader _inputStreamReader = new InputStreamReader(stream, _newDecoder);
        BufferedReader breader = new BufferedReader(_inputStreamReader);
        lineArray.clear();
        String line = null;
        while (((line = breader.readLine()) != null)) {
          lineArray.add(line);
        }
        return true;
      } catch (final Throwable _t) {
        if (_t instanceof CharacterCodingException) {
          final CharacterCodingException e = (CharacterCodingException)_t;
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
      return false;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @SyntheticMember
  public LocalizedString() {
    super();
  }
}
