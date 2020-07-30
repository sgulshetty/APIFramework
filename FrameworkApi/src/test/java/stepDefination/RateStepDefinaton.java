package stepDefination;

import static io.restassured.RestAssured.given;
import org.junit.Assert;
import Utils.Utilities;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RateStepDefinaton {

	public RequestSpecification request;
	public Response response;

	@Given("^The API URI to get the foreign rates$")
	public void the_API_URI_to_get_the_foreign_rates() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		request=given().pathParam("content","latest");

	}

	@When("^User submit the GET request$")
	public void user_submit_the_GET_request() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		response=request.get(RestAssured.baseURI+RestAssured.basePath);

	}

	@Given("^the API to get the foreign exchange rates for past date \"([^\"]*)\"$")
	public void the_API_to_get_the_foreign_exchange_rates_for_past_date(String date) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		request=given().pathParam("content",date);

	}

	@Then("^Response code received should be \"([^\"]*)\"$")
	public void response_Success_code_should_be(int responseCode) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(response.statusCode(), responseCode);   
	}


	@Given("^The API URI with \"([^\"]*)\" and \"([^\"]*)\" to get the foreign rate$")
	public void the_API_URI_with_and_to_get_the_foreign_rate(String base, String symbol) throws Throwable {
		request=given().pathParam("content","latest").queryParam("base",base).queryParam("symbols",symbol);

	}


	@Given("^the API to get the foreign exchange rates with \"([^\"]*)\" and \"([^\"]*)\" and \"([^\"]*)\"$")
	public void the_API_to_get_the_foreign_exchange_rates_with_and_and(String base, String symbol, String date) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		request=given().pathParam("content",date).queryParam("base",base).queryParam("symbols",symbol);

	}

	@Then("^latest foreign exchange rates should be received as response$")
	public void latest_foreign_exchange_rates_should_be_received_as_response() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String str=response.jsonPath().prettify();
		System.out.println(str);
	}

	@Then("^Validate the error in the response body \"([^\"]*)\"$")
	public void validate_the_error_in_the_response_body(String expectedMessage) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String actualMessage=response.jsonPath().get("error");
		Assert.assertEquals(expectedMessage, actualMessage);
	}

	
	@Then("^Validate the error in the response body \"([^\"]*)\" \"([^\"]*)\"$")
	public void validate_the_error_in_the_response_body(String expectedMessage, String datePattern) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		String actualMessage=response.jsonPath().get("error");
		String date=Utilities.getGMTDate("yyyy-MM-dd");
		Assert.assertEquals(expectedMessage+datePattern.replace("yyyy-MM-dd",date), actualMessage);
	}


	@Given("^the API URI with invalid Base \"([^\"]*)\"$")
	public void the_API_URI_with_invalid_Base(String base) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		request=given().pathParam("content","latest").queryParam("base",base);
	}

	@Given("^the API URI with invalid Symbol \"([^\"]*)\"$")
	public void the_API_URI_with_invalid_Symbol(String symbol) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		request=given().pathParam("content","latest").queryParam("symbols",symbol);
	}

}
