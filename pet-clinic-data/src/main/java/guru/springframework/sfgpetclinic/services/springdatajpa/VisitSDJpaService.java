package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import guru.springframework.sfgpetclinic.services.VisitService;

@Profile({ "springdatajpa" })
@Service
public class VisitSDJpaService implements VisitService {
  private final VisitRepository visitRepository;

  @Autowired
  public VisitSDJpaService(VisitRepository visitRepository) {
    this.visitRepository = visitRepository;
  }

  @Override
  public Set<Visit> findAll() {
    Iterable<Visit> visitIterable = visitRepository.findAll();

    int size = ((Collection<?>) visitIterable).size();

    Set<Visit> visits = new HashSet<>(size);
    visitIterable.forEach(visits::add);
    return visits;
  }

  @Override
  public Visit findById(Long id) {
    return visitRepository.findById(id).orElse(null);
  }

  @Override
  public Visit save(Visit object) {
    return visitRepository.save(object);
  }

  @Override
  public void delete(Visit object) {
    visitRepository.delete(object);
  }

  @Override
  public void deleteById(Long id) {
    visitRepository.deleteById(id);
  }

}
