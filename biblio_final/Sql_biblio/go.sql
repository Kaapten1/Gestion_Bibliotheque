/***************************************************************
MAJ: XH
***************************************************************/
--S'il y a un param�tre � ce fichier, alors il contient le chemin absolu
--
-- Redirection de la sortie
SPOOL go.log
-- echo des requ�tes dans SQL*Plus
SET ECHO ON
-- pas d'affichage des substitutions de variable
SET VERIFY OFF
-- sortir en cas d'erreur
WHENEVER OSERROR EXIT
WHENEVER SQLERROR EXIT rollback
set linesize 60
set pagesize 200

@install_biblio.sql
@install_data_biblio.sql
@install_post_biblio_pour_londres.sql

PROMPT     FIN NORMALE DU SCRIPT

-- Comportement par d�faut
spool off
set echo off
set verify on

-- Comportement par d�faut: ne pas sortir en cas d'erreur SQL
WHENEVER SQLERROR continue none
