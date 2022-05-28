create table `t_command` (
    `id` int(11) NOT NULL,
    `status` int(11) NOT NULL DEFAULT 0, -- 0 created, 1 delivered, 2 arrived, 4 finished
    `content` text,
    `order_day` int(11),
    `uid` int(11),-- user id
    `cdid` int(11),-- command detail id
    `ts` varchar(50),
    `dr` int(11)  DEFAULT 0, -- 0 active, 1 inactive
    primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `t_command_detail` (
    `id` int(11) NOT NULL,
    `pid` int(11),-- product id
    `product_num` int(11),-- number of product
    `cid` int(11),-- command id
    `ts` varchar(50),
    `dr` int(11)  DEFAULT 0, -- 0 active, 1 inactive
    primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table `t_user` (
    `id` int(11) NOT NULL,
    `name` varchar(50) NOT NULL DEFAULT '',
    `mail` varchar(50),
    `address` varchar(255),
    `ts` varchar(50),
    `dr` int(11)  DEFAULT 0, -- 0 active, 1 inactive
    primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;