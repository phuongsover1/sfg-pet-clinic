package guru.springframework.sfgpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.sfgpetclinic.model.Owner;

public class OwnerMapServiceTest {
  OwnerMapService ownerMapService;

  final Long ownerId = 1L;

  final String lastName = "Smith";

  @BeforeEach
  void setUp() {
    ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

    Owner owner = new Owner();
    owner.setId(ownerId);
    owner.setLastName(lastName);
    ownerMapService.save(owner);
  }

  @Test
  void testDelete() {
    ownerMapService.delete(ownerMapService.findById(ownerId));

    assertEquals(0, ownerMapService.findAll().size());
  }

  @Test
  void testDeleteById() {
    ownerMapService.deleteById(ownerId);

    assertEquals(0, ownerMapService.findAll().size());
  }

  @Test
  void testFindAll() {
    Set<Owner> owners = ownerMapService.findAll();
    assertEquals(1, owners.size());
  }

  @Test
  void testFindById() {
    Owner owner = ownerMapService.findById(ownerId);

    assertEquals(ownerId, owner.getId());
  }

  @Test
  void testFindByLastName() {
    Owner smith = ownerMapService.findByLastName(lastName);

    assertNotNull(smith);
    assertEquals(1L, smith.getId());
  }

  @Test
  void testFindByLastNameNotFound() {
    Owner foo = ownerMapService.findByLastName("foo");

    assertNull(foo);
  }

  @Test
  void testSaveExistingId() {
    Long id = 2L;
    Owner owner2 = new Owner();
    owner2.setId(id);

    Owner savedOwner = ownerMapService.save(owner2);

    assertEquals(id, savedOwner.getId());

  }

  @Test
  void saveNoId() {
    Owner owner = new Owner();
    Owner savedOwner = ownerMapService.save(owner);

    assertNotNull(savedOwner);
    assertNotNull(savedOwner.getId());
  }

}
