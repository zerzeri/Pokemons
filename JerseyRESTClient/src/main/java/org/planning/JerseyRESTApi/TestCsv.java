package org.planning.JerseyRESTApi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class TestCsv {

	public static void main(String[] args) {
        String csvFile = "pokemon.csv";
        try {
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			String line = "";
			StringTokenizer st = null;
			Pokemon poke = new Pokemon();
			String[] pokemonValues = new String[12];
			br.readLine();
			while((line = br.readLine())!= null) {
				pokemonValues = line.split(",");
//                st = new StringTokenizer(line, ",");
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
				
//				System.out.println(poke.getName() + " " + poke.getType1() + " " + poke.getType2() + " ");				
				System.out.println(poke);				
//				System.out.println(line);				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
