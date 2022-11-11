package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.lang.StackWalker.Option;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;

@Profile({ "springdatajpa" })
@Service
public class OwnerSDJpaService implements OwnerService {

  private final OwnerRepository ownerRepository;
  private final PetRepository petRepository;
  private final PetTypeRepository petTypeRepository;

  @Autowired
  public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
      PetTypeRepository petTypeRepository) {
    this.ownerRepository = ownerRepository;
    this.petRepository = petRepository;
    this.petTypeRepository = petTypeRepository;
  }

  @Override
  public Set<Owner> findAll() {
    Iterable<Owner> ownerIterable = ownerRepository.findAll();
    int size = ((Collection<?>) ownerIterable).size();
    Set<Owner> owners = new HashSet<>(size);

    ownerIterable.forEach(owners::add);
    return owners;
  }

  @Override
  public Owner findById(Long id) {
    return ownerRepository.findById(id).orElse(null);
  }

  @Override
  public Owner save(Owner object) {
    return ownerRepository.save(object);
  }

  @Override
  public void delete(Owner object) {
    ownerRepository.delete(object);
  }

  @Override
  public void deleteById(Long id) {
    ownerRepository.deleteById(id);
  }

  @Override
  public Owner findByLastName(String lastName) {
    Optional<Owner> ownerOptional = ownerRepository.findByLastName(lastName);
    return ownerOptional.orElse(null);
  }

}
