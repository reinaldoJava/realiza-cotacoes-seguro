package com.realizacontacoes.realizacontacoes.seguro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ComponentScan(basePackages = {"com.realizacontacoes.realizacontacoes.seguro"})
@EnableFeignClients
public class RealizaContacoesSeguroApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealizaContacoesSeguroApplication.class, args);
	}

}
