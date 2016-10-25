SET client_encoding = 'UTF8';

/**
 * TABLE version
 */
CREATE TABLE version (
	id_version SERIAL NOT NULL,
	numero CHARACTER VARYING(32) NOT NULL,
	date_maj TIMESTAMP NOT NULL,
	CONSTRAINT pk_version PRIMARY KEY (id_version)
);

INSERT INTO version (numero, date_maj) VALUES (1.0, CURRENT_TIMESTAMP);

/**
 * TABLE hexagone
 */
CREATE TABLE hexagone (
	id_hexagone SERIAL NOT NULL,
	x integer NOT NULL,
	y integer NOT NULL,
	nom CHARACTER VARYING(32),
	visible BOOLEAN NOT NULL DEFAULT FALSE,
	royaume BOOLEAN NOT NULL DEFAULT FALSE,
	route BOOLEAN NOT NULL DEFAULT FALSE,
	CONSTRAINT pk_hexagone PRIMARY KEY (id_hexagone)
);

CREATE FUNCTION populate_hexagone() RETURNS void AS $$
BEGIN
	FOR i IN 0..19 LOOP
		FOR j IN 0..9 LOOP
			INSERT INTO hexagone (x, y) values (i, j);
		END LOOP;
	END LOOP;
END 
$$ language plpgsql;
SELECT populate_hexagone();
DROP FUNCTION populate_hexagone();