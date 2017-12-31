package com.ucab.restful.data.predicates;

import com.querydsl.core.types.Predicate;
import com.ucab.restful.data.model.QUser;


public class UserPredicates {
	private UserPredicates() {
	}

	private static QUser user = QUser.user;

	public static Predicate recordContains(String searchTerm) {
		if (searchTerm != null && !searchTerm.isEmpty()) {
			return user.lastName.containsIgnoreCase(searchTerm)
					.or(user.firstName.containsIgnoreCase(searchTerm)).and(isActive());
		} else {
			return isActive();
		}
	}

	private static Predicate isActive() {

		return user.isActive.eq(true);
	}

}
