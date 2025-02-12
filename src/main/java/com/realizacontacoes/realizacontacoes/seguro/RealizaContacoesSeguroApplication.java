package com.realizacontacoes.realizacontacoes.seguro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(scanBasePackages = {"com.realizacontacoes.realizacontacoes.seguro.domain.ports"})
//@ComponentScan(basePackages = {"com.realizacontacoes.realizacontacoes"})

@EnableFeignClients
public class RealizaContacoesSeguroApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealizaContacoesSeguroApplication.class, args);
	}

}
