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
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Utilities on collections.
 * 
 * @author $Author: galland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public final class CollectionUtil {
  /**
   * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
   * @version $Name$ $Revision$ $Date$
   */
  @SarlSpecification("0.5")
  private static class Iterable1<T extends Object> implements Iterable<T> {
    /**
     * @author $Author: galland$
     * @version $FullVersion$
     * @mavengroupid $GroupId$
     * @mavenartifactid $ArtifactId$
     */
    @SarlSpecification("0.5")
    private static class Iterator1<T extends Object> implements Iterator<T> {
      private final Iterator<? extends T> iterator;
      
      private final T[] objects;
      
      private int index = 0;
      
      /**
       * @param iterable - elements.
       * @param objects - elements.
       */
      public Iterator1(final Iterable<? extends T> iterable, final T... objects) {
        this.iterator = iterable.iterator();
        this.objects = objects;
      }
      
      @Override
      @Pure
      public boolean hasNext() {
        return (this.iterator.hasNext() || (this.index < this.objects.length));
      }
      
      @Override
      public T next() {
        boolean _hasNext = this.iterator.hasNext();
        if (_hasNext) {
          return this.iterator.next();
        }
        int _length = this.objects.length;
        boolean _lessThan = (this.index < _length);
        if (_lessThan) {
          T n = this.objects[this.index];
          this.index++;
          return n;
        }
        throw new NoSuchElementException();
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
        Iterator1 other = (Iterator1) obj;
        if (this.iterator == null) {
          if (other.iterator != null)
            return false;
        } else if (!this.iterator.equals(other.iterator))
          return false;
        if (this.objects == null) {
          if (other.objects != null)
            return false;
        } else if (!this.objects.equals(other.objects))
          return false;
        if (other.index != this.index)
          return false;
        return super.equals(obj);
      }
      
      @Override
      @Pure
      @SyntheticMember
      public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.iterator== null) ? 0 : this.iterator.hashCode());
        result = prime * result + ((this.objects== null) ? 0 : this.objects.hashCode());
        result = prime * result + this.index;
        return result;
      }
    }
    
    private final Iterable<? extends T> iterable;
    
    private final T[] objects;
    
    /**
     * @param iterable - elements.
     * @param objects - elements.
     */
    public Iterable1(final Iterable<? extends T> iterable, final T... objects) {
      this.iterable = iterable;
      this.objects = objects;
    }
    
    @Override
    @Pure
    public Iterator<T> iterator() {
      return new CollectionUtil.Iterable1.Iterator1<T>(this.iterable, this.objects);
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
      Iterable1 other = (Iterable1) obj;
      if (this.iterable == null) {
        if (other.iterable != null)
          return false;
      } else if (!this.iterable.equals(other.iterable))
        return false;
      if (this.objects == null) {
        if (other.objects != null)
          return false;
      } else if (!this.objects.equals(other.objects))
        return false;
      return super.equals(obj);
    }
    
    @Override
    @Pure
    @SyntheticMember
    public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + ((this.iterable== null) ? 0 : this.iterable.hashCode());
      result = prime * result + ((this.objects== null) ? 0 : this.objects.hashCode());
      return result;
    }
  }
  
  /**
   * Create an iterable with the given elements.
   * 
   * @param iterable - elements.
   * @param objects - elements.
   * @return the iterable.
   */
  public static <T extends Object> Iterable<T> newIterable(final Iterable<? extends T> iterable, final T... objects) {
    return new CollectionUtil.Iterable1<T>(iterable, objects);
  }
  
  @SyntheticMember
  public CollectionUtil() {
    super();
  }
}
