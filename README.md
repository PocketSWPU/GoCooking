# GoCooking
## 项目背景
纯练手项目，目标秋招Offer

## 项目介绍
GoCooking是个给厨房新手的辅助工具，记录做一个菜需要的配料和步骤，同时提供对某个做某些菜需要采购的材料的速记提示功能。

## 技术栈
- 前端：Vue、Element-UI
- 后端：SpringBoot3.0.2、Java17、Redis、MySql、MyBatis

## 已实现功能一览

### 菜品版块
![image](https://github.com/PocketSWPU/GoCooking/assets/107466625/65ee52dd-86a7-4d1f-8c6e-a2606492900b)
#### 新增
![image](https://github.com/PocketSWPU/GoCooking/assets/107466625/243ff600-75c3-4d92-a8a5-c456ca223b4b)

### 详情版块
![image](https://github.com/PocketSWPU/GoCooking/assets/107466625/dc0313b0-eef7-4386-9598-1efa43d3cb4f)
#### 新增步骤
![image](https://github.com/PocketSWPU/GoCooking/assets/107466625/e5db5fda-54e0-4772-9f1d-d7248d9e3e5d)
#### 配料修改
![image](https://github.com/PocketSWPU/GoCooking/assets/107466625/781013d0-2319-452d-818e-abb15f6a1615)


### 采购版块
![image](https://github.com/PocketSWPU/GoCooking/assets/107466625/0b929a96-d00f-4c60-829d-eaa0c0c45ffa)


## 开发记录
- 2023-08-11: 新增修改配料功能demo，暂时完成回显，后续补充提交功能。
- 2023-08-10: 用Redis优化了新增菜品的实现。把每个配料用Redis的Hash对象存储，新增菜品时，需判断数据库内是否已有相应配料的记录，改为Redis大量减少了和数据库的交互。
- 2023-08-9: 优化菜品详情页面，实现了菜品步骤详情的功能。
