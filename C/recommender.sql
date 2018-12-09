create table recommender(
fmenu varchar(20),
cook varchar(10),
sauce boolean,
taste varchar(20),
score double(5,1),
count int(10),
foreign key(fMenu) references foodlist(menu)
);

select * from recommender;

insert into recommender values('onion','fried',true,'sweet','4.1','26');
insert into recommender values('sweet','fried',	false,	'sweet',	'2.1',	'15');
insert into recommender values('fried','fried',	false,	'salty',	'3.2',	'30');
insert into recommender values('sour','fried',	true,	'sour',	'4.3',	'21');
insert into recommender values('original','baked',	true,	'salty',	'3.8',	'13');
insert into recommender values('seasoned','fried',	true,	'salty',	'3.3',	'25');
insert into recommender values('olive','baked',	false,	'salty',	'4.7',	'35');
insert into recommender values('honey','baked',	true,	'sweet',	'1.3',	'10');
insert into recommender values('cheese','baked',	false,	'salty',	'3.2',	'45');
insert into recommender values('steak',	'baked',	true,	'sweet',	'2.6',	'33');
insert into recommender values('bulkafalse',	'fired',	true,	'hot',	'4.8',	'7');
insert into recommender values('sweetpotato',	'baked',	true,	'sweet',	'4.7',	'33');
insert into recommender values('potato',	'baked',	false,	'salty',	'0.8',	'13');
insert into recommender values('albolo',	'baked',	false,	'sour',	'5',	'23');
insert into recommender values('shrimp',	'baked',	true,	'bitter',	'3.3',	'44');
insert into recommender values('crab',	'fired',	false,	'bitter',	'2.3',	'46');
insert into recommender values('corn',	'baked',	false,	'salty',	'3.7',	'10');
insert into recommender values('jajang',	'boiled',	true,	'salty',	'2.1',	'38');
insert into recommender values('jjambbong',	'boiled',	false,	'salty',	'2.7',	'7');
insert into recommender values('tangsuyuk',	'fried',	true,	'sweet',	'3.5',	'10');
insert into recommender values('yusansool',	'boiled',	true,	'sour',	'4.9',	'40');
insert into recommender values('woodong',	'boiled',	false,	'salty',	'4.5',	'15');
insert into recommender values('jajangrice',	'boiled',	true,	'sweet',	'1.7',	'35');
insert into recommender values('friedrice',	'boiled',	false,	'bitter',	'3.8',	'28');
insert into recommender values('palbochae',	'boiled',	true,	'bitter',	'2.9',	'17');
insert into recommender values('jabchaerice',	'boiled',	false,	'salty',	'4.6',	'12');


insert into recommender values();