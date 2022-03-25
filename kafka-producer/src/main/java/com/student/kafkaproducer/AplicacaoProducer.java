package com.student.kafkaproducer;

import com.student.kafka.producer.eventos.ProducerEvento;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AplicacaoProducer {

	public static void main(String[] args) {
		
		 AplicacaoProducer aplicacao = new AplicacaoProducer();
	        aplicacao.iniciar();
	    }

	    private void iniciar() {
	        log.info("Iniciando a aplicação");
	        ProducerEvento produtor = new ProducerEvento();
	        produtor.executar();
	    }
	}