# 数据库设计文档
## 项目名: GoCooking
## 数据库名称：OnlineStoreDB

## 版本 2.0
## 创建日期: 2023-07-20
## 修改日期: 2023-08-25
## 修改者: Pocket

## 表结构
### dish_table 表
描述：存储菜品信息

| 字段名      | 数据类型       | 空值约束   | 主键 | 描述 |
|------------|---------------|----------|------|------|
| dish_id    | INT           | NOT NULL | ✔    | 菜品唯一标识符 |
| name       | VARCHAR(50)   | NOT NULL |      | 菜品名称 |
| difficulty | VARCHAR(10)   | NOT NULL |      | 菜品难度 |
| creat_id   | INT           | NOT NULL |      | 创建者外键 | 

#### 约束
- 主键: dish_id
- 外键: creat_id (user_table 表的 id)

#### 注释
- `difficulty`: 取值范围
    - '简单'
    - '中等'
    - '复杂'
  
#### 索引
- 前缀索引: `dish_name_index` name 列, 索引长度`2`

### user_table 表
| 字段名      | 数据类型       | 空值约束   | 主键  | 描述 |
|------------|---------------|----------|------|------|
| id         | INT           | NOT NULL | ✔    | 用户唯一标识符 |
| username   | VARCHAR(50)   | NOT NULL |      | 用户名 |
| password   | VARCHAR(50)   | NOT NULL |      | 密码 |
| gender     | INT           | NOT NULL |      | 性别外键 |
| age        | INT           | NOT NULL |      | 年龄外键 |
| province   | INT           | NOT NULL |      | 省份外键 | 

#### 约束
- 主键: id
- 外键:
    - gender (user_info_gender 表的 id)
    - age (user_info_age 表的 id)
    - province (user_info_province 表的 id)
    
- 唯一约束: username

### use_info_age
| 字段名      | 数据类型       | 空值约束   | 主键  | 描述 |
|------------|---------------|----------|------|------|
| id         | INT           | NOT NULL | ✔    |年龄唯一标识符 |
| age        | VARCHAR(20)   | NOT NULL |      | 年龄段 |

### use_info_gender
| 字段名      | 数据类型       | 空值约束   | 主键  | 描述 |
|------------|---------------|----------|------|------|
| id         | INT           | NOT NULL | ✔    | 性别唯一标识符 |
| gender     | VARCHAR(20)   | NOT NULL |      | 性别 |

### use_info_province
| 字段名      | 数据类型       | 空值约束   | 主键  | 描述 |
|------------|---------------|----------|------|------|
| id         | INT           | NOT NULL | ✔    | 省份唯一标识符 |
| province   | VARCHAR(20)   | NOT NULL |      | 省份 |

### ingredient_table
| 字段名      | 数据类型       | 空值约束   | 主键  | 描述 |
|------------|---------------|----------|------|------|
| ingredient_id | INT   | NOT NULL | ✔    | 配料唯一标识符 |
| category_id   | INT   | NOT NULL |      | 种类外键 |
| ingredient_name   | VARCHAR(50)   | NOT NULL |      | 配料名 |

#### 索引
- 联合索引: `ingredient_name_category_index` ingredient_name列, category_id列

#### 约束
- 主键: ingredient_id 
- 外键: category_id
- 唯一约束: ingredient_name

### step_table
| 字段名      | 数据类型       | 空值约束   | 主键  | 描述 |
|------------|---------------|----------|------|------|
| id | INT   | NOT NULL   | ✔    | 步骤唯一标识符 |
| dish_id    | INT   | NOT NULL |      | 菜品外键 |
| step       | INT   | NOT NULL |      | 步骤记数 |
| description   | VARCHAR(200)   | NOT NULL |      | 详细描述 |

#### 约束
- 主键: id
- 外键: dish_id

### ingredient_category_table
| 字段名      | 数据类型       | 空值约束   | 主键  | 描述 |
|------------|---------------|----------|------|------|
| ingredient_category_id | INT   | NOT NULL   | ✔    | 配料分类唯一标识符 |
| category_name    | VARCHAR(10)   | NOT NULL |      | 分类名 |

#### 注释
- category_name: 取值范围 (`主料`, `配料`, `辅料`, `其他`)

### dish_ingredient_table
| 字段名      | 数据类型       | 空值约束   | 主键  | 描述 |
|------------|---------------|----------|------|------|
| dish_id | INT   | NOT NULL   |     | 菜品id |
| ingredient_id    | INT   | NOT NULL |      | 配料id |

## 表关系

| 主表         | 关联字段    | 从表          | 描述                           |
|--------------|-------------|---------------|------------------------------|
| dish_table    | dish_id | ingredient_table        | 一对多，一个菜品可以有多个配料 |
| user_table    | id | dish_table        | 一对多，一个用户可以创建多个菜品 |
| ingredient_table    | category_id | ingredient_category_id        | 一对一，一个配料对应一个类别 |
| dish_table    | dish_id | step_table        | 一对多，一个菜品可以有多个步骤 |
| user_table    | id | user_info_age        | 一对一，一个用户一个年龄 |
| user_table    | id | user_info_gender        | 一对一，一个用户一个性别 |
| user_table    | id | user_info_province       | 一对一，一个用户一个省份 |


## 更新历史
- 1.0: 初始设计 (2023-6-24)
- 2.0: 完善功能, 添加更多支持业务的表 (2023-8-25)
