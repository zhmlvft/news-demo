#[ng2-news](http://github.com/zhmlvft/ng2-news)的服务端
采用spring-boot开发，使用showapi的新闻api。
##快速启动
###1.修改application.properties中的数据库配置信息，创建所需的数据库（news）。
###2.将application.properties文件中的spring.datasource.initialize改为true.
###3.运行NewsDemoApplicationTests中的loadNewsType测试方法，开始导入新闻数据进入本地数据库中。
###4.启动项目，运行NewsDemoApplication。