package fr.rjacot.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hexagone")
public class Hexagone {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_hexagone")
	private Integer idHexagone;
	private Integer x;
	private Integer y;
	
	private String nom;
	
	private Boolean visible;
	private Boolean royaume;
	private Boolean route;
	
	public Integer getIdHexagone() {
		return idHexagone;
	}

	public void setIdHexagone(Integer idHexagone) {
		this.idHexagone = idHexagone;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Boolean getRoyaume() {
		return royaume;
	}

	public void setRoyaume(Boolean royaume) {
		this.royaume = royaume;
	}

	public Boolean getRoute() {
		return route;
	}

	public void setRoute(Boolean route) {
		this.route = route;
	}
}
