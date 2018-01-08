package com.ucab.restful.service;

import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.commons.exceptions.CustomDataBaseOperationException;
import com.ucab.restful.data.model.Subscription;
import com.ucab.restful.data.model.User;
import com.ucab.restful.repository.SubscriptionRepository;

@Service("ISubscriptionService")
public class SubscriptionService implements ISubscriptionService {
	
	@Autowired
	SubscriptionRepository subscriptionRepository;
	
	Logger log = LogManager.getLogger();

	@Override
	public List<User> getUserByRelation(UUID userId, Boolean subs) throws CustomBaseException {
		try {
			return subscriptionRepository.getUsersInSubscription(userId, subs);
		} catch (Exception e) {
			log.error("Error while trying finding subscription users \n Error: " + e.getMessage());
			throw new CustomDataBaseOperationException(
					"Error while trying finding subscription users \n Error: " + e.getMessage());
		}
	}

	@Override
	public Subscription createSubscription(Subscription subscription) throws CustomBaseException {
		try {
			return subscriptionRepository.save(subscription);
		} catch (Exception e) {
			log.error("Error while trying save subscription  \n Error: " + e.getMessage());
			throw new CustomDataBaseOperationException(
					"Error while trying save subscription users \n Error: " + e.getMessage());
		}
	}

}
