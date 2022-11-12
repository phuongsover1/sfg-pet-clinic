package guru.springframework.sfgpetclinic.model;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public class Person extends BaseEntity {
	private String firstName;

	private String lastName;
}