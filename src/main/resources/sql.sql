/* 书籍首页的sql */
CREATE TABLE `index_book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_url` varchar(255) NOT NULL,
  `book_name` varchar(255) NOT NULL,
  `enabled_flag` int(11) NOT NULL,
  `up_time` varchar(30) NOT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

/*书籍中转页*/
CREATE TABLE `transition_book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_url` varchar(225) NOT NULL,
  `book_name` varchar(32) NOT NULL,
  `book_author` varchar(32) NOT NULL,
  `up_time` varchar(32) NOT NULL,
  `enabled_flag` int(11) NOT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*书籍章节页*/
CREATE TABLE `details_book` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_details_id` int(11) NOT NULL,
  `book_chapter` varchar(225) NOT NULL,
  `book_chapter_url` varchar(225) NOT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


