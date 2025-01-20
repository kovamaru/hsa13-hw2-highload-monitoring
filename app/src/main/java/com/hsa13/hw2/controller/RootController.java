package com.hsa13.hw2.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/")
public class RootController {

  @Get
  public String helloWorld() {
    return "Привіт =^_^=";
  }
}
