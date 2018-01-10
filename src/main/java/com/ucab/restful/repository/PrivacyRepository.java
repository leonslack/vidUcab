package com.ucab.restful.repository;

import java.util.UUID;

import com.ucab.restful.data.model.Privacy;

public interface PrivacyRepository extends CustomBaseJpaRepository<Privacy, UUID>,PrivacyRepositoryCustom{

}
