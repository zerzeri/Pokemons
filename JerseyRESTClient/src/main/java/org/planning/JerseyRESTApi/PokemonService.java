package org.planning.JerseyRESTApi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class PokemonService {

	private Map<String, Pokemon> pokemons = new HashMap<String, Pokemon>();

	public PokemonService() {
		super();
		initPokemons();
	}
	// Initialisation des pokemons dans la BDD à partir du csv
	private void initPokemons() {
        String csvFile = "pokemon.csv";
        try {
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			String line = "";
			Pokemon poke = new Pokemon();
			String[] pokemonValues = new String[12];
			br.readLine();
			Integer idNum = 0;
			String id;
			while((line = br.readLine())!= null) {
				idNum++;
				// Réinitialisation du pokemon à ajouter
				poke = new Pokemon();
				
				pokemonValues = line.split(",");

				//Creation du pokemon
				id = "fr"+idNum;
				poke.setId(id);
				
				poke.setName(pokemonValues[1]);
				poke.setType1(pokemonValues[2]);
				poke.setType2(pokemonValues[3]);
				poke.setTotal(Integer.valueOf(pokemonValues[4]));
				poke.setHp(Integer.valueOf(pokemonValues[5]));
				poke.setAttack(Integer.valueOf(pokemonValues[6]));
				poke.setDefense(Integer.valueOf(pokemonValues[7]));
				poke.setSpAtk(Integer.valueOf(pokemonValues[8]));
				poke.setSpDef(Integer.valueOf(pokemonValues[9]));
				poke.setSpeed(Integer.valueOf(pokemonValues[10]));
				poke.setGeneration(Short.valueOf(pokemonValues[11]));
				poke.setLegendary(pokemonValues[12].equals("true"));
				
				// Ajouts dans la map
				pokemons.put(poke.getId(), poke);
								
			}			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Pokemon add(Pokemon pokemon) {
		final String id;
		if(pokemon.getId()==null) {id = "fr" + System.currentTimeMillis();} 
		else id = pokemon.getId();
		pokemon.setId(id);
		final Pokemon newpokemon = new Pokemon(id,
				pokemon.getName(),
				pokemon.getType1(),
				pokemon.getType2(),
				pokemon.getTotal(),
				pokemon.getHp(),
				pokemon.getAttack(),
				pokemon.getDefense(),
				pokemon.getSpAtk(),
				pokemon.getSpDef(),
				pokemon.getSpeed(),
				pokemon.getGeneration(),
				pokemon.isLegendary());
		pokemons.put(id, newpokemon);
		return newpokemon;
	}

	public List<Pokemon> findAll() {
		return pokemons.values().stream()
				.collect(Collectors.toList());
	}
	public List<Pokemon> findAllFilter(int perPages, int nbPages) {
		return pokemons.values().stream()
				.collect(Collectors.toList()).subList(0, nbPages*perPages);
	}
	public Pokemon findById(final String id) {
		return pokemons.get(id);
	}
	public Pokemon update(final Pokemon pokemon) {
		pokemons.put(pokemon.getId(), pokemon);
		return pokemon;
	}
	public void remove(final String id) {
		pokemons.remove(id);
	}

}
