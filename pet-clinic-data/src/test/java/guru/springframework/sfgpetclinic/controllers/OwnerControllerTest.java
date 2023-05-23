package guru.springframework.sfgpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.hasSize;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class OwnerControllerTest {

  @Mock
  OwnerService ownerService;

  @Mock
  Model model;

  @InjectMocks
  OwnerController controller;

  Set<Owner> owners;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    owners = new HashSet<>();
    owners.add(Owner.builder().id(1l).build());
    owners.add(Owner.builder().id(2l).build());

    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

  }

  @Test
  void testFindOwners() throws Exception {
    mockMvc.perform(get("/owners/find"))
        .andExpect(view().name("notimplemented"))
        .andExpect(status().isOk());

    verifyNoInteractions(ownerService);
  }

  @Test
  void testListOwners() throws Exception {
    when(ownerService.findAll()).thenReturn(owners);
    
    mockMvc.perform(get("/owners/index"))
            .andExpect(status().isOk())
            .andExpect(view().name("owner/index"))
            .andExpect(model().attribute("owners",hasSize(2)));

  }
}
