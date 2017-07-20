-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema oasisdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `oasisdb` ;

-- -----------------------------------------------------
-- Schema oasisdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `oasisdb` DEFAULT CHARACTER SET utf8 ;
USE `oasisdb` ;

-- -----------------------------------------------------
-- Table `oasisdb`.`employee_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`employee_role` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`employee_role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`role` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`employee` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nic` VARCHAR(10) NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `middle_name` VARCHAR(200) NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `gender` VARCHAR(1) NOT NULL,
  `dob` DATE NULL,
  `start_date` DATETIME(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `end_date` DATETIME(0) NULL,
  `employee_role_id` INT NOT NULL,
  `default_shift_start` TIME NOT NULL,
  `default_shift_end` TIME NOT NULL,
  `working_days` VARCHAR(7) NOT NULL DEFAULT '1111100',
  PRIMARY KEY (`id`),
  INDEX `fk_employee_employee_role_idx` (`employee_role_id` ASC),
  CONSTRAINT `fk_employee_employee_role`
    FOREIGN KEY (`employee_role_id`)
    REFERENCES `oasisdb`.`employee_role` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`speciality`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`speciality` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`speciality` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`doctor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`doctor` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`doctor` (
  `employee_id` INT NOT NULL,
  `speciality_id` INT NOT NULL,
  PRIMARY KEY (`employee_id`),
  INDEX `fk_doctor_employee1_idx` (`employee_id` ASC),
  INDEX `fk_doctor_speciality1_idx` (`speciality_id` ASC),
  CONSTRAINT `fk_doctor_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `oasisdb`.`employee` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_doctor_speciality1`
    FOREIGN KEY (`speciality_id`)
    REFERENCES `oasisdb`.`speciality` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`ward`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`ward` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`ward` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(200) NULL,
  `max_patient_count` INT NOT NULL,
  `current_patient_count` INT NOT NULL DEFAULT 0,
  `gender_acceptance` VARCHAR(1) NOT NULL,
  `supervisor_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ward_doctor1_idx` (`supervisor_id` ASC),
  CONSTRAINT `fk_ward_doctor1`
    FOREIGN KEY (`supervisor_id`)
    REFERENCES `oasisdb`.`doctor` (`employee_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`blood_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`blood_group` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`blood_group` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`ethnicity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`ethnicity` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`ethnicity` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`patient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`patient` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`patient` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nic` VARCHAR(10) NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `middle_name` VARCHAR(100) NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `gender` VARCHAR(1) NOT NULL,
  `dob` DATE NOT NULL,
  `blood_group_id` INT NOT NULL,
  `ethnicity_id` INT NOT NULL,
  `added_date` DATETIME(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `description` TEXT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nic_UNIQUE` (`nic` ASC),
  INDEX `fk_patient_blood_group1_idx` (`blood_group_id` ASC),
  INDEX `fk_patient_ethnicity1_idx` (`ethnicity_id` ASC),
  CONSTRAINT `fk_patient_blood_group1`
    FOREIGN KEY (`blood_group_id`)
    REFERENCES `oasisdb`.`blood_group` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_patient_ethnicity1`
    FOREIGN KEY (`ethnicity_id`)
    REFERENCES `oasisdb`.`ethnicity` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`physician_designation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`physician_designation` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`physician_designation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`physician`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`physician` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`physician` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `middle_name` VARCHAR(200) NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `physician_designation_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_physician_physician_designation1_idx` (`physician_designation_id` ASC),
  CONSTRAINT `fk_physician_physician_designation1`
    FOREIGN KEY (`physician_designation_id`)
    REFERENCES `oasisdb`.`physician_designation` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`test`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`test` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`test` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `description` VARCHAR(1000) NULL,
  `base_price` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`charge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`charge` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`charge` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE NOT NULL,
  `description` TEXT NULL,
  `charged_date` DATETIME(0) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`admission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`admission` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`admission` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `patient_id` INT NOT NULL,
  `physician_id` INT NOT NULL,
  `admission_consultant_id` INT NOT NULL,
  `leading_consultant_id` INT NOT NULL,
  `cause` TEXT NOT NULL,
  `admission_date` DATETIME(0) NOT NULL,
  `release_date` DATETIME(0) NULL,
  `charge_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_admission_patient1_idx` (`patient_id` ASC),
  INDEX `fk_admission_physician1_idx` (`physician_id` ASC),
  INDEX `fk_admission_doctor1_idx` (`admission_consultant_id` ASC),
  INDEX `fk_admission_doctor2_idx` (`leading_consultant_id` ASC),
  INDEX `fk_admission_charge1_idx` (`charge_id` ASC),
  CONSTRAINT `fk_admission_patient1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `oasisdb`.`patient` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_admission_physician1`
    FOREIGN KEY (`physician_id`)
    REFERENCES `oasisdb`.`physician` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_admission_doctor1`
    FOREIGN KEY (`admission_consultant_id`)
    REFERENCES `oasisdb`.`doctor` (`employee_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_admission_doctor2`
    FOREIGN KEY (`leading_consultant_id`)
    REFERENCES `oasisdb`.`doctor` (`employee_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_admission_charge1`
    FOREIGN KEY (`charge_id`)
    REFERENCES `oasisdb`.`charge` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`treatment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`treatment` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`treatment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `admission_id` INT NOT NULL,
  `description` TEXT NOT NULL,
  `result` TEXT NULL,
  `given_date` DATETIME(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `charge_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_treatment_admission1_idx` (`admission_id` ASC),
  INDEX `fk_treatment_charge1_idx` (`charge_id` ASC),
  CONSTRAINT `fk_treatment_admission1`
    FOREIGN KEY (`admission_id`)
    REFERENCES `oasisdb`.`admission` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_treatment_charge1`
    FOREIGN KEY (`charge_id`)
    REFERENCES `oasisdb`.`charge` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`payment` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`payment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `admission_id` INT NOT NULL,
  `amount` DOUBLE NOT NULL,
  `payment_date` DATETIME(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  PRIMARY KEY (`id`),
  INDEX `fk_payment_admission1_idx` (`admission_id` ASC),
  CONSTRAINT `fk_payment_admission1`
    FOREIGN KEY (`admission_id`)
    REFERENCES `oasisdb`.`admission` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`employee_telephone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`employee_telephone` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`employee_telephone` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NOT NULL,
  `telephone` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_employee_telephone_employee1_idx` (`employee_id` ASC),
  CONSTRAINT `fk_employee_telephone_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `oasisdb`.`employee` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`employee_address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`employee_address` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`employee_address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NOT NULL,
  `street` VARCHAR(100) NOT NULL,
  `town` VARCHAR(50) NULL,
  `province` VARCHAR(50) NULL,
  `postal_code` VARCHAR(5) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_employee_address_employee1_idx` (`employee_id` ASC),
  CONSTRAINT `fk_employee_address_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `oasisdb`.`employee` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`employee_email`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`employee_email` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`employee_email` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_employee_email_employee1_idx` (`employee_id` ASC),
  CONSTRAINT `fk_employee_email_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `oasisdb`.`employee` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`nurse`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`nurse` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`nurse` (
  `employee_id` INT NOT NULL,
  PRIMARY KEY (`employee_id`),
  INDEX `fk_nurse_employee1_idx` (`employee_id` ASC),
  CONSTRAINT `fk_nurse_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `oasisdb`.`employee` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`degree`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`degree` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`degree` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `acronym` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`employee_has_degree`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`employee_has_degree` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`employee_has_degree` (
  `employee_id` INT NOT NULL,
  `degree_id` INT NOT NULL,
  PRIMARY KEY (`employee_id`, `degree_id`),
  INDEX `fk_employee_has_degree_degree1_idx` (`degree_id` ASC),
  INDEX `fk_employee_has_degree_employee1_idx` (`employee_id` ASC),
  CONSTRAINT `fk_employee_has_degree_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `oasisdb`.`employee` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_has_degree_degree1`
    FOREIGN KEY (`degree_id`)
    REFERENCES `oasisdb`.`degree` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`janitor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`janitor` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`janitor` (
  `employee_id` INT NOT NULL,
  PRIMARY KEY (`employee_id`),
  INDEX `fk_janitor_employee1_idx` (`employee_id` ASC),
  CONSTRAINT `fk_janitor_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `oasisdb`.`employee` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`ward_has_doctor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`ward_has_doctor` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`ward_has_doctor` (
  `ward_id` INT NOT NULL,
  `doctor_employee_id` INT NOT NULL,
  PRIMARY KEY (`ward_id`, `doctor_employee_id`),
  INDEX `fk_ward_has_doctor_doctor1_idx` (`doctor_employee_id` ASC),
  INDEX `fk_ward_has_doctor_ward1_idx` (`ward_id` ASC),
  CONSTRAINT `fk_ward_has_doctor_ward1`
    FOREIGN KEY (`ward_id`)
    REFERENCES `oasisdb`.`ward` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ward_has_doctor_doctor1`
    FOREIGN KEY (`doctor_employee_id`)
    REFERENCES `oasisdb`.`doctor` (`employee_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`ward_has_nurse`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`ward_has_nurse` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`ward_has_nurse` (
  `ward_id` INT NOT NULL,
  `nurse_employee_id` INT NOT NULL,
  PRIMARY KEY (`ward_id`, `nurse_employee_id`),
  INDEX `fk_ward_has_nurse_nurse1_idx` (`nurse_employee_id` ASC),
  INDEX `fk_ward_has_nurse_ward1_idx` (`ward_id` ASC),
  CONSTRAINT `fk_ward_has_nurse_ward1`
    FOREIGN KEY (`ward_id`)
    REFERENCES `oasisdb`.`ward` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ward_has_nurse_nurse1`
    FOREIGN KEY (`nurse_employee_id`)
    REFERENCES `oasisdb`.`nurse` (`employee_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`ward_has_janitor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`ward_has_janitor` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`ward_has_janitor` (
  `ward_id` INT NOT NULL,
  `janitor_employee_id` INT NOT NULL,
  PRIMARY KEY (`ward_id`, `janitor_employee_id`),
  INDEX `fk_ward_has_janitor_janitor1_idx` (`janitor_employee_id` ASC),
  INDEX `fk_ward_has_janitor_ward1_idx` (`ward_id` ASC),
  CONSTRAINT `fk_ward_has_janitor_ward1`
    FOREIGN KEY (`ward_id`)
    REFERENCES `oasisdb`.`ward` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ward_has_janitor_janitor1`
    FOREIGN KEY (`janitor_employee_id`)
    REFERENCES `oasisdb`.`janitor` (`employee_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`patient_address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`patient_address` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`patient_address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `patient_id` INT NOT NULL,
  `street` VARCHAR(100) NOT NULL,
  `town` VARCHAR(50) NULL,
  `province` VARCHAR(50) NULL,
  `postal_code` VARCHAR(5) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_patient_address_patient1_idx` (`patient_id` ASC),
  CONSTRAINT `fk_patient_address_patient1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `oasisdb`.`patient` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`patient_telephone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`patient_telephone` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`patient_telephone` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `patient_id` INT NOT NULL,
  `telephone` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_patient_telephone_patient1_idx` (`patient_id` ASC),
  CONSTRAINT `fk_patient_telephone_patient1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `oasisdb`.`patient` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`patient_email`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`patient_email` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`patient_email` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `patient_id` INT NOT NULL,
  `email` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_patient_email_patient1_idx` (`patient_id` ASC),
  CONSTRAINT `fk_patient_email_patient1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `oasisdb`.`patient` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`emergency_contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`emergency_contact` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`emergency_contact` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `patient_id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `relationship` VARCHAR(50) NOT NULL,
  `telephone` VARCHAR(10) NOT NULL,
  `address` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_emergency_contact_patient1_idx` (`patient_id` ASC),
  CONSTRAINT `fk_emergency_contact_patient1`
    FOREIGN KEY (`patient_id`)
    REFERENCES `oasisdb`.`patient` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`admission_has_test`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`admission_has_test` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`admission_has_test` (
  `admission_id` INT NOT NULL,
  `test_id` INT NOT NULL,
  `tested_date` DATETIME(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `charge_id` INT NOT NULL,
  PRIMARY KEY (`admission_id`, `test_id`),
  INDEX `fk_admission_has_test_test1_idx` (`test_id` ASC),
  INDEX `fk_admission_has_test_admission1_idx` (`admission_id` ASC),
  INDEX `fk_admission_has_test_charge1_idx` (`charge_id` ASC),
  CONSTRAINT `fk_admission_has_test_admission1`
    FOREIGN KEY (`admission_id`)
    REFERENCES `oasisdb`.`admission` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_admission_has_test_test1`
    FOREIGN KEY (`test_id`)
    REFERENCES `oasisdb`.`test` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_admission_has_test_charge1`
    FOREIGN KEY (`charge_id`)
    REFERENCES `oasisdb`.`charge` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`admission_has_doctor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`admission_has_doctor` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`admission_has_doctor` (
  `admission_id` INT NOT NULL,
  `doctor_employee_id` INT NOT NULL,
  `examined_date` DATETIME(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(),
  `note` TEXT NULL,
  PRIMARY KEY (`admission_id`, `doctor_employee_id`),
  INDEX `fk_admission_has_doctor_doctor1_idx` (`doctor_employee_id` ASC),
  INDEX `fk_admission_has_doctor_admission1_idx` (`admission_id` ASC),
  CONSTRAINT `fk_admission_has_doctor_admission1`
    FOREIGN KEY (`admission_id`)
    REFERENCES `oasisdb`.`admission` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_admission_has_doctor_doctor1`
    FOREIGN KEY (`doctor_employee_id`)
    REFERENCES `oasisdb`.`doctor` (`employee_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`attendance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`attendance` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`attendance` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NOT NULL,
  `shift_start` DATETIME(0) NOT NULL,
  `shift_end` DATETIME(0) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_attendance_employee1_idx` (`employee_id` ASC),
  CONSTRAINT `fk_attendance_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `oasisdb`.`employee` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`physician_telephone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`physician_telephone` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`physician_telephone` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `physician_id` INT NOT NULL,
  `telephone` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_physician_telephone_physician1_idx` (`physician_id` ASC),
  CONSTRAINT `fk_physician_telephone_physician1`
    FOREIGN KEY (`physician_id`)
    REFERENCES `oasisdb`.`physician` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `oasisdb`.`employee_login`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `oasisdb`.`employee_login` ;

CREATE TABLE IF NOT EXISTS `oasisdb`.`employee_login` (
  `employee_id` INT NOT NULL,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`employee_id`),
  INDEX `fk_employee_login_employee1_idx` (`employee_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  CONSTRAINT `fk_employee_login_employee1`
    FOREIGN KEY (`employee_id`)
    REFERENCES `oasisdb`.`employee` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `oasisdb`.`employee_role`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`employee_role` (`id`, `role`) VALUES (1, 'Doctor');
INSERT INTO `oasisdb`.`employee_role` (`id`, `role`) VALUES (2, 'Nurse');
INSERT INTO `oasisdb`.`employee_role` (`id`, `role`) VALUES (3, 'Tech');
INSERT INTO `oasisdb`.`employee_role` (`id`, `role`) VALUES (4, 'Therapist');
INSERT INTO `oasisdb`.`employee_role` (`id`, `role`) VALUES (5, 'Pharmacist');
INSERT INTO `oasisdb`.`employee_role` (`id`, `role`) VALUES (6, 'Medical Assistant');
INSERT INTO `oasisdb`.`employee_role` (`id`, `role`) VALUES (7, 'Medical Lab Technologist');
INSERT INTO `oasisdb`.`employee_role` (`id`, `role`) VALUES (8, 'Dietician');
INSERT INTO `oasisdb`.`employee_role` (`id`, `role`) VALUES (9, 'Janitor');

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`employee`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (1, '950722355', 'Oshan', 'Ivantha', 'Mudannayake', 'm', '1995-03-12', '2000-01-07 08:00:00', NULL, 1, '08:30:00', '15:00:00', '1111101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (2, '454162944', 'Henricus', NULL, 'Erdem', 'm', '1933-02-18', '2004-01-05 08:00:00', NULL, 1, '11:30:00', '02:00:00', '1101101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (3, '489059824', 'Samantha', NULL, 'Wand', 'f', '1995-12-28', '2013-01-03 08:00:00', NULL, 9, '09:00:00', '16:30:00', '1110111');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (4, '159348147', 'Wilimar', NULL, 'Tunnelly', 'm', '1966-04-19', '2015-07-18 08:00:00', NULL, 9, '09:00:00', '16:30:00', '1111100');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (5, '507522997', 'Zeliha', NULL, 'Timayev', 'f', '1971-09-17', '2011-12-15 08:00:00', NULL, 2, '08:00:00', '15:30:00', '1110111');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (6, '196169052', 'Miranda', NULL, 'Brandybuck', 'f', '1977-02-01', '2012-02-23 08:00:00', NULL, 4, '15:00:00', '04:00:00', '1111101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (7, '441135250', 'Walliyullah', NULL, 'Ganem', 'm', '1939-01-23', '2001-09-15 08:00:00', NULL, 5, '09:00:00', '16:30:00', '1011101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (8, '420279171', 'Lisa', NULL, 'Mills', 'f', '1966-04-06', '2000-05-28 08:00:00', NULL, 1, '08:00:00', '15:30:00', '1101101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (9, '180200915', 'Aquilesia', NULL, 'Vergara', 'f', '1985-07-16', '2008-06-22 08:00:00', NULL, 1, '15:00:00', '04:00:00', '1110111');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (10, '342229675', 'Maximilian', NULL, 'Abt', 'm', '1962-03-22', '2001-09-15 08:00:00', NULL, 8, '18:00:00', '07:30:00', '1111101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (11, '437885549', 'Karin', NULL, 'Jaeger', 'f', '1996-08-05', '2013-03-19 08:00:00', NULL, 2, '11:30:00', '02:00:00', '1111101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (12, '327762488', 'Kristina', NULL, 'Hedlund', 'f', '1963-06-12', '2007-12-12 08:00:00', NULL, 1, '18:00:00', '07:30:00', '1011101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (13, '347781159', 'Abdeljalil', NULL, 'Brandse', 'm', '1945-09-25', '2000-04-25 08:00:00', NULL, 1, '08:00:00', '15:30:00', '1011101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (14, '282102962', 'Brianna', NULL, 'Dolling', 'f', '1966-04-22', '2000-12-03 08:00:00', NULL, 1, '08:00:00', '15:30:00', '1111101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (15, '764013956', 'Riia', NULL, 'Sulin', 'f', '1965-06-29', '2000-03-27 08:00:00', NULL, 2, '08:00:00', '15:30:00', '1101101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (16, '162726179', 'Noel', NULL, 'Antal', 'm', '1941-10-20', '2005-05-27 08:00:00', NULL, 3, '09:00:00', '16:30:00', '1011101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (17, '026109877', 'Bianca', NULL, 'Ek', 'f', '1933-01-26', '2000-06-09 08:00:00', NULL, 8, '15:00:00', '04:00:00', '1110111');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (18, '533565226', 'Jurek', NULL, 'Nowakowski', 'm', '1982-12-06', '2014-10-18 08:00:00', NULL, 7, '09:00:00', '16:30:00', '1111101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (19, '548655439', 'Terence', NULL, 'Kirillov', 'm', '1937-11-02', '2015-05-22 08:00:00', NULL, 1, '11:30:00', '02:00:00', '1111100');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (20, '763012806', 'Lisa', NULL, 'Aguinaldo', 'f', '1934-11-17', '2011-11-25 08:00:00', NULL, 8, '18:00:00', '07:30:00', '1111101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (21, '630090594', 'Marina', NULL, 'Gruenewald', 'f', '1933-07-13', '2012-02-25 08:00:00', NULL, 2, '18:00:00', '07:30:00', '1101101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (22, '691052063', 'Mochihiro', NULL, 'Tani', 'm', '1976-02-27', '2004-04-25 08:00:00', NULL, 1, '09:00:00', '16:30:00', '1111101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (23, '511051208', 'Tymon', NULL, 'Wysocki', 'm', '1934-12-22', '2007-01-04 08:00:00', '2016-09-07 15:00:00', 1, '08:00:00', '15:30:00', '1101101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (24, '528895517', 'Margo', NULL, 'Antall', 'f', '1943-12-26', '2012-09-01 08:00:00', NULL, 7, '15:00:00', '04:00:00', '1111100');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (25, '420585946', 'Mohamad', NULL, 'Isaksson', 'm', '1991-02-27', '2013-11-18 08:00:00', NULL, 9, '09:00:00', '16:30:00', '1111100');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (26, '088243202', 'Leila', NULL, 'Olsson', 'f', '1980-07-18', '2010-01-15 08:00:00', NULL, 9, '08:00:00', '15:30:00', '1111101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (27, '044583213', 'Asmait', NULL, 'Asmara', 'f', '1972-12-21', '2013-04-11 08:00:00', NULL, 2, '11:30:00', '02:00:00', '1111100');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (28, '459620343', 'Bowie', NULL, 'de Lange', 'm', '1970-12-17', '2006-03-05 08:00:00', NULL, 1, '09:00:00', '16:30:00', '1111101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (29, '494125294', 'Uzodimma', NULL, 'Chibugo', 'f', '1955-04-16', '2007-01-14 08:00:00', NULL, 1, '11:30:00', '02:00:00', '1111101');
INSERT INTO `oasisdb`.`employee` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `start_date`, `end_date`, `employee_role_id`, `default_shift_start`, `default_shift_end`, `working_days`) VALUES (30, '508247379', 'Carlos', NULL, 'Correia', 'm', '1933-07-18', '2017-02-12 08:00:00', NULL, 4, '08:00:00', '15:30:00', '1101101');

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`speciality`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (1, 'Allergy and immunology', 'Immunology is a branch of biology that covers the study of immune systems in all organisms.[1] It was the Russian biologist Ilya Ilyich Mechnikov who boosted studies on immunology, and received the Nobel Prize in 1908 for his work. He jabbed the thorn of a rose on a starfish and noted that, 24 hours later, cells were surrounding the tip. It was an active response of the body, trying to maintain its integrity. It was Mechnikov who first observed the phenomenon of phagocytosis, in which the body defends itself against a foreign body, and coined the term. Immunology charts, measures, and contextualizes the: physiological functioning of the immune system in states of both health and diseases; malfunctions of the immune system in immunological disorders (such as autoimmune diseases, hypersensitivities, immune deficiency, and transplant rejection); the physical, chemical and physiological characteristics of the components of the immune system in vitro, in situ, and in vivo. Immunology has applications in numerous disciplines of medicine, particularly in the fields of organ transplantation, oncology, virology, bacteriology, parasitology, psychiatry, and dermatology.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (2, 'Adolescent medicine', 'Adolescent medicine or hebiatrics is a medical subspecialty that focuses on care of patients who are in the adolescent period of development, generally ranging from the last years of elementary school until graduation from high school (some doctors in this subspecialty treat young adults attending college at area clinics, in the subfield of college health). Patients have generally entered puberty, which typically begins between the ages of 11 and 13 for boys.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (3, 'Anaesthesiology', 'An anesthesiologist (American English) or anaesthetist (British English) is a medical practitioner trained in anesthesia and perioperative medicine.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (4, 'Aerospace medicine', 'Aviation medicine, also called flight medicine or aerospace medicine, is a preventive or occupational medicine in which the patients/subjects are pilots, aircrews, or astronauts.[1] The specialty strives to treat or prevent conditions to which aircrews are particularly susceptible, applies medical knowledge to the human factors in aviation and is thus a critical component of aviation safety.[1] A military practitioner of aviation medicine may be called a flight surgeon and a civilian practitioner is an aviation medical examiner.[1] One of the biggest differences between the military and civilian flight docs is the military flight surgeon\'s requirement to log flight hours.[2]');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (5, 'Pathology', 'Pathology (from the Greek roots of pathos (πάθος), meaning \"experience\" or \"suffering\", and -logia (-λογία), \"study of\") is a significant component of the causal study of disease and a major field in modern medicine and diagnosis.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (6, 'Cardiology', 'Cardiology (from Greek καρδίᾱ kardiā, \"heart\" and -λογία -logia, \"study\") is a branch of medicine dealing with disorders of the heart as well as parts of the circulatory system. The field includes medical diagnosis and treatment of congenital heart defects, coronary artery disease, heart failure, valvular heart disease and electrophysiology. Physicians who specialize in this field of medicine are called cardiologists, a specialty of internal medicine. Pediatric cardiologists are pediatricians who specialize in cardiology. Physicians who specialize in cardiac surgery are called cardiothoracic surgeons or cardiac surgeons, a specialty of general surgery.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (7, 'Paediatric cardiology', NULL);
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (8, 'Cardiothoracic surgery', 'Cardiothoracic surgery (also known as thoracic surgery) is the field of medicine involved in surgical treatment of organs inside the thorax (the chest)—generally treatment of conditions of the heart (heart disease) and lungs (lung disease). In most countries, cardiac surgery (involving the heart and the great vessels) and general thoracic surgery (involving the lungs, esophagus, thymus, etc.) are separate surgical specialties; the exceptions are the United States, Australia, New Zealand, and some EU countries, such as the United Kingdom and Portugal.[1]');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (9, 'Child and adolescent psychiatry and psychotherapy', NULL);
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (10, 'Clinical neurophysiology', 'Clinical neurophysiology is a medical specialty that studies the central and peripheral nervous systems through the recording of bioelectrical activity, whether spontaneous or stimulated. It encompasses both research regarding the pathophysiology along with clinical methods used to diagnose diseases involving both central and peripheral nervous systems. Examinations in the clinical neurophysiology field are not limited to tests conducted in a laboratory. It is thought of as an extension of a neurologic consultation. Tests that are conducted are concerned with measuring the electrical functions of the brain, spinal cord, and nerves in the limbs and muscles. It can give the precise definition of site, the type and degree of the lesion, along with revealing the abnormalities that are in question. Due to these abilities, clinical neurophysiology is used to mainly help diagnose diseases rather than treat them.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (11, 'Colon and Rectal Surgery', 'Colorectal surgery is a field in medicine, dealing with disorders of the rectum, anus, and colon. The field is also known as proctology, but the latter term is now used infrequently within medicine, and is most often employed to identify practices relating to the anus and rectum in particular. The word proctology is derived from the Greek words πρωκτός proktos, meaning \"anus\" or \"hindparts\", and -λογία -logia, meaning \"science\" or \"study\".');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (12, 'Dermatology-Venereology', NULL);
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (13, 'Emergency medicine', 'Emergency medicine, formerly known in some countries as accident and emergency medicine, is the medical specialty involving care for undifferentiated and unscheduled patients with illnesses or injuries requiring immediate medical attention. In their role as first-line providers, emergency physicians are responsible for initiating investigations and interventions to diagnose and/or treat patients in the acute phase (including initial resuscitation and stabilization), coordinating care with physicians from other specialities, and making decisions regarding a patient\'s need for hospital admission, observation, or discharge. Emergency physicians generally practice in hospital emergency departments, pre-hospital settings via emergency medical services, and intensive care units, but may also work in primary care settings such as urgent care clinics.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (14, 'Endocrinology', 'Endocrinology (from Greek ἔνδον, endon, \"within\"; κρίνω, krīnō, \"to separate\"; and -λογία, -logia) is a branch of biology and medicine dealing with the endocrine system, its diseases, and its specific secretions known as hormones. It is also concerned with the integration of developmental events proliferation, growth, and differentiation, and the psychological or behavioral activities of metabolism, growth and development, tissue function, sleep, digestion, respiration, excretion, mood, stress, lactation, movement, reproduction, and sensory perception caused by hormones. Specializations include behavioral endocrinology[1][2][3] and comparative endocrinology.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (15, 'Gastroenterology', 'Gastroenterology (MeSH heading)[1] is the branch of medicine focused on the digestive system and its disorders.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (16, 'General practice', 'In the medical profession, a general practitioner (GP) is a medical doctor who treats acute and chronic illnesses and provides preventive care and health education to patients.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (17, 'Geriatrics', 'Geriatrics or geriatric medicine[1] is a specialty that focuses on health care of elderly people.[2] It aims to promote health by preventing and treating diseases and disabilities in older adults. There is no set age at which patients may be under the care of a geriatrician or geriatric physician, a physician who specializes in the care of elderly people. Rather, this decision is determined by the individual patient\'s needs, and the availability of a specialist. It is important to note the difference between geriatrics, the care of aged people, and gerontology, which is the study of the aging process itself. The term geriatrics comes from the Greek γέρων geron meaning \"old man\", and ιατρός iatros meaning \"healer\". However, geriatrics is sometimes called medical gerontology.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (18, 'Obstetrics and gynaecology', 'Obstetrics and Gynecology (commonly known as OB/GYN, OBG, O&G or Obs & Gynae) is the medical specialty that deals with obstetrics and gynecology. The postgraduate training program for both aspects is unified. This combined training prepares the practicing OB/GYN to be adept at the care of female reproductive organs\' health and at the management of pregnancy.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (19, 'Health informatics', 'Health informatics (also called health care informatics, healthcare informatics, medical informatics, nursing informatics, clinical informatics, or biomedical informatics) is informatics in health care. It is a multidisciplinary field that uses health information technology (HIT) to improve health care via any combination of higher quality, higher efficiency (spurring lower cost and thus greater availability), and new opportunities. The disciplines involved include information science, computer science, social science, behavioral science, management science, and others. The NLM defines health informatics as \"the interdisciplinary study of the design, development, adoption and application of IT-based innovations in healthcare services delivery, management and planning.\"[1] It deals with the resources, devices, and methods required to optimize the acquisition, storage, retrieval, and use of information in health and biomedicine. Health informatics tools include amongst others computers, clinical guidelines, formal medical terminologies, and information and communication systems.[2][3] It is applied to the areas of nursing, clinical medicine, dentistry, pharmacy, public health, occupational therapy, physical therapy, biomedical research, and alternative medicine.[4][unreliable medical source?] All of which are designed to improve the overall of effectiveness of patient care delivery by ensuring that the data generated is of a high quality e.g. an mHealth based early warning scorecard.[5]');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (20, 'Hospice and palliative medicine', NULL);
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (21, 'Infectious disease', 'Infectious disease, also known as infectious diseases, infectious medicine, infectious disease medicine or infectiology, is a medical specialty dealing with the diagnosis, control and treatment of infections. An infectious disease (ID) specialist\'s practice may consist largely of managing nosocomial (hospital-acquired) infections, or it may be out-patient based.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (22, 'Internal medicine', 'Internal medicine or general medicine (in Commonwealth nations) is the medical specialty dealing with the prevention, diagnosis, and treatment of adult diseases. Physicians specializing in internal medicine are called internists, or physicians (without a modifier) in Commonwealth nations. Internists are skilled in the management of patients who have undifferentiated or multi-system disease processes. Internists care for hospitalized and ambulatory patients and may play a major role in teaching and research.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (23, 'Interventional radiology', 'Interventional radiology (IR), sometimes known as vascular and interventional radiology (VIR), is a medical sub-specialty which provides minimally invasive image-guided diagnosis and treatment of disease. Although the range of procedures performed by interventional radiologists is broad, the unifying concept behind these procedures is the application of image guidance and minimally invasive techniques in order to minimize risk to the patient.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (24, 'Vascular medicine', NULL);
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (25, 'Microbiology', 'Microbiology (from Greek μῑκρος, mīkros, \"small\"; βίος, bios, \"life\"; and -λογία, -logia) is the study of microscopic organisms, those being unicellular (single cell), multicellular (cell colony), or acellular (lacking cells).[1] Microbiology encompasses numerous sub-disciplines including virology, mycology, parasitology, and bacteriology.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (26, 'Nephrology', 'Nephrology (from Greek νεφρός nephros \"kidney\", combined with the suffix -logy, \"the study of\") is a specialty of medicine and pediatrics that concerns itself with the kidneys: the study of normal kidney function and kidney problems, the preservation of kidney health, and the treatment of kidney problems, from diet and medication to renal replacement therapy (dialysis and kidney transplantation). Systemic conditions that affect the kidneys (such as diabetes and autoimmune disease) and systemic problems that occur as a result of kidney problems (such as renal osteodystrophy and hypertension) are also studied in nephrology. A physician who has undertaken additional training to become an expert in nephrology may call themselves a nephrologist or renal physician');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (27, 'Neurology', 'Neurology (from Greek: νεῦρον, neuron, and the suffix -λογία -logia \"study of\") is a branch of medicine dealing with disorders of the nervous system. Neurology deals with the diagnosis and treatment of all categories of conditions and disease involving the central and peripheral nervous system (and its subdivisions, the autonomic nervous system and the somatic nervous system); including their coverings, blood vessels, and all effector tissue, such as muscle.[1] Neurological practice relies heavily on the field of neuroscience, which is the scientific study of the nervous system.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (28, 'Neurosurgery', 'Neurosurgery, or neurological surgery, is the medical specialty concerned with the prevention, diagnosis, surgical treatment, and rehabilitation of disorders which affect any portion of the nervous system including the brain, spinal cord, peripheral nerves, and extra-cranial cerebrovascular system.[1]');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (29, 'Nuclear medicine', 'Nuclear medicine is a medical specialty involving the application of radioactive substances in the diagnosis and treatment of disease. Nuclear medicine, in a sense, is \"radiology done inside out\" or \"endoradiology\" because it records radiation emitting from within the body rather than radiation that is generated by external sources like X-rays. In addition, nuclear medicine scans differ from radiology as the emphasis is not on imaging anatomy but the function and for such reason, it is called a physiological imaging modality. Single Photon Emission Computed Tomography or SPECT and Positron Emission Tomography or PET scans are the two most common imaging modalities in nuclear medicine.[1]');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (30, 'Occupational medicine', 'Occupational medicine, especially until 1960[1] called industrial medicine,[2][3] is the branch of medicine which deals with the maintenance of health in the workplace, including the prevention and treatment of diseases and injuries, and also promotes productivity and social adjustment.[2][4]');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (31, 'Ophthalmology', 'Ophthalmology (/ˌɒfθɑːlˈmɑːlədʒi/ or /ˌɒpθɑːlˈmɒlədʒi/)[1] is the branch of medicine that deals with the anatomy, physiology and diseases of the eyeball.[2] An ophthalmologist is a specialist in medical and surgical eye problems. Since ophthalmologists perform operations on eyes, they are both surgical and medical specialists. A multitude of diseases and conditions can be diagnosed from the eye.[3]');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (32, 'Orthodontics', 'Orthodontia, also known as orthodontics and dentofacial orthopedics, is a specialty field of dentistry. An orthodontist is a specialist who has undergone special training in a dental school or college after they have graduated in dentistry. It was established by the efforts of pioneering orthodontists such as Edward Angle and Norman William Kingsley. The specialty deals primarily with the diagnosis, prevention and correction of malpositioned teeth and the jaws.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (33, 'Orthopaedics', 'Orthopedic surgery or orthopedics (alternatively spelled orthopaedic surgery and orthopaedics) is the branch of surgery concerned with conditions involving the musculoskeletal system. Orthopedic surgeons use both surgical and nonsurgical means to treat musculoskeletal trauma, spine diseases, sports injuries, degenerative diseases, infections, tumors, and congenital disorders.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (34, 'Oral and maxillofacial surgery', 'Oral and maxillofacial surgery (OMS or OMFS) specializes in treating many diseases, injuries and defects in the head, neck, face, jaws and the hard and soft tissues of the oral (mouth) and maxillofacial (jaws and face) region. It is an internationally recognized surgical specialty. In some countries around the world, including the United States, India, Canada, Brazil, Australia, Sweden and Israel, it is a recognized specialty of dentistry; in others, such as the UK and most of Europe, it is recognized as both a specialty of medicine and dentistry, and a dual degree in medicine and dentistry is compulsory.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (35, 'Otorhinolaryngology', 'Otorhinolaryngology /oʊtoʊˌraɪnoʊˌlærənˈɡɒlədʒi/ (also called otolaryngology-head and neck surgery) is a surgical subspecialty within medicine that deals with conditions of the ear, nose, and throat (ENT) and related structures of the head and neck. Doctors who specialize in this area are called otorhinolaryngologists, otolaryngologists, ENT doctors, ENT surgeons, or head and neck surgeons. Patients seek treatment from an otorhinolaryngologist for diseases of the ear, nose, throat, base of the skull, and for the surgical management of cancers and benign tumors of the head and neck.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (36, 'Paediatrics', 'Pediatrics (also spelled paediatrics or pædiatrics) is the branch of medicine that deals with the medical care of infants, children, and adolescents. The American Academy of Pediatrics recommends people be under pediatric care up to the age of 21.[1] A medical practitioner who specializes in this area is known as a pediatrician, or paediatrician. The word pediatrics and its cognates mean \"healer of children\"; they derive from two Greek words: παῖς (pais \"child\") and ἰατρός (iatros \"doctor, healer\"). Pediatricians work both in hospitals, particularly those working in its specialized subfields such as neonatology, and as primary care physicians.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (37, 'Paediatric allergology', NULL);
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (38, 'Paediatric endocrinology and diabetes', NULL);
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (39, 'Paediatric gastroenterology, hepatology and nutrition', NULL);
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (40, 'Paediatric haematology and oncology', NULL);
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (41, 'Paediatric infectious diseases', NULL);
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (42, 'Neonatology', 'Neonatology is a subspecialty of pediatrics that consists of the medical care of newborn infants, especially the ill or premature newborn infant. It is a hospital-based specialty, and is usually practiced in neonatal intensive care units (NICUs). The principal patients of neonatologists are newborn infants who are ill or requiring special medical care due to prematurity, low birth weight, intrauterine growth retardation, congenital malformations (birth defects), sepsis, pulmonary hypoplasia or birth asphyxias.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (43, 'Paediatric nephrology', NULL);
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (44, 'Paediatric respiratory medicine', NULL);
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (45, 'Paediatric rheumatology', NULL);
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (46, 'Paediatric surgery', 'Pediatric surgery is a subspecialty of surgery involving the surgery of fetuses, infants, children, adolescents, and young adults.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (47, 'Physical medicine and rehabilitation', 'Physical medicine and rehabilitation (PM&R), also known as physiatry /fᵻˈzaɪ.ətri/ or rehabilitation medicine, or physical and rehabilitation medicine (PRM) outside of the United States, is a branch of medicine that aims to enhance and restore functional ability and quality of life to those with physical impairments or disabilities. A physician having completed training in this field is referred to as a physiatrist. Physiatrists specialize in restoring optimal function to people with injuries to the muscles, bones, ligaments, or nervous system.[1]');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (48, 'Plastic, reconstructive and aesthetic surgery', 'Plastic surgery is a surgical specialty involving the restoration, reconstruction, or alteration of the human body. It includes cosmetic or aesthetic surgery, reconstructive surgery, craniofacial surgery, hand surgery, microsurgery, and the treatment of burns.[1]');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (49, 'Podiatric medicine', 'Podiatry (/poʊˈdaɪətri/) or podiatric medicine (/poʊdiˈætrᵻk/ or /poʊˈdaɪətrᵻk/) is a branch of medicine devoted to the study, diagnosis, and medical and surgical treatment of disorders of the foot, ankle and lower extremity. The term podiatry came into use in the early 20th century in the United States and is now used worldwide, including countries such as the United Kingdom and Australia.[1]');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (50, 'Pulmonology', 'Pulmonology is a medical speciality that deals with diseases involving the respiratory tract.[1] The term is derived from the Latin word pulmō, pulmōnis (\"lung\") and the Greek suffix -λογία, -logia (\"study of\"). Pulmonology is synonymous with pneumology (from Greek πνεύμων (\"lung\") and -λογία), respirology and respiratory medicine.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (51, 'Psychiatry', 'Psychiatry is the medical specialty devoted to the diagnosis, prevention, study, and treatment of mental disorders.[1][2] These include various abnormalities related to mood, behaviour, cognition, and perceptions.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (52, 'Public Health', 'Public health refers to \"the science and art of preventing disease, prolonging life and promoting human health through organized efforts and informed choices of society, organizations, public and private, communities and individuals.\"[1] It is concerned with threats to health based on population health analysis.[2] The population in question can be as small as a handful of people, or as large as all the inhabitants of several continents (for instance, in the case of a pandemic). The dimensions of health can encompass \"a state of complete physical, mental and social well-being and not merely the absence of disease or infirmity,\" as defined by the United Nations\' World Health Organization.[3] Public health incorporates the interdisciplinary approaches of epidemiology, biostatistics and health services. Environmental health, community health, behavioral health, health economics, public policy, mental health and occupational safety and health are other important subfields.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (53, 'Radiology', 'Radiology is a specialty that uses medical imaging to diagnose and treat diseases seen within the body. A variety of imaging techniques such as X-ray radiography, ultrasound, computed tomography (CT), nuclear medicine including positron emission tomography (PET), and magnetic resonance imaging (MRI) are used to diagnose and/or treat diseases. Interventional radiology is the performance of (usually minimally invasive) medical procedures with the guidance of imaging technologies.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (54, 'Sports medicine', 'Sports medicine, also known as sport and exercise medicine (SEM), is a branch of medicine that deals with physical fitness and the treatment and prevention of injuries related to sports and exercise. Although most sports teams have employed team physicians for many years, it is only since the late 20th century that sports medicine has emerged as a distinct field of health care.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (55, 'Neuroradiology', 'Neuroradiology is a subspecialty of radiology focusing on the diagnosis and characterization of abnormalities of the central and peripheral nervous system, spine, and head and neck using neuroimaging techniques. Primary imaging modalities include computed tomography (CT) and magnetic resonance imaging (MRI). Plain radiography is utilized on a limited basis and ultrasound is used in limited circumstances, in particular in the pediatric population. Angiography is traditionally used for diagnosis of vascular abnormalities or diagnosis and characterization of masses or other lesions but is being replaced in many instances by CT or MRI angiography and imaging.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (56, 'Radiotherapy', 'Radiation therapy or radiotherapy, often abbreviated RT, RTx, or XRT, is therapy using ionizing radiation, generally as part of cancer treatment to control or kill malignant cells and normally delivered by a linear accelerator. Radiation therapy may be curative in a number of types of cancer if they are localized to one area of the body. It may also be used as part of adjuvant therapy, to prevent tumor recurrence after surgery to remove a primary malignant tumor (for example, early stages of breast cancer). Radiation therapy is synergistic with chemotherapy, and has been used before, during, and after chemotherapy in susceptible cancers. The subspecialty of oncology concerned with radiotherapy is called radiation oncology.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (57, 'General surgery', 'General surgery is a surgical specialty that focuses on abdominal contents including esophagus, stomach, small bowel, colon, liver, pancreas, gallbladder and bile ducts, and often the thyroid gland (depending on local reference patterns). They also deal with diseases involving the skin, breast, soft tissue, trauma, peripheral vascular surgery and hernias.');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (58, 'Urology', 'Urology (from Greek οὖρον ouron \"urine\" and -λογία -logia \"study of\"), also known as genitourinary surgery, is the branch of medicine that focuses on surgical and medical diseases of the male and female urinary tract system and the male reproductive organs. The organs under the domain of urology include the kidneys, adrenal glands, ureters, urinary bladder, urethra, and the male reproductive organs (testes, epididymis, vas deferens, seminal vesicles, prostate, and penis).');
INSERT INTO `oasisdb`.`speciality` (`id`, `name`, `description`) VALUES (59, 'Vascular surgery', 'Vascular surgery is a surgical subspecialty in which diseases of the vascular system, or arteries, veins and lymphatic circulation, are managed by medical therapy, minimally-invasive catheter procedures, and surgical reconstruction. The specialty evolved from general and cardiac surgery as well as minimally invasive techniques pioneered by interventional radiology. The vascular surgeon is trained in the diagnosis and management of diseases affecting all parts of the vascular system except those of the heart and brain. Cardiothoracic surgeons and interventional cardiologists manage disease of vessels of the heart. Neurosurgeons and interventional neuroradiologists manage surgical disease of the vessels in the brain (e.g., intracranial aneurysms).');

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`doctor`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`doctor` (`employee_id`, `speciality_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`ward`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`ward` (`id`, `name`, `description`, `max_patient_count`, `current_patient_count`, `gender_acceptance`, `supervisor_id`) VALUES (1, 'General', '-', 100, 20, 'm', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`blood_group`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`blood_group` (`id`, `name`) VALUES (1, 'A+');
INSERT INTO `oasisdb`.`blood_group` (`id`, `name`) VALUES (2, 'B+');
INSERT INTO `oasisdb`.`blood_group` (`id`, `name`) VALUES (3, 'O+');
INSERT INTO `oasisdb`.`blood_group` (`id`, `name`) VALUES (4, 'AB+');
INSERT INTO `oasisdb`.`blood_group` (`id`, `name`) VALUES (5, 'A-');
INSERT INTO `oasisdb`.`blood_group` (`id`, `name`) VALUES (6, 'B-');
INSERT INTO `oasisdb`.`blood_group` (`id`, `name`) VALUES (7, 'O-');
INSERT INTO `oasisdb`.`blood_group` (`id`, `name`) VALUES (8, 'AB-');

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`ethnicity`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (1, 'Acholi');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (2, 'Akan');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (3, 'Albanians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (4, 'Afar');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (5, 'Afrikaners');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (6, 'Amhara');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (7, 'Arabs');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (8, 'Armenians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (9, 'Assamese');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (10, 'Assyrians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (11, 'Azerbaijanis');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (12, 'Balochis');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (13, 'Bamars');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (14, 'Bambara');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (15, 'Bashkirs');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (16, 'Basques');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (17, 'Belarusians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (18, 'Bemba');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (19, 'Bengalis');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (20, 'Berbers');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (21, 'Beti-Pahuin');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (22, 'Bosniaks');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (23, 'Brahui');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (24, 'British');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (25, 'Bulgarians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (26, 'Catalans');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (27, 'Chuvash');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (28, 'Circassians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (29, 'Chewa');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (30, 'Cornish');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (31, 'Croats');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (32, 'Czechs');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (33, 'Danes');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (34, 'Dinka');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (35, 'Dutch');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (36, 'English');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (37, 'Estonians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (38, 'Faroese');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (39, 'Finns');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (40, 'French');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (41, 'Frisians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (42, 'Fula');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (43, 'Ga-Adangbe');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (44, 'Gagauz');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (45, 'Galician');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (46, 'Ganda');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (47, 'Germans');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (48, 'Greeks');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (49, 'Georgians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (50, 'Gujarati');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (51, 'Han Chinese');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (52, 'Hausa');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (53, 'Hindustani');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (54, 'Hui');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (55, 'Hungarians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (56, 'Ibibio');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (57, 'Icelanders');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (58, 'Igbo');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (59, 'Ijaw');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (60, 'Irish');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (61, 'Italians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (62, 'Japanese');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (63, 'Javanese');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (64, 'Jews');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (65, 'Kannada');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (66, 'Kashmiris');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (67, 'Kazakhs');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (68, 'Kikuyu');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (69, 'Kongo');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (70, 'Konkani');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (71, 'Koreans');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (72, 'Kurds');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (73, 'Kyrgyz');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (74, 'Lango');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (75, 'Latvians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (76, 'Lithuanians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (77, 'Laz');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (78, 'Luba');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (79, 'Luo');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (80, 'Macedonians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (81, 'Malays');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (82, 'Malayali');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (83, 'Maltese');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (84, 'Manchu');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (85, 'Mandinka');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (86, 'Marathi');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (87, 'Moldovans');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (88, 'Mongo');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (89, 'Mongols');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (90, 'Naga');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (91, 'Nepali');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (92, 'Norwegians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (93, 'Nubians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (94, 'Nuer');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (95, 'Odia');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (96, 'Oromo');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (97, 'Parsi');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (98, 'Pashtuns');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (99, 'Persians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (100, 'Poles');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (101, 'Portuguese');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (102, 'Punjabi');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (103, 'Pedi');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (104, 'Romanians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (105, 'Romani');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (106, 'Russians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (107, 'Sara');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (108, 'Scottish');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (109, 'Serbs');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (110, 'Shona');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (111, 'Sindhis');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (112, 'Sinhalese');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (113, 'Slovaks');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (114, 'Slovenes');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (115, 'Soga');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (116, 'Somalis');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (117, 'Songhai');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (118, 'Soninke');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (119, 'Sotho');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (120, 'Spaniards');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (121, 'Sundanese');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (122, 'Sukuma');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (123, 'Swedes');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (124, 'Tajiks');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (125, 'Tamils');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (126, 'Telugu');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (127, 'Tais');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (128, 'Tibetans');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (129, 'Tswana');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (130, 'Tuaregs');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (131, 'Turks');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (132, 'Turkmens');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (133, 'Ukrainians');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (134, 'Uyghur');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (135, 'Uzbek');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (136, 'Vietnamese');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (137, 'Volga Tatars');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (138, 'Welsh');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (139, 'Xhosa');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (140, 'Yakuts');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (141, 'Yoruba');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (142, 'Zhuang');
INSERT INTO `oasisdb`.`ethnicity` (`id`, `name`) VALUES (143, 'Zulu');

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`patient`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (1, '547225851', 'Hayley', NULL, 'Praed', 'f', '1986-10-13', 1, 1, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (2, '426958651', 'Candie', NULL, 'Hamilton', 'f', '1964-07-21', 2, 5, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (3, '680561658', 'Rebecca', NULL, 'McClurg', 'f', '1962-11-07', 2, 6, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (4, '424456215', 'Tayla', NULL, 'Steiner', 'f', '1991-12-17', 2, 4, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (5, '232580484', 'Finn', NULL, 'Mullens', 'm', '1953-06-06', 3, 85, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (6, '519671392', 'Abigail', NULL, 'Collins', 'f', '1970-09-04', 1, 45, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (7, '565652075', 'Sophia', NULL, 'Allan', 'f', '1983-06-23', 5, 25, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (8, '136222026', 'Amber', NULL, 'Millington', 'f', '1948-08-24', 4, 36, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (9, '662075824', 'Roger', NULL, 'Sheets', 'm', '1975-01-11', 1, 54, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (10, '554062213', 'Ryan', NULL, 'Hose', 'm', '1988-04-11', 2, 75, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (11, '621888426', 'Ronald', NULL, 'Rice', 'm', '1974-08-18', 8, 12, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (12, '351187417', 'Louise', NULL, 'Machuca', 'f', '1974-07-17', 3, 84, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (13, '028382690', 'Loraine', NULL, 'Rosas', 'f', '1986-10-08', 5, 75, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (14, '536526796', 'Jai', NULL, 'Gallop', 'm', '1944-04-28', 2, 2, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (15, '315159226', 'James', NULL, 'Kaufman', 'm', '1947-04-17', 6, 56, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (16, '529210629', 'Tia', NULL, 'Peterson', 'f', '1970-10-09', 4, 82, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (17, '567195456', 'Tayla', NULL, 'Smythe', 'f', '1945-08-02', 1, 2, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (18, '686012735', 'Lucas', NULL, 'Selwyn', 'm', '1940-01-05', 3, 8, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (19, '608266046', 'Molly', NULL, 'Cracknell', 'f', '1938-05-04', 2, 9, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (20, '452270280', 'Ryder', NULL, 'De Loitte', 'm', '1980-08-12', 4, 75, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (21, '745865248', 'Charlene', NULL, 'Vieira', 'f', '1942-09-04', 8, 65, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (22, '609286966', 'Bailey', NULL, 'Balfe', 'm', '1970-05-27', 3, 14, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (23, '610480180', 'Hayden', NULL, 'Mansell', 'm', '1986-08-25', 7, 18, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (24, '642801067', 'William', NULL, 'Yuen', 'm', '1992-05-18', 1, 62, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (25, '698018496', 'Hayley', NULL, 'Scutt', 'f', '1979-07-30', 4, 58, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (26, '544571406', 'Donald', NULL, 'Bell', 'm', '1956-01-23', 7, 95, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (27, '190169878', 'George', NULL, 'Weeks', 'm', '1950-05-26', 3, 35, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (28, '680525952', 'Ella', NULL, 'Tom', 'f', '1935-02-13', 5, 100, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (29, '374297755', 'Jessica', NULL, 'Everette', 'f', '1948-07-06', 1, 15, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (30, '471808282', 'Michael', NULL, 'Torres', 'm', '1937-09-07', 1, 75, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (31, '642708559', 'Jesus', NULL, 'Burke', 'm', '1936-12-31', 4, 84, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (32, '014223196', 'Gabriel', NULL, 'Phocas', 'm', '1951-10-26', 2, 63, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (33, '297301230', 'Greg', NULL, 'Perrine', 'm', '1964-01-24', 6, 26, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (34, '480908798', 'Ava', NULL, 'Neild', 'f', '1944-08-18', 3, 26, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (35, '539805874', 'Stephanie', NULL, 'Hartley', 'f', '1989-04-09', 8, 84, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (36, '243129089', 'Beau', NULL, 'Freame', 'm', '1988-07-13', 6, 56, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (37, '766366354', 'Douglas', NULL, 'Koon', 'm', '1948-05-14', 1, 97, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (38, '126923499', 'Harold', NULL, 'Hansley', 'm', '1952-12-25', 2, 85, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (39, '314230119', 'Luke', NULL, 'Cecilia', 'm', '1944-08-15', 1, 82, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (40, '155240779', 'Evie', NULL, 'Maconochie', 'f', '1941-06-09', 3, 32, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (41, '564121362', 'Gemma', NULL, 'Warnes', 'f', '1996-09-22', 2, 45, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (42, '764600678', 'Justin', NULL, 'Cousin', 'm', '1950-09-18', 4, 12, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (43, '542331928', 'Savannah', NULL, 'Grills', 'f', '1974-11-13', 8, 100, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (44, '477543428', 'Molly', NULL, 'Creer', 'f', '1945-06-23', 4, 111, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (45, '293622109', 'Ann', NULL, 'Williams', 'f', '1986-01-05', 4, 45, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (46, '762013857', 'Bessie', NULL, 'Bowers', 'f', '1945-11-05', 5, 65, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (47, '563370494', 'Natalie', NULL, 'Alford', 'f', '1991-04-13', 2, 78, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (48, '672206468', 'Lilian', NULL, 'Leggo', 'f', '1961-09-08', 7, 79, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (49, '547401248', 'Sophie', NULL, 'Clarke', 'f', '1969-03-09', 3, 65, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (50, '587445679', 'Janine', NULL, 'Calhoun', 'f', '1970-11-02', 1, 41, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (51, '550433453', 'Tristan', NULL, 'Trenerry', 'm', '1943-12-24', 6, 42, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (52, '350585195', 'Liam', NULL, 'Pittard', 'm', '1935-05-13', 3, 43, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (53, '203283530', 'Mikayla', NULL, 'Simson', 'f', '1979-02-26', 4, 98, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (54, '315885054', 'Carlos', NULL, 'Ross', 'm', '1933-02-12', 8, 52, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (55, '628463171', 'Kathleen', NULL, 'Olivas', 'f', '1950-02-10', 1, 94, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (56, '114501806', 'Mohammad', NULL, 'Craddock', 'm', '1995-05-08', 3, 75, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (57, '025420461', 'Emilia', NULL, 'Keasler', 'f', '1937-04-02', 4, 61, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (58, '592823069', 'Hannah', NULL, 'Prescott', 'f', '1986-12-04', 1, 12, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (59, '758011595', 'Eden', NULL, 'Turley', 'f', '1950-08-08', 8, 35, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (60, '292648562', 'Michael', NULL, 'Medina', 'm', '1946-06-27', 2, 58, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (61, '125926075', 'Taj', NULL, 'Gowrie', 'm', '1954-09-19', 1, 65, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (62, '031325459', 'Felix', NULL, 'Thorne', 'm', '1968-01-08', 3, 71, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (63, '510645408', 'Jeremy', NULL, 'Hoff', 'm', '1983-02-25', 6, 23, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (64, '360227905', 'Hugo', NULL, 'Moss', 'm', '1992-05-15', 2, 45, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (65, '759013848', 'Molly', NULL, 'Fereday', 'f', '1950-05-21', 2, 32, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (66, '486466096', 'Amanda', NULL, 'Burrough', 'f', '1950-01-14', 3, 45, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (67, '129017475', 'Jake', NULL, 'Gooseberry', 'm', '1968-11-23', 4, 18, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (68, '029627088', 'Spencer', NULL, 'Gates', 'm', '1961-09-10', 1, 75, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (69, '495923554', 'Alan', NULL, 'Engel', 'm', '1972-01-24', 7, 65, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (70, '468987903', 'Lauren', NULL, 'Moloney', 'f', '1950-11-12', 5, 42, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (71, '311162625', 'Laura', NULL, 'Llewelyn', 'f', '1942-07-27', 2, 78, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (72, '647667592', 'Roxanne', NULL, 'Fox', 'f', '1955-12-28', 2, 76, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (73, '285469396', 'Cody', NULL, 'Searle', 'm', '1991-02-10', 8, 82, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (74, '540162314', 'Dennis', NULL, 'McEwan', 'm', '1992-10-13', 1, 32, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (75, '697017780', 'Piper', NULL, 'Moffat', 'f', '1951-06-26', 7, 65, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (76, '761011894', 'Sophia', NULL, 'Sulman', 'f', '1962-10-06', 3, 85, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (77, '060568194', 'Hamish', NULL, 'Wright', 'm', '1946-06-10', 2, 94, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (78, '376032754', 'Lily', NULL, 'Coghlan', 'f', '1947-02-27', 6, 35, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (79, '506663223', 'Nicholas', NULL, 'Michaels', 'm', '1994-04-08', 4, 120, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (80, '405404146', 'Michael', NULL, 'Roth', 'm', '1931-10-18', 1, 111, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (81, '508399007', 'Pat', NULL, 'Levy', 'm', '1963-04-18', 8, 25, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (82, '271766748', 'Gordon', NULL, 'Hall', 'm', '1975-06-01', 4, 112, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (83, '637804844', 'Owen', NULL, 'Sherlock', 'm', '1968-11-21', 2, 115, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (84, '015011334', 'Oscar', NULL, 'Street', 'm', '1967-03-13', 7, 28, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (85, '538383923', 'Daniel', NULL, 'Olmstead', 'm', '1988-05-11', 3, 45, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (86, '536907418', 'Matthew', NULL, 'Henry', 'm', '1990-10-19', 1, 38, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (87, '148663291', 'Aaron', NULL, 'Rohu', 'm', '1979-01-10', 5, 39, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (88, '403329762', 'Paul', NULL, 'Haynes', 'm', '1991-01-31', 6, 21, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (89, '419985466', 'Pamela', NULL, 'Jackson', 'f', '1991-01-23', 2, 45, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (90, '408490251', 'Jeremy', NULL, 'Goold', 'm', '1968-05-28', 4, 8, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (91, '309079547', 'Katie ', NULL, 'Burke', 'f', '1978-05-10', 3, 32, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (92, '407764903', 'Tahlia', NULL, 'Greig', 'f', '1974-01-09', 1, 9, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (93, '091011163 ', 'Georgia', NULL, 'Miner', 'f', '1986-10-12', 7, 21, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (94, '637425446', 'James', NULL, 'Gamboa', 'm', '1984-11-03', 3, 4, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (95, '441158816', 'Alex', NULL, 'Vanzetti', 'm', '1957-01-29', 2, 67, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (96, '539963411', 'Sheila', NULL, 'Jarvis', 'f', '1941-12-29', 4, 64, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (97, '017094657', 'Karen', NULL, 'Gill', 'f', '1945-05-17', 3, 29, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (98, '417175783', 'Samantha', NULL, 'Carr-Glyn', 'f', '1968-08-02', 1, 35, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (99, '553944680', 'Claudia', NULL, 'Ord', 'f', '1964-03-26', 2, 12, DEFAULT, NULL);
INSERT INTO `oasisdb`.`patient` (`id`, `nic`, `first_name`, `middle_name`, `last_name`, `gender`, `dob`, `blood_group_id`, `ethnicity_id`, `added_date`, `description`) VALUES (100, '174587423', 'Olivia', NULL, 'Bladin', 'f', '1932-01-11', 1, 96, DEFAULT, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`physician_designation`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`physician_designation` (`id`, `name`) VALUES (1, 'Family doctor');
INSERT INTO `oasisdb`.`physician_designation` (`id`, `name`) VALUES (2, 'Regional doctor');
INSERT INTO `oasisdb`.`physician_designation` (`id`, `name`) VALUES (3, 'Family physician');
INSERT INTO `oasisdb`.`physician_designation` (`id`, `name`) VALUES (4, 'Pharmacist');

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`physician`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`physician` (`id`, `first_name`, `middle_name`, `last_name`, `physician_designation_id`) VALUES (1, 'Gerda', NULL, 'Terrel', 2);
INSERT INTO `oasisdb`.`physician` (`id`, `first_name`, `middle_name`, `last_name`, `physician_designation_id`) VALUES (2, 'Elenor', NULL, 'Felkins', 3);
INSERT INTO `oasisdb`.`physician` (`id`, `first_name`, `middle_name`, `last_name`, `physician_designation_id`) VALUES (3, 'Venus', NULL, 'Panton', 1);
INSERT INTO `oasisdb`.`physician` (`id`, `first_name`, `middle_name`, `last_name`, `physician_designation_id`) VALUES (4, 'Gaylene', NULL, 'Wines', 4);
INSERT INTO `oasisdb`.`physician` (`id`, `first_name`, `middle_name`, `last_name`, `physician_designation_id`) VALUES (5, 'Elouse', NULL, 'Marcin', 2);
INSERT INTO `oasisdb`.`physician` (`id`, `first_name`, `middle_name`, `last_name`, `physician_designation_id`) VALUES (6, 'Leona ', NULL, 'Hamm', 3);
INSERT INTO `oasisdb`.`physician` (`id`, `first_name`, `middle_name`, `last_name`, `physician_designation_id`) VALUES (7, 'Ned', NULL, 'Coleman', 4);
INSERT INTO `oasisdb`.`physician` (`id`, `first_name`, `middle_name`, `last_name`, `physician_designation_id`) VALUES (8, 'Luana ', NULL, 'Gadison', 4);
INSERT INTO `oasisdb`.`physician` (`id`, `first_name`, `middle_name`, `last_name`, `physician_designation_id`) VALUES (9, 'Leisha', NULL, 'Ruse', 1);
INSERT INTO `oasisdb`.`physician` (`id`, `first_name`, `middle_name`, `last_name`, `physician_designation_id`) VALUES (10, 'Mary ', NULL, 'Mickernan', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`test`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (1, 'Antinuclear Antibody Test', 'A blood test that is used to detect autoimmune diseases. Typically, these unusual antibodies are directed against elements within the nucleus of cells.', 75000);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (2, 'CA 125', 'A blood sample or fluid from the chest or abdominal cavity is examined for CA 125, a protein that\'s a so-called tumor marker.', 23000);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (3, 'CAT Scan', 'A procedure that produces images of structures within the body created by a computer that takes data from multiple X-ray images and turns them in pictures on a screen.', 45600);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (4, 'Chemotherapy', 'This treatment—which can be given intravenously (through a vein), by mouth, through an injection (shot), or applied on the skin—destroys cancer cells.', 12000);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (5, 'Colonoscopy', 'Procedure using a viewing tube that enables an examiner (usually a gastroenterologist) to evaluate the appearance of the inside of the colon (large bowel).', 5800);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (6, 'Complete Blood Count', 'A calculation of the cellular (formed elements) of blood. Generally determined by specially designed machines that analyze the different components of blood in less than a minute.', 35500);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (7, 'Cortisone Injection', 'Used to treat localized inflammation (local injections) or widespread inflammation (systemic injections).', 28900);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (8, 'Creatinine Blood Test', 'This blood test can detect abnormally high levels of creatinine, which indicates kidney malfunction or failure.', 36000);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (9, 'Electrolytes', 'The balance of the electrolytes in our bodies is essential for normal function of our cells and our organs. Common electrolytes measured by blood tests include sodium, potassium, chloride, and bicarbonate.', 120000);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (10, 'Hemoglobin', 'Hemoglobin is the protein molecule in red blood cells that carries oxygen from the lungs to the body\'s tissues and returns carbon dioxide from the tissues to the lungs. Low hemoglobin levels may indicate anemia, while high levels may be caused by lung disease and certain tumors.', 20000);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (11, 'Hysterectomy', 'The uterus is removed during this common surgical procedure.', 16000);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (12, 'Lap Band Surgery (Gastric Banding)', 'This is a surgical weight loss procedure that involves the placement of an adjustable belt around the upper portion of the stomach.', 15000);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (13, 'Liver Blood Tests', 'Blood tests often used to detect and monitor liver damage that measures certain liver enzymes.', 8400);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (14, 'MRI Scan', 'An MRI (magnetic resonance imaging) scan is a radiology technique using magnetism, radio waves, and a computer to produce images of body structures.', 5000);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (15, 'Pap Smear', 'During this procedure, cells from a woman\'s cervix are collected and spread onto a microscope slide and then examined for pre-malignant or malignant changes.', 7800);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (16, 'Thyroid Blood Tests', 'Blood tests used to determine the adequacy of the levels of thyroid hormones.', 8550);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (17, 'Total Hip Replacement', 'In this surgical procedure, the diseased cartilage and bone of the hip joint is replaced with artificial material.', 19000);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (18, 'Total Knee Replacement', 'In this surgical procedure, the diseased knee joint is replaced with artificial material.', 25000);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (19, 'Tuberculosis Skin Test (PPD Skin Test)', 'This skin test determines if someone has developed an immune response to the bacterium that causes tuberculosis.', 40000);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (20, 'Ultrasound', 'A radiology technique using high- frequency sound waves to produce images of the organs and structures of the body.', 38000);
INSERT INTO `oasisdb`.`test` (`id`, `name`, `description`, `base_price`) VALUES (21, 'Coronary Artery Bypass Graft (CABG)', 'Surgically creates new routes around narrowed and blocked arteries, which allow sufficient blood flow to deliver oxygen and nutrients to the heart muscles.', 1800);

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`charge`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`charge` (`id`, `amount`, `description`, `charged_date`) VALUES (1, 3500, NULL, '2006-03-27');
INSERT INTO `oasisdb`.`charge` (`id`, `amount`, `description`, `charged_date`) VALUES (2, 6000, 'Initial charge.', '2007-05-04');
INSERT INTO `oasisdb`.`charge` (`id`, `amount`, `description`, `charged_date`) VALUES (3, 7800, 'AC room.', '2016-07-21');

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`admission`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`admission` (`id`, `patient_id`, `physician_id`, `admission_consultant_id`, `leading_consultant_id`, `cause`, `admission_date`, `release_date`, `charge_id`) VALUES (1, 1, 1, 1, 1, 'aaa', '2015-03-12', NULL, 1);
INSERT INTO `oasisdb`.`admission` (`id`, `patient_id`, `physician_id`, `admission_consultant_id`, `leading_consultant_id`, `cause`, `admission_date`, `release_date`, `charge_id`) VALUES (2, 1, 2, 1, 1, 'bbb', '1996-04-08', NULL, 2);
INSERT INTO `oasisdb`.`admission` (`id`, `patient_id`, `physician_id`, `admission_consultant_id`, `leading_consultant_id`, `cause`, `admission_date`, `release_date`, `charge_id`) VALUES (3, 1, 3, 1, 1, 'ccc', '2008-03-04', '1998-09-03', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`employee_telephone`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (1, 1, '0719084020');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (2, 2, '9157596255');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (3, 3, '5736121723');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (4, 4, '7242439888');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (5, 5, '4023753200');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (6, 6, '7176953205');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (7, 7, '5809658103');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (8, 8, '2563645731');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (9, 9, '7178276947');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (10, 10, '2173713329');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (11, 11, '2253956738');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (12, 12, '6184024150');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (13, 13, '7733547056');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (14, 14, '2168989239');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (15, 15, '6027239178');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (16, 16, '4129735183');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (17, 17, '5089773815');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (18, 18, '4253368055');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (19, 19, '6199564515');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (20, 20, '4235989780');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (21, 21, '4326839996');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (22, 22, '8044238005');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (23, 23, '9139823016');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (24, 24, '8019841009');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (25, 25, '3343856179');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (26, 26, '5162194679');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (27, 27, '8606607465');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (28, 28, '2546542442');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (29, 29, '8164646592');
INSERT INTO `oasisdb`.`employee_telephone` (`id`, `employee_id`, `telephone`) VALUES (30, 30, '4022795048');

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`employee_address`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (1, 1, 'Colombo road, Dambokka, Boyagane', 'Kurunegala', 'North Western', '60027');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (2, 2, '2068 Birch  Street', 'El Paso', NULL, '79924');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (3, 3, '2728 John Daniel Drive', 'Rolla', NULL, '65401');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (4, 4, '1749 Pine Street', 'Pittsburgh', NULL, '15212');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (5, 5, '238 Poling Farm Road', 'Wayne', NULL, '68787');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (6, 6, '3234 Aaron Smith Drive', 'Harrisburg', NULL, '17101');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (7, 7, '1609 Dovetail Estates', 'Cartwright', NULL, '74731');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (8, 8, '3877 Turnpike Drive', 'Gadsden', NULL, '35901');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (9, 9, '1021 Simpson Avenue', 'Harrisburg', NULL, '17102');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (10, 10, '517 Bolman Court', 'Jacksonville', NULL, '62650');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (11, 11, '2164 Washburn Street', 'Springfield', NULL, '70462');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (12, 12, '729 Eagle Street', 'Stlouis', NULL, '63101');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (13, 13, '1417 Oakmound Drive', 'Bridgeview', NULL, '60455');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (14, 14, '691 Sunny Glen Lane', 'Brook Park', NULL, '44142');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (15, 15, '1394 Dogwood Road', 'Phoenix', NULL, '85040');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (16, 16, '2480 Frank Avenue', 'Crafton', NULL, '15205');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (17, 17, '381 Lyon Avenue', 'Taunton', NULL, '2780');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (18, 18, '2401 Ryder Avenue', 'Seattle', NULL, '98109');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (19, 19, '176 Ocello Street', 'Santee', NULL, '92071');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (20, 20, '553 Raver Croft Drive', 'Chattanooga', NULL, '37403');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (21, 21, '2074 South Street', 'Midland', NULL, '79701');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (22, 22, '2486 Melody Lane', 'Richmond', NULL, '23227');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (23, 23, '842 Lake Forest Drive', 'Kansas City', NULL, '64106');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (24, 24, '452 Buck Drive', 'Salt Lake City', NULL, '84104');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (25, 25, '2256 Fleming Street', 'Arlington', NULL, '36722');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (26, 26, '4050 Heavner Court', 'Garden City', NULL, '11530');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (27, 27, '4574 Copperhead Road', 'Newington', NULL, '6111');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (28, 28, '3373 Clair Street', 'Waco', NULL, '76706');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (29, 29, '2018 Tree Frog Lane', 'Ferrelview', NULL, '64163');
INSERT INTO `oasisdb`.`employee_address` (`id`, `employee_id`, `street`, `town`, `province`, `postal_code`) VALUES (30, 30, '3290 Crummit Lane', 'Hardy', NULL, '68934');

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`employee_email`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (1, 1, 'oshan.ivantha@gmail.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (2, 2, 'HenricusErdem@gustr.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (3, 3, 'SamanthaWand@dayrep.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (4, 4, 'WilimarTunnelly@teleworm.us');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (5, 5, 'ZelihaTimayev@fleckens.hu');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (6, 6, 'MirandaBrandybuck@dayrep.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (7, 7, 'WalliyullahHudhaifahGanem@rhyta.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (8, 8, 'LisaKMills@gustr.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (9, 9, 'AquilesiaVergaraSedillo@armyspy.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (10, 10, 'MaximilianAbt@einrot.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (11, 11, 'KarinJaeger@jourrapide.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (12, 12, 'KristinaHedlund@jourrapide.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (13, 13, 'AbdeljalilBrandse@teleworm.us');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (14, 14, 'BriannaDolling@gustr.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (15, 15, 'RiiaSulin@einrot.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (16, 16, 'AntalNoel@superrito.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (17, 17, 'BiancaEk@teleworm.us');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (18, 18, 'JurekNowakowski@einrot.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (19, 19, 'TerenceKirillov@fleckens.hu');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (20, 20, 'LisaDAguinaldo@teleworm.us');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (21, 21, 'MarinaGruenewald@armyspy.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (22, 22, 'MochihiroTani@dayrep.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (23, 23, 'TymonWysocki@dayrep.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (24, 24, 'AntallMargo@gustr.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (25, 25, 'MohamadIsaksson@jourrapide.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (26, 26, 'LeilaOlsson@armyspy.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (27, 27, 'AsmaitAsmara@jourrapide.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (28, 28, 'BowiedeLange@jourrapide.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (29, 29, 'UzodimmaChibugo@armyspy.com');
INSERT INTO `oasisdb`.`employee_email` (`id`, `employee_id`, `email`) VALUES (30, 30, 'CarlosDiasCorreia@rhyta.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`degree`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`degree` (`id`, `name`, `acronym`) VALUES (1, 'Doctor of Medicine', 'MD');
INSERT INTO `oasisdb`.`degree` (`id`, `name`, `acronym`) VALUES (2, 'Bachelor of Medicine, Bachelor of Surgery', 'MBBS');
INSERT INTO `oasisdb`.`degree` (`id`, `name`, `acronym`) VALUES (3, 'Doctor of Osteopathic Medicine', 'DO');
INSERT INTO `oasisdb`.`degree` (`id`, `name`, `acronym`) VALUES (4, 'Doctorate in Medicine', 'DM');
INSERT INTO `oasisdb`.`degree` (`id`, `name`, `acronym`) VALUES (5, 'Doctor of Philosophy', 'DPhil');
INSERT INTO `oasisdb`.`degree` (`id`, `name`, `acronym`) VALUES (6, 'Master of Clinical Medicine', 'MCM');
INSERT INTO `oasisdb`.`degree` (`id`, `name`, `acronym`) VALUES (7, 'Master of Medical Science', 'MMSc');
INSERT INTO `oasisdb`.`degree` (`id`, `name`, `acronym`) VALUES (8, 'Master of Medicine', 'MM');
INSERT INTO `oasisdb`.`degree` (`id`, `name`, `acronym`) VALUES (9, 'Master of Surgery ', 'MS');
INSERT INTO `oasisdb`.`degree` (`id`, `name`, `acronym`) VALUES (10, 'Master of Science in Medicine or Surgery', 'MSc');
INSERT INTO `oasisdb`.`degree` (`id`, `name`, `acronym`) VALUES (11, 'Doctor of Clinical Medicine', 'DCM');
INSERT INTO `oasisdb`.`degree` (`id`, `name`, `acronym`) VALUES (12, 'Doctor of Clinical Surgery', 'DClinSurg');
INSERT INTO `oasisdb`.`degree` (`id`, `name`, `acronym`) VALUES (13, 'Doctor of Medical Science', 'DMSc');
INSERT INTO `oasisdb`.`degree` (`id`, `name`, `acronym`) VALUES (14, 'Doctor of Surgery', 'DS');

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`employee_has_degree`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`employee_has_degree` (`employee_id`, `degree_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`patient_address`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (1, 1, '142 Leisure Lane', 'San Luis Obispo', 'California', '93401');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (2, 2, '3461 O Conner Street', 'Biloxi', 'Mississippi', '39531');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (3, 3, '3374 Hall Street', 'Las Vegas', 'Nevada', '89119');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (4, 4, '554 Ferry Street', 'Florence', 'Alabama', '35630');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (5, 5, '3990 Hall Valley Drive', 'Clarksburg', 'West Virginia', '26301');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (6, 6, '3679 Science Center Drive', 'Spirit Lake', 'Idaho', '83869');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (7, 7, '2579 Rainbow Road', 'Santa Fe Springs', 'California', '90670');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (8, 8, '2686 Hudson Street', 'Newton', 'New Jersey', '07860');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (9, 9, '280 Hillside Drive', 'Abbeville', 'Louisiana', '70510');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (10, 10, '1617 Half and Half Drive', 'Fresno', 'California', '93721');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (11, 11, '130 Locust View Drive', 'San Francisco', 'California', '94143');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (12, 12, '4564 Little Acres Lane', 'Kansas', 'Illinois', '61933');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (13, 13, '3355 Leverton Cove Road', 'Agawam', 'Massachusetts', '01001');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (14, 14, '2043 Fort Street', 'Tacoma', 'Washington', '98402');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (15, 15, '181 Heliport Loop', 'Milan', 'Indiana', '47031');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (16, 16, '1847 Lang Avenue', 'Price', 'Utah', '84501');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (17, 17, '3870 Nickel Road', 'Riverside', 'California', '92507');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (18, 18, '1624 Clarence Court', 'Laurinburg', 'North Carolina', '28352');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (19, 19, '3899 Center Avenue', 'Fresno', 'California', '93710');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (20, 20, '1762 Patterson Street', 'Houston', 'Texas', '77063');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (21, 21, '1376 Thompson Street', 'Paramount', 'California', '90723');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (22, 22, '328 Thompson Street', 'Santa Fe Springs', 'California', '90670');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (23, 23, '3043 Lynn Ogden Lane', 'Beaumont', 'Texas', '77701');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (24, 24, '648 Ashford Drive', 'Dale City', 'Virginia', '22193');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (25, 25, '2880 Buena Vista Avenue', 'Salem', 'Oregon', '97301');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (26, 26, '2977 Simons Hollow Road', 'Wyalusing', 'Pennsylvania', '18853');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (27, 27, '265 Hickory Ridge Drive', 'Las Vegas', 'Nevada', '89030');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (28, 28, '3702 Charles Street', 'Southfield', 'Michigan', '48034');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (29, 29, '4854 Terra Cotta Street', 'Glyndon', 'Minnesota', '56547');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (30, 30, '2376 Brooke Street', 'Houston', 'Texas', '77022');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (31, 31, '828 Stadium Drive', 'Wareham', 'Massachusetts', '02571');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (32, 32, '4404 Peaceful Lane', 'Garfield Heights', 'Ohio', '44128');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (33, 33, '2200 Nutters Barn Lane', 'Urbandale', 'Iowa', '50322');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (34, 34, '3841 University Street', 'Seattle', 'Washington', '98101');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (35, 35, '2182 Joyce Street', 'Wilson', 'North Carolina', '27893');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (36, 36, '2121 Agriculture Lane', 'Perrine', 'Florida', '33157');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (37, 37, '2526 Geneva Street', 'New York', 'New York', '10019');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (38, 38, '3476 Parrill Court', 'South Bend', 'Indiana', '46625');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (39, 39, '884 Pooz Street', 'South River', 'New Jersey', '08882');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (40, 40, '4028 Grim Avenue', 'San Diego', 'California', '92073');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (41, 41, '4655 Griffin Street', 'Phoenix', 'Arizona', '85003');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (42, 42, '297 New Street', 'Coos Bay', 'Oregon', '97420');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (43, 43, '1983 Pritchard Court', 'Mankato', 'Minnesota', '56001');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (44, 44, '970 Old House Drive', 'Columbus', 'Ohio', '43215');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (45, 45, '4453 Glory Road', 'Dibrell', 'Tennessee', '37110');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (46, 46, '3032 Evergreen Lane', 'Los Angeles', 'California', '90036');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (47, 47, '212 Hanifan Lane', 'Duluth', 'Georgia', '30097');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (48, 48, '3322 Middleville Road', 'Los Angeles', 'California', '90017');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (49, 49, '712 Rafe Lane', 'Tupelo', 'Mississippi', '38801');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (50, 50, '4762 Parkway Street', 'Palm Springs', 'California', '92262');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (51, 51, '2324 Hog Camp Road', 'Chicago', 'Illinois', '60631');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (52, 52, '1733 Platinum Drive', 'Crafton', 'Pennsylvania', '15205');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (53, 53, '4107 Cessna Drive', 'Petroleum', 'Indiana', '46778');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (54, 54, '1222 Adamsville Road', 'Harlingen', 'Texas', '78550');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (55, 55, '3677 Bell Street', 'New York', 'New York', '10004');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (56, 56, '676 Long Street', 'Framingham', 'Massachusetts', '01702');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (57, 57, '188 Ethels Lane', 'Fort Myers', 'Florida', '33912');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (58, 58, '2149 Wilkinson Street', 'Nashville', 'Tennessee', '37216');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (59, 59, '4027 Pursglove Court', 'Dayton', 'Ohio', '45402');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (60, 60, '154 Caldwell Road', 'Rochester', 'New York', '14626');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (61, 61, '1119 Leverton Cove Road', 'Springfield', 'Massachusetts', '01103');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (62, 62, '2262 Dog Hill Lane', 'Lawrence', 'Kansas', '66044');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (63, 63, '4255 Hickman Street', 'Lemont', 'Illinois', '60439');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (64, 64, '4300 Glory Road', 'Nashville', 'Tennessee', '37210');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (65, 65, '4732 Fairmont Avenue', 'Atlanta', 'Missouri', '63530');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (66, 66, '892 Feathers Hooves Drive', 'New York', 'New York', '10004');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (67, 67, '4408 Stanton Hollow Road', 'Cambridge', 'Massachusetts', '02141');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (68, 68, '1620 Fairmont Avenue', 'Laredo', 'Missouri', '64652.');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (69, 69, '4604 Orchard Street', 'Golden Valley', 'Minnesota', '55427');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (70, 70, '2042 Windy Ridge Road', 'Preble', 'Indiana', '46782');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (71, 71, '273 Lamberts Branch Road', 'Salt Lake City', 'Utah', '84104');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (72, 72, '1363 Rivendell Drive', 'Barberton', 'Ohio', '44203');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (73, 73, '1794 Center Street', 'Monument', 'Oregon', '97864');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (74, 74, '3171 Murry Street', 'Portsmouth', 'Virginia', '23707');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (75, 75, '4109 Frum Street', 'Nashville', 'Tennessee', '37207');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (76, 76, '4454 Turkey Pen Road', 'New York', 'New York', '10016');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (77, 77, '938 Pinewood Avenue', 'Menominee', 'Michigan', '49858');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (78, 78, '502 Crummit Lane', 'Omaha', 'Nebraska', '68102');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (79, 79, '1508 Counts Lane', 'Winchester', 'Kentucky', '40391');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (80, 80, '1050 Kyle Street', 'Grand Island', 'Nebraska', '68801');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (81, 81, '195 Andell Road', 'Worthington', 'Ohio', '43085');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (82, 82, '2730 Ersel Street', 'Dallas', 'Texas', '75247');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (83, 83, '4687 Hillside Drive', 'Bedford', 'Massachusetts', '01730');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (84, 84, '3064 Owagner Lane', 'Seattle', 'Washington', '98106');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (85, 85, '4712 Conifer Drive', 'Bothell', 'Washington', '98021');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (86, 86, '2853 Valley Street', 'Woodbury', 'New Jersey', '08096');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (87, 87, '4001 Shady Pines Drive', 'Madisonville', 'Kentucky', '42431');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (88, 88, '3985 Lonely Oak Drive', 'Spanish Fort', 'Alabama', '36527');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (89, 89, '1080 Hershell Hollow Road', 'Johnson City', 'Tennessee', '37601');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (90, 90, '799 Raintree Boulevard', 'Indianapolis', 'Indiana', '46202');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (91, 91, '3943 Earnhardt Drive', 'Louisville', 'Kentucky', '40242');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (92, 92, '3417 Farnum Road', 'New York', 'New York', '10016');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (93, 93, '3949 Short Street', 'Austin', 'Texas', '78701');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (94, 94, '2383 Luke Lane', 'Elk City', 'Oklahoma', '73644');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (95, 95, '810 Elliot Avenue', 'Seattle', 'Washington', '98109');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (96, 96, '3939 Tenmile Road', 'Cambridge', 'Massachusetts', '02142');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (97, 97, '3183 Joyce Street', 'Belle Fontaine', 'Alabama', '36607');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (98, 98, '1753 Sumner Street', 'Los Angeles', 'California', '90017');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (99, 99, '180 Platinum Drive', 'Parker', 'Pennsylvania', '16049');
INSERT INTO `oasisdb`.`patient_address` (`id`, `patient_id`, `street`, `town`, `province`, `postal_code`) VALUES (100, 100, '1516 Burton Avenue', 'Memphis', 'Tennessee', '38116');

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`patient_telephone`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (1, 1, '8055420854');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (2, 2, '2285968265');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (3, 3, '7025234139');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (4, 4, '2567607570');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (5, 5, '3046695932');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (6, 6, '2086232712');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (7, 7, '6262305323');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (8, 8, '9739406275');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (9, 9, '3378930446');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (10, 10, '5599400081');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (11, 11, '4159029819');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (12, 12, '2179487164');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (13, 13, '4133357230');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (14, 14, '2532075428');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (15, 15, '8126543097');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (16, 16, '4358201203');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (17, 17, '6266296145');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (18, 18, '9102179535');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (19, 19, '5593612075');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (20, 20, '7132798141');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (21, 21, '5626011255');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (22, 22, '5626885285');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (23, 23, '4097815044');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (24, 24, '7036808068');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (25, 25, '5417496194');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (26, 26, '5707465527');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (27, 27, '7026337499');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (28, 28, '7343896553');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (29, 29, '2184986042');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (30, 30, '7136914938');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (31, 31, '5082950453');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (32, 32, '2164688263');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (33, 33, '5155540726');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (34, 34, '2064056106');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (35, 35, '2522066255');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (36, 36, '3052327580');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (37, 37, '9174725754');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (38, 38, '2195077101');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (39, 39, '7322543960');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (40, 40, '6196613560');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (41, 41, '6022812179');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (42, 42, '5412946566');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (43, 43, '5073828027');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (44, 44, '7409560261');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (45, 45, '9319342542');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (46, 46, '3236340675');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (47, 47, '6786038135');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (48, 48, '6263735745');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (49, 49, '6626915040');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (50, 50, '7602725343');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (51, 51, '7084155658');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (52, 52, '7247998980');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (53, 53, '2603468595');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (54, 54, '9566378480');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (55, 55, '2122844767');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (56, 56, '3515556740');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (57, 57, '8638408344');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (58, 58, '6153794885');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (59, 59, '9373155817');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (60, 60, '5852276831');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (61, 61, '4133416470');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (62, 62, '7856901647');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (63, 63, '6304274059');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (64, 64, '9319528669');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (65, 65, '6602391014');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (66, 66, '6317151846');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (67, 67, '7815557759');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (68, 68, '6602868223');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (69, 69, '9525910784');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (70, 70, '2605471041');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (71, 71, '8012084246');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (72, 72, '3308612513');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (73, 73, '5419341690');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (74, 74, '7575088110');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (75, 75, '6152687528');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (76, 76, '9172715974');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (77, 77, '9068644542');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (78, 78, '4022205631');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (79, 79, '8598933166');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (80, 80, '3085897280');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (81, 81, '6149949179');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (82, 82, '2143369260');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (83, 83, '3395262255');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (84, 84, '2062956216');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (85, 85, '4254891412');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (86, 86, '8568452153');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (87, 87, '2709186803');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (88, 88, '2516251685');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (89, 89, '4239285422');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (90, 90, '7652376393');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (91, 91, '5026213723');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (92, 92, '2123387227');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (93, 93, '5128715315');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (94, 94, '5802438359');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (95, 95, '2065608625');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (96, 96, '7817532393');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (97, 97, '2519738684');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (98, 98, '3105791988');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (99, 99, '7248183026');
INSERT INTO `oasisdb`.`patient_telephone` (`id`, `patient_id`, `telephone`) VALUES (100, 100, '9015960879');

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`patient_email`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (1, 1, 'HayleyPraed@gustr.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (2, 2, 'CandieSHamilton@superrito.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (3, 3, 'RebeccaJMcClurg@armyspy.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (4, 4, 'TaylaSteiner@superrito.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (5, 5, 'FinnMullens@einrot.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (6, 6, 'AbigailCollins@dayrep.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (7, 7, 'SophiaAllan@cuvox.de');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (8, 8, 'AmberMillington@cuvox.de');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (9, 9, 'RogerKSheets@teleworm.us');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (10, 10, 'RyanHose@jourrapide.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (11, 11, 'RonaldCRice@rhyta.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (12, 12, 'LouiseEMachuca@einrot.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (13, 13, 'LoraineLRosas@einrot.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (14, 14, 'JaiGallop@fleckens.hu');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (15, 15, 'JamesMKaufman@superrito.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (16, 16, 'TiaWPeterson@teleworm.us');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (17, 17, 'TaylaSmythe@einrot.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (18, 18, 'LucasSelwyn@cuvox.de');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (19, 19, 'MollyCracknell@armyspy.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (20, 20, 'RyderDeLoitte@superrito.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (21, 21, 'CharleneBVieira@rhyta.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (22, 22, 'BaileyBalfe@gustr.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (23, 23, 'HaydenMansell@fleckens.hu');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (24, 24, 'WilliamYuen@armyspy.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (25, 25, 'HayleyScutt@teleworm.us');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (26, 26, 'DonaldSBell@jourrapide.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (27, 27, 'GeorgeWeeks@superrito.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (28, 28, 'EllaDTom@teleworm.us');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (29, 29, 'JessicaKEverette@jourrapide.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (30, 30, 'MichaelSTorres@teleworm.us');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (31, 31, 'JesusSBurke@dayrep.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (32, 32, 'GabrielPhocas@dayrep.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (33, 33, 'GregDPerrine@jourrapide.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (34, 34, 'AvaNeild@gustr.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (35, 35, 'StephanieHartley@einrot.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (36, 36, 'BeauFreame@gustr.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (37, 37, 'DouglasBKoon@rhyta.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (38, 38, 'HaroldSHansley@armyspy.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (39, 39, 'LukeCecilia@cuvox.de');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (40, 40, 'EvieMaconochie@rhyta.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (41, 41, 'GemmaWarnes@superrito.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (42, 42, 'JustinCousin@superrito.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (43, 43, 'SavannahGrills@superrito.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (44, 44, 'MollyCreer@gustr.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (45, 45, 'AnnJWilliams@jourrapide.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (46, 46, 'BessieDBowers@cuvox.de');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (47, 47, 'NatalieJAlford@rhyta.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (48, 48, 'LilianLeggo@gustr.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (49, 49, 'SophieClarke@cuvox.de');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (50, 50, 'JanineECalhoun@superrito.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (51, 51, 'TristanTrenerry@rhyta.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (52, 52, 'LiamPittard@gustr.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (53, 53, 'MikaylaSimson@jourrapide.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (54, 54, 'CarlosDRoss@dayrep.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (55, 55, 'KathleenBOlivas@gustr.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (56, 56, 'MohammadMCraddock@superrito.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (57, 57, 'EmiliaWKeasler@gustr.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (58, 58, 'HannahPrescott@dayrep.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (59, 59, 'EdenTurley@rhyta.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (60, 60, 'MichaelMMedina@rhyta.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (61, 61, 'TajGowrie@teleworm.us');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (62, 62, 'FelixWThorne@einrot.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (63, 63, 'JeremyHoff@rhyta.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (64, 64, 'HugoMoss@fleckens.hu');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (65, 65, 'MollyFereday@armyspy.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (66, 66, 'AmandaTBurrough@dayrep.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (67, 67, 'JakeGooseberry@einrot.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (68, 68, 'SpencerGates@superrito.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (69, 69, 'AlanHEngel@rhyta.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (70, 70, 'LaurenMoloney@superrito.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (71, 71, 'LauraLlewelyn@jourrapide.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (72, 72, 'RoxanneRFox@fleckens.hu');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (73, 73, 'CodySearle@rhyta.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (74, 74, 'DennisLMcEwan@gustr.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (75, 75, 'PiperMoffat@superrito.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (76, 76, 'SophiaSulman@cuvox.de');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (77, 77, 'HamishWright@teleworm.us');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (78, 78, 'LilyCoghlan@fleckens.hu');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (79, 79, 'NicholasKMichaels@armyspy.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (80, 80, 'MichaelJRoth@teleworm.us');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (81, 81, 'PatDLevy@armyspy.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (82, 82, 'GordonMHall@superrito.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (83, 83, 'OwenSherlock@superrito.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (84, 84, 'OscarStreet@einrot.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (85, 85, 'DanielVOlmstead@jourrapide.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (86, 86, 'MatthewJHenry@rhyta.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (87, 87, 'AaronRohu@armyspy.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (88, 88, 'PaulMHaynes@cuvox.de');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (89, 89, 'PamelaHJackson@armyspy.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (90, 90, 'JeremyGoold@fleckens.hu');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (91, 91, 'KatieBurke@superrito.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (92, 92, 'TahliaGreig@gustr.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (93, 93, 'GeorgiaFMiner@gustr.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (94, 94, 'JamesJGamboa@fleckens.hu');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (95, 95, 'AlexVanzetti@rhyta.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (96, 96, 'SheilaMJarvis@cuvox.de');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (97, 97, 'KarenRGill@superrito.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (98, 98, 'SamanthaCarr-Glyn@teleworm.us');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (99, 99, 'ClaudiaOrd@einrot.com');
INSERT INTO `oasisdb`.`patient_email` (`id`, `patient_id`, `email`) VALUES (100, 100, 'OliviaBladin@einrot.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`physician_telephone`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`physician_telephone` (`id`, `physician_id`, `telephone`) VALUES (1, 1, '0370037706');
INSERT INTO `oasisdb`.`physician_telephone` (`id`, `physician_id`, `telephone`) VALUES (2, 2, '0712761631');
INSERT INTO `oasisdb`.`physician_telephone` (`id`, `physician_id`, `telephone`) VALUES (3, 3, '0714877765');
INSERT INTO `oasisdb`.`physician_telephone` (`id`, `physician_id`, `telephone`) VALUES (4, 4, '0112256984');
INSERT INTO `oasisdb`.`physician_telephone` (`id`, `physician_id`, `telephone`) VALUES (5, 5, '0372245698');
INSERT INTO `oasisdb`.`physician_telephone` (`id`, `physician_id`, `telephone`) VALUES (6, 6, '0774585412');
INSERT INTO `oasisdb`.`physician_telephone` (`id`, `physician_id`, `telephone`) VALUES (7, 7, '0716859552');
INSERT INTO `oasisdb`.`physician_telephone` (`id`, `physician_id`, `telephone`) VALUES (8, 8, '0714578426');
INSERT INTO `oasisdb`.`physician_telephone` (`id`, `physician_id`, `telephone`) VALUES (9, 9, '0372258479');
INSERT INTO `oasisdb`.`physician_telephone` (`id`, `physician_id`, `telephone`) VALUES (10, 10, '0773569847');

COMMIT;


-- -----------------------------------------------------
-- Data for table `oasisdb`.`employee_login`
-- -----------------------------------------------------
START TRANSACTION;
USE `oasisdb`;
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (1, 'ivantha', 'cat');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (2, 'Wompe1933', 'ieguti8Ooc');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (3, 'Sucipt', 'UM1soo4sai');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (4, 'Mighat', 'Eifah9ee');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (5, 'Varmethe', 'lah0Foobae');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (6, 'Seakelver', 'ohpoh3Ojae');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (7, 'Thowenty39', 'Ohy5ohth0');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (8, 'Ingthe66', 'ohB4ais9oit');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (9, 'Agavered85', 'Aim3Ool1aov');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (10, 'Baboure', 'roRietah2u');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (11, 'Sagems', 'huoCahk4');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (12, 'Strust63', 'thu8Iochoo');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (13, 'Mathesembed', 'eshiho8Jai');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (14, 'Laidence', 'Aomie7ohngoo');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (15, 'Liess1965', 'Aethae2eec');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (16, 'Ittries', 'Ieha5uN7Qu');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (17, 'Thoself', 'Naix8euchee');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (18, 'Undfuld', 'DahN8ooh');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (19, 'Taides1937', 'uehooh3Epo');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (20, 'Dunneve', 'uweiTeiyoh0oh');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (21, 'Propediet', 'kichuphieB3');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (22, 'Sellot', 'aaWooyoh9fo');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (23, 'Aress1934', 'oong7Igh1');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (24, 'Abbeact', 'bahQu1aivoh');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (25, 'Sarehely1991', 'xiNgie5ligh');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (26, 'Gess1980', 're3Xohvah');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (27, 'Ashery', 'soe1Of9w');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (28, 'Wonsize', 'Naithaphar9');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (29, 'Surns1955', 'Ahdegai8goo');
INSERT INTO `oasisdb`.`employee_login` (`employee_id`, `username`, `password`) VALUES (30, 'Prons1933', 'Ga7oxah7ex');

COMMIT;

