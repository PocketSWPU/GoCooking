# GoCooking
## 项目背景
纯练手项目，目标秋招Offer

## 项目文档

[数据库设计文档](https://github.com/PocketSWPU/GoCooking/blob/master/docs/DatabaseDesignDoc.md)

[Redis使用记录](https://github.com/PocketSWPU/GoCooking/blob/master/docs/RedisListDoc.md)

## 项目介绍
GoCooking是个给厨房新手的辅助工具，记录做一个菜需要的配料和步骤，同时提供对某个做某些菜需要采购的材料的速记提示功能。

## 技术栈
- 前端：Vue、Element-UI
- 后端：SpringBoot3.0.2、Java17、Redis、MySql、MyBatis

## 已实现功能一览

### 推荐功能
![image](https://github.com/PocketSWPU/GoCooking/assets/107466625/01eb778b-c7d0-4fdb-a058-1009d18bd5da)

点击后进行推荐

![image](https://github.com/PocketSWPU/GoCooking/assets/107466625/e656996d-5c91-46ac-aec3-e1f19165eb5e)

推荐算法: 为了在Java中方便使用，采用了轻量级的推荐算法。灵感来源于基于内容的协同过滤(Content-based RS)和基于人口信息的推荐系统(Demographic-based RS)，通过用户特征计算用户间的相似度，然后进行推荐。
推荐思路有：
- 基于相似用户的随机推荐
- 基于相似用户的喜好推荐 (用烹饪次数代表喜欢)


### 菜品版块
![image](https://github.com/PocketSWPU/GoCooking/assets/107466625/65ee52dd-86a7-4d1f-8c6e-a2606492900b)
#### 新增
![image](https://github.com/PocketSWPU/GoCooking/assets/107466625/243ff600-75c3-4d92-a8a5-c456ca223b4b)

菜品对于每个用户是独立的，然而配料可以共用，所以用享元模式优化了配料对象的创建销毁，增加效率

### 详情版块
![image](https://github.com/PocketSWPU/GoCooking/assets/107466625/d4ef9352-9480-48ed-b891-eadeaea9a442)

#### 新增步骤
![image](https://github.com/PocketSWPU/GoCooking/assets/107466625/e5db5fda-54e0-4772-9f1d-d7248d9e3e5d)
#### 配料修改
![image](https://github.com/PocketSWPU/GoCooking/assets/107466625/781013d0-2319-452d-818e-abb15f6a1615)

### 首页

#### 排行榜
![image](https://github.com/PocketSWPU/GoCooking/assets/107466625/299727eb-98df-4167-92ae-2fe2c81c185c)

Redis实现排行榜, 再以菜品id为键，菜名为值加一层redis缓存

### 采购版块
![image](https://github.com/PocketSWPU/GoCooking/assets/107466625/0b929a96-d00f-4c60-829d-eaa0c0c45ffa)


## 开发记录
- 2023-08-28: 新增排行榜功能，新增烹饪次数。用享元模式优化了配料对象的创建过程。
- 2023-08-22: 新增推荐功能
- 2023-08-18: 补全配料修改功能
- 2023-08-11: 新增修改配料功能demo，暂时完成回显，后续补充提交功能。
- 2023-08-10: 用Redis优化了新增菜品的实现。把每个配料用Redis的Hash对象存储，新增菜品时，需判断数据库内是否已有相应配料的记录，改为Redis大量减少了和数据库的交互。
- 2023-08-9: 优化菜品详情页面，实现了菜品步骤详情的功能。
