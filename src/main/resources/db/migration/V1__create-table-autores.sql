create table autores(
	id bigint not null auto_increment,
	nome varchar(100) not null,
	email varchat(100) not null,
	nascimento date not null,
	mini_curriculo varchar(200) not null,
	primary key (id)
	);
	
	