package org.planning.JerseyRESTApi;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
public class MyApiVerticle extends AbstractVerticle {
  private static final Logger LOGGER = LoggerFactory.getLogger(MyApiVerticle.class);
  @Override
  public void start() throws Exception {
    LOGGER.info("Dans le start...");
    final Router router = Router.router(vertx);
    final PokemonResource pokemonResource = new PokemonResource();
    final Router pokemonSubRouter = pokemonResource.getSubRouter(vertx);
    router.mountSubRouter("/api/v1/pokemons", pokemonSubRouter);
    vertx.createHttpServer()
        .requestHandler(router)
        .listen(8080);
  }
  @Override
  public void stop() throws Exception {
    LOGGER.info("Dans le stop...");
  }
}