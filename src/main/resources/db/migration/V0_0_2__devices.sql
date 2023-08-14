create table bt_t_devices
(
  id         uuid        not null
    constraint pk_bt_t_devices primary key,

  brand_name varchar(50) not null,
  model_name varchar(50) not null,


  version    smallint    not null,


  created    timestamp   not null default current_timestamp,
  updated    timestamp   not null default current_timestamp,
  deleted    timestamp
);


insert into bt_t_devices (id, brand_name, model_name, version, created, updated)
values ('53f681cd-be9e-4d11-896e-dd31297357f2', 'Samsung', 'Galaxy S9', 1, current_timestamp, current_timestamp),
       ('d3ec5067-9c4e-4611-bdfd-282139bd098b', 'Samsung', 'Galaxy S8', 1, current_timestamp, current_timestamp),
       ('6cdaa8d2-3e2c-473d-b2cf-e9ef6a16729f', 'Motorola', 'Nexus 6', 1, current_timestamp, current_timestamp),
       ('472bbded-7382-4d12-af7f-537b495c38d9', 'Oneplus', '9', 1, current_timestamp, current_timestamp),
       ('2c0544ed-0c27-4687-9177-9ddb7c5aac99', 'Apple', 'iPhone 13', 1, current_timestamp, current_timestamp),
       ('aebfc226-ff0a-480f-af00-197e43955da6', 'Apple', 'iPhone 12', 1, current_timestamp, current_timestamp),
       ('1233b0e3-0758-44a3-a343-6827c54352c6', 'Apple', 'iPhone 11', 1, current_timestamp, current_timestamp),
       ('cf5bb79d-d9c3-4447-b969-56434f0f305e', 'Apple', 'iPhone X', 1, current_timestamp, current_timestamp),
       ('b46b5920-7e28-4a8c-9cbd-5decaf8a8275', 'Nokia', '3310', 1, current_timestamp, current_timestamp);