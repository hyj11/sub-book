package com.sim;

import com.sim.model.TbBookInfo;
import com.sim.service.BookService;
import com.sim.service.SendMailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubBookApplicationTests {

	@Autowired
	private SendMailService sendMailService;

	@Test
	public void contextLoads() {
		sendMailService.mail();
	}

}
