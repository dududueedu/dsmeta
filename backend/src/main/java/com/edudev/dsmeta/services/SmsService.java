package com.edudev.dsmeta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.edudev.dsmeta.entities.Sale;
import com.edudev.dsmeta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	@Value("${twilio.phone.to}")
	private String twilioPhoneTo;
	
	@Autowired
	private SaleRepository saleRepo;

	public void sendSms(Long salaId) {
		
		Sale sale = saleRepo.findById(salaId).get();
	
		String date =  sale.getDate().getMonthValue() +"/"+ sale.getDate().getYear();
				
		String amount = String.format("%.2f", sale.getAmount());
		String msg = "O vendedor " + sale.getSellerName() + " foi destaque em " + date +
				" com um total de R$ " + amount + " em vendas.";
		
		
		Twilio.init(twilioSid, twilioKey);

		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

		Message message = Message.creator(to, from, msg).create();

		System.out.println("ENVIOU "+message.getSid());
	}
}