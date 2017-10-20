package com.ucab.vid.vidUcab.data.repositories;

import com.ucab.vid.vidUcab.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
}
