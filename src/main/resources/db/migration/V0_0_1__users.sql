create table bt_t_users
(
  id      uuid        not null
    constraint pk_bt_t_users primary key,

  name    varchar(50) not null unique,
  version smallint    not null,


  created timestamp   not null default current_timestamp,
  updated timestamp   not null default current_timestamp,
  deleted timestamp
);


insert into bt_t_users (id, name, version, created, updated)
values ('f4d1e11e-aec4-4e62-a938-35146c2993d5', 'user-1', 1, current_timestamp, current_timestamp);

insert into bt_t_users (id, name, version, created, updated)
values ('c0d84d66-d091-4d97-8a07-434dd8af33ec', 'user-2', 1, current_timestamp, current_timestamp);

insert into bt_t_users (id, name, version, created, updated)
values ('acf219ae-c1d4-490c-b27a-663d8cf37abc', 'user-3', 1, current_timestamp, current_timestamp);

insert into bt_t_users (id, name, version, created, updated)
values ('0f8742ed-d456-46af-8022-e4b6547f6a8b', 'user-4', 1, current_timestamp, current_timestamp);

insert into bt_t_users (id, name, version, created, updated)
values ('d36cb4d1-7603-435b-bf54-313ae4397489', 'user-5', 1, current_timestamp, current_timestamp);

insert into bt_t_users (id, name, version, created, updated)
values ('07bf8b1e-1c6d-4886-8be0-0bea5af0aa35', 'user-6', 1, current_timestamp, current_timestamp);

insert into bt_t_users (id, name, version, created, updated)
values ('a35fdec9-5d13-4bd0-be2a-e17b7c4e3417', 'user-7', 1, current_timestamp, current_timestamp);

insert into bt_t_users (id, name, version, created, updated)
values ('d3931991-d6cc-4905-80ae-303bb9071b1c', 'user-8', 1, current_timestamp, current_timestamp);

insert into bt_t_users (id, name, version, created, updated)
values ('e6ce704f-8939-4dc5-8c3f-de108ac0953a', 'user-9', 1, current_timestamp, current_timestamp);

insert into bt_t_users (id, name, version, created, updated)
values ('7cbed57c-4ea0-4d39-b680-7f7523acaf5e', 'user-10', 1, current_timestamp, current_timestamp);