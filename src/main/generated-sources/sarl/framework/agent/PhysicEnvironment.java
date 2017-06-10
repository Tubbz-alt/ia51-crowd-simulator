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

import framework.environment.Influence;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;
import org.arakhne.afc.math.geometry.d2.d.Vector2d;

@SarlSpecification("0.5")
@SuppressWarnings("all")
public interface PhysicEnvironment extends Capacity {
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   * @param otherInfluences other influences to emit in parallel to the motion influence.
   */
  @DefaultValueSource
  public abstract void influenceKinematic(@DefaultValue("framework.agent.PhysicEnvironment#INFLUENCEKINEMATIC_0") final Vector2d linearInfluence, @DefaultValue("framework.agent.PhysicEnvironment#INFLUENCEKINEMATIC_1") final double angularInfluence, final Influence... otherInfluences);
  
  /**
   * Default value for the parameter linearInfluence
   */
  @SyntheticMember
  @SarlSourceCode("null")
  public final static Vector2d $DEFAULT_VALUE$INFLUENCEKINEMATIC_0 = null;
  
  /**
   * Default value for the parameter angularInfluence
   */
  @SyntheticMember
  @SarlSourceCode("0.0")
  public final static double $DEFAULT_VALUE$INFLUENCEKINEMATIC_1 = 0.0;
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   * @param otherInfluences other influences to emit in parallel to the motion influence.
   */
  @DefaultValueSource
  public abstract void influenceSteering(@DefaultValue("framework.agent.PhysicEnvironment#INFLUENCESTEERING_0") final Vector2d linearInfluence, @DefaultValue("framework.agent.PhysicEnvironment#INFLUENCESTEERING_1") final double angularInfluence, final Influence... otherInfluences);
  
  /**
   * Default value for the parameter linearInfluence
   */
  @SyntheticMember
  @SarlSourceCode("null")
  public final static Vector2d $DEFAULT_VALUE$INFLUENCESTEERING_0 = null;
  
  /**
   * Default value for the parameter angularInfluence
   */
  @SyntheticMember
  @SarlSourceCode("0.0")
  public final static double $DEFAULT_VALUE$INFLUENCESTEERING_1 = 0.0;
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   * @param otherInfluences other influences to emit in parallel to the motion influence.
   */
  @DefaultValueUse("org.arakhne.afc.math.geometry.d2.d.Vector2d,double,framework.environment.Influence*")
  @SyntheticMember
  public default void influenceKinematic(final Influence... otherInfluences) {
    influenceKinematic($DEFAULT_VALUE$INFLUENCEKINEMATIC_0, $DEFAULT_VALUE$INFLUENCEKINEMATIC_1, otherInfluences);
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   * @param otherInfluences other influences to emit in parallel to the motion influence.
   */
  @DefaultValueUse("org.arakhne.afc.math.geometry.d2.d.Vector2d,double,framework.environment.Influence*")
  @SyntheticMember
  public default void influenceKinematic(final double angularInfluence, final Influence... otherInfluences) {
    influenceKinematic($DEFAULT_VALUE$INFLUENCEKINEMATIC_0, angularInfluence, otherInfluences);
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   * @param otherInfluences other influences to emit in parallel to the motion influence.
   */
  @DefaultValueUse("org.arakhne.afc.math.geometry.d2.d.Vector2d,double,framework.environment.Influence*")
  @SyntheticMember
  public default void influenceKinematic(final Vector2d linearInfluence, final Influence... otherInfluences) {
    influenceKinematic(linearInfluence, $DEFAULT_VALUE$INFLUENCEKINEMATIC_1, otherInfluences);
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   * @param otherInfluences other influences to emit in parallel to the motion influence.
   */
  @DefaultValueUse("org.arakhne.afc.math.geometry.d2.d.Vector2d,double,framework.environment.Influence*")
  @SyntheticMember
  public default void influenceSteering(final Influence... otherInfluences) {
    influenceSteering($DEFAULT_VALUE$INFLUENCESTEERING_0, $DEFAULT_VALUE$INFLUENCESTEERING_1, otherInfluences);
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   * @param otherInfluences other influences to emit in parallel to the motion influence.
   */
  @DefaultValueUse("org.arakhne.afc.math.geometry.d2.d.Vector2d,double,framework.environment.Influence*")
  @SyntheticMember
  public default void influenceSteering(final double angularInfluence, final Influence... otherInfluences) {
    influenceSteering($DEFAULT_VALUE$INFLUENCESTEERING_0, angularInfluence, otherInfluences);
  }
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   * @param otherInfluences other influences to emit in parallel to the motion influence.
   */
  @DefaultValueUse("org.arakhne.afc.math.geometry.d2.d.Vector2d,double,framework.environment.Influence*")
  @SyntheticMember
  public default void influenceSteering(final Vector2d linearInfluence, final Influence... otherInfluences) {
    influenceSteering(linearInfluence, $DEFAULT_VALUE$INFLUENCESTEERING_1, otherInfluences);
  }
  
  public static class ContextAwareCapacityWrapper<C extends PhysicEnvironment> extends Capacity.ContextAwareCapacityWrapper<C> implements PhysicEnvironment {
    public ContextAwareCapacityWrapper(final C capacity, final AgentTrait caller) {
      super(capacity, caller);
    }
    
    public void influenceKinematic(final Vector2d linearInfluence, final double angularInfluence, final Influence... otherInfluences) {
      try {
        ensureCallerInLocalThread();
        this.capacity.influenceKinematic(linearInfluence, angularInfluence, otherInfluences);
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public void influenceSteering(final Vector2d linearInfluence, final double angularInfluence, final Influence... otherInfluences) {
      try {
        ensureCallerInLocalThread();
        this.capacity.influenceSteering(linearInfluence, angularInfluence, otherInfluences);
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public void influenceKinematic(final Influence... otherInfluences) {
      try {
        ensureCallerInLocalThread();
        this.capacity.influenceKinematic(otherInfluences);
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public void influenceKinematic(final double angularInfluence, final Influence... otherInfluences) {
      try {
        ensureCallerInLocalThread();
        this.capacity.influenceKinematic(angularInfluence, otherInfluences);
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public void influenceKinematic(final Vector2d linearInfluence, final Influence... otherInfluences) {
      try {
        ensureCallerInLocalThread();
        this.capacity.influenceKinematic(linearInfluence, otherInfluences);
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public void influenceSteering(final Influence... otherInfluences) {
      try {
        ensureCallerInLocalThread();
        this.capacity.influenceSteering(otherInfluences);
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public void influenceSteering(final double angularInfluence, final Influence... otherInfluences) {
      try {
        ensureCallerInLocalThread();
        this.capacity.influenceSteering(angularInfluence, otherInfluences);
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public void influenceSteering(final Vector2d linearInfluence, final Influence... otherInfluences) {
      try {
        ensureCallerInLocalThread();
        this.capacity.influenceSteering(linearInfluence, otherInfluences);
      } finally {
        resetCallerInLocalThread();
      }
    }
  }
}
