package guru.springframework.sfgpetclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "specialities")
public class Speciality extends BaseEntity {

	@Column(name = "description")
	private String description;

	@Builder
	public Speciality(Long id, String description) {
		super(id);
		this.description = description;
	}

}
