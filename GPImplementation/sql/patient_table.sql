CREATE TABLE `patient` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(30) NOT NULL,
  `LAST_NAME` varchar(30) NOT NULL,
  `ADDRESS` varchar(50) NOT NULL,
  `TEL_NO` int(20),
  `DATE_TIME` DATETIME,
  `BOOKED` char(1) NOT NULL,
  PRIMARY KEY  (`ID`)
  );
  
  CREATE TABLE `visit` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(30) NOT NULL,
  `LAST_NAME` varchar(30) NOT NULL,
  `DATE_TIME` DATETIME,
  PRIMARY KEY  (`ID`)
  );
  
  INSERT INTO patient VALUES (null, "John", "Smith", "5 Blue Road, Dublin 7.", 0878342211, "14-06-18 10:30:00", "y"),
  							 (null, "Mary", "May", "76 Green Street, Dublin 9", 0871322292, "14-06-24 09:00:00", "y"),
  							 (null, "Peter", "May", "76 Green Street, Dublin 9", 0879393992, NULL, "n"),
  							 (null, "Maurice", "McFly", "3 Browns Street, Dublin 1", 0892353258, NULL, "n"),
  							 (null, "Stefan", "Bricks", "107 Fussagh Road, Dublin 9", 0879396112, "14-06-23 09:30:00", "y");
  							 							 
  							 
 drop table patient;
 UPDATE PATIENT SET DATE_TIME = "14-06-29 09:30:00", BOOKED = "y" WHERE FIRST_NAME = "Peter" AND LAST_NAME = "May";
 
  select * from patient;						