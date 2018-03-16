
# Script for creation of database.

# Deleting database;
DROP DATABASE IF EXISTS bestbusroute;

# Creating database;

CREATE DATABASE bestbusroute;

# Connect to  database

\c bestbusroute

# Creating Tables

CREATE TABLE USER_BBR(
   ID             SERIAL PRIMARY KEY    NOT NULL,
   firstName      TEXT     NOT NULL,
   lastName       TEXT     NOT NULL,
   email          TEXT     NOT NULL,
   password       TEXT,
   latitude       REAL NULL,
   longitude      REAL NULL,
   version        INT,
   enable         BOOLEAN DEFAULT true
);

CREATE TABLE ROUTE(
   ID             SERIAL PRIMARY KEY    NOT NULL,
   route          TEXT NOT NULL,
   name           TEXT NOT NULL,
   path1          TEXT NOT NULL,
   version        INT,
   path2          TEXT NOT NULL
);


CREATE TABLE ALERT(
   ID             SERIAL PRIMARY KEY    NOT NULL,
   name           TEXT NOT NULL,
   latitude       REAL NOT NULL,
   longitude      REAL NOT NULL,
   userID         INT  NOT NULL references USER_BBR(ID),
   version        INT,
   ratio          INT  DEFAULT 200
);

CREATE TABLE SAVED_PLACE(
   ID             SERIAL  PRIMARY KEY  NOT NULL,
   name           TEXT NOT NULL,
   latitude       REAL NOT NULL,
   longitude      REAL NOT NULL,
   ratio          INT  DEFAULT 100,     
   version        INT,
   userID         INT  NOT NULL references USER_BBR(ID)
);

CREATE TABLE HISTORY(
   ID 		     SERIAL PRIMARY KEY    NOT NULL,
   latitude          REAL NOT NULL,
   longitude         REAL NOT NULL,
   time_stamp        TIMESTAMP NOT NULL DEFAULT now(),
   routeID           INT NULL references ROUTE(ID),
   savedPlacedID     INT NULL references SAVED_PLACE(ID),
   version           INT,
   userID            INT NOT NULL references USER_BBR(ID)
);

CREATE TABLE SCHEDULE(
   ID 		  SERIAL PRIMARY KEY    NOT NULL,
   name           TEXT NOT NULL,
   routeID        INT NULL references ROUTE(ID),
   calendar       JSON NOT NULL,
   version        INT,
   minTime        REAL NOT NULL,
   maxTime        REAL NOT NULL
);

ALTER TABLE USER_BBR
ADD CONSTRAINT unique_email UNIQUE(email);

# CREATING USERS
# 69.12.78.230:5432  app_dataUsagetic  $dA7A_71c

 CREATE USER app_bbroute WITH PASSWORD '$b3st_R0ut';

 GRANT ALL ON ALL TABLES IN SCHEMA public TO app_bbroute;

GRANT USAGE, SELECT ON SEQUENCE alert_id_seq TO app_bbroute;
GRANT USAGE, SELECT ON SEQUENCE history_id_seq TO app_bbroute;
GRANT USAGE, SELECT ON SEQUENCE route_id_seq TO app_bbroute;
GRANT USAGE, SELECT ON SEQUENCE saved_place_id_seq TO app_bbroute;
GRANT USAGE, SELECT ON SEQUENCE schedule_id_seq TO app_bbroute;
GRANT USAGE, SELECT ON SEQUENCE user_bbr_id_seq TO app_bbroute;

# Initial Insertion

INSERT INTO USER_BBR (firstName,lastName,email,password)
VALUES('Angel De Jesus', 'Banuelos Sahagun', 'ing.angel.banuelos@gmail.com','s0m3_p4ssw0rd');

INSERT INTO USER_BBR (firstName,lastName,email,password)
VALUES('Anonymous', 'anonymous', 'anonimous@mejorruta.com','p4ssw0rd');

