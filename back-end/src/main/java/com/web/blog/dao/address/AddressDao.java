package com.web.blog.dao.address;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.blog.model.address.Address;

@Repository
public interface AddressDao extends JpaRepository<Address, String>{
	Optional<Address> findBySiAndGu(final String si, final String gu);
}
