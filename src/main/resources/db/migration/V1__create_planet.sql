CREATE TABLE planet
(
    ID            bigserial PRIMARY KEY,
    NAME          VARCHAR(255)
);

SELECT setval('planet_id_seq', (select max(id) from planet), true);