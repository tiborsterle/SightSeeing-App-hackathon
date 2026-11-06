INSERT INTO SIGHTSEEING_APP.TYPES (name) VALUES ('Muzej');
INSERT INTO SIGHTSEEING_APP.TYPES (name) VALUES ('Park');
INSERT INTO SIGHTSEEING_APP.TYPES (name) VALUES ('Trg');

INSERT INTO SIGHTSEEING_APP.LOCATIONS (name) VALUES ('Zagreb');
INSERT INTO SIGHTSEEING_APP.LOCATIONS (name) VALUES ('Split');
INSERT INTO SIGHTSEEING_APP.LOCATIONS (name) VALUES ('Šibenik');

-- Zagreb
INSERT INTO SIGHTSEEING_APP.ATTRACTIONS (name, description, attractionURLName, ID_LOCATION, ID_TYPE) VALUES
    ('Muzej Mimara', 'Bogata privatna zbirka umjetnina svjetskih majstora.', 'Muzej%20Mimara',
     (SELECT id FROM SIGHTSEEING_APP.LOCATIONS WHERE name = 'Zagreb'),
     (SELECT id FROM SIGHTSEEING_APP.TYPES WHERE name = 'Muzej'));

INSERT INTO SIGHTSEEING_APP.ATTRACTIONS (name, description, attractionURLName, ID_LOCATION, ID_TYPE) VALUES
    ('Park Maksimir', 'Najstariji uređeni park u jugoistočnoj Europi.', 'Park%20Maksimir',
     (SELECT id FROM SIGHTSEEING_APP.LOCATIONS WHERE name = 'Zagreb'),
     (SELECT id FROM SIGHTSEEING_APP.TYPES WHERE name = 'Park'));

INSERT INTO SIGHTSEEING_APP.ATTRACTIONS (name, description, attractionURLName, ID_LOCATION, ID_TYPE) VALUES
    ('Trg bana Jelacica', 'Središnji gradski trg Zagreba.', 'Trg%20bana%20Jelacica',
     (SELECT id FROM SIGHTSEEING_APP.LOCATIONS WHERE name = 'Zagreb'),
     (SELECT id FROM SIGHTSEEING_APP.TYPES WHERE name = 'Trg'));

-- Split
INSERT INTO SIGHTSEEING_APP.ATTRACTIONS (name, description, attractionURLName, ID_LOCATION, ID_TYPE) VALUES
    ('Galerija Mestrovic', 'Muzej posvećen djelu kipara Ivana Meštrovića.', 'Galerija%20Mestrovic',
     (SELECT id FROM SIGHTSEEING_APP.LOCATIONS WHERE name = 'Split'),
     (SELECT id FROM SIGHTSEEING_APP.TYPES WHERE name = 'Muzej'));

INSERT INTO SIGHTSEEING_APP.ATTRACTIONS (name, description, attractionURLName, ID_LOCATION, ID_TYPE) VALUES
    ('Park suma Marjan', 'Zeleni poluotok i najdraže izletište Splićana.', 'Park%20suma%20Marjan',
     (SELECT id FROM SIGHTSEEING_APP.LOCATIONS WHERE name = 'Split'),
     (SELECT id FROM SIGHTSEEING_APP.TYPES WHERE name = 'Park'));

INSERT INTO SIGHTSEEING_APP.ATTRACTIONS (name, description, attractionURLName, ID_LOCATION, ID_TYPE) VALUES
    ('Peristil', 'Središnji trg unutar Dioklecijanove palače.', 'Peristil',
     (SELECT id FROM SIGHTSEEING_APP.LOCATIONS WHERE name = 'Split'),
     (SELECT id FROM SIGHTSEEING_APP.TYPES WHERE name = 'Trg'));

-- Šibenik
INSERT INTO SIGHTSEEING_APP.ATTRACTIONS (name, description, attractionURLName, ID_LOCATION, ID_TYPE) VALUES
    ('Muzej grada Sibenika', 'Stalna postava o povijesti i kulturi Šibenika.', 'Muzej%20grada%20Sibenika',
     (SELECT id FROM SIGHTSEEING_APP.LOCATIONS WHERE name = 'Šibenik'),
     (SELECT id FROM SIGHTSEEING_APP.TYPES WHERE name = 'Muzej'));

INSERT INTO SIGHTSEEING_APP.ATTRACTIONS (name, description, attractionURLName, ID_LOCATION, ID_TYPE) VALUES
    ('Nacionalni park Krka', 'Slapovi i prirodne ljepote rijeke Krke.', 'Nacionalni%20park%20Krka',
     (SELECT id FROM SIGHTSEEING_APP.LOCATIONS WHERE name = 'Šibenik'),
     (SELECT id FROM SIGHTSEEING_APP.TYPES WHERE name = 'Park'));

INSERT INTO SIGHTSEEING_APP.ATTRACTIONS (name, description, attractionURLName, ID_LOCATION, ID_TYPE) VALUES
    ('Trg Republike Hrvatske', 'Glavni gradski trg Šibenika s katedralom sv. Jakova.', 'Trg%20Republike%20Hrvatske',
     (SELECT id FROM SIGHTSEEING_APP.LOCATIONS WHERE name = 'Šibenik'),
     (SELECT id FROM SIGHTSEEING_APP.TYPES WHERE name = 'Trg'));