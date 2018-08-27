package com.example.login;

import com.example.login.pojo.Log;
import com.example.login.pojo.User;
import com.example.login.service.LogService;
import com.example.login.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginApplicationTests {

	@Autowired
	private LogService logService;

	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
		List<Log> all = logService.getAll();
		System.out.println(all.toString());
	}

	@Test
	public void testUser() {
		User user = userService.getOneByUserName("admin");
		System.out.println(user.toString());
	}

}
