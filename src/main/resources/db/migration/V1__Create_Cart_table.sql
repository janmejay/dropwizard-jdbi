create table CARTS (
    ID int not null AUTO_INCREMENT,
    EMAIL varchar(100) not null,
    ITEM_COUNT int not null default 0,
    ITEM_QUANTITY int not null default 0,
    PRIMARY KEY(ID)
);
create unique index CARTS_BY_EMAIL on CARTS (EMAIL);
