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

import framework.environment.Environment;
import framework.environment.SituatedObject;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * State of the world model.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public class WorldModelState {
  private final List<SituatedObject> objects;
  
  /**
   * @param environment
   */
  public WorldModelState(final Environment environment) {
    ArrayList<SituatedObject> _arrayList = new ArrayList<SituatedObject>();
    this.objects = _arrayList;
    Iterable<? extends SituatedObject> _allObjects = environment.getAllObjects();
    for (final SituatedObject o : _allObjects) {
      SituatedObject _clone = o.clone();
      this.objects.add(_clone);
    }
  }
  
  /**
   * Replies the objects in the environment.
   * 
   * @return the objects in the environment.
   */
  @Pure
  public Iterable<SituatedObject> getObjects() {
    return this.objects;
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
    WorldModelState other = (WorldModelState) obj;
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
    result = prime * result + ((this.objects== null) ? 0 : this.objects.hashCode());
    return result;
  }
}
