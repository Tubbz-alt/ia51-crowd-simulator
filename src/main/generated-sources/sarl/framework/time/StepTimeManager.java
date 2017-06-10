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
package framework.time;

import framework.time.AbstractTime;
import framework.time.AbstractTimeManager;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.concurrent.TimeUnit;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Step-based Time manager.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public class StepTimeManager extends AbstractTimeManager {
  private double delay = 10.0;
  
  private double t;
  
  private final double step;
  
  /**
   * @param stepDuration the duration of one simulation step (in ms)
   */
  public StepTimeManager(final double stepDuration) {
    this.step = AbstractTime.unitsToSeconds(stepDuration, TimeUnit.MILLISECONDS);
  }
  
  @DefaultValueSource
  @Override
  @Pure
  public synchronized double getCurrentTime(@DefaultValue("framework.time.StepTimeManager#GETCURRENTTIME_0") final TimeUnit unit) {
    TimeUnit _elvis = null;
    if (unit != null) {
      _elvis = unit;
    } else {
      _elvis = TimeUnit.SECONDS;
    }
    return AbstractTime.secondsToUnits(this.t, _elvis);
  }
  
  /**
   * Default value for the parameter unit
   */
  @SyntheticMember
  @SarlSourceCode("null")
  private final static TimeUnit $DEFAULT_VALUE$GETCURRENTTIME_0 = null;
  
  @DefaultValueSource
  @Override
  @Pure
  public synchronized double getLastStepDuration(@DefaultValue("framework.time.StepTimeManager#GETLASTSTEPDURATION_0") final TimeUnit unit) {
    TimeUnit _elvis = null;
    if (unit != null) {
      _elvis = unit;
    } else {
      _elvis = TimeUnit.SECONDS;
    }
    return AbstractTime.secondsToUnits(this.step, _elvis);
  }
  
  /**
   * Default value for the parameter unit
   */
  @SyntheticMember
  @SarlSourceCode("null")
  private final static TimeUnit $DEFAULT_VALUE$GETLASTSTEPDURATION_0 = null;
  
  @Override
  public synchronized void increment() {
    double _t = this.t;
    this.t = (_t + this.step);
  }
  
  @Override
  @Pure
  public synchronized double getSimulationDelay() {
    return this.delay;
  }
  
  @Override
  public synchronized void setSimulationDelay(final double delay) {
    this.delay = Math.max(0.0, delay);
  }
  
  @Override
  @Pure
  public String toString() {
    return ((((("t=" + Double.valueOf(this.t)) + "; step=") + Double.valueOf(this.step)) + "; delay = ") + Double.valueOf(this.delay));
  }
  
  @DefaultValueUse("java.util.concurrent.TimeUnit")
  @SyntheticMember
  @Override
  @Pure
  public final double getCurrentTime() {
    return getCurrentTime($DEFAULT_VALUE$GETCURRENTTIME_0);
  }
  
  @DefaultValueUse("java.util.concurrent.TimeUnit")
  @SyntheticMember
  @Override
  @Pure
  public final double getLastStepDuration() {
    return getLastStepDuration($DEFAULT_VALUE$GETLASTSTEPDURATION_0);
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
    StepTimeManager other = (StepTimeManager) obj;
    if (Double.doubleToLongBits(other.delay) != Double.doubleToLongBits(this.delay))
      return false;
    if (Double.doubleToLongBits(other.t) != Double.doubleToLongBits(this.t))
      return false;
    if (Double.doubleToLongBits(other.step) != Double.doubleToLongBits(this.step))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (int) (Double.doubleToLongBits(this.delay) ^ (Double.doubleToLongBits(this.delay) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.t) ^ (Double.doubleToLongBits(this.t) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.step) ^ (Double.doubleToLongBits(this.step) >>> 32));
    return result;
  }
}
