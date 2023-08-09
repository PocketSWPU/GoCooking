# 数据库建表分析

## 菜品表
`dishes_table`
- `dish_id`: 主键, id
- `name`: 菜名
- `time`: 耗时 String
- `difficulty`: 难易度，易中难

## 材料表
`ingredients_table`
- `ingredient_id`: 主键, id
- `category_id`: 外键, 种类id
- `ingredient_name`: 材料名

## 材料分类表
`ingredient_category_table`
- `ingredient_category_id`: 主键，材料分类id
- `category_name`: 分类名
Id，分类【取值：主料、辅料、调料】

## 菜品材料表
`dish_ingredient_table`
- `dish_id`
- `ingredient_id`
菜品ID，材料ID


## 步骤表
`step_table`

- `id`： 表主键
- `dish_id`: 菜品主键
- `step`: 步骤编号
- `description`: 文本描述

啊啊
