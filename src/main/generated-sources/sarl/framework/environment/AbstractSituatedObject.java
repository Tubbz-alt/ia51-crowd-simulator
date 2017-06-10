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
import framework.environment.SituatedObject;
import framework.util.LocalizedString;
import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.io.Serializable;
import java.util.UUID;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Shape2d;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * Abstract implementation of an object on the environment.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public abstract class AbstractSituatedObject implements SituatedObject, Serializable {
  private final UUID id;
  
  private Point2d position = new Point2d();
  
  private Shape2d<?> shape;
  
  private String name;
  
  private Serializable type;
  
  /**
   * @param id the identifier of the object.
   * @param shape the shape of the body, considering that it is centered at the (0,0) position.
   * @param position is the position of the object.
   */
  @DefaultValueSource
  public AbstractSituatedObject(final UUID id, final Shape2d<?> shape, @DefaultValue("framework.environment.AbstractSituatedObject#NEW_0") final Point2d position) {
    this.id = id;
    this.shape = shape;
    if ((position != null)) {
      this.position.set(position);
    }
  }
  
  /**
   * Default value for the parameter position
   */
  @SyntheticMember
  @SarlSourceCode("null")
  private final static Point2d $DEFAULT_VALUE$NEW_0 = null;
  
  /**
   * @param id the identifier of the object.
   * @param shape the shape of the body, considering that it is centered at the (0,0) position.
   * @param x is the position of the object.
   * @param y is the position of the object.
   */
  public AbstractSituatedObject(final UUID id, final Shape2d<?> shape, final double x, final double y) {
    this.id = id;
    this.shape = shape;
    this.position.set(x, y);
  }
  
  @Override
  @Pure
  public AbstractSituatedObject clone() {
    try {
      Object _clone = super.clone();
      AbstractSituatedObject clone = ((AbstractSituatedObject) _clone);
      clone.position = this.position.clone();
      clone.shape = this.shape.clone();
      return clone;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Override
  @Pure
  public boolean equals(final Object obj) {
    if ((obj == this)) {
      return true;
    }
    if ((obj instanceof SituatedObject)) {
      UUID _iD = ((SituatedObject)obj).getID();
      UUID _iD_1 = this.getID();
      return Objects.equal(_iD, _iD_1);
    }
    return false;
  }
  
  @Override
  @Pure
  public int hashCode() {
    return Objects.hashCode(this.getID());
  }
  
  @Override
  public int compareTo(final SituatedObject o) {
    int _xifexpression = (int) 0;
    if ((o == null)) {
      _xifexpression = Integer.MAX_VALUE;
    } else {
      UUID _iD = this.getID();
      UUID _iD_1 = o.getID();
      _xifexpression = (_iD.compareTo(_iD_1));
    }
    return _xifexpression;
  }
  
  @Override
  @Pure
  public Serializable getType() {
    return this.type;
  }
  
  @Override
  @Pure
  public UUID getID() {
    return this.id;
  }
  
  /**
   * Set the type of the object.
   * 
   * @param type
   */
  public Serializable setType(final Serializable type) {
    return this.type = type;
  }
  
  @Override
  @Pure
  public String getName() {
    return this.name;
  }
  
  /**
   * Change the name of the object.
   * 
   * The name is defined only for displaying purpose.
   * 
   * @return the name of the object.
   */
  public String setName(final String name) {
    return this.name = name;
  }
  
  @Pure
  public Shape2d<?> getShape() {
    return this.shape;
  }
  
  @Override
  @Pure
  public Point2d getPosition() {
    return this.position.clone();
  }
  
  @Override
  @Pure
  public double getX() {
    return this.position.getX();
  }
  
  @Override
  @Pure
  public double getY() {
    return this.position.getY();
  }
  
  /**
   * Set the position of the object.
   * 
   * @param x
   * @param y
   */
  protected void setPosition(final double x, final double y) {
    if ((Double.isNaN(x) || Double.isNaN(y))) {
      System.err.println(LocalizedString.getString(this.getClass(), "INVALID_POSITION", this.name));
    } else {
      this.position.set(x, y);
    }
  }
  
  /**
   * Set the position of the object.
   * 
   * @param position
   */
  protected void setPosition(final Point2d position) {
    if ((Double.isNaN(position.getX()) || Double.isNaN(position.getY()))) {
      System.err.println(LocalizedString.getString(this.getClass(), "INVALID_POSITION", this.name));
    } else {
      this.position.set(position);
    }
  }
  
  /**
   * Move the position of the object.
   * 
   * @param x
   * @param y
   */
  protected void addPosition(final double x, final double y) {
    if ((Double.isNaN(x) || Double.isNaN(y))) {
      System.err.println(LocalizedString.getString(this.getClass(), "INVALID_POSITION", this.name));
    } else {
      this.position.add(x, y);
    }
  }
  
  @Override
  @Pure
  public String toString() {
    String _elvis = null;
    if (this.name != null) {
      _elvis = this.name;
    } else {
      String _string = super.toString();
      _elvis = _string;
    }
    return _elvis;
  }
  
  /**
   * @optionalparam id the identifier of the object.
   * @optionalparam shape the shape of the body, considering that it is centered at the (0,0) position.
   * @optionalparam position is the position of the object.
   */
  @DefaultValueUse("java.util.UUID,org.arakhne.afc.math.geometry.d2.d.Shape2d<?>,org.arakhne.afc.math.geometry.d2.d.Point2d")
  @SyntheticMember
  public AbstractSituatedObject(final UUID id, final Shape2d<?> shape) {
    this(id, shape, $DEFAULT_VALUE$NEW_0);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 3630756739L;
}
