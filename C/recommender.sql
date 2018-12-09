create table recommender(
fmenu varchar(20),
cook varchar(10),
sauce char(2),
taste varchar(10),
score double(5,1),
count int(10),
foreign key(fMenu) references foodlist(menu)
);

select * from recommender;

insert into recommender values('onion','fried','o','sweet','4.1','26');
insert into recommender values('sweet','fried',	'x',	'sweet',	'2.1',	'15');
insert into recommender values('fried','fried',	'x',	'salty',	'3.2',	'30');
insert into recommender values('sour','fried',	'o',	'sour',	'4.3',	'21');
insert into recommender values('original','baked',	'o',	'salty',	'3.8',	'13');
insert into recommender values('seasoned','fried',	'o',	'salty',	'3.3',	'25');
insert into recommender values('olive','baked',	'x',	'salty',	'4.7',	'35');
insert into recommender values('honey','baked',	'o',	'sweet',	'1.3',	'10');
insert into recommender values('cheese','baked',	'x',	'salty',	'3.2',	'45');
insert into recommender values('steak',	'baked',	'o',	'sweet',	'2.6',	'33');
insert into recommender values('bulkano',	'fired',	'o',	'hot',	'4.8',	'7');
insert into recommender values('sweetpotato',	'baked',	'o',	'sweet',	'4.7',	'33');
insert into recommender values('potato',	'baked',	'x',	'salty',	'0.8',	'13');
insert into recommender values('albolo',	'baked',	'x',	'sour',	'5',	'23');
insert into recommender values('shrimp',	'baked',	'o',	'bitter',	'3.3',	'44');
insert into recommender values('crab',	'fired',	'x',	'bitter',	'2.3',	'46');
insert into recommender values('corn',	'baked',	'x',	'salty',	'3.7',	'10');
insert into recommender values('jajang',	'boiled',	'o',	'salty',	'2.1',	'38');
insert into recommender values('jjambbong',	'boiled',	'x',	'salty',	'2.7',	'7');
insert into recommender values('tangsuyuk',	'fried',	'o',	'sweet',	'3.5',	'10');
insert into recommender values('yusansool',	'boiled',	'o',	'sour',	'4.9',	'40');
insert into recommender values('woodong',	'boiled',	'x',	'salty',	'4.5',	'15');
insert into recommender values('jajangrice',	'boiled',	'o',	'sweet',	'1.7',	'35');
insert into recommender values('friedrice',	'boiled',	'x',	'bitter',	'3.8',	'28');
insert into recommender values('palbochae',	'boiled',	'o',	'bitter',	'2.9',	'17');
insert into recommender values('jabchaerice',	'boiled',	'x',	'salty',	'4.6',	'12');


insert into recommender values();