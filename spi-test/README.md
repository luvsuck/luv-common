1. ## 本实验包含四个模块

   1. `spi-interface` 由nasa提供的标准SPI接口(指定接口规范)
   2. `landing-spacex` 由spaceX提供的着陆实现方案(服务提供1)
   3. `landing-blue` 由BlueOrigin提供的着陆实现方案(服务提供2)
   4. `landing-test` 测试模块，在pom里依赖不同服务提供者来测试着陆

   ## 实现SPI的步骤

   1. 制定标准: 在spi-interface里编写基础功能接口(solid原则，对修改关闭对扩展开放)并且编写loader加载器通过ServiceLoader来加载服务提供类
   2. 实现扩展: 在服务提供者里引入spi-interface包，并且对功能进行完善、扩展。然后在resource/META-INF/services/ 以spi接口全限定类名   新建一个文件，内容为服务提供者锁实现类的全限定类名
   3. 三方使用: 引入具体的服务提供者的包，调用实现。

   ## Java SPI 机制实现的核心

   1. SPI接口里 `ServiceLoader.load(LandingOnTheMoon.class);`
   2. 获取到一个ServiceLoader实例，该实例是一个`Iterable`接口的实现(允许使用增强for-each)，这个步骤最主要的是初始化实例配置
   3. 遍历loader(next()、hasNext())来触发服务提供者懒加载机制 ，来 发现与加载服务提供者实例
