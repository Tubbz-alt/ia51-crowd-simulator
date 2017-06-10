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

import framework.environment.DynamicType;
import framework.environment.Influence;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.UUID;
import org.arakhne.afc.math.geometry.d2.d.Vector2d;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Abstract implementation of a motion influence.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public class MotionInfluence extends Influence {
  private final DynamicType type;
  
  private final Vector2d linearInfluence = new Vector2d();
  
  private double angularInfluence;
  
  /**
   * @param type indicates if the influence is kinematic or steering.
   * @param influencedObject is the influenced object.
   * @param linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueSource
  public MotionInfluence(final DynamicType type, @DefaultValue("framework.environment.MotionInfluence#NEW_0") final UUID influencedObject, @DefaultValue("framework.environment.MotionInfluence#NEW_1") final Vector2d linearInfluence, @DefaultValue("framework.environment.MotionInfluence#NEW_2") final double angularInfluence) {
    super(influencedObject);
    this.type = type;
    if ((linearInfluence != null)) {
      this.linearInfluence.set(linearInfluence);
    }
    this.angularInfluence = angularInfluence;
  }
  
  /**
   * Default value for the parameter influencedObject
   */
  @SyntheticMember
  @SarlSourceCode("null")
  private final static UUID $DEFAULT_VALUE$NEW_0 = null;
  
  /**
   * Default value for the parameter linearInfluence
   */
  @SyntheticMember
  @SarlSourceCode("null")
  private final static Vector2d $DEFAULT_VALUE$NEW_1 = null;
  
  /**
   * Default value for the parameter angularInfluence
   */
  @SyntheticMember
  @SarlSourceCode("0")
  private final static double $DEFAULT_VALUE$NEW_2 = 0;
  
  /**
   * Set the linear influence.
   * 
   * @param l is the linear influence
   */
  public void setLinarInfluence(final Vector2d l) {
    this.linearInfluence.set(l);
  }
  
  /**
   * Set the linear influence.
   * 
   * @param dx is the linear influence
   * @param dy is the linear influence
   */
  public void setLinarInfluence(final double dx, final double dy) {
    this.linearInfluence.set(dx, dy);
  }
  
  /**
   * Set the angular influence.
   * 
   * @param a
   */
  public double setAngularInfluence(final double a) {
    return this.angularInfluence = a;
  }
  
  /**
   * Replies the linear influence.
   * 
   * @return the linear influence
   */
  @Pure
  public Vector2d getLinearInfluence() {
    return this.linearInfluence;
  }
  
  /**
   * Replies the angular influence.
   * 
   * @return the angular influence
   */
  @Pure
  public double getAngularInfluence() {
    return this.angularInfluence;
  }
  
  /**
   * Replies the type of the influence.
   * 
   * @return the type of the influence.
   */
  @Pure
  public DynamicType getType() {
    return this.type;
  }
  
  @Override
  @Pure
  public String toString() {
    String _plus = (this.linearInfluence + "|");
    return (_plus + Double.valueOf(this.angularInfluence));
  }
  
  /**
   * @optionalparam type indicates if the influence is kinematic or steering.
   * @optionalparam influencedObject is the influenced object.
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("framework.environment.DynamicType,java.util.UUID,org.arakhne.afc.math.geometry.d2.d.Vector2d,double")
  @SyntheticMember
  public MotionInfluence(final DynamicType type) {
    this(type, $DEFAULT_VALUE$NEW_0, $DEFAULT_VALUE$NEW_1, $DEFAULT_VALUE$NEW_2);
  }
  
  /**
   * @optionalparam type indicates if the influence is kinematic or steering.
   * @optionalparam influencedObject is the influenced object.
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("framework.environment.DynamicType,java.util.UUID,org.arakhne.afc.math.geometry.d2.d.Vector2d,double")
  @SyntheticMember
  public MotionInfluence(final DynamicType type, final double angularInfluence) {
    this(type, $DEFAULT_VALUE$NEW_0, $DEFAULT_VALUE$NEW_1, angularInfluence);
  }
  
  /**
   * @optionalparam type indicates if the influence is kinematic or steering.
   * @optionalparam influencedObject is the influenced object.
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("framework.environment.DynamicType,java.util.UUID,org.arakhne.afc.math.geometry.d2.d.Vector2d,double")
  @SyntheticMember
  public MotionInfluence(final DynamicType type, final UUID influencedObject) {
    this(type, influencedObject, $DEFAULT_VALUE$NEW_1, $DEFAULT_VALUE$NEW_2);
  }
  
  /**
   * @optionalparam type indicates if the influence is kinematic or steering.
   * @optionalparam influencedObject is the influenced object.
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("framework.environment.DynamicType,java.util.UUID,org.arakhne.afc.math.geometry.d2.d.Vector2d,double")
  @SyntheticMember
  public MotionInfluence(final DynamicType type, final Vector2d linearInfluence) {
    this(type, $DEFAULT_VALUE$NEW_0, linearInfluence, $DEFAULT_VALUE$NEW_2);
  }
  
  /**
   * @optionalparam type indicates if the influence is kinematic or steering.
   * @optionalparam influencedObject is the influenced object.
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("framework.environment.DynamicType,java.util.UUID,org.arakhne.afc.math.geometry.d2.d.Vector2d,double")
  @SyntheticMember
  public MotionInfluence(final DynamicType type, final UUID influencedObject, final double angularInfluence) {
    this(type, influencedObject, $DEFAULT_VALUE$NEW_1, angularInfluence);
  }
  
  /**
   * @optionalparam type indicates if the influence is kinematic or steering.
   * @optionalparam influencedObject is the influenced object.
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("framework.environment.DynamicType,java.util.UUID,org.arakhne.afc.math.geometry.d2.d.Vector2d,double")
  @SyntheticMember
  public MotionInfluence(final DynamicType type, final UUID influencedObject, final Vector2d linearInfluence) {
    this(type, influencedObject, linearInfluence, $DEFAULT_VALUE$NEW_2);
  }
  
  /**
   * @optionalparam type indicates if the influence is kinematic or steering.
   * @optionalparam influencedObject is the influenced object.
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("framework.environment.DynamicType,java.util.UUID,org.arakhne.afc.math.geometry.d2.d.Vector2d,double")
  @SyntheticMember
  public MotionInfluence(final DynamicType type, final Vector2d linearInfluence, final double angularInfluence) {
    this(type, $DEFAULT_VALUE$NEW_0, linearInfluence, angularInfluence);
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
    MotionInfluence other = (MotionInfluence) obj;
    if (this.type == null) {
      if (other.type != null)
        return false;
    } else if (!this.type.equals(other.type))
      return false;
    if (this.linearInfluence == null) {
      if (other.linearInfluence != null)
        return false;
    } else if (!this.linearInfluence.equals(other.linearInfluence))
      return false;
    if (Double.doubleToLongBits(other.angularInfluence) != Double.doubleToLongBits(this.angularInfluence))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.type== null) ? 0 : this.type.hashCode());
    result = prime * result + ((this.linearInfluence== null) ? 0 : this.linearInfluence.hashCode());
    result = prime * result + (int) (Double.doubleToLongBits(this.angularInfluence) ^ (Double.doubleToLongBits(this.angularInfluence) >>> 32));
    return result;
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 3765016963L;
}
