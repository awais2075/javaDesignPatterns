-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2019 at 06:24 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.1.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `employee_management_system`
--
CREATE DATABASE IF NOT EXISTS `employee_management_system` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `employee_management_system`;

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `attendance_id` int(11) NOT NULL,
  `attendance_date` date NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`attendance_id`, `attendance_date`, `user_id`) VALUES
(3, '2019-12-09', 1);

-- --------------------------------------------------------

--
-- Table structure for table `leave_table`
--

CREATE TABLE `leave_table` (
  `leave_id` int(11) NOT NULL,
  `leave_date` date NOT NULL,
  `reason` varchar(128) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` enum('pending','approved') DEFAULT 'pending'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `leave_table`
--

INSERT INTO `leave_table` (`leave_id`, `leave_date`, `reason`, `user_id`, `status`) VALUES
(1, '2019-12-01', 'fsfasdfas', 1, 'approved'),
(2, '2019-12-01', 'fsfasdfas', 13, 'pending'),
(3, '2019-12-01', 'fsfasdfas', 14, 'approved'),
(4, '2019-12-01', 'fsfasdfas', 15, 'approved'),
(5, '2019-12-01', 'fsfasdfas', 2, 'pending'),
(6, '2019-12-01', 'fsfasdfas', 3, 'approved'),
(7, '2019-09-24', 'leave leave leave', 1, 'approved'),
(8, '2019-09-18', 'Sickness', 1, 'approved'),
(9, '2019-09-24', 'fasdfafsa', 1, 'pending'),
(10, '2019-09-24', 'asdfasfsd', 1, 'pending'),
(11, '2019-09-24', 'afasf', 1, 'pending'),
(12, '2019-09-24', 'safasf', 1, 'pending'),
(13, '2019-09-24', 'asfsaf', 1, 'pending'),
(14, '2019-09-24', 'sadfasfd', 1, 'pending'),
(15, '2019-09-24', 'afsdaf', 1, 'pending'),
(16, '2019-09-24', '2432423424', 1, 'pending');

-- --------------------------------------------------------

--
-- Table structure for table `meeting`
--

CREATE TABLE `meeting` (
  `meeting_id` int(11) NOT NULL,
  `meeting_title` varchar(50) NOT NULL,
  `meeting_date` date NOT NULL,
  `meeting_agenda` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meeting`
--

INSERT INTO `meeting` (`meeting_id`, `meeting_title`, `meeting_date`, `meeting_agenda`) VALUES
(4, 'Meeting Title', '2019-12-02', 'Meeting Agenda'),
(5, 'safsf', '1990-09-24', 'Meeting Agenda:  '),
(6, '1234', '1990-09-24', 'Meeting Agenda:  '),
(7, 'new title', '2006-01-10', 'afdafasfasdfsafs'),
(8, 'New Meeting', '1990-09-25', 'afsafsfsfsafsdfsdfsdfdsfdsfsdf'),
(9, 'Meeting Title', '2019-12-23', 'Meeting Agenda'),
(10, 'Meeting Title', '2019-12-03', 'Meeting Agenda'),
(11, 'Meeting Title', '2019-12-23', 'Meeting Agenda'),
(12, 'Meeting Title', '2019-12-03', 'Meeting Agenda'),
(13, 'New Meeting Title', '2019-09-27', 'fsadfsafsdfdsfsdf'),
(14, 'safasfdasffgfgfgfgfgf', '2019-09-24', 'afasfsadfsdf'),
(15, 'afsadf', '2019-09-24', 'afasfd'),
(16, 'testing', '2019-09-24', 'afdafasf'),
(17, 'asfasf23432', '2019-09-24', 'sdfsd23423');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `first_name` varchar(40) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `status` enum('pending','registered','unregistered','admin') DEFAULT 'pending'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `first_name`, `last_name`, `email`, `password`, `status`) VALUES
(1, 'awais2075', 'awais', 'rashid', 'awais2075@gmail.com', 'abcd1234', 'registered'),
(2, 'anas7981', 'anas', 'rashid', 'anas7981@gmail.com', 'abcd1234', 'registered'),
(3, 'ubaid999', 'ubaid', 'rashid', 'ubaid999@gmail.com', 'abcd1234', 'registered'),
(4, 'admin', 'admiin', 'admin', 'admin@mail.com', 'abcd1234', 'admin'),
(8, 'awais2075', 'awais', 'rashid', 'awais2076@gmail.com', 'abcd1234', 'registered'),
(11, 'david123', 'david', 'malan', 'david@gmail.com', 'abcd1234', 'registered'),
(12, 'taylor123', 'taylor', 'swift', 'taylor123@gmail.com', 'abcd1234', 'registered'),
(13, 'shawn123', 'shawn', 'mendes', 'shawn123@gmail.com', 'abcd1234', 'unregistered'),
(14, 'test123', 'test', 'mail', 'test@mail.com', 'abcd1234', 'registered'),
(15, 'kelly', 'kelly', 'brook', 'kelly123@mail.com', 'abcd1234', 'pending');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`attendance_id`),
  ADD UNIQUE KEY `attendance_date` (`attendance_date`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `leave_table`
--
ALTER TABLE `leave_table`
  ADD PRIMARY KEY (`leave_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `meeting`
--
ALTER TABLE `meeting`
  ADD PRIMARY KEY (`meeting_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attendance`
--
ALTER TABLE `attendance`
  MODIFY `attendance_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `leave_table`
--
ALTER TABLE `leave_table`
  MODIFY `leave_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `meeting`
--
ALTER TABLE `meeting`
  MODIFY `meeting_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `leave_table`
--
ALTER TABLE `leave_table`
  ADD CONSTRAINT `leave_table_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
