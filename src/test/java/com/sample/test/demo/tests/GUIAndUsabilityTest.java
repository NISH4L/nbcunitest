package com.sample.test.demo.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.sample.test.demo.TestBase;


public class GUIAndUsabilityTest extends TestBase {
	
	//First we are making sure if we are in the desired page
	@Test
	public void pageTitleTest() {
		String title = driver.getTitle();
		System.out.println(title);
	
		assertEquals(title, "Pizza Order Form");
	}
	
	//We will use this method whenever we need to fill up form
	public void fillFields() {
		pizzaDropdown.selectByValue("Small 6 Slices - 1 topping");
		toppings1Dropdown.selectByIndex(3);
		toppings2Dropdown.selectByIndex(4);
		driver.findElement(By.id("pizza1Qty")).sendKeys("4");
		driver.findElement(By.id("name")).sendKeys("random name");
		driver.findElement(By.id("email")).sendKeys("randomname@gmail.com");
		driver.findElement(By.id("phone")).sendKeys("980000000");
	}
	
	//Topping selection dropdown test.
	//If no topping pizza is selected, no dropdown option shouldn't be available
	@Test
	public void noToppingsSelectionTest() throws InterruptedException {
		Boolean toppings1Present = driver.findElement(By.xpath("//div[@id='pizza1']//select[@class='toppings1']")).isEnabled();
		pizzaDropdown.selectByValue("Large 10 Slices - no toppings");
		if(toppings1Present == true) {
			System.out.println("Topping option appearing");
		} else {
			System.out.println("Topping option not available");
		}
			
	}
	
	//If atleast one topping is selected, only one dropdown option should be available
	@Test
	public void oneToppingSelectionTest() {
		Boolean toppings2Present = driver.findElement(By.xpath("//div[@id='pizza1']//select[@class='toppings2']")).isEnabled();
		pizzaDropdown.selectByValue("Small 6 Slices - 1 topping");
		if(toppings2Present == true) {
			System.out.println("Topping option appearing");
		} else {
			System.out.println("Topping option not available");
		}
		
	}
	
	//Here we are assuming 10 minimum pizzas are allowed in an order
	@Test
	public void quantityLimitTest() {
		Boolean costDisplayed = driver.findElement(By.id("pizza1Cost")).isDisplayed();
		driver.findElement(By.id("pizza1Qty")).sendKeys("12");
		if(costDisplayed == true ) {
			System.out.println("More than 10 pizzas/order is allowed");
		} else {
			System.out.println("More than 10 pizzas/order is not allowed");
		}
		
	}
	
	//Along with test, this also tests for page's popup message
	@Test
	public void mandatoryFieldTest() {
		driver.findElement(By.id("email")).sendKeys("randomname@gmail.com");
		driver.findElement(By.id("placeOrder")).click();
		String  actualMessage = driver.findElement(By.xpath("//div[@id='dialog']/p")).getText();
		String exptectedMessage = "Missing name\n"+ "Missing phone number";
		
		assertEquals(actualMessage, exptectedMessage);
	}
	
	//This tests if any input field is allowing unnecessary special characters
	@Test
	public void inputFieldTest() {
		fillFields();
		driver.findElement(By.id("name")).sendKeys("$hf#%$hlk DSJlj$#j99");
		driver.findElement(By.id("email")).sendKeys("998#$@$ 9%($)# ");
		driver.findElement(By.id("phone")).sendKeys("lskdflkd (*FDHk");
		driver.findElement(By.id("placeOrder")).click();
		
		fail("Thank you for your order! TOTAL: 30 Small 6 Slices - 1 topping");
		
	}
	
	//Testing radio buttons
	@Test
	public void radioButtonTest() {
		  Boolean bothEnabled = false;
		  WebElement creditCardPayment = driver.findElement(By.id("ccpayment"));
		  WebElement cashPayment = driver.findElement(By.id("cashpayment"));
		  creditCardPayment.click();
		  cashPayment.click();
		  if(creditCardPayment.isEnabled() && cashPayment.isEnabled()) {
			  bothEnabled = true;
		  }
		  
		  assertFalse(bothEnabled);	
	}
	
	//Testing if reset button works on all field
	@Test
	public void resetButtonTest() {
		fillFields();
		driver.findElement(By.id("ccpayment")).click();
		//Finally, click the reset button
		driver.findElement(By.id("reset")).click();
		
		assertEquals(toppings1Dropdown, "Choose a Toppings 1");
		
	}
	//Testing if the order places correctly with correct information as submitted
	//Happypath
	@Test
	public void submitButtonTest() throws InterruptedException {
		fillFields();
		driver.findElement(By.id("ccpayment")).click();
		driver.findElement(By.id("placeOrder")).click();
		//After placing order
		String expectedMessage = "Thank you for your order! TOTAL: 30 Small 6 Slices - 1 topping";
    		String actualMessage = driver.findElement(By.xpath("//div[@id='dialog']/p")).getText();
    	
    		assertEquals(expectedMessage,  actualMessage);	
	}
	

}
