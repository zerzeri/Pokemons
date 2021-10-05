package org.planning.JerseyRESTApi;

import io.vertx.core.Vertx;

/**
 * Hello world!
 *
 */
public class App 
{
	  public static void main(String[] args) {
		    System.out.println("App...");
		    final Vertx vertx = Vertx.vertx();
		    vertx.deployVerticle(new MyApiVerticle());
		  }

}
