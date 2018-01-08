package com.ucab.restful.repository;

import java.util.List;
import java.util.UUID;

import com.ucab.restful.data.model.User;

public interface SubscriptionRepositoryCustom {
	
	List<User> getUsersInSubscription(UUID userId, Boolean subs);  

}
