package com.ServiceNow.test;

import org.junit.runner.RunWith;

import com.ServiceNow.setup.Setup;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(strict = false,features = {Setup.sAPIUSERCREATION},  format = { "pretty","html:target/site/cucumber-pretty","html:target/cucumber-html-report", "json:target/cucumber.json" }, monochrome = false)
//@CucumberOptions(strict = false,features={Setup.sTATASKY},  format = { "pretty","html:target/site/cucumber-pretty","html:target/cucumber-html-report", "json:target/cucumber.json" }, monochrome = false)

public class API {

}
