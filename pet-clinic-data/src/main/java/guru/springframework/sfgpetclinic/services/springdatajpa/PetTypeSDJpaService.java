package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.PetTypeService;

@Profile({ "springdata" })
@Service
public class PetTypeSDJpaService implements PetTypeService {
  private final PetTypeRepository petTypeRepository;

  @Autowired
  public PetTypeSDJpaService(PetTypeRepository petTypeRepository) {
    this.petTypeRepository = petTypeRepository;
  }

  @Override
  public Set<PetType> findAll() {
    Iterable<PetType> petTypeIterable = petTypeRepository.findAll();
    int size = ((Collection<?>) petTypeIterable).size();

    Set<PetType> petTypes = new HashSet<>(size);
    petTypeIterable.forEach(petTypes::add);

    return petTypes;
  }

  @Override
  public PetType findById(Long id) {
    return petTypeRepository.findById(id).orElse(null);
  }

  @Override
  public PetType save(PetType object) {
    return petTypeRepository.save(object);
  }

  @Override
  public void delete(PetType object) {
    petTypeRepository.delete(object);
  }

  @Override
  public void deleteById(Long id) {
    petTypeRepository.deleteById(id);
  }

}
