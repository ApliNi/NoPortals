## NoPortals `v0.0`
用于阻止特定世界创建下界和末地传送门的 Minecraft 服务器插件

---

本机版本: [1.19], 经过测试的版本: [1.19.2], 预计支持版本: [1.19+].


**功能**
1. 阻止特定世界创建下界和末地传送门


**指令**
- `/noportals` 显示插件信息
    - `/noportals reload` 重新加载配置


**配置**
```yaml
# 需要禁用传送门的世界名, 区分大小写 | The world name of the portal needs to be disabled, case-sensitive
# 将阻止下界传送门和末地传送门的创建
worlds:
  - world
  - world_nether
```
