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
    pDOB date,
    pReferred varchar(10),
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
    eDOB date,
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

create table surgery_type(
    surgery_name varchar(50) primary key,
    min_duration int,
    charges float
);

create table operations(
    oID int not null primary key
            generated always as identity (start with 1, increment by 1), 
    sID int references booking_slots(sID),
    eID int references employee(eID),
    pID int references patients(pID),
    surgery_name varchar(50) references surgery_type(surgery_name),
    oDuration int
);


create table prescription(
    prID int not null primary key
        generated always as identity(start with 1, increment by 1),
    oID int references operations(oID),
    eID int references employee(eID),
    pID int references patients(pID),
    prDetails varchar(100),
    prRequest varchar (10)

);

create table charges(
    cID int not null primary key
        generated always as identity(start with 1, increment by 1),
    eID int references employee(eID),
    pID int references patients(pID),
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


INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, PDOB, PREFERRED, UNAME) VALUES ('Mr.','Charly', 'Aidan', '14 King Street, Aberdeen, AB24 1BR', 'NHS', '1971-06-14', '','caidan');
INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, PDOB, PREFERRED, UNAME) VALUES ('Mr.','Prince', 'Hassan', 'Non-UK street, Non-UK Town, Non_UK', 'private', '1987-03-05',  '', 'princehassan');
INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, PDOB, PREFERRED, UNAME) VALUES ('Mr.','Chris', 'Hobday', 'Bristol, BS16 5AC, 3 Hill lane ', 'private', '1974-07-01',  '', 'chris');
INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, PDOB, PREFERRED, UNAME) VALUES ('Mr.','Grant', 'Hughes', 'Bristol, BS16 9XP,34 Oxford Street','NHS', '1992-12-12',  '', 'grant');
INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, PDOB, PREFERRED, UNAME) VALUES ('Mrs.','Rita' ,'Evans', 'Bristol, BS4 9ST,19 Fishponds Road','NHS', '1991-10-22', '', 'rita');
INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, PDOB, PREFERRED, UNAME) VALUES ('Ms.','Emma', 'Hill', 'Bristol, BS13 5GT,7 Lawrence Hill','NHS', '1997-09-17', '', 'emma');
INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, PDOB, PREFERRED, UNAME) VALUES ('Mr.','Brian', 'Coverdale', 'Bristol, BS9 8AC, 22 Lacock Drive','private', '1993-11-20','', 'brian');
INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, PDOB, PREFERRED, UNAME) VALUES ('Mr.','Jon', 'White', 'Bristol, BS10 1HD, 4A Church Road','private', '1971-08-03','', 'jon');
INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, PDOB, PREFERRED, UNAME) VALUES ('Mrs.','Angela', 'White', 'Bristol, BS10 1HD, 4A Church Road','private', '1971-02-27','', 'angela');
INSERT INTO PATIENTS (PTITLE, PFIRST_NAME, PLAST_NAME, PADDRESS, PTYPE, PDOB, PREFERRED, UNAME) VALUES ('Ms.','Angela', 'White', 'Bristol, BS3 19TH, 44B Gilbert Road','private', '1986-11-21','', 'angela11');

INSERT INTO EMPLOYEE (ETITLE, EFIRST_NAME, ELAST_NAME, EADDRESS, EDAYS,ESHIFT_START, ESHIFT_END, EDOB, UNAME) VALUES ('Mr.', 'Raul', 'Ginj', 'Bristol BS14 4ST','Mo,Tu,We,Th,Fr','08:00:00','16:00:00','1995-01-20', 'raul');
INSERT INTO EMPLOYEE (ETITLE, EFIRST_NAME, ELAST_NAME, EADDRESS, EDAYS,ESHIFT_START, ESHIFT_END, EDOB, UNAME) VALUES ('Ms.', 'Alexandra', 'Rotaru', 'Bristol BS7 5TH', 'Mo,Tu,We,Th,Fr','08:00:00','17:00:00','1994-08-11', 'alexandra');
INSERT INTO EMPLOYEE (ETITLE, EFIRST_NAME, ELAST_NAME, EADDRESS, EDAYS,ESHIFT_START, ESHIFT_END, EDOB, UNAME) VALUES ('Dr.', 'Ashley', 'Gregory', 'Bristol, BS30 7HD','Mo,Tu,We,Th,Fr','08:00:00','17:00:00','1989-02-09', 'ashley');
INSERT INTO EMPLOYEE (ETITLE, EFIRST_NAME, ELAST_NAME, EADDRESS, EDAYS,ESHIFT_START, ESHIFT_END, EDOB, UNAME) VALUES ('Dr.', 'Evelin', 'Smallwood', 'Bristol, BS20 2TH','Tu,We,Th','08:00:00','17:00:00','1991-11-16', 'evelin');


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
INSERT INTO BOOKING_SLOTS(EID, PID, SDATE, STIME) VALUES(4,5, '2020-12-31', '13:00:00');
INSERT INTO BOOKING_SLOTS(EID, PID, SDATE, STIME) VALUES(3,10, '2020-12-31', '15:00:00');
INSERT INTO BOOKING_SLOTS(EID, PID, SDATE, STIME) VALUES(3,2, '2020-12-31', '11:30:00');


INSERT INTO SURGERY_TYPE(SURGERY_NAME, MIN_DURATION, CHARGES) VALUES ('Consultation' , 10 , 10.00);
INSERT INTO SURGERY_TYPE(SURGERY_NAME, MIN_DURATION, CHARGES) VALUES ('Leg surgery' , 60 , 150.00);
INSERT INTO SURGERY_TYPE(SURGERY_NAME, MIN_DURATION, CHARGES) VALUES ('Arm surgery' , 60 , 150.00);
INSERT INTO SURGERY_TYPE(SURGERY_NAME, MIN_DURATION, CHARGES) VALUES ('Amputation', 70 , 100.00);
INSERT INTO SURGERY_TYPE(SURGERY_NAME, MIN_DURATION, CHARGES) VALUES ('Appendix surgery' , 40 , 60.00);
INSERT INTO SURGERY_TYPE(SURGERY_NAME, MIN_DURATION, CHARGES) VALUES ('Cataract surgery', 80 , 200.00);


INSERT INTO OPERATIONS(SID, EID, PID, SURGERY_NAME, ODURATION) VALUES(1, 3, 4, 'Consultation', 10);
INSERT INTO OPERATIONS(SID, EID, PID, SURGERY_NAME, ODURATION) VALUES(2, 3, 3, 'Consultation', 10);
INSERT INTO OPERATIONS(SID, EID, PID, SURGERY_NAME, ODURATION) VALUES(3, 4, 8, 'Consultation', 30);
INSERT INTO OPERATIONS(SID, EID, PID, SURGERY_NAME, ODURATION) VALUES(4, 4, 7, 'Consultation', 20);
INSERT INTO OPERATIONS(SID, EID, PID, SURGERY_NAME, ODURATION) VALUES(5 ,3, 6, 'Consultation', 10);
INSERT INTO OPERATIONS(SID, EID, PID, SURGERY_NAME, ODURATION) VALUES(6 ,2, 5, 'Consultation', 20);
INSERT INTO OPERATIONS(SID, EID, PID, SURGERY_NAME, ODURATION) VALUES(7, 2, 4, 'Consultation', 10);
INSERT INTO OPERATIONS(SID, EID, PID, SURGERY_NAME, ODURATION) VALUES(8, 3, 3, 'Consultation', 40);
INSERT INTO OPERATIONS(SID, EID, PID, SURGERY_NAME, ODURATION) VALUES(9, 3, 8, 'Consultation', 10);
INSERT INTO OPERATIONS(SID, EID, PID, SURGERY_NAME, ODURATION) VALUES(10, 4, 1, 'Consultation', 15);
INSERT INTO OPERATIONS(SID, EID, PID, SURGERY_NAME, ODURATION) VALUES(11, 4, 2, 'Consultation', 10);
INSERT INTO OPERATIONS(SID, EID, PID, SURGERY_NAME, ODURATION) VALUES(12, 4, 8, 'Consultation', 10);
INSERT INTO OPERATIONS(SID, EID, PID, SURGERY_NAME, ODURATION) VALUES(13, 4, 9, 'Consultation', 20);
INSERT INTO OPERATIONS(SID, EID, PID, SURGERY_NAME, ODURATION) VALUES(14, 3, 10, 'Consultation', 30);
INSERT INTO OPERATIONS(SID, EID, PID, SURGERY_NAME, ODURATION) VALUES(15, 4, 5, 'Leg surgery', 60);
INSERT INTO OPERATIONS(SID, EID, PID, SURGERY_NAME, ODURATION) VALUES(16, 3, 10, 'Appendix surgery', 40);
INSERT INTO OPERATIONS(SID, EID, PID, SURGERY_NAME, ODURATION) VALUES(17, 3, 2, 'Cataract surgery', 90);


INSERT INTO PRESCRIPTION(OID, EID, PID, PRDETAILS, PRREQUEST) VALUES (1, 3, 4, ' Paracetamol 3 times/day for 7 days ',' ');
INSERT INTO PRESCRIPTION(OID, EID, PID, PRDETAILS, PRREQUEST) VALUES (2, 3, 3, 'Nurofen 3 times/day for 5 days ',' ');
INSERT INTO PRESCRIPTION(OID, EID, PID, PRDETAILS, PRREQUEST) VALUES(3, 4, 8, 'IBS syndrome, take Buscopan 2 times a day for 7 days','requested' );
INSERT INTO PRESCRIPTION(OID, EID, PID, PRDETAILS, PRREQUEST) VALUES(4, 4, 7, 'Itchy skin, Take antihistamines 2 times a day for 2 weeks','requested');
INSERT INTO PRESCRIPTION(OID, EID, PID, PRDETAILS, PRREQUEST) VALUES(5, 3, 6, 'Rigevidon tables, one tablet daily for 21 days, repeat after 7 day tablet free, 126 tables',' ');
INSERT INTO PRESCRIPTION(OID, EID, PID, PRDETAILS, PRREQUEST) VALUES(6, 2, 5,'Rigevidon tables, one tablet daily for 21 days, repeat after 7 day tablet free, 126 tables', ' ');
INSERT INTO PRESCRIPTION(OID, EID, PID, PRDETAILS, PRREQUEST) VALUES(7, 2, 4, 'Wrist pain, take paracetamol 2 tablets per day for 5 days','requested' );
INSERT INTO PRESCRIPTION(OID, EID, PID, PRDETAILS, PRREQUEST) VALUES(8, 3, 3,'Chest pain, Take meta-blocker for 2 weeks twice a day',' ' );
INSERT INTO PRESCRIPTION(OID, EID, PID, PRDETAILS, PRREQUEST) VALUES(9, 3, 8, 'IBS syndrome, take Alosetron and Eluxadoline for 2 weeks',' ' );
INSERT INTO PRESCRIPTION(OID, EID, PID, PRDETAILS, PRREQUEST) VALUES(10, 4, 1,'Ear infection, take Tylenol for 1 week',' ' );
INSERT INTO PRESCRIPTION(OID, EID, PID, PRDETAILS, PRREQUEST) VALUES(11,  4, 2,'Kidney Stones, take flomax Oral for 9 days',' ');
INSERT INTO PRESCRIPTION(OID, EID, PID, PRDETAILS, PRREQUEST) VALUES(12,  4, 8,'Kidney Stones, take flomax Oral for 9 days',' ');
INSERT INTO PRESCRIPTION(OID, EID, PID, PRDETAILS, PRREQUEST) VALUES(13,  4, 9,'Flu, take Panadol for 7 days',' ');
INSERT INTO PRESCRIPTION(OID, EID, PID, PRDETAILS, PRREQUEST) VALUES(14, 3, 10, 'Severe acnee, use Benzoyl Peroxide for one month',' ');

INSERT INTO CHARGES(OID, EID, PID) VALUES (1, 3, 4);
INSERT INTO CHARGES(OID, EID, PID) VALUES (2, 3, 3);
INSERT INTO CHARGES(OID, EID, PID) VALUES(3, 4, 8);
INSERT INTO CHARGES(OID, EID, PID) VALUES(4, 4, 7);
INSERT INTO CHARGES(OID, EID, PID) VALUES(5, 3, 6);
INSERT INTO CHARGES(OID, EID, PID) VALUES(6, 2, 5);
INSERT INTO CHARGES(OID, EID, PID) VALUES(7, 2, 4);
INSERT INTO CHARGES(OID, EID, PID) VALUES(8, 3, 3);
INSERT INTO CHARGES(OID, EID, PID) VALUES(9, 3, 8);
INSERT INTO CHARGES(OID, EID, PID) VALUES(10, 4, 1);
INSERT INTO CHARGES(OID, EID, PID) VALUES(11,  4, 2);
INSERT INTO CHARGES(OID, EID, PID) VALUES(12,  4, 8);
INSERT INTO CHARGES(OID, EID, PID) VALUES(13,  4, 9);
INSERT INTO CHARGES(OID, EID, PID) VALUES(14, 3, 10);
