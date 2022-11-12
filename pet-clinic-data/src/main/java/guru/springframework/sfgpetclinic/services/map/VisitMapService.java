package guru.springframework.sfgpetclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.VisitService;

@Profile({ "default", "map" })
@Service
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

  @Override
  public Visit save(Visit object) {
    if (object == null || object.getPet() == null || object.getPet().getOwner() == null
        || object.getPet().getId() == null)
      throw new RuntimeException("Invalid Visit");
    return super.save(object);
  }

}
