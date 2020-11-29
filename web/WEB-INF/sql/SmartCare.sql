create table users(
	uname varchar(20) primary key,
	passwd varchar(20),
	role varchar(10)
);

create table patients(
	pID int not null primary key
            generated always as identity (start with 1, increment by 1), 
	pName varchar(50),
	pAddress varchar(100),
	pType varchar(10),
	uName varchar(20) references users(uname)
);

create table employee(
	eID int not null primary key
            generated always as identity (start with 1, increment by 1), 
	eName varchar(50),
	eAddress varchar(100),
	uName varchar(20) references users(uname)
);

create table operations(
    oID int not null primary key
            generated always as identity (start with 1, increment by 1), 
    eName varchar(50),
    pName varchar(50),
    oDate date, 
    oTime time,
    oDuration float, 
    oCharge float
);


create table booking_slots(
    sID int not null primary key
            generated always as identity (start with 1, increment by 1),
    eName varchar(50),
    pName varchar(50),
    sDate date,
    sTime time
    
	
);

create table prescription(
    prID int not null primary key
        generated always as identity(start with 1, increment by 1),
    eName varchar(50),
    pName varchar(50),
    prDetails varchar(100)

);

create table charges(
    cID int not null primary key
        generated always as identity(start with 1, increment by 1),
    eName varchar(50),
    pName varchar(50),
    pType varchar(10),
    oCharge float 	
);	
	

INSERT INTO USERS (UNAME, PASSWD, "ROLE") VALUES ('meaydin', 'aydinme', 'doctor');
INSERT INTO USERS (UNAME, PASSWD, "ROLE") VALUES ('eaydin', '12345me', 'nurse');
INSERT INTO USERS (UNAME, PASSWD, "ROLE") VALUES ('caidan', '5432@10', 'patient');
INSERT INTO USERS (UNAME, PASSWD, "ROLE") VALUES ('princehassan', 'prince_passwd', 'patient');
INSERT INTO USERS (UNAME, PASSWD, "ROLE") VALUES ('raul ', 'raul123', 'admin');
INSERT INTO USERS (UNAME, PASSWD, "ROLE") VALUES ('alexandra', 'alexandra123', 'nurse');
INSERT INTO USERS (UNAME, PASSWD, "ROLE") VALUES ('ashley ', 'ashley123', 'doctor');
INSERT INTO USERS (UNAME, PASSWD, "ROLE") VALUES ('chris', 'chris123', 'patient');
INSERT INTO USERS (UNAME, PASSWD, "ROLE") VALUES ('grant', 'grant123', 'patient');



INSERT INTO EMPLOYEE (ENAME, EADDRESS, UNAME) VALUES ('Mehmet Aydin', 'Mehmets Address, London, NW4 0BH', 'meaydin');
INSERT INTO EMPLOYEE (ENAME, EADDRESS, UNAME) VALUES ('Emin Aydin', 'Emiin''s Address, Bristol, BS16', 'eaydin');
INSERT INTO EMPLOYEE (ENAME, EADDRESS, UNAME) VALUES ('Raul Ginj', 'Bristol BS14 4ST', 'raul');
INSERT INTO EMPLOYEE (ENAME, EADDRESS, UNAME) VALUES ('Alexandra Rotaru', 'Bristol BS7 5TH', 'alexandra');
INSERT INTO EMPLOYEE (ENAME, EADDRESS, UNAME) VALUES ('Ashley Gregory', ' Bristol, BS30 7HD', 'ashley');



INSERT INTO PATIENTS (PNAME, PADDRESS, PTYPE, UNAME) VALUES ('Charly Aidan', '14 King Street, Aberdeen, AB24 1BR', 'NHS', 'caidan');
INSERT INTO PATIENTS (PNAME, PADDRESS, PTYPE, UNAME) VALUES ('Prince Hassan', 'Non-UK street, Non-UK Town, Non_UK', 'private', 'princehassan');
INSERT INTO PATIENTS (PNAME, PADDRESS, PTYPE, UNAME) VALUES ('Chris Hobday', 'Bristol BS16 5AC ', 'private', 'chris');
INSERT INTO PATIENTS (PNAME, PADDRESS, PTYPE, UNAME) VALUES ('Grant Hughes', 'Bristol, BS16 9XP','NHS', 'grant');

INSERT INTO OPERATIONS(ENAME, PNAME, ODATE,OTIME,ODURATION,OCHARGE) VALUES('Ashely Gregory','Grant Hughes', '2020-11-20', '16:10:00', 10, 0);
INSERT INTO OPERATIONS(ENAME, PNAME, ODATE,OTIME,ODURATION,OCHARGE) VALUES('Ashley Gregory','Chris Hobday', '2020-11-21', '18:00:00', 10, 10.00);



INSERT INTO BOOKING_SLOTS(ENAME, PNAME, SDATE, STIME) VALUES ('Ashely Gregory','Grant Hughes', '2020-11-20', '16:10:00');
INSERT INTO BOOKING_SLOTS(ENAME, PNAME, SDATE, STIME) VALUES ('Ashley Gregory','Chris Hobday', '2020-11-21', '18:00:00');


INSERT INTO PRESCRIPTION(ENAME, PNAME,PRDETAILS) VALUES ('Ashely Gregory','Grant Hughes',' Paracetamol 3 times/day ');
INSERT INTO PRESCRIPTION(ENAME, PNAME,PRDETAILS) VALUES ('Ashley Gregory','Chris Hobday',' Nurofen 3 times/day ');


INSERT INTO CHARGES(ENAME, PNAME, PTYPE,OCHARGE) VALUES ('Ashely Gregory','Grant Hughes','NHS', 0);
INSERT INTO CHARGES(ENAME, PNAME,PTYPE,OCHARGE) VALUES ('Ashley Gregory','Chris Hobday','PRIVATE',10.00);





