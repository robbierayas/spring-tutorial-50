package com.dfs.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dfs.spring.web.dao.Offer;
import com.dfs.spring.web.dao.OffersDao;
import com.dfs.spring.web.dao.User;
import com.dfs.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/dfs/spring/web/config/dao-context.xml",
		"classpath:com/dfs/spring/web/config/security-context.xml",
		"classpath:com/dfs/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class OfferDaoTests {

	@Autowired
	private OffersDao offersDao;

	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
	}

	@Test
	public void testOffers() {

		User user = new User("johnwpurcell",  "John W Purcell", "hellohello", true, "user", "john@dfs.com");

		assertTrue("User creation should return true", usersDao.create(user));

		Offer offer = new Offer(user, "This is a test offer.");

		assertTrue("Offer creation should return true", offersDao.create(offer));

		List<Offer> offers = offersDao.getOffers();

		assertEquals("Should be one offer in database.", 1, offers.size());

		assertEquals("Retrieved offer should match created offer.", offer,
				offers.get(0));

		// Get the offer with ID filled in.
		offer = offers.get(0);

		offer.setText("Updated offer text.");
		assertTrue("Offer update should return true", offersDao.update(offer));

		Offer updated = offersDao.getOffer(offer.getId());

		assertEquals("Updated offer should match retrieved updated offer",
				offer, updated);
		
		
		//Test get by id
		
		Offer offer2= new Offer(user,"Thisis a test offer.");
		assertTrue("Offer creation should return true",offersDao.create(offer2));
		
		List<Offer> userOffers = offersDao.getOffers(user.getUsername());
		assertEquals("Should be 2 offers for user",2,userOffers.size());
		List<Offer> secondList=offersDao.getOffers();
		for(Offer current:secondList) {
			Offer retrieved = offersDao.getOffer(current.getId());
			assertEquals("Offer by Id should match offer from list.",current,retrieved);
		}
		
		
		//Test deletion

		offersDao.delete(offer.getId());

		List<Offer> finalList = offersDao.getOffers();

		assertEquals("Offers lists should be empty.", 1, finalList.size());
	}

}
