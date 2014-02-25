create table RSS_Feed (
	feedId LONG not null primary key,
	companyId LONG,
	userId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	url VARCHAR(75) null,
	scope VARCHAR(75) null
);