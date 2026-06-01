--
--    Copyright 2010-2026 the original author or authors.
--
--    Licensed under the Apache License, Version 2.0 (the "License");
--    you may not use this file except in compliance with the License.
--    You may obtain a copy of the License at
--
--       https://www.apache.org/licenses/LICENSE-2.0
--
--    Unless required by applicable law or agreed to in writing, software
--    distributed under the License is distributed on an "AS IS" BASIS,
--    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
--    See the License for the specific language governing permissions and
--    limitations under the License.
--

INSERT INTO sequence VALUES('ordernum', 1000);

INSERT INTO signon VALUES('j2ee','j2ee');
INSERT INTO signon VALUES('ACID','ACID');

INSERT INTO account VALUES('j2ee','admin@indio-group.com','Indio', 'Admin', 'OK', 'Siège Social', 'Bâtiment A', 'Paris', 'IDF', '75001', 'France',  '01-02-03-04-05');
INSERT INTO account VALUES('ACID','staff@indio-group.com','Employé', 'Indio', 'OK', 'Bureau Lyon', 'Espace Cowork', 'Lyon', 'Rhone', '69000', 'France',  '04-05-06-07-08');

INSERT INTO profile VALUES('j2ee','french','IT',1,1);
INSERT INTO profile VALUES('ACID','french','OFFICE',1,1);

-- Suppression des bannières animaux (on laisse vide ou on met du texte)
INSERT INTO bannerdata VALUES ('IT','MATÉRIEL INFORMATIQUE');
INSERT INTO bannerdata VALUES ('OFFICE','FOURNITURES DE BUREAU');
INSERT INTO bannerdata VALUES ('FURN','MOBILIER ET ERGONOMIE');
INSERT INTO bannerdata VALUES ('ELEC','ÉLECTRONIQUE ET CÂBLES');
INSERT INTO bannerdata VALUES ('COF','ESPACE DÉTENTE ET CAFÉ');

-- Catégories INDIO GROUP
INSERT INTO CATEGORY VALUES ('IT','Informatique','<p>Laptops, Écrans, Périphériques</p>');
INSERT INTO CATEGORY VALUES ('OFFICE','Bureautique','<p>Papeterie et fournitures de bureau</p>');
INSERT INTO CATEGORY VALUES ('FURN','Mobilier','<p>Chaises ergonomiques, Bureaux</p>');
INSERT INTO CATEGORY VALUES ('ELEC','Électronique','<p>Câbles, Adaptateurs, Chargeurs</p>');
INSERT INTO CATEGORY VALUES ('COF','Espace Café','<p>Capsules, Gobelets, Thé</p>');

-- Produits INDIO GROUP (ID, Catégorie, Nom, Description)
INSERT INTO product VALUES ('IT-LP-01','IT','Dell XPS 15','Ordinateur portable haute performance pour développeur');
INSERT INTO product VALUES ('IT-LP-02','IT','MacBook Pro','Apple Silicon M3 - 16GB RAM');
INSERT INTO product VALUES ('IT-MN-01','IT','Écran 27 Pouces','Dalle 4K pour graphistes et bureautique');
INSERT INTO product VALUES ('OFF-01','OFFICE','Carnet Indio','Carnet de notes format A5 aux couleurs du groupe');
INSERT INTO product VALUES ('OFF-02','OFFICE','Pack Stylos','Lot de 10 stylos bille noirs');
INSERT INTO product VALUES ('FUR-01','FURN','Chaise Ergonomique','Soutien lombaire et accoudoirs réglables');
INSERT INTO product VALUES ('ELE-01','ELEC','Adaptateur USB-C','Hub Multiports HDMI / USB 3.0');
INSERT INTO product VALUES ('COF-01','COF','Boîte Café','Pack de 50 capsules expresso');

INSERT INTO supplier VALUES (1,'Indio Logistique','AC','Quai de livraison','','Paris','FR','75000','01-99-99-99-99');
INSERT INTO supplier VALUES (2,'Fournisseur IT','AC','Avenue des technos','','Lyon','FR','69000','04-99-99-99-99');

-- Items (Variantes de produits et Prix)
-- Format: itemid, productid, listprice, unitcost, supplier, status, attr1
INSERT INTO  item (itemid, productid, listprice, unitcost, supplier, status, attr1) VALUES('EST-1','IT-LP-01',1500.00,1200.00,1,'P','32GB RAM');
INSERT INTO  item (itemid, productid, listprice, unitcost, supplier, status, attr1) VALUES('EST-2','IT-LP-01',1300.00,1000.00,1,'P','16GB RAM');
INSERT INTO  item (itemid, productid, listprice, unitcost, supplier, status, attr1) VALUES('EST-3','IT-LP-02',2200.00,1800.00,1,'P','M3 Pro');
INSERT INTO  item (itemid, productid, listprice, unitcost, supplier, status, attr1) VALUES('EST-4','IT-MN-01',350.00,250.00,1,'P','Dalle Mat');
INSERT INTO  item (itemid, productid, listprice, unitcost, supplier, status, attr1) VALUES('EST-5','OFF-01',5.00,2.00,1,'P','Couverture Cuir');
INSERT INTO  item (itemid, productid, listprice, unitcost, supplier, status, attr1) VALUES('EST-6','FUR-01',450.00,300.00,1,'P','Couleur Noir');
INSERT INTO  item (itemid, productid, listprice, unitcost, supplier, status, attr1) VALUES('EST-7','ELE-01',45.00,20.00,1,'P','Aluminium');
INSERT INTO  item (itemid, productid, listprice, unitcost, supplier, status, attr1) VALUES('EST-8','COF-01',25.00,15.00,1,'P','Arabica');

-- Inventaire
INSERT INTO inventory (itemid, qty ) VALUES ('EST-1',50);
INSERT INTO inventory (itemid, qty ) VALUES ('EST-2',100);
INSERT INTO inventory (itemid, qty ) VALUES ('EST-3',30);
INSERT INTO inventory (itemid, qty ) VALUES ('EST-4',200);
INSERT INTO inventory (itemid, qty ) VALUES ('EST-5',500);
INSERT INTO inventory (itemid, qty ) VALUES ('EST-6',40);
INSERT INTO inventory (itemid, qty ) VALUES ('EST-7',150);
INSERT INTO inventory (itemid, qty ) VALUES ('EST-8',1000);
