package com.ucab.restful.service;

import java.io.IOException;
import java.util.List;

import com.ucab.restful.commons.exceptions.CustomBaseException;
import com.ucab.restful.data.model.User;

public interface IGoogleService {

	List<String> fileNames(User user) throws IOException, CustomBaseException;
}
 