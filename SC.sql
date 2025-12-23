CREATE DATABASE IF NOT EXISTS `SC_ASSIGNMENT`;
USE `SC_ASSIGNMENT`;

CREATE TABLE `seiyuu` (
  `SEIYUU_ID` INT AUTO_INCREMENT,
  `BIRTHDAY` DATE,
  `FIRST_NAME` VARCHAR(15),
  `LAST_NAME` VARCHAR(15),
  `AGE` INT,
  `SEX` CHAR(1), CONSTRAINT sex_check CHECK (sex IN ('M', 'F')),
  `AGENCY` VARCHAR(50),
  `MUSIC_LABEL` VARCHAR(50),
  `JAPANESE_NAME` VARCHAR(50),
  `SEIYUU_PICS` VARCHAR(50),
  `TWITTER` VARCHAR(50),
  `INSTAGRAM` VARCHAR(50),
  `NOTES` VARCHAR(50),
  PRIMARY KEY (`SEIYUU_ID`)
);

CREATE TABLE `staff` (
  `STAFF_ID` INT AUTO_INCREMENT,
  `FIRST_NAME` VARCHAR(15),
  `LAST_NAME` VARCHAR(15),
  `POSITION` VARCHAR(10),
  PRIMARY KEY (`STAFF_ID`)
);

CREATE TABLE `staff_assignment` (
  `STAFF_ID` INT,
  `SEIYUU_ID` INT UNIQUE,
  PRIMARY KEY (`STAFF_ID`, `SEIYUU_ID`),
  FOREIGN KEY (`SEIYUU_ID`)
      REFERENCES `seiyuu`(`SEIYUU_ID`),
  FOREIGN KEY (`STAFF_ID`)
      REFERENCES `staff`(`STAFF_ID`)
);

-- For December
INSERT INTO staff (FIRST_NAME, LAST_NAME, POSITION) VALUES 
('Ken', 'Pueyo', 'SC Manager'),
('Jerome', 'Valatero', 'Moderator'),
('Ghifary', NULL, 'Moderator'),
('Wednesday', 'Alcala', 'Staff'),
('Mynhi', NULL, 'Staff'),
('Pearse', 'Smyth', 'Staff'),
('Marcus', 'Timothy', 'Staff');

INSERT INTO seiyuu (BIRTHDAY, FIRST_NAME, LAST_NAME, AGE, SEX, AGENCY, MUSIC_LABEL, JAPANESE_NAME) VALUES
('1995-12-02', 'Inori', 'Minase', 30, 'F', 'Axl One', 'King Records','水瀬 いのり'),
('1949-12-02', 'Shūichi', 'Ikeda', 76, 'M', 'Haikyō', NULL, '池田 秀一'),
('1994-12-3', 'Yū', 'Serizawa', 31, 'F', '81 Produce',	'Avex', '芹澤 優'),
('1965-12-3', 'Yūya', 'Uchida', 60, 'M', 'Haiyuza Theatre Company', NULL, '内田 夕夜'),
('1972-12-4', 'Yūko', 'Miyamura', 53, 'F', 'JAE Promotion', NULL, '宮村 優'),
('2023-12-4', 'Saki', 'Miyashita', 2, 'F', 'Haikyō', NULL, '宮下 早紀'),
('1987-12-04', 'Miho', 'Arakawa', 38, 'F', 'Haikyō', NULL, '荒川 美穂'),
('1961-12-04', 'Chafurin', '',	64, 'M', 'Office Osawa', NULL, '茶風林'),
('1980-12-05', 'Shizuka', 'Itō', 45, 'F', 'Ken Production', NULL, '伊藤 静'),
('1988-12-06', 'Nobunaga', 'Shimazaki', 37, 'M', 'Aoni Production', NULL, '島崎 信長'),
('1975-12-06', 'Sayaka', 'Ohara', 50, 'F', 'Haikyō', NULL, '大原 さやか'),
('1994-12-06', 'Miyuri', 'Shimabukuro', 31,	'F', 'Office Osawa', NULL, '島袋 美由利'),
('1998-12-06', 'Eri', 'Yukimura', 27, 'F', 'VIMS', NULL, '幸村 恵理'),
('2023-12-07', 'Yuki', 'Yagi', 2, 'F', 'Production Ace', NULL, '八木 侑紀'),
('1967-12-08', 'Kotono', 'Mitsuishi', 58, 'F', 'Arts Vision', NULL,	'三石 琴乃'),
('1990-12-08', 'Megumi', 'Toda', 35, 'F', 'Kenyu Office', NULL,	'戸田 めぐみ'),
('1984-12-09', 'Mayuki', 'Makiguchi', 41, 'F', '81 Produce', NULL, '牧口 真幸'),
('1985-12-10', 'Emi', 'Nitta', 40, 'F', 'Difference', NULL,	'新田 恵海'),
('1975-12-12', 'Houko', 'Kuwashima', 50, 'F', 'Aoni Production', NULL, '桑島 法子'),
('1963-12-12', 'Ai', 'Orikasa',	62,	'F', 'Axlone', NULL, '折笠 愛'),
('1969-12-13', 'Hideo', 'Ishikawa', 56, 'M', 'Aoni Production', NULL, '石川 英郎'),
('1986-12-13', 'Hiromi', 'Igarashi', 39, 'F', 'Mausu Promotion', NULL, '五十嵐 裕美'),
('1988-12-14', 'Ayumu', 'Murase', 37, 'M', 'VIMS', NULL, '村瀬 歩'),
('1977-12-14', 'Haruko', 'Momoi', 48, 'F', 'Avex Trax', NULL, '桃井 はるこ'),
('1993-12-14', 'Yūya', 'Hozumi', 32, 'M', 'Ken Production', NULL, '保住 有哉'),
('1957-12-15', 'Chō', '', 68, 'M', 'Haikyō', NULL, 'チョー'),
('1975-12-15', 'Haruna', 'Ikezawa', 50, 'F', 'Across Entertainment', NULL,	'池澤 春菜'),
('1987-12-16', 'Hisako', 'Kanemoto', 38, 'F', 'Production Baoba', 'Victor Entertainment', '金元 寿子'),
('2023-12-16', 'Akira', 'Sekine', 2, 'F', 'Aptepro', NULL,	'関根 明良'),
('1960-12-17', 'Tarako', '',	65,	'F', 'Troubadour Musique Office', NULL,	'たらこ'),
('1993-12-17', 'Madoka', 'Asahina', 32, 'F', '81 Produce', NULL, '朝日奈 丸佳'),
('1993-12-17', 'Rika', 'Kinugawa',	32,	'F', 'Haikyō', NULL, '衣川 里佳'),
('1963-12-18', 'Rikiya', 'Koyama',	62, 'M', 'Haiyuza Theatre Company', NULL, '小山 力也'),
('1992-12-18', 'Taito', 'Ban', 33,	'M', 'Office Osawa', NULL, '坂 泰斗'),
('1987-12-18', 'Shō', 'Karino', 38,	'M', 'Haikyō', NULL, '狩野 翔'),
('2000-12-18', 'Moe', 'Kahara', 25,	'F', 'Holy Peak', NULL, '佳原 萌枝'),
('1991-12-19', 'Sumire', 'Uesaka', 34, 'F', 'Voice Kit', 'King Amusement Creative', '上坂すみれ'),
('1957-12-19', 'Jūrōta', 'Kosugi', 68, 'M', 'Office Osawa', NULL, '小杉 十郎太'),
('2024-12-19', 'Yukino', 'Shuto', 1, 'F', 'Raccoon Dog', NULL, '首藤 志奈'),
('1996-12-20', 'Kana', 'Ichinose',	29,	'F', 'Sigma Seven', NULL, '市ノ瀬 加那'),
('1990-12-20', 'Minami', 'Takahashi', 35, 'F', 'Haikyō', NULL,	'髙橋 ミナミ'),
('1983-12-20', 'Takuma', 'Terashima', 42, 'M', 'Axlone', 'Lantis', '寺島 拓篤'),
('1966-12-20', 'Yumi', 'Tōma', 59, 'F', 'ALLURE&Y', NULL, '冬馬 由美'),
('1987-12-20', 'Chiharu', 'Sawashiro', 38,	'M', 'Stay Luck', NULL, '沢城 千春'),
('1999-12-22', 'Tomori', 'Kusunoki', 26, 'F', 'Sony Music Artists', 'Sacra Music', '楠木ともり'),
('1990-12-22', 'Chika', 'Anzai', 35, 'F', 'Avex', NULL, '安済 知佳'),
('1998-12-22', 'Ryōta', 'Suzuki', 27, 'M', 'TMS Music', NULL, '鈴木 崚汰'),
('2003-12-22', 'Shuri', '', 22, 'F', NULL, NULL, '朱李'),
('1997-12-22', 'Tomomi', 'Mineuchi', 28, 'F', 'I\'m Enterprise', NULL, '嶺内 ともみ'),
('1997-12-23', 'Sayaka', 'Harada', 27, 'F', 'V-FORK', NULL,	'原田 彩楓'),
('1998-12-23', 'Hinata', 'Satō', 26, 'F', 'Amuse', NULL,	'佐藤 日向'),
('1982-12-24', 'Kakihara', 'Tetsuya', 42, 'M', 'Zynchro', 'Kiramune', '柿原 徹也'),
('1989-12-24', 'Kitsune', 'Kagura', 35,	'M', 'Freelance', NULL, '花倉 桔道'),
('1991-12-25', 'Aimi', '', 33, 'F', 'Hibiki', 'King Records', '愛美'),
('1986-12-25', 'Aya', 'Suzaki', 38,	'F', 'I\'m Enterprise', NULL, '洲崎 綾'),
('1993-12-25', 'Akari', 'Kageyama', 31,	'F', 'IAM Agency', NULL, '影山 灯'),
('1989-12-26', 'Sora', 'Tokui', 35,	'F', 'Avex Pictures', NULL, '徳井 青空'),
('1989-12-27', 'Maaya', 'Uchida', 35, 'F', 'I\'m Enterprise', 'Pony Canyon', '内田 真礼'),
('1974-12-27', 'Fumiko', 'Orikasa',	50, 'F', 'Atomic Monkey', NULL,	'折笠 富美子'),
('2023-12-30', 'Yuka', 'Nukui',	1, 'F', 'Air Agency', NULL, '貫井 柚佳'),
('1995-12-31', 'Maki', 'Kawase', 29, 'F', 'Arts Vision', NULL, '河瀬 茉希'),
('1989-12-31', 'Ayaka', 'Fukuhara', 35,	'F', 'Across Entertainment', NULL, '福原 綾香'),
('2023-12-31', 'Riho', 'Sugiyama', 1, 'F', 'Mausu Promotion', NULL,	'杉山 里穂'),
('2023-12-31', 'Yukari', 'Anzai', 1, 'F', 'Swallow', NULL, '安齋 由香'),
('1998-12-31', 'Akane', 'Yonezawa', 26,	'F', 'Freelance', NULL,	'米澤茜');

-- To be done in the java app
INSERT INTO staff_assignment VALUES
(1, 1),
(6, 2),
(5, 3),
(4, 4),
(2, 5),
(1, 6),
(7, 7),
(3, 8),
(2, 9),
(4, 10),
(1, 11),
(3, 12),
(1, 13),
(6, 14),
(4, 15),
(7, 16),
(6, 17),
(3, 18),
(5, 19),
(7, 20),
(4, 21),
(7, 22),
(5, 23),
(6, 24),
(2, 25),
(6, 26),
(4, 27),
(6, 28),
(2, 29),
(3, 31),
(6, 32),
(3, 33),
(5, 34),
(7, 35),
(2, 36),
(3, 37),
(2, 38),
(4, 39),
(1, 40),
(2, 41),
(5, 42),
(7, 43),
(3, 44),
(7, 45),
(2, 46),
(5, 47),
(4, 48),
(6, 50),
(3, 51),
(4, 52),
(5, 53),
(1, 54),
(7, 55),
(5, 56),
(1, 57),
(1, 58),
(1, 59),
(1, 60),
(1, 61),
(1, 62),
(1, 63),
(1, 64),
(1, 65);

UPDATE seiyuu SET NOTES = 'Real Name: Hirotaka Shimazawa' WHERE seiyuu_id = 8;
UPDATE seiyuu SET NOTES = 'TogeToge' WHERE seiyuu_id = 48;
UPDATE seiyuu SET NOTES = 'Retired' WHERE seiyuu_id = 49;

-- Queries
SELECT * FROM staff;
SELECT * FROM staff_assignment;
SELECT * FROM seiyuu;

-- Assignment Table
SELECT s.first_name AS 'Assigned', su.Birthday, CONCAT(su.first_name, ' ', su.last_name) AS Name, su.Age, su.Sex, su.Agency, su.Music_Label, su.Japanese_Name, su.Seiyuu_Pics, su.Twitter, su.Instagram, su.Notes 
FROM staff_assignment sa JOIN staff s ON sa.staff_id = s.staff_id
						JOIN seiyuu su ON sa.seiyuu_id = su.seiyuu_id 
-- WHERE sa.staff_id = 7
ORDER BY su.seiyuu_id;