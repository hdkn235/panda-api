package com.hd.api.utils.db;

import com.hd.utils.common.PropsUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

/**
 * MongoDB工具类 Mongo实例代表了一个数据库连接池，即使在多线程的环境中，一个Mongo实例对我们来说已经足够了
 * 注意Mongo已经实现了连接池，并且是线程安全的。 设计为单例模式， 因
 * MongoDB的Java驱动是线程安全的，对于一般的应用，只要一个Mongo实例即可， Mongo有个内置的连接池（默认为10个）
 * 对于有大量写和读的环境中，为了确保在一个Session中使用同一个DB时， DB和DBCollection是绝对线程安全的
 * 
 * @author hoda
 * @since 2016-8-5
 */
public enum MongoDBUtil {

	/**
	 * 定义一个枚举的元素，它代表此类的一个实例
	 */
	instance;

	private static final String CONFIG_PATH = "src/main/resources/api.properties";

	private MongoClient mongoClient;

	private static String dbHost;

	private static String dbUser;

	private static String dbPwd;

	private static String dbName;

	static {
		dbHost = PropsUtil.getString(CONFIG_PATH, "mongodb_host");
		dbUser = PropsUtil.getString(CONFIG_PATH, "mongodb_user");
		dbPwd = PropsUtil.getString(CONFIG_PATH, "mongodb_password");
		dbName = PropsUtil.getString(CONFIG_PATH, "mongodb_dbname");

		String sURI = String.format("mongodb://%s:%s@%s/%s", dbUser, dbPwd, dbHost, dbName);
		MongoClientURI uri = new MongoClientURI(sURI);
		instance.mongoClient = new MongoClient(uri);
	}

	/**
	 * 获取collection对象 - 指定Collection
	 * 
	 * @param collName
	 * @return
	 */
	public MongoCollection<Document> getCollection(String collName) {
		MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collName);
		return collection;
	}

	/**
	 * 查找对象 - 根据主键_id
	 * 
	 * @param collection
	 * @param id
	 * @return
	 */
	public Document findById(MongoCollection<Document> coll, String id) {
		ObjectId _idobj = null;
		try {
			_idobj = new ObjectId(id);
		} catch (Exception e) {
			return null;
		}
		Document myDoc = coll.find(Filters.eq("_id", _idobj)).first();
		return myDoc;
	}

	/**
	 * 统计总记录数
	 * 
	 * @param coll
	 * @return
	 */
	public int getCount(MongoCollection<Document> coll) {
		int count = (int) coll.count();
		return count;
	}

	/**
	 * 条件查询
	 * 
	 * @param coll
	 * @param filter
	 * @return
	 */
	public MongoCursor<Document> find(MongoCollection<Document> coll, Bson filter) {
		return coll.find(filter).iterator();
	}

	/**
	 * 条件查询
	 * 
	 * @param coll
	 * @param filter
	 * @return
	 */
	public MongoCursor<Document> find(MongoCollection<Document> coll, Bson filter, Bson fields, Bson sort) {
		return coll.find(filter).iterator();
	}

	/**
	 * 条件查询
	 * 
	 * @param coll
	 * @param filter
	 * @return
	 */
	public Document findOne(MongoCollection<Document> coll, Bson filter, Bson fields, Bson sort) {
		return coll.find(filter).projection(fields).sort(sort).first();
	}

	/**
	 * 分页查询
	 * 
	 * @param coll
	 * @param filter
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public MongoCursor<Document> findByPage(MongoCollection<Document> coll, Bson filter, int pageNo, int pageSize) {
		Bson orderBy = new BasicDBObject("_id", 1);
		return findByPage(coll, filter, orderBy, pageNo, pageSize);
	}

	/**
	 * 分页查询
	 * 
	 * @param coll
	 * @param filter
	 * @param orderBy
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public MongoCursor<Document> findByPage(MongoCollection<Document> coll, Bson filter, Bson orderBy, int pageNo,
			int pageSize) {
		return coll.find(filter).sort(orderBy).skip((pageNo - 1) * pageSize).limit(pageSize).iterator();
	}

	/**
	 * 删除Document-通过ID
	 * 
	 * @param coll
	 * @param id
	 * @return
	 */
	public int deleteById(MongoCollection<Document> coll, String id) {
		int count = 0;
		ObjectId _id = null;
		try {
			_id = new ObjectId(id);
		} catch (Exception e) {
			return 0;
		}
		Bson filter = Filters.eq("_id", _id);
		DeleteResult deleteResult = coll.deleteOne(filter);
		count = (int) deleteResult.getDeletedCount();
		return count;
	}

	/**
	 * 修改Document-通过ID
	 * 
	 * @param coll
	 * @param id
	 * @param newdoc
	 * @return
	 */
	public Document updateById(MongoCollection<Document> coll, String id, Document newdoc) {
		ObjectId _idobj = null;
		try {
			_idobj = new ObjectId(id);
		} catch (Exception e) {
			return null;
		}
		Bson filter = Filters.eq("_id", _idobj);
		coll.updateOne(filter, new Document("$set", newdoc));
		return newdoc;
	}

	/**
	 * 关闭Mongodb
	 */
	public void close() {
		if (mongoClient != null) {
			mongoClient.close();
			mongoClient = null;
		}
	}

	/**
	 * 测试入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		String collName = "runningData";
		MongoCollection<Document> coll = MongoDBUtil.instance.getCollection(collName);

		// 插入多条
		for (int i = 1; i <= 4; i++) {
			Document doc = new Document();
			doc.put("name", "zhoulf");
			doc.put("school", "NEFU" + i);
			Document interests = new Document();
			interests.put("game", "game" + i);
			interests.put("ball", "ball" + i);
			doc.put("interests", interests);
			coll.insertOne(doc);
		}

		// // 根据ID查询
		// String id = "556925f34711371df0ddfd4b";
		// Document doc = MongoDBUtil2.instance.findById(coll, id);
		// System.out.println(doc);

		// 查询多个
		// MongoCursor<Document> cursor1 = coll.find(Filters.eq("name",
		// "zhoulf")).iterator();
		// while (cursor1.hasNext()) {
		// org.bson.Document _doc = (Document) cursor1.next();
		// System.out.println(_doc.toString());
		// }
		// cursor1.close();

		// 查询多个
		// MongoCursor<Person> cursor2 = coll.find(Person.class).iterator();

		// 删除数据库
		// MongoDBUtil2.instance.dropDB("testdb");

		// 删除表
		// MongoDBUtil2.instance.dropCollection(dbName, collName);

		// 修改数据
		// String id = "556949504711371c60601b5a";
		// Document newdoc = new Document();
		// newdoc.put("name", "时候");
		// MongoDBUtil.instance.updateById(coll, id, newdoc);

		// 统计表
		// System.out.println(MongoDBUtil.instance.getCount(coll));

		// 查询所有
		// Bson filter = Filters.eq("count", 0);
		// MongoDBUtil.instance.find(coll, filter);

	}

}