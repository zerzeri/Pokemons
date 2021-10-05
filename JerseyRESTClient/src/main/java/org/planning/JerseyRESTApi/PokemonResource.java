package org.planning.JerseyRESTApi;

import java.util.List;

import org.planning.JerseyRESTApi.Pokemon;
import org.planning.JerseyRESTApi.PokemonService;

import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class PokemonResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(PokemonResource.class);

  private final PokemonService pokemonService = new PokemonService();

  public Router getSubRouter(final Vertx vertx) {
    final Router subRouter = Router.router(vertx);

    // Body handler
    subRouter.route("/*").handler(BodyHandler.create());

    // Routes
    subRouter.get("/").handler(this::getAllPokemons);
//    subRouter.get("/per_page=:per_page").handler(this::getPagePokemons);
    subRouter.get("/page=:page&per_page=:per_page").handler(this::getPagePokemons);
    subRouter.get("/id=:id").handler(this::getOnePokemon);
    subRouter.post("/").handler(this::createOnePokemon);
    subRouter.put("/:id").handler(this::updateOnePokemon);
    subRouter.delete("/:id").handler(this::deleteOnePokemon);

    return subRouter;
  }

  private void getAllPokemons(final RoutingContext routingContext) {
    LOGGER.info("Dans getAllPokemons...");

    final List<Pokemon> pokemons = pokemonService.findAll();

    final JsonObject jsonResponse = new JsonObject();
    jsonResponse.put("pokemons", pokemons);

    routingContext.response()
        .setStatusCode(200)
        .putHeader("content-type", "application/json")
        .end(Json.encode(jsonResponse));
  }
  
  private void getPagePokemons(final RoutingContext routingContext) {
	    LOGGER.info("Dans getAllPokemons...");

	    final Integer perPages = Integer.valueOf(routingContext.request().getParam("per_page"));
	    final Integer nbPages = Integer.valueOf(routingContext.request().getParam("page"));

	    final List<Pokemon> pokemons = pokemonService.findAllFilter(perPages, nbPages);

	    final JsonObject jsonResponse = new JsonObject();
	    jsonResponse.put("pokemons", pokemons);

	    routingContext.response()
	        .setStatusCode(200)
	        .putHeader("content-type", "application/json")
	        .end(Json.encode(jsonResponse));
	  }

  private void getOnePokemon(final RoutingContext routingContext) {
    LOGGER.info("Dans getOnePokemon...");

    final String id = routingContext.request().getParam("id");

    final Pokemon pokemon = pokemonService.findById(id);

    if (pokemon == null) {
      final JsonObject errorJsonResponse = new JsonObject();
      errorJsonResponse.put("error", "No pokemon can be found for the specified id:" + id);
      errorJsonResponse.put("id", id);

      routingContext.response()
          .setStatusCode(404)
          .putHeader("content-type", "application/json")
          .end(Json.encode(errorJsonResponse));
      return;
    }
    routingContext.response()
        .setStatusCode(200)
        .putHeader("content-type", "application/json")
        .end(Json.encode(pokemon));
  }
  private void createOnePokemon(final RoutingContext routingContext) {
    LOGGER.info("Dans createOnePokemon...");
    final JsonObject body = routingContext.getBodyAsJson();
    final String name = body.getString("name");
    final String type1 = body.getString("type1");
    final String type2 = body.getString("type2");
    final Integer total = body.getInteger("total");
    final Integer hp = body.getInteger("hp");
    final Integer attack = body.getInteger("attack");
    final Integer defense = body.getInteger("defense");
    final Integer spAtk = body.getInteger("spAtk");
    final Integer spDef = body.getInteger("spDef");
    final Integer speed = body.getInteger("speed");
    final Short generation = body.getInteger("generation").shortValue();
    final boolean legendary = body.getString("legendary").matches("true");
    // TODO Vérification des champs...
    final Pokemon pokemon = new Pokemon(null, name, type1, type2, total, hp, attack, defense,
    		spAtk, spDef, speed, generation, legendary);
    final Pokemon createdPokemon = pokemonService.add(pokemon);
    routingContext.response()
        .setStatusCode(201)
        .putHeader("content-type", "application/json")
        .end(Json.encode(createdPokemon));
  }
  private void updateOnePokemon(final RoutingContext routingContext) {
    LOGGER.info("Dans updateOnePokemon...");
    final String id = routingContext.request().getParam("id");
    final JsonObject body = routingContext.getBodyAsJson();
    final String name = body.getString("name");
    final String type1 = body.getString("type1");
    final String type2 = body.getString("type2");
    final Integer total = body.getInteger("total");
    final Integer hp = body.getInteger("hp");
    final Integer attack = body.getInteger("attack");
    final Integer defense = body.getInteger("defense");
    final Integer spAtk = body.getInteger("spAtk");
    final Integer spDef = body.getInteger("spDef");
    final Integer speed = body.getInteger("speed");
    final Short generation = body.getInteger("generation").shortValue();
    final boolean legendary = body.getString("legendary")=="true";
    // TODO Vérification des champs...
    final Pokemon pokemon = new Pokemon(id, name, type1, type2, total, hp, attack, defense,
    		spAtk, spDef, speed, generation, legendary);
    final Pokemon updatedPokemon = pokemonService.update(pokemon);
    routingContext.response()
        .setStatusCode(200)
        .putHeader("content-type", "application/json")
        .end(Json.encode(updatedPokemon));
  }
  private void deleteOnePokemon(final RoutingContext routingContext) {
    LOGGER.info("Dans deleteOnePokemon...");
    final String id = routingContext.request().getParam("id");
    pokemonService.remove(id);
    routingContext.response()
        .setStatusCode(200)
        .putHeader("content-type", "application/json")
        .end();
  }
}