DROP TABLE ref_poste;
DROP TABLE personnage_caracteristique;
DROP TABLE ref_personnage;
DROP TABLE ref_caracteristique_royaume;
DROP TABLE ref_caracteristique_personnage;
DROP TABLE royaume;

INSERT INTO version (numero, date_maj) VALUES (1.1, CURRENT_TIMESTAMP);

/**
 * TABLE royaume
 */
CREATE TABLE royaume (
	id_royaume SERIAL NOT NULL,
	nom CHARACTER VARYING(32),
	taille integer NOT NULL DEFAULT 0,
	economie integer NOT NULL DEFAULT 0,
	loyaute integer NOT NULL DEFAULT 0,
	stabilite integer NOT NULL DEFAULT 0,
	tresorerie integer NOT NULL DEFAULT 0,
	population integer NOT NULL DEFAULT 0,
	insatisfaction integer NOT NULL DEFAULT 0,
	valeur integer NOT NULL DEFAULT 0,
	CONSTRAINT pk_royaume PRIMARY KEY (id_royaume)
);

INSERT INTO royaume (id_royaume, nom) VALUES (1, 'Royaume');

/**
 * TABLE ref_caracteristique_personnage
 */
CREATE TABLE ref_caracteristique_personnage (
	id_caracteristique_personnage SERIAL NOT NULL,
	nom CHARACTER VARYING(32),
	abreviation  CHARACTER VARYING(3),
	CONSTRAINT pk_caracteristique_personnage PRIMARY KEY (id_caracteristique_personnage)
);

INSERT INTO ref_caracteristique_personnage (id_caracteristique_personnage, nom, abreviation) VALUES (1, 'Force', 'FOR');
INSERT INTO ref_caracteristique_personnage (id_caracteristique_personnage, nom, abreviation) VALUES (2, 'Constitution', 'CON');
INSERT INTO ref_caracteristique_personnage (id_caracteristique_personnage, nom, abreviation) VALUES (3, 'Dextérité', 'DEX');
INSERT INTO ref_caracteristique_personnage (id_caracteristique_personnage, nom, abreviation) VALUES (4, 'Intelligence', 'INT');
INSERT INTO ref_caracteristique_personnage (id_caracteristique_personnage, nom, abreviation) VALUES (5, 'Charisme', 'CHA');
INSERT INTO ref_caracteristique_personnage (id_caracteristique_personnage, nom, abreviation) VALUES (6, 'Sagesse', 'SAG');

/**
 * TABLE ref_caracteristique_royaume
 */
CREATE TABLE ref_caracteristique_royaume (
	id_caracteristique_royaume SERIAL NOT NULL,
	nom CHARACTER VARYING(32),
	CONSTRAINT pk_caracteristique_royaume PRIMARY KEY (id_caracteristique_royaume)
);

INSERT INTO ref_caracteristique_royaume (id_caracteristique_royaume, nom) VALUES (1, 'Economie');
INSERT INTO ref_caracteristique_royaume (id_caracteristique_royaume, nom) VALUES (2, 'Loyaute');
INSERT INTO ref_caracteristique_royaume (id_caracteristique_royaume, nom) VALUES (3, 'Stabilité');

/**
 * TABLE ref_personnage
 */
CREATE TABLE ref_personnage (
	id_personnage SERIAL NOT NULL,
	nom CHARACTER VARYING(32),
	CONSTRAINT pk_personnage PRIMARY KEY (id_personnage)
);

/**
 * TABLE pe
 */
CREATE TABLE personnage_caracteristique (
	id_personnage INTEGER NOT NULL,
	id_caracteristique_personnage INTEGER NOT NULL,
	valeur INTEGER NOT NULL,
	CONSTRAINT pk_personnage_caracteristique PRIMARY KEY (id_personnage, id_caracteristique_personnage)
);


/**
 * TABLE poste
 */
CREATE TABLE ref_poste (
	id_poste SERIAL NOT NULL,
	nom CHARACTER VARYING(32),
	id_caracteristique_royaume INTEGER NOT NULL,
	id_personnage INTEGER,
	choix BOOLEAN NOT NULL DEFAULT FALSE,
	actif BOOLEAN NOT NULL DEFAULT TRUE,
	CONSTRAINT pk_poste PRIMARY KEY (id_poste),
	CONSTRAINT fk_poste_caracteristique_royaume FOREIGN KEY (id_caracteristique_royaume) REFERENCES ref_caracteristique_royaume(id_caracteristique_royaume),
	CONSTRAINT fk_poste_personnage FOREIGN KEY (id_personnage) REFERENCES ref_personnage(id_personnage)
);

INSERT INTO ref_poste (id_poste, nom, id_caracteristique_royaume, choix) VALUES (1, 'Dirigeant', 1, true);
INSERT INTO ref_poste (id_poste, nom, id_caracteristique_royaume, choix) VALUES (2, 'Consort', 1, true);
INSERT INTO ref_poste (id_poste, nom, id_caracteristique_royaume) VALUES (3, 'Conseiller', 2);
INSERT INTO ref_poste (id_poste, nom, id_caracteristique_royaume) VALUES (4, 'Général', 3);
INSERT INTO ref_poste (id_poste, nom, id_caracteristique_royaume) VALUES (5, 'Grand diplomate', 3);
INSERT INTO ref_poste (id_poste, nom, id_caracteristique_royaume) VALUES (6, 'Héritier', 2);
INSERT INTO ref_poste (id_poste, nom, id_caracteristique_royaume) VALUES (7, 'Grand prêtre', 3);
INSERT INTO ref_poste (id_poste, nom, id_caracteristique_royaume) VALUES (8, 'Grand érudit', 1);
INSERT INTO ref_poste (id_poste, nom, id_caracteristique_royaume) VALUES (9, 'Prévôt', 1);
INSERT INTO ref_poste (id_poste, nom, id_caracteristique_royaume) VALUES (10, 'Agent de l''ordre royal', 2);
INSERT INTO ref_poste (id_poste, nom, id_caracteristique_royaume, choix) VALUES (11, 'Maître espion', 1, true);
INSERT INTO ref_poste (id_poste, nom, id_caracteristique_royaume) VALUES (12, 'Trésorier', 1);
INSERT INTO ref_poste (id_poste, nom, id_caracteristique_royaume) VALUES (13, 'Chef de la garde', 2);


