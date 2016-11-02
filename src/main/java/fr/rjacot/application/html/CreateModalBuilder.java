package fr.rjacot.application.html;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Id;

import org.apache.commons.lang.WordUtils;

public class CreateModalBuilder<T> {

	public final static int ADD = 1;
	public final static int EDIT = 2;

	private final static String DIV = "div";
	private final static String FORM = "form:form";
	private final static String CLASS = "class";
	private final static String ID = "id";
	private final static String TABINDEX = "tabindex";
	private final static String ROLE = "role";
	private final static String METHODE = "method";
	private final static String ACTION = "action";
	private final static String ARIA_LABELLEDBY = "aria-labelledby";
	private final static String BUTTON = "button";
	private final static String TYPE = "type";
	private final static String DATE_DISMISS = "data-dismiss";
	private final static String ARIA_LABEL = "aria-label";
	private final static String SPAN = "span";
	private final static String ARIA_HIDDEN = "aria_hidden";
	private final static String H4 = "h4";
	private final static String LABEL = "label";
	private final static String FOR = "for";
	private static final String INPUT = "form:input";
	private static final String PLACEHOLDER = "placeholder";

	private final StringBuilder html = new StringBuilder();
	private HashMap<String, String> replaceTexte = new HashMap<>();
	private Map<Integer, String> toClose = new HashMap<Integer, String>();
	private int offsetTab = 0;
	private Class<T> myClass;
	private int mode;

	String nomClasse = null;

	public CreateModalBuilder(Class<T> clazz, int mode) {

		this.nomClasse = clazz.getSimpleName();
		this.myClass = clazz;
		this.mode = mode;
		this.beginBuild();
	}

	public CreateModalBuilder(Class<T> clazz, int mode, HashMap<String, String> replaceTexte) {
		this.nomClasse = clazz.getSimpleName();
		this.myClass = clazz;
		this.mode = mode;
		this.replaceTexte = replaceTexte;
		this.beginBuild();
		
	}

	private void beginBuild() {
		String identifiantModal = null;

		switch (this.mode) {
		case ADD:
			identifiantModal = "add" + this.nomClasse;
		case EDIT:
			identifiantModal = "edit" + this.nomClasse;
		default:
			identifiantModal = "edit" + this.nomClasse;
		}

		this.addTag(DIV, add(CLASS, "modal fade"), add(ID, identifiantModal), add(TABINDEX, "-1"),
				add(ARIA_LABELLEDBY, "myModalLabel"));
		this.addTag(DIV, add(CLASS, "modal-dialog"), add(ROLE, "document"));
		this.addTag(DIV, add(CLASS, "modal-content"));
		this.addTag(FORM, add(CLASS, "form-horizontal"), add(METHODE, "post"), add(ACTION, this.nomClasse),
				add(ROLE, "form"));
		this.addTag(DIV, add(CLASS, "modal-header"));
		this.addTag(BUTTON, add(CLASS, "close"), add(TYPE, "button"), add(DATE_DISMISS, "modal"),
				add(ARIA_LABEL, "close"));
		this.addTagText(SPAN, "&times;", add(ARIA_HIDDEN, "true"));
		this.closeLevel();
		this.closeLevel();
		this.addTagText(H4, "Ajout d'un " + nomClasse, add(CLASS, "modal-title"), add(ID, "myModalLabel"));
		this.closeLevel();

		this.generateDynamicFields();

		this.addTag(DIV, add(CLASS, "modal-footer"));
		this.addTagText(BUTTON, "Annuler", add(CLASS, "btn btn-default"), add(TYPE, "button"),
				add(DATE_DISMISS, "modal"));
		this.addTagText(BUTTON, "Ajouter", add(CLASS, "btn btn-primary"), add(TYPE, "submit"));
	}

	private void generateDynamicFields() {

		for (Field f : this.myClass.getDeclaredFields()) {

			boolean toBuild = true;
			String attributName = f.getName();
			String inputName = "edit" + WordUtils.capitalize(attributName);
			String showName = attributName;

			if (this.replaceTexte.containsKey(attributName)) {
				showName = replaceTexte.get(attributName);
			}

			for (Annotation annotation : f.getAnnotations()) {
				if (annotation instanceof Id) {
					toBuild = false;
				}
			}

			if (toBuild) {

				this.addTag(DIV, add(CLASS, "modal-body"));
				this.addTag(DIV, add(CLASS, "form-group"));
				this.addTagText(LABEL, showName, add(CLASS, "col-sm-2 control-label"), add(FOR, inputName));
				this.closeLevel();
				this.addTag(DIV, add(CLASS, "col-sm-10"));
				this.addAutoTag(INPUT, add(TYPE, "text"), add(CLASS, "form-control"), add(ID, inputName),
						add(PLACEHOLDER, attributName));
				this.closeLevel();
				this.closeLevel();
				this.closeLevel();
			}
		}
	}

	/**
	 * Permet de fermer un niveau de Tag
	 */
	private void closeLevel() {
		this.html.deleteCharAt(this.html.length() - 1);
		this.html.append("</" + this.toClose.get(--offsetTab) + ">\n");

	}

	/**
	 * Ajoute un attribut
	 * 
	 * @param attributName
	 * @param valeur
	 * @return
	 */
	private String add(String attributName, String valeur) {

		return attributName + "=\"" + valeur + "\"";
	}

	/**
	 * Ajoute un Tag au builder à fermer directement
	 * 
	 * @param tagName
	 *            Nom du tag
	 * @param attributs
	 *            Liste d'attributs à associer au tag
	 */
	private void addAutoTag(String tagName, String... attributs) {
		this.addTag(tagName, attributs);
		this.html.deleteCharAt(this.html.length() - 1);
		this.html.deleteCharAt(this.html.length() - 1);
		this.html.append("\\>\n");
		this.toClose.get(--offsetTab);

	}

	/**
	 * Ajoute un Tag au builder
	 * 
	 * @param tagName
	 *            Nom du tag
	 * @param texte
	 *            Texte du tag
	 * @param attributs
	 *            Liste d'attributs à associer au tag
	 */
	private void addTagText(String tagName, String texte, String... attributs) {

		this.addTag(tagName, attributs);
		this.html.deleteCharAt(this.html.length() - 1);
		this.html.append(texte + "\n");
	}

	/**
	 * Ajoute un Tag au builder
	 * 
	 * @param tagName
	 *            Nom du tag
	 * @param attributs
	 *            Liste d'attributs à associer au tag
	 */
	private void addTag(String tagName, String... attributs) {

		for (int i = 0; i < this.offsetTab; i++) {
			this.html.append("\t");
		}

		this.html.append("<" + tagName);

		for (String attribut : attributs) {
			this.html.append(" " + attribut);
		}

		this.html.append(">\n");
		this.toClose.put(this.offsetTab++, tagName);
	}

	/**
	 * Ferme toutes les balises ouverte et retourne la chaîne du builder
	 * 
	 * @return
	 */
	public String renderer() {

		for (int pos = this.offsetTab - 1; pos >= 0; pos--) {
			for (int i = 0; i < pos; i++) {
				this.html.append("\t");
			}

			this.html.append("</" + this.toClose.get(pos) + ">\n");
		}

		return html.toString();
	}

	/**
	 * Défini la map de remplacement des attributs
	 * 
	 * @param map
	 */
	public void setReplaceTexteMap(HashMap<String, String> map) {
		this.replaceTexte = map;
	}
}
