package com.newer.respository;

import com.newer.pojo.RequestMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 只需定义该接口，即可完成CRUD操作
 */
public interface LoggerRepository extends MongoRepository<RequestMessage,String> {

}
