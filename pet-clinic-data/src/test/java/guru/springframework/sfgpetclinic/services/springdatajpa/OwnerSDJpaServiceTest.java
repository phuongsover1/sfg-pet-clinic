package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class OwnerSDJpaServiceTest {

  @Mock
  OwnerRepository ownerRepository;

  @InjectMocks
  OwnerSDJpaService ownerSDJpaService;

  final Long ownerId = 1L;

  final String lastName = "Smith";

  Owner returnOwner;

  @BeforeEach
  void setUp() {
    returnOwner = new Owner();
    returnOwner.setId(1L);
    returnOwner.setLastName(lastName);
  }

  @Test
  void testDelete() {
    ownerSDJpaService.delete(returnOwner);

    verify(ownerRepository).delete(any());
  }

  @Test
  void testDeleteById() {
    ownerSDJpaService.deleteById(ownerId);

    verify(ownerRepository).deleteById(anyLong());
  }

  @Test
  void testFindAll() {
    Set<Owner> owners = new HashSet<>(2);
    Owner owner2 = new Owner();
    owner2.setId(2L);
    owner2.setLastName("Nguyen");

    owners.add(returnOwner);
    owners.add(owner2);
    log.debug(owners.toString());
    when(ownerRepository.findAll()).thenReturn(owners);

    Set<Owner> returnedOwners = ownerSDJpaService.findAll();
    assertEquals(2, returnedOwners.size());
    verify(ownerRepository).findAll();
  }

  @Test
  void testFindById() {
    Optional<Owner> optional = Optional.of(returnOwner);

    when(ownerRepository.findById(anyLong())).thenReturn(optional);

    returnOwner = ownerSDJpaService.findById(ownerId);
    assertNotNull(returnOwner);
    assertEquals(lastName, returnOwner.getLastName());
  }

  @Test
  void testFindByLastName() {
    when(ownerRepository.findByLastName(lastName)).thenReturn(Optional.of(returnOwner));
    Owner smith = ownerSDJpaService.findByLastName(lastName);

    assertNotNull(smith);
    verify(ownerRepository).findByLastName(anyString());
  }

  @Test
  void testFindByLastNameNotFound() {
    when(ownerRepository.findByLastName(anyString())).thenReturn(Optional.empty());

    Owner temp = ownerSDJpaService.findByLastName("fdfdf");
    assertNull(temp);

  }

  @Test
  void testSave() {
    when(ownerRepository.save(any())).thenReturn(returnOwner);

    Owner savedOwner = ownerSDJpaService.save(returnOwner);
    verify(ownerRepository).save(any());
  assertNotNull(savedOwner);
  }
}
