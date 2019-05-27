package com.dfs.web.test.tests;

import static org.junit.Assert.*;

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

import com.dfs.spring.web.dao.User;
import com.dfs.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/dfs/spring/web/config/dao-context.xml",
		"classpath:com/dfs/spring/web/config/security-context.xml",
		"classpath:com/dfs/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
		jdbc.execute("delete from authorities");
		
	}
	
	@Test
	public void testCreateUser() {
		User user = new User("robrrrrr","hellohello",true,"user","rob@trof.com");
		assertTrue("User creation should return true",usersDao.create(user));
		List<User> users = usersDao.getAllUsers();
		assertEquals("Number of users should be 1.",1,users.size());
		assertTrue("User should exist.",usersDao.exists(user.getUsername()));
		assertEquals("Created user should be identical to retrieved user",user,users.get(0));
	}
}
