drop database spotify;
create database if not exists spotify;
use spotify;
-- tengo que crear una tabla entre canciones y usuarios 
create table usuarios (
usu_codusu int unsigned primary key auto_increment,
usu_nick VARCHAR(60) not null,
usu_nombre varchar(45) not null,
usu_apellidos varchar(90) not null,
usu_nacimiento date not null,
usu_email varchar(90) not null,
usu_contra varchar(90) not null
);

create table cantantes (
can_codcan int unsigned primary key auto_increment,
can_codusu int unsigned not null
);

create table canciones_cantantes(
ccs_codccs int unsigned primary key,
ccs_codcan int unsigned not null,
ccs_codces int unsigned not null
);

create table canciones(
ces_codces int unsigned primary key auto_increment,
ces_nombre varchar(45) not null,
ces_fechaPublicacion date not null,
ces_duracion varchar(10) not null,
ces_url VARCHAR(100) not null,
ces_codalb int unsigned,
ces_codcan int unsigned not null,
ces_codpla int unsigned
);

create table albumes(
alb_codalb int unsigned primary key auto_increment,
alb_nombre varchar(90) not null,
alb_fechaCreacion date not null,
alb_codcan int unsigned not null
);

create table usuarios_gusta_canciones(
uce_coduce int unsigned primary key auto_increment,
uce_codusu int unsigned not null,
uce_codces int unsigned not null
);

create table usuarios_gusta_albumes(
ual_codual int unsigned primary key auto_increment,
ual_codusu int unsigned not null,
ual_codalb int unsigned not null
);

create table usuarios_gusta_cantantes(
uca_coduca int unsigned primary key auto_increment,
uca_codusu int unsigned not null,
uca_codcan int unsigned not null
);

create table playlists (
pla_codpla int unsigned primary key auto_increment,
pla_nombre varchar (90) not null,
pla_fechaCreacion datetime default now()
);
alter table playlists add pla_codusu int unsigned;
alter table playlists add foreign key PLA_USU_FK(pla_codusu) references usuarios (usu_codusu);

create table usuarios_gusta_playlists(
upl_coduca int unsigned primary key auto_increment,
upl_codusu int unsigned not null,
upl_codpla int unsigned not null
);

create table playlist_añadir_canciones (
pca_codpca int unsigned primary key auto_increment,
pca_codces int unsigned not null,
pca_codpla int unsigned not null
);

alter table cantantes add foreign key CAN_USU_FK(can_codusu) references usuarios (usu_codusu);
alter table canciones_cantantes add foreign key CCS_CAN_FK(ccs_codcan) references cantantes(can_codcan);
alter table canciones_cantantes add foreign key CCS_CES_FK(ccs_codces) references canciones(ces_codces);
alter table canciones add foreign key CES_ALB_FK(ces_codalb) references albumes(alb_codalb);
alter table albumes add foreign key ALB_CAN_FK(alb_codcan) references cantantes(can_codcan);
alter table canciones add foreign key CES_PLA_FK(ces_codpla) references playlists(pla_codpla);
-- alter table playlists add foreign key PLA_USU_FK(pla_codusu) references usuarios(usu_codusu);

alter table usuarios_gusta_canciones add foreign key UCE_USU_FK(uce_codusu) references usuarios(usu_codusu);
alter table usuarios_gusta_canciones add foreign key UCE_CES_FK(uce_codces) references canciones(ces_codces);

alter table usuarios_gusta_albumes add foreign key UAL_USU_FK(ual_codusu) references usuarios(usu_codusu);
alter table usuarios_gusta_albumes add foreign key UAL_ALB_FK(ual_codalb) references canciones(ces_codces);

alter table usuarios_gusta_cantantes add foreign key UCA_USU_FK(uca_codusu) references usuarios(usu_codusu);
alter table usuarios_gusta_cantantes add foreign key UCA_CES_FK(uca_codcan) references cantantes(can_codcan);

alter table usuarios_gusta_playlists add foreign key UPL_USU_FK(upl_codusu) references usuarios(usu_codusu);
alter table usuarios_gusta_playlists add foreign key UPL_PLA_FK(upl_codpla) references playlists(pla_codpla);

alter table playlist_añadir_canciones add foreign key PCA_CES_FK(pca_codces) references canciones(ces_codces);
alter table playlist_añadir_canciones add foreign key PCA_PLA_FK(pca_codpla) references playlists(pla_codpla);

select * from usuarios;
select * from cantantes;
select * from canciones;
select * from albumes;
select * from playlists;
 
select * from usuarios_gusta_cantantes;
select * from usuarios_gusta_canciones inner join canciones on uce_codces=ces_codces;
select * from usuarios_gusta_albumes;
select * from usuarios_gusta_playlists;
select * from playlist_añadir_canciones;

select * from playlist_añadir_canciones;
delete from playlist_añadir_canciones where pca_codpla = 2;
delete from usuarios_gusta_playlists where upl_codpla = 2;

delete from playlists where pla_codpla = 2 ;