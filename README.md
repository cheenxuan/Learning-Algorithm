### 数据结构

#### 数据结构概述

* 线性结构

1. 线性结构作为最常用的数据结构，其特点是数据元素之间存在一对一的线性关系
2. 线性结构有两种不同的存储结构，即顺序存储结构和链式存储结构。顺序存储的线性表称为顺序表，顺序表中的存储元素是连续的。
3. 链式存储的线性表称为链表，链表中的存储元素不一定是连续的，元素节点中存放数据元素以及相邻元素的地址信息。
4. 线性结构常见的有：数据、队列、链表和栈。

* 非线性结构

1. 非线性结构包括：二维数组、多维数组、广义表、树结构、图结构。

#### 稀疏数组和队列

##### 稀疏数组（SparseArray）

> 当一个数组大部分元素为0，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。
>
> 稀疏数组的处理方法是：
>
> 1. 记录数组一个有几行几列，有多少个不同的值
> 2. 把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模。

#### 队列

##### 队列

> 队列是一个有序列表，可以用数组或是链表来实现。
>
> 遵循先入先出的原则。

#### 链表

##### 单链表

> 链表是有序的列表，链表是以节点的方式来存储的，是链式存储。
>
> 每个节点包含data域，next域：指向下一个节点
>
> 链表的各个节点不一定是连续存储。
>
> 链表分带头节点的链表和头节点的链表

##### 双向链表

