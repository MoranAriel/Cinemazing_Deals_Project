public class Notes {

    //creating the schema for the tables:
    // "CREATE SCHEMA `project_doron_moran` ;"

    //creating the COMPANIES table:
    // "CREATE TABLE `project_doron_moran`.`companies` (
    //  `ID` INT NOT NULL AUTO_INCREMENT,
    //  `NAME` VARCHAR(45) NOT NULL,
    //  `EMAIL` VARCHAR(45) NOT NULL,
    //  `PASSWORD` VARCHAR(45) NOT NULL,
    //  PRIMARY KEY (`ID`),
    //  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE,
    //  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC) VISIBLE);"

    //creating the CUSTOMERS table:
    // "CREATE TABLE `project_doron_moran`.`customers` (
    //  `ID` INT NOT NULL AUTO_INCREMENT,
    //  `FIRST_NAME` VARCHAR(45) NOT NULL,
    //  `LAST_NAME` VARCHAR(45) NOT NULL,
    //  `EMAIL` VARCHAR(45) NOT NULL,
    //  `PASSWORD` VARCHAR(45) NOT NULL,
    //  PRIMARY KEY (`ID`),
    //  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) VISIBLE);"

    //creating the CATEGORIES table:
    // "CREATE TABLE `project_doron_moran`.`categories` (
    //  `ID` INT NOT NULL AUTO_INCREMENT,
    //  `NAME` VARCHAR(45) NOT NULL,
    //  PRIMARY KEY (`ID`),
    //  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC) VISIBLE);"

    //creating the COUPONS table:
    // "CREATE TABLE `project_doron_moran`.`coupons` (
    //  `ID` INT NOT NULL AUTO_INCREMENT,
    //  `COMPANY_ID` INT NOT NULL,
    //  `CATEGORY_ID` INT NOT NULL,
    //  `TITLE` VARCHAR(45) NOT NULL,
    //  `DESCRIPTION` VARCHAR(45) NOT NULL,
    //  `START_DATE` DATE NOT NULL,
    //  `END_DATE` DATE NOT NULL,
    //  `AMOUNT` INT NULL,
    //  `PRICE` DECIMAL NULL,
    //  `IMAGE` VARCHAR(45) NOT NULL,
    //  PRIMARY KEY (`ID`),
    //  UNIQUE INDEX `COMPANY_ID_UNIQUE` (`COMPANY_ID` ASC) VISIBLE,
    //  UNIQUE INDEX `CATEGORY_ID_UNIQUE` (`CATEGORY_ID` ASC) VISIBLE,
    //  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC) VISIBLE);"

    //creating the CUSTOMERS_VS_COUPONS table:
    // "CREATE TABLE `project_doron_moran`.`customers_vs_coupons` (
    //  `CUSTOMER_ID` INT NOT NULL,
    //  `COUPON_ID` INT NOT NULL,
    //  PRIMARY KEY (`CUSTOMER_ID`, `COUPON_ID`),
    //  UNIQUE INDEX `customers_vs_couponscol_UNIQUE` (`COUPON_ID` ASC) VISIBLE,
    //  UNIQUE INDEX `CUSTOMER_ID_UNIQUE` (`CUSTOMER_ID` ASC) VISIBLE);"

}
