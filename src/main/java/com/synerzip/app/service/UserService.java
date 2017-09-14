package com.synerzip.app.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.synerzip.app.model.Result;
import com.synerzip.app.model.User;

@Service
public class UserService {

	@Autowired
	MongoTemplate mongoTemplate;

	public List<User> getUsers() {
		return mongoTemplate.findAll(User.class);
	}

	public String saveUser(User user) {
		mongoTemplate.insert(user);
		return "record saved!!";
	}

	public User getUser(int id) {
		return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)),
				User.class);
	}

	public String updateUser(int id, User user) {
		mongoTemplate.save(user);
		return "record updated!!";
	}

	public void deleteUser(int id) {
		mongoTemplate
				.remove(new Query(Criteria.where("id").is(id)), User.class);
	}

	public User getByEmail(String email) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));
		User users = mongoTemplate.findOne(query, User.class);
		return users;
	}

	public String doMapReduce() {
		String map = UserService.read("/map.js");
		String reduce = UserService.read("/reduce.js");
		MapReduceResults<Result> mapReduceResults = mongoTemplate.mapReduce(
				"users", map, reduce, MapReduceOptions.options()
						.outputCollection("AggregationByMaxPrice"),
				Result.class);
		System.out.println(mapReduceResults.getTiming());
		return "map reduce done!!";
	}

	public static String read(String path) {
		StringBuffer str = new StringBuffer();
		try {
			BufferedReader bufferedReader= new BufferedReader(new FileReader(
					new ClassPathResource(path).getFile()));
			String line = bufferedReader.readLine();
			while (line != null) {
				str.append(line);
				str.append("\n");
				line = bufferedReader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str.toString();
	}

}
