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
import framework.environment.AbstractMobileObject;
import framework.environment.Body;
import framework.environment.DynamicType;
import framework.environment.Frustum;
import framework.environment.Influence;
import framework.environment.MotionInfluence;
import framework.environment.Percept;
import framework.util.LocalizedString;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.arakhne.afc.math.MathUtil;
import org.arakhne.afc.math.geometry.d2.d.Shape2d;
import org.arakhne.afc.math.geometry.d2.d.Vector2d;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.StringExtensions;

/**
 * Object on the environment.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public class AgentBody extends AbstractMobileObject implements Body {
  private final Frustum frustum;
  
  private transient MotionInfluence motionInfluence;
  
  private transient List<Influence> otherInfluences = new ArrayList<Influence>();
  
  private transient List<Percept> perceptions = new ArrayList<Percept>();
  
  /**
   * @param id
   * @param shape the shape of the body, considering that it is centered at the (0,0) position.
   * @param maxLinearSpeed is the maximal linear speed.
   * @param maxLinearAcceleration is the maximal linear acceleration.
   * @param maxAngularSpeed is the maximal angular speed.
   * @param maxAngularAcceleration is the maximal angular acceleration.
   * @param frustum the field-of-view associated to the body.
   */
  public AgentBody(final UUID id, final Shape2d<?> shape, final double maxLinearSpeed, final double maxLinearAcceleration, final double maxAngularSpeed, final double maxAngularAcceleration, final Frustum frustum) {
    super(id, shape, maxLinearSpeed, maxLinearAcceleration, maxAngularSpeed, maxAngularAcceleration);
    this.frustum = frustum;
    this.setType("BODY");
  }
  
  @Override
  @Pure
  public AgentBody clone() {
    AbstractMobileObject _clone = super.clone();
    AgentBody c = ((AgentBody) _clone);
    c.motionInfluence = null;
    ArrayList<Influence> _arrayList = new ArrayList<Influence>();
    c.otherInfluences = _arrayList;
    ArrayList<Percept> _arrayList_1 = new ArrayList<Percept>();
    c.perceptions = _arrayList_1;
    return c;
  }
  
  @Override
  @Pure
  public String toString() {
    String label = LocalizedString.getString(this.getClass(), "BODY_OF", this.getID());
    String name = this.getName();
    boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(name);
    if (_isNullOrEmpty) {
      return (((name + "(") + label) + ")");
    }
    return label;
  }
  
  /**
   * Replies the frustum associated to this body.
   * 
   * @return the frustum.
   */
  @Pure
  public Frustum getFrustum() {
    return this.frustum;
  }
  
  /**
   * Invoked to send the given influence to the environment.
   * 
   * @param influence the influence.
   */
  public Boolean influence(final Influence influence) {
    boolean _xifexpression = false;
    if ((influence instanceof MotionInfluence)) {
      boolean _xifexpression_1 = false;
      if (((((MotionInfluence)influence).getInfluencedObject() == null) || Objects.equal(((MotionInfluence)influence).getInfluencedObject(), this.getID()))) {
        DynamicType _type = ((MotionInfluence)influence).getType();
        if (_type != null) {
          switch (_type) {
            case KINEMATIC:
              this.influenceKinematic(((MotionInfluence)influence).getLinearInfluence(), ((MotionInfluence)influence).getAngularInfluence());
              break;
            case STEERING:
              this.influenceSteering(((MotionInfluence)influence).getLinearInfluence(), ((MotionInfluence)influence).getAngularInfluence());
              break;
            default:
              break;
          }
        }
      } else {
        _xifexpression_1 = this.otherInfluences.add(((MotionInfluence)influence));
      }
      _xifexpression = _xifexpression_1;
    } else {
      boolean _xifexpression_2 = false;
      if ((influence != null)) {
        _xifexpression_2 = this.otherInfluences.add(influence);
      }
      _xifexpression = _xifexpression_2;
    }
    return Boolean.valueOf(_xifexpression);
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueSource
  public void influenceKinematic(@DefaultValue("framework.environment.AgentBody#INFLUENCEKINEMATIC_0") final Vector2d linearInfluence, @DefaultValue("framework.environment.AgentBody#INFLUENCEKINEMATIC_1") final double angularInfluence) {
    Vector2d li = null;
    if ((linearInfluence != null)) {
      li = linearInfluence.clone();
      double nSpeed = li.getLength();
      double _maxLinearSpeed = this.getMaxLinearSpeed();
      boolean _greaterThan = (nSpeed > _maxLinearSpeed);
      if (_greaterThan) {
        li.setLength(this.getMaxLinearSpeed());
      }
    }
    double _maxAngularSpeed = this.getMaxAngularSpeed();
    double _minus = (-_maxAngularSpeed);
    double ai = MathUtil.clamp(angularInfluence, _minus, this.getMaxAngularSpeed());
    UUID _iD = this.getID();
    MotionInfluence _motionInfluence = new MotionInfluence(DynamicType.KINEMATIC, _iD, li, ai);
    this.motionInfluence = _motionInfluence;
  }
  
  /**
   * Default value for the parameter linearInfluence
   */
  @SyntheticMember
  @SarlSourceCode("null")
  private final static Vector2d $DEFAULT_VALUE$INFLUENCEKINEMATIC_0 = null;
  
  /**
   * Default value for the parameter angularInfluence
   */
  @SyntheticMember
  @SarlSourceCode("0")
  private final static double $DEFAULT_VALUE$INFLUENCEKINEMATIC_1 = 0;
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueSource
  public void influenceSteering(@DefaultValue("framework.environment.AgentBody#INFLUENCESTEERING_0") final Vector2d linearInfluence, @DefaultValue("framework.environment.AgentBody#INFLUENCESTEERING_1") final double angularInfluence) {
    Vector2d li = null;
    if ((linearInfluence != null)) {
      li = linearInfluence.clone();
      double nSpeed = li.getLength();
      double _maxLinearAcceleration = this.getMaxLinearAcceleration();
      boolean _greaterThan = (nSpeed > _maxLinearAcceleration);
      if (_greaterThan) {
        li.setLength(this.getMaxLinearAcceleration());
      }
    }
    double _maxAngularAcceleration = this.getMaxAngularAcceleration();
    double _minus = (-_maxAngularAcceleration);
    double ai = MathUtil.clamp(angularInfluence, _minus, this.getMaxAngularAcceleration());
    UUID _iD = this.getID();
    MotionInfluence _motionInfluence = new MotionInfluence(DynamicType.STEERING, _iD, li, ai);
    this.motionInfluence = _motionInfluence;
  }
  
  /**
   * Default value for the parameter linearInfluence
   */
  @SyntheticMember
  @SarlSourceCode("null")
  private final static Vector2d $DEFAULT_VALUE$INFLUENCESTEERING_0 = null;
  
  /**
   * Default value for the parameter angularInfluence
   */
  @SyntheticMember
  @SarlSourceCode("0")
  private final static double $DEFAULT_VALUE$INFLUENCESTEERING_1 = 0;
  
  /**
   * Replies all the perceived objects.
   * 
   * @return the perceived objects.
   */
  @Pure
  public List<Percept> getPerceivedObjects() {
    return this.perceptions;
  }
  
  /**
   * Replies the influence.
   * 
   * @return the influence.
   */
  List<Influence> consumeOtherInfluences() {
    List<Influence> _xblockexpression = null;
    {
      List<Influence> otherInfluences = this.otherInfluences;
      ArrayList<Influence> _arrayList = new ArrayList<Influence>();
      this.otherInfluences = _arrayList;
      for (final Influence i : otherInfluences) {
        if (i!=null) {
          i.setEmitter(this.getID());
        }
      }
      _xblockexpression = otherInfluences;
    }
    return _xblockexpression;
  }
  
  /**
   * Replies the influence.
   * 
   * @return the influence.
   */
  MotionInfluence consumeMotionInfluence() {
    MotionInfluence _xblockexpression = null;
    {
      MotionInfluence mi = this.motionInfluence;
      this.motionInfluence = null;
      if (mi!=null) {
        mi.setEmitter(this.getID());
      }
      _xblockexpression = mi;
    }
    return _xblockexpression;
  }
  
  /**
   * Set the perceptions.
   * 
   * @param perceptions
   */
  void setPerceptions(final List<Percept> perceptions) {
    this.perceptions = perceptions;
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("org.arakhne.afc.math.geometry.d2.d.Vector2d,double")
  @SyntheticMember
  public final void influenceKinematic() {
    influenceKinematic($DEFAULT_VALUE$INFLUENCEKINEMATIC_0, $DEFAULT_VALUE$INFLUENCEKINEMATIC_1);
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("org.arakhne.afc.math.geometry.d2.d.Vector2d,double")
  @SyntheticMember
  public final void influenceKinematic(final double angularInfluence) {
    influenceKinematic($DEFAULT_VALUE$INFLUENCEKINEMATIC_0, angularInfluence);
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("org.arakhne.afc.math.geometry.d2.d.Vector2d,double")
  @SyntheticMember
  public final void influenceKinematic(final Vector2d linearInfluence) {
    influenceKinematic(linearInfluence, $DEFAULT_VALUE$INFLUENCEKINEMATIC_1);
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("org.arakhne.afc.math.geometry.d2.d.Vector2d,double")
  @SyntheticMember
  public final void influenceSteering() {
    influenceSteering($DEFAULT_VALUE$INFLUENCESTEERING_0, $DEFAULT_VALUE$INFLUENCESTEERING_1);
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("org.arakhne.afc.math.geometry.d2.d.Vector2d,double")
  @SyntheticMember
  public final void influenceSteering(final double angularInfluence) {
    influenceSteering($DEFAULT_VALUE$INFLUENCESTEERING_0, angularInfluence);
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("org.arakhne.afc.math.geometry.d2.d.Vector2d,double")
  @SyntheticMember
  public final void influenceSteering(final Vector2d linearInfluence) {
    influenceSteering(linearInfluence, $DEFAULT_VALUE$INFLUENCESTEERING_1);
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
    AgentBody other = (AgentBody) obj;
    if (this.frustum == null) {
      if (other.frustum != null)
        return false;
    } else if (!this.frustum.equals(other.frustum))
      return false;
    if (this.motionInfluence == null) {
      if (other.motionInfluence != null)
        return false;
    } else if (!this.motionInfluence.equals(other.motionInfluence))
      return false;
    if (this.otherInfluences == null) {
      if (other.otherInfluences != null)
        return false;
    } else if (!this.otherInfluences.equals(other.otherInfluences))
      return false;
    if (this.perceptions == null) {
      if (other.perceptions != null)
        return false;
    } else if (!this.perceptions.equals(other.perceptions))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.frustum== null) ? 0 : this.frustum.hashCode());
    result = prime * result + ((this.motionInfluence== null) ? 0 : this.motionInfluence.hashCode());
    result = prime * result + ((this.otherInfluences== null) ? 0 : this.otherInfluences.hashCode());
    result = prime * result + ((this.perceptions== null) ? 0 : this.perceptions.hashCode());
    return result;
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -3607667384L;
}
