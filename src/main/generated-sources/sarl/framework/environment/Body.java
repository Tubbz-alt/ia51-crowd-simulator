/**
 * $Id$
 * 
 * Copyright (c) 2011-15 Stephane GALLAND <stephane.galland@utbm.fr>.
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

import framework.environment.Percept;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.List;
import java.util.UUID;
import org.arakhne.afc.math.geometry.d2.d.Vector2d;

/**
 * Provides the services of a body.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public interface Body {
  /**
   * Replies the id of this body.
   * 
   * @return the id of this body.
   */
  public abstract UUID getID();
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueSource
  public abstract void influenceKinematic(@DefaultValue("framework.environment.Body#INFLUENCEKINEMATIC_0") final Vector2d linearInfluence, @DefaultValue("framework.environment.Body#INFLUENCEKINEMATIC_1") final double angularInfluence);
  
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
  @SarlSourceCode("0")
  public final static double $DEFAULT_VALUE$INFLUENCEKINEMATIC_1 = 0;
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @param linearInfluence is the linear influence to apply on the object.
   * @param angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueSource
  public abstract void influenceSteering(@DefaultValue("framework.environment.Body#INFLUENCESTEERING_0") final Vector2d linearInfluence, @DefaultValue("framework.environment.Body#INFLUENCESTEERING_1") final double angularInfluence);
  
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
  @SarlSourceCode("0")
  public final static double $DEFAULT_VALUE$INFLUENCESTEERING_1 = 0;
  
  /**
   * Replies all the perceived objects.
   * 
   * @return the perceived objects.
   */
  public abstract List<Percept> getPerceivedObjects();
  
  /**
   * Invoked to send the influence to the environment.
   * 
   * @optionalparam linearInfluence is the linear influence to apply on the object.
   * @optionalparam angularInfluence is the angular influence to apply on the object.
   */
  @DefaultValueUse("org.arakhne.afc.math.geometry.d2.d.Vector2d,double")
  @SyntheticMember
  public default void influenceKinematic() {
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
  public default void influenceKinematic(final double angularInfluence) {
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
  public default void influenceKinematic(final Vector2d linearInfluence) {
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
  public default void influenceSteering() {
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
  public default void influenceSteering(final double angularInfluence) {
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
  public default void influenceSteering(final Vector2d linearInfluence) {
    influenceSteering(linearInfluence, $DEFAULT_VALUE$INFLUENCESTEERING_1);
  }
}
