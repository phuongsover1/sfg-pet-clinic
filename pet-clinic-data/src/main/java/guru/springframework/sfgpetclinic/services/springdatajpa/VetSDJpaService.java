package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.repositories.SpecialityRepository;
import guru.springframework.sfgpetclinic.repositories.VetRepository;
import guru.springframework.sfgpetclinic.services.VetService;

@Profile({ "springdatajpa" })
@Service
public class VetSDJpaService implements VetService {
  private final VetRepository vetRepository;
  private final SpecialityRepository specialityRepository;

  @Autowired
  public VetSDJpaService(VetRepository vetRepository, SpecialityRepository specialityRepository) {
    this.vetRepository = vetRepository;
    this.specialityRepository = specialityRepository;
  }

  @Override
  public Set<Vet> findAll() {
    Iterable<Vet> vetIterable = vetRepository.findAll();
    int size = ((Collection<?>) vetIterable).size();

    Set<Vet> vets = new HashSet<>(size);
    vetIterable.forEach(vets::add);
    return vets;
  }

  @Override
  public Vet findById(Long id) {
    Optional<Vet> vetOptional = vetRepository.findById(id);
    return vetOptional.orElse(null);
  }

  @Override
  public Vet save(Vet object) {
    if (object != null) {
      if (object.getSpecialities().size() > 0) {
        object.getSpecialities().forEach(speciality -> {
          if (speciality.getId() == null) {
            Speciality savedSpeciality = specialityRepository.save(speciality);
            speciality.setId(savedSpeciality.getId());
          }
        });
      }
      return vetRepository.save(object);
    }
    return null;
  }

  @Override
  public void delete(Vet object) {
    vetRepository.delete(object);
  }

  @Override
  public void deleteById(Long id) {
    vetRepository.deleteById(id);
  }

}
