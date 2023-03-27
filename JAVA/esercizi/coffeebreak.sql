-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mar 27, 2023 alle 09:34
-- Versione del server: 10.4.27-MariaDB
-- Versione PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `coffeebreak`
--
CREATE DATABASE IF NOT EXISTS `coffeebreak` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `coffeebreak`;

-- --------------------------------------------------------

--
-- Struttura della tabella `coffees`
--

DROP TABLE IF EXISTS `coffees`;
CREATE TABLE `coffees` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` decimal(4,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `coffees`
--

INSERT INTO `coffees` (`id`, `name`, `price`) VALUES
(1, 'Lavazza', '1.50'),
(2, 'Nespresso', '1.00');

-- --------------------------------------------------------

--
-- Struttura della tabella `sells`
--

DROP TABLE IF EXISTS `sells`;
CREATE TABLE `sells` (
  `id` bigint(20) NOT NULL,
  `date_sell` date NOT NULL,
  `quantity` int(11) NOT NULL,
  `id_coffee` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `sells`
--

INSERT INTO `sells` (`id`, `date_sell`, `quantity`, `id_coffee`) VALUES
(3, '2023-02-02', 120, 1),
(4, '2023-03-26', 25, 2),
(5, '2023-03-27', 138, 2),
(6, '2023-03-27', 27, 1);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `coffees`
--
ALTER TABLE `coffees`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `sells`
--
ALTER TABLE `sells`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_coffee` (`id_coffee`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `coffees`
--
ALTER TABLE `coffees`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `sells`
--
ALTER TABLE `sells`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `sells`
--
ALTER TABLE `sells`
  ADD CONSTRAINT `sells_ibfk_1` FOREIGN KEY (`id_coffee`) REFERENCES `coffees` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
