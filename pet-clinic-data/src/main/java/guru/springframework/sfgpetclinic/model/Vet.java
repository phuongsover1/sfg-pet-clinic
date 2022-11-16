package guru.springframework.sfgpetclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "vets")
public class Vet extends Person {

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "vet_speciality", joinColumns = @JoinColumn(name = "vet_id"), inverseJoinColumns = @JoinColumn(name = "specialty_id"))
	private Set<Speciality> specialities = new HashSet<>();

	@Builder
	public Vet(Long id, String firstName, String lastName, Set<Speciality> specialities) {
		super(id, firstName, lastName);
		this.specialities = specialities;
	}

}
