# BOS
基于itheimaSSH阶段物流管理项目


## 语言类问题

1.基类中需要创建一个派生类声明的泛型对象,我们肯定知道这个泛型对象是在派生类生成时就创建,那么可以用lateinit来声明该对象,我们也可以在init{}代码块中对这个对象进行初始化,那么就可以省略声明对象时的lateinit声明,并且在声明时也可以省略掉可以为空(不过我们得在基类声明反省时让T继承Any,这时它才是不能为空的)

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



2.forEach{}代码块中可以用it或者this代表遍历到的对象

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



3.Hibernate生成一对多的Set时报错,Kotlin的类和Java的类不能互相引用(比如SubArea和Area是多对一的关系,一个是Kotlin类一个Java类会报一个Set泛型引起的错误)



4.想让Struts中页面的参数设置到对应Action的同名field时,field可以不写setXXX方法,但该field的权限修饰符不能是private,可以是默认或open,那这时它自带的setter/getter就能被调用

```kotlin
   	// 使用属性驱动接收页面提交的分页参数
    open var page: Int = 0// 当前页码
    open var rows: Int = 0// 每页显示的记录数
```





## 项目遇到的问题



SubArea没有name字段,如何让生成的json带有name字段?

```
1.如果是使用json-lib的话,我们给SubArea添加一个getName方法,并返回province+city+district即可;
2.如果是使用gson,首先我们要知道gson不是用getXXX方法获取值来生成json,所以就算写getXXX方法也没法生成相应json,但找了好久没找到怎么生成自定义字段
3.
如果用fastJson,也是添加一个getName方法并返回province+city+district即可;
用fastJson的时候,序列化json的时候想忽略字段可以添加一个注解:@JSONField(serialize = false)
```



spring报错:not a managed type class spring;

```xml
<!-- 可能是配置jpa扫描的包没有把你新建的实体类扫描到,在property-name="packagesToScan"指定一下你的实体类位置 -->

<bean id="entityManagerFactory"
          class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <!-- 指定扫描的包，存放实体类 -->
        <property name="packagesToScan"
                  value="com.itheima.bos.domain" />

</bean>
```



Hibernate生成对应一对多的Set时报错;

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







## IDEA的问题

创建pom模块的时候没有选web项目,main文件夹下没有webapp,复制了一份web.xml过来,我明明写了servlet节点和对应的servlet-mapping节点,却被编译器报错:说我servlet没有对应的mapping节点,这是因为IDEA没有将这个web.xml识别为该项目的配置文件,在ProjectStructure添加web.xml作为配置文件即可

![servletneedmapping](mdsrc\servletneedmapping.png)



项目跑起来想更新classes或resources时可以在run窗口点击Update application,但有时候没有更新选项,只能重新发布或者重启服务器,这比较慢,结果这个问题可以重新配置Run Configuration;

如果是在Deployment中配置的是war包,那IDEA就只能让你选择Restart Server或redeploy,如果我们选择的是war exploded,那就可以补充其服务器,快速更新classes or resources

![servletneedmapping](mdsrc\updateclasses.png)

