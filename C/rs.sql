create table rs(
mid varchar(20),
fMenu varchar(20),
foreign key(fMenu) references foodlist(menu),
foreign key(mid) references member(id)
);