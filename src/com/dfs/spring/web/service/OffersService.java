package com.dfs.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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


	public void create(Offer offer) {
		offersDao.create(offer);
	}


	public void throwTestException() {
		// TODO Auto-generated method stub
		offersDao.getOffer(99999);
	}
}
