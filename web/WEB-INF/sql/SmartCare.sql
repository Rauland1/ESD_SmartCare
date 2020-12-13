create table users(
	uname varchar(20) primary key,
	passwd varchar(20),
	registration_type varchar(10),
	uRole varchar(10)
);

create table patients(
	pID int not null primary key
            generated always as identity (start with 1, increment by 1), 
	pTitle varchar(5),
	pFirst_name varchar(50),
	pLast_name varchar(50),
	pAddress varchar(100),
	pType varchar(10),
	uName varchar(20) references users(uname)
);

create table employee(
	eID int not null primary key
            generated always as identity (start with 1, increment by 1), 
	eTitle varchar(5),
	eFirst_name varchar(50),
	eLast_name varchar(50),
	eAddress varchar(100),
	eDays varchar(30),
	eSHift_start time,
	eShift_end time,
	uName varchar(20) references users(uname)
);
create table booking_slots(
    sID int not null primary key
            generated always as identity (start with 1, increment by 1),
    eID int references employee(eID),
    pID int references patients(pID),
    sDate date,
    sTime time	
);

create table operations(
    oID int not null primary key
            generated always as identity (start with 1, increment by 1), 
    sID int references booking_slots(sID),
    oDuration float, 
    oCharge float
);



create table prescription(
    prID int not null primary key
        generated always as identity(start with 1, increment by 1),
    oID int references operations(oID),
    prDetails varchar(100)

);

create table charges(
    cID int not null primary key
        generated always as identity(start with 1, increment by 1),
    oID int references operations(oID)
);
				


INSERT INTO USERS (UNAME, PASSWD, REGISTRATION_TYPE, UROLE) VALUES ('caidan', '5432@10','comfirmed', 'patient');
INSERT INTO USERS (UNAME, PASSWD, REGISTRATION_TYPE, UROLE) VALUES ('princehassan', 'prince_passwd','comfirmed', 'patient');
INSERT INTO USERS (UNAME, PASSWD, REGISTRATION_TYPE, UROLE) VALUES ('raul ', 'raul123','comfirmed', 'admin');
INSERT INTO USERS (UNAME, PASSWD, REGISTRATION_TYPE, UROLE) VALUES ('alexandra', 'alexandra123','comfirmed', 'nurse');
INSERT INTO USERS (UNAME, PASSWD, REGISTRATION_TYPE, UROLE) VALUES ('ashley ', 'ashley123','comfirmed', 'doctor');
INSERT INTO USERS (UNAME, PASSWD, REGISTRATION_TYPE, UROLE) VALUES ('chris', 'chris123', 'comfirmed','patient');
INSERT INTO USERS (UNAME, PASSWD, REGISTRATION_TYPE, UROLE) VALUES ('grant', 'grant123','comfirmed', 'patient');
INSERT INTO USERS (UNAME, PASSWD, REGISTRATION_TYPE, UROLE) VALUES ('kane', 'kane123','pending', 'doctor');
INSERT INTO USERS (UNAME, PASSWD, REGISTRATION_TYPE, UROLE) VALUES ('avin', 'avin123','declined', 'nurse');
INSERT INTO USERS (UNAME, PASSWD, REGISTRATION_TYPE, UROLE) VALUES ('rita', 'rita123','comfirmed', 'patient');
INSERT INTO USERS (UNAME, PASSWD, REGISTRATION_TYPE, UROLE) VALUES ('emma', 'emma123','comfirmed', 'patient');
INSERT INTO USERS (UNAME, PASSWD, REGISTRATION_TYPE, UROLE) VALUES ('brian', 'brian123','comfirmed', 'patient');
INSERT INTO USERS (UNAME, PASSWD, REGISTRATION_TYPE, UROLE) VALUES ('jon', 'jon123','comfirmed', 'patient');
INSERT INTO USERS (UNAME, PASSWD, REGISTRATION_TYPE, UROLE) VALUES ('shaun', 'shaun123','pending', 'nurse');
INSERT INTO USERS (UNAME, PASSWD, REGISTRATION_TYPE, UROLE) VALUES ('evelin', 'evelin123','comfirmed', 'doctor');
INSERT INTO USERS (UNAME, PASSWD, REGISTRATION_TYPE, UROLE) VALUES ('angela', 'angela123','comfirmed', 'patient');
INSERT INTO USERS (UNAME, PASSWD, REGISTRATION_TYPE, UROLE) VALUES ('angela11', 'angelawhite123','comfirmed', 'patient');


INSERT INTO EMPLOYEE (ETITLE, EFIRST_NAME, ELAST_NAME, EADDRESS, EDAYS,ESHIFT_START, ESHIFT_END, UNAME) VALUES ('Mr.', 'Raul', 'Ginj', 'Bristol BS14 4ST','Mo,Tu,We,Th,Fr','08:00:00','16:00:00','raul');
INSERT INTO EMPLOYEE (ETITLE, EFIRST_NAME, ELAST_NAME, EADDRESS, EDAYS,ESHIFT_START, ESHIFT_END, UNAME) VALUES ('Ms.', 'Alexandra', 'Rotaru', 'Bristol BS7 5TH', 'Mo,Tu,We,Th,Fr','08:00:00','17:00:00','alexandra');
INSERT INTO EMPLOYEE (ETITLE, EFIRST_NAME, ELAST_NAME, EADDRESS, EDAYS,ESHIFT_START, ESHIFT_END, UNAME) VALUES ('Dr.', 'Ashley', 'Gregory', 'Bristol, BS30 7HD','Mo,Tu,We,Th,Fr','08:00:00','17:00:00','ashley');
INSERT INTO EMPLOYEE (ETITLE, EFIRST_NAME, ELAST_NAME, EADDRESS, EDAYS,ESHIFT_START, ESHIFT_END, UNAME) VALUES ('Dr.', 'Evelin', 'Smallwood', 'Bristol, BS20 2TH','Tu,We,Th','08:00:00','17:00:00','evelin');


INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, UNAME) VALUES ('Mr.','Charly', 'Aidan', '14 King Street, Aberdeen, AB24 1BR', 'NHS', 'caidan');
INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, UNAME) VALUES ('Mr.','Prince', 'Hassan', 'Non-UK street, Non-UK Town, Non_UK', 'private', 'princehassan');
INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, UNAME) VALUES ('Mr.','Chris', 'Hobday', 'Bristol, BS16 5AC, 3 Hill lane ', 'private', 'chris');
INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, UNAME) VALUES ('Mr.','Grant', 'Hughes', 'Bristol, BS16 9XP,34 Oxford Street','NHS', 'grant');
INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, UNAME) VALUES ('Mrs.','Rita' ,'Evans', 'Bristol, BS4 9ST,19 Fishponds Road','NHS', 'rita');
INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, UNAME) VALUES ('Ms.','Emma', 'Hill', 'Bristol, BS13 5GT,7 Lawrence Hill','NHS', 'emma');
INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, UNAME) VALUES ('Mr.','Brian', 'Coverdale', 'Bristol, BS9 8AC, 22 Lacock Drive','private', 'brian');
INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, UNAME) VALUES ('Mr.','Jon', 'White', 'Bristol, BS10 1HD, 4A Church Road','private', 'jon');
INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, UNAME) VALUES ('Mrs.','Angela', 'White', 'Bristol, BS10 1HD, 4A Church Road','private', 'angela');
INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, UNAME) VALUES ('Ms.','Angela', 'White', 'Bristol, BS3 19TH, 44B Gilbert Road','private', 'angela11');




INSERT INTO BOOKING_SLOTS(EID, PID, SDATE, STIME) VALUES (3,4, '2020-11-20', '16:10:00');
INSERT INTO BOOKING_SLOTS(EID, PID, SDATE, STIME) VALUES (3,3, '2020-11-21', '15:00:00');
INSERT INTO BOOKING_SLOTS(EID, PID, SDATE, STIME) VALUES(4,8, '2020-12-15', '13:00:00');
INSERT INTO BOOKING_SLOTS(EID, PID, SDATE, STIME) VALUES(4,7, '2020-12-15', '13:30:00');
INSERT INTO BOOKING_SLOTS(EID, PID, SDATE, STIME) VALUES(3,6, '2020-12-14', '10:00:00');
INSERT INTO BOOKING_SLOTS(EID, PID, SDATE, STIME) VALUES(2,5, '2020-12-17', '11:00:00');
INSERT INTO BOOKING_SLOTS(EID, PID, SDATE, STIME) VALUES(2,4, '2020-12-18', '08:30:00');
INSERT INTO BOOKING_SLOTS(EID, PID, SDATE, STIME) VALUES(3,3, '2020-12-18', '9:00:00');
INSERT INTO BOOKING_SLOTS(EID, PID, SDATE, STIME) VALUES(3,8, '2020-12-18', '9:15:00');
INSERT INTO BOOKING_SLOTS(EID, PID, SDATE, STIME) VALUES(4,1, '2020-12-24', '12:00:00');
INSERT INTO BOOKING_SLOTS(EID, PID, SDATE, STIME) VALUES(4,2, '2020-12-24', '12:35:00');
INSERT INTO BOOKING_SLOTS(EID, PID, SDATE, STIME) VALUES(4,8, '2020-12-29', '12:15:00');
INSERT INTO BOOKING_SLOTS(EID, PID, SDATE, STIME) VALUES(4,9, '2020-12-29', '12:40:00');
INSERT INTO BOOKING_SLOTS(EID, PID, SDATE, STIME) VALUES(3,10, '2020-12-30', '14:40:00');


INSERT INTO OPERATIONS(SID, ODURATION, OCHARGE) VALUES( 1, 10, 0);
INSERT INTO OPERATIONS(SID, ODURATION, OCHARGE) VALUES( 2, 10, 10.00);
INSERT INTO OPERATIONS(SID, ODURATION, OCHARGE) VALUES(3, 10, 10.00);
INSERT INTO OPERATIONS(SID, ODURATION, OCHARGE) VALUES(4, 10, 10.00);
INSERT INTO OPERATIONS(SID, ODURATION, OCHARGE) VALUES(5 ,10, 0);
INSERT INTO OPERATIONS(SID, ODURATION, OCHARGE) VALUES(6 ,10, 0);
INSERT INTO OPERATIONS(SID, ODURATION, OCHARGE) VALUES(7, 10, 0);
INSERT INTO OPERATIONS(SID, ODURATION, OCHARGE) VALUES(8 ,10, 12.00);
INSERT INTO OPERATIONS(SID, ODURATION, OCHARGE) VALUES(9,10, 15.00);
INSERT INTO OPERATIONS(SID, ODURATION, OCHARGE) VALUES(10, 10, 0);
INSERT INTO OPERATIONS(SID, ODURATION, OCHARGE) VALUES(11, 10, 15.50);
INSERT INTO OPERATIONS(SID, ODURATION, OCHARGE) VALUES(12, 10, 12.50);
INSERT INTO OPERATIONS(SID, ODURATION, OCHARGE) VALUES(13, 10, 10.50);
INSERT INTO OPERATIONS(SID, ODURATION, OCHARGE) VALUES(14, 10, 20.00);



INSERT INTO PRESCRIPTION(OID, PRDETAILS) VALUES (1,' Paracetamol 3 times/day for 7 days ');
INSERT INTO PRESCRIPTION(OID, PRDETAILS) VALUES (2, 'Nurofen 3 times/day for 5 days ');
INSERT INTO PRESCRIPTION(OID, PRDETAILS) VALUES(3,'IBS syndrome, take Buscopan 2 times a day for 7 days' );
INSERT INTO PRESCRIPTION(OID, PRDETAILS) VALUES(4,'Itchy skin, Take antihistamines 2 times a day for 2 weeks');
INSERT INTO PRESCRIPTION(OID, PRDETAILS) VALUES(5,'Rigevidon tables, one tablet dayli for 21 days, repeat after 7 day tablet free, 126 tables');
INSERT INTO PRESCRIPTION(OID, PRDETAILS) VALUES(6,'Rigevidon tables, one tablet dayli for 21 days, repeat after 7 day tablet free, 126 tables');
INSERT INTO PRESCRIPTION(OID, PRDETAILS) VALUES(7,'Wrist pain, take paracetamol 2 tablets per day for 5 days' );
INSERT INTO PRESCRIPTION(OID, PRDETAILS) VALUES(8,'Chest pain, Take meta-blocker for 2 weeks twice a day' );
INSERT INTO PRESCRIPTION(OID, PRDETAILS) VALUES(9, 'IBS syndrome, take Alosetron and Eluxadoline for 2 weeks' );
INSERT INTO PRESCRIPTION(OID, PRDETAILS) VALUES(10,'Ear infection, take Tylenol for 1 week' );
INSERT INTO PRESCRIPTION(OID, PRDETAILS) VALUES(11,'Kidney Stones, take flomax Oral for 9 days');
INSERT INTO PRESCRIPTION(OID, PRDETAILS) VALUES(12,'Kidney Stones, take flomax Oral for 9 days');
INSERT INTO PRESCRIPTION(OID, PRDETAILS) VALUES(13,'Flu, take Panadol for 7 days');
INSERT INTO PRESCRIPTION(OID, PRDETAILS) VALUES(14,'Severe acnee, use Benzoyl Peroxide for one month');

INSERT INTO CHARGES(OID) VALUES (1);
INSERT INTO CHARGES(OID) VALUES (2);
INSERT INTO CHARGES(OID) VALUES(3);
INSERT INTO CHARGES(OID) VALUES(4);
INSERT INTO CHARGES(OID) VALUES(5);
INSERT INTO CHARGES(OID) VALUES(6);
INSERT INTO CHARGES(OID) VALUES(7);
INSERT INTO CHARGES(OID) VALUES(8);
INSERT INTO CHARGES(OID) VALUES(9);
INSERT INTO CHARGES(OID) VALUES(10);
INSERT INTO CHARGES(OID) VALUES(11);
INSERT INTO CHARGES(OID) VALUES(12);
INSERT INTO CHARGES(OID) VALUES(13);
INSERT INTO CHARGES(OID) VALUES(14);
