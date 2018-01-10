package com.ucab.restful.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ucab.restful.data.model.QSubscription;
import com.ucab.restful.data.model.QUser;
import com.ucab.restful.data.model.User;

public class SubscriptionRepositoryImpl implements SubscriptionRepositoryCustom {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<User> getUsersInSubscription(UUID userId, Boolean subs) {

		JPQLQueryFactory query = new JPAQueryFactory(em);

		QSubscription subscription = QSubscription.subscription;
		
		QUser quser = new QUser("quser");
		
		List<User> result = new LinkedList<>();
		
		if(subs){
			result = query.select(quser).from(subscription).leftJoin(subscription.subscriber,quser).where(subscription.owner.id.eq(userId))
			.fetch();
		}else{
			result = query.select(quser).from(subscription).leftJoin(subscription.owner,quser).where(subscription.subscriber.id.eq(userId))
			.fetch();
		}

						
		return result;				
	}

}
