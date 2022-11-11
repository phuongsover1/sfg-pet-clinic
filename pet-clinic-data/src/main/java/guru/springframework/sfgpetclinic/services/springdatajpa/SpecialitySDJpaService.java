package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialityRepository;
import guru.springframework.sfgpetclinic.services.SpecialityService;

@Profile({ "springdatajpa" })
@Service
public class SpecialitySDJpaService implements SpecialityService {
  private final SpecialityRepository specialityRepository;

  @Autowired
  public SpecialitySDJpaService(SpecialityRepository specialityRepository) {
    this.specialityRepository = specialityRepository;
  }

  @Override
  public Set<Speciality> findAll() {
    Iterable<Speciality> specialityIterable = specialityRepository.findAll();
    int size = ((Collection<?>) specialityIterable).size();

    Set<Speciality> specialities = new HashSet<>(size);
    specialityIterable.forEach(specialities::add);
    return specialities;

  }

  @Override
  public Speciality findById(Long id) {
    return specialityRepository.findById(id).orElse(null);
  }

  @Override
  public Speciality save(Speciality object) {
    return specialityRepository.save(object);
  }

  @Override
  public void delete(Speciality object) {
    specialityRepository.delete(object);
  }

  @Override
  public void deleteById(Long id) {
    specialityRepository.deleteById(id);
  }

}
