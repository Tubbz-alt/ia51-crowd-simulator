/**
 * $Id$
 * 
 * Copyright (c) 2014-17 Stephane GALLAND <stephane.galland@utbm.fr>.
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
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Scope;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * This scope is accepting the addresses that has the agent with the given identifier.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public class AddressUUIDScope implements Scope<Address> {
  private final static String SCOPE_ID = "uuid://";
  
  private UUID id;
  
  /**
   * @param id - identifier to put in the scope.
   */
  public AddressUUIDScope(final UUID id) {
    this.id = id;
  }
  
  @Override
  @Pure
  public String toString() {
    String _string = this.id.toString();
    return (AddressUUIDScope.SCOPE_ID + _string);
  }
  
  @Override
  public boolean matches(final Address address) {
    UUID _uUID = address.getUUID();
    return Objects.equal(this.id, _uUID);
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
    AddressUUIDScope other = (AddressUUIDScope) obj;
    if (this.id == null) {
      if (other.id != null)
        return false;
    } else if (!this.id.equals(other.id))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.id== null) ? 0 : this.id.hashCode());
    return result;
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 2282983073L;
}
