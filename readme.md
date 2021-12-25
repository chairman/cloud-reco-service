docker run -p 6379:6379 --name myredis -v /Users/apple/docker/redis/conf/redis.conf:/etc/redis/redis.conf -v /Users/apple/docker/redis/data:/data -d redis redis-server /etc/redis/redis.conf --appendonly yes

docker run -itd --name mongo -p 27017:27017 mongo --auth
docker exec -it mongo mongo admin