show databases;

select * from foodlist;
insert into 

drop tables foodlist;

create table foodlist(
	location VARCHAR(20),
	sort VARCHAR(20),
	rest VARCHAR(20),
	menu VARCHAR(20) primary key,
	price INT
); 


insert into foodlist values("seoul","chicken","bhc","matchoking",16000);
insert into foodlist values("seoul","chicken","bbq","goldOlive",16000);
insert into foodlist values("seoul","chicken","kfc","original",16000);
insert into foodlist values("seoul","chicken","bbq","gangjung",15900);
insert into foodlist values("seoul","chicken","bhc","sunsalbbulingcle",15900);
insert into foodlist values("seoul","chicken","bhc","sunFlower",16000);
insert into foodlist values("seoul","chicken","kfc","red",16000);
insert into foodlist values("seoul","chicken","kfc","hotKrispy",16000);
insert into foodlist values("seoul","chicken","bbq","jameica",16000);
insert into foodlist values("seoul","pizza","domino","wing",20000);
insert into foodlist values("seoul","pizza","domino","hotChickenGold",21000);
insert into foodlist values("seoul","pizza","domino","gold",22000);
insert into foodlist values("seoul","pizza","pizzahut","shirimp",22000);
insert into foodlist values("seoul","pizza","pizzahut","crunchCube",23000);
insert into foodlist values("seoul","pizza","pizzahut","doubleCheese",25000);
insert into foodlist values("seoul","pizza","ogurice","realbulgoki",23500);
insert into foodlist values("seoul","pizza","ogurice","bacon",23500);
insert into foodlist values("seoul","pizza","ogurice","combie",24500);
insert into foodlist values("seoul","chinese","kyodong","friedRice",6000);
insert into foodlist values("seoul","chinese","kyodong","jjambbongRice",6000);
insert into foodlist values("seoul","chinese","kyodong","jjajangRice",6000);
insert into foodlist values("seoul","chinese","backs","eggRice",6000);
insert into foodlist values("seoul","chinese","backs","kimchi",5000);
insert into foodlist values("seoul","chinese","backs","jabchae",5500);
insert into foodlist values("seoul","chinese","hongkong","jjajang",5000);
insert into foodlist values("seoul","chinese","hongkong","jjanbbong",6000);
insert into foodlist values("seoul","chinese","hongkong","ganJjajang",6000);


drop table foodlist;


