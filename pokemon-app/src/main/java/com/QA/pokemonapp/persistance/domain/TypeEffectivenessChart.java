package com.QA.pokemonapp.persistance.domain;

import java.util.List;

import com.QA.pokemonapp.constantsandenums.ETypes;

public class TypeEffectivenessChart {
	//3 lists which contain types which this type does double, half or no damage to
	//no list for 1x damage to save processing
	private List<ETypes> doesDoubleDamage;
	private List<ETypes> doesHalfDamage;
	private List<ETypes> doesNoDamage;
	
	private ETypes thisType;
	
	public TypeEffectivenessChart(){
		
	}

	public TypeEffectivenessChart(ETypes thisType, List<ETypes> doesDoubleDamage, List<ETypes> doesHalfDamage,
			List<ETypes> doesNoDamage) {
		super();
		this.thisType = thisType;
		this.doesDoubleDamage = doesDoubleDamage;
		this.doesHalfDamage = doesHalfDamage;
		this.doesNoDamage = doesNoDamage;
	}
	
	
	public List<ETypes> doubleDamage() {
		return this.doesDoubleDamage;
	}
	
	public List<ETypes> halfDamage() {
		return this.doesHalfDamage;
	}
	
	public List<ETypes> noDamage() {
		return this.doesNoDamage;
	}

	public ETypes getThisType() {
		return thisType;
	}

	public void setThisType(ETypes thisType) {
		this.thisType = thisType;
	}
}
