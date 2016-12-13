#1、需要生成model、dao、service、controller等层次代码。

#2、model层生成时需要准备数据表信息，表信息至少包含表名、主键信息、常规字段。字段中至少需要包含字段名，字段类型、getter、setter等信息。
##2.1创建TableInfo和Column Info类
##2.2pring boot 配置JDBC,连接数据库
##2.3读取数据表，封装table Info和column Info
##2.4读取modelVM 生成代码

#3、dao层需要准备数据连接信息（JdbcTemplate或SqlSessionFactory等），常规的增删改查方法（适配泛型）。
##3.1利用entity读取表名、主键、字段名等关键信息
###3.1.1利用泛型得到当前entity类型，读取jpa注解
##3.2得到所需信息后拼出crud的sql

#4、service层需要准备dao层注入，常规的增删改查方法。

#5、controller层需要准备service层注入，常规的增删改查方法。

#6、生成器需要配置模板路径、不同层次文件生成路径等。