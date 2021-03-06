DROP TABLE IF EXISTS T_ACCOUNT;
DROP TABLE IF EXISTS T_ACCOUNT_SETTINGS;
DROP TABLE IF EXISTS T_INVOICE;
DROP TABLE IF EXISTS T_INVOICE_RECIPIENT;
DROP TABLE IF EXISTS T_INVOICE_ITEM;

CREATE TABLE T_ACCOUNT ( 
    ID BIGINT NOT NULL AUTO_INCREMENT,
    SALUTATION varchar(2) NOT NULL,
    FIRSTNAME varchar(255) NOT NULL,
    LASTNAME varchar(255) NOT NULL,
    BIRTHDAY date,
    PRIMARY KEY(ID)
);

CREATE TABLE T_ACCOUNT_SETTINGS (
	ID BIGINT NOT NULL AUTO_INCREMENT,
	EXPIRE DATE NOT NULL,
	PRODUCT VARCHAR(100) NOT NULL,
	PRIMARY KEY(ID)
);

CREATE TABLE T_INVOICE ( 
    ID BIGINT NOT NULL AUTO_INCREMENT,
    INVOICE_DATE date ,
    INVOICE_NO varchar(50) ,
    CURRENCY varchar(3) ,
    VAT number ,
    VAT_AMOUNT number ,
    NET_AMOUNT number ,
    TOTAL_AMOUNT number ,
    DISCOUNT number ,
    DISCOUNT_AMOUNT number ,
    PRIMARY KEY(ID)
);

CREATE TABLE T_INVOICE_RECIPIENT (
	ID BIGINT NOT NULL AUTO_INCREMENT,
	CORPORATION VARCHAR(100) NOT NULL,
	DEPARTMENT VARCHAR(100) NOT NULL,
	FIRSTNAME VARCHAR(100) NOT NULL,
	LASTNAME VARCHAR(100) NOT NULL,
	STREET VARCHAR(100) NOT NULL,
	STREET_ADDITION VARCHAR(100) NULL,
	ZIPCODE VARCHAR(100) NOT NULL,
	CITY VARCHAR(100) NOT NULL,
	STATE VARCHAR(100) NOT NULL,
	COUNTRY_ISO3 VARCHAR(100) NOT NULL,
	TAXID VARCHAR(100) NULL,
	PRIMARY KEY(ID)
);

CREATE TABLE T_INVOICE_ITEM (
	ID BIGINT NOT NULL AUTO_INCREMENT,
	POSITION INTEGER NOT NULL,
	CURRENCY VARCHAR(100) NOT NULL,
	PRODUCT_NO VARCHAR(100) NOT NULL,
	PRODUCT VARCHAR(100) NOT NULL,
	DESCRIPTION VARCHAR(100) NOT NULL,
	QUANTITY DOUBLE NOT NULL,
	VAT DOUBLE NOT NULL,
	VAT_AMOUNT DOUBLE NOT NULL,
	NET_AMOUNT DOUBLE NOT NULL,
	TOTAL_AMOUNT DOUBLE NOT NULL,
	DISCOUNT DOUBLE NOT NULL,
	DISCOUNT_AMOUNT DOUBLE NOT NULL,
	PRIMARY KEY(ID)
);
