package com.calculator.runner

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber.class)

@CucumberOptions (format = "pretty", features = "classpath:cucumber/calculator.feature")

class RunCalculatorTest {
}
