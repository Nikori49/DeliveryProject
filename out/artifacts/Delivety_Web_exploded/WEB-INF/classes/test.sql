USE delivery_company;
INSERT INTO routes
VALUES (DEFAULT,'Изюм','Харьков',124);
INSERT INTO routes
VALUES (DEFAULT,'Харьков','Изюм',124);
INSERT INTO routes
VALUES (DEFAULT,'Киев','Харьков',479);
INSERT INTO routes
VALUES (DEFAULT,'Харьков','Киев',479);
INSERT INTO routes
VALUES (DEFAULT,'Киев','Чернигов',142);
INSERT INTO routes
VALUES (DEFAULT,'Чернигов','Киев',142);
INSERT INTO routes
VALUES (DEFAULT,'Киев','Винница',263);
INSERT INTO routes
VALUES (DEFAULT,'Винница','Киев',263);
INSERT INTO routes
VALUES (DEFAULT,'Киев','Львов',534);
INSERT INTO routes
VALUES (DEFAULT,'Львов','Киев',534);
INSERT INTO routes
VALUES (DEFAULT,'Полтава','Харьков',143);
INSERT INTO routes
VALUES (DEFAULT,'Харьков','Полтава',143);
INSERT INTO routes
VALUES (DEFAULT,'Киев','Одесса',477);
INSERT INTO routes
VALUES (DEFAULT,'Одесса','Киев',477);

INSERT INTO users
VALUES (DEFAULT,'bosskachalki@gmail.com','+3807777777','Владелец','Заводов','HozyainBarin',MD5('adminpass1'),'admin');


INSERT INTO users
VALUES (DEFAULT,'manager2@gmail.com','+3809999999','Генадий','Бэкфлипенко','GBManager',MD5('managerpass1'),'manager');


INSERT INTO users
VALUES (DEFAULT,'clientno1@gmail.com','+3801111111','Первый','Клиент','ClientN1',MD5('clientpass1'),'client');


INSERT INTO users
VALUES (DEFAULT,'clientno2@gmail.com','+3802222222','Второй','Клиент','ClientN2',MD5('clientpass2'),'client');

INSERT INTO users
VALUES (DEFAULT,'clientno3@gmail.com','+3803333333','Third','Client','ClientN3',MD5('clientpass3'),'client');

INSERT INTO users
VALUES (DEFAULT,'clientno4@gmail.com','+3804444444','Четвертый','Клиент','ClientN4',MD5('clientpass4'),'client');

INSERT INTO tariffs
VALUES (DEFAULT,'Бюджетный Ближний',1.25,2,1.5,800,16,'до 150км');

INSERT INTO tariffs
VALUES (DEFAULT,'Бюджетный Средний',1.25,2,1.5,800,18,'от 150км до 500км');

INSERT INTO tariffs
VALUES (DEFAULT,'Стандарт Ближний',2,3,1.8,2000,23,'до 150км');

INSERT INTO tariffs
VALUES (DEFAULT,'Стандарт Средний',2,3,1.8,2000,28,'от 150км до 500км');

INSERT INTO tariffs
VALUES (DEFAULT,'Стандарт Дальний',2,3,1.8,2000,36,'от 500км');

INSERT INTO tariffs
VALUES (DEFAULT,'Серьйозный Бизнес Ближний',2.2,4,2,3100,25,'до 150км');

INSERT INTO tariffs
VALUES (DEFAULT,'Серьйозный Бизнес Средний',2.2,4,2,3100,30,'от 150км до 500км');

INSERT INTO tariffs
VALUES (DEFAULT,'Серьйозный Бизнес Дальний',2.2,4,2,3100,40,'от 500км');

