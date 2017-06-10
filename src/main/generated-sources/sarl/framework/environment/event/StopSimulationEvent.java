package framework.environment.event;

import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

/**
 * @author Leon Victor
 */
@SarlSpecification("0.5")
@SuppressWarnings("all")
public class StopSimulationEvent extends Event {
  @SyntheticMember
  public StopSimulationEvent() {
    super();
  }
  
  @SyntheticMember
  public StopSimulationEvent(final Address source) {
    super(source);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 588368462L;
}
