package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class Responsespec {

	
	
	
	   public static ResponseSpecification successspec(){
	        return new ResponseSpecBuilder()
	                .expectStatusCode(200)
	                .build();
	    }

	    public static ResponseSpecification createspec(){
	        return new ResponseSpecBuilder()
	                .expectStatusCode(201)
	                .build();
	    }

	    public static ResponseSpecification deletespec(){
	        return new ResponseSpecBuilder()
	        		.expectStatusCode(200)
	                //.expectStatusCode(204)
	                //here 204 comes only for real api but we are using fake api so i cant dekete so we ned to add 200
	                .build();
	    }

	    public static ResponseSpecification patchspec(){
	        return new ResponseSpecBuilder()
	                .expectStatusCode(200)
	                .build();
	    }
}



