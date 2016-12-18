create table CARTS (
    ID int not null AUTO_INCREMENT,
    EMAIL varchar(100) not null,
    PRIMARY KEY(ID)
);
create unique index CARTS_BY_EMAIL on CARTS (EMAIL);
