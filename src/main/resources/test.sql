-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2014 年 02 月 28 日 23:08
-- 服务器版本: 5.5.35
-- PHP 版本: 5.3.10-1ubuntu3.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `test`
--

-- --------------------------------------------------------

--
-- 表的结构 `MENU`
--

CREATE TABLE IF NOT EXISTS `MENU` (
  `ID` char(36) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `FID` char(36) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `URL` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `NAME` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `NUM` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `MENU`
--

INSERT INTO `MENU` (`ID`, `FID`, `URL`, `NAME`, `NUM`) VALUES
('be96ac19-9b06-11e3-9124-00235af07f9f', '', '/main/index', '首页', 1);

-- --------------------------------------------------------

--
-- 表的结构 `USER`
--

CREATE TABLE IF NOT EXISTS `USER` (
  `ID` char(36) NOT NULL,
  `NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `PASSWORD` char(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- 转存表中的数据 `USER`
--

INSERT INTO `USER` (`ID`, `NAME`, `PASSWORD`) VALUES
('10000', 'SYSTEM_ADMIN', '0A113EF6B61820DAA5611C870ED8D5EE');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
