package com.web.blog.service.address;

import com.web.blog.model.address.Address;

public interface AddressService {
	public Address findAddressBySiAndGu(final String si, final String gu);
}
