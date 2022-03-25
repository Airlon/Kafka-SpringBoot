package com.student.kafka.producer.eventos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.student.kafkaproducer.AplicacaoProducer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProducerEvento {
	
	private final Producer<String, String> producer;

	public ProducerEvento() {
		producer = criarProducer();
	}
	
	private Producer<String, String> criarProducer() {
		if(producer != null) {
			return producer;
		}
		
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("serializer.class", "kafka.serializer.DefaultEncoder");
		return new KafkaProducer<String, String>(properties);
		
	}
	
	public void executar() {
		
		String chave = UUID.randomUUID().toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String mensagem = sdf.format(new Date());
		mensagem += "|" + chave;
		mensagem += "|NOVA MENSAGEM";
		
		log.info("Iniciando o envio de mensagem");
		ProducerRecord<String, String> record = new ProducerRecord<String, String>("RegistroEvento", chave, mensagem);
		producer.send(record);
		producer.flush();
		producer.close();
		log.info("Mensagem enviada com sucesso [{}]", mensagem);
	}
}
