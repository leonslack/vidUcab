package com.ucab.restful;

import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ucab.restful.commons.exceptions.CustomAlreadyExistsException;
import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.commons.exceptions.CustomNotFoundException;
import com.ucab.restful.data.model.Subscription;
import com.ucab.restful.data.model.User;
import com.ucab.restful.data.predicates.SubscriptionPredicates;
import com.ucab.restful.repository.SubscriptionRepository;
import com.ucab.restful.service.SubscriptionService;

@RunWith(MockitoJUnitRunner.class)
public class SubscriptionTests {

	@Mock
	SubscriptionRepository subscriptionRepository;

	@InjectMocks
	SubscriptionService service;

	@Test(expected = CustomAlreadyExistsException.class)
	public void testCreateSubscription() throws CustomBaseException {
		User owner = new User();
		User subscriber = new User();
		owner.setId(UUID.randomUUID());
		subscriber.setId(UUID.randomUUID());

		Subscription subscription = new Subscription();

		subscription.setOwner(owner);
		subscription.setSubscriber(subscriber);
		
		when(subscriptionRepository.findOne(SubscriptionPredicates.usersIdEq(owner.getId(), subscriber.getId())))
		.thenReturn(subscription);
		
		subscription = service.createSubscription(subscription);
		
	}
	
	@Test(expected = CustomNotFoundException.class)
	public void testDeleteSubscription() throws CustomBaseException {
		User owner = new User();
		User subscriber = new User();
		owner.setId(UUID.randomUUID());
		subscriber.setId(UUID.randomUUID());

		Subscription subscription = new Subscription();

		subscription.setOwner(owner);
		subscription.setSubscriber(subscriber);
		
		when(subscriptionRepository.findOne(SubscriptionPredicates.usersIdEq(owner.getId(), subscriber.getId())))
		.thenReturn(null);
		
		service.deleteSubscription(subscription);
		
	}

}
