/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/7/26 11:19:04                           */
/*==============================================================*/


drop table if exists coosv_sys_operation_log;

drop table if exists coosv_sys_organization;

drop table if exists coosv_sys_permission;

drop table if exists coosv_sys_resource;

drop table if exists coosv_sys_role;

drop table if exists coosv_sys_role_organization;

drop table if exists coosv_sys_user;

drop table if exists coosv_sys_user_organization;

drop table if exists coosv_sys_user_role;

/*==============================================================*/
/* Table: coosv_sys_operation_log                               */
/*==============================================================*/
create table coosv_sys_operation_log
(
   id                   varchar(32) not null,
   path                 varchar(50),
   host                 varchar(20),
   creater              varchar(32),
   create_date          datetime,
   primary key (id)
);

/*==============================================================*/
/* Table: coosv_sys_organization                                */
/*==============================================================*/
create table coosv_sys_organization
(
   id                   varchar(32) not null,
   name                 varchar(50),
   parent_id            varchar(32),
   type                 varchar(10),
   creater              varchar(32),
   create_date          datetime,
   updater              varchar(32),
   update_date          datetime,
   del                  bit,
   primary key (id)
);

/*==============================================================*/
/* Table: coosv_sys_permission                                  */
/*==============================================================*/
create table coosv_sys_permission
(
   id                   varchar(32) not null,
   resource_id          varchar(32),
   role_id              varchar(32),
   creater              varchar(32),
   create_date          datetime,
   updater              varchar(32),
   update_date          datetime,
   primary key (id)
);

/*==============================================================*/
/* Table: coosv_sys_resource                                    */
/*==============================================================*/
create table coosv_sys_resource
(
   id                   varchar(32) not null,
   name                 varchar(50),
   url                  varchar(100),
   code                 varchar(20),
   type                 int,
   parent_id            char(10),
   creater              varchar(32),
   create_date          datetime,
   updater              varchar(32),
   update_date          datetime,
   del                  bit,
   primary key (id)
);

/*==============================================================*/
/* Table: coosv_sys_role                                        */
/*==============================================================*/
create table coosv_sys_role
(
   id                   varchar(32) not null,
   name                 varchar(50),
   parent_id            char(10),
   creater              varchar(32),
   create_date          datetime,
   updater              varchar(32),
   update_date          datetime,
   del                  bit,
   primary key (id)
);

/*==============================================================*/
/* Table: coosv_sys_role_organization                           */
/*==============================================================*/
create table coosv_sys_role_organization
(
   id                   varchar(32) not null,
   role_id              varchar(32),
   organization_id      varchar(32),
   creater              varchar(32),
   create_date          datetime,
   updater              varchar(32),
   update_date          datetime,
   primary key (id)
);

/*==============================================================*/
/* Table: coosv_sys_user                                        */
/*==============================================================*/
create table coosv_sys_user
(
   id                   varchar(32) not null,
   first_name           varchar(10),
   last_name            varchar(10),
   username             varchar(20),
   password             varchar(50),
   email                varchar(50),
   sex                  varchar(20),
   birth_date           datetime,
   status               int,
   creater              varchar(32),
   create_date          datetime,
   updater              varchar(32),
   del                  bit,
   primary key (id)
);

/*==============================================================*/
/* Table: coosv_sys_user_organization                           */
/*==============================================================*/
create table coosv_sys_user_organization
(
   id                   varchar(32) not null,
   user_id              varchar(32),
   organization_id      varchar(32),
   creater              varchar(32),
   create_date          datetime,
   updater              varchar(32),
   update_date          datetime,
   primary key (id)
);

/*==============================================================*/
/* Table: coosv_sys_user_role                                   */
/*==============================================================*/
create table coosv_sys_user_role
(
   id                   varchar(32) not null,
   user_id              varchar(32),
   role_id              varchar(32),
   creater              varchar(32),
   create_date          datetime,
   updater              varchar(32),
   update_date          datetime,
   primary key (id)
);

alter table coosv_sys_permission add constraint FK_resource_permission_mapper foreign key (resource_id)
      references coosv_sys_resource (id) on delete restrict on update restrict;

alter table coosv_sys_permission add constraint FK_role_permission_mapper foreign key (role_id)
      references coosv_sys_role (id) on delete restrict on update restrict;

alter table coosv_sys_role_organization add constraint FK_org_role_mapper foreign key (organization_id)
      references coosv_sys_organization (id) on delete restrict on update restrict;

alter table coosv_sys_role_organization add constraint FK_role_org_mapper foreign key (role_id)
      references coosv_sys_role (id) on delete restrict on update restrict;

alter table coosv_sys_user_organization add constraint FK_org_user_mapper foreign key (organization_id)
      references coosv_sys_organization (id) on delete restrict on update restrict;

alter table coosv_sys_user_organization add constraint FK_user_org_mapper foreign key (user_id)
      references coosv_sys_user (id) on delete restrict on update restrict;

alter table coosv_sys_user_role add constraint FK_role_uer_mapper foreign key (role_id)
      references coosv_sys_role (id) on delete restrict on update restrict;

alter table coosv_sys_user_role add constraint FK_user_role_mapper foreign key (user_id)
      references coosv_sys_user (id) on delete restrict on update restrict;

