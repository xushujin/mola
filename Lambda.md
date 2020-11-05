# Lambda学习

### List之间转换
```java
public static void main(String[] args) {
    List<Group> groups = Stream.of(
            Group.builder().name("许书进").build(),
            Group.builder().name("许黑炭").build()
    ).collect(Collectors.toList());
    List<String> list = groups.stream().map(o -> o.getName()).collect(Collectors.toList());
    log.info("groups:{}", groups);
    log.info("list:{}", list);
}
```