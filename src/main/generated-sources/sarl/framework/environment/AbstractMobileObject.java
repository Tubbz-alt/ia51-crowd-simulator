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

import framework.environment.AbstractSituatedObject;
import framework.environment.MobileObject;
import framework.time.TimeManager;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.UUID;
import org.arakhne.afc.math.MathUtil;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.math.geometry.d2.d.Shape2d;
import org.arakhne.afc.math.geometry.d2.d.Vector2d;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Abstract implementation of an object on the environment.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public abstract class AbstractMobileObject extends AbstractSituatedObject implements MobileObject {
  private final double maxLinearSpeed;
  
  private final double maxLinearAcceleration;
  
  private final double maxAngularSpeed;
  
  private final double maxAngularAcceleration;
  
  private double angle = 0.0;
  
  private double currentAngularSpeed = 0.0;
  
  private Vector2d linearMove = new Vector2d();
  
  /**
   * @param id the identifier of the object.
   * @param shape the shape of the body, considering that it is centered at the (0,0) position.
   * @param maxLinearSpeed is the maximal linear speed.
   * @param maxLinearAcceleration is the maximal linear acceleration.
   * @param maxAngularSpeed is the maximal angular speed.
   * @param maxAngularAcceleration is the maximal angular acceleration.
   */
  public AbstractMobileObject(final UUID id, final Shape2d<?> shape, final double maxLinearSpeed, final double maxLinearAcceleration, final double maxAngularSpeed, final double maxAngularAcceleration) {
    super(id, shape);
    this.maxLinearSpeed = Math.abs(maxLinearSpeed);
    this.maxLinearAcceleration = Math.abs(maxLinearAcceleration);
    this.maxAngularAcceleration = Math.abs(maxAngularAcceleration);
    this.maxAngularSpeed = Math.abs(maxAngularSpeed);
  }
  
  @Override
  @Pure
  public AbstractMobileObject clone() {
    AbstractSituatedObject _clone = super.clone();
    AbstractMobileObject clone = ((AbstractMobileObject) _clone);
    clone.linearMove = this.linearMove.clone();
    return clone;
  }
  
  @Override
  @Pure
  public double getAngle() {
    return this.angle;
  }
  
  @Override
  @Pure
  public Vector2d getDirection() {
    return new Vector2d(Math.cos(this.angle), Math.sin(this.angle));
  }
  
  /**
   * Set the orientation of the object.
   * 
   * @param angle
   */
  protected double setAngle(final double angle) {
    double _xblockexpression = (double) 0;
    {
      this.angle = angle;
      _xblockexpression = this.currentAngularSpeed = 0;
    }
    return _xblockexpression;
  }
  
  /**
   * Set the direction of the object.
   * 
   * @param dx
   * @param dy
   */
  protected double setDirection(final double dx, final double dy) {
    double _xblockexpression = (double) 0;
    {
      this.angle = new Vector2d(dx, dy).getOrientationAngle();
      _xblockexpression = this.currentAngularSpeed = 0;
    }
    return _xblockexpression;
  }
  
  @Override
  @Pure
  public double getMaxLinearSpeed() {
    return this.maxLinearSpeed;
  }
  
  @Override
  @Pure
  public double getMaxAngularSpeed() {
    return this.maxAngularSpeed;
  }
  
  @Override
  @Pure
  public double getMaxLinearAcceleration() {
    return this.maxLinearAcceleration;
  }
  
  @Override
  @Pure
  public double getMaxAngularAcceleration() {
    return this.maxAngularAcceleration;
  }
  
  @Override
  @Pure
  public double getCurrentAngularSpeed() {
    return this.currentAngularSpeed;
  }
  
  @Override
  @Pure
  public double getCurrentLinearSpeed() {
    return this.linearMove.getLength();
  }
  
  @Override
  @Pure
  public Vector2d getCurrentLinearMotion() {
    return this.linearMove.clone();
  }
  
  /**
   * Rotate the object.
   * 
   * @param rotation is the real instant motion.
   * @param simulationDuration is the time during which the motion is applied.
   */
  protected double rotate(final double rotation, final double simulationDuration) {
    double _xblockexpression = (double) 0;
    {
      double _angle = this.angle;
      this.angle = (_angle + rotation);
      _xblockexpression = this.currentAngularSpeed = (rotation / simulationDuration);
    }
    return _xblockexpression;
  }
  
  public void setPosition(final double x, final double y) {
    super.setPosition(x, y);
    this.linearMove.set(0, 0);
  }
  
  /**
   * Move the situated object.
   * 
   * @param dx is the real instant motion.
   * @param dy is the real instant motion.
   * @param simulationDuration is the time during which the motion is applied.
   * @param worldWidth is the width of the world.
   * @param worldHeight is the height of the world.
   * @return the real motion.
   */
  protected Vector2d move(final double dx, final double dy, final double simulationDuration, final double worldWidth, final double worldHeight) {
    Vector2d r = new Vector2d(dx, dy);
    Shape2d<?> targetShape = this.getShape();
    targetShape.translate(r.getX(), r.getY());
    Rectangle2d targetBounds = targetShape.toBoundingBox();
    double _minX = targetBounds.getMinX();
    boolean _lessThan = (_minX < 0);
    if (_lessThan) {
      double _minX_1 = targetBounds.getMinX();
      double _minus = (-_minX_1);
      r.addX(_minus);
    } else {
      double _maxX = targetBounds.getMaxX();
      boolean _greaterThan = (_maxX > worldWidth);
      if (_greaterThan) {
        double _maxX_1 = targetBounds.getMaxX();
        double _minus_1 = (_maxX_1 - worldWidth);
        r.subX(_minus_1);
      }
    }
    double _minY = targetBounds.getMinY();
    boolean _lessThan_1 = (_minY < 0);
    if (_lessThan_1) {
      double _minY_1 = targetBounds.getMinY();
      double _minus_2 = (-_minY_1);
      r.addY(_minus_2);
    } else {
      double _maxY = targetBounds.getMaxY();
      boolean _greaterThan_1 = (_maxY > worldHeight);
      if (_greaterThan_1) {
        double _maxY_1 = targetBounds.getMaxY();
        double _minus_3 = (_maxY_1 - worldHeight);
        r.subY(_minus_3);
      }
    }
    this.addPosition(r.getX(), r.getY());
    if ((simulationDuration > 0)) {
      this.linearMove.set(r.getX(), r.getY());
      double distance = this.linearMove.getLength();
      if ((distance > 0)) {
        this.linearMove.normalize();
        this.linearMove.scale((distance / simulationDuration));
      }
    } else {
      this.linearMove.set(0, 0);
    }
    return r;
  }
  
  /**
   * Compute a steering move according to the linear move and to
   * the internal attributes of this object.
   * 
   * @param move is the requested motion, expressed with acceleration.
   * @param clock is the simulation time manager
   * @return the linear instant motion.
   */
  protected Vector2d computeSteeringTranslation(final Vector2d move, final TimeManager clock) {
    double length = move.getLength();
    Vector2d v = null;
    if ((length != 0.0)) {
      double _xifexpression = (double) 0;
      double _multiply = move.operator_multiply(this.linearMove);
      boolean _lessThan = (_multiply < 0.0);
      if (_lessThan) {
        _xifexpression = (-length);
      } else {
        _xifexpression = length;
      }
      double acceleration = MathUtil.clamp(_xifexpression, 
        (-this.maxLinearAcceleration), 
        this.maxLinearAcceleration);
      double _abs = Math.abs(acceleration);
      double _divide = (_abs / length);
      acceleration = _divide;
      Vector2d _multiply_1 = move.operator_multiply(acceleration);
      v = _multiply_1;
      double _lastStepDuration = clock.getLastStepDuration();
      double _multiply_2 = (0.5 * _lastStepDuration);
      v.scale(_multiply_2);
      v.operator_add(this.linearMove);
    } else {
      v = this.linearMove.clone();
    }
    double _x = v.getX();
    double _x_1 = v.getX();
    double _multiply_3 = (_x * _x_1);
    double _y = v.getY();
    double _y_1 = v.getY();
    double _multiply_4 = (_y * _y_1);
    double _plus = (_multiply_3 + _multiply_4);
    length = _plus;
    if ((length != 0.0)) {
      double _sqrt = Math.sqrt(length);
      length = ((double) _sqrt);
      double _xifexpression_1 = (double) 0;
      double _multiply_5 = v.operator_multiply(this.linearMove);
      boolean _lessThan_1 = (_multiply_5 < 0.0);
      if (_lessThan_1) {
        _xifexpression_1 = (-length);
      } else {
        _xifexpression_1 = length;
      }
      double speed = MathUtil.clamp(_xifexpression_1, 
        0.0, 
        this.maxLinearSpeed);
      double _lastStepDuration_1 = clock.getLastStepDuration();
      double _abs_1 = Math.abs(speed);
      double _multiply_6 = (_lastStepDuration_1 * _abs_1);
      double factor = (_multiply_6 / length);
      return v.operator_multiply(factor);
    }
    return new Vector2d();
  }
  
  /**
   * Compute a kinematic move according to the linear move and to
   * the internal attributes of this object.
   * 
   * @param move is the requested motion, expressed with speed.
   * @param clock is the simulation time manager
   * @return the linear instant motion.
   */
  protected Vector2d computeKinematicTranslation(final Vector2d move, final TimeManager clock) {
    double speed = move.getLength();
    if ((speed != 0.0)) {
      double _lastStepDuration = clock.getLastStepDuration();
      double _clamp = MathUtil.clamp(speed, 0, this.maxLinearSpeed);
      double _multiply = (_lastStepDuration * _clamp);
      double factor = (_multiply / speed);
      return move.operator_multiply(factor);
    }
    return new Vector2d();
  }
  
  /**
   * Compute a kinematic move according to the angular move and to
   * the internal attributes of this object.
   * 
   * @param move is the requested motion with speed.
   * @param clock is the simulation time manager
   * @return the angular instant motion.
   */
  protected double computeKinematicRotation(final double move, final TimeManager clock) {
    double speed = Math.abs(move);
    if ((speed != 0.0)) {
      double _lastStepDuration = clock.getLastStepDuration();
      double _clamp = MathUtil.clamp(speed, 0, this.maxAngularSpeed);
      double _multiply = (_lastStepDuration * _clamp);
      double factor = (_multiply / speed);
      return (move * factor);
    }
    return 0.0;
  }
  
  /**
   * Compute a steering move according to the angular move and to
   * the internal attributes of this object.
   * 
   * @param move is the requested motion.
   * @param clock is the simulation time manager
   * @return the angular instant motion.
   */
  protected double computeSteeringRotation(final double move, final TimeManager clock) {
    double v = 0;
    if ((move != 0.0)) {
      double acceleration = MathUtil.clamp(move, 
        (-this.maxAngularAcceleration), 
        this.maxAngularAcceleration);
      double _abs = Math.abs(acceleration);
      double _abs_1 = Math.abs(move);
      double _divide = (_abs / _abs_1);
      acceleration = _divide;
      v = (move * acceleration);
      double _v = v;
      double _lastStepDuration = clock.getLastStepDuration();
      double _multiply = (0.5 * _lastStepDuration);
      v = (_v * _multiply);
      double _v_1 = v;
      v = (_v_1 + this.currentAngularSpeed);
    } else {
      v = this.currentAngularSpeed;
    }
    if ((v != 0.0)) {
      double speed = MathUtil.clamp(v, 
        (-this.maxAngularSpeed), 
        this.maxAngularSpeed);
      double _lastStepDuration_1 = clock.getLastStepDuration();
      double _abs_2 = Math.abs(speed);
      double _multiply_1 = (_lastStepDuration_1 * _abs_2);
      double _abs_3 = Math.abs(v);
      double factor = (_multiply_1 / _abs_3);
      return (v * factor);
    }
    return 0.0;
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
    AbstractMobileObject other = (AbstractMobileObject) obj;
    if (Double.doubleToLongBits(other.maxLinearSpeed) != Double.doubleToLongBits(this.maxLinearSpeed))
      return false;
    if (Double.doubleToLongBits(other.maxLinearAcceleration) != Double.doubleToLongBits(this.maxLinearAcceleration))
      return false;
    if (Double.doubleToLongBits(other.maxAngularSpeed) != Double.doubleToLongBits(this.maxAngularSpeed))
      return false;
    if (Double.doubleToLongBits(other.maxAngularAcceleration) != Double.doubleToLongBits(this.maxAngularAcceleration))
      return false;
    if (Double.doubleToLongBits(other.angle) != Double.doubleToLongBits(this.angle))
      return false;
    if (Double.doubleToLongBits(other.currentAngularSpeed) != Double.doubleToLongBits(this.currentAngularSpeed))
      return false;
    if (this.linearMove == null) {
      if (other.linearMove != null)
        return false;
    } else if (!this.linearMove.equals(other.linearMove))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (int) (Double.doubleToLongBits(this.maxLinearSpeed) ^ (Double.doubleToLongBits(this.maxLinearSpeed) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.maxLinearAcceleration) ^ (Double.doubleToLongBits(this.maxLinearAcceleration) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.maxAngularSpeed) ^ (Double.doubleToLongBits(this.maxAngularSpeed) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.maxAngularAcceleration) ^ (Double.doubleToLongBits(this.maxAngularAcceleration) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.angle) ^ (Double.doubleToLongBits(this.angle) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.currentAngularSpeed) ^ (Double.doubleToLongBits(this.currentAngularSpeed) >>> 32));
    result = prime * result + ((this.linearMove== null) ? 0 : this.linearMove.hashCode());
    return result;
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -5080880805L;
}
