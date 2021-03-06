package com.ucab.restful.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.pojo.ApiStage;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.Subscription;
import com.ucab.restful.dto.response.SimpleResponseStructure;
import com.ucab.restful.service.ISubscriptionService;

@RestController
@Api(name = "Subscription Services", description = "Services to manage Subscription", visibility = ApiVisibility.PUBLIC, stage = ApiStage.ALPHA)
public class SubscriptionController extends CustomBaseController{

	Logger logger = LogManager.getLogger();

	private ISubscriptionService subscriptionService;

	@Autowired
	public SubscriptionController(ISubscriptionService subscriptionService) {
		super();
		this.subscriptionService = subscriptionService;
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value="/subscribe",method = RequestMethod.POST)
	@ApiMethod(description = "Cretate a new subscription", summary = "CREATE SUBSCRIPTION")
	public ResponseEntity<SimpleResponseStructure<Subscription>> addSubcription(@RequestBody Subscription subscription)
			throws CustomBaseException {
		subscription = subscriptionService.createSubscription(subscription);

		SimpleResponseStructure<Subscription> response = new SimpleResponseStructure<>();

		response.setData(subscription);
		logger.debug("Added:: " + subscription);

		return ResponseEntity.ok(response);
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/unsubscribe", method = RequestMethod.POST)
	@ApiMethod(description = "Remove a subscription", summary = "DELETE SUBSCRIPTION")
	public ResponseEntity<SimpleResponseStructure<String>> deleteSubscription(@RequestBody Subscription subscription)
			throws CustomBaseException {

		SimpleResponseStructure<String> response = new SimpleResponseStructure<>();

		response.setData(subscriptionService.deleteSubscription(subscription));
		logger.debug("removed subscription");

		return ResponseEntity.ok(response);
	}


}
