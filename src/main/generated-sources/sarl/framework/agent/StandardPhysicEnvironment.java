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
package framework.agent;

import framework.agent.PhysicEnvironment;
import framework.environment.DynamicType;
import framework.environment.Influence;
import framework.environment.KillInfluence;
import framework.environment.MotionInfluence;
import framework.environment.events.InfluenceEvent;
import framework.util.AddressUUIDScope;
import io.sarl.core.Behaviors;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Skill;
import io.sarl.lang.util.ClearableReference;
import io.sarl.util.OpenEventSpace;
import java.lang.reflect.Array;
import java.util.List;
import java.util.UUID;
import org.arakhne.afc.math.geometry.d2.d.Vector2d;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.5")
@SuppressWarnings("all")
public class StandardPhysicEnvironment extends Skill implements PhysicEnvironment {
  private final UUID spaceID;
  
  private final UUID environmentID;
  
  private OpenEventSpace physicSpace;
  
  private Address myAdr;
  
  public StandardPhysicEnvironment(final UUID spaceID, final UUID environmentID) {
    this.environmentID = environmentID;
    this.spaceID = spaceID;
  }
  
  public void install() {
    do {
      {
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
        this.physicSpace = _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.getDefaultContext().<OpenEventSpace>getSpace(this.spaceID);
        Thread.yield();
      }
    } while((this.physicSpace == null));
    Behaviors _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER = this.$castSkill(Behaviors.class, (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = $getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
    this.physicSpace.register(_$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER.asEventListener());
    this.myAdr = this.physicSpace.getAddress(this.getOwner().getID());
  }
  
  public void uninstall() {
    KillInfluence _killInfluence = new KillInfluence();
    InfluenceEvent event = new InfluenceEvent(_killInfluence);
    event.setSource(this.myAdr);
    AddressUUIDScope _addressUUIDScope = new AddressUUIDScope(this.environmentID);
    this.physicSpace.emit(event, _addressUUIDScope);
    this.physicSpace = null;
  }
  
  @DefaultValueSource
  public void influenceKinematic(@DefaultValue("framework.agent.StandardPhysicEnvironment#INFLUENCEKINEMATIC_0") final Vector2d linearInfluence, @DefaultValue("framework.agent.StandardPhysicEnvironment#INFLUENCEKINEMATIC_1") final double angularInfluence, final Influence... otherInfluences) {
    MotionInfluence mi = null;
    if ((linearInfluence == null)) {
      MotionInfluence _motionInfluence = new MotionInfluence(DynamicType.KINEMATIC, angularInfluence);
      mi = _motionInfluence;
    } else {
      MotionInfluence _motionInfluence_1 = new MotionInfluence(DynamicType.KINEMATIC, linearInfluence, angularInfluence);
      mi = _motionInfluence_1;
    }
    this.emitInfluences(mi, otherInfluences);
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
  @SarlSourceCode("0.0")
  private final static double $DEFAULT_VALUE$INFLUENCEKINEMATIC_1 = 0.0;
  
  @DefaultValueSource
  public void influenceSteering(@DefaultValue("framework.agent.StandardPhysicEnvironment#INFLUENCESTEERING_0") final Vector2d linearInfluence, @DefaultValue("framework.agent.StandardPhysicEnvironment#INFLUENCESTEERING_1") final double angularInfluence, final Influence... otherInfluences) {
    MotionInfluence mi = null;
    if ((linearInfluence == null)) {
      MotionInfluence _motionInfluence = new MotionInfluence(DynamicType.STEERING, angularInfluence);
      mi = _motionInfluence;
    } else {
      MotionInfluence _motionInfluence_1 = new MotionInfluence(DynamicType.STEERING, linearInfluence, angularInfluence);
      mi = _motionInfluence_1;
    }
    this.emitInfluences(mi, otherInfluences);
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
  @SarlSourceCode("0.0")
  private final static double $DEFAULT_VALUE$INFLUENCESTEERING_1 = 0.0;
  
  public void emitInfluences(final MotionInfluence motionInfluence, final Influence... otherInfluences) {
    Influence[] influences = null;
    boolean _isEmpty = ((List<Influence>)Conversions.doWrapArray(otherInfluences)).isEmpty();
    if (_isEmpty) {
      Object _newInstance = Array.newInstance(Influence.class, 1);
      influences = ((Influence[]) _newInstance);
      influences[0] = motionInfluence;
    } else {
      int _length = otherInfluences.length;
      int _plus = (_length + 1);
      Object _newInstance_1 = Array.newInstance(Influence.class, _plus);
      influences = ((Influence[]) _newInstance_1);
      influences[0] = motionInfluence;
      System.arraycopy(otherInfluences, 0, influences, 1, otherInfluences.length);
    }
    InfluenceEvent event = new InfluenceEvent(influences);
    event.setSource(this.myAdr);
    AddressUUIDScope _addressUUIDScope = new AddressUUIDScope(this.environmentID);
    this.physicSpace.emit(event, _addressUUIDScope);
  }
  
  @Extension
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS)", imported = DefaultContextInteractions.class)
  private DefaultContextInteractions $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $getSkill(DefaultContextInteractions.class);
    }
    return $castSkill(DefaultContextInteractions.class, this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
  }
  
  @Extension
  @ImportedCapacityFeature(Behaviors.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_BEHAVIORS;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Behaviors.class, (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = $getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS)", imported = Behaviors.class)
  private Behaviors $CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = $getSkill(Behaviors.class);
    }
    return $castSkill(Behaviors.class, this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
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
    StandardPhysicEnvironment other = (StandardPhysicEnvironment) obj;
    if (this.spaceID == null) {
      if (other.spaceID != null)
        return false;
    } else if (!this.spaceID.equals(other.spaceID))
      return false;
    if (this.environmentID == null) {
      if (other.environmentID != null)
        return false;
    } else if (!this.environmentID.equals(other.environmentID))
      return false;
    if (this.physicSpace == null) {
      if (other.physicSpace != null)
        return false;
    } else if (!this.physicSpace.equals(other.physicSpace))
      return false;
    if (this.myAdr == null) {
      if (other.myAdr != null)
        return false;
    } else if (!this.myAdr.equals(other.myAdr))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.spaceID== null) ? 0 : this.spaceID.hashCode());
    result = prime * result + ((this.environmentID== null) ? 0 : this.environmentID.hashCode());
    result = prime * result + ((this.physicSpace== null) ? 0 : this.physicSpace.hashCode());
    result = prime * result + ((this.myAdr== null) ? 0 : this.myAdr.hashCode());
    return result;
  }
}
