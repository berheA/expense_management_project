--Create database ERSDB;
--DROP TABLE IF exists ERS_REIMBURSEMENT_STATUS, ERS_REIMBURSEMENT_TYPE, ERS_USER_ROLES, ERS_USERS,ERS_Reimbursement;

Create table if not exists ERS_REIMBURSEMENT_STATUS (
REIMB_Status_Id SERIAL PRIMARY KEY,
REIMB_Status VARCHAR(10) NOT NULL );


Create table if not exists ERS_REIMBURSEMENT_TYPE (
REIMB_Type_Id SERIAL primary key ,
REIMB_Type VARCHAR(10) NOT NULL );


Create table if not exists ERS_USER_ROLES (
ERS_USER_ROLE_ID SERIAL primary key ,
ERS_USER_ROLE VARCHAR(10) NOT null);

Create table if not exists ERS_USERS (
ERS_Users_Id SERIAL primary key ,
ERS_Username Varchar(50) UNIQUE NOT NULL,
ERS_Password Varchar(200) NOT NULL,
ERS_FirstName Varchar(100) NOT NULL,
ERS_LastName Varchar(100) NOT NULL,
ERS_Email Varchar(100) UNIQUE NOT NULL,
User_Role_Id  int NOT NULL,
CONSTRAINT fk_ERS_User_Roles
	FOREIGN KEY (USER_Role_ID)
	REFERENCES ERS_User_Roles(ERS_USER_ROLE_ID)
	ON DELETE CASCADE
);

Create table if not exists ERS_Reimbursement (
REIMB_Id SERIAL primary key,
REIMB_Amount NUMERIC(6,2) NOT NULL CHECK(reimb_amount > 0),
REIMB_Submitted timestamp NOT NULL,
REIMB_Resolved timestamp,
REIMB_Description varchar(250),
REIMB_Receipt bytea,
REIMB_Author int NOT NULL,
CONSTRAINT fk_ERS_Users
	FOREIGN KEY (Reimb_Author)
	REFERENCES ERS_Users(ERS_Users_Id)
	ON DELETE CASCADE,
REIMB_Resolver int,
REIMB_Status_Id int DEFAULT 1 NOT NULL,
REIMB_Type_Id int NOT NULL
);
ALTER TABLE ers_reimbursement ADD CONSTRAINT fk_Reimb_Resolver
FOREIGN KEY (Reimb_resolver) REFERENCES ERS_Users(ers_users_ID) ON DELETE CASCADE;

ALTER TABLE ers_reimbursement ADD CONSTRAINT fk_Reimb_Status_ID
FOREIGN KEY (REIMB_Status_Id) REFERENCES ERS_REIMBURSEMENT_STATUS(REIMB_Status_Id) ON DELETE CASCADE;

ALTER TABLE ers_reimbursement ADD CONSTRAINT fk_Reimb_Type_ID
FOREIGN KEY (REIMB_Type_Id) REFERENCES ERS_REIMBURSEMENT_Type(REIMB_Type_Id) ON DELETE CASCADE;

INSERT INTO ers_reimbursement_status (reimb_status) VALUES ('PENDING'), ('APPROVED'), ('DENIED');
--
INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('LODGING'), ('TRAVEL'), ('FOOD'), ('OTHER');
------
INSERT INTO ers_user_roles (ers_user_role) VALUES ('EMPLOYEE'), ('MANAGER');

INSERT INTO ers_users(ers_username, ers_password, ers_firstname, ers_lastname, ers_email, user_role_id )
VALUES ('bobby', '$argon2id$v=19$m=15360,t=2,p=1$dJPMlDO1PkHPDA9+Et1yVg$iMrA6hsJj2tmQzlrV0NgGjMJEyPSe0+fJUmmyhWhCno', 'bob', 'by', 'bobby@gmail.com', 1);
INSERT INTO ers_users(ers_username, ers_password, ers_firstname, ers_lastname, ers_email, user_role_id )
VALUES ('nan', '$argon2id$v=19$m=15360,t=2,p=1$dJPMlDO1PkHPDA9+Et1yVg$iMrA6hsJj2tmQzlrV0NgGjMJEyPSe0+fJUmmyhWhCno', 'nan', 'y', 'nan@gmail.com', 2);
--
--
--INSERT INTO ers_reimbursement (reimb_amount, reimb_submitted, reimb_author, reimb_type_id)
--VALUES (-24.23, (SELECT CURRENT_TIMESTAMP), 2, 1)
--RETURNING *;

--SELECT * FROM ers_users;


--UPDATE ers_reimbursement SET reimb_status_id=2 WHERE reimb_id = 3
--RETURNING *;

--SELECT * FROM ers_reimbursement WHERE reimb_author = 2;
--
--SELECT * FROM ers_reimbursement
--INNER JOIN ers_reimbursement_status ON ers_reimbursement.reimb_status_id = ers_reimbursement_status.reimb_status_id
--INNER JOIN ers_reimbursement_type  ON ers_reimbursement.reimb_type_id = ers_reimbursement_type.reimb_type_id
--WHERE ers_reimbursement.reimb_author=1;
--
--
--, , , ers_reimbursement.reimb_receipt,
--ers_reimbursement.reimb_resolved, ers_reimbursement.reimb_resolver, ers_reimbursement.reimb_status_id, ers_reimbursement.reimb_submitted
--
--SELECT
--DISTINCT ers_users.ers_username,ers_reimbursement.reimb_author,
--ers_users.ers_email, ers_users.ers_firstname, ers_users.ers_lastname, ers_users.ers_users_id,
--ers_user_roles.ers_user_role
--FROM ers_users
--INNER JOIN ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id
--INNER JOIN ers_reimbursement ON ers_users.ers_users_id = ers_reimbursement.reimb_author
--INNER JOIN ers_reimbursement_status ON ers_reimbursement.reimb_type_id = ers_reimbursement_status.reimb_status_id
--WHERE ers_reimbursement_status.reimb_status = 'PENDING';

--SELECT * FROM ers_users INNER JOIN ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id
--WHERE ers_username='nan';
--
--
--
----WHERE ers_reimbursement.reimb_author =2 AND reimb_status = 'PENDING';
--
--
--SELECT reimb_status_id FROM ers_reimbursement_status WHERE reimb_status ='APPROVED';
--
--
--
--UPDATE ers_reimbursement SET reimb_status_id=2, reimb_resolved = (SELECT CURRENT_TIMESTAMP), reimb_resolver=2
--WHERE reimb_id =2 RETURNING *
--
--;
--
--SELECT ers_reimbursement_type.reimb_type FROM
--ers_reimbursement INNER JOIN ers_reimbursement_type ON
--ers_reimbursement.reimb_type_id = ers_reimbursement_type.reimb_type_id
--WHERE ers_reimbursement.reimb_id = 2;
--
--SELECT reimb_status_id FROM ers_reimbursement WHERE reimb_id=2;
