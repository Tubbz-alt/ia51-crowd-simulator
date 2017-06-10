package framework.environment.events;

import framework.environment.Influence;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Leon Victor
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public class InfluenceEvent extends Event {
  public final List<Influence> influences;
  
  public InfluenceEvent(final Influence... e) {
    this.influences = ((List<Influence>)Conversions.doWrapArray(e));
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
    InfluenceEvent other = (InfluenceEvent) obj;
    if (this.influences == null) {
      if (other.influences != null)
        return false;
    } else if (!this.influences.equals(other.influences))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.influences== null) ? 0 : this.influences.hashCode());
    return result;
  }
  
  /**
   * Returns a String representation of the InfluenceEvent event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected String attributesToString() {
    StringBuilder result = new StringBuilder(super.attributesToString());
    result.append("influences  = ").append(this.influences);
    return result.toString();
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 2784705763L;
}
