package com.mydemo.demo;

import com.mydemo.demo.Entity.BirthDeathRate;
import com.mydemo.demo.Mapper.BirthDeathRateMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {



	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	private final BirthDeathRateMapper birthDeathRateMapper;
	public DemoApplication(BirthDeathRateMapper birthDeathRateMapper) {
		this.birthDeathRateMapper = birthDeathRateMapper;
	}

	@Bean
	CommandLineRunner commandLineRunner() {
		return args -> {
			BirthDeathRate bdr=new BirthDeathRate();
			bdr.setPeriod("2020");
			bdr.setBirth_or_death("birth");
			bdr.setRegion("Shanghai");
			bdr.setCount(1000);
			birthDeathRateMapper.insert(bdr);
			System.out.println(this.birthDeathRateMapper.findById(bdr.getId()));
		};
	}

}
