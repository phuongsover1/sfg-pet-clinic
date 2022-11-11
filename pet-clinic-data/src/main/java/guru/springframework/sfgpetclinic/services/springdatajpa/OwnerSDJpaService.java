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
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
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
    if (object != null) {
      if (object.getPets().size() > 0) {
        object.getPets().forEach(pet -> {
          PetType petType = pet.getPetType();
          if (petType.getId() == null) {
            PetType savedPetType = petTypeRepository.save(petType);
            petType.setId(savedPetType.getId());
          }
          if (pet.getId() == null) {
            Pet savedPet = petRepository.save(pet);
            pet.setId(savedPet.getId());
          }
        });
      }
      return ownerRepository.save(object);
    }
    return null;
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
