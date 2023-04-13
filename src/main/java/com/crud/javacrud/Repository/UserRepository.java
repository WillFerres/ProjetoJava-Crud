package com.crud.javacrud.Repository;
import com.crud.javacrud.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User,Long> {

}
