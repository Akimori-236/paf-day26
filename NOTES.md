## OLAP vs OLTP

## OLTP

relational database - licensing is expensive

due to transactions enforcing consistency

## OLAP

OLAP generally used  on archivlal data

faster for large amount of data

data warehousing

## columnal database

records in columns instead of rows

also for archival as need to move columns to add data

PIVOTING

usually they conpress the columns

cost-effect relationship

economist prediction if one factor rises or falls

## Graph database

usually foreign keys to create relationship

cannot do temporary relationship

### DAG directed acyclic graph

# NoSQL

## BASE

Basically Available SoftState EventualConsistency

can tolerate partitioning , splitting into clusters

**BA** - some clusters can fail and the thing will still run

**S** - data will change by itself

**E** - insert/update now, and then read now, wont give back what u inserted immediately (testing problems?)

## MongoDB

**document DB**.. not **key-value DB**

stores JSON

also accepts CSV files

**complex data** = data without fixed schema

## Primary Key `_id= ObjectId("...")`

can supply your own, if not it will be auto-incremental

DANGER => spelling errors will go through as it will create a new table/collection for you

easy for node.js - Express (like springboot)

run command in `bin` folder

```
mongoimport 'mongodb://localhost:27017"
	--drop			// drop table if exists
	-d shows    		// DB name
	-c tv			// collection name
	--jsonArray			// file data type
	--file ../tv-shows.json	// filename

```

mongo shell `mongosh`

`show dbs`  / `show databases`

ctrl+L to clear screen

`use <databasename>` to go in DB -- if no DB a new one is created

`db.<collectionname>.`

Selection with mongo:filter / springboot:criteria

```
db.tv.find({
    language: "English",
    status: "Ended"
})
```

#### ignore case

```
db.tv.find({
    language: {
        $regex: 'english',
        $options: 'i'
    }
})
```

LIKE

```
db.tv.find({
    name: {
        $regex: 'crystal',
        $options: 'i'
    }
})
```

get crime or supernatural with rating more or equal to 9

```
db.tv.find({
    genres: {
        $in: ['Crime', 'Supernatural']
    },
    "rating.average": {$gte:9}
})
```

get names, no id,  of crime or supernatural genre, with rating more or equal to 9

```
db.tv.find({
    genres: {
        $in: ['Crime', 'Supernatural']
    },
    "rating.average": {$gte:9}
}).projection({
    _id:0,
    name:1
})
```

## CONVERSION

Document doc = ...

JsonArrayReader jar = Json.createReader(

    new StringReader( doc.Json());

JsonObject jobj = jar.readObject(); 

JsonArray jarr = jar.readArray();

);


# SCHEMA

rdbms - Schema on write.. schema needs to be already there to write data in

mongo - Schema on read
