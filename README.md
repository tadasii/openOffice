# openOffice demo

> SpringBoot实现office文件预览

jobconverter结合OpenOffice把office文件转为pdf文件、通过pdf.js实现在线预览pdf文件。（核心代码直接跳PdfController层）


### 1.	导入依赖

```xml
<!-- openOffice 和 jobconverter-->
<dependency>
    <groupId>org.jodconverter</groupId>
    <artifactId>jodconverter-core</artifactId>
    <version>4.2.2</version>
</dependency>
<dependency>
    <groupId>org.jodconverter</groupId>
    <artifactId>jodconverter-spring-boot-starter</artifactId>
    <version>4.2.2</version>
</dependency>
<dependency>
    <groupId>org.jodconverter</groupId>
    <artifactId>jodconverter-local</artifactId>
    <version>4.2.2</version>
</dependency>
<!-- openOffice end-->
```

### 2.  安装OpenOffice
OpenOffice下载地址: http://www.openoffice.org/download/

**（Linux）安装**

*1.下载安装包到Linux opt文件夹*

*2.解压：tar -zxvf Apache_OpenOffice_4.1.6_Linux_x86-64_install-rpm_zh-CN.tar.gz*

*3.进入cd zh-CN/RPMS*

*4.安装：rpm -ivh *.rpm*

*5.进入openOffice安装目录，命令：cd /opt/openoffice4/program/*

*6.开启服务（永久启动）：soffice --headless --accept=“socket,host=127.0.0.1,port=8100;urp;” --nofirststartwizard &*

*7.查看进程：netstat -lnp |grep 8100*

*8.查询指定端口是否已开（开放8100端口）： firewall-cmd --query-port=8100/tcp*

​    *提示 yes，表示开启；no表示未开启*

*9.添加指定需要开放的端口：firewall-cmd --add-port=8100/tcp --permanent*

*10.重载入添加的端口：firewall-cmd --reload*

*11.查询指定端口是否开启成功：firewall-cmd --query-port=8100/tcp*

**（Windows）安装**

1.一直按下一步就ok

2.启动服务，cmd进入安装目录

```cmd
cd C:\Program Files (x86)\OpenOffice 4\program soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard
```
