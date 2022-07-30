package com.mydemo.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;


@ExtendWith(OutputCaptureExtension.class)
@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads(CapturedOutput output) {
//		Assertions.assertThat(output.getOut().contains("BirthDeathRate{" +
//				"id=1" +
//				", period=2020" + '\'' +
//				", birth_or_death=birth" + '\'' +
//				", region=Shanghai" + '\'' +
//				", count=1000" +
//				'}'));
//		System.out.println(output.getOut());
	}

}
