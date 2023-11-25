/**** Connect to MongoDB ****/
// Since 2.10.0, uses MongoClient
MongoClient mongo = new MongoClient("localhost", 27017);

/**** Get database ****/
// if database doesn't exists, MongoDB will create it for you
DB db = mongo.getDB("geeksforgeeks");

/**** Get collection / table from 'geeksforgeeks' ****/
// if collection doesn't exists, MongoDB will create it for you
DBCollection table = db.getCollection("authors");
