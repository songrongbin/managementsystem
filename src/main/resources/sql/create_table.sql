CREATE TABLE `s_menus` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '菜单父ID',
  `menu_key` varchar(50) NOT NULL COMMENT '菜单key',
  `menu_label` varchar(30) NOT NULL COMMENT '菜单标签',
  `menu_link` varchar(255) NOT NULL COMMENT '菜单链接',
  `menu_order` int(11) NOT NULL DEFAULT '0' COMMENT '菜单顺序',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8
