package com.ucab.restful.service;

import java.util.List;
import java.util.UUID;

import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.Subscription;
import com.ucab.restful.data.model.User;

public interface ISubscriptionService {
	
	List<User> getUserByRelation(UUID userId, Boolean subs) throws CustomBaseException;
	
	Subscription createSubscription(Subscription subscription) throws CustomBaseException;
	
	String deleteSubscription(Subscription subcription) throws CustomBaseException;

}
