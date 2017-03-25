create table team (
  id integer not null primary key,
  name varchar(255) not null
);

create table team_member (
  team_id integer not null,
  username varchar(255) not null,
  constraint pk_team_member primary key (team_id, username),
  constraint fk_team_id foreign key (team_id) references team (id)
);