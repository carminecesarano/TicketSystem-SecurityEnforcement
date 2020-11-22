-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Giu 18, 2020 alle 09:46
-- Versione del server: 10.4.11-MariaDB
-- Versione PHP: 7.2.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ticket_system`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `notifiche`
--

CREATE TABLE `notifiche` (
  `id` int(11) NOT NULL,
  `messaggio` varchar(250) NOT NULL,
  `letta` tinyint(1) NOT NULL,
  `id_ticket` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `notifiche`
--

INSERT INTO `notifiche` (`id`, `messaggio`, `letta`, `id_ticket`) VALUES
(183, 'Il ticket no. 110 è stato preso in carico.', 0, 110),
(184, 'Il ticket no. 111 è stato preso in carico.', 0, 111),
(185, 'Il ticket no. 111 è stato chiuso.', 0, 111),
(186, 'Il ticket no. 109 è stato preso in carico.', 0, 109),
(187, 'Il ticket no. 112 è stato preso in carico.', 0, 112),
(188, 'Il ticket no. 112 è stato chiuso.', 0, 112),
(189, 'Il ticket no. 110 è stato chiuso.', 0, 110),
(190, 'Il ticket no. 113 è stato preso in carico.', 0, 113),
(191, 'Il ticket no. 116 è stato preso in carico.', 0, 116),
(192, 'Il ticket no. 116 è stato chiuso.', 0, 116),
(193, 'Il ticket no. 117 è stato preso in carico.', 0, 117),
(194, 'Il ticket no. 117 è stato chiuso.', 0, 117),
(195, 'Il ticket no. 109 è stato chiuso.', 0, 109),
(198, 'Il ticket no. 118 è stato preso in carico.', 0, 118);

-- --------------------------------------------------------

--
-- Struttura della tabella `ruoli`
--

CREATE TABLE `ruoli` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `ruoli`
--

INSERT INTO `ruoli` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_CLIENTE'),
(3, 'ROLE_OPERATORE');

-- --------------------------------------------------------

--
-- Struttura della tabella `statoticket`
--

CREATE TABLE `statoticket` (
  `id` int(1) NOT NULL,
  `name` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `statoticket`
--

INSERT INTO `statoticket` (`id`, `name`) VALUES
(1, 'APERTO'),
(2, 'IN_LAVORAZIONE'),
(3, 'CHIUSO');

-- --------------------------------------------------------

--
-- Struttura della tabella `ticket`
--

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL,
  `title` varchar(250) NOT NULL,
  `description` text NOT NULL,
  `created_date` date NOT NULL,
  `status` int(1) DEFAULT NULL,
  `cliente_id` int(11) NOT NULL,
  `operatore_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `ticket`
--

INSERT INTO `ticket` (`id`, `title`, `description`, `created_date`, `status`, `cliente_id`, `operatore_id`) VALUES
(109, 'Problema memoria RAM', 'Salve, ho acquistato un banco di memoria RAM LPX Vengeance da 16Gb ed ho riscontrato molti problemi effettuando un memtest.\r\nChiedo gentilmente supporto.', '2020-06-16', 3, 2, 8),
(110, 'Problema Monitor', 'Salve,\r\nho riscontrato dei problemi di luminosit� con il monitor Philips tx98 appena acquistato. \r\nVolevo chiedervi, dato che � il secondo problema riscontrato,\r\nma fanno cos� schifo i vostri prodotti?', '2020-06-16', 3, 2, 8),
(111, 'Problema latenza rete', 'Salve, ho riscontrato dei problemi di latenza. Come posso risolvere?', '2020-06-16', 3, 2, 8),
(112, 'Problema latenza rete', 'Ho ancora quei problemi alla rete. Quando mi risponderete??', '2020-06-16', 3, 2, 8),
(113, 'Problema scheda madre', 'Salve, vorrei segnalare ancora un altro problema.\r\nAnche la scheda madre � rotta! Come faccio?', '2020-06-16', 3, 2, 8),
(114, 'Problema alla tastiera', 'Salve, ho riscontrato un problema alla tastiera.', '2020-06-16', 3, 2, 8),
(115, 'Problema scheda audio', 'Salve, ho un problema alla scheda audio. Come posso risolvere?', '2020-06-16', 2, 2, 8),
(116, 'Problema alla scheda audio', 'Salve, ho un problema alla scheda audio. Come potrei risolvere?', '2020-06-16', 3, 2, 8),
(117, 'Problema scheda audio', 'Salve, ho un problema alla scheda audio. Come posso risolvere?', '2020-06-16', 3, 2, 8),
(118, 'Problema latenza rete', 'Problema', '2020-06-16', 2, 2, 8);

-- --------------------------------------------------------

--
-- Struttura della tabella `utenti`
--

CREATE TABLE `utenti` (
  `id` int(11) NOT NULL,
  `username` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `full_name` varchar(250) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `email` varchar(250) NOT NULL,
  `ruolo_id` int(11) NOT NULL,
  `phone` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `utenti`
--

INSERT INTO `utenti` (`id`, `username`, `password`, `full_name`, `status`, `email`, `ruolo_id`, `phone`) VALUES
(2, 'c01', '$2a$10$Qlg8aHiUvrESJQj9VKeK9uoiFpq/VEXi1GsPBeAym5hBvA3RH43PG', 'Alessandro Castaldo', 1, 'alessandro.castaldo@hotmail.com', 2, '87621834761'),
(3, 'c02', '$2a$10$Qlg8aHiUvrESJQj9VKeK9uoiFpq/VEXi1GsPBeAym5hBvA3RH43PG', 'Carmine Cesarano', 1, 'c.cesarano@hotmail.it', 2, '3402178992'),
(8, 'o01', '$2a$10$YtrpkQmWGIY4SwhMHk54POtrahZp8BsC.eklNmZiwNV8/EEfh/4uy', 'Roberto Allocca', 1, 'roberto.allocca@gmail.com', 3, '1234567'),
(9, 'o02', '$2a$10$IwddBCT8sFdlcbllmMjZFOibNJN.dpz3I.amVKAPVl8K8IFvQ2jOq', 'Topo Gigio', 0, 'topo.gigio@hotmail.com', 3, '124124124');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `notifiche`
--
ALTER TABLE `notifiche`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_ticket` (`id_ticket`);

--
-- Indici per le tabelle `ruoli`
--
ALTER TABLE `ruoli`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `statoticket`
--
ALTER TABLE `statoticket`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cliente_id` (`cliente_id`) USING BTREE,
  ADD KEY `operatore_id` (`operatore_id`) USING BTREE,
  ADD KEY `status` (`status`);

--
-- Indici per le tabelle `utenti`
--
ALTER TABLE `utenti`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `role_id` (`ruolo_id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `notifiche`
--
ALTER TABLE `notifiche`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=199;

--
-- AUTO_INCREMENT per la tabella `ruoli`
--
ALTER TABLE `ruoli`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `statoticket`
--
ALTER TABLE `statoticket`
  MODIFY `id` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=124;

--
-- AUTO_INCREMENT per la tabella `utenti`
--
ALTER TABLE `utenti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `notifiche`
--
ALTER TABLE `notifiche`
  ADD CONSTRAINT `fk_ticket_id` FOREIGN KEY (`id_ticket`) REFERENCES `ticket` (`id`);

--
-- Limiti per la tabella `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_ibfk_4` FOREIGN KEY (`cliente_id`) REFERENCES `utenti` (`id`),
  ADD CONSTRAINT `ticket_ibfk_5` FOREIGN KEY (`operatore_id`) REFERENCES `utenti` (`id`),
  ADD CONSTRAINT `ticket_ibfk_6` FOREIGN KEY (`status`) REFERENCES `statoticket` (`id`);

--
-- Limiti per la tabella `utenti`
--
ALTER TABLE `utenti`
  ADD CONSTRAINT `utenti_ibfk_1` FOREIGN KEY (`ruolo_id`) REFERENCES `ruoli` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
