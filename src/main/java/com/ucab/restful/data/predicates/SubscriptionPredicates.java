package com.ucab.restful.data.predicates;

import java.util.UUID;

import com.querydsl.core.types.Predicate;
import com.ucab.restful.data.model.QSubscription;

public class SubscriptionPredicates {

	private SubscriptionPredicates() {
	}

	public static Predicate usersIdEq(UUID ownerId, UUID subscriberId) {
		return QSubscription.subscription.owner.id.eq(ownerId)
				.and(QSubscription.subscription.subscriber.id.eq(subscriberId));
	}

}
