CREATE TABLE LOTS( 
    LNAME   varchar(40) not null, 
    LADD    varchar(40) unique not null, 
    TSPACE  number(5) not null, 
    VSPACE  number(5) not null, 
    NOTES    varchar(300), 
    CONSTRAINT pk_lots PRIMARY KEY (LNAME) 
    );

CREATE TABLE ZONES( 
    ZID varchar(10) not null, 
    CONSTRAINT zone_pk PRIMARY KEY (ZID) 
    );

CREATE TABLE LHASZ (   
    ZID varchar(4),  
    constraint fk_zid foreign key(ZID) references ZONES(ZID),  
    LNAME varchar(40),  
    constraint fk_lname foreign key(LNAME) references LOTS(LNAME)   
    );

CREATE TABLE vehicles ( 
    plate       varchar(8) primary key, 
    carManf     varchar(20) not null, 
    carModel    varchar(10) not null, 
    carYear     number(4) not null, 
    carColor    varchar(10) not null 
);

CREATE TABLE vpermits ( 
    pid         varchar(8) primary key, 
    pvnumber    varchar(8) references vehicles (plate), 
    zid varchar(10) references ZONES(ZID), 
    pType       varchar(11) not null, 
    startDate   date not null, 
    expDate     date not null, 
    startHour   number(2) not null, 
    startMinute number(2) not null, 
    expHour    number(2) not null, 
    expMinute   number(2) not null, 
    requestedHour   number(2) not null, 
    spaceNum    number(3) not null, 
    lname       varchar(40) references LOTS (lname) 
);

CREATE TABLE epermits ( 
    pid         varchar(8) primary key, 
    pvnumber    varchar(8) references vehicles (plate), 
    zid varchar(10) references ZONES(ZID), 
    pType       varchar(11) not null, 
    startDate   date not null, 
    endDate     date not null, 
    startHour   number(2) not null, 
    startMinute number(2) not null, 
    expHour    number(2) not null, 
    expMinute   number(2) not null, 
    univid      number(7) unique not null 
);

CREATE TABLE nepermits ( 
    pid         varchar(8) primary key, 
    pvnumber    varchar(8) references vehicles (plate), 
    zid varchar(10) references ZONES(ZID), 
    pType       varchar(11) not null, 
    startDate   date not null, 
    endDate     date not null, 
    startHour   number(2) not null, 
    startMinute number(2) not null, 
    expHour    number(2) not null, 
    expMinute   number(2) not null, 
    univid      number(7) unique not null 
);

CREATE TABLE EhasV ( 
    pid         varchar(8) references epermits (pid), 
    plate       varchar(8) references vehicles (plate) 
);

create table Citation( 
    CID number(10) primary key, 
    CARNO varchar2(10) not null, 
    CMODEL varchar2(10) not null, 
    COLOR varchar2(10) not null, 
    CDATE date not null, 
    LNAME varchar2(30) not null, 
    CHH number(2) not null, 
    CMM number(2) not null, 
    VCAT varchar2(20) not null, 
    FEE number(2) not null, 
    DUE date not null, 
    STATUS varchar2(10) not null 
    );