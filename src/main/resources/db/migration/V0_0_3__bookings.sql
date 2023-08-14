create table bt_t_bookings
(
  id          uuid                              not null
    constraint pk_bt_t_bookings primary key,

  version     smallint                          not null,

  user_id     uuid references bt_t_users (id)   not null,
  device_id   uuid references bt_t_devices (id) not null,

  book_time   timestamp                         not null,
  unbook_time timestamp,

  created     timestamp                         not null default current_timestamp,
  updated     timestamp                         not null default current_timestamp,
  deleted     timestamp
)