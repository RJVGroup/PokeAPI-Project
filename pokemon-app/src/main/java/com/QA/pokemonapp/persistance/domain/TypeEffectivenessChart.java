package com.QA.pokemonapp.persistance.domain;

import java.util.List;

import com.QA.pokemonapp.constantsandenums.ETypes;

public class TypeEffectivenessChart {
	//3 lists which contain types which this type takes double, half or no damage from
	//no list for 1x damage to save processing
	private List<ETypes> takesDoubleDamage;
	private List<ETypes> takesHalfDamage;
	private List<ETypes> takesNoDamage;
	
	private ETypes thisType;
	
	public TypeEffectivenessChart(){
		
	}

	public TypeEffectivenessChart(ETypes thisType, List<ETypes> takesDoubleDamage, List<ETypes> takesHalfDamage,
			List<ETypes> takesNoDamage) {
		super();
		this.thisType = thisType;
		this.takesDoubleDamage = takesDoubleDamage;
		this.takesHalfDamage = takesHalfDamage;
		this.takesNoDamage = takesNoDamage;
	}
	
	
	public List<ETypes> doubleDamage() {
		return this.takesDoubleDamage;
	}
	
	public List<ETypes> halfDamage() {
		return this.takesHalfDamage;
	}
	
	public List<ETypes> noDamage() {
		return this.takesNoDamage;
	}

	public ETypes getThisType() {
		return thisType;
	}

	public void setThisType(ETypes thisType) {
		this.thisType = thisType;
	}
}
