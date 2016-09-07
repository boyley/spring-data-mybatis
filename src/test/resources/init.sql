drop table if exists sys_account;

/*==============================================================*/
/* Table: sys_account                                           */
/*==============================================================*/
create table sys_account
(
   id                   int not null auto_increment comment '主键',
   country_code         varchar(10) comment '国家代码',
   phone_no             varchar(32) comment '手机号',
   primary key (id)
);

alter table sys_account comment '账号信息';
