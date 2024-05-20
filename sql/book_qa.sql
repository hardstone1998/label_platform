/*
 Navicat Premium Data Transfer

 Source Server         : 36.112.150.185
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : 36.112.150.185:3305
 Source Schema         : geo-db

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 20/05/2024 11:11:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book_qa
-- ----------------------------
DROP TABLE IF EXISTS `book_qa`;
CREATE TABLE `book_qa`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `book_class_id` int(11) NULL DEFAULT NULL COMMENT '标题id',
  `title_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '标题内容',
  `qa1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问答对1',
  `qa2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问答对2',
  `qa3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问答对3',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据插入时间',
  `updata_time` datetime(0) NULL DEFAULT NULL COMMENT '数据更新时间',
  `is_delete` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除',
  `is_mark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否已标注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_qa
-- ----------------------------
INSERT INTO `book_qa` VALUES (4, 38, '第一节  矿产、矿业经济、我国矿产资源矿产是人类社会赖以生存和发展的一种重要物质基础。人类很早就已开发利用天然矿物资源。早期社会以石器时代、青铜器时代、铁器时代划分, 正是反映了矿产利用对社会生产力发展的决定作用。随着生产力的发展和社会生活的进步, 人们对矿产开发的规模不断扩大, 对矿产利用的程度不断提高。近代的工业化也可以说是以矿产资源为基础发展起来的, 表现在欧美主要工业国家的工业革命大多是在拥有煤、铁的地区建立起一批钢铁工业和机械制造业从而带动了整个工业经济的成长。随后, 又是有丰富石油资源的一些国家进入了工业化国家的行列。工业现代化极大地加速了矿产的消费, 带动了对矿产的更大需求。一个统计说明, 从 1812 年～1912 年, 即大致为工业革命头 100 年间, 在人口增长三倍的情况下, 铜消耗增长 80 倍, 铁消耗量增长 100 倍, 突破了在此以前矿产消耗与人口成比例增长的规律( 朱训等, 1999)。还有人估算, 近半个世纪以来的几十年间, 矿产开采量已经超过了人类历史几千年中开采量的总和。20 世纪 60 ～ 70 年代矿产勘查仍蓬勃发展, 但进入 80 年代以来出现了矿业投入降低, 矿业经济萎缩的趋势。不过, 要看到在广大的发展中国家, 尤其是新兴工业化国家, 矿产的消耗量则仍保持迅速增长, 例如, 亚太地区这一时期内铜的消耗量年均增长 2 . 2 % , 钢的消耗量年均增长 8 . 4 % 。另据 31 届国际地质大会资料, 认为到 2050年世界对金属矿产的需求量估计仍将会有成倍增长( 陈毓川, 1999)。人类利用矿产的种类是逐渐扩大的, 从金属的利用看, 1850 年前只应用了 Fe、Cu、Pb、Zn、Au、Ag、Sn、Hg 等为数不多的十来种, 到 20 世纪初 Mn、Ni、Al、W、 Mg 开始较大量地为工业利用, 在前 50 年内又增加了包括 Ti、Cr、Co、U 等十几种, 而稀有分散金属、稀土金属是 60 年代以来逐渐得到应用的。同样, 传统的非金属矿产只有硫、磷、石棉、云母等少数几种, 随着现代工业的发展, 人们应用在冶金辅助材料、化工及化肥原料、光学原料、陶瓷及玻璃工业原料、建筑及水泥原料、宝石及工艺美术材料等方面约近百种工业矿物与岩石。而像矽线石、蓝晶石、红柱石等高铝矿物, 沸石、硅灰石、透辉石、钠质膨润土、海泡石粘土、凹凸棒石粘土等矿物又都是随着科学进步及新的需求成为新的非金属矿产的。矿产主要产于地下, 是一种地下资源。因此, 开发矿业首先是要找到产出地点。然后进行必要的地质勘查工作。其目的是提高对地下矿产分布的控制程度, 并取得能够合理回收的肯定评价。经过勘查, 使潜在的资源变为现实的可供开发利用的矿石储量。矿产开发进一步的工作包括采矿、选矿等生产环节的矿山建设和相关的冶炼加工企业的建设。矿业开发在国民经济建设中居基础产业地位, 这是因为它担负着为工业、农业和其他产业部门提供所需的矿产品及其初级加工制品等原材料的任务。对一个国家和地区来说, 矿产资源状况是决定其经济建设方针、布局、规模的重要条件, 对其经济发展的速度也有重要影响。从世界来看, 矿产的利用已达到很高的水平, 1962 年世界原矿生产产值为 2350 亿美元, 1973 年为 4300 亿美元, 1983 年为 9890 亿美元, 而 1989 年已达到 11250 亿美元, 即在 20 多年中增长了近 5 倍( 朱训等, 1999 )。一个国家矿产资源开发利用是形成其综合国力的重要组成部分, 是生产力水平和社会发展水平的体现。现今发达国家人均矿产消耗量已达到 1015 吨, 目前世界上已形成西欧、北美和亚太地区三大矿产消费中心。亚太地区近 20 年来矿产消费增长很快, 但矿产耗用强度和人均矿产消费量仍比较低。我国 50 多年来矿产人均消耗量已从不到一吨增长到今天的五吨。我国目前及可预见的未来一段时间, 95 % 的能源和 75 % 的工业原料还是要取自矿产资源, 经济建设的持续快速发展必然要求矿产资源量的更大增长来保证建设的需求。开发矿业是一项具有其自身特点的经济活动, 其中涉及到价值、成本、长期投资、矿业权、矿产的国际流通等问题。矿产品的价值除与金属或非金属矿本身的价值高低、采掘、加工、运输的成本有关外, 还必然受到市场供需关系和价格变动的影响。矿山建设主要根据矿床的储量及分布确定生产规模与生产年限、制定采矿方案、配置基础设施, 由此而确定首期和长期的资金投入。然而由于受地质条件、采掘、选矿、冶炼效率、运输成本等诸多方面因素的影响, 矿业经济效率总是具有相当的不确定性。矿石的短缺是常常会出现的问题, 尤其到一个矿床开采后期要及时进行补充勘查, 以期有新的矿体发现, 延长矿山的寿命。为此,有的地区在开采期间, 即按其收益的一定比例预留下来这项资金。现在, 人们也已普遍认识到由于矿产开发会带来各种环境问题。因而, 在矿业开发的投入中必须考虑地面勘探、开采、选矿、冶炼中所需的环境保护措施与治理费用。矿业权包括探矿权和采矿权, 是经济转轨中的一个核心问题, 在我国矿业开发领域里, 国有矿山企业为主体, 同时, 也有多种经济成分并存, 正确处理好矿业权管理中的关系, 对于改善投资环境、吸收外资、完善市场运行机制、实现矿业资产合理流转、促进矿产勘查和开发工作良性循环是非常重要的。有些矿产显著局限在世界少数地区, 因此, 各国资源的互相补充也是世界经济发展的一个重要条件。国际间矿产品贸易增长迅速, 1963 年贸易额为 400 亿美元, 1987 年增长到 4423亿美元, 29 年间增长了约 11 倍( 朱训等, 1999)。在世界经济一体化的潮流中, 国际间矿产品的贸易及矿产勘查开发中各种形式的国际合作必将更快的发展起来。中国是世界上少数几个矿产资源较为丰富、种类较为齐全的国家之一。截止 2002 年底, 我国现已发现 171 种, 其中 158 种都已有了探明储量 ( 陈毓川, 2005)。有多种矿产的探明储量在世界矿产总储量中占重要位置。据 1991 年估算, 我国矿产资源总值可能居世界第三位, 但人均资源占有量则远低于世界平均水平, 位居世界第 53 位。在主要金属、非金属矿产中, 我国拥有储量居世界前列或具相当优势的有:钨、锡、钼、锑、稀土、钽、铌、铍、锂、芒硝、菱镁矿、重晶石、膨润土、耐火粘土、石棉、萤石、滑石、石膏、石墨、煤, 其中锑、锡储量和产量长期居于世界之首, 稀土储量占世界储量的五分之四, 滑石、石膏产量占世界一、二位。菱镁矿是最大资源国和生产出口国。煤矿探明储量及 1990 年原煤产量居世界之首。我国较丰富的矿产还有铁、铝、铅、锌、汞、硫、硼、高岭土、珍珠岩、磷。但铜、铬铁矿、钴、铂族、金刚石、钾盐、石油和天然气一直是短缺的矿产。50 多年来, 我国矿产资源勘查工作基本保证了国民经济建设各个时期对矿产的需求, 但随着经济建设规模的不断扩大, 人民生活水平的显著提高, 矿产资源的消耗量迅速增加, 一些重要矿产供需矛盾已日趋突出。例如目前铁矿石和锰矿石自给率为 80 % 左右, 铜精矿为 38 % , 铬铁矿为 14 % , 钾盐为 6 % , 相应矿产品进口数字明显提高, 并形成较大的进出口贸易逆差。据中国地质科学院对 21 世纪初期我国矿产资源需求和增长潜力所作的研究认为: 五种传统大宗金属矿产绝对需求量仍不断增加, 富铁、铜的供需缺口将加大。铝的前景不确定。冶金用的合金元素、非常用有色金属、稀有稀土金属、贵金属需求量加大, 其中铬明显短缺, 金、银、锰、镍也有不足。绝大部分非金属矿产需求旺盛; 磷、钾需求增长更快, 钾盐资源不足; 金刚石、石墨、重晶石、高岭土、膨润土需求量快速上升 , 除金刚石资源不足外, 其他供应形势尚好。能源矿产中, 煤、天然气供需关系趋向好转, 石油储量、产量增长将低于需求量的增长。我们要正确认识我国矿产资源的形势, 既看到矿产资源的优势和潜力, 也要看到当前供需矛盾加大的严峻形势。合理的加大地质矿产勘查工作的投入, 争取勘查工作有新的突破。同时, 要做到开源与节流并重, 保护矿产资源, 合理节约地使用矿产, 改进管理, 革新技术, 解决矿产开发过程中资源浪费问题, 实现可持续发展。其次, 还要执行好立足国内为主, 国外为辅, 利用国内外两种资源的方针, 通过与亚太和世界主要矿产生产国家矿产品的贸易和其他形式的国际合作, 努力改善在进出口贸易中的地位。', NULL, NULL, NULL, '2024-05-20 10:21:35', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

ALTER TABLE `asr-identify`.`geo_book_qa` 
MODIFY COLUMN `qa1` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '问答对1' AFTER `title_content`,
MODIFY COLUMN `qa2` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '问答对2' AFTER `qa1`,
MODIFY COLUMN `qa3` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '问答对3' AFTER `qa2`,
ADD COLUMN `verity_qa1` text NULL COMMENT '审核后的qa1' AFTER `qa3`,
ADD COLUMN `verity_qa2` text NULL COMMENT '审核后的qa2' AFTER `verity_qa1`,
ADD COLUMN `verity_qa3` text NULL COMMENT '审核后的qa3' AFTER `verity_qa2`,
ADD COLUMN `task_owner` varchar(255) NULL COMMENT '任务拥有者' AFTER `is_mark`,
ADD COLUMN `task_id` bigint(0) NULL COMMENT '任务id' AFTER `task_owner`,
ADD COLUMN `clazz_id` bigint(0) UNSIGNED NULL COMMENT '类别id' AFTER `task_id`,
ADD COLUMN `label_user` varchar(30) NULL COMMENT '标注人' AFTER `clazz_id`,
ADD COLUMN `label_time` datetime(0) NULL COMMENT '标注时间' AFTER `label_user`,
ADD COLUMN `verity_user` varchar(30) NULL COMMENT '审核人' AFTER `label_time`,
ADD COLUMN `verity_time` datetime(0) NULL COMMENT '审核时间' AFTER `verity_user`,
ADD COLUMN `is_pass` int(1) NULL COMMENT '是否通过。1通过，0不通过' AFTER `verity_time`,
ADD COLUMN `verity_feedback` varchar(1024) NULL COMMENT '审核意见' AFTER `is_pass`,
ADD COLUMN `accuracy` int(255) NULL COMMENT '正确率*100' AFTER `verity_feedback`,
ADD COLUMN `verity_clazz_id` int(0) NULL COMMENT '审核后分类' AFTER `accuracy`,
ADD COLUMN `insert_batch_id` int(0) UNSIGNED NULL COMMENT '插入批次id' AFTER `verity_clazz_id`,
ADD CONSTRAINT `pk_geo_book_qa_insert_batch` FOREIGN KEY (`insert_batch_id`) REFERENCES `asr-identify`.`insert_batch` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
ADD CONSTRAINT `pk_geo_book_qa_label_user_sysuser` FOREIGN KEY (`label_user`) REFERENCES `asr-identify`.`sys_user` (`user_name`) ON DELETE SET NULL ON UPDATE SET NULL,
ADD CONSTRAINT `pk_geo_book_qa_task_allocation` FOREIGN KEY (`task_id`) REFERENCES `asr-identify`.`task_allocation` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
ADD CONSTRAINT `pk_geo_book_qa_verity_user_sysuser` FOREIGN KEY (`verity_user`) REFERENCES `asr-identify`.`sys_user` (`user_name`) ON DELETE SET NULL ON UPDATE SET NULL;