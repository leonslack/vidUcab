package com.ucab.restful.repository;

import java.util.UUID;

import com.ucab.restful.data.model.Subscription;

public interface SubscriptionRepository extends CustomBaseJpaRepository<Subscription, UUID>, SubscriptionRepositoryCustom{

}
