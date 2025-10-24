-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Sep 26, 2025 at 12:19 PM
-- Server version: 8.0.43-0ubuntu0.24.04.1
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `SivajiAndSons`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_files`
--

CREATE TABLE `admin_files` (
  `id` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `filename` varchar(255) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `airlines`
--

CREATE TABLE `airlines` (
  `id` int NOT NULL,
  `airline` varchar(255) DEFAULT NULL,
  `arrivalairport` varchar(255) DEFAULT NULL,
  `arrivaltime` datetime(6) DEFAULT NULL,
  `baggageallowance` varchar(255) DEFAULT NULL,
  `departureairport` varchar(255) DEFAULT NULL,
  `departuretime` datetime(6) DEFAULT NULL,
  `flightnumber` varchar(255) DEFAULT NULL,
  `terminal` varchar(255) DEFAULT NULL,
  `ticketnumber` varchar(255) DEFAULT NULL,
  `travelclass` varchar(255) DEFAULT NULL,
  `input_data_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `airlines`
--

INSERT INTO `airlines` (`id`, `airline`, `arrivalairport`, `arrivaltime`, `baggageallowance`, `departureairport`, `departuretime`, `flightnumber`, `terminal`, `ticketnumber`, `travelclass`, `input_data_id`) VALUES
(1, 'Scoot (TR)', 'Singapore (SIN)', '2025-04-28 08:30:00.000000', '0', 'Tiruchirappalli (TRZ)', '2025-04-28 01:25:00.000000', 'TR 17', NULL, 'Y6PK5V', 'Economy', 1),
(2, 'Indigo (6E)', 'Tiruchirappalli (TRZ)', '2025-04-29 04:20:00.000000', '30 KG ', 'Singapore (SIN)', '2025-04-29 02:45:00.000000', '6E 1008', NULL, 'VBGY5R', 'Economy', 1),
(3, 'Etihad Airways (EY)', 'Abu Dhabi (AUH)', '2025-05-22 07:00:00.000000', '2 PC ', 'Kochi (COK)', '2025-05-22 04:20:00.000000', 'EY 337', NULL, '6072863601305', 'Business', 2),
(4, 'Etihad Airways (EY)', 'New York (JFK)', '2025-05-22 16:05:00.000000', '2 PC', 'Abu Dhabi (AUH)', '2025-05-22 10:00:00.000000', 'EY 3', NULL, '6072863601305', 'Business', 2),
(5, 'Etihad Airways (EY)', 'Abu Dhabi (AUH)', '2025-05-30 11:20:00.000000', '2 PC', 'New York (JFK)', '2025-05-29 14:30:00.000000', 'EY 2', NULL, '6072863601305', 'Economy', 2),
(6, 'Etihad Airways (EY)', 'Kochi (COK)', '2025-05-30 20:00:00.000000', '2 PC', 'Abu Dhabi (AUH)', '2025-05-30 14:25:00.000000', 'EY 334', NULL, '6072863601305', 'Economy', 2),
(7, 'Etihad Airways (EY)', 'Abu Dhabi (AUH)', '2025-05-22 07:00:00.000000', '2 PC', 'Kochi (COK)', '2025-05-22 04:20:00.000000', 'EY 337', NULL, '6072863601306', 'Business', 3),
(8, 'Etihad Airways (EY)', 'New York (JFK)', '2025-05-22 16:05:00.000000', '2 PC', 'Abu Dhabi (AUH)', '2025-05-22 10:00:00.000000', 'EY 3', NULL, '6072863601306', 'Business', 3),
(9, 'Etihad Airways (EY)', 'Abu Dhabi (AUH)', '2025-10-25 19:30:00.000000', '2 PC', 'New York (JFK)', '2025-10-24 22:40:00.000000', 'EY 4', NULL, '6072863601306', 'Economy', 3),
(10, 'Etihad Airways (EY)', 'Kochi (COK)', '2025-10-26 02:35:00.000000', '2 PC', 'Abu Dhabi (AUH)', '2025-10-25 21:00:00.000000', 'EY 336', NULL, '6072863601306', 'Economy', 3),
(11, 'Indigo (6E)', 'Singapore (SIN)', '2025-05-30 18:35:00.000000', '15Kg', 'Chennai (MAA)', '2025-05-16 15:30:00.000000', 'EY 337', NULL, '6072863601306', 'Economy', 4),
(12, 'Etihad Airways (EY)', 'Abu Dhabi (AUH)', '2025-05-09 18:35:00.000000', '15', 'Chennai (MAA)', '2025-05-09 15:30:00.000000', 'EY 337', NULL, '9655150814', 'Economy', 5),
(13, 'Etihad Airways (EY)', 'Chennai (MAA)', '2025-05-10 18:35:00.000000', '15', 'Abu Dhabi (AUH)', '2025-05-10 15:30:00.000000', 'EY 349', NULL, '9655150814', 'Economy', 5),
(14, 'Malaysia Airlines (MH)', 'Bangkok (BKK)', '2025-08-13 23:45:00.000000', '20 KG', 'Chennai (MAA)', '2025-08-13 11:30:00.000000', 'MH 183', NULL, '9655150814', 'Economy', 6),
(15, 'Malaysia Airlines (MH)', 'Chennai (MAA)', '2025-08-18 11:30:00.000000', '20 KG', 'Kuala Lumpur (KUL)', '2025-08-18 09:30:00.000000', 'MH 181', NULL, '9655150814', 'Economy', 6),
(16, 'Emirates (EK)', 'Dubai (DXB)', '2025-05-14 23:25:00.000000', '25 KG', 'Bengaluru (BLR)', '2025-05-14 21:00:00.000000', 'EK 567', NULL, '1765605863602', 'Economy', 7),
(17, 'Emirates (EK)', 'Madrid (MAD)', '2025-05-15 13:30:00.000000', '25 KG', 'Dubai (DXB)', '2025-05-15 07:40:00.000000', 'EK 141', NULL, '1765605863602', 'Economy', 7),
(18, 'Emirates (EK)', 'Dubai (DXB)', '2025-05-19 00:35:00.000000', '25 KG', 'Madrid (MAD)', '2025-05-18 15:25:00.000000', 'EK 142', NULL, '1765605863602', 'Economy', 7),
(19, 'Emirates (EK)', 'Chennai (MAA)', '2025-05-19 08:05:00.000000', '25 KG', 'Dubai (DXB)', '2025-05-19 02:35:00.000000', 'EK 544 ', NULL, '1765605863602', 'Economy', 7),
(21, 'Indigo (6E)', 'Singapore (SIN)', '2025-08-02 20:30:00.000000', '20', 'Chennai (MAA)', '2025-08-02 11:45:00.000000', 'EY 37', 'terminal-5', '1336688', 'Economy', 8),
(22, 'Indigo (6E)', 'Ahmedabad (AMD)', '2025-07-31 20:05:00.000000', '15 Kg', 'Chennai (MAA)', '2025-07-31 17:45:00.000000', '6E - 848', '1', 'Q4Q8HM', 'Economy', 9),
(23, 'Air India (AI)', 'Abu Dhabi (AUH)', '2025-08-03 11:15:00.000000', '15 Kg', 'Chennai (MAA)', '2025-08-02 21:30:00.000000', 'ey123', '1', '561615456456', 'Economy', 10),
(24, 'Qatar Airways (QR)', 'Abu Dhabi (AUH)', '2025-08-10 11:15:00.000000', '20 KG', 'Chennai (MAA)', '2025-08-09 21:00:00.000000', '564564154', '4', 'asdfsdf', 'Economy', 11),
(25, 'Indigo (6E)', 'Chennai (MAA)', '2025-08-06 07:50:00.000000', '15 Kg', 'Ahmedabad (AMD)', '2025-08-06 05:30:00.000000', '6078', '1', 'C249FC', 'Economy', 12),
(26, 'Lufthansa (LH)', 'Frankfurt(FRA)', '2025-08-16 07:05:00.000000', '0', 'Basel Airport (BSL)', '2025-08-16 06:05:00.000000', '6917', '- ,1', '2818871707-08-09', 'Economy', 13),
(27, 'LH', 'Chennai (MAA)', '2025-08-17 00:10:00.000000', '0', 'Frankfurt(FRA)', '2025-08-16 11:10:00.000000', '758', '1,2', '2818871707-08-09', 'Economy', 13),
(28, 'LH', 'Frankfurt(FRA)', '2025-09-18 08:45:00.000000', '0', 'Chennai (MAA)', '2025-09-18 01:55:00.000000', '759', '2,1', '2818871707-08-09', 'Economy', 13),
(29, 'LH', 'Basel Bad Railway Station (ZBA)', '2025-09-18 12:36:00.000000', '0', 'Frankfurt(FRA)', '2025-09-18 09:51:00.000000', '3540', 'TN,-', '2818871707-08-09', 'Economy', 13),
(30, 'Lufthansa (LH)', 'Frankfurt(FRA)', '2025-08-16 07:05:00.000000', '0', 'Basel Airport (BSL)', '2025-08-16 06:05:00.000000', '6917', '- ,1', '2202818871710', 'Economy', 14),
(31, 'LH', 'Chennai (MAA)', '2025-08-17 00:10:00.000000', '0', 'Frankfurt(FRA)', '2025-08-16 11:10:00.000000', '758', '1,2', '2202818871710', 'Economy', 14),
(32, 'LH', 'Frankfurt(FRA)', '2025-09-18 08:45:00.000000', '0', 'Chennai (MAA)', '2025-09-18 01:55:00.000000', '759', '2,1', '2202818871710', 'Economy', 14),
(33, 'LH', 'Basel Bad Railway Station (ZBA)', '2025-09-18 12:36:00.000000', '0', 'Frankfurt(FRA)', '2025-09-18 09:51:00.000000', '3540', 'TN,-', '2202818871710', 'Economy', 14),
(34, 'Etihad Airways (EY)', 'Abu Dhabi (AUH)', '2025-09-01 19:25:00.000000', '30', 'Chennai (MAA)', '2025-09-01 16:35:00.000000', '1017', '2,A', '6072411529314-15', 'Economy', 15),
(35, 'Etihad Airways (EY)', 'Zurich (ZRH)', '2025-09-02 07:00:00.000000', '30', 'Abu Dhabi (AUH)', '2025-09-02 02:25:00.000000', '143', 'A,-', '6072411529314-15', 'Economy', 15),
(36, 'Etihad Airways (EY)', 'Abu Dhabi (AUH)', '2025-09-11 06:35:00.000000', '30', 'Zurich (ZRH)', '2025-09-10 22:35:00.000000', '142', '-, A', '6072411529314-15', 'Economy', 15),
(37, 'Etihad Airways (EY)', 'Chennai (MAA)', '2025-09-11 14:35:00.000000', '30', 'Abu Dhabi (AUH)', '2025-09-11 08:50:00.000000', '1016', 'A,2', '6072411529314-15', 'Economy', 15),
(38, 'Emirates (EK)', 'Dubai (DXB)', '2025-09-13 06:40:00.000000', '30KG', 'Chennai (MAA)', '2025-09-13 04:00:00.000000', '543', '2,3', '1762818873382383', 'Economy', 16),
(39, 'Emirates (EK)', 'Frankfurt(FRA)', '2025-09-13 13:15:00.000000', '30KG', 'Dubai (DXB)', '2025-09-13 08:25:00.000000', '45', '3,2', '1762818873382383', 'Economy', 16),
(40, 'Rail Fly (W2)', 'Stuttgart Railway Station (ZWS)', '2025-09-13 16:08:00.000000', '30KG', 'Frankfurt(FRA)', '2025-09-13 14:51:00.000000', '6652', '3,-', '1762818873382383', 'Economy', 16),
(41, 'Lufthansa (LH)', 'Frankfurt(FRA)', '2025-09-30 09:05:00.000000', '25KG', 'Stuttgart ( STR )', '2025-09-30 08:15:00.000000', '131', '3,1', '1762818873382383', 'Economy', 16),
(42, 'Emirates (EK)', 'Dubai (DXB)', '2025-09-30 19:15:00.000000', '25KG', 'Frankfurt(FRA)', '2025-09-30 11:00:00.000000', '44', '2,3', '1762818873382383', 'Economy', 16),
(43, 'Emirates (EK)', 'Chennai (MAA)', '2025-10-01 02:30:00.000000', '25KG', 'Dubai (DXB)', '2025-09-30 21:00:00.000000', '542', '3,2', '1762818873382383', 'Economy', 16);

-- --------------------------------------------------------

--
-- Table structure for table `airlines_with_logo`
--

CREATE TABLE `airlines_with_logo` (
  `id` int NOT NULL,
  `airline` varchar(255) DEFAULT NULL,
  `logopath` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `airlines_with_logo`
--

INSERT INTO `airlines_with_logo` (`id`, `airline`, `logopath`) VALUES
(1, 'Scoot (TR)', '/var/www/fastuser/data/spring/airlinelogos/Scoot_logo.svg.png'),
(2, 'Indigo (6E)', '/var/www/fastuser/data/spring/airlinelogos/IndiGo-Logo.jpg'),
(3, 'Air India (AI)', '/var/www/fastuser/data/spring/airlinelogos/Air-India-Logo.png'),
(4, 'Emirates (EK)', '/var/www/fastuser/data/spring/airlinelogos/Emirates_logo.svg.png'),
(5, 'Qatar Airways (QR)', '/var/www/fastuser/data/spring/airlinelogos/Qatar-Airways-logo.jpg'),
(6, 'Air Asia (AK)', '/var/www/fastuser/data/spring/airlinelogos/AirAsia_New_Logo.svg.png'),
(7, 'Air India Express (IX)', '/var/www/fastuser/data/spring/airlinelogos/Air_India_Express_logo.svg.png'),
(8, 'Sri Lankan Airlines (UL)', '/var/www/fastuser/data/spring/airlinelogos/Srilankan-Airlines-logo.png'),
(9, 'Air Arabia (G9)', '/var/www/fastuser/data/spring/airlinelogos/Air_Arabia-Logo.wine.png'),
(10, 'Air France (AF)', '/var/www/fastuser/data/spring/airlinelogos/Air_France-Logo.wine.png'),
(11, 'Akasa Air (QP)', '/var/www/fastuser/data/spring/airlinelogos/Akasa_Air_logo.svg.png'),
(12, 'British Airways (BA)', '/var/www/fastuser/data/spring/airlinelogos/British_Airways-Logo.wine.png'),
(13, 'Etihad Airways (EY)', '/var/www/fastuser/data/spring/airlinelogos/etihad-airways-logo-png_seeklogo-177330.png'),
(14, 'Finnair (AY)', '/var/www/fastuser/data/spring/airlinelogos/finnair-logo.png'),
(15, 'Fly Dubai (FZ)', '/var/www/fastuser/data/spring/airlinelogos/FlyDubai-Logo.jpg'),
(16, 'Gulf Air (GF)', '/var/www/fastuser/data/spring/airlinelogos/Gulf-Air-logo.png'),
(17, 'KLM Airlines (KL)', '/var/www/fastuser/data/spring/airlinelogos/klm-airlines-vector-logo.png'),
(18, 'Kuwait Airways (KU)', '/var/www/fastuser/data/spring/airlinelogos/Kuwait-Airways-logo.png'),
(19, 'Lufthansa (LH)', '/var/www/fastuser/data/spring/airlinelogos/Lufthansa_Logo_2018.svg.png'),
(20, 'Malaysia Airlines (MH)', '/var/www/fastuser/data/spring/airlinelogos/images (2).png'),
(21, 'Oman Air (WY)', '/var/www/fastuser/data/spring/airlinelogos/Oman_Air-Logo.wine.png'),
(22, 'Singapore Airline (SQ)', '/var/www/fastuser/data/spring/airlinelogos/singapore-airlines-logo (1).png'),
(23, 'Spicejet (SG)', '/var/www/fastuser/data/spring/airlinelogos/SpiceJet-Logo.png'),
(24, 'Thai Airways (TG)', '/var/www/fastuser/data/spring/airlinelogos/Thai_Airways-Logo.wine.png'),
(25, 'Rail Fly (W2)', '/var/www/fastuser/data/spring/airlinelogos/images (3).png'),
(26, 'Scandinavian Airlines (SK)', '/var/www/fastuser/data/spring/airlinelogos/images (4).png');

-- --------------------------------------------------------

--
-- Table structure for table `airportcodes`
--

CREATE TABLE `airportcodes` (
  `id` int NOT NULL,
  `airportcode` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `airportcodes`
--

INSERT INTO `airportcodes` (`id`, `airportcode`) VALUES
(1, 'Chennai (MAA)'),
(2, 'Singapore (SIN)'),
(3, 'Abu Dhabi (AUH)'),
(4, 'Ahmedabad (AMD)'),
(5, 'Amsterdam (AMS)'),
(6, 'Athens (ATH)'),
(7, 'Ayodhya (AYJ)'),
(8, 'Bagdogra (IXB)'),
(9, 'Bali (DPS)'),
(10, 'Bangkok (BKK)'),
(11, 'Bengaluru (BLR)'),
(12, 'Boston (BOS)'),
(13, 'Brisbane (BNE)'),
(14, 'Budapest (BUD)'),
(15, 'Chicago (ORD)'),
(16, 'Coimbatore (CJB)'),
(17, 'Colombo (CMB)'),
(18, 'Copenhagen (CPH)'),
(19, 'Dehradun (DED)'),
(20, 'Delhi (DEL)'),
(21, 'Dhaka (DAC)'),
(22, 'Doha (DOH)'),
(23, 'Dubai (DXB)'),
(24, 'Frankfurt(FRA)'),
(25, 'Gatwick (LGW)'),
(26, 'Goa (GOI)'),
(27, 'Guwahati (GAU)'),
(28, 'Heathrow (LHR)'),
(29, 'Ho Chi Minh City (SGN)'),
(30, 'HongKong (HKG)'),
(31, 'Hyderabad (HYD)'),
(32, 'Indore (IDR)'),
(33, 'Istanbul (IST)'),
(34, 'Jodhpur (JDH)'),
(35, 'Kathmandu (KTM)'),
(36, 'Kochi (COK)'),
(37, 'Kolkata (CCU)'),
(38, 'Kozhikode (CCJ)'),
(39, 'Kuala Lumpur (KUL)'),
(40, 'Kuwait(KWI)'),
(41, 'Lisbon (LIS)'),
(42, 'Madrid (MAD)'),
(43, 'Madurai (IXM)'),
(44, 'Manchester (MAN)'),
(45, 'Mangaluru (IXE) '),
(46, 'Melbourne (MEL)'),
(47, 'Milan (MXP)'),
(48, 'Mumbai (BOM)'),
(49, 'Muscat (MCT)'),
(50, 'New York (JFK)'),
(51, 'Nice (NCE)'),
(52, 'North Goa (GOX)'),
(53, 'Paris (CDG)'),
(54, 'Phuket (HKT)'),
(55, 'Port Blair (IXZ)'),
(56, 'Pune (PNQ)'),
(57, 'Rome (FCO)'),
(58, 'Salem (SXV)'),
(59, 'San Francisco (SFO)'),
(60, 'Sharjah (SHJ)'),
(61, 'Houston (IAH)'),
(62, 'Sydney (SYD)'),
(63, 'Thiruvananthapuram (TRV)'),
(64, 'Tiruchirappalli (TRZ)'),
(65, 'Tuticorin (TCR)'),
(66, 'Udaipur (UDR)'),
(67, 'Varanasi (VNS)'),
(68, 'Vienna (VIE)'),
(69, 'Zurich (ZRH)'),
(70, 'Basel Airport (BSL)'),
(71, 'Basel Bad Railway Station (ZBA)'),
(72, 'Stuttgart Railway Station (ZWS)'),
(73, 'Stuttgart ( STR )');

-- --------------------------------------------------------

--
-- Table structure for table `attendence`
--

CREATE TABLE `attendence` (
  `id` int NOT NULL,
  `date` date DEFAULT NULL,
  `empid` varchar(255) DEFAULT NULL,
  `intime` time(6) DEFAULT NULL,
  `staffname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `attendence`
--

INSERT INTO `attendence` (`id`, `date`, `empid`, `intime`, `staffname`) VALUES
(1, '2025-04-26', 'SS008', '11:02:16.596000', 'Diwakar'),
(2, '2025-04-26', 'SS006', '12:56:45.418000', 'Saranraj'),
(3, '2025-04-27', 'SS006', '12:27:17.635000', 'Saranraj'),
(4, '2025-04-28', 'SS006', '06:34:40.741000', 'Saranraj'),
(5, '2025-04-28', 'SS008', '08:51:54.931000', 'Diwakar'),
(6, '2025-04-29', 'SS006', '04:04:43.576000', 'Saranraj'),
(7, '2025-04-30', 'SS006', '03:54:38.168000', 'Saranraj'),
(8, '2025-04-30', 'SS008', '04:43:01.797000', 'Diwakar'),
(9, '2025-04-30', 'SS007', '16:09:17.686000', 'Anupriya'),
(10, '2025-05-01', 'SS007', '02:41:44.534000', 'Anupriya'),
(11, '2025-05-01', 'SS006', '02:42:30.761000', 'Saranraj'),
(12, '2025-05-02', 'SS006', '04:31:57.738000', 'Saranraj'),
(13, '2025-05-03', 'SS008', '04:58:16.604000', 'Diwakar'),
(14, '2025-05-03', 'SS006', '04:59:07.772000', 'Saranraj'),
(16, '2025-05-05', 'SS006', '07:54:32.739000', 'Saranraj'),
(17, '2025-05-06', 'SS006', '03:22:03.074000', 'Saranraj'),
(18, '2025-05-07', 'SS006', '05:16:19.116000', 'Saranraj'),
(19, '2025-05-08', 'SS006', '05:29:33.354000', 'Saranraj'),
(20, '2025-05-08', 'SS007', '07:07:33.390000', 'Anu'),
(21, '2025-05-08', 'SS009', '07:07:41.361000', 'Kalai'),
(22, '2025-05-09', 'SS006', '03:40:33.773000', 'Saran'),
(23, '2025-05-09', 'SS007', '03:40:35.713000', 'Anu'),
(24, '2025-05-09', 'SS009', '03:40:37.576000', 'Kalai'),
(25, '2025-05-10', 'SS006', '04:08:01.065000', 'Saran'),
(26, '2025-05-10', 'SS007', '04:08:06.271000', 'Anu'),
(27, '2025-05-10', 'SS009', '04:08:09.126000', 'Kalai'),
(28, '2025-05-11', 'SS006', '10:38:41.480000', 'Saran'),
(29, '2025-05-11', 'SS007', '10:38:43.351000', 'Anu'),
(30, '2025-05-11', 'SS009', '10:38:46.106000', 'Kalai'),
(31, '2025-05-12', 'SS006', '04:40:05.213000', 'Saran'),
(32, '2025-05-12', 'SS007', '04:40:07.788000', 'Anu'),
(33, '2025-05-12', 'SS009', '04:40:10.894000', 'Kalai'),
(34, '2025-05-12', 'SS008', '05:34:18.857000', 'Diwa'),
(35, '2025-05-13', 'SS006', '03:36:48.366000', 'Saran'),
(36, '2025-05-13', 'SS007', '03:36:50.172000', 'Anu'),
(37, '2025-05-13', 'SS009', '03:36:51.941000', 'Kalai'),
(38, '2025-05-13', 'SS008', '06:40:00.577000', 'Diwa'),
(39, '2025-05-14', 'SS006', '04:48:48.940000', 'Saran'),
(40, '2025-05-14', 'SS007', '04:48:50.026000', 'Anu'),
(41, '2025-05-14', 'SS009', '04:48:51.763000', 'Kalai'),
(42, '2025-05-14', 'SS008', '04:48:55.869000', 'Diwa'),
(43, '2025-05-15', 'SS006', '07:55:04.370000', 'Saran'),
(44, '2025-05-15', 'SS007', '07:55:06.157000', 'Anu'),
(45, '2025-05-15', 'SS008', '07:55:07.798000', 'Diwa'),
(46, '2025-05-15', 'SS009', '07:55:09.312000', 'Kalai'),
(47, '2025-05-16', 'SS006', '04:22:00.057000', 'Saran'),
(48, '2025-05-16', 'SS007', '04:22:01.452000', 'Anu'),
(49, '2025-05-16', 'SS009', '04:22:03.308000', 'Kalai'),
(50, '2025-05-16', 'SS008', '09:51:24.748000', 'Diwa'),
(51, '2025-05-19', 'SS006', '05:06:19.215000', 'Saran'),
(52, '2025-05-19', 'SS007', '05:06:20.199000', 'Anu'),
(53, '2025-05-19', 'SS009', '05:06:21.739000', 'Kalai'),
(54, '2025-05-20', 'SS006', '04:28:46.432000', 'Saran'),
(55, '2025-05-20', 'SS007', '04:28:47.396000', 'Anu'),
(56, '2025-05-20', 'SS009', '04:28:48.888000', 'Kalai'),
(57, '2025-05-21', 'SS006', '04:15:27.866000', 'Saran'),
(58, '2025-05-21', 'SS007', '04:15:29.258000', 'Anu'),
(59, '2025-05-21', 'SS009', '04:15:31.310000', 'Kalai'),
(60, '2025-05-22', 'SS006', '06:49:40.232000', 'Saran'),
(61, '2025-05-22', 'SS007', '06:50:17.299000', 'Anu'),
(62, '2025-05-22', 'SS009', '06:50:18.955000', 'Kalai'),
(63, '2025-05-23', 'SS006', '08:19:29.028000', 'Saran'),
(64, '2025-05-23', 'SS007', '08:19:30.324000', 'Anu'),
(65, '2025-05-23', 'SS009', '08:19:32.082000', 'Kalai'),
(66, '2025-05-24', 'SS006', '05:49:07.410000', 'Saran'),
(67, '2025-05-24', 'SS007', '05:49:08.886000', 'Anu'),
(68, '2025-05-24', 'SS009', '05:49:10.480000', 'Kalai'),
(69, '2025-05-26', 'SS006', '06:09:33.033000', 'Saran'),
(70, '2025-05-26', 'SS007', '06:09:37.182000', 'Anu'),
(71, '2025-05-26', 'SS009', '06:09:38.826000', 'Kalai'),
(72, '2025-05-27', 'SS006', '04:06:23.188000', 'Saran'),
(73, '2025-05-27', 'SS007', '04:06:24.694000', 'Anu'),
(74, '2025-05-27', 'SS009', '04:06:26.563000', 'Kalai'),
(75, '2025-05-28', 'SS006', '05:15:21.951000', 'Saran'),
(76, '2025-05-28', 'SS007', '05:15:22.951000', 'Anu'),
(77, '2025-05-28', 'SS009', '05:15:24.291000', 'Kalai'),
(78, '2025-05-29', 'SS006', '05:16:44.406000', 'Saran'),
(79, '2025-05-29', 'SS007', '05:16:45.317000', 'Anu'),
(80, '2025-05-29', 'SS009', '05:16:46.877000', 'Kalai'),
(81, '2025-05-30', 'SS006', '04:54:15.558000', 'Saran'),
(82, '2025-05-30', 'SS007', '04:54:17.173000', 'Anu'),
(83, '2025-05-30', 'SS009', '04:54:18.472000', 'Kalai'),
(84, '2025-06-02', 'SS008', '04:43:02.065000', 'Diwakar'),
(85, '2025-06-02', 'SS006', '04:44:38.363000', 'Saran'),
(86, '2025-06-02', 'SS007', '04:44:39.165000', 'Anu'),
(87, '2025-06-02', 'SS009', '04:44:39.915000', 'Kalai'),
(88, '2025-06-03', 'SS006', '04:31:53.879000', 'Saran'),
(89, '2025-06-03', 'SS007', '04:31:55.361000', 'Anu'),
(90, '2025-06-03', 'SS009', '04:31:56.763000', 'Kalai'),
(91, '2025-06-03', 'SS008', '04:47:16.155000', 'Diwakar'),
(92, '2025-06-05', 'SS006', '05:28:44.844000', 'Saran'),
(93, '2025-06-05', 'SS007', '05:28:45.869000', 'Anu'),
(94, '2025-06-05', 'SS009', '05:28:47.549000', 'Kalai'),
(95, '2025-06-06', 'SS008', '03:14:03.993000', 'Diwakar'),
(96, '2025-06-07', 'SS006', '03:58:48.215000', 'Saran'),
(97, '2025-06-07', 'SS007', '03:58:49.195000', 'Anu'),
(98, '2025-06-07', 'SS009', '03:58:50.837000', 'Kalai'),
(99, '2025-06-07', 'SS008', '04:23:42.921000', 'Diwakar'),
(100, '2025-06-09', 'SS008', '04:39:37.183000', 'Diwakar'),
(101, '2025-06-10', 'SS008', '07:17:54.693000', 'Diwakar'),
(102, '2025-06-10', 'SS006', '09:03:10.463000', 'Saran'),
(103, '2025-06-10', 'SS007', '09:03:11.442000', 'Anu'),
(104, '2025-06-10', 'SS009', '09:03:12.338000', 'Kalai'),
(105, '2025-06-11', 'SS006', '05:20:24.257000', 'Saran'),
(106, '2025-06-11', 'SS007', '05:20:25.542000', 'Anu'),
(107, '2025-06-11', 'SS009', '05:20:26.920000', 'Kalai'),
(108, '2025-06-11', 'SS008', '05:51:21.481000', 'Diwakar'),
(109, '2025-06-12', 'SS008', '04:44:09.712000', 'Diwakar'),
(110, '2025-06-13', 'SS008', '04:18:55.015000', 'Diwakar'),
(111, '2025-06-13', 'SS006', '06:04:31.772000', 'Saran'),
(112, '2025-06-13', 'SS007', '06:04:32.967000', 'Anu'),
(113, '2025-06-13', 'SS009', '06:04:33.838000', 'Kalai'),
(114, '2025-06-14', 'SS008', '05:03:44.726000', 'Diwakar'),
(115, '2025-06-14', 'SS006', '05:57:42.909000', 'Saran'),
(116, '2025-06-14', 'SS007', '05:57:43.791000', 'Anu'),
(117, '2025-06-14', 'SS009', '05:57:44.645000', 'Kalai'),
(118, '2025-06-16', 'SS008', '04:24:52.515000', 'Diwakar'),
(119, '2025-06-16', 'SS006', '04:25:46.496000', 'Saran'),
(120, '2025-06-16', 'SS007', '04:25:47.584000', 'Anu'),
(121, '2025-06-16', 'SS009', '04:25:48.569000', 'Kalai'),
(122, '2025-06-17', 'SS008', '05:00:00.535000', 'Diwakar'),
(123, '2025-06-17', 'SS006', '06:25:35.173000', 'Saran'),
(124, '2025-06-17', 'SS007', '06:25:36.915000', 'Anu'),
(125, '2025-06-17', 'SS009', '06:25:38.014000', 'Kalai'),
(126, '2025-06-18', 'SS008', '04:17:55.076000', 'Diwakar'),
(128, '2025-06-18', 'SS007', '07:42:07.236000', 'Anu'),
(134, '2025-06-18', 'SS006', '10:41:52.946000', 'Saran'),
(135, '2025-06-18', 'SS009', '10:41:55.041000', 'Kalai'),
(136, '2025-06-19', 'SS006', '03:59:57.505000', 'Saran'),
(137, '2025-06-19', 'SS007', '03:59:58.792000', 'Anu'),
(138, '2025-06-19', 'SS009', '04:00:00.572000', 'Kalai'),
(139, '2025-06-19', 'SS008', '04:48:36.800000', 'Diwakar'),
(140, '2025-06-20', 'SS008', '04:23:32.598000', 'Diwakar'),
(141, '2025-06-20', 'SS006', '06:54:54.607000', 'Saran'),
(142, '2025-06-20', 'SS007', '06:54:55.652000', 'Anu'),
(143, '2025-06-20', 'SS009', '06:54:56.447000', 'Kalai'),
(144, '2025-06-21', 'SS008', '04:53:28.374000', 'Diwakar'),
(145, '2025-06-23', 'SS008', '04:21:03.812000', 'Diwakar'),
(146, '2025-06-23', 'SS006', '04:53:35.358000', 'Saran'),
(147, '2025-06-23', 'SS007', '04:53:36.266000', 'Anu'),
(148, '2025-06-23', 'SS009', '04:53:37.154000', 'Kalai'),
(149, '2025-06-24', 'SS006', '04:56:54.709000', 'Saran'),
(150, '2025-06-24', 'SS007', '04:56:56.118000', 'Anu'),
(151, '2025-06-24', 'SS009', '04:56:57.408000', 'Kalai'),
(152, '2025-06-24', 'SS008', '04:58:08.800000', 'Diwakar'),
(153, '2025-06-25', 'SS008', '04:28:45.903000', 'Diwakar'),
(154, '2025-06-25', 'SS006', '10:30:06.119000', 'Saran'),
(155, '2025-06-25', 'SS007', '10:30:07.458000', 'Anu'),
(156, '2025-06-25', 'SS009', '10:30:08.359000', 'Kalai'),
(157, '2025-06-26', 'SS008', '04:54:32.588000', 'Diwakar'),
(158, '2025-06-26', 'SS006', '08:41:52.461000', 'Saran'),
(159, '2025-06-26', 'SS007', '08:41:53.538000', 'Anu'),
(160, '2025-06-26', 'SS009', '08:41:54.379000', 'Kalai'),
(161, '2025-06-27', 'SS008', '04:19:27.052000', 'Diwakar'),
(162, '2025-06-27', 'SS006', '06:38:50.851000', 'Saran'),
(163, '2025-06-27', 'SS007', '06:38:52.112000', 'Anu'),
(164, '2025-06-27', 'SS009', '06:38:52.999000', 'Kalai'),
(165, '2025-06-28', 'SS008', '05:15:49.415000', 'Diwakar'),
(166, '2025-06-28', 'SS006', '08:07:04.247000', 'Saran'),
(167, '2025-06-28', 'SS007', '08:07:05.593000', 'Anu'),
(168, '2025-06-28', 'SS009', '08:07:06.612000', 'Kalai'),
(169, '2025-07-05', 'SS006', '15:56:51.438000', 'Saran'),
(170, '2025-07-05', 'SS007', '15:56:53.478000', 'Anu'),
(171, '2025-07-05', 'SS009', '15:56:55.535000', 'Kalai'),
(172, '2025-07-05', 'SS008', '16:05:33.908000', 'Diwa'),
(173, '2025-07-05', 'SS008', '17:49:46.380000', 'Diwakar'),
(175, '2025-07-07', 'SS007', '10:25:45.930000', 'Anu'),
(176, '2025-07-07', 'SS009', '10:25:47.150000', 'Kalai'),
(177, '2025-07-07', 'SS008', '10:31:12.232000', 'Diwakar'),
(178, '2025-07-07', 'SS006', '11:18:37.046000', 'Saran'),
(179, '2025-06-30', 'SS006', '11:09:14.870000', 'Saran'),
(180, '2025-06-30', 'SS007', '11:09:17.005000', 'Anu'),
(181, '2025-06-30', 'SS009', '11:09:19.984000', 'Kalai'),
(182, '2025-07-01', 'SS008', '04:38:10.677000', 'Diwakar'),
(183, '2025-07-01', 'SS006', '06:50:20.063000', 'Saran'),
(184, '2025-07-01', 'SS007', '06:50:22.302000', 'Anu'),
(185, '2025-07-01', 'SS009', '06:50:23.323000', 'Kalai'),
(186, '2025-07-03', 'SS006', '05:23:26.738000', 'Saran'),
(187, '2025-07-03', 'SS007', '05:23:28.108000', 'Anu'),
(188, '2025-07-03', 'SS009', '05:23:29.972000', 'Kalai'),
(189, '2025-07-04', 'SS006', '05:06:08.907000', 'Saran'),
(190, '2025-07-04', 'SS007', '05:06:15.887000', 'Anu'),
(191, '2025-07-04', 'SS009', '05:06:17.798000', 'Kalai'),
(192, '2025-07-09', 'SS008', '11:46:53.805000', 'Diwakar'),
(193, '2025-07-10', 'SS008', '10:34:00.156000', 'Diwakar'),
(194, '2025-07-10', 'SS006', '14:28:35.315000', 'Saran'),
(195, '2025-07-10', 'SS007', '14:28:37.067000', 'Anu'),
(196, '2025-07-10', 'SS009', '14:28:38.600000', 'Kalai'),
(197, '2025-07-11', 'SS008', '10:15:07.654000', 'Diwakar'),
(198, '2025-07-11', 'SS006', '10:41:11.682000', 'Saran'),
(199, '2025-07-11', 'SS007', '10:41:12.786000', 'Anu'),
(200, '2025-07-11', 'SS009', '10:41:14.067000', 'Kalai'),
(201, '2025-07-12', 'SS008', '11:36:01.472000', 'Diwakar'),
(202, '2025-07-14', 'SS008', '12:12:34.329000', 'Diwakar'),
(203, '2025-07-28', 'SS008', '09:55:06.816000', 'Diwakar'),
(204, '2025-07-28', 'SS006', '10:04:56.212000', 'Saran'),
(205, '2025-07-28', 'SS007', '10:04:57.741000', 'Anu'),
(206, '2025-07-28', 'SS009', '10:04:59.191000', 'Kalai'),
(207, '2025-07-31', 'SS010', '10:16:25.294000', 'Premkumar'),
(209, '2025-07-31', 'SS007', '11:59:41.496000', 'Anu'),
(210, '2025-07-31', 'SS009', '11:59:42.650000', 'Kalai'),
(212, '2025-07-31', 'SS006', '13:38:22.866000', 'Saranraj'),
(213, '2025-08-01', 'SS008', '09:47:01.041000', 'Diwakar'),
(214, '2025-08-01', 'SS010', '09:52:47.544000', 'Premkumar'),
(215, '2025-08-02', 'SS008', '09:41:28.837000', 'Diwakar'),
(216, '2025-08-02', 'SS010', '10:00:10.057000', 'Premkumar'),
(217, '2025-08-04', 'SS008', '10:03:48.305000', 'Diwakar'),
(218, '2025-08-04', 'SS010', '10:06:07.780000', 'Premkumar'),
(219, '2025-08-04', 'SS006', '10:43:53.129000', 'Saran'),
(220, '2025-08-04', 'SS007', '10:43:54.478000', 'Anu'),
(221, '2025-08-04', 'SS009', '10:43:55.475000', 'Kalai'),
(222, '2025-08-05', 'SS010', '09:56:31.215000', 'Premkumar'),
(223, '2025-08-05', 'SS008', '10:00:43.859000', 'Diwakar'),
(224, '2025-08-05', 'SS006', '13:14:58.135000', 'Saran'),
(225, '2025-08-05', 'SS007', '13:15:01.143000', 'Anu'),
(226, '2025-08-05', 'SS009', '13:15:02.509000', 'Kalai'),
(227, '2025-08-06', 'SS008', '10:09:00.399000', 'Diwakar'),
(228, '2025-08-06', 'SS010', '10:10:08.677000', 'Premkumar'),
(229, '2025-08-06', 'SS006', '12:35:54.966000', 'Saran'),
(230, '2025-08-06', 'SS007', '12:35:56.040000', 'Anu'),
(231, '2025-08-06', 'SS009', '12:35:57.190000', 'Kalai'),
(232, '2025-08-07', 'SS006', '10:09:19.614000', 'Saran'),
(233, '2025-08-07', 'SS007', '10:09:21.058000', 'Anu'),
(234, '2025-08-07', 'SS009', '10:09:23.333000', 'Kalai'),
(235, '2025-08-07', 'SS010', '10:15:12.125000', 'Premkumar'),
(236, '2025-08-07', 'SS008', '10:42:09.756000', 'Diwakar'),
(237, '2025-08-08', 'SS008', '10:09:26.666000', 'Diwakar'),
(238, '2025-08-08', 'SS010', '10:13:35.709000', 'Premkumar'),
(239, '2025-08-09', 'SS008', '10:01:59.038000', 'Diwakar'),
(240, '2025-08-09', 'SS010', '10:03:32.337000', 'Premkumar'),
(241, '2025-08-09', 'SS007', '11:48:35.048000', 'Anu'),
(242, '2025-08-09', 'SS006', '11:48:34.445000', 'Saran'),
(243, '2025-08-09', 'SS009', '11:48:36.043000', 'Kalai'),
(244, '2025-08-11', 'SS010', '10:08:33.754000', 'Premkumar'),
(245, '2025-08-11', 'SS008', '10:18:43.952000', 'Diwakar'),
(246, '2025-08-12', 'SS006', '17:43:36.197000', 'Saran'),
(247, '2025-08-12', 'SS007', '17:43:37.470000', 'Anu'),
(248, '2025-08-12', 'SS008', '17:43:38.233000', 'Diwa'),
(249, '2025-08-12', 'SS009', '17:43:39.195000', 'Kalai'),
(250, '2025-08-12', 'SS010', '17:43:39.953000', 'Prem'),
(251, '2025-08-13', 'SS010', '09:55:08.149000', 'Premkumar'),
(252, '2025-08-13', 'SS008', '09:55:54.451000', 'Diwakar'),
(253, '2025-08-13', 'SS006', '11:44:30.349000', 'Saran'),
(254, '2025-08-13', 'SS007', '11:44:33.545000', 'Anu'),
(255, '2025-08-13', 'SS009', '11:44:35.448000', 'Kalai'),
(256, '2025-08-14', 'SS010', '09:42:38.038000', 'Premkumar'),
(257, '2025-08-16', 'SS010', '10:10:11.041000', 'Premkumar'),
(258, '2025-08-16', 'SS008', '10:10:46.539000', 'Diwakar'),
(259, '2025-08-16', 'SS006', '11:30:41.538000', 'Saran'),
(260, '2025-08-16', 'SS007', '11:30:42.772000', 'Anu'),
(261, '2025-08-16', 'SS009', '11:30:43.746000', 'Kalai'),
(262, '2025-08-18', 'SS006', '10:09:31.147000', 'Saran'),
(263, '2025-08-18', 'SS007', '10:09:34.848000', 'Anu'),
(264, '2025-08-18', 'SS008', '10:13:20.142000', 'Diwakar'),
(265, '2025-08-18', 'SS009', '11:18:19.641000', 'Kalai'),
(266, '2025-08-19', 'SS008', '11:19:56.820000', 'Diwakar'),
(267, '2025-08-19', 'SS010', '11:34:43.008000', 'Premkumar'),
(268, '2025-08-19', 'SS006', '13:43:26.933000', 'Saran'),
(269, '2025-08-19', 'SS006', '13:43:24.563000', 'Saran'),
(270, '2025-08-19', 'SS007', '13:43:29.290000', 'Anu'),
(271, '2025-08-19', 'SS009', '13:43:30.440000', 'Kalai'),
(272, '2025-08-20', 'SS010', '09:37:21.146000', 'Premkumar'),
(273, '2025-08-20', 'SS006', '10:56:17.691000', 'Saran'),
(274, '2025-08-20', 'SS007', '10:56:21.897000', 'Anu'),
(275, '2025-08-20', 'SS009', '10:56:24.087000', 'Kalai'),
(276, '2025-08-22', 'SS008', '09:38:05.147000', 'Diwakar'),
(277, '2025-08-22', 'SS010', '09:38:34.637000', 'Premkumar'),
(278, '2025-08-22', 'SS006', '10:13:44.943000', 'Saran'),
(279, '2025-08-22', 'SS007', '10:13:47.945000', 'Anu'),
(280, '2025-08-22', 'SS009', '10:13:51.642000', 'Kalai'),
(281, '2025-08-23', 'SS010', '09:44:10.839000', 'Premkumar'),
(282, '2025-08-23', 'SS008', '09:45:33.635000', 'Diwakar'),
(283, '2025-08-23', 'SS006', '10:41:32.140000', 'Saran'),
(284, '2025-08-23', 'SS007', '10:41:34.037000', 'Anu'),
(285, '2025-08-23', 'SS009', '10:41:36.038000', 'Kalai'),
(286, '2025-08-25', 'SS006', '15:01:03.403000', 'Saran'),
(287, '2025-08-25', 'SS007', '15:01:06.070000', 'Anu'),
(288, '2025-08-25', 'SS008', '15:01:07.260000', 'Diwa'),
(289, '2025-08-25', 'SS009', '15:01:08.125000', 'Kalai'),
(290, '2025-08-25', 'SS010', '15:01:09.933000', 'Prem');

-- --------------------------------------------------------

--
-- Table structure for table `customerbook`
--

CREATE TABLE `customerbook` (
  `id` int NOT NULL,
  `customermail` varchar(255) DEFAULT NULL,
  `customername` varchar(255) DEFAULT NULL,
  `customernumber` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `customerbook`
--

INSERT INTO `customerbook` (`id`, `customermail`, `customername`, `customernumber`) VALUES
(1, 'saranraj@sivajison.com', 'Saranraj', '9655150814'),
(2, 'DIWAKAR@SIVAJISON.COM', 'MA TIN TIN KHAING', '7305111675'),
(3, 'itsmeyourkavi@gmail.com', 'Kaviyarasan', '9176316030'),
(10, 'ajithak7595@gmail.com', 'Ajith', '9843613235'),
(11, 'saravananactor@yahoo.com', 'Saravanan', '9789020393'),
(12, 'subashcb7799@gmail.com', 'Testing', '1234567890'),
(16, 'damu@jayaam.com', 'Damu - Jayaam ', '9445008794'),
(20, 'justin.s.antony@gmail.com', 'MR JUSTIN ANTONY SELVARAJ', '1521122486'),
(23, 'dilipkumar@sightspectrum.com', 'MR.DILIP KUMAR', '9384876735');

-- --------------------------------------------------------

--
-- Table structure for table `customerotherdetails`
--

CREATE TABLE `customerotherdetails` (
  `id` int NOT NULL,
  `busticket` text,
  `collectiondate` date DEFAULT NULL,
  `customerid` varchar(255) DEFAULT NULL,
  `customermail` varchar(255) DEFAULT NULL,
  `customername` varchar(255) DEFAULT NULL,
  `customernumber` varchar(255) DEFAULT NULL,
  `passportdetails` text,
  `submissiondate` date DEFAULT NULL,
  `travelinsurance` text,
  `visadetails` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `customerotherdetails`
--

INSERT INTO `customerotherdetails` (`id`, `busticket`, `collectiondate`, `customerid`, `customermail`, `customername`, `customernumber`, `passportdetails`, `submissiondate`, `travelinsurance`, `visadetails`) VALUES
(1, '', NULL, '9655150814', 'saranraj@sivajison.com', 'Saranraj', '9655150814', '', NULL, '', ''),
(2, 'testing', '2025-08-02', '9597656498', 'subashcb7799@gmail.com', 'subash', '9597656498', 'testing', '2025-07-31', 'testing', 'testing');

-- --------------------------------------------------------

--
-- Table structure for table `customers_hotel_details`
--

CREATE TABLE `customers_hotel_details` (
  `id` int NOT NULL,
  `bookingid` varchar(255) DEFAULT NULL,
  `checkindate` date DEFAULT NULL,
  `checkoutdate` date DEFAULT NULL,
  `customermail` varchar(255) DEFAULT NULL,
  `customername` varchar(255) DEFAULT NULL,
  `customernumber` varchar(255) DEFAULT NULL,
  `hcn` varchar(255) DEFAULT NULL,
  `hotelcontactperson` varchar(255) DEFAULT NULL,
  `hotelname` varchar(255) DEFAULT NULL,
  `noofnights` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `customers_hotel_details`
--

INSERT INTO `customers_hotel_details` (`id`, `bookingid`, `checkindate`, `checkoutdate`, `customermail`, `customername`, `customernumber`, `hcn`, `hotelcontactperson`, `hotelname`, `noofnights`) VALUES
(9, '99991111', '2025-08-02', '2025-08-03', 'subashcb7799@gmail.com', 'subash', '9597656498', '044 945678', 'testing', 'Testing', 1);

-- --------------------------------------------------------

--
-- Table structure for table `customer_flight_details`
--

CREATE TABLE `customer_flight_details` (
  `id` int NOT NULL,
  `traveldate` date DEFAULT NULL,
  `customermail` varchar(255) DEFAULT NULL,
  `customername` varchar(255) DEFAULT NULL,
  `customernumber` varchar(255) DEFAULT NULL,
  `cutomerid` varchar(255) DEFAULT NULL,
  `issuedate` date DEFAULT NULL,
  `pnr` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `customer_flight_details`
--

INSERT INTO `customer_flight_details` (`id`, `traveldate`, `customermail`, `customername`, `customernumber`, `cutomerid`, `issuedate`, `pnr`) VALUES
(1, '2025-04-28', 'DIWAKAR@SIVAJISON.COM', 'MA TIN TIN KHAING', '7305111675', '7305111675', '2025-04-26', 'Y6PK5V'),
(2, '2025-05-22', 'itsmeyourkavi@gmail.com', 'Kaviyarasan', '9176316030', '9176316030', '2025-04-27', '58XW92'),
(3, '2025-05-22', 'itsmeyourkavi@gmail.com', 'Kaviyarasu', '9176316030', '9176316030', '2025-04-27', '58XYYD'),
(4, '2025-05-16', 'saranraj@sivajison.com', 'Saranraj', '9655150814', '9655150814', '2025-05-08', '5NXH9D'),
(5, '2025-05-09', 'saranraj@sivajison.com', 'saranraj', '9655150814', '9655150814', '2025-05-08', '123456'),
(6, '2025-08-13', 'ajithak7595@gmail.com', 'Ajith', '9843613235', '9843613235', '2025-05-09', '123456'),
(7, '2025-05-14', 'saravananactor@yahoo.com', 'Saravanan', '9789020393', '9789020393', '2025-03-13', 'F85FX2'),
(8, '2025-08-02', 'subashcb7799@gmail.com', 'subash', '9597656498', '9597656498', '2025-07-31', 'PNR9999'),
(9, '2025-07-31', 'damu@jayaam.com', 'Damu - Jayaam ', '9445008794', '9445008794', '2025-07-31', 'Q4Q8HM'),
(10, '2025-08-02', 'saranraj@sivajison.com', 'saranraj', '9655150814', '9655150814', '2025-07-31', 'Gnnasdfn'),
(11, '2025-08-09', 'saranraj@sivajison.com', 'saranraj', '9655150814', '9655150814', '2025-07-31', 'test now 1'),
(12, '2025-08-06', 'damu@jayaam.com', 'JAYAAM GALVANIZERS PRIVATE LIMITED', '9445008795', '9445008795', '2025-08-05', 'C249FC'),
(13, '2025-08-16', 'justin.s.antony@gmail.com', 'MR JUSTIN ANTONY SELVARAJ', '1521122486', '1521122486', '2025-08-05', 'WW14LT'),
(14, '2025-08-16', 'justin.s.antony@gmail.com', 'MR JUSTIN ANTONY SELVARAJ', '1521122486', '1521122486', '2025-08-05', 'FVW2SE'),
(15, '2025-09-01', 'itsmeyourkavi@gmail.com', 'KAVIYARASAN', '9176316030', '9176316030', '2025-08-06', '722PAT'),
(16, '2025-09-13', 'dilipkumar@sightspectrum.com', 'MR.DILIP KUMAR', '9384876735', '9384876735', '2025-08-06', 'NP4CIN');

-- --------------------------------------------------------

--
-- Table structure for table `dailyreport`
--

CREATE TABLE `dailyreport` (
  `id` int NOT NULL,
  `date` date DEFAULT NULL,
  `empid` varchar(255) DEFAULT NULL,
  `empname` varchar(255) DEFAULT NULL,
  `report` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `hotelguest`
--

CREATE TABLE `hotelguest` (
  `id` int NOT NULL,
  `passengername` varchar(255) DEFAULT NULL,
  `hotel_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `hotelguest`
--

INSERT INTO `hotelguest` (`id`, `passengername`, `hotel_id`) VALUES
(1, 'testing 11', 9),
(2, 'testing 12', 9);

-- --------------------------------------------------------

--
-- Table structure for table `passengername`
--

CREATE TABLE `passengername` (
  `id` int NOT NULL,
  `passengername` varchar(255) DEFAULT NULL,
  `flight_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `passengername`
--

INSERT INTO `passengername` (`id`, `passengername`, `flight_id`) VALUES
(1, 'MS. MA TIN TIN KHAING', 1),
(2, 'JOSEPH/JAYARAJ MR ', 2),
(3, 'ALEX/LISSY MRS ', 3),
(4, 'saranraj', 4),
(5, 'Saranraj Sivaji', 5),
(6, 'Anupriya Saranraj', 5),
(7, 'AJITHKUMAR', 6),
(8, 'SARANRAJ', 6),
(9, 'MR. SARAVANAN SARANGAPANI', 7),
(10, 'testing9', 8),
(11, 'testing10', 8),
(12, 'MR ARUN KUMAR JENA ', 9),
(13, 'MR THUMMA GIRISH KUMAR', 9),
(14, 'test now', 10),
(15, 'Mr. ARUN KUMAR JENA', 12),
(16, 'Mr. THUMMA GIRISH KUMAR', 12),
(17, 'MR JUSTIN ANTONY SELVARAJ', 13),
(18, 'M KAVITHENDRAL JUSTIN', 13),
(19, 'MISS MAHILVADHANI LOVISHA JUSTIN', 13),
(20, 'MS. RENU KARTHIKA / MURUGESAN', 14),
(21, 'MS. MANJULA DEVI THIRUGNANAM', 15),
(22, 'MS. SELVI GURUNATHAN', 15),
(23, 'Mr. DILIP KUMAR DEVARAJ', 16);

-- --------------------------------------------------------

--
-- Table structure for table `reminder`
--

CREATE TABLE `reminder` (
  `id` int NOT NULL,
  `finaldate` varchar(255) DEFAULT NULL,
  `paymentdues` varchar(255) DEFAULT NULL,
  `updates` varchar(255) DEFAULT NULL,
  `visaappointment` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `reminder`
--

INSERT INTO `reminder` (`id`, `finaldate`, `paymentdues`, `updates`, `visaappointment`) VALUES
(55, '2025-08-11', 'vgp payment ', '', ''),
(59, '2025-08-15', 'baskar payment 136585/-', '', ''),
(89, '2025-08-30', '', 'Saravanan Actor\nUSA visa appoinment 01 SEP 2025', ''),
(90, '2025-09-05', '', 'Yohan USA TKT - Date Change ', ''),
(91, '2025-09-25', '', 'FAROUK X 2 PAX 25 SEP ', 'GERMANY VISA VFS APPOINTMENT '),
(92, '2025-08-22', '', 'SOBY AUSTRIA VFS APPOINTMENT 22 AUG ', 'AUSTRIA VISA APPOINTMENT ');

-- --------------------------------------------------------

--
-- Table structure for table `resourcelink`
--

CREATE TABLE `resourcelink` (
  `id` int NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `words` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `resourcelink`
--

INSERT INTO `resourcelink` (`id`, `link`, `words`) VALUES
(7, 'https://imigresen-online.imi.gov.my/mdac/main', 'Malaysia Arrivel Card'),
(8, 'https://eservices.ica.gov.sg/ipsave/index.xhtml', 'Singapore Visa Status '),
(9, 'https://tdac.immigration.go.th/arrival-card/#/home', 'Thailand Arrivel Card'),
(10, 'https://evisa.gov.vn/', 'Viatnam Visa '),
(11, 'https://eservices.ica.gov.sg/sgarrivalcard/fvipa', 'Singapore Arrivel Card'),
(12, 'https://www.immd.gov.hk/eng/services/visas/pre-arrival_registration_for_indian_nationals.html', 'Hong Kong - Pre-arrival Registration');

-- --------------------------------------------------------

--
-- Table structure for table `task_list`
--

CREATE TABLE `task_list` (
  `id` int NOT NULL,
  `empid` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `staffname` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `task` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int NOT NULL,
  `empid` varchar(255) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `empid`, `mail`, `name`, `number`, `password`, `role`, `username`) VALUES
(1, NULL, NULL, 'admin', NULL, '$2a$10$R9lITz.G4XdEGIjASsNi3e/cTKAGWUoCzpD2c3B7t5GYpFKfDO2Cm', 'admin', 'admin'),
(9, 'SS006', NULL, 'Saranraj', '9655150814', '$2a$10$puUI3D2rWOKvKdW4QKeM9.OLOqpxciogG1NdiFi3jLG.XxwlhPm/W', 'staff', 'Saran'),
(10, 'SS007', NULL, 'Anupriya', '7823992399', '$2a$10$WAmI7AkhjgSSm3e4bRUx9e6vSALTLuqOcGYm4vbeD52oXDqxfEcV6', 'staff', 'Anu'),
(11, 'SS008', NULL, 'Diwakar', '8428293626', '$2a$10$8QLAzUCMXJ2Tp7da8flb0.mrwt.rj940fArzQAgzgRIwfzBb84FxW', 'staff', 'Diwa'),
(17, 'SS009', NULL, 'Kalaimathi', '9843766380', '$2a$10$RNUNMI3/DeA9AWeoh0k1ae7YGSniEGfmDx4uPBUtRtw8Doal6m.DK', 'staff', 'Kalai'),
(19, 'SS010', NULL, 'Premkumar', '8825972314', '$2a$10$EOskBJxrKTzvi4YIpyEF1etDL5My574tHZQ0lIoQR/fWB4LCMvdtS', 'staff', 'Prem');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_files`
--
ALTER TABLE `admin_files`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `airlines`
--
ALTER TABLE `airlines`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKknyuo373wgg0n572t0aj4qs9g` (`input_data_id`);

--
-- Indexes for table `airlines_with_logo`
--
ALTER TABLE `airlines_with_logo`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `airportcodes`
--
ALTER TABLE `airportcodes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `attendence`
--
ALTER TABLE `attendence`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customerbook`
--
ALTER TABLE `customerbook`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKkkkaubfaw8ynb01n04vg74pdm` (`customermail`),
  ADD UNIQUE KEY `UKrb846lqkc5dh8yvk3wwgtxf6r` (`customernumber`);

--
-- Indexes for table `customerotherdetails`
--
ALTER TABLE `customerotherdetails`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customers_hotel_details`
--
ALTER TABLE `customers_hotel_details`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKm0f0apqq3tcwyd3rfw762qafj` (`bookingid`);

--
-- Indexes for table `customer_flight_details`
--
ALTER TABLE `customer_flight_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `dailyreport`
--
ALTER TABLE `dailyreport`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hotelguest`
--
ALTER TABLE `hotelguest`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKl5to8jloaphl9wxte26gxb3te` (`hotel_id`);

--
-- Indexes for table `passengername`
--
ALTER TABLE `passengername`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcpkvx6cp9goqomh3ui3759pe0` (`flight_id`);

--
-- Indexes for table `reminder`
--
ALTER TABLE `reminder`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `resourcelink`
--
ALTER TABLE `resourcelink`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `task_list`
--
ALTER TABLE `task_list`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKlemr9fkllhw9b0l7yfj83qrcm` (`empid`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_files`
--
ALTER TABLE `admin_files`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `airlines`
--
ALTER TABLE `airlines`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `airlines_with_logo`
--
ALTER TABLE `airlines_with_logo`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `airportcodes`
--
ALTER TABLE `airportcodes`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=74;

--
-- AUTO_INCREMENT for table `attendence`
--
ALTER TABLE `attendence`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=291;

--
-- AUTO_INCREMENT for table `customerbook`
--
ALTER TABLE `customerbook`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `customerotherdetails`
--
ALTER TABLE `customerotherdetails`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `customers_hotel_details`
--
ALTER TABLE `customers_hotel_details`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `customer_flight_details`
--
ALTER TABLE `customer_flight_details`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `dailyreport`
--
ALTER TABLE `dailyreport`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `hotelguest`
--
ALTER TABLE `hotelguest`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `passengername`
--
ALTER TABLE `passengername`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `reminder`
--
ALTER TABLE `reminder`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=93;

--
-- AUTO_INCREMENT for table `resourcelink`
--
ALTER TABLE `resourcelink`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `task_list`
--
ALTER TABLE `task_list`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `airlines`
--
ALTER TABLE `airlines`
  ADD CONSTRAINT `FKknyuo373wgg0n572t0aj4qs9g` FOREIGN KEY (`input_data_id`) REFERENCES `customer_flight_details` (`id`);

--
-- Constraints for table `hotelguest`
--
ALTER TABLE `hotelguest`
  ADD CONSTRAINT `FKl5to8jloaphl9wxte26gxb3te` FOREIGN KEY (`hotel_id`) REFERENCES `customers_hotel_details` (`id`);

--
-- Constraints for table `passengername`
--
ALTER TABLE `passengername`
  ADD CONSTRAINT `FKcpkvx6cp9goqomh3ui3759pe0` FOREIGN KEY (`flight_id`) REFERENCES `customer_flight_details` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
