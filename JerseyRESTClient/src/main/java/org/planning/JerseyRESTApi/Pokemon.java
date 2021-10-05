package org.planning.JerseyRESTApi;


public class Pokemon {
	private String id, name, type1, type2;
	private Integer total, hp, attack, defense, spAtk, spDef, speed;
	private short generation;
	private boolean legendary;
	
	public Pokemon(String id, String name, String type1, String type2, Integer total, Integer hp, Integer attack,
			Integer defense, Integer spAtk, Integer spDef, Integer speed, short generation, boolean legendary) {
		super();
		this.id = id;
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;
		this.total = total;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.spAtk = spAtk;
		this.spDef = spDef;
		this.speed = speed;
		this.generation = generation;
		this.legendary = legendary;
	}
	
	public Pokemon() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getHp() {
		return hp;
	}

	public void setHp(Integer hp) {
		this.hp = hp;
	}

	public Integer getAttack() {
		return attack;
	}

	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	public Integer getDefense() {
		return defense;
	}

	public void setDefense(Integer defense) {
		this.defense = defense;
	}

	public Integer getSpAtk() {
		return spAtk;
	}

	public void setSpAtk(Integer spAtk) {
		this.spAtk = spAtk;
	}

	public Integer getSpDef() {
		return spDef;
	}

	public void setSpDef(Integer spDef) {
		this.spDef = spDef;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public short getGeneration() {
		return generation;
	}

	public void setGeneration(short generation) {
		this.generation = generation;
	}

	public boolean isLegendary() {
		return legendary;
	}

	public void setLegendary(boolean legendary) {
		this.legendary = legendary;
	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", name=" + name + ", type1=" + type1 + ", type2=" + type2 + ", total=" + total
				+ ", hp=" + hp + ", attack=" + attack + ", defense=" + defense + ", spAtk=" + spAtk + ", spDef=" + spDef
				+ ", speed=" + speed + ", generation=" + generation + ", legendary=" + legendary + "]";
	}
	
	
	

}
