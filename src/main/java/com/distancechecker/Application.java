package com.distancechecker;

import com.distancechecker.service.PaymentProcessFactoryImpl;
import com.distancechecker.service.PaymentProcessor;
import com.distancechecker.service.Tes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableFeignClients
public class Application implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Tes tes = Tes.getInstance();
		Tes tes1 = Tes.getInstance();

		PaymentProcessFactoryImpl paymentProcessFactory = new PaymentProcessFactoryImpl();
		PaymentProcessor payment = paymentProcessFactory.createPaymentProcessor("PIX");
		String formaDePagamento = payment.processPayment();
	}
}
