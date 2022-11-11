package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.VisitService;

public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

  @Override
  public Visit save(Visit object) {
    if (object == null || object.getPet() == null || object.getPet().getOwner() == null
        || object.getPet().getId() == null)
      throw new RuntimeException("Invalid Visit");
    return super.save(object);
  }

}
