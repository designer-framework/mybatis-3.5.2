package org.designer.ibatis.plugin;

import lombok.extern.log4j.Log4j;
import org.apache.ibatis.builder.ExamplePlugin;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.Properties;

/**
 * @description:
 * @author: Designer
 * @date : 2021/4/30 18:04
 * {@link org.apache.ibatis.session.Configuration#newParameterHandler(MappedStatement, Object, BoundSql) 更多可以拦截的对象需要看该方法最近的几十行代码}
 */
@Log4j
@Intercepts(value = @Signature(type = Executor.class, method = "commit", args = {boolean.class}))
public class PluginImpl extends ExamplePlugin implements Plugin {

  @Override
  public int add(String str) {
    return 0;
  }

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    log.info("重写该方法来增强目标方法调用");
    return super.intercept(invocation);
  }

  @Override
  public Object plugin(Object target) {
    return super.plugin(target);
  }

  @Override
  public Properties getProperties() {
    return super.getProperties();
  }

  @Override
  public void setProperties(Properties properties) {
    super.setProperties(properties);
  }
}
