/***************************************************************
Attribution des privil�ges sur les objets.Cr�ation des synonymes
MAJ: XH
***************************************************************/
--Granter le privil�ge syst�me "create public synonym" � biblio
--Le propri�taire du sch�ma (biblio) donne des droits aux utilisateurs de la BD
--Cr�ez un user bibliothecaire


--Les tables
grant select on Utilisateur to bibliothecaire;
grant select on Employe to bibliothecaire;
grant select on Adherent to bibliothecaire;
grant select, update on Exemplaire to bibliothecaire;
grant select, insert, delete on EmpruntEncours to bibliothecaire;
grant insert on EmpruntArchive  to bibliothecaire;
grant select on AdherentGeneral to bibliothecaire;

--Les s�quences
grant select on seq_archive to biblio;


--Le propri�taire cre�e des biblio SYNONYM sur les tables
CREATE OR REPLACE public SYNONYM Utilisateur FOR biblio.Utilisateur;
CREATE OR REPLACE public SYNONYM Employe FOR biblio.Employe;
CREATE OR REPLACE public SYNONYM Adherent FOR biblio.Adherent;
CREATE OR REPLACE public SYNONYM Exemplaire FOR biblio.Exemplaire;
CREATE OR REPLACE public SYNONYM EmpruntEncours FOR biblio.EmpruntEncours;
CREATE OR REPLACE public SYNONYM EmpruntArchive FOR biblio.EmpruntArchive;
CREATE OR REPLACE public SYNONYM AdherentGeneral FOR biblio.AdherentGeneral;

--Le propri�taire cre�e des public SYNONYM sur les sequences
CREATE OR REPLACE public SYNONYM seq_archive FOR biblio.seq_archive;


