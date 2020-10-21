insert into Citation values(10001,'TRK1093','Rio','Blue','14-AUG-20','Justice Lot',14,40,'Expired Permit',25,'13-SEP-20','Paid');

insert into Citation values(10002,'UGY9123','Maxima','Black','17-AUG-20','Justice Lot',12,55,'Expired Permit',25,'16-SEP-20','Unpaid');

insert into Citation values(10003,'AKL1732','Model X','Silver','17-AUG-20','Justice Lot',13,00,'Expired Permit',25,'16-SEP-20','Unpaid');

insert into Citation values(10004,'NEV9889','Elantra','Red','10-SEP-20','Justice Lot',15,50,'Invalid Permit',20,'09-OCT-20','Unpaid');

insert into Citation values(10005,'PTL5642','Sentra','Black','14-SEP-20','Freedom Lot',10,05,'No Permit',40,'13-OCT-20','Paid');

insert into Citation values(10006,'TRK1093','Rio','Blue','21-SEP-20','Premiere Lot',14,00,'Expired Permit',25,'20-OCT-20','Unpaid');

INSERT INTO LOTS VALUES('Freedom Lot', '2105 Daniel Allen St, NC 27505', 150,0,0 );

INSERT INTO LOTS VALUES('Premiere Lot', '2108 McKent St, NC 27507', 200,1,null);

INSERT INTO LOTS VALUES('Justice Lot','2704 Ben Clark St, NC 26701',175,25,'Visitors: Handicapped:151-155 Electric: 172-175');

INSERT INTO ZONES VALUES('A');

INSERT INTO ZONES VALUES('B');

INSERT INTO ZONES VALUES('C');

INSERT INTO ZONES VALUES('D');

INSERT INTO ZONES VALUES('AS');

INSERT INTO ZONES VALUES('BS');

INSERT INTO ZONES VALUES('CS');

INSERT INTO ZONES VALUES('DS');

INSERT INTO ZONES VALUES('V');

INSERT INTO LHASZ VALUES('A', 'Freedom Lot');

INSERT INTO LHASZ VALUES('B', 'Freedom Lot');

INSERT INTO LHASZ VALUES('C', 'Freedom Lot');

INSERT INTO LHASZ VALUES('D', 'Freedom Lot');

INSERT INTO LHASZ VALUES('A', 'Premiere Lot');

INSERT INTO LHASZ VALUES('B', 'Premiere Lot');

INSERT INTO LHASZ VALUES('C', 'Premiere Lot');

INSERT INTO LHASZ VALUES('D', 'Premiere Lot');

INSERT INTO LHASZ VALUES('AS', 'Premiere Lot');

INSERT INTO LHASZ VALUES('BS', 'Premiere Lot');

INSERT INTO LHASZ VALUES('CS', 'Premiere Lot');

INSERT INTO LHASZ VALUES('DS', 'Premiere Lot');

INSERT INTO LHASZ VALUES('V', 'Premiere Lot');

INSERT INTO LHASZ VALUES('AS', 'Justice Lot');

INSERT INTO LHASZ VALUES('BS', 'Justice Lot');

INSERT INTO LHASZ VALUES('CS', 'Justice Lot');

INSERT INTO LHASZ VALUES('DS', 'Justice Lot');

INSERT INTO LHASZ VALUES('V', 'Justice Lot');

INSERT INTO vehicles VALUES ('CDF5731','Toyota','Camry',2018,'Red');

INSERT INTO vehicles VALUES ('UGY9123','Nissan','Maxima',2015,'Black');

INSERT INTO vehicles VALUES ('TRK1093','Kia','Rio',2017,'Blue');

INSERT INTO vehicles VALUES ('UWA1118','Audi','Q3',2016,'White');

INSERT INTO vehicles VALUES ('TIR3487','BMW','X5',2017,'White');

INSERT INTO vehicles VALUES ('RPU1824','Honda','Odyssey',2016,'Blue');

INSERT INTO vehicles VALUES ('NEV9889','Hyundai','Elantra',2011,'Red');

INSERT INTO vehicles VALUES ('KTP2003','Acura','RDX',2009,'Black');

INSERT INTO VEHICLES VALUES('VTZ87543', 'Nissan', 'LEAF', 2018, 'black');

INSERT INTO VEHICLES VALUES('UGB9020', 'Chevrolet', 'Cruze', 2014, 'Silver');

INSERT INTO VEHICLES VALUES('AKL1732', 'Tesla', 'Model X', 2019, 'Silver');

INSERT INTO VPERMITS VALUES('20V0001A', 'CDF5731', 'V', 'Regular', '12-AUG-20', '12-AUG-20', 14, 0, 16, 0, 2, 200, 'Premiere Lot');

INSERT INTO VPERMITS VALUES('20V0012B', 'TRK1093', 'V', 'Regular', '14-AUG-20', '14-AUG-20', 11, 0, 14, 0, 3, 160, 'Justice Lot');

INSERT INTO VPERMITS VALUES('20V0015J', 'UGY9123', 'V', 'Handicapped', '17-AUG-20', '17-AUG-20', 10, 10, 12, 10, 2, 151, 'Justice Lot');

INSERT INTO VPERMITS VALUES('20V0021L', 'AKL1732', 'V', 'Electric', '17-AUG-20', '17-AUG-20', 11, 45, 12, 45, 1, 173, 'Justice Lot');

INSERT INTO VPERMITS VALUES('20V0026P', 'UWA1118', 'V', 'Handicapped', '19-AUG-20', '19-AUG-20', 14, 50, 16, 50, 2, 153, 'Justice Lot');

INSERT INTO VPERMITS VALUES('20V0025B', 'TRK1093', 'V', 'Regular', '21-SEP-20', '21-SEP-20', 9, 30, 13, 30, 4, 200, 'Premiere Lot');

INSERT INTO EPERMITS VALUES('20B0001B', 'VTZ87543', 'B', 'Electric', '10-AUG-20', '09-AUG-21', 0, 0, 23, 59, 1007999);

INSERT INTO NEPERMITS VALUES('20CS001C', 'UGB9020', 'CS', 'Handicapped', '15-AUG-20', '14-DEC-20', 0, 0, 23, 59, 1006003);

INSERT INTO EPERMITS VALUES('20D0021D', 'TIR3487', 'D', 'Regular', '10-JUL-20', '09-JUL-21', 0, 0, 23, 59, 1006020);

INSERT INTO NEPERMITS VALUES('20AS016S', 'NEV9889', 'AS', 'Regular','01-SEP-20', '31-DEC-20', 0, 0, 23, 59, 1006135);

INSERT INTO EPERMITS VALUES('20A0052A', 'KTP2003', 'A', 'Regular', '29-JUL-20', '28-JUL-21', 0, 0, 23, 59, 1006022);

INSERT INTO EHASV VALUES('20B0001B', 'AKL1732');

INSERT INTO EHASV VALUES('20D0021D', 'TIR3487');

INSERT INTO EHASV VALUES('20D0021D', 'RPU1824');

INSERT INTO EHASV VALUES('20A0052A', 'KTP2003');


INSERT INTO SPACES VALUES('Justice Lot', 151, 'Handicapped', 'V', 1);

INSERT INTO SPACES VALUES('Justice Lot', 152, 'Handicapped', 'V', 0);

INSERT INTO SPACES VALUES('Justice Lot', 153, 'Handicapped', 'V', 1);

INSERT INTO SPACES VALUES('Justice Lot', 154, 'Handicapped', 'V', 0);

INSERT INTO SPACES VALUES('Justice Lot', 155, 'Handicapped', 'V', 0);

INSERT INTO SPACES VALUES('Justice Lot', 172, 'Electric', 'V', 0);

INSERT INTO SPACES VALUES('Justice Lot', 173, 'Electric', 'V', 1);

INSERT INTO SPACES VALUES('Justice Lot', 174, 'Electric', 'V', 0);

INSERT INTO SPACES VALUES('Justice Lot', 175, 'Electric', 'V', 0);

INSERT INTO SPACES VALUES('Premiere Lot', 200, 'Regular', 'V', 1);

INSERT INTO SPACES VALUES('Premiere Lot', 200, 'Regular', 'V', 1);

INSERT INTO SPACES VALUES('Justice Lot', 160, 'Regular', 'V', 1);
