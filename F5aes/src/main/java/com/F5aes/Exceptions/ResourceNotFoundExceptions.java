package com.F5aes.Exceptions;

import org.springframework.web.client.ResourceAccessException;

public class ResourceNotFoundExceptions extends ResourceAccessException {
	public ResourceNotFoundExceptions(String msg) {
		super(msg);
	}
}
