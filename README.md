# 运行环境说明 #
1.Vue前端 
源码在FaceDatabaseFrontend目录下，已用Nginx完成部署，nginx-1.21.4为Nginx目录

2.Springboot后端和Flask后端
硬件及环境要求：
显卡：NVIDIA cuda 10.0
Java版本：Java 8及以上
Python版本：3.5.5
Pytorch(GPU)版本 1.2.0
torchvision版本：0.4.0
运行：
Springboot后端源码在SpringbootBackend目录下，执行SpringbootBackend/target/FaceDatabase-1.0.0-SNAPSHOT.jar
以运行Springboot后端
Flask后端源码在FlaskBackend目录下，执行FlaskBackend/flask_main.py以运行Flask后端

3.数据库:MySql数据库
运行DatabaseInitialization.sql完成数据库表初始化