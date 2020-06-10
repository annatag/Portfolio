use SuperHeroDB;
insert into Hero(nameHero, descriptionHero, powerHero)
values('Spiderman', 'red costume', 'web'),
('Megamen', 'black costume', 'fire'),
('Terminator', 'grey costume', 'iron'),
('Batman', 'mixed creature', 'night vision');
insert into Organization(nameOrg, descriptionOrg, contactInfoOrg)
values('Apple', 'Superheros', 'New York'),
('Nike', 'Villains', 'San Francisco'),
('Puma', 'Mutans', 'Paris');
insert into Location(nameLocation, descriptionLocation, addressLocation, coordinates)
values('MOA', 'USA', '5th Ave', '1343292'),
('Minneapolis Institute of Arts', 'USA', '4-th street', '4533443'),
('Big Wall', 'China', 'Xiong', '242424242'),
('El Sol', 'Spain', 'Madrid center', '224242424');
insert into Sighting(dateSighting, Location_idLocation)
values('1999-01-01', 1),
('1823-03-20', 2),
('1543-01-23', 3),
('2018-03-30', 4),
('2000-12-12', 2),
('1959-08-31', 3);
insert into Sighting_has_Hero (Sighting_idSighSighting, Hero_idHero)
values(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 1),
(6, 2);
insert into Hero_has_Organization (Hero_idHero, Organization_idOrganization)
values (1, 1),
(2, 3),
(3, 2), 
(4, 1);