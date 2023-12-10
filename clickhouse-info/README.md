# Информация о clickhouse
## Структура таблиц
Была выбрана следующая структура событий:

Таблица событий
- page - название страницы  
- tagName - какой элемент(тэг элемента) был выбран  
- className - название класса элемента  
- textContent - содержимое элемента  
- timestamp (время в миллисекундах) - временная метка  
  
##  Создание базы данных и таблиц в clickhouse  
`create database pp` - создание базы данных pp  
`create table pp.stat_metric (id UUID, tagName String, className String, textContent String, timestamp Int64) engine = MergeTree() order by timestamp;` - создание таблицы с вышеописанной структурой
## Развертывание на локальной машине (windows)
https://clickhouse.com/docs/knowledgebase/install-clickhouse-windows10