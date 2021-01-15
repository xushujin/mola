# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/maven-plugin/reference/html/#build-image)

### 创建数据库
use admin;
db.createUser(
  {
    user: "admin",
    pwd: "it_password",
    roles: [ { role: "readWrite", db: "admin" } ]
  }
);


use mola_auth;
db.createUser(
  {
    user: "admin",
    pwd: "it_password",
    roles: [ { role: "readWrite", db: "mola_auth" } ]
  }
);
