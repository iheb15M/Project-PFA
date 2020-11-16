--------------------------------------------------------
--  File created - Tuesday-May-19-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table BRANCHE
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."BRANCHE" 
   (	"BRANCHE_ID" NUMBER, 
	"SURNOM" VARCHAR2(20 BYTE), 
	"TYPE" VARCHAR2(20 BYTE), 
	"DEP_ID" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table COMPTE
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."COMPTE" 
   (	"PSEUDO" VARCHAR2(20 BYTE), 
	"PASSWORD" VARCHAR2(20 BYTE), 
	"TYPE" VARCHAR2(20 BYTE), 
	"ID_EMP" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table DEPARTEMENT
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."DEPARTEMENT" 
   (	"DEP_ID" NUMBER, 
	"CHEF_ID" NUMBER, 
	"NOM_DEP" VARCHAR2(20 BYTE), 
	"NOM_DOMAINE" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table EMPLOYE
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."EMPLOYE" 
   (	"ID_EMP" NUMBER, 
	"NOM" VARCHAR2(20 BYTE), 
	"PRENOM" VARCHAR2(20 BYTE), 
	"DATE_NAISS" DATE, 
	"ID_POSTE" NUMBER, 
	"CIN" NUMBER, 
	"NUM_TEL" NUMBER(*,0), 
	"MAIL" VARCHAR2(40 BYTE), 
	"ADRESSE" VARCHAR2(40 BYTE), 
	"GENDER" CHAR(1 BYTE), 
	"ZIP" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table POSTE
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."POSTE" 
   (	"ID_POST" NUMBER, 
	"SALAIRE" FLOAT(126), 
	"TYPE_POST" VARCHAR2(20 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Table SALLE
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."SALLE" 
   (	"ID_SALLE" NUMBER(*,0), 
	"CAPACITE" NUMBER(*,0), 
	"SURNOM" VARCHAR2(20 BYTE), 
	"TYPE" VARCHAR2(20 BYTE), 
	"DEP_ID" NUMBER(*,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into EDUSPACE.BRANCHE
SET DEFINE OFF;
Insert into EDUSPACE.BRANCHE (BRANCHE_ID,SURNOM,TYPE,DEP_ID) values (42,'BRA','BTS','1');
Insert into EDUSPACE.BRANCHE (BRANCHE_ID,SURNOM,TYPE,DEP_ID) values (1,'tech','BTS','1');
Insert into EDUSPACE.BRANCHE (BRANCHE_ID,SURNOM,TYPE,DEP_ID) values (21,'technique','BTP','1');
REM INSERTING into EDUSPACE.COMPTE
SET DEFINE OFF;
Insert into EDUSPACE.COMPTE (PSEUDO,PASSWORD,TYPE,ID_EMP) values ('superAdmin','adminadmin','SUPER',null);
REM INSERTING into EDUSPACE.DEPARTEMENT
SET DEFINE OFF;
Insert into EDUSPACE.DEPARTEMENT (DEP_ID,CHEF_ID,NOM_DEP,NOM_DOMAINE) values (3,4,'dep tech','technique');
Insert into EDUSPACE.DEPARTEMENT (DEP_ID,CHEF_ID,NOM_DEP,NOM_DOMAINE) values (1,4,'dep','domaine');
Insert into EDUSPACE.DEPARTEMENT (DEP_ID,CHEF_ID,NOM_DEP,NOM_DOMAINE) values (2,4,'informatique','info');
REM INSERTING into EDUSPACE.EMPLOYE
SET DEFINE OFF;
Insert into EDUSPACE.EMPLOYE (ID_EMP,NOM,PRENOM,DATE_NAISS,ID_POSTE,CIN,NUM_TEL,MAIL,ADRESSE,GENDER,ZIP) values (4,'mejri','iheb',null,21,12345678,20145623,'iheb15mejri@gmail.com','bizerte','h',7000);
REM INSERTING into EDUSPACE.POSTE
SET DEFINE OFF;
Insert into EDUSPACE.POSTE (ID_POST,SALAIRE,TYPE_POST) values (1,1000,'RH');
Insert into EDUSPACE.POSTE (ID_POST,SALAIRE,TYPE_POST) values (21,1200,'chefDep');
REM INSERTING into EDUSPACE.SALLE
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index BRANCHE_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."BRANCHE_UK1" ON "SYSTEM"."BRANCHE" ("SURNOM") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index BRANCHE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."BRANCHE_PK" ON "SYSTEM"."BRANCHE" ("BRANCHE_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index COMPTE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."COMPTE_PK" ON "SYSTEM"."COMPTE" ("PSEUDO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index DEPARTEMENT_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."DEPARTEMENT_UK1" ON "SYSTEM"."DEPARTEMENT" ("CHEF_ID", "NOM_DEP") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index DEPARTEMENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."DEPARTEMENT_PK" ON "SYSTEM"."DEPARTEMENT" ("DEP_ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index EMPLOYE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."EMPLOYE_PK" ON "SYSTEM"."EMPLOYE" ("ID_EMP") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index POSTE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."POSTE_PK" ON "SYSTEM"."POSTE" ("ID_POST") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Index SALLE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."SALLE_PK" ON "SYSTEM"."SALLE" ("ID_SALLE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SALLE_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYSTEM"."SALLE_UK1" ON "SYSTEM"."SALLE" ("SURNOM") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger BRANCHE_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."BRANCHE_TRG" 
BEFORE INSERT ON BRANCHE 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SYSTEM"."BRANCHE_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger BRANCHE_TRG1
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."BRANCHE_TRG1" 
BEFORE INSERT ON BRANCHE 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.BRANCHE_ID IS NULL THEN
      SELECT BRANCHE_SEQ1.NEXTVAL INTO :NEW.BRANCHE_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SYSTEM"."BRANCHE_TRG1" ENABLE;
--------------------------------------------------------
--  DDL for Trigger DEPARTEMENT_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."DEPARTEMENT_TRG" 
BEFORE INSERT ON DEPARTEMENT 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SYSTEM"."DEPARTEMENT_TRG" DISABLE;
--------------------------------------------------------
--  DDL for Trigger DEPARTEMENT_TRG2
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."DEPARTEMENT_TRG2" 
BEFORE INSERT ON DEPARTEMENT 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SYSTEM"."DEPARTEMENT_TRG2" ENABLE;
--------------------------------------------------------
--  DDL for Trigger DEPARTEMENT_TRG1
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."DEPARTEMENT_TRG1" 
BEFORE INSERT ON DEPARTEMENT 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SYSTEM"."DEPARTEMENT_TRG1" DISABLE;
--------------------------------------------------------
--  DDL for Trigger DEPARTEMENT_TRG3
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."DEPARTEMENT_TRG3" 
BEFORE INSERT ON DEPARTEMENT 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.DEP_ID IS NULL THEN
      SELECT DEPARTEMENT_SEQ1.NEXTVAL INTO :NEW.DEP_ID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SYSTEM"."DEPARTEMENT_TRG3" ENABLE;
--------------------------------------------------------
--  DDL for Trigger EMPLOYE_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."EMPLOYE_TRG" 
BEFORE INSERT ON EMPLOYE 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;

/
ALTER TRIGGER "SYSTEM"."EMPLOYE_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger EMPLOYE_TRG1
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."EMPLOYE_TRG1" 
BEFORE INSERT ON EMPLOYE 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID_EMP IS NULL THEN
      SELECT EMPLOYE_SEQ.NEXTVAL INTO :NEW.ID_EMP FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SYSTEM"."EMPLOYE_TRG1" ENABLE;
--------------------------------------------------------
--  DDL for Trigger POSTE_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."POSTE_TRG" 
BEFORE INSERT ON POSTE 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID_POST IS NULL THEN
      SELECT POSTE_SEQ.NEXTVAL INTO :NEW.ID_POST FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;

/
ALTER TRIGGER "SYSTEM"."POSTE_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger SALLE_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SYSTEM"."SALLE_TRG" 
BEFORE INSERT ON SALLE 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.ID_SALLE IS NULL THEN
      SELECT SALLE_SEQ.NEXTVAL INTO :NEW.ID_SALLE FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SYSTEM"."SALLE_TRG" ENABLE;
--------------------------------------------------------
--  Constraints for Table BRANCHE
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."BRANCHE" ADD CONSTRAINT "BRANCHE_PK" PRIMARY KEY ("BRANCHE_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "SYSTEM"."BRANCHE" ADD CONSTRAINT "BRANCHE_UK1" UNIQUE ("SURNOM")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "SYSTEM"."BRANCHE" MODIFY ("BRANCHE_ID" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."BRANCHE" MODIFY ("SURNOM" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."BRANCHE" MODIFY ("TYPE" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."BRANCHE" MODIFY ("DEP_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table COMPTE
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."COMPTE" ADD CONSTRAINT "COMPTE_PK" PRIMARY KEY ("PSEUDO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
 
  ALTER TABLE "SYSTEM"."COMPTE" MODIFY ("PSEUDO" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."COMPTE" MODIFY ("PASSWORD" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."COMPTE" MODIFY ("TYPE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table DEPARTEMENT
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."DEPARTEMENT" ADD CONSTRAINT "DEPARTEMENT_PK" PRIMARY KEY ("DEP_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "SYSTEM"."DEPARTEMENT" ADD CONSTRAINT "DEPARTEMENT_UK1" UNIQUE ("CHEF_ID", "NOM_DEP")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "SYSTEM"."DEPARTEMENT" MODIFY ("DEP_ID" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."DEPARTEMENT" MODIFY ("CHEF_ID" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."DEPARTEMENT" MODIFY ("NOM_DEP" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."DEPARTEMENT" MODIFY ("NOM_DOMAINE" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table EMPLOYE
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."EMPLOYE" ADD CONSTRAINT "EMPLOYE_PK" PRIMARY KEY ("ID_EMP")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
 
  ALTER TABLE "SYSTEM"."EMPLOYE" MODIFY ("NOM" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."EMPLOYE" MODIFY ("PRENOM" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."EMPLOYE" MODIFY ("ID_POSTE" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."EMPLOYE" MODIFY ("GENDER" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."EMPLOYE" MODIFY ("CIN" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."EMPLOYE" MODIFY ("NUM_TEL" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."EMPLOYE" MODIFY ("MAIL" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."EMPLOYE" MODIFY ("ADRESSE" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."EMPLOYE" MODIFY ("ZIP" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table POSTE
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."POSTE" ADD CONSTRAINT "POSTE_PK" PRIMARY KEY ("ID_POST")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
 
  ALTER TABLE "SYSTEM"."POSTE" MODIFY ("ID_POST" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."POSTE" MODIFY ("SALAIRE" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."POSTE" MODIFY ("TYPE_POST" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SALLE
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."SALLE" ADD CONSTRAINT "SALLE_PK" PRIMARY KEY ("ID_SALLE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "SYSTEM"."SALLE" ADD CONSTRAINT "SALLE_UK1" UNIQUE ("SURNOM")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
 
  ALTER TABLE "SYSTEM"."SALLE" MODIFY ("ID_SALLE" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."SALLE" MODIFY ("CAPACITE" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."SALLE" MODIFY ("SURNOM" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."SALLE" MODIFY ("TYPE" NOT NULL ENABLE);
 
  ALTER TABLE "SYSTEM"."SALLE" MODIFY ("DEP_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table COMPTE
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."COMPTE" ADD CONSTRAINT "COMPTE_FK1" FOREIGN KEY ("ID_EMP")
	  REFERENCES "SYSTEM"."EMPLOYE" ("ID_EMP") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table DEPARTEMENT
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."DEPARTEMENT" ADD CONSTRAINT "DEPARTEMENT_FK2" FOREIGN KEY ("CHEF_ID")
	  REFERENCES "SYSTEM"."EMPLOYE" ("ID_EMP") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table EMPLOYE
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."EMPLOYE" ADD CONSTRAINT "EMPLOYE_FK1" FOREIGN KEY ("ID_POSTE")
	  REFERENCES "SYSTEM"."POSTE" ("ID_POST") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SALLE
--------------------------------------------------------

  ALTER TABLE "SYSTEM"."SALLE" ADD CONSTRAINT "SALLE_FK1" FOREIGN KEY ("DEP_ID")
	  REFERENCES "SYSTEM"."DEPARTEMENT" ("DEP_ID") ENABLE;