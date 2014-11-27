-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 26, 2012 at 07:04 AM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `talentdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `name` varchar(30) NOT NULL,
  `address` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `age` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`name`, `address`, `password`, `age`, `email`, `userId`) VALUES
('John Smith', 'US', 'john', 33, 'john@email.com', 1),
('Harry Potter', 'US', 'harry', 23, 'harry@email.com', 3),
('Asterix', 'Rome', 'astrix', 35, 'asterix@email.com', 4),
('Mathew', 'US', 'mathew', 24, 'mathew@gmail.com', 6);
