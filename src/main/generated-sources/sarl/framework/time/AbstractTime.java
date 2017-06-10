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

import framework.time.Time;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.concurrent.TimeUnit;

/**
 * Abstract implementation of a time.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public abstract class AbstractTime implements Time {
  public static double secondsToUnits(final double t, final TimeUnit unit) {
    double _switchResult = (double) 0;
    if (unit != null) {
      switch (unit) {
        case DAYS:
          _switchResult = (t / 86400.0);
          break;
        case HOURS:
          _switchResult = (t / 3600.0);
          break;
        case MINUTES:
          _switchResult = (t / 60.0);
          break;
        case MILLISECONDS:
          _switchResult = (t * 1000.0);
          break;
        case MICROSECONDS:
          _switchResult = (t * 1000000.0);
          break;
        case NANOSECONDS:
          _switchResult = (t * 1000000000.0);
          break;
        default:
          _switchResult = t;
          break;
      }
    } else {
      _switchResult = t;
    }
    return _switchResult;
  }
  
  public static double unitsToSeconds(final double t, final TimeUnit unit) {
    double _switchResult = (double) 0;
    if (unit != null) {
      switch (unit) {
        case DAYS:
          _switchResult = (t * 86400.0);
          break;
        case HOURS:
          _switchResult = (t * 3600.0);
          break;
        case MINUTES:
          _switchResult = (t * 60.0);
          break;
        case MILLISECONDS:
          _switchResult = (t / 1000.0);
          break;
        case MICROSECONDS:
          _switchResult = (t / 1000000.0);
          break;
        case NANOSECONDS:
          _switchResult = (t / 1000000000.0);
          break;
        default:
          _switchResult = t;
          break;
      }
    } else {
      _switchResult = t;
    }
    return _switchResult;
  }
  
  @Override
  public double perSecond(final double amountPerSecond) {
    double _lastStepDuration = this.getLastStepDuration();
    return (amountPerSecond * _lastStepDuration);
  }
  
  @Override
  public int compareTo(final Time o) {
    double _currentTime = 0.0;
    if (o!=null) {
      _currentTime=o.getCurrentTime();
    }
    double t = _currentTime;
    double _currentTime_1 = this.getCurrentTime();
    return (Double.valueOf(_currentTime_1).compareTo(Double.valueOf(t)));
  }
  
  @SyntheticMember
  public AbstractTime() {
    super();
  }
}
