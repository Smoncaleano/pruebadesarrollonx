DROP TABLE IF EXISTS mercancias_audits;

CREATE TABLE mercancias_audits (
   mercancias_id INT ,
   nombre VARCHAR(40) ,
   create_at TIMESTAMP(6) ,
   update_at TIMESTAMP(6) ,
   fk_usuario_creador INT 
);


CREATE OR REPLACE FUNCTION mercancia_update_trigger_fnc()
  RETURNS trigger AS
$$
BEGIN
    INSERT INTO "mercancias_audits" ( "mercancias_id", "nombre", "create_at","update_at" ,"fk_usuario_creador")

         VALUES(NEW."id",NEW."nombre",NEW."create_at",current_date,NEW."fk_usuario_creador");

RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';


CREATE OR REPLACE TRIGGER mercancia_insert_trigger
  AFTER UPDATE
  ON "mercancias"
  FOR EACH ROW
  EXECUTE PROCEDURE mercancia_update_trigger_fnc();