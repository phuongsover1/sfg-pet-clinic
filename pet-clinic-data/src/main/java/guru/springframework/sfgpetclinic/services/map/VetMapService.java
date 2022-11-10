package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialityService;
import guru.springframework.sfgpetclinic.services.VetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {
	private final SpecialityService specialityService;

	@Autowired
	public VetMapService(SpecialityService specialtyService) {
		this.specialityService = specialtyService;
	}

	@Override
	public Set<Vet> findAll() {
		return super.findAll();
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public Vet save(Vet vet) {
		if (vet != null) {
			if (vet.getSpecialities().size() > 0) {
				vet.getSpecialities().forEach(specialty -> {
					if (specialty.getId() == null) {
						Speciality savedSpeciality = specialityService.save(specialty);
						specialty.setId(savedSpeciality.getId());
					}
				});
			}
			return super.save(vet);
		}
		return null;

	}

	@Override
	public void delete(Vet vet) {
		super.delete(vet);
	}

	@Override
	public Vet findById(Long id) {
		return super.findById(id);
	}
}