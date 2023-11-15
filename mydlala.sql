-- Adminer 4.7.7 PostgreSQL dump

\connect "mydlala";

DROP TABLE IF EXISTS "phones";
DROP SEQUENCE IF EXISTS phones_id_phone_seq;
CREATE SEQUENCE phones_id_phone_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

CREATE TABLE "public"."phones" (
    "id_phone" integer DEFAULT nextval('phones_id_phone_seq') NOT NULL,
    "marque_phone" character varying,
    "model_phone" character varying,
    "etat_phone" character varying,
    "batterie_phone" character varying,
    "accessoires_phone" character varying,
    "prix_achat_phone" character varying,
    "prix_vente_phone" character varying,
    "cout_rep_article" character varying,
    "cote_phone" character varying,
    "date_achat_phone" character varying,
    "date_vente_phone" character varying,
    "date_maj_phone" character varying,
    "statut_phone" character varying,
    CONSTRAINT "phones_id_phone" PRIMARY KEY ("id_phone")
) WITH (oids = false);

INSERT INTO "phones" ("id_phone", "marque_phone", "model_phone", "etat_phone", "batterie_phone", "accessoires_phone", "prix_achat_phone", "prix_vente_phone", "cout_rep_article", "cote_phone", "date_achat_phone", "date_vente_phone", "date_maj_phone", "statut_phone") VALUES
(1,	'Apple',	'Iphone 12',	'Bon état',	'90 %',	'Aucun',	'150$ cad',	'280$ cad',	'20 $cad',	'Bonne affaire',	'2023-07-09',	'2023-07-10',	'2023-07-15',	NULL),
(2,	'Samsung',	'Galaxy S23',	'Neuf',	'90 %',	'Aucun',	'150$ cad',	'280$ cad',	'20 $cad',	'Bonne affaire',	'2023-07-09',	'2023-07-10',	'2023-07-15',	NULL),
(7,	'Put',	'Test',	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL),
(8,	'Put',	'Test',	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL),
(9,	'Test2',	'Put',	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL),
(10,	'Allo',	'Test4',	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL),
(11,	'Allo',	'Test5',	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL,	NULL),
(12,	'Alcatel',	'Test',	'neuf',	'50%',	'Boite',	'100$',	'0',	'Affaire',	'Affaire',	'2023-07-16',	'2023-07-16',	'2023-07-16',	'disponible');

DROP TABLE IF EXISTS "utilisateurs";
DROP SEQUENCE IF EXISTS utilisateurs_id_utilisateur_seq;
CREATE SEQUENCE utilisateurs_id_utilisateur_seq INCREMENT 1 MINVALUE 1 MAXVALUE 2147483647 START 1 CACHE 1;

CREATE TABLE "public"."utilisateurs" (
    "id_utilisateur" integer DEFAULT nextval('utilisateurs_id_utilisateur_seq') NOT NULL,
    "nom_utilisateur" character varying(100),
    "prenom_utilisateur" character varying(100),
    "courriel_utilisateur" character varying(100),
    "password_utilisateur" character varying(100),
    "role_utilisateur" character varying(100)
) WITH (oids = false);

CREATE INDEX "utilisateurs_id_utilisateur" ON "public"."utilisateurs" USING btree ("id_utilisateur");

INSERT INTO "utilisateurs" ("id_utilisateur", "nom_utilisateur", "prenom_utilisateur", "courriel_utilisateur", "password_utilisateur", "role_utilisateur") VALUES
(27,	'hbibti',	'dj',	'zak@dj.test',	'test',	NULL),
(39,	'zak',	'dj',	'zak@dj.test',	'test',	NULL),
(43,	'Samw Gamgee',	'ring bearer',	'zak@dj.test',	'test',	NULL),
(45,	NULL,	NULL,	NULL,	NULL,	NULL);

-- 2023-07-17 22:19:43.030766-04
