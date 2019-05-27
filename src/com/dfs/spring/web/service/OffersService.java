package com.dfs.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.dfs.spring.web.dao.Offer;
import com.dfs.spring.web.dao.OffersDao;

@Service("offersService")
public class OffersService {
	
	private OffersDao offersDao;
	
	
	@Autowired
	public void setOffersDAO(OffersDao offersDao) {
		this.offersDao = offersDao;
	}


	public List<Offer> getCurrent() {
		return offersDao.getOffers();
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	public void create(Offer offer) {
		offersDao.create(offer);
	}


	public void throwTestException() {
		// TODO Auto-generated method stub
		offersDao.getOffer(99999);
	}


	public boolean hasOffer(String name) {
		if(name==null) return false;
		List<Offer> offers = offersDao.getOffers(name);
		if(offers.size()==0) {
			return false;
		}
		return true;
	}


	public Offer getOffer(String username) {
		if(username==null) {
			return null;
		}
		List<Offer> offers = offersDao.getOffers(username);
		if(offers.size()==0) {
			return null;
		}
		return offers.get(0);
	}


	public void saveOrUpdate(Offer offer) {
		if(offer.getId() != 0) {
			offersDao.update(offer);
		}else {
			offersDao.create(offer);
		}
	}


	public void delete(int id) {
		offersDao.delete(id);
		
	}
}
