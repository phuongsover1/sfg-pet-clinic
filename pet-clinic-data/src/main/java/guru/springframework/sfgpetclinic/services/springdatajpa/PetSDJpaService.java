package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.services.PetService;

@Profile({ "springdatajpa" })
@Service
public class PetSDJpaService implements PetService {
  private final PetRepository petRepository;

  @Autowired
  public PetSDJpaService(PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  @Override
  public Set<Pet> findAll() {
    Iterable<Pet> petIterable = petRepository.findAll();
    int size = ((Collection<?>) petIterable).size();

    Set<Pet> pets = new HashSet<>(size);
    petIterable.forEach(pets::add);

    return pets;
  }

  @Override
  public Pet findById(Long id) {
    return petRepository.findById(id).orElse(null);
  }

  @Override
  public Pet save(Pet object) {
    return petRepository.save(object);
  }

  @Override
  public void delete(Pet object) {
    petRepository.delete(object);
  }

  @Override
  public void deleteById(Long id) {
    petRepository.deleteById(id);
  }

}
