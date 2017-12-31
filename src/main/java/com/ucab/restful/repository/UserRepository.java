package com.ucab.restful.repository;

import java.util.UUID;

import com.ucab.restful.data.model.User;

public interface UserRepository extends CustomBaseJpaRepository<User, UUID> {

}
