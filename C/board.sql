create table board(
number int(5) AUTO_INCREMENT PRIMARY KEY, 
title varchar(20), 
id varchar(20), 
content varchar(100),
fmenu varchar(20),
foreign key(fmenu) references foodlist(menu)
);

insert into board values(null,'I feel so happy','Yang','I hope every others to be happy too','onion') ;
insert into board values(null,'Notice','admin','This is a notice','original');
insert into board values(null,'sdfsdf','admin','sdfsdfsdfsd','sweetpotato');
insert into board values(null,'Value of living life','admin','You should all know thataaa','potato');
insert into board values(null,'what is living?','Yang','HowdieHowdie','jajang');
insert into board values(null,'Benefits of living','Lee','Benefits','friedrice');
insert into board values(null,'Get to know you','Song','It is nice to know you','crab');
insert into board values(null,'sadfadsfff','admin','aaaa','honey');


ALTER TABLE board AUTO_INCREMENT=1;

