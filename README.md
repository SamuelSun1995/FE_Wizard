# FE_Wizard
## 图标使用
https://www.iconfont.cn/collections/detail?spm=a313x.7781069.1998910419.d9df05512&cid=748
## 权限相关
Kotlin 协程 一行代码动态申请权限（CoroutinePermissions）
https://www.jianshu.com/p/c1008263e574
RxPermissions
https://juejin.cn/post/6844904205400932365

## MVVM搭建
https://juejin.cn/post/6844903955693043725
https://github.com/ccaong/JetpackDemo
- DataBinding
- Lifecycles

## 知识
Kotlin各种语法等
Jetpack：主要是ViewModel、LifeCycle、LiveData、Room、ViewBinding
Kotlin协程
思考哪些地方可能会存在多线程带来的线程同步问题以及处理方案
Retrofit+OkHttp


Jetpack从入门到几乎入门
REF：https://juejin.cn/post/7149594482730598408
https://juejin.cn/post/7150940351174279198
https://juejin.cn/post/7150960510203068452

## kotlin基本语法

### 变量后面的？表示变量可以为空
```agsl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
```

### val(value)和var(variable)的区别
- 使用var(variable)声明变量,可以被重新赋值,引用可以变。
- 使用val(value)声明常量, 引用不可变。
  - val修饰的变量，相当于Java中final修饰的变量;

### 全局变量
java
```agsl
private final int num1 = 10;
private final String str1= "1234";
private int num2;
private String str2;
private Student student;

public static final int num3 = 10;
public static final String str1 = "str";
public static int num4 = 11;
public static int num5 ;
public static String str2;
```

kotlin

```agsl
private val num1 = 10
private val str1= "1234"

private var num2 = 0 // kotlin中没有int，也没有Integer，只有Int，需要默认给 0
private num2 by Delegates.notNull<Int>() // 其他如Long，Double，Float等数字类型相似

private lateinit var str2: String
private var str2: String? = null

private var student: Student? = null // 实体、类等基本如此定义
private lateinit var student: Student

companion object {
    val num3 = 10
    val str1 = "str"
    var num4 = 11
    var num5 = 0 // 另外定义方法看前面
    var str2: String? = null // 另外定义方法看前面
}
```

函数
表达式：fun 方法名(参数名1: 数据类型, 参数名2: 数据类型): 返回值数据类型{

}

##  Lifecycle基本使用
https://zhuanlan.zhihu.com/p/533420346
作为生命周期感知组件、它的作用就是监听宿主Activity/Fragment，然后派发给观察者。这句看似简单的概括，却倒出3个重要的角色：
宿主，观察者，用来派发的调度器

## Kotlin的静态方法
https://www.jianshu.com/p/a71f41186aa0
