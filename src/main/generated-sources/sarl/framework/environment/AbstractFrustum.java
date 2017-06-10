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
package framework.environment;

import com.google.common.base.Objects;
import framework.environment.Frustum;
import framework.environment.SituatedObject;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Abstract implementation of a field-of-view.
 * This implementation provides the default filtering behavior: remove the perceiver's body
 * from the perception.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public abstract class AbstractFrustum implements Frustum {
  /**
   * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
   * @version $Name$ $Revision$ $Date$
   */
  @SarlSpecification("0.5")
  private static class PerceiverBodyFilter<D extends SituatedObject> implements Iterator<D> {
    private final AbstractFrustum enclosing;
    
    private final Iterator<D> original;
    
    private D next;
    
    public PerceiverBodyFilter(final AbstractFrustum enclosing, final Iterator<D> original) {
      this.enclosing = enclosing;
      this.original = original;
      this.searchNext();
    }
    
    private void searchNext() {
      this.next = null;
      while (((this.next == null) && this.original.hasNext())) {
        {
          D candidate = this.original.next();
          UUID _iD = candidate.getID();
          boolean _notEquals = (!Objects.equal(_iD, this.enclosing.owner));
          if (_notEquals) {
            this.next = candidate;
          }
        }
      }
    }
    
    @Override
    @Pure
    public boolean hasNext() {
      return (this.next != null);
    }
    
    @Override
    public D next() {
      D n = this.next;
      if ((n == null)) {
        throw new NoSuchElementException();
      }
      this.searchNext();
      return n;
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
      PerceiverBodyFilter other = (PerceiverBodyFilter) obj;
      if (this.enclosing == null) {
        if (other.enclosing != null)
          return false;
      } else if (!this.enclosing.equals(other.enclosing))
        return false;
      if (this.original == null) {
        if (other.original != null)
          return false;
      } else if (!this.original.equals(other.original))
        return false;
      if (this.next == null) {
        if (other.next != null)
          return false;
      } else if (!this.next.equals(other.next))
        return false;
      return super.equals(obj);
    }
    
    @Override
    @Pure
    @SyntheticMember
    public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + ((this.enclosing== null) ? 0 : this.enclosing.hashCode());
      result = prime * result + ((this.original== null) ? 0 : this.original.hashCode());
      result = prime * result + ((this.next== null) ? 0 : this.next.hashCode());
      return result;
    }
  }
  
  private final UUID owner;
  
  /**
   * @param owner the identifier of the owner of this frustum.
   */
  public AbstractFrustum(final UUID owner) {
    this.owner = owner;
  }
  
  @Override
  @Pure
  public UUID getOwner() {
    return this.owner;
  }
  
  @Override
  public <D extends SituatedObject> Iterator<D> filter(final Iterator<D> iterator) {
    return new AbstractFrustum.PerceiverBodyFilter<D>(this, iterator);
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
    AbstractFrustum other = (AbstractFrustum) obj;
    if (this.owner == null) {
      if (other.owner != null)
        return false;
    } else if (!this.owner.equals(other.owner))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.owner== null) ? 0 : this.owner.hashCode());
    return result;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public AbstractFrustum clone() {
    try {
      return (AbstractFrustum) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 895535452L;
}
