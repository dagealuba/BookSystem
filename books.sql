-- 创建触发器，分别实现借书和还书时自动更新图书信息的在册数量；
drop trigger if exists `borrow`;
DELIMITER $$
USE `booksystem`$$
create trigger `borrow`
after insert on `booksystem`.`borrow`
for each row
begin 
update `booksystem`.`books` set `now_num`=`now_num` -1
where `bookId`=new.`bookId`;
END$$

DELIMITER ;

drop trigger if exists `return`;
DELIMITER $$
USE `booksystem`$$
create trigger `return`
after  on `booksystem`.`borrow`
for each row
begin 
update `booksystem`.`books` set `now_num`=`now_num` +1
where `bookId`=new.`bookId`;
END$$

DELIMITER ;

-- 触发器关于finishTime  DML
drop trigger if exists `finishTime_input`;
DELIMITER $$
USE `booksystem`$$
create trigger `finishTime_input`
before insert on `booksystem`.`borrow`
for each row
begin
set new.`finishTime`=date_add(new.`startTime`, interval 1 MONTH);
END$$

