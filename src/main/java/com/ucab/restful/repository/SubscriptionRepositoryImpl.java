package com.ucab.restful.repository;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ucab.restful.data.model.QSubscription;
import com.ucab.restful.data.model.User;

public class SubscriptionRepositoryImpl implements SubscriptionRepositoryCustom {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<User> getUsersInSubscription(UUID userId, Boolean subs) {

		JPQLQueryFactory query = new JPAQueryFactory(em);

		QSubscription subscription = QSubscription.subscription;

		return subs
				? query.select(subscription.subscriber).from(subscription).where(subscription.owner.id.eq(userId))
						.fetch()
				: query.select(subscription.owner).from(subscription).where(subscription.subscriber.id.eq(userId))
						.fetch();
	}

}
