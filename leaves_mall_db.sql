-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- 主机： 127.0.0.1
-- 生成日期： 2021-04-30 13:24:50
-- 服务器版本： 10.4.18-MariaDB
-- PHP 版本： 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `leaves_mall_db`
--

-- --------------------------------------------------------

--
-- 表的结构 `tb_leaves_mall_admin_user`
--

CREATE TABLE `tb_leaves_mall_admin_user` (
  `admin_user_id` int(11) NOT NULL COMMENT '管理员id',
  `login_user_name` varchar(50) NOT NULL COMMENT '管理员登陆名称',
  `login_password` varchar(50) NOT NULL COMMENT '管理员登陆密码',
  `nick_name` varchar(50) NOT NULL COMMENT '管理员显示昵称',
  `locked` tinyint(4) DEFAULT 0 COMMENT '是否锁定 0未锁定 1已锁定无法登陆'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `tb_leaves_mall_admin_user`
--

INSERT INTO `tb_leaves_mall_admin_user` (`admin_user_id`, `login_user_name`, `login_password`, `nick_name`, `locked`) VALUES
(1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '十三', 0),
(2, 'newbee-admin1', 'e10adc3949ba59abbe56e057f20f883e', '新蜂01', 0),
(3, 'newbee-admin2', 'e10adc3949ba59abbe56e057f20f883e', '新蜂02', 0);

-- --------------------------------------------------------

--
-- 表的结构 `tb_leaves_mall_carousel`
--

CREATE TABLE `tb_leaves_mall_carousel` (
  `carousel_id` int(11) NOT NULL COMMENT '首页轮播图主键id',
  `carousel_url` varchar(100) NOT NULL DEFAULT '' COMMENT '轮播图',
  `redirect_url` varchar(100) NOT NULL DEFAULT '''##''' COMMENT '点击后的跳转地址(默认不跳转)',
  `carousel_rank` int(11) NOT NULL DEFAULT 0 COMMENT '排序值(字段越大越靠前)',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `create_user` int(11) NOT NULL DEFAULT 0 COMMENT '创建者id',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `update_user` int(11) NOT NULL DEFAULT 0 COMMENT '修改者id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `tb_leaves_mall_carousel`
--

INSERT INTO `tb_leaves_mall_carousel` (`carousel_id`, `carousel_url`, `redirect_url`, `carousel_rank`, `is_deleted`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES
(2, 'https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner1.png', 'https://juejin.im/book/5da2f9d4f265da5b81794d48/section/5da2f9d6f265da5b794f2189', 13, 0, '2019-11-29 00:00:00', 0, '2019-11-29 00:00:00', 0),
(5, 'https://newbee-mall.oss-cn-beijing.aliyuncs.com/images/banner2.png', 'https://juejin.im/book/5da2f9d4f265da5b81794d48/section/5da2f9d6f265da5b794f2189', 0, 0, '2019-11-29 00:00:00', 0, '2019-11-29 00:00:00', 0);

-- --------------------------------------------------------

--
-- 表的结构 `tb_leaves_mall_goods_category`
--

CREATE TABLE `tb_leaves_mall_goods_category` (
  `category_id` bigint(20) NOT NULL COMMENT '分类id',
  `category_level` tinyint(4) NOT NULL DEFAULT 0 COMMENT '分类级别(1-一级分类 2-二级分类 3-三级分类)',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父分类id',
  `category_name` varchar(50) NOT NULL DEFAULT '' COMMENT '分类名称',
  `category_rank` int(11) NOT NULL DEFAULT 0 COMMENT '排序值(字段越大越靠前)',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `create_user` int(11) NOT NULL DEFAULT 0 COMMENT '创建者id',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  `update_user` int(11) DEFAULT 0 COMMENT '修改者id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `tb_leaves_mall_goods_category`
--

INSERT INTO `tb_leaves_mall_goods_category` (`category_id`, `category_level`, `parent_id`, `category_name`, `category_rank`, `is_deleted`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES
(15, 1, 0, '运动女', 100, 0, '2019-09-11 18:45:40', 0, '2021-04-14 14:48:18', 0),
(16, 1, 0, '运动男', 99, 0, '2019-09-11 18:46:07', 0, '2021-04-14 17:37:19', 0),
(59, 1, 0, '休闲男', 97, 0, '2019-09-12 00:08:46', 0, '2021-04-14 18:52:58', 0),
(61, 1, 0, '休闲女', 98, 0, '2019-09-12 00:09:27', 0, '2021-04-14 18:13:20', 0),
(65, 1, 0, '童装', 96, 0, '2019-09-12 00:11:17', 0, '2021-04-14 20:53:38', 0),
(67, 2, 16, '女装', 10, 1, '2019-09-12 00:15:19', 0, '2019-09-12 00:15:19', 0),
(68, 2, 16, '男装', 9, 1, '2019-09-12 00:15:28', 0, '2019-09-12 00:15:28', 0),
(69, 2, 16, '运动男装', 8, 0, '2019-09-12 00:15:35', 0, '2021-04-14 17:39:41', 0),
(70, 2, 61, '休闲女装', 10, 0, '2019-09-12 00:20:22', 0, '2021-04-14 18:16:29', 0),
(71, 2, 61, '裙子', 9, 1, '2019-09-12 00:20:29', 0, '2021-04-14 18:15:21', 0),
(72, 2, 61, '休闲女鞋', 8, 0, '2019-09-12 00:20:35', 0, '2021-04-14 18:16:55', 0),
(73, 2, 59, '休闲男装', 10, 0, '2019-09-12 00:20:49', 0, '2021-04-14 18:53:31', 0),
(74, 2, 59, '户外', 9, 1, '2019-09-12 00:20:58', 0, '2019-09-12 00:20:58', 0),
(75, 2, 59, '休闲男鞋', 8, 0, '2019-09-12 00:21:05', 0, '2021-04-14 18:53:45', 0),
(76, 3, 67, '外套', 0, 0, '2019-09-12 00:21:55', 0, '2021-04-14 15:07:12', 0),
(77, 3, 70, '休闲女裤', 10, 0, '2019-09-12 00:22:21', 0, '2021-04-14 18:17:22', 0),
(78, 3, 73, '休闲男上衣', 10, 0, '2019-09-12 00:22:42', 0, '2021-04-14 18:54:03', 0),
(83, 2, 62, '美妆', 10, 0, '2019-09-12 00:23:58', 0, '2019-09-17 18:22:44', 0),
(98, 3, 95, '休闲鞋', 10, 0, '2019-09-12 00:27:48', 0, '2019-09-12 00:27:48', 0),
(107, 2, 15, '鞋类', 0, 0, '2021-04-14 14:49:01', 0, '2021-04-14 15:03:56', 0),
(108, 3, 19, '裤子', 0, 0, '2021-04-14 15:03:21', 0, '2021-04-14 15:03:21', 0),
(109, 3, 19, '上衣', 1, 0, '2021-04-14 15:04:10', 0, '2021-04-14 15:04:19', 0),
(110, 3, 107, '运动鞋', 0, 0, '2021-04-14 17:29:31', 0, '2021-04-14 17:29:31', 0),
(111, 3, 69, '运动上衣', 0, 0, '2021-04-14 17:41:41', 0, '2021-04-14 17:41:41', 0),
(112, 2, 16, '运动鞋类', 1, 0, '2021-04-14 17:51:00', 0, '2021-04-14 17:51:00', 0),
(113, 3, 69, '运动裤', 1, 0, '2021-04-14 17:51:17', 0, '2021-04-14 17:51:25', 0),
(114, 3, 112, '鞋类', 0, 0, '2021-04-14 17:54:11', 0, '2021-04-14 17:54:11', 0),
(115, 3, 70, '休闲女裙', 0, 0, '2021-04-14 18:17:32', 0, '2021-04-14 18:17:32', 0),
(116, 3, 72, '女鞋', 0, 0, '2021-04-14 18:17:48', 0, '2021-04-14 18:17:48', 0),
(117, 3, 70, '休闲女上衣', 0, 0, '2021-04-14 18:23:59', 0, '2021-04-14 18:23:59', 0),
(118, 3, 73, '休闲男裤', 0, 0, '2021-04-14 18:54:13', 0, '2021-04-14 18:54:13', 0),
(119, 3, 75, '休闲男鞋类', 0, 0, '2021-04-14 18:54:30', 0, '2021-04-14 18:54:30', 0),
(120, 2, 65, '童装上身', 0, 0, '2021-04-14 20:57:08', 0, '2021-04-14 20:57:08', 0),
(121, 2, 65, '童装下身', 0, 0, '2021-04-14 20:57:21', 0, '2021-04-14 20:57:21', 0),
(122, 3, 120, '童装上衣', 0, 0, '2021-04-14 20:57:46', 0, '2021-04-14 20:57:46', 0),
(123, 3, 120, '童装裙', 0, 0, '2021-04-14 20:57:56', 0, '2021-04-14 20:57:56', 0),
(124, 3, 121, '童装裤', 0, 0, '2021-04-14 20:58:06', 0, '2021-04-14 20:58:06', 0),
(127, 3, 125, '时尚女 鞋子 常见', 0, 0, '2021-04-15 13:51:17', 0, '2021-04-15 13:51:17', 0),
(151, 1, 0, '商务男', 100, 0, '2019-09-11 18:45:40', 0, '2021-04-15 13:28:33', 0),
(161, 1, 0, '商务女', 99, 0, '2019-09-11 18:46:07', 0, '2021-04-15 13:29:04', 0),
(171, 2, 15, '商务男 上衣', 10, 0, '2019-09-11 18:46:32', 0, '2021-04-15 13:32:33', 0),
(181, 2, 15, '商务男 裤子', 9, 0, '2019-09-11 18:46:43', 0, '2021-04-15 13:31:32', 0),
(191, 2, 15, '商务男 鞋子', 8, 0, '2019-09-11 18:46:52', 0, '2021-04-15 13:31:46', 0),
(201, 3, 17, '商务男 上衣 黑色', 0, 0, '2019-09-11 18:47:38', 0, '2021-04-15 13:32:47', 0),
(291, 3, 17, '商务男 上衣 灰色', 0, 0, '2019-09-11 18:49:09', 0, '2021-04-15 13:33:00', 0),
(321, 3, 18, '商务男 裤子 黑色', 0, 0, '2019-09-11 18:49:50', 0, '2021-04-15 13:33:29', 0),
(421, 3, 18, '商务男 裤子 灰色', 0, 0, '2019-09-11 18:53:01', 0, '2021-04-15 13:33:35', 0),
(561, 3, 19, '商务男 鞋子 黑色', 0, 0, '2019-09-11 18:56:49', 0, '2021-04-15 13:34:15', 0),
(571, 3, 19, '商务男 鞋子 棕色', 0, 0, '2019-09-11 18:57:01', 0, '2021-04-15 13:34:22', 0),
(671, 2, 16, '商务女 上衣', 10, 0, '2019-09-12 00:15:19', 0, '2021-04-15 13:35:40', 0),
(681, 2, 16, '商务女 裤子', 9, 0, '2019-09-12 00:15:28', 0, '2021-04-15 13:36:04', 0),
(691, 2, 16, '商务女 鞋子', 8, 0, '2019-09-12 00:15:35', 0, '2021-04-15 13:36:11', 0),
(761, 3, 67, '商务女 上衣 黑色', 10, 0, '2019-09-12 00:21:55', 0, '2021-04-15 13:36:31', 0),
(1071, 3, 67, '商务女 上衣 灰白色', 0, 0, '2021-04-15 13:37:58', 0, '2021-04-15 13:37:58', 0),
(1081, 3, 68, '商务女 裤子 黑色', 0, 0, '2021-04-15 13:41:26', 0, '2021-04-15 13:41:26', 0),
(1091, 3, 69, '商务女 鞋子 高跟鞋', 0, 0, '2021-04-15 13:42:02', 0, '2021-04-15 13:43:36', 0),
(1101, 1, 0, '时尚男', 98, 0, '2021-04-15 13:42:20', 0, '2021-04-15 13:42:40', 0),
(1111, 1, 0, '时尚女', 97, 0, '2021-04-15 13:42:30', 0, '2021-04-15 13:42:45', 0),
(1121, 2, 110, '时尚男 上衣', 0, 0, '2021-04-15 13:43:18', 0, '2021-04-15 13:43:18', 0),
(1131, 3, 112, '时尚男 上衣 T恤', 0, 0, '2021-04-15 13:44:36', 0, '2021-04-15 13:44:36', 0),
(1141, 3, 112, '时尚男 上衣 牛仔服', 0, 0, '2021-04-15 13:45:03', 0, '2021-04-15 13:45:03', 0),
(1151, 3, 112, '时尚男 上衣 皮衣', 0, 0, '2021-04-15 13:45:10', 0, '2021-04-15 13:45:10', 0),
(1161, 3, 112, '时尚男 上衣 长袖衫', 0, 0, '2021-04-15 13:45:36', 0, '2021-04-15 13:45:36', 0),
(1171, 2, 111, '时尚女 上衣', 0, 0, '2021-04-15 13:46:17', 0, '2021-04-15 13:46:17', 0),
(1191, 3, 117, '时尚女 上衣 长袖', 0, 0, '2021-04-15 13:47:09', 0, '2021-04-15 13:47:45', 0),
(1201, 3, 117, '时尚女 上衣 连衣裙', 0, 0, '2021-04-15 13:47:18', 0, '2021-04-15 13:47:18', 0),
(1211, 3, 117, '时尚女 上衣 短袖', 0, 0, '2021-04-15 13:47:51', 0, '2021-04-15 13:47:51', 0),
(1221, 3, 117, '时尚女 上衣 性感', 0, 0, '2021-04-15 13:47:57', 0, '2021-04-15 13:47:57', 0),
(1231, 2, 111, '时尚女 裙子', 0, 0, '2021-04-15 13:48:36', 0, '2021-04-15 13:48:36', 0),
(1241, 3, 123, '时尚女 裙子 皮质', 0, 0, '2021-04-15 13:49:13', 0, '2021-04-15 13:49:13', 0),
(1251, 2, 111, '时尚女 鞋子', 0, 0, '2021-04-15 13:50:35', 0, '2021-04-15 13:50:39', 0),
(1261, 3, 125, '时尚女 鞋子 长筒靴', 0, 0, '2021-04-15 13:50:47', 0, '2021-04-15 13:50:47', 0);

-- --------------------------------------------------------

--
-- 表的结构 `tb_leaves_mall_goods_info`
--

CREATE TABLE `tb_leaves_mall_goods_info` (
  `goods_id` bigint(20) UNSIGNED NOT NULL COMMENT '商品表主键id',
  `goods_name` varchar(200) NOT NULL DEFAULT '' COMMENT '商品名',
  `goods_intro` varchar(200) NOT NULL DEFAULT '' COMMENT '商品简介',
  `goods_category_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联分类id',
  `goods_cover_img` varchar(200) NOT NULL DEFAULT '/admin/dist/img/no-img.png' COMMENT '商品主图',
  `goods_carousel` varchar(500) NOT NULL DEFAULT '/admin/dist/img/no-img.png' COMMENT '商品轮播图',
  `goods_detail_content` text NOT NULL COMMENT '商品详情',
  `original_price` int(11) NOT NULL DEFAULT 1 COMMENT '商品价格',
  `selling_price` int(11) NOT NULL DEFAULT 1 COMMENT '商品实际售价',
  `stock_num` int(11) NOT NULL DEFAULT 0 COMMENT '商品库存数量',
  `tag` varchar(20) NOT NULL DEFAULT '' COMMENT '商品标签',
  `goods_sell_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '商品上架状态 0-下架 1-上架',
  `create_user` int(11) NOT NULL DEFAULT 0 COMMENT '添加者主键id',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '商品添加时间',
  `update_user` int(11) NOT NULL DEFAULT 0 COMMENT '修改者主键id',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '商品修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `tb_leaves_mall_goods_info`
--

INSERT INTO `tb_leaves_mall_goods_info` (`goods_id`, `goods_name`, `goods_intro`, `goods_category_id`, `goods_cover_img`, `goods_carousel`, `goods_detail_content`, `original_price`, `selling_price`, `stock_num`, `tag`, `goods_sell_status`, `create_user`, `create_time`, `update_user`, `update_time`) VALUES
(10907, 'FOG', 'Just My Size Women\'s French Terry Capri', 108, 'goods-img/187921486.jpg', 'goods-img/187921486.jpg', '<p>商品介绍加载中...</p>', 1, 1000, 1000, '裤子', 0, 0, '2021-04-14 15:05:35', 0, '2021-04-18 17:02:00'),
(10908, 'NOIRE', 'FILA Women\'s Stretch Cotton Raglan Sleeve Tee', 109, 'goods-img/384777111_edit_98162115906374.jpg', 'goods-img/384777111_edit_98162115906374.jpg', '<p><br/></p>', 1, 1000, 2000, '上衣', 0, 0, '2021-04-14 15:09:52', 0, '2021-04-18 17:01:40'),
(10909, 'PORAIT', 'Adidas Women\'s Short Sleeve V-Neck Shirts Loose Casual Tee T-Shirt', 109, 'goods-img/565154769.jpg', 'goods-img/565154769.jpg', '<p><br/></p>', 1, 1500, 500, '上衣', 0, 0, '2021-04-14 15:11:50', 0, '2021-04-18 17:01:13'),
(10910, 'HUGO', 'Women\'s Drawstring Linen Crop Pant;', 108, 'goods-img/534229504_edit_99009229936453.jpg', 'goods-img/534229504_edit_99009229936453.jpg', '<p><br/></p>', 1, 800, 500, '裤子', 0, 0, '2021-04-14 17:23:55', 0, '2021-04-18 17:00:43'),
(10911, 'FILA a', 'FILA Women\'s V Neck T Shirt Rolled Sleeve Side Split Tunic Tops', 109, 'goods-img/-986866865_edit_98126684507942.jpg', 'goods-img/-986866865_edit_98126684507942.jpg', '<p><br/></p>', 1, 799, 500, '上衣', 0, 0, '2021-04-14 17:26:01', 0, '2021-04-18 17:00:18'),
(10912, 'COLN', 'Adidas Women’s T Shirts', 109, 'goods-img/1036330792.jpg', 'goods-img/1036330792.jpg', '<p><br/></p>', 1, 800, 1000, '上衣', 0, 0, '2021-04-14 17:28:23', 0, '2021-04-18 16:59:46'),
(10913, 'AIMU', 'NIKE shoes', 110, 'goods-img/1652930075.jpg', 'goods-img/1652930075.jpg', '<p><br/></p>', 1, 2000, 200, '运动鞋', 0, 0, '2021-04-14 17:30:57', 0, '2021-04-18 16:59:15'),
(10914, 'AIMU', 'ANTA Women\'s Basic Short Sleeve Scoop Neck Crop Top', 109, 'goods-img/Screenshot_20210323_121102_com.taobao.taobao_edit_99036742511970.jpg', 'goods-img/Screenshot_20210323_121102_com.taobao.taobao_edit_99036742511970.jpg', '<p><br/></p>', 1, 399, 780, '上衣', 0, 0, '2021-04-14 17:32:42', 0, '2021-04-18 16:58:40'),
(10915, 'JARRE', 'SweatyRocks Women\'s Short Sleeve Crew Neck Basic Crop Top T Shirts', 109, 'goods-img/Screenshot_20210323_121149_com.taobao.taobao_edit_99023498669785.jpg', 'goods-img/Screenshot_20210323_121149_com.taobao.taobao_edit_99023498669785.jpg', '<p><br/></p>', 1, 499, 800, '上衣', 0, 0, '2021-04-14 17:33:58', 0, '2021-04-18 16:58:06'),
(10916, 'MAJE', 'Biucly Women\'s Long Sleeve Crewneck Shirts Loose Casual Tee T-Shirt', 109, 'goods-img/Screenshot_20210323_121416_com.taobao.taobao_edit_98980775589062.jpg', 'goods-img/Screenshot_20210323_121416_com.taobao.taobao_edit_98980775589062.jpg', '<p><br/></p>', 1, 1299, 700, '上衣', 0, 0, '2021-04-14 17:35:06', 0, '2021-04-18 16:57:35'),
(10917, 'KARA', 'FILA Women Summer Crewneck Short Sleeve Lightweight T Shirt', 109, 'goods-img/Screenshot_20210323_121525_com.taobao.taobao_edit_98963459920835.jpg', 'goods-img/Screenshot_20210323_121525_com.taobao.taobao_edit_98963459920835.jpg', '<p>&nbsp;</p>', 1, 1199, 900, '上衣', 0, 0, '2021-04-14 17:36:23', 0, '2021-04-18 16:55:21'),
(10918, 'MONTY', 'simple and comfortable', 111, 'goods-img/440799828_edit_98147273450647.jpg', 'goods-img/440799828_edit_98147273450647.jpg', '<p>1. The dress version is very nice, it\'s a little thick<br/>2. The signature on the right arm of the dress is perfect</p>', 1, 999, 979, '上衣', 0, 0, '2021-04-14 17:42:33', 0, '2021-04-14 17:42:33'),
(10919, 'BALMAIN', 'with huge logo', 111, 'goods-img/Screenshot_20210323_122528_com.taobao.taobao_edit_99616796262402.jpg', 'goods-img/Screenshot_20210323_122528_com.taobao.taobao_edit_99616796262402.jpg', '<p>1. The fabric is very comfortable and breathable<br/>2. Clothes lose color easily when washed too much</p>', 1, 399, 980, '上衣', 0, 0, '2021-04-14 17:50:28', 0, '2021-04-14 17:50:28'),
(10920, 'LEZY', 'with thick fabric', 113, 'goods-img/Screenshot_20210323_122313_com.taobao.taobao_edit_99574408533763.jpg', 'goods-img/Screenshot_20210323_122313_com.taobao.taobao_edit_99574408533763.jpg', '<p>1. It\'s warm and goes well with your clothes<br/>2. For me, the pants are a little too long, otherwise they are perfect</p>', 1, 599, 790, '裤子', 0, 0, '2021-04-14 17:52:28', 0, '2021-04-14 17:52:28'),
(10921, 'CARA', 'suitable for running', 114, 'goods-img/Screenshot_20210323_122414_com.taobao.taobao_edit_99585723936886.jpg', 'goods-img/Screenshot_20210323_122414_com.taobao.taobao_edit_99585723936886.jpg', '<p>1. Shoes especially show leg length, like very much<br/>2. The shoes are light and fit for sports</p>', 1, 799, 1080, '运动鞋', 0, 0, '2021-04-14 17:55:53', 0, '2021-04-14 17:55:53'),
(10922, 'MAJE', 'unisex', 113, 'goods-img/1593772389_edit_99548714489496.jpg', 'goods-img/1593772389_edit_99548714489496.jpg', '<p>1. It goes well with your clothes<br/>2. This pair of trousers is simple and elegan</p>', 1, 699, 799, '裤子', 0, 0, '2021-04-14 17:58:35', 0, '2021-04-14 17:58:35'),
(10923, 'RIORA', 'Very cool to wear', 111, 'goods-img/tb_image_share_1616472555605_edit_99069010718215.png', 'goods-img/tb_image_share_1616472555605_edit_99069010718215.png', '<p>1. Very cool to wear, very recommended<br/>2. The fabric is waterproof and very practica</p>', 1, 899, 1010, '上衣', 0, 0, '2021-04-14 18:00:05', 0, '2021-04-14 18:00:05'),
(10924, 'VON', 'sport style', 111, 'goods-img/-1604019892.jpg', 'goods-img/-1604019892.jpg', '<p>1. The clothes are a little thin, see below put on a few more pieces<br/>2. It\'s breathable and fit for exercise</p>', 1, 1099, 780, '上衣', 0, 0, '2021-04-14 18:00:51', 0, '2021-04-14 18:00:51'),
(10925, 'PINTO', 'unisex', 111, 'goods-img/-513187392_edit_98177123030330.jpg', 'goods-img/-513187392_edit_98177123030330.jpg', '<p>1. This sweater is so cool, but it gets dirty easily<br/>2. The fabric is thin</p>', 1, 899, 500, '上衣', 0, 0, '2021-04-14 18:01:47', 0, '2021-04-14 18:01:47'),
(10926, 'SANDRO', 'suitable for sports', 114, 'goods-img/Screenshot_20210323_122512_com.taobao.taobao_edit_99606593390008.jpg', 'goods-img/Screenshot_20210323_122512_com.taobao.taobao_edit_99606593390008.jpg', '<p>1. Shoes especially show leg length, like very much<br/>2. The shoes are light and fit for sports</p>', 1, 1299, 931, '运动鞋', 0, 0, '2021-04-14 18:02:39', 0, '2021-04-14 18:02:39'),
(10927, 'MARGOT', 'White sporty short sleeves with small logo,comfortable and breathable', 111, 'goods-img/Screenshot_20210323_122732.jpg', 'goods-img/Screenshot_20210323_122732.jpg', '<p><br/></p>', 1, 799, 390, '上衣', 0, 0, '2021-04-14 18:03:58', 0, '2021-04-18 16:54:35'),
(10928, 'WILK', 'Patchwork sport coat for both men and women, fashionable,unisex', 111, 'goods-img/tb_image_share_1616472569510_edit_99055575536446.png', 'goods-img/tb_image_share_1616472569510_edit_99055575536446.png', '<p><br/></p>', 1, 1099, 679, '上衣', 0, 0, '2021-04-14 18:05:25', 0, '2021-04-18 16:53:45'),
(10929, 'CEJAI', 'brown casual pants, fine workmanship', 77, 'goods-img/1761077623.jpg', 'goods-img/1761077623.jpg', '<p><br/></p>', 1, 899, 790, '裤子', 0, 0, '2021-04-14 18:21:06', 0, '2021-04-18 16:49:30'),
(10930, 'MOCO', 'denim style women\'s trousers, simple and fashionable, easy to match', 77, 'goods-img/IMG_20210323_115406.jpg', 'goods-img/IMG_20210323_115406.jpg', '<p><br/></p>', 1, 799, 300, '裤子', 0, 0, '2021-04-14 18:22:27', 0, '2021-04-18 16:49:09'),
(10931, 'Dutti', 'leisure fashion women\'s skirt, rich in color, distinctive style', 115, 'goods-img/IMG_20210323_114425.jpg', 'goods-img/IMG_20210323_114425.jpg', '<p><br/></p>', 1, 499, 890, '短裙', 0, 0, '2021-04-14 18:23:29', 0, '2021-04-18 16:48:46'),
(10932, 'LILY', 'Pink casual women\'s sweater, very fashionable', 117, 'goods-img/IMG_20210323_114829.jpg', 'goods-img/IMG_20210323_114829.jpg', '<p><br/></p>', 1, 1099, 1090, '上衣', 0, 0, '2021-04-14 18:25:23', 0, '2021-04-18 16:48:22'),
(10933, 'V&T', 'Black Floral Dress, quality is very good', 115, 'goods-img/-2043028196.jpg', 'goods-img/-2043028196.jpg', '<p><br/></p>', 1, 1599, 1099, '连衣裙', 0, 0, '2021-04-14 18:26:52', 0, '2021-04-18 16:47:58'),
(10934, 'WEY', 'Pink waistcoat, woolen texture', 109, 'goods-img/2011470364.jpg', 'goods-img/2011470364.jpg', '<p><br/></p>', 1, 399, 560, '上衣', 0, 0, '2021-04-14 18:27:41', 0, '2021-04-18 16:47:07'),
(10935, 'ochirly', 'casual white lace blouse, very loose', 117, 'goods-img/-554373175.jpg', 'goods-img/-554373175.jpg', '<p><br/></p>', 1, 599, 1060, '上衣', 0, 0, '2021-04-14 18:29:01', 0, '2021-04-18 16:46:41'),
(10936, 'edition', 'Leopard Print Dress, fine workmanship', 115, 'goods-img/IMG_20210323_115942.jpg', 'goods-img/IMG_20210323_115942.jpg', '<p><br/></p>', 1, 899, 1079, '吊带裙', 0, 0, '2021-04-14 18:30:28', 0, '2021-04-18 16:45:40'),
(10937, 'DAZZLE', 'white female Lefu shoes, very comfortable', 116, 'goods-img/IMG_20210323_123506.jpg', 'goods-img/IMG_20210323_123506.jpg', '<p><br/></p>', 1, 1099, 799, '鞋类', 0, 0, '2021-04-14 18:31:41', 0, '2021-04-18 16:44:57'),
(10938, 'MAXco', 'black lace up ballet shoes, good-looking', 116, 'goods-img/IMG_20210323_123343.jpg', 'goods-img/IMG_20210323_123343.jpg', '<p><br/></p>', 1, 1899, 897, '鞋类', 0, 0, '2021-04-14 18:33:43', 0, '2021-04-18 16:33:39'),
(10939, 'G·Mone', 'Casual loose men\'s sweater, low-key color, comfortable texture.', 78, 'goods-img/IMG_20210323_122358.jpg', 'goods-img/IMG_20210323_122358.jpg', '<p>1.The product version is suitable and the quality is very good<br/>2. The fabric of the product is comfortable and looks good</p>', 1, 699, 680, '上衣', 0, 0, '2021-04-14 18:55:42', 0, '2021-04-18 16:33:03'),
(10940, 'Amarni', 'loose styles sweater, bright colors', 78, 'goods-img/IMG_20210323_121039.jpg', 'goods-img/IMG_20210323_121039.jpg', '<p><br/></p>', 1, 699, 889, '上衣', 0, 0, '2021-04-14 18:57:40', 0, '2021-04-18 16:32:35'),
(10941, 'HANY', 'casual style short, linen fabric, very comfortable', 78, 'goods-img/IMG_20210323_122019.jpg', 'goods-img/IMG_20210323_122019.jpg', '<p><br/></p>', 1, 599, 780, '上衣', 0, 0, '2021-04-14 18:59:12', 0, '2021-04-18 16:30:57'),
(10942, 'Con', 'fashion and leisure shoe, comfortable to  wear', 119, 'goods-img/IMG_20210323_122902.jpg', 'goods-img/IMG_20210323_122902.jpg', '<p><br/></p>', 1, 2099, 998, '鞋类', 0, 0, '2021-04-14 19:00:38', 0, '2021-04-18 16:42:41'),
(10943, 'Hazzys', 'fashionable trousers, light in  color and full of vigor', 118, 'goods-img/IMG_20210323_121244.jpg', 'goods-img/IMG_20210323_121244.jpg', '<p><br/></p>', 1, 899, 870, '裤子', 0, 0, '2021-04-14 19:01:32', 0, '2021-04-18 16:42:14'),
(10944, 'BOSS', 'denim fabric trousers, very durable, not easy to dirty', 118, 'goods-img/IMG_20210323_121610.jpg', 'goods-img/IMG_20210323_121610.jpg', '<p><br/></p>', 1, 1089, 788, '裤子', 0, 0, '2021-04-14 19:02:29', 0, '2021-04-18 16:41:39'),
(10945, 'HANY', 'loose version', 78, 'goods-img/IMG_20210323_121732.jpg', 'goods-img/IMG_20210323_121732.jpg', '<p><br/></p>', 1, 798, 760, '上衣', 0, 0, '2021-04-14 19:03:38', 0, '2021-04-18 16:41:08'),
(10946, 'J&J', 'young version short, leisure with fashion', 78, 'goods-img/IMG_20210323_120929.jpg', 'goods-img/IMG_20210323_120929.jpg', '<p><br/></p>', 1, 599, 780, '上衣', 0, 0, '2021-04-14 19:04:40', 0, '2021-04-18 16:40:33'),
(10947, 'Lomen', 'casual sports pants, can be worn at home or out for exercise', 118, 'goods-img/IMG_20210323_121421.jpg', 'goods-img/IMG_20210323_121421.jpg', '<p><br/></p>', 1, 1099, 897, '裤子', 0, 0, '2021-04-14 19:06:59', 0, '2021-04-18 16:39:27'),
(10948, 'GXG', 'casual sports shoes, fashionable appearance, young color', 119, 'goods-img/-1832672057.jpg', 'goods-img/-1832672057.jpg', '<p><br/></p>', 1, 1299, 769, '鞋类', 0, 0, '2021-04-14 19:07:51', 0, '2021-04-18 16:38:52'),
(10949, 'Milk', 'TLAENSON Boys Girls Jacket Waterproof Dinosaur Windbreaker Lightweight  Hooded coat', 122, 'goods-img/-160689692.jpg', 'goods-img/-160689692.jpg', '<p><br/></p>', 1, 500, 300, '上衣', 0, 0, '2021-04-14 21:00:18', 0, '2021-04-18 16:37:25'),
(10950, 'A&B', 'Fila Heritage Girls Two (2) Piece Short Set Kids Clothes', 123, 'goods-img/-433886465.jpg', 'goods-img/-433886465.jpg', '<p><br/></p>', 1, 500, 708, '上衣', 0, 0, '2021-04-14 21:01:51', 0, '2021-04-18 16:36:53'),
(10951, 'Lutyic', 'HAXICO Unisex Kids Solid Cotton Thin Pullover Sweatshirt T-Shirt', 122, 'goods-img/-492057902.jpg', 'goods-img/-492057902.jpg', '<p><br/></p>', 1, 599, 309, '上衣', 0, 0, '2021-04-14 21:04:42', 0, '2021-04-18 16:36:15'),
(10952, 'Chenglei', 'Fila Heritage Girls Two Piece Top and skirt Sets for Baby Girls Clothing', 123, 'goods-img/729864237.jpg', 'goods-img/729864237.jpg', '<p><br/></p>', 1, 499, 287, '上衣', 0, 0, '2021-04-14 21:05:47', 0, '2021-04-18 16:35:54'),
(10953, 'Chiying', 'Monnalisa Baby Girls\' Cotton Dresses, Featuring adorable prints and stripes', 123, 'goods-img/-908479387.jpg', 'goods-img/-908479387.jpg', '<p><br/></p>', 1, 699, 780, '上衣', 0, 0, '2021-04-14 21:07:12', 0, '2021-04-18 16:35:11'),
(10954, 'HOIO', 'REWANGOING 2 Pack of Little Boys Girls Cartoon Print Drawstring Elastic  Sweatpants Sport Jogger', 124, 'goods-img/-1004287474.jpg', 'goods-img/-1004287474.jpg', '<p><br/></p>', 1, 299, 890, '裤子', 0, 0, '2021-04-14 21:08:16', 0, '2021-04-18 16:30:06'),
(10955, 'M&C', 'Cotton blend dress', 123, 'goods-img/-1957191062.jpg', 'goods-img/-1957191062.jpg', '<p><br/></p>', 1, 399, 877, '上衣', 0, 0, '2021-04-14 21:10:36', 0, '2021-04-18 16:28:09'),
(10956, 'JORYA', 'Kids Toddler Baby Girl Outfit long Sleeve Tops Shirts Denim A-Line Skirt  Set 2 Piece Clothes Set', 123, 'goods-img/IMG_20210327_103159.jpg', 'goods-img/IMG_20210327_103159.jpg', '<p>1. Fayoela w: The skirt is very nice but top runs a little small.<br/>2. Stefine: Love love love this! I bought it for my daughter’s birthday so she hasn’t tried it <br/>on yet but it’s so adorable and I am super happy with this purchase! It arrived super fast <br/>and is better then I pictured!!</p>', 1, 499, 786, '上衣', 0, 0, '2021-04-14 21:12:37', 0, '2021-04-18 16:27:41'),
(10957, 'BRIK', 'Sleeveless Casual Cowboy Sundress for Girls 3-4 years', 123, 'goods-img/IMG_20210327_103227.jpg', 'goods-img/IMG_20210327_103227.jpg', '<p><br/></p>', 1, 299, 370, '上衣', 0, 0, '2021-04-14 21:13:38', 0, '2021-04-18 16:26:35'),
(10958, 'CERY', 'Snoopy Woodstock Joe Cool Boys Fleece Hoodie. This hoodie is so dang cute. It\'s so soft.', 122, 'goods-img/IMG_20210327_103311.jpg', 'goods-img/IMG_20210327_103311.jpg', '<p><br/></p>', 1, 399, 570, '上衣', 0, 0, '2021-04-14 21:14:30', 0, '2021-04-18 16:26:01'),
(10959, 'SOACAI', 'IjnUhb Waterproof Hooded Jacket for Boys Girls', 122, 'goods-img/IMG_20210327_103357.jpg', 'goods-img/IMG_20210327_103357.jpg', '<p><br/></p>', 1, 379, 850, '上衣', 0, 0, '2021-04-14 21:15:35', 0, '2021-04-18 16:24:52'),
(10960, 'ANTI', 'Toddler Boys Girls Cartoon Bear Thick Pants Cute Shark Sweatpants Cotton  Harem Trousers', 124, 'goods-img/IMG_20210327_103419.jpg', 'goods-img/IMG_20210327_103419.jpg', '<p>1. Staycg685: My three year old twin niece and nephew love these pants. Especially my <br/>niece. When she first wore them she Was bet upset because she kept getting <br/>compliments on them, but she couldn’t see them since the face is on the bottom <br/>haha. Now she wears them every time they are cleaned!<br/>2. Libby: Love the design however the stitching and sizing wasnt on point. Overall happy <br/>for what pricing due to us not expecting top quality. One person found this helpful</p>', 1, 299, 890, '裤子', 0, 0, '2021-04-14 21:16:38', 0, '2021-04-18 16:23:14'),
(10961, 'GIEVE', 'Bigzzia Baby Boys Girls Winter Coat, Warm Kids Toddlers', 122, 'goods-img/IMG_20210327_103457.jpg', 'goods-img/IMG_20210327_103457.jpg', '<p><br/></p>', 1, 399, 781, '上衣', 0, 0, '2021-04-14 21:18:05', 0, '2021-04-18 16:22:41'),
(109071, 'formal suit', 'Abandon the bondage feeling of formal suit, a bit more leisure and comfortable.', 20, 'goods-img/IMG_20210416_15373713.JPG', 'goods-img/IMG_20210416_15373713.JPG', '<p>1. High quality tailoring and breathable fabric make it comfortable to wear<br/>2. Cut three-dimensional, fit the body curve</p>', 100, 50, 10, '商务 黑色 男', 0, 0, '2021-04-16 15:40:13', 0, '2021-04-16 15:41:55'),
(109081, 'formal suit', 'It is suitable for a variety of occasions, including weddings, parties and casual wear.', 29, 'goods-img/IMG_20210416_15404571.JPG', 'goods-img/IMG_20210416_15404571.JPG', '<p>1. Flat collar design, simple and neat<br/>2. The shoulders are full and fit the body curve</p>', 100, 100, 10, '商务 灰色 男', 0, 0, '2021-04-16 15:41:35', 0, '2021-04-16 15:42:08'),
(109091, 'formal suit', 'Light, thin and breathable, no sense of bondage', 20, 'goods-img/IMG_20210416_15425340.JPG', 'goods-img/IMG_20210416_15425340.JPG', '<p>1. Fine workmanship, soft fabric<br/>2. Classic style with elegance</p>', 100, 58, 10, '商务 黑色 男', 0, 0, '2021-04-16 15:43:05', 0, '2021-04-16 15:43:05'),
(109101, 'formal suit', 'Various colors are available', 20, 'goods-img/IMG_20210416_15433054.JPG', 'goods-img/IMG_20210416_15433054.JPG', '<p>1. Clothes don\'t warp easily after pulling<br/>2. The waist is appropriately tightened to highlight the male charm</p>', 100, 100, 10, '商务 黑色 男', 0, 0, '2021-04-16 15:43:39', 0, '2021-04-16 15:43:39'),
(109111, 'formal suit', 'The waist design moves up the visual center of gravity', 20, 'goods-img/IMG_20210416_15441013.JPG', 'goods-img/IMG_20210416_15441013.JPG', '<p>1. Manually sewn cloth, the lines are symmetrical and smooth<br/>2. Cufflinks are beautiful and durable</p>', 100, 100, 10, '商务 黑色 男', 0, 0, '2021-04-16 15:44:17', 0, '2021-04-16 15:44:17'),
(109121, 'formal suit', ': The fabric imported from Australia is elastic', 20, 'goods-img/IMG_20210416_15444957.JPG', 'goods-img/IMG_20210416_15444957.JPG', '<p>1. Shoulder line back, wear natural straight<br/>2. The beauty of this suits is the ability to customize the jacket and pants size.</p>', 100, 98, 10, '商务 黑色 男', 0, 0, '2021-04-16 15:44:59', 0, '2021-04-16 15:44:59'),
(109131, 'formal leather shoes', 'Vamp material: leather, lining material: cloth', 57, 'goods-img/IMG_20210416_15451661.JPG', 'goods-img/IMG_20210416_15451661.JPG', '<p>1. Insoles absorb moisture, remove sweat, deodorize and remove fungus<br/>2. It provides extra cushioning to keep your feet comfortable all day long.</p>', 100, 198, 10, '商务 棕色 男', 0, 0, '2021-04-16 15:46:42', 0, '2021-04-16 15:46:42'),
(109141, 'formal leather shoes', 'Soft real leather upper', 56, 'goods-img/IMG_20210416_15472735.JPG', 'goods-img/IMG_20210416_15472735.JPG', '<p>1. Small-cushioned EVA insoles are matched with molded EVA heel cups for extraordinary<br/>comfort<br/>2. Wear-resistant rubber outsole provides maximum grip on wet surfaces</p>', 100, 138, 10, '商务 黑色 男', 0, 0, '2021-04-16 15:47:33', 0, '2021-04-16 15:47:33'),
(109151, 'formal trousers', 'The trousers are designed slightly below the hips', 32, 'goods-img/IMG_20210416_15474292.JPG', 'goods-img/IMG_20210416_15474292.JPG', '<p>1. This classic high performance wool fabric has a refined aesthetic and is suitable to be<br/>worn all year round in any climate.<br/>2. Wool is a sustainable fabric with versatile natural stretch for added comfort and easy care<br/>and maintenance.</p>', 100, 158, 10, '商务 黑色 男', 0, 0, '2021-04-16 15:49:39', 0, '2021-04-16 15:49:39'),
(109161, 'formal trousers', 'An expandable waist guarantees a perfect fit in these classic-fit dress pants', 41, 'goods-img/IMG_20210416_15494836.JPG', 'goods-img/IMG_20210416_15494836.JPG', '<p>1. Button-through double welt rear pockets<br/>2. Work made better: we listen to customer feedback and fine-tune every detail to ensure<br/>quality, fit, and comfort</p>', 100, 100, 10, '商务 灰色 男', 0, 0, '2021-04-16 15:50:32', 0, '2021-04-16 15:50:32'),
(109171, 'formal shoes', 'High-end luxury business women\'s high-heeled shoes, proper color matching, rich texture', 109, 'goods-img/IMG_20210416_15513057.JPG', 'goods-img/IMG_20210416_15513057.JPG', '<p>1. It\'s comfortable to wear and doesn\'t hold your feet<br/>2. It looks high-end, sophisticated and classy</p>', 100, 100, 10, '商务 黑色 白色 女', 0, 0, '2021-04-16 15:51:37', 0, '2021-04-16 15:51:37'),
(109181, 'formal suit', 'Simple style business women\'s clothing, black-and-white color matching, neat and clean', 76, 'goods-img/IMG_20210416_15521888.JPG', 'goods-img/IMG_20210416_15521888.JPG', '<p>1. It makes people feel capable and attentively<br/>2. Comfortable material and it’s durable to wear</p>', 100, 100, 10, '商务 黑色 白色 女', 0, 0, '2021-04-16 15:52:22', 0, '2021-04-16 15:52:22'),
(109191, 'formal suit', 'Black business women\'s sweater, dark color, warm and comfortable', 76, 'goods-img/IMG_20210416_15524875.JPG', 'goods-img/IMG_20210416_15524875.JPG', '<p>1. Warm clothing for workplace, which would impress others.<br/>2. No pilling and it‘s suitable to wash.</p>', 100, 100, 10, '商务 黑色 女', 0, 0, '2021-04-16 15:52:59', 0, '2021-04-16 15:52:59'),
(109201, 'formal suit', 'Loose business women\'s suit, heavy and elegant, full of luxury', 76, 'goods-img/IMG_20210416_15532796.JPG', 'goods-img/IMG_20210416_15532796.JPG', '<p>1. Dark tone and neat style give people a mature and steady feeling<br/>2. Comfortable material, high quality, not tig</p>', 100, 100, 10, '商务 黑色 女', 0, 0, '2021-04-16 15:53:33', 0, '2021-04-16 15:53:33'),
(109211, 'formal trousers', 'Loose business women\'s trousers, black tone , ownership of tidiness', 108, 'goods-img/IMG_20210416_15541144.JPG', 'goods-img/IMG_20210416_15541144.JPG', '<p>1. It’s next to the skin and is suitable for workplace.<br/>2. It’s comfortable for walking and sitting</p>', 100, 100, 10, '商务 黑色 女', 0, 0, '2021-04-16 15:54:22', 0, '2021-04-16 15:54:22'),
(109221, 'formal suit', 'White business women\'s outer wear, bright tone, clean and tidy', 107, 'goods-img/IMG_20210416_15551513.JPG', 'goods-img/IMG_20210416_15551513.JPG', '<p>1.It’s close fitting and neat<br/>2. Although it’s white color, it’s easy to clean</p>', 100, 100, 10, '商务 灰白色 女', 0, 0, '2021-04-16 15:55:20', 0, '2021-04-16 15:55:20'),
(109231, 'formal suit', 'Bright color business women\'s coat, rich in texture, neat and tidy', 107, 'goods-img/IMG_20210416_15555381.JPG', 'goods-img/IMG_20210416_15555381.JPG', '<p>1. It’s loose and comfortable<br/>2. It’s warm to wear</p>', 100, 100, 10, '商务 灰白色 女', 0, 0, '2021-04-16 15:55:58', 0, '2021-04-16 15:55:58'),
(109241, 'formal suit', 'Loose business women\'s coat, thick and tidy, visual solemnity', 76, 'goods-img/IMG_20210416_1556307.JPG', 'goods-img/IMG_20210416_1556307.JPG', '<p>1. It’s excellent in quality and is in fine texture<br/>2. It’s in formal style and gives people a sense of seriousness</p>', 100, 100, 10, '商务 黑色 女', 0, 0, '2021-04-16 15:56:38', 0, '2021-04-16 15:56:38'),
(109251, 'formal suit', 'Business women\'s dress for workplace, dark color, good texture', 76, 'goods-img/IMG_20210416_15565952.JPG', 'goods-img/IMG_20210416_15565952.JPG', '<p>1. It’s thick and smooth, and it touches good.<br/>2. It’s easy to wear and loose</p>', 100, 100, 10, '商务 黑色 女', 0, 0, '2021-04-16 15:57:05', 0, '2021-04-16 15:57:05'),
(109261, 'formal suit', 'Business women\'s coat, deep tone, excellent material quality.', 76, 'goods-img/IMG_20210416_15572248.JPG', 'goods-img/IMG_20210416_15572248.JPG', '<p>1. It looks very nice.<br/>2. It’s comfortable to wear and is in rich texture.</p>', 100, 100, 10, '商务 黑色 女', 0, 0, '2021-04-16 15:57:29', 0, '2021-04-16 15:57:29'),
(109271, 'fashional suit', 'Men\'s stylish jacket, black and white color', 113, 'goods-img/IMG_20210416_16005581.JPG', 'goods-img/IMG_20210416_16005581.JPG', '<p>1. Rich lines, with a sense of hierarchy</p><p>\n\n</p><p>2. Do not over wash, easy to deform</p>', 100, 100, 10, '时尚 白色 男', 0, 0, '2021-04-16 16:01:40', 0, '2021-04-16 16:01:40'),
(109281, 'fashional suit', 'Men’s stylish Denim jacket, black and white color', 116, 'goods-img/IMG_20210416_16023783.JPG', 'goods-img/IMG_20210416_16023783.JPG', '<p>1.	Three-dimensional version, very good highlight the figure<br/>2.	Comfortable material&nbsp;<br/></p>', 100, 100, 10, '时尚 黑色 白色 男', 0, 0, '2021-04-16 16:02:39', 0, '2021-04-16 16:02:39'),
(109291, 'fashional suit', 'Men\'s fashion leather jacket. Black color', 115, 'goods-img/IMG_20210416_16032033.JPG', 'goods-img/IMG_20210416_16032033.JPG', '<p>1. Clothes embellished with silver ornaments,\nhigh-end, fashionable</p><p>\n\n</p><p>2. Leather fabrics slim\nthe body</p>', 100, 100, 10, '时尚 黑色 男', 0, 0, '2021-04-16 16:03:28', 0, '2021-04-16 16:05:46'),
(109301, 'fashional suit', 'Men\'s fashion cartoon hoodie. Black color.', 116, 'goods-img/IMG_20210416_16035523.JPG', 'goods-img/IMG_20210416_16035523.JPG', '<p>1. Cartoon pattern embellishment looks younger</p><p>\n\n</p><p>2. comfortable materials</p>', 100, 100, 10, '时尚 黑色 男', 0, 0, '2021-04-16 16:04:15', 0, '2021-04-16 16:05:36'),
(109311, 'fashional suit', 'Men\'s stylish print denim jacket', 114, 'goods-img/IMG_20210416_16050112.JPG', 'goods-img/IMG_20210416_16050112.JPG', '<p>1. Three-dimensional plate,</p><p>\n\n</p><p>2. No polling and its suitable to wash</p>', 100, 100, 10, '时尚 灰色 男', 0, 0, '2021-04-16 16:05:02', 0, '2021-04-16 16:05:26'),
(109321, 'fashional suit', 'Men\'s fashion cartoon hoodie. gray color.', 116, 'goods-img/IMG_20210416_16055731.JPG', 'goods-img/IMG_20210416_16055731.JPG', '<p>1.comfortable materials, <br/>2. It\'s very well made, very breathable<br/></p>', 100, 100, 10, '时尚 白色 黑色 男', 0, 0, '2021-04-16 16:06:33', 0, '2021-04-16 16:06:33'),
(109331, 'fashional suit', 'Men\'s stylish blue denim jacket', 114, 'goods-img/IMG_20210416_16071235.JPG', 'goods-img/IMG_20210416_16071235.JPG', '<p>1. The product version is suitable and the quality is very good<br/>2. The color of the dress is very beautiful<br/></p>', 100, 100, 10, '时尚 蓝色 男', 0, 0, '2021-04-16 16:07:13', 0, '2021-04-16 16:07:13'),
(109341, 'fashional suit', 'Men\'s stylish blue print shirt', 116, 'goods-img/IMG_20210416_16074453.JPG', 'goods-img/IMG_20210416_16074453.JPG', '<p>1. The floral pattern is rich and bright <br/>2. Cultivate one\'s morality<br/></p>', 100, 100, 10, '时尚 蓝色 白色 男', 0, 0, '2021-04-16 16:08:07', 0, '2021-04-16 16:08:07'),
(109351, 'fashional suit', 'Men fashion black short sleeves', 20, 'goods-img/IMG_20210416_16083219.JPG', 'goods-img/IMG_20210416_16083219.JPG', '<p>1. The back print catches the eye<br/>2. Easy to wash and fade<br/></p>', 100, 100, 10, '时尚 黑色 男', 0, 0, '2021-04-16 16:08:45', 0, '2021-04-16 16:08:45'),
(109361, 'fashional suit', 'Men\'s fashion print hoodie. Gray color', 116, 'goods-img/IMG_20210416_16090256.JPG', 'goods-img/IMG_20210416_16090256.JPG', '<p>1. The chest pattern is fashionable and eye-catching<br/>2. To be dry-cleaned<br/></p>', 100, 100, 10, '时尚 灰色 男', 0, 0, '2021-04-16 16:09:24', 0, '2021-04-16 16:09:24'),
(109371, 'fashion suit', 'Women fashion brown cloth with a smile on', 119, 'goods-img/IMG_20210418_13365272.JPG', 'goods-img/IMG_20210418_13365272.JPG', '<p>1. smile face is eye-catching</p><p>2. looks arresting in the first time</p>', 100, 100, 10, '时尚 棕色 女', 0, 0, '2021-04-18 13:38:34', 0, '2021-04-18 14:30:52'),
(109381, 'fashion shoes', 'Black leather women boots', 126, 'goods-img/IMG_20210418_14324087.JPG', 'goods-img/IMG_20210418_14324087.JPG', '<p>1. Fells elegant</p><p>2. suitable for any kind of legs</p>', 100, 100, 10, '时尚 黑色 女', 0, 0, '2021-04-18 14:32:46', 0, '2021-04-18 14:42:56'),
(109391, 'fashion shoes', 'Black fashion women shoes', 127, 'goods-img/IMG_20210418_14430436.JPG', 'goods-img/IMG_20210418_14430436.JPG', '<p>1. looks pretty&nbsp;</p><p>2. suitable for daily walking requirements</p>', 1, 1, 0, '时尚 黑色 女', 0, 0, '2021-04-18 14:45:09', 0, '2021-04-18 14:45:09'),
(109401, 'fashion suit', 'Black short-sleeved dress', 121, 'goods-img/IMG_20210418_14454269.JPG', 'goods-img/IMG_20210418_14454269.JPG', '<p>1.&nbsp;Show a female figure while wearing</p><p>2. Can handle spring and autumn weather</p>', 100, 100, 10, '时尚 黑色 女', 0, 0, '2021-04-18 14:48:37', 0, '2021-04-18 14:48:37'),
(109411, 'fashional suit', 'Colorful women dress', 120, 'goods-img/IMG_20210418_14484696.JPG', 'goods-img/IMG_20210418_14484696.JPG', '<p>1. Feels elegant and dignified</p><p>2. Suitable for big occasion</p>', 100, 180, 10, '时尚 花印 女', 0, 0, '2021-04-18 15:01:46', 0, '2021-04-18 15:01:46'),
(109421, 'fashional suit', 'Sexy black silk dress', 122, 'goods-img/IMG_20210418_15030789.JPG', 'goods-img/IMG_20210418_15030789.JPG', '<p>1. Expose women\'s body to their husbands partly</p><p>2. Looks pretty</p>', 1, 1, 0, '时尚 性感 黑色 女', 0, 0, '2021-04-18 15:03:49', 0, '2021-04-18 15:03:49'),
(109431, 'fashional skirt', 'Brown fashional leather skirt', 124, 'goods-img/IMG_20210418_15063715.JPG', 'goods-img/IMG_20210418_15063715.JPG', '<p>1. Mask the true hip circumference</p><p>2. Suitable with most other type of suits</p>', 100, 100, 10, '时尚 棕色 女', 0, 0, '2021-04-18 15:08:28', 0, '2021-04-18 15:08:28'),
(109441, 'fashional suit', 'Brown fashion dress', 121, 'goods-img/IMG_20210418_15083312.JPG', 'goods-img/IMG_20210418_15083312.JPG', '<p>1. Fit in daily life&nbsp;</p><p>2.&nbsp;Show your good waist</p>', 1, 1, 0, '时尚 棕色 女', 0, 0, '2021-04-18 15:13:24', 0, '2021-04-18 15:13:24'),
(109451, 'fashional suit', 'Black and white dress', 121, 'goods-img/IMG_20210418_15133062.JPG', 'goods-img/IMG_20210418_15133062.JPG', '<p>1. Simple color combo with simple&nbsp;modelling</p><p>2. Suitable for the life that occupy the home</p>', 100, 100, 10, '时尚 黑色 白色 女', 0, 0, '2021-04-18 15:31:23', 0, '2021-04-18 15:31:23'),
(109461, 'fashional suit', 'White-mainly dress', 119, 'goods-img/IMG_20210418_1531291.JPG', 'goods-img/IMG_20210418_1531291.JPG', '<p>1. Some daily-life painting on the cloth draws attention</p><p>2. Comfortable wearing feeling</p>', 100, 100, 10, '时尚 白色 女', 0, 0, '2021-04-18 15:33:12', 0, '2021-04-18 15:33:12');

-- --------------------------------------------------------

--
-- 表的结构 `tb_leaves_mall_index_config`
--

CREATE TABLE `tb_leaves_mall_index_config` (
  `config_id` bigint(20) NOT NULL COMMENT '首页配置项主键id',
  `config_name` varchar(50) NOT NULL DEFAULT '' COMMENT '显示字符(配置搜索时不可为空，其他可为空)',
  `config_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '1-搜索框热搜 2-搜索下拉框热搜 3-(首页)热销商品 4-(首页)新品上线 5-(首页)为你推荐',
  `goods_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '商品id 默认为0',
  `redirect_url` varchar(100) NOT NULL DEFAULT '##' COMMENT '点击后的跳转地址(默认不跳转)',
  `config_rank` int(11) NOT NULL DEFAULT 0 COMMENT '排序值(字段越大越靠前)',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `create_user` int(11) NOT NULL DEFAULT 0 COMMENT '创建者id',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '最新修改时间',
  `update_user` int(11) DEFAULT 0 COMMENT '修改者id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `tb_leaves_mall_index_config`
--

INSERT INTO `tb_leaves_mall_index_config` (`config_id`, `config_name`, `config_type`, `goods_id`, `redirect_url`, `config_rank`, `is_deleted`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES
(25, 'New', 4, 10957, '##', 1, 0, '2021-04-18 15:37:27', 0, '2021-04-18 15:37:27', 0),
(26, 'New', 4, 10920, '##', 0, 0, '2021-04-18 21:08:30', 0, '2021-04-18 21:08:30', 0),
(27, 'New', 4, 10918, '##', 0, 0, '2021-04-18 21:09:37', 0, '2021-04-18 21:09:37', 0),
(28, 'New', 4, 10917, '##', 0, 0, '2021-04-18 21:10:41', 0, '2021-04-18 21:10:41', 0),
(29, 'Hot', 3, 10914, '##', 0, 0, '2021-04-18 21:11:19', 0, '2021-04-18 21:11:19', 0),
(30, 'Hot', 3, 10940, '##', 0, 0, '2021-04-18 21:11:42', 0, '2021-04-18 21:11:42', 0),
(31, 'Hot', 3, 10938, '##', 0, 0, '2021-04-18 21:12:07', 0, '2021-04-18 21:12:07', 0),
(32, 'recommend products', 5, 10916, '##', 0, 0, '2021-04-18 21:14:43', 0, '2021-04-18 21:14:43', 0),
(33, 'recommend products', 5, 10960, '##', 0, 0, '2021-04-18 21:14:58', 0, '2021-04-18 21:14:58', 0),
(34, 'recommend products', 5, 10958, '##', 0, 0, '2021-04-18 21:15:15', 0, '2021-04-18 21:15:15', 0),
(35, 'recommend products', 5, 10955, '##', 0, 0, '2021-04-18 21:15:31', 0, '2021-04-18 21:15:31', 0),
(36, 'recommend products', 5, 10940, '##', 0, 0, '2021-04-18 21:15:47', 0, '2021-04-18 21:15:47', 0),
(37, 'recommend products', 5, 10935, '##', 0, 0, '2021-04-18 21:16:06', 0, '2021-04-18 21:16:06', 0),
(38, 'recommend products', 5, 10934, '##', 0, 0, '2021-04-18 21:16:15', 0, '2021-04-18 21:16:15', 0),
(39, 'recommend products', 5, 10918, '##', 0, 0, '2021-04-18 21:16:33', 0, '2021-04-18 21:16:33', 0),
(40, 'recommend products', 5, 10914, '##', 0, 0, '2021-04-18 21:17:28', 0, '2021-04-18 21:17:28', 0),
(41, 'recommend products', 5, 10915, '##', 0, 0, '2021-04-18 21:17:39', 0, '2021-04-18 21:17:39', 0);

-- --------------------------------------------------------

--
-- 表的结构 `tb_leaves_mall_order`
--

CREATE TABLE `tb_leaves_mall_order` (
  `order_id` bigint(20) NOT NULL COMMENT '订单表主键id',
  `order_no` varchar(20) NOT NULL DEFAULT '' COMMENT '订单号',
  `user_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '用户主键id',
  `total_price` int(11) NOT NULL DEFAULT 1 COMMENT '订单总价',
  `pay_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '支付状态:0.未支付,1.支付成功,-1:支付失败',
  `pay_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0.无 1.支付宝支付 2.微信支付',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `order_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '订单状态:0.待支付 1.已支付 2.配货完成 3:出库成功 4.交易成功 -1.手动关闭 -2.超时关闭 -3.商家关闭',
  `extra_info` varchar(100) NOT NULL DEFAULT '' COMMENT '订单body',
  `user_name` varchar(30) NOT NULL DEFAULT '' COMMENT '收货人姓名',
  `user_phone` varchar(11) NOT NULL DEFAULT '' COMMENT '收货人手机号',
  `user_address` varchar(100) NOT NULL DEFAULT '' COMMENT '收货人收货地址',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '最新修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `tb_leaves_mall_order`
--

INSERT INTO `tb_leaves_mall_order` (`order_id`, `order_no`, `user_id`, `total_price`, `pay_status`, `pay_type`, `pay_time`, `order_status`, `extra_info`, `user_name`, `user_phone`, `user_address`, `is_deleted`, `create_time`, `update_time`) VALUES
(1, '15688187285093508', 1, 2492, 1, 2, '2019-09-18 23:00:18', -1, '', '', '', 'xafsdufhpwe', 0, '2019-09-18 22:53:07', '2019-09-18 22:55:32'),
(2, '15688188616936181', 1, 135, 1, 1, '2019-09-18 23:01:06', 1, '', '', '', 'xafsdufhpwe', 0, '2019-09-18 22:55:20', '2019-09-18 23:01:06'),
(3, '15689089426956979', 1, 15487, 1, 1, '2019-09-20 00:16:03', 3, '', '', '', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, '2019-09-19 23:56:40', '2019-09-20 00:10:39'),
(4, '15689090398492576', 1, 8499, 0, 0, NULL, 0, '', '', '', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, '2019-09-19 23:58:18', '2019-09-19 23:58:18'),
(5, '15689096266448452', 1, 115, 1, 2, '2019-09-20 00:13:52', 1, '', '', '', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, '2019-09-20 00:08:04', '2019-09-20 00:13:52'),
(6, '15691645776131562', 7, 7998, 1, 1, '2019-09-22 23:05:53', 1, '', '', '', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, '2019-09-22 22:57:15', '2019-09-22 23:05:53'),
(7, '15691648465397435', 7, 13998, 1, 2, '2019-09-22 23:07:38', -1, '', '', '', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, '2019-09-22 23:01:44', '2019-09-22 23:02:10'),
(8, '15691649071896878', 7, 1246, 1, 1, '2019-09-22 23:08:31', 1, '', '', '', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, '2019-09-22 23:02:45', '2019-09-22 23:08:31'),
(9, '15691649748362177', 7, 25656, 1, 1, '2019-09-22 23:09:39', 4, '', '', '', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, '2019-09-22 23:03:52', '2019-09-22 23:50:45'),
(10, '15691652286194502', 7, 16197, 0, 0, NULL, 0, '', '', '', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, '2019-09-22 23:08:06', '2019-09-22 23:08:06'),
(11, '15692210075967186', 6, 5999, 1, 2, '2019-09-23 17:03:05', 1, '', '', '', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, '2019-09-23 14:43:27', '2019-09-23 17:03:05'),
(12, '15692218454123239', 6, 7245, 0, 0, NULL, 0, '', '', '', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, '2019-09-23 14:57:25', '2019-09-23 14:57:25'),
(13, '15692225252983527', 6, 5488, 1, 2, '2019-09-23 15:38:54', 1, '', '', '', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, '2019-09-23 15:08:45', '2019-09-23 15:38:54'),
(14, '15692291639125640', 6, 9046, 1, 2, '2019-09-23 16:59:32', -1, '', '', '', '杭州市西湖区xx小区x幢419 十三 137xxxx2709', 0, '2019-09-23 16:59:23', '2019-09-23 16:59:40'),
(15, '15692295348262843', 6, 65, 1, 2, '2019-09-23 17:06:17', 1, '', '', '', '杭州市西湖区xx小区x幢419 十三 137xxxx2709', 0, '2019-09-23 17:05:34', '2019-09-23 17:06:17'),
(16, '15692298037679052', 6, 15233, 1, 2, '2019-09-23 17:10:08', 1, '', '', '', '上海浦东新区XX路XX号 999 137xxxx7797', 0, '2019-09-23 17:10:03', '2019-09-23 17:10:08'),
(17, '15694781962831307', 7, 1246, 1, 2, '2019-09-26 14:10:12', -1, '', '', '', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, '2019-09-26 14:09:56', '2019-09-26 14:10:22'),
(18, '15698039249771093', 7, 3199, 0, 0, NULL, 0, '', '', '', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, '2019-09-30 08:38:26', '2019-09-30 08:38:26'),
(19, '15702783557537865', 7, 6819, 0, 0, NULL, 0, '', '', '', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, '2019-10-05 20:20:10', '2019-10-05 20:20:10'),
(20, '15702847670935185', 6, 3999, 1, 2, '2019-10-05 22:13:03', 1, '', '', '', '上海浦东新区XX路XX号 999 137xxxx7797', 0, '2019-10-05 22:12:47', '2019-10-05 22:13:03'),
(21, '16196934677762020', 1, 999, 0, 0, NULL, 0, '', '', '', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, '2021-04-29 18:51:07', '2021-04-29 18:51:07'),
(22, '16196938741217814', 1, 999, 0, 0, NULL, 0, '', '', '', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, '2021-04-29 18:57:54', '2021-04-29 18:57:54');

-- --------------------------------------------------------

--
-- 表的结构 `tb_leaves_mall_order_item`
--

CREATE TABLE `tb_leaves_mall_order_item` (
  `order_item_id` bigint(20) NOT NULL COMMENT '订单关联购物项主键id',
  `order_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '订单主键id',
  `goods_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联商品id',
  `goods_name` varchar(200) NOT NULL DEFAULT '' COMMENT '下单时商品的名称(订单快照)',
  `goods_cover_img` varchar(200) NOT NULL DEFAULT '' COMMENT '下单时商品的主图(订单快照)',
  `selling_price` int(11) NOT NULL DEFAULT 1 COMMENT '下单时商品的价格(订单快照)',
  `goods_count` int(11) NOT NULL DEFAULT 1 COMMENT '数量(订单快照)',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `tb_leaves_mall_order_item`
--

INSERT INTO `tb_leaves_mall_order_item` (`order_item_id`, `order_id`, `goods_id`, `goods_name`, `goods_cover_img`, `selling_price`, `goods_count`, `create_time`) VALUES
(35, 21, 10918, 'MONTY', 'goods-img/440799828_edit_98147273450647.jpg', 999, 1, '2021-04-29 18:51:07'),
(36, 22, 10918, 'MONTY', 'goods-img/440799828_edit_98147273450647.jpg', 999, 1, '2021-04-29 18:57:54');

-- --------------------------------------------------------

--
-- 表的结构 `tb_leaves_mall_shopping_cart_item`
--

CREATE TABLE `tb_leaves_mall_shopping_cart_item` (
  `cart_item_id` bigint(20) NOT NULL COMMENT '购物项主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键id',
  `goods_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联商品id',
  `goods_count` int(11) NOT NULL DEFAULT 1 COMMENT '数量(最大为5)',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标识字段(0-未删除 1-已删除)',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '最新修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `tb_leaves_mall_shopping_cart_item`
--

INSERT INTO `tb_leaves_mall_shopping_cart_item` (`cart_item_id`, `user_id`, `goods_id`, `goods_count`, `is_deleted`, `create_time`, `update_time`) VALUES
(76, 1, 10918, 1, 1, '2021-04-28 22:20:02', '2021-04-28 22:20:02'),
(77, 1, 10918, 1, 1, '2021-04-28 22:36:47', '2021-04-28 22:37:02'),
(78, 1, 10918, 1, 1, '2021-04-29 18:48:15', '2021-04-29 18:48:15'),
(79, 1, 10918, 1, 1, '2021-04-29 18:57:48', '2021-04-29 18:57:48'),
(80, 1, 10918, 1, 0, '2021-04-30 09:59:22', '2021-04-30 09:59:22');

-- --------------------------------------------------------

--
-- 表的结构 `tb_leaves_mall_user`
--

CREATE TABLE `tb_leaves_mall_user` (
  `user_id` bigint(20) NOT NULL COMMENT '用户主键id',
  `nick_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `login_name` varchar(11) NOT NULL DEFAULT '' COMMENT '登陆名称(默认为手机号)',
  `password_md5` varchar(32) NOT NULL DEFAULT '' COMMENT 'MD5加密后的密码',
  `introduce_sign` varchar(100) NOT NULL DEFAULT '' COMMENT '个性签名',
  `address` varchar(100) NOT NULL DEFAULT '' COMMENT '收货地址',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '注销标识字段(0-正常 1-已注销)',
  `locked_flag` tinyint(4) NOT NULL DEFAULT 0 COMMENT '锁定标识字段(0-未锁定 1-已锁定)',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '注册时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- 转存表中的数据 `tb_leaves_mall_user`
--

INSERT INTO `tb_leaves_mall_user` (`user_id`, `nick_name`, `login_name`, `password_md5`, `introduce_sign`, `address`, `is_deleted`, `locked_flag`, `create_time`) VALUES
(1, '十三', '13700002703', 'e10adc3949ba59abbe56e057f20f883e', '我不怕千万人阻挡，只怕自己投降', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, 0, '2019-09-22 08:44:57'),
(6, '测试用户1', '13711113333', 'dda01dc6d334badcd031102be6bee182', '测试用户1', '上海浦东新区XX路XX号 999 137xxxx7797', 0, 0, '2019-08-29 10:51:39'),
(7, '测试用户2测试用户2测试用户2测试用户2', '13811113333', 'dda01dc6d334badcd031102be6bee182', '测试用户2', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, 0, '2019-08-29 10:55:08'),
(8, '测试用户3', '13911113333', 'dda01dc6d334badcd031102be6bee182', '测试用户3', '杭州市西湖区xx小区x幢419 十三 137xxxx2703', 0, 0, '2019-08-29 10:55:16');

--
-- 转储表的索引
--

--
-- 表的索引 `tb_leaves_mall_admin_user`
--
ALTER TABLE `tb_leaves_mall_admin_user`
  ADD PRIMARY KEY (`admin_user_id`) USING BTREE;

--
-- 表的索引 `tb_leaves_mall_carousel`
--
ALTER TABLE `tb_leaves_mall_carousel`
  ADD PRIMARY KEY (`carousel_id`) USING BTREE;

--
-- 表的索引 `tb_leaves_mall_goods_category`
--
ALTER TABLE `tb_leaves_mall_goods_category`
  ADD PRIMARY KEY (`category_id`) USING BTREE;

--
-- 表的索引 `tb_leaves_mall_goods_info`
--
ALTER TABLE `tb_leaves_mall_goods_info`
  ADD PRIMARY KEY (`goods_id`) USING BTREE;

--
-- 表的索引 `tb_leaves_mall_index_config`
--
ALTER TABLE `tb_leaves_mall_index_config`
  ADD PRIMARY KEY (`config_id`) USING BTREE;

--
-- 表的索引 `tb_leaves_mall_order`
--
ALTER TABLE `tb_leaves_mall_order`
  ADD PRIMARY KEY (`order_id`) USING BTREE;

--
-- 表的索引 `tb_leaves_mall_order_item`
--
ALTER TABLE `tb_leaves_mall_order_item`
  ADD PRIMARY KEY (`order_item_id`) USING BTREE;

--
-- 表的索引 `tb_leaves_mall_shopping_cart_item`
--
ALTER TABLE `tb_leaves_mall_shopping_cart_item`
  ADD PRIMARY KEY (`cart_item_id`) USING BTREE;

--
-- 表的索引 `tb_leaves_mall_user`
--
ALTER TABLE `tb_leaves_mall_user`
  ADD PRIMARY KEY (`user_id`) USING BTREE;

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `tb_leaves_mall_admin_user`
--
ALTER TABLE `tb_leaves_mall_admin_user`
  MODIFY `admin_user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id', AUTO_INCREMENT=4;

--
-- 使用表AUTO_INCREMENT `tb_leaves_mall_carousel`
--
ALTER TABLE `tb_leaves_mall_carousel`
  MODIFY `carousel_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '首页轮播图主键id', AUTO_INCREMENT=8;

--
-- 使用表AUTO_INCREMENT `tb_leaves_mall_goods_category`
--
ALTER TABLE `tb_leaves_mall_goods_category`
  MODIFY `category_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类id', AUTO_INCREMENT=1262;

--
-- 使用表AUTO_INCREMENT `tb_leaves_mall_goods_info`
--
ALTER TABLE `tb_leaves_mall_goods_info`
  MODIFY `goods_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品表主键id', AUTO_INCREMENT=109462;

--
-- 使用表AUTO_INCREMENT `tb_leaves_mall_index_config`
--
ALTER TABLE `tb_leaves_mall_index_config`
  MODIFY `config_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '首页配置项主键id', AUTO_INCREMENT=42;

--
-- 使用表AUTO_INCREMENT `tb_leaves_mall_order`
--
ALTER TABLE `tb_leaves_mall_order`
  MODIFY `order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单表主键id', AUTO_INCREMENT=23;

--
-- 使用表AUTO_INCREMENT `tb_leaves_mall_order_item`
--
ALTER TABLE `tb_leaves_mall_order_item`
  MODIFY `order_item_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单关联购物项主键id', AUTO_INCREMENT=37;

--
-- 使用表AUTO_INCREMENT `tb_leaves_mall_shopping_cart_item`
--
ALTER TABLE `tb_leaves_mall_shopping_cart_item`
  MODIFY `cart_item_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '购物项主键id', AUTO_INCREMENT=81;

--
-- 使用表AUTO_INCREMENT `tb_leaves_mall_user`
--
ALTER TABLE `tb_leaves_mall_user`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户主键id', AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
