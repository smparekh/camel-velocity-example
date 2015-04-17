package com.redhat.examples.camel_velocity_example;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class VelocityRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("file:///home/sparekh/Fuse/inbox?noop=true&charset=UTF-8")
			.setHeader("firstName", constant("World"))
			.process(new Processor() {
			    public void process(Exchange exchange) throws Exception {
			        String payload = exchange.getIn().getBody(String.class);
			        // do something with the payload and/or exchange here
			       exchange.getIn().setBody(payload.concat(", test message addition"));
			   }
			})
			.log("Message sent to template: ${body}")
			.to("velocity:ResponseTemplate.vm?contentCache=true")
			.to("file:///home/sparekh/Fuse/outbox");
	}
	
}