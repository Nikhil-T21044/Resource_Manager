package com.rmgr.rmgr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.concurrent.TimeoutException;


@Service
public class ClientService {

  private static final Logger LOG = LoggerFactory.getLogger(ClientService.class);
  private final WebClient webClient;
  private final ReactiveCircuitBreaker clientCircuitBreaker;

  public ClientService(ReactiveCircuitBreakerFactory circuitBreakerFactory) {
    this.webClient = WebClient.builder().baseUrl("http://localhost:9001").build();
//    CircuitBreakerConfig config = CircuitBreakerConfig.custom()
//            .failureRateThreshold(60).slidingWindow(5,5, CircuitBreakerConfig.SlidingWindowType.COUNT_BASED).permittedNumberOfCallsInHalfOpenState(0).waitDurationInOpenState(Duration.ofMillis(20000)).build();
//    CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);

    this.clientCircuitBreaker = circuitBreakerFactory.create("apiGateway");
    //this.clientCircuitBreaker=registry.circuitBreaker("my_breaker");
  }
      public Mono<String> getData(Map<String, String> pathVarsMap) {
        Mono apiGateway;
        try {
//          try to call the object constructor of EC2 if pass then ok else return the error message
          JobManager jm = new JobManager(pathVarsMap.get("name"),pathVarsMap.get("duration"),pathVarsMap.get("numofresources"));
          //TODO: save in apigateway appropriate response
          apiGateway = Mono.just("success");

    }catch (Exception e){
          apiGateway = Mono.error(new TimeoutException());
    }

//        Mono apiGateway = Mono.just("df");
//        Mono apiGateway = webClient.get().uri("/").retrieve().bodyToMono(String.class).flatMap(response->
//                {
//                  //System.out.println(response);
//                  if(response.compareTo("Poor")==0)
//                    return Mono.error(new TimeoutException());
//                  else return Mono.just(response);
//                });

        return clientCircuitBreaker.run(apiGateway, throwable -> {
          LOG.warn("Error making request to API Gateway");
          return Mono.just("Video Service is down. Pls try again later");
        });}

}
