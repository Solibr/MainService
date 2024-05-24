package ru.gashev.test.advenjineering.projectmanager.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gashev.test.advenjineering.projectmanager.entity.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, String> {
}
