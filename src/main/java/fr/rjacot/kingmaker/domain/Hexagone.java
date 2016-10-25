package fr.rjacot.kingmaker.domain;

public class Hexagone {

	private Integer idHexagone;
	private Integer x;
	private Integer y;
	private String nom;
	private Boolean visible;
	private Boolean royaume;
	private Boolean route;
	
	public Hexagone() {
		// TODO Auto-generated constructor stub
	}

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
