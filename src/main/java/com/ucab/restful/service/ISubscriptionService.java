package com.ucab.restful.service;

import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.Subscription;
import com.ucab.restful.data.model.User;

public interface ISubscriptionService {
	
	@Transactional(readOnly=true)
	List<User> getUserByRelation(UUID userId, Boolean subs) throws CustomBaseException;
	
	@Transactional(rollbackFor = Exception.class)
	Subscription createSubscription(Subscription subscription) throws CustomBaseException;
	
	@Transactional(readOnly=true)
	String deleteSubscription(Subscription subcription) throws CustomBaseException;

}
