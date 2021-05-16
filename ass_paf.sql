-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2021 at 01:54 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ass_paf`
--

-- --------------------------------------------------------

--
-- Table structure for table `fund`
--

CREATE TABLE `fund` (
  `Funder_ID` int(11) NOT NULL,
  `FundingOrgName` varchar(100) NOT NULL,
  `InterestingProjArea` varchar(50) NOT NULL,
  `FundingAmount` double(12,2) NOT NULL,
  `Email` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fund`
--

INSERT INTO `fund` (`Funder_ID`, `FundingOrgName`, `InterestingProjArea`, `FundingAmount`, `Email`) VALUES
(1, 'ABC(Pvt) LTd.', 'IOT', 80000.00, 'abc@gmail.com'),
(2, 'DEF(Pvt)Ltd.', 'ML,AI', 50000.00, 'def@gmail.com'),
(4, 'update(Pvt) Ltd', 'updated', 5000.00, 'update@gmailcom'),
(5, 'lmn@gmail.com', 'IOT', 40000.00, 'lmn@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `Payment_ID` int(11) NOT NULL,
  `Transaction_Code` varchar(50) NOT NULL,
  `Depositor` varchar(50) NOT NULL,
  `Account_No` int(20) NOT NULL,
  `Bank` varchar(50) NOT NULL,
  `Amount` double(12,2) NOT NULL,
  `CVV` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`Payment_ID`, `Transaction_Code`, `Depositor`, `Account_No`, `Bank`, `Amount`, `CVV`) VALUES
(1, 'T001', 'Chanaka', 121344, 'HNB', 15000.00, 578),
(2, 'T002', 'Arun', 897564, 'BOC', 20000.00, 852),
(3, 'T004', 'Dhasun', 7797446, 'Commercial', 89855.00, 365),
(5, 'T005', 'Unknown', 2455546, 'HSBC', 548656.00, 478);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `Product_ID` int(11) NOT NULL,
  `Product_Code` varchar(50) NOT NULL,
  `Product_Name` varchar(50) NOT NULL,
  `Category` varchar(50) NOT NULL,
  `collaborators` varchar(100) NOT NULL,
  `Price` double(12,2) NOT NULL,
  `Email` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`Product_ID`, `Product_Code`, `Product_Name`, `Category`, `collaborators`, `Price`, `Email`) VALUES
(1, 'P001', 'Electric Car', 'ML, IOT', 'Vivek, Chandru', 32000.00, 'vivek@gmail.com'),
(2, 'P002', 'Smart Wifi', 'AI', 'Suresh, Pradeep', 84000.00, 'pradeep@gmail.com'),
(4, 'PR006', 'updated', 'updated', 'ashen', 100000.00, 'update@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_ID` int(11) NOT NULL,
  `user_Name` varchar(30) NOT NULL,
  `User_Type` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_ID`, `user_Name`, `User_Type`, `Email`, `Password`) VALUES
(1, 'Kumar', 'Researcher', 'kumar@gmail.com', '123456'),
(4, 'update', 'Admin', 'updated@gmail.com', 'update');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fund`
--
ALTER TABLE `fund`
  ADD PRIMARY KEY (`Funder_ID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`Payment_ID`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`Product_ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fund`
--
ALTER TABLE `fund`
  MODIFY `Funder_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `Payment_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `Product_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
