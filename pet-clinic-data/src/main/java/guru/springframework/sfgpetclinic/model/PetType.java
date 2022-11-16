package guru.springframework.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "pet_types")
public class PetType extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Builder
	public PetType(Long id, String name) {
		super(id);
		this.name = name;
	}

}
