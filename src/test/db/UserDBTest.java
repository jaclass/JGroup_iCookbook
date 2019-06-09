package test.db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.db.DBController;
import entity.User;

/**
 * Test the methods about the users.
 * 
 * @author JGroup
 *
 */
class UserDBTest {

	@Test
	void testInsertUser() {
		User alice = new User("alice", "980102");
		User alice2 = new User("alice", "980103");
		DBController.insertUser(alice);
		assertTrue(DBController.insertUser(alice2) == 0);
	}
	
	@Test
	void testLoginUser() {
		User alicer = new User("aliceer", "980102");
		User aliceer = new User("aliceer", "980102");
		User wrong_aliceer = new User("aliceer", "980202");
		DBController.insertUser(alicer);
		assertTrue(DBController.loginUser(aliceer).equals("aliceer"));
		assertTrue(DBController.loginUser(wrong_aliceer) == null);
	}

}
