/**
 * Copyright 2009-2021 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.designer.ibatis;

import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.designer.ibatis.entity.SmsRecord;
import org.designer.ibatis.mapper.SmsRecordMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.InputStream;

/**
 * @description:
 * @author: Designer
 * @date : 2021/4/29 18:42
 */
@Log4j
@RunWith(JUnit4.class)
public class MyBatisTest {

  private static final String RESOURCES_PREFIX = "resources";

  public static final String CONFIG_PATH = RESOURCES_PREFIX + "/mybatis-config.xml";

  @Test
  public void testSqlSessionFactory() {
    //ObjectWrapperFactory
    //ReflectorFactory
    try (InputStream inputStream = MyBatisTest.class.getClassLoader().getResource(CONFIG_PATH).openStream();) {
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      try (SqlSession session = sqlSessionFactory.openSession()) {
        log.info("第一次查询");
        SmsRecordMapper smsRecordMapper = session.getMapper(SmsRecordMapper.class);
        log.info("第一次查询结果：" + smsRecordMapper.selectSms(1).get(0));
        log.info("第二次查询");
        session.commit();
        SmsRecord smsRecord = session.selectOne("org.designer.ibatis.mapper.SmsRecordMapper.selectSms", 1);
        log.info("第二次查询结果：" + smsRecord);
        session.commit();
        //session.close();
        log.info("第三次查询");
        SmsRecord smsRecord1 = session.selectOne("org.designer.ibatis.mapper.SmsRecordMapper.selectSms", 1);
        log.info("第三次查询结果： " + smsRecord1);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
