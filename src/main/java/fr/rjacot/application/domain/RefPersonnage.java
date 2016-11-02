package fr.rjacot.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ref_personnage")
public class RefPersonnage {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_personnage")
	private Integer idPersonnage;
	private String nom;

	public Integer getIdPersonnage() {
		return idPersonnage;
	}

	public void setIdPersonnage(Integer idPersonnage) {
		this.idPersonnage = idPersonnage;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
