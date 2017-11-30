# BOS
基于itheimaSSH阶段物流管理项目




Kotlin入门也好久了,但好像还是不熟悉,熟悉一个语言还是用起来会熟悉更快,这是第二个Kotlin敲的项目,想着又要踩不少之前没踩过的坑,就做个记录,顺便分享分享,
毕竟总会有人有点收获?享受了享受不用写部分繁杂模板代码的甜头,也尝试着多用Kotlin的操作符/语法/本家lib的方法





## IDEA的问题

### 使用前的准备

我觉得不管用个啥软件,一开始该做的事情是看看它的快捷键,不管怎么说大部分快捷键都是比用鼠标操作要来得快,看快捷键最好去官网看:[Keyboard Shortcuts You Cannot Miss](https://www.jetbrains.com/help/idea/keyboard-shortcuts-you-cannot-miss.html) ,还有个[极客学院](http://wiki.jikexueyuan.com/project/intellij-idea-tutorial/keymap-introduce.html) 整理的中文版,官网那个是他们觉得用的最多最频繁的,极客学院那套好像是全部快捷键都给翻译过来了?

然后....就可以开始了.(以前搞Android的用起IDEA会觉得很熟悉,毕竟快捷键是一致的)



### 新建MavenWeb模块

以前课程上在Eclipse新建web模块的时候没有选择Create from archetype,在IDEA上我们创建web项目的时候最好从archetype创建,因为它能帮我们生成一些默认文件夹与文件;

我们选择maven webapp:

![mvnwebmodule01](/mdsrc/mvnwebmodule01.png)

上面这一步选好后先一直点下一步直到下图:

Content root不要用默认的,如果是默认的他会是直接放在父模块中,也就是会将子模块的pom.xml和父模块的pom.xml放一起,会创建模块失败,我们得手动的在Content root后追加"\你的模块名"

![mvnwebmodule02](/mdsrc/mvnwebmodule02.png)

接着就有webapp文件夹及默认的web.xml了

![mvnwebmodule03](/mdsrc/mvnwebmodule03.png)



### web项目的启动配置(tomcat)

在IDEA中如果想将web项目跑起来,需要配置一下运行设置,打开Run/Debug Configurations窗口,点击左上角的加号→Tomcat Server →Local新建tomcat configuration



![tomcatrunconfiguration01](/mdsrc/tomcatrunconfiguration01.png)



如果你的IDEA之前指定过tomcat,那第一个红圈里的tomcat就默认选中不用自己设置;

第二个红框里的端口号可以看自己的需求设置,如果只是单独一个项目就不用改端口号了;

最重要的第三个窗口,我们需要选择该tomcat run configuration需要发布个啥

![tomcatrunconfiguration02](/mdsrc/tomcatrunconfiguration02.png)



配置好后的可见下图,其实也就是点个加号把想发布的war包添加进来而已



![tomcatrunconfiguration03](/mdsrc/tomcatrunconfiguration03.png)



### servlet和servlet-mapping节点报错

创建模块的时候没有选web项目,main文件夹下没有webapp,复制了一份web.xml过来,我明明写了servlet节点和对应的servlet-mapping节点,却被编译器报错:说我servlet没有对应的mapping节点,这是因为IDEA没有将这个web.xml识别为该项目的配置文件,在ProjectStructure添加web.xml作为配置文件即可

![servletneedmapping](/mdsrc/servletneedmapping.png)



项目跑起来想更新classes或resources时可以在run窗口点击Update application,但有时候没有更新选项,只能重新发布或者重启服务器,这比较慢,结果这个问题可以重新配置Run Configuration;

如果是在Deployment中配置的是war包,那IDEA就只能让你选择Restart Server或redeploy,如果我们选择的是war exploded,那就可以补充其服务器,快速更新classes or resources

![updateclasses](/mdsrc/updateclasses.png)



### 注解扫描Component

IDEA和Spring一起用真的很舒服,不管是提示还是类的跳转都方便好看快捷,比如说Spring扫描Component:

不给Component注解时,该类会有波浪线提示该类未被使用,可以起到提醒开发者是不是忘记配置这个类/是不是忘记使用这个类的作用

![scan01](/mdsrc/componentscan01.png)

并且如果我们没配置注解,Spring配置文件中会红色提示该类未找到

![scan01](/mdsrc/componentscan03.png)



配置了Component注解后,我们可以看到该类的波浪线提示没了,类名颜色也充实了起来,并且在class左边还多了Spring图标,说明被Spring找到

![scan02](/mdsrc/componentscan02.png)





### IDEA中通过Spring自动注入

我们如果已经在Spring配置文件配置好了类或者通过注解扫描找到了类,我们可以通过IDEA自带的Generate生成Autowired依赖,快捷键是alt+Insert

![autoWired](/mdsrc/autowired01.png)

如果是我们写的是Kotlin代码,那IDEA还能自动帮我们添加lateinit关键字,Kotlin果然是亲女儿

这里有点小坑的就是,我这个模块依赖了另一个模块的WebService,注解没法扫描到,编译器会红色报错很丑,这里我就把错误压制了

![autoWired](/mdsrc/autowired02.png)



### 不正确导包引起ClassNotFound

以前刚开始用IDEA的时候遇到过明明在Modules里添加了jar包,运行起项目却说类找不到,也不知道那个时候是哪一步不正确,如果出现了这个问题,可以在Project Structure界面左下角的Problems解决,导包或者Module哪里有问题这里都有提示,点击Fix选一下基本就能解决绝大多数问题

![artific](/mdsrc/artific01.png)



## 语言类问题

```
Kotlin是一门比Java优雅的语言,很多时候用Java的模式写的代码,写了Java感觉的代码后IDEA如果有提示,
那就让它帮着转换下,一般来说它帮着格式化的代码会更Kotlin
```



### 基类的lateinit泛型对象创建

基类中需要创建一个派生类声明的泛型对象,我们知道这个泛型对象是在派生类生成时肯定会创建的,那么这个对象肯定不会为null,并且可以用lateinit来声明该对象,我们初始化该对象的操作可以放在init代码块中,这时lateinit声明也可以省掉

```kotlin
open class CommonAction<T : Any> : ActionSupport(), ModelDriven<T>, ServletResponseAware {
      private var model: T// 声明模型对象
      override fun getModel(): T = model

      // 在运行期动态获得泛型的具体类型，并通过反射创建model对象
      init {//init代码块用于初始化参数,有了init代码块可以省略lateinit修饰符
          // 获得父类（CommonAction）对应的类型
          val genericSuperclass = this.javaClass.genericSuperclass as ParameterizedType
          // 获得父类上声明的泛型类型数组
          val actualTypeArguments = genericSuperclass.actualTypeArguments
          // 获得实体类型
          val entityClass = actualTypeArguments[0] as Class<*>
          // 通过反射创建model对象
          @Suppress("UNCHECKED_CAST")
          model = entityClass.newInstance() as T
      }
  //code here...
}
```



### forEach和循环标记

forEach{}代码块中可以用it或者this代表遍历到的对象

```kotlin
 private fun getAreas(wb: HSSFWorkbook): ArrayList<Area> {
        val sheet = wb.getSheetAt(0)

        val areas = arrayListOf<Area>()
        sheet.forEach rows@ {
            if (it.rowNum == 0) return@rows
            val area = Area()
            val province = it.getCell(1).stringCellValue
            val city = it.getCell(2).stringCellValue           
          //...
            area.shortcode = PinYin4jUtils.getHeadByString(province + city + district).joinToString("")
            area.citycode = PinYin4jUtils.hanziToPinyin(city, "")
            areas.add(area)
        }
        return areas
    }
```



### Hibernate一对多Java类和Kotlin类不可互相引用

Hibernate生成一对多的Set时报错,Kotlin的类和Java的类不能互相引用(比如SubArea和Area是多对一的关系,一个是Kotlin类一个Java类会报一个Set泛型引起的错误)



### Struts属性注入

想让Struts中页面的参数设置到对应Action的同名field时,field可以不写setXXX方法,但该field的权限修饰符不能是private,可以是默认或open,那这时它自带的setter/getter就能被调用

```kotlin
 	//模型驱动对象直接返回
    private val order = Order()
    override fun getModel(): Order = order


    //基本数据类型好像可以不初始化,不初始化就给个lateinit
    open var page: Int = 0
    open var rows: Int = 0

    //引用数据类型给个lateinit等Struts注入就好,不用写setter干净利索
    lateinit var sendAreaInfo: String
    lateinit var recAreaInfo: String

```



### apply操作符的使用

以前java代码需要给创建的新对象手动赋值得这么做:

```java
					WorkBill workBill = new WorkBill();				
					workBill.setAttachbilltimes(0);
					workBill.setBuildtime(new Date());
					workBill.setCourier(courier);
					workBill.setOrder(order);
					workBill.setPickstate("新单");
					workBill.setRemark(order.getRemark());
					workBill.setSmsNumber("123");
					workBill.setType("新");
```

用apply操作符的话我们可以省一大串的对象引用(比如这里的workBill),眼尖的能看到下面有一行代码用到了this关键字,这是因为我在外部代码块也有一个order,为了区分对象,在apply里可以用this指定当前对象,想了解更多操作符相关内容,可以看看https://www.zeljko.link/2017/07/kotlin-also-apply-let-run.html

```kotlin
val workBill = WorkBill().apply { 
                    attachbilltimes = 0
                    buildtime = Date()
                    courier = order.courier
                    this.order = order
                    pickstate = Constants.WORKBILLPICKSTATE_NEW
                    remark = order.remark
                    type = Constants.WORKBILLTYPE_NEW
                    smsNumber = order.sendMobile
                }
```





### Lambda表达式

还是写Android的时候匿名类多,JavaEE在基础阶段基本没啥机会碰到,好不容易碰到个就也截个图扔上来表示自己会用好了??

没用lambda之前需要先new一个匿名对象,实现一下未实现的方法:

![lambda01](/mdsrc/msglambda01.png)

用了lambda后,魔法PIU一下写一行代码创建的对象就已经是返回值了,好像也没啥好说的,就是省代码,省的不要不要的:

![lambda01](/mdsrc/msglambda02.png)

### Kotlin库里一些自带的好用方法

#### String的判null,判空,判空白

```kotlin
 		val isNull:String?=null
        println("isNull")
        println("isNullOrEmpty:${isNull.isNullOrEmpty()}")
        println("isNullOrBlank:${isNull.isNullOrBlank()}")
        println("===============================================")

        val isEmpty=""
        println("isEmpty")
        println("isNullOrEmpty:${isEmpty.isNullOrEmpty()}")
        println("isNullOrBlank:${isEmpty.isNullOrBlank()}")
        println("===============================================")

        val isSpace=" "
        println("isSpace")
        println("isNullOrEmpty:${isSpace.isNullOrEmpty()}")
        println("isNullOrBlank:${isSpace.isNullOrBlank()}")
```

上面代码输出结果:

```


isNullOrEmpty:true
isNullOrBlank:true
===============================================


isNullOrEmpty:true
isNullOrBlank:true
===============================================

isNullOrEmpty:false
isNullOrBlank:true


isNull---null的对象是null,是empty并且也是blank;反正如果已经是null就返回true了
isEmpty---空字符串的对象会被isEmpty判断该字符长度是否为0,长度为0则返回true
isSpace---带空格的对象会先被isNullOrBlank先判断长度是否为0,如果不是为0则判断它的自否是否都是空格
就真的是见名知意,不用自己做好几个判断然后来个&&或||
```




#### String的equals可忽略大小写

```kotlin
        val s1="abc"
        val s2="ABc"
        println(s1.equals(s2,true))//equals方法有重载,第二个参数代表是否要忽略大小写
        println(s1.equals(s2,false))
        println(s1.equals(s2))//判断字符串时IDEA推荐用==不用equals方法

```







## 项目知识点遇到的问题



### 怎么生成带自定义字段的json字符串?

```
1.如果是使用json-lib的话,我们给SubArea添加一个getName方法,并返回province+city+district即可;
2.如果是使用gson,首先我们要知道gson不是用getXXX方法获取值来生成json,所以就算写getXXX方法也没法生成相应json,但找了好久没找到怎么生成自定义字段,换fastJson了
3.
如果用fastJson,也是添加一个getName方法并返回province+city+district即可;
用fastJson的时候,序列化json的时候想忽略字段可以添加一个注解:@JSONField(serialize = false)
```



### spring报错:not a managed type class spring

```xml
<!-- 可能是配置jpa扫描的包没有把你新建的实体类扫描到,
在property-name="packagesToScan"指定一下你的实体类位置 -->

<bean id="entityManagerFactory"
          class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <!-- 指定扫描的包，存放实体类 -->
        <property name="packagesToScan"
                  value="com.itheima.bos.domain" />

</bean>
```



### Hibernate生成对应一对多的Set时报错

```java
package com.mkyong.stock.model;
//...
@Entity
@Table(name = "stock", catalog = "mkyong")
public class Stock implements java.io.Serializable {

    //下面的写法会报错
    //private Set stockCategories = new HashSet(0);
    //指定set泛型后可以解决报错
	private Set<stockCategory> stockCategories = new HashSet<>(0);
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stock")
    public Set getStockCategories() {
	return this.stockCategories;
    }

    public void setStockCategories(Set stockCategories) {
	this.stockCategories = stockCategories;
    }
}
```







