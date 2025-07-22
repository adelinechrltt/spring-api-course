package com.codewithmosh.store.repositories;

import com.codewithmosh.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

///  jpa repository interface provides overload for pagination etc.
public interface UserRepository extends JpaRepository<User, Long> {
}
