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
package framework.agent;

import com.google.common.base.Objects;
import framework.environment.DynamicType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.Serializable;
import org.arakhne.afc.math.geometry.d2.d.Vector2d;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Output of a behaviour.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public class MotionAlgorithmOutput implements Serializable {
  private final DynamicType type;
  
  private final Vector2d linear = new Vector2d();
  
  private double angular = 0.0;
  
  /**
   * @param type is the type of the output.
   */
  public MotionAlgorithmOutput(final DynamicType type) {
    this.type = type;
  }
  
  /**
   * @param outputToCopy is the output tp copy.
   */
  public MotionAlgorithmOutput(final MotionAlgorithmOutput outputToCopy) {
    this.type = outputToCopy.type;
    this.linear.set(outputToCopy.linear);
    this.angular = outputToCopy.angular;
  }
  
  /**
   * Replies the type of the output.
   * 
   * @return the type of the output.
   */
  @Pure
  public DynamicType getType() {
    return this.type;
  }
  
  /**
   * Replies the linear output.
   * 
   * @return the linear output.
   */
  @Pure
  public Vector2d getLinear() {
    return this.linear;
  }
  
  /**
   * Replies the angular output.
   * 
   * @return the angular output.
   */
  @Pure
  public double getAngular() {
    return this.angular;
  }
  
  /**
   * Set the linear output.
   * 
   * @param linear
   */
  public void setLinear(final Vector2d linear) {
    this.linear.set(linear);
  }
  
  /**
   * Set the linear output.
   * 
   * @param dx
   * @param dy
   */
  public void setLinear(final double dx, final double dy) {
    this.linear.set(dx, dy);
  }
  
  /**
   * Set the angular output.
   * 
   * @param angular
   */
  public double setAngular(final double angular) {
    return this.angular = angular;
  }
  
  /**
   * Set the linear output.
   * 
   * @param outputToCopy
   */
  public void setLinear(final MotionAlgorithmOutput outputToCopy) {
    if ((outputToCopy != null)) {
      boolean _notEquals = (!Objects.equal(outputToCopy.type, this.type));
      if (_notEquals) {
        throw new IllegalArgumentException();
      }
      this.linear.set(outputToCopy.linear);
    }
  }
  
  /**
   * Set the angular output.
   * 
   * @param outputToCopy
   */
  public double setAngular(final MotionAlgorithmOutput outputToCopy) {
    double _xifexpression = (double) 0;
    if ((outputToCopy != null)) {
      double _xblockexpression = (double) 0;
      {
        boolean _notEquals = (!Objects.equal(outputToCopy.type, this.type));
        if (_notEquals) {
          throw new IllegalArgumentException();
        }
        _xblockexpression = this.angular = outputToCopy.angular;
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
  
  /**
   * Set the linear and angular output.
   * 
   * @param outputToCopy
   */
  public double set(final MotionAlgorithmOutput outputToCopy) {
    double _xifexpression = (double) 0;
    if ((outputToCopy != null)) {
      double _xblockexpression = (double) 0;
      {
        boolean _notEquals = (!Objects.equal(outputToCopy.type, this.type));
        if (_notEquals) {
          throw new IllegalArgumentException();
        }
        this.linear.set(outputToCopy.linear);
        _xblockexpression = this.angular = outputToCopy.angular;
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
  
  @Override
  @Pure
  public String toString() {
    String _string = this.linear.toString();
    String _plus = ("l=" + _string);
    String _plus_1 = (_plus + "; a=");
    return (_plus_1 + Double.valueOf(this.angular));
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
    MotionAlgorithmOutput other = (MotionAlgorithmOutput) obj;
    if (this.type == null) {
      if (other.type != null)
        return false;
    } else if (!this.type.equals(other.type))
      return false;
    if (this.linear == null) {
      if (other.linear != null)
        return false;
    } else if (!this.linear.equals(other.linear))
      return false;
    if (Double.doubleToLongBits(other.angular) != Double.doubleToLongBits(this.angular))
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
    result = prime * result + ((this.linear== null) ? 0 : this.linear.hashCode());
    result = prime * result + (int) (Double.doubleToLongBits(this.angular) ^ (Double.doubleToLongBits(this.angular) >>> 32));
    return result;
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 3846954754L;
}
